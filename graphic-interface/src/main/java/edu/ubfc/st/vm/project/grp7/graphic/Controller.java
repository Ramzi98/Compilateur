package edu.ubfc.st.vm.project.grp7.graphic;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterListener;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.ASTParsingException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaLexer;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaListenerImpl;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable, MJJInterpreterListener {

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
    private Tab areaErrorTab;
    @FXML
    private Tab areaRunTab;

    private String currentFile;
    private String currentFileMiniJaja;
    private String currentFileJajaCode;

    private CharStream codePointCharStream;
    private MiniJajaLexer lexer;
    private MiniJajaParser parser;
    private ParseTreeWalker walker =new ParseTreeWalker();
    private MiniJajaListenerImpl listener;
    private Executor threadWrite = Executors.newSingleThreadExecutor();
    private Memory memory = Memory.getInstance();

    private BreakPoint breakPointMiniJaja;
    private BreakPoint breakPointJajaCode;

    private CodeArea currentArea ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init_code_area(codeAreaMiniJaja);
        init_code_area(codeAreaJajaCode);

        currentArea = codeAreaMiniJaja;


        folderTreeView.getSelectionModel().selectedItemProperty().addListener(
                    (v, oldValue, newValue) -> {
                    saveFile(null);
                    File f2 = new File(v.getValue().toString().split(" ")[3]);
                    if (f2 != null) {
                        try{
                            setCurrentFile(f2.getAbsolutePath()) ;
                            InputStream flux = new FileInputStream(f2.getAbsoluteFile());
                            InputStreamReader lecture = new InputStreamReader(flux);
                            BufferedReader buff = new BufferedReader(lecture);
                            String ligne;
                            currentArea.clear();
                            while ((ligne = buff.readLine()) != null){
                                currentArea.appendText(ligne);
                                currentArea.appendText( System.getProperty("line.separator"));
                            }
                            buff.close();
                        }
                        catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                });
    }

    private void treeListener(){

    }

    private void init_code_area(CodeArea codeArea){
        IntFunction<Node> numberFactory = LineNumberFactory.get(codeArea);
        IntFunction<Node> graphicFactory;
        if (codeArea.equals(codeAreaMiniJaja)){
            breakPointMiniJaja = new BreakPoint(codeArea.currentParagraphProperty());
            graphicFactory= line -> {
                HBox hbox = new HBox(
                        numberFactory.apply(line),
                        breakPointMiniJaja.apply(line));
                hbox.setAlignment(Pos.CENTER_LEFT);
                return hbox;
            };
        }else{
            breakPointJajaCode =new BreakPoint(codeArea.currentParagraphProperty());
            graphicFactory= line -> {
                HBox hbox = new HBox(
                        numberFactory.apply(line),
                        breakPointJajaCode.apply(line));
                hbox.setAlignment(Pos.CENTER_LEFT);
                return hbox;
            };
        }
        codeArea.setParagraphGraphicFactory(graphicFactory);
    }

    @FXML
    public void openFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        setCurrentFile(selectedFile.getAbsolutePath());
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
        File dir = folderChooser.showDialog(new Stage());
        if (dir != null)
        {
            FolderTreeView treeView = new FolderTreeView(dir);
            TreeItem<File> root =  treeView.tree;
            folderTreeView.setRoot(root);
        }

    }

    @FXML
    public void saveFileAs(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        setCurrentFile(file.getAbsolutePath());
        if (file != null) {
            saveTextToFile(currentArea.getText(), file);
        }
    }

    @FXML
    public void saveFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        if (currentArea.getText().trim() != "" && currentArea.getText().length() != 0)
        {
            if (getCurrentFile() == null){
                saveFileAs(actionEvent);
            }
            File file = new File(getCurrentFile());
            file.delete();
            File newFile = new File(getCurrentFile());
            if (newFile != null) {
                saveTextToFile(codeAreaMiniJaja.getText(), newFile);
            }
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void newFile(ActionEvent actionEvent) {
        currentArea = getCurrentCodeArea();
        if (currentArea.getText().trim() == ""){
            saveFile(actionEvent);
        }
        currentArea.clear();
    }

    @FXML
    public void compile(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void run(ActionEvent actionEvent) {
        saveFile(actionEvent);
            try {
                codePointCharStream = CharStreams.fromPath(Paths.get(getCurrentFile()));
                lexer = new MiniJajaLexer(codePointCharStream);
                parser = new MiniJajaParser(new CommonTokenStream(lexer));
                listener = new MiniJajaListenerImpl();
                try {
                    walker.walk(listener, parser.classe());
                    ClasseNode classeNode = (ClasseNode)listener.getRoot();
                    this.threadWrite.execute(() -> {
                                areaRun.appendText("new execution ... \n\n");
                            });
                    MiniJajaInterpreter.getFactory().createFrom(memory,classeNode).interpret(new MJJInterpreterController(this));
                    this.threadWrite.execute(() -> {
                        areaRun.appendText("\nEnd of execution\n");
                    });
                    areaRunTab.getTabPane().getSelectionModel().select(areaRunTab);
                }catch (ASTParsingException e){
                    System.out.println(e.getMessage());
                    areaError.clear();
                    areaError.appendText(e.getMessage());
                    areaErrorTab.getTabPane().getSelectionModel().select(areaErrorTab);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void mjjWrite(String str) {
        this.threadWrite.execute(() -> {
            areaRun.appendText(str);
        });
    }

    @Override
    public void mjjWriteLn(String str) {
        this.threadWrite.execute(() -> {
            areaRun.appendText(str);
            areaRun.appendText("\n");
        });
    }

    @FXML
    private void debug(ActionEvent actionEvent) {
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)) {
            breakPointMiniJaja.returnCheckedLine();
        }else{
            breakPointJajaCode.returnCheckedLine();
        }
    }

    private Tab getTabCodeSelected(){
        return tabPaneCode.getSelectionModel().getSelectedItem();
    }

    private CodeArea getCurrentCodeArea(){
        if (getTabCodeSelected() == null){
            return codeAreaMiniJaja;
        }
        if (getTabCodeSelected().equals(tabMiniJaja)){
            return codeAreaMiniJaja;
        }else{
            return codeAreaJajaCode;
        }
    }

    private void setCurrentFile(String path){
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)){
            currentFileMiniJaja = path;
        }else{
            currentFileJajaCode = path;
        }
    }

    private String getCurrentFile(){
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)){
            return currentFileMiniJaja;
        }else{
            return currentFileJajaCode;
        }
    }
}