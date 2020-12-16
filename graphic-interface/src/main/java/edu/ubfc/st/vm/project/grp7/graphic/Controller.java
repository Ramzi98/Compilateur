package edu.ubfc.st.vm.project.grp7.graphic;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.Subscription;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable{
    @FXML
    private CodeArea codeAreaMiniJaja;
    @FXML
    private CodeArea codeAreaJajaCode;
    @FXML
    private TabPane tabPaneCode;
    @FXML
    private Tab tabMiniJaja;
    @FXML
    private Tab tabJajaCode;
    @FXML
    private TreeView folderTreeView;
    @FXML
    private TextArea areaRun;
    @FXML
    private TextArea areaError;
    @FXML
    private TextArea areaDebug;
    @FXML
    private Tab areaErrorTab;
    @FXML
    private Tab areaRunTab;

    private ExecutorService colorSyntaxique = Executors.newSingleThreadExecutor();

    private InterpreterMiniJajaModel interpreterMiniJajaModel;
    private InterpreterJajaCodeModel interpreterJajaCodeModel;
    private CompilerModel compilerGraphic;
    private Memory memory;

    private FileModel fileModel;

    public String currentFileMiniJaja;
    private String currentFileJajaCode;

    private CodeArea currentArea;
    private String  currentFile;

    ExecutorService executor;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interpreterMiniJajaModel = new InterpreterMiniJajaModel(areaRun,areaError,areaDebug,codeAreaMiniJaja);
        interpreterJajaCodeModel = new InterpreterJajaCodeModel(areaRun,areaError,areaDebug,codeAreaJajaCode);
        compilerGraphic = new CompilerModel(interpreterJajaCodeModel, interpreterMiniJajaModel);
        fileModel = new FileModel(codeAreaMiniJaja,codeAreaJajaCode);

        setCurrent();

        syntaxiqueColor(codeAreaJajaCode);
        syntaxiqueColor(codeAreaMiniJaja);

        folderTreeView.getSelectionModel().selectedItemProperty().addListener(
            (v, oldValue, newValue) -> {
            saveFile(null);
            File f2 = new File(getPathFromTree(v.getValue().toString()));
            if (f2 != null) {
                try {
                    setCurrentFile(f2.getAbsolutePath()) ;
                    InputStream flux = new FileInputStream(f2.getAbsoluteFile());
                    InputStreamReader lecture = new InputStreamReader(flux);
                    BufferedReader buff = new BufferedReader(lecture);
                    String ligne;
                    currentArea.clear();
                    while ((ligne = buff.readLine()) != null) {
                        currentArea.appendText(ligne);
                        currentArea.appendText(System.getProperty("line.separator"));
                    }
                    buff.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void openFile(ActionEvent actionEvent) {
        setCurrent();
        setCurrentFile(fileModel.openFile(currentArea));
    }

    @FXML
    public void openFolder(ActionEvent actionEvent) {
        TreeItem<File> root = fileModel.openFolder();
        folderTreeView.setRoot(root);
    }

    @FXML
    public void saveFileAs(ActionEvent actionEvent) {
        setCurrent();
        setCurrentFile(fileModel.saveFileAs(currentArea));
    }


    @FXML
    public void saveFile(ActionEvent actionEvent) {
        setCurrent();
        fileModel.saveFile(currentArea,currentFile);
    }


    @FXML
    public void newFile(ActionEvent actionEvent) {
        setCurrent();
        fileModel.newFile(currentArea,currentFile);
        setCurrentFile("");
    }

    @FXML
    public void compile(ActionEvent actionEvent) throws IOException {
        saveFile(actionEvent);
        compilerGraphic.compile(currentFileMiniJaja);
        selectTabPan(tabJajaCode);
        setCurrentFile(fileModel.saveFileAs(currentArea));
    }
    @FXML
    public void runCode(ActionEvent actionEvent) throws Exception {
        setCurrent();
        System.out.println("file : "+currentFile);
        fileModel.saveFile(currentArea,currentFile);
        run(false);
    }

    @FXML
    private void runWithDebug(ActionEvent actionEvent) throws Exception {
        setCurrent();
        fileModel.saveFile(currentArea,currentFile);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(()-> {
            run(true);
        });
        executor.shutdown();
    }

    private void syntaxiqueColor(CodeArea codeArea){
        Subscription cleanupWhenDone = codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if (t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);
    }

    private String getPathFromTree(String str){
        return str.split(" ")[3];
    }

    public Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        setCurrent();
        String text = currentArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return Pattern.computeHighlighting(text);
            }
        };
        colorSyntaxique.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        setCurrent();
        currentArea.setStyleSpans(0, highlighting);
    }


    public void run(boolean debug){
        setCurrent();
        memory = Memory.getInstance();

        if (currentArea.equals(codeAreaMiniJaja)){
            runMiniJaja(debug);
        }else{
            if(interpreterJajaCodeModel.getNodes().size()!=0){
                runJajaCode(debug);
            }
        }
    }

    public void runMiniJaja(boolean debug){
        if (interpreterMiniJajaModel.runAll(currentFile,debug, memory) == -1){
            selectTabPan(areaErrorTab);
        }else{
            selectTabPan(areaRunTab);
        }

    }

    public void runJajaCode(boolean debug){

        if (interpreterJajaCodeModel.runAll(debug,memory) == -1){
            selectTabPan(areaErrorTab);
        }else{
            selectTabPan(areaRunTab);
        }

    }

    public void nextBreakPoint(ActionEvent actionEvent) {
        setCurrent();
        if (currentArea.equals(codeAreaMiniJaja))
            interpreterMiniJajaModel.nextBreakPoint(actionEvent);
        else
            interpreterJajaCodeModel.nextBreakPoint(actionEvent);
    }

    public void step(ActionEvent actionEvent) {
        setCurrent();
        if (currentArea.equals(codeAreaMiniJaja))
            interpreterMiniJajaModel.step(actionEvent);
        else
            interpreterJajaCodeModel.step(actionEvent);
    }

    public void setCurrent(){
        if (getTabCodeSelected().equals(tabMiniJaja)) {
            currentArea = codeAreaMiniJaja;
            currentFile = currentFileMiniJaja;
        } else {
            currentArea = codeAreaJajaCode;
            currentFile = currentFileJajaCode;
            System.out.println("file : "+ currentFile);
        }
    }

    private void setCurrentFile(String path) {
        if (currentArea.equals(codeAreaMiniJaja)) {
            currentFileMiniJaja = path;
        } else {
            currentFileJajaCode = path;
        }
    }

    private Tab getTabCodeSelected(){
        return tabPaneCode.getSelectionModel().getSelectedItem();
    }

    public void selectTabPan(Tab tab){
        tab.getTabPane().getSelectionModel().select(tab);
    }


}