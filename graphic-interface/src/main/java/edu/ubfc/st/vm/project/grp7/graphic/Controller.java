package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.ASTParsingException;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.Subscription;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML
    private Tab areaDebugTab;

    private ExecutorService colorSyntaxique = Executors.newSingleThreadExecutor();


    private InterpreterMiniJajaModel interpreterMiniJajaModel;
    private InterpreterJajaCodeModel interpreterJajaCodeModel;
    private CompilerModel compilerGraphic;
    private Memory memory;

    public String currentFileMiniJaja;
    private String currentFileJajaCode;

    private CodeArea currentArea;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interpreterMiniJajaModel = new InterpreterMiniJajaModel(areaRun,areaError,areaDebug,codeAreaMiniJaja);
        interpreterJajaCodeModel = new InterpreterJajaCodeModel(areaRun,areaError,areaDebug,codeAreaJajaCode);
        compilerGraphic = new CompilerModel(interpreterJajaCodeModel, interpreterMiniJajaModel);

        currentArea = codeAreaMiniJaja;

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
                    }
                    buff.close();
                }
                catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
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



    @FXML
    public void openFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Mini Jaja Files", "*.mjj"));
        File selectedFile = null;
        try {
            selectedFile = fileChooser.showOpenDialog(new Stage());
            setCurrentFile(selectedFile.getAbsolutePath());
        } catch (Exception e){
            e.printStackTrace();
        }
        if (selectedFile != null) {
            if (getCurrentFile() != selectedFile.getAbsolutePath() && getCurrentFile() != "" ) {
                saveFile(actionEvent);
            }
            currentArea.clear();
            try{
                InputStream flux = new FileInputStream(selectedFile.getAbsoluteFile());
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);
                String ligne;
                while ((ligne = buff.readLine()) != null){
                    currentArea.appendText(ligne);
                    currentArea.appendText(System.getProperty("line.separator"));
                }
                buff.close();
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }

    @FXML
    public void openFolder(ActionEvent actionEvent) {
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("choose your directory");
        File dir = null;
        try {
            dir = folderChooser.showDialog(new Stage());
        } catch(Exception e){}

        if (dir != null) {
            FolderTreeView treeView = new FolderTreeView(dir);
            TreeItem<File> root = treeView.tree;
            folderTreeView.setRoot(root);
        }

    }

    @FXML
    public void saveFileAs(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = null;
        try {
            file = fileChooser.showSaveDialog(new Stage());
        } catch (Exception e){
            e.printStackTrace();
        }
        setCurrentFile(file.getAbsolutePath());
        if (file != null) {
            saveTextToFile(currentArea.getText(), file);
        }
    }


    @FXML
    public void saveFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        if (currentArea.getText().trim() != "" && currentArea.getText().length() != 0) {
            if (getCurrentFile() == null) {
                saveFileAs(actionEvent);
            }
            File file = null;
            File newFile = null;
            try {
                file = new File(getCurrentFile());
                file.delete();
                newFile = new File(getCurrentFile());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (newFile != null) {
                saveTextToFile(currentArea.getText(), newFile);
            }
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void newFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        if (currentArea.getText().trim() == "") {
            saveFile(actionEvent);
        }
        if (currentArea.equals(codeAreaMiniJaja)){
            currentFileMiniJaja = "";
        }
        currentFileJajaCode = "";
        currentArea.clear();
    }

    @FXML
    public void compile(ActionEvent actionEvent) throws IOException {
        saveFile(actionEvent);
        compilerGraphic.compile(currentFileMiniJaja);
        tabJajaCode.getTabPane().getSelectionModel().select(tabJajaCode);
    }

    private Tab getTabCodeSelected(){
        return tabPaneCode.getSelectionModel().getSelectedItem();
    }

    private CodeArea getCurrentCodeArea(){
        if (getTabCodeSelected() == null) {
            return codeAreaMiniJaja;
        }
        if (getTabCodeSelected().equals(tabMiniJaja)) {
            return codeAreaMiniJaja;
        } else {
            return codeAreaJajaCode;
        }
    }

    private void setCurrentFile(String path) {
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)) {
            currentFileMiniJaja = path;
        } else {
            currentFileJajaCode = path;
        }
    }

    private String getCurrentFile(){
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)) {
            return currentFileMiniJaja;
        } else {
            return currentFileJajaCode;
        }
    }

    public Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        currentArea = getCurrentCodeArea();
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
        currentArea = getCurrentCodeArea();
        currentArea.setStyleSpans(0, highlighting);
    }



    @FXML
    public void runCode(ActionEvent actionEvent) throws Exception {
        saveFile(actionEvent);
        run(false);
    }


    @FXML
    private void runWithDebug(ActionEvent actionEvent) throws Exception {
        saveFile(actionEvent);
        run(true);
    }



    public void run(boolean debug){
        currentArea = getCurrentCodeArea();
        memory = Memory.getInstance();
        try {
            if (currentArea.equals(codeAreaMiniJaja)){
                runMiniJaja(debug);
            }else{
                if(interpreterJajaCodeModel.getNodes().size()!=0){
                    runJajaCode(debug);
                }
            }
            areaRunTab.getTabPane().getSelectionModel().select(areaRunTab);
        } catch (ASTParsingException | IOException e) {
            System.out.println(e.getMessage());
            areaError.clear();
            areaError.appendText(e.getMessage());
            areaErrorTab.getTabPane().getSelectionModel().select(areaErrorTab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runMiniJaja(boolean debug) throws Exception {
        interpreterMiniJajaModel.setBreakpoints();
        interpreterMiniJajaModel.init(currentFileMiniJaja);
        interpreterMiniJajaModel.setMemory(memory);
        interpreterMiniJajaModel.interpret();
        interpreterMiniJajaModel.run(debug);
    }



    public void runJajaCode(boolean debug) throws Exception {
        if (interpreterJajaCodeModel.getNodes().size() == 0){
            areaError.clear();
            areaError.appendText("You need compile MiniJajaBefore Execute");
        }else {
            interpreterJajaCodeModel.setBreakpoints();
            interpreterJajaCodeModel.run(debug);
        }
    }

    public void nextBreakPoint(ActionEvent actionEvent) {

    }

    public void step(ActionEvent actionEvent) {
        
    }
}