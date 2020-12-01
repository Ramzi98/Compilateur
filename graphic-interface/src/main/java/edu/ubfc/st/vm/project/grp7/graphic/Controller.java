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
import javafx.scene.control.*;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable, MJJInterpreterListener {

    @FXML
    CodeArea codeTextArea;

    @FXML
    public TreeView folderTreeView;

    @FXML
    public TextArea areaRun;
    @FXML
    public TextArea areaError;

    @FXML
    public Tab areaErrorTab;
    @FXML
    public Tab areaRunTab;

    private String currentFile;

    private CharStream codePointCharStream;
    private MiniJajaLexer lexer;
    private MiniJajaParser parser;
    private ParseTreeWalker walker =new ParseTreeWalker();
    private MiniJajaListenerImpl listener;
    private Executor backgroundThread1 = Executors.newSingleThreadExecutor();
    private Memory memory = Memory.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeTextArea.setParagraphGraphicFactory(LineNumberFactory.get(codeTextArea));
        this.currentFile = "";
        folderTreeView.getSelectionModel().selectedItemProperty().addListener(
                    (v, oldValue, newValue) -> {
                    saveFile(null);
                    File f2 = new File(v.getValue().toString().split(" ")[3]);
                    if (f2 != null) {
                        try{
                            this.currentFile = f2.getAbsolutePath();
                            InputStream flux = new FileInputStream(f2.getAbsoluteFile());
                            InputStreamReader lecture = new InputStreamReader(flux);
                            BufferedReader buff = new BufferedReader(lecture);
                            String ligne;
                            codeTextArea.clear();
                            while ((ligne = buff.readLine()) != null){
                                codeTextArea.appendText(ligne);
                                codeTextArea.appendText( System.getProperty("line.separator"));
                            }
                            buff.close();
                        }
                        catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                });
    }

    @FXML
    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        currentFile = selectedFile.getAbsolutePath();
        if (selectedFile != null) {
            if (this.currentFile != selectedFile.getAbsolutePath() && this.currentFile != "" ) {
                saveFile(actionEvent);
            }
            codeTextArea.clear();
            try{
                InputStream flux = new FileInputStream(selectedFile.getAbsoluteFile());
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);
                String ligne;
                while ((ligne = buff.readLine()) != null){
                    codeTextArea.appendText(ligne);
                    codeTextArea.appendText(System.getProperty("line.separator"));
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
            edu.ubfc.st.vm.project.grp7.graphic.FolderTreeView treeView = new edu.ubfc.st.vm.project.grp7.graphic.FolderTreeView(dir);
            TreeItem<File> root =  treeView.tree;
            folderTreeView.setRoot(root);
        }

    }

    @FXML
    public void saveFileAs(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        currentFile = file.getAbsolutePath();
        if (file != null) {
            saveTextToFile(codeTextArea.getText(), file);
        }
    }

    @FXML
    public void saveFile(ActionEvent actionEvent) {
        if (codeTextArea.getText().trim() != "" && codeTextArea.getText().length() != 0)
        {
            if (this.currentFile == ""){
                saveFileAs(actionEvent);
            }
            File file = new File(this.currentFile);
            file.delete();
            File newFile = new File(this.currentFile);
            if (newFile != null) {
                saveTextToFile(codeTextArea.getText(), newFile);
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
        if (codeTextArea.getText().trim() == ""){
            saveFile(actionEvent);
        }
        codeTextArea.clear();
    }

    @FXML
    public void compile(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void run(ActionEvent actionEvent) {
        saveFile(actionEvent);
            try {
                codePointCharStream = CharStreams.fromPath(Paths.get(currentFile));
                lexer = new MiniJajaLexer(codePointCharStream);
                parser = new MiniJajaParser(new CommonTokenStream(lexer));
                listener = new MiniJajaListenerImpl();
                try {
                    walker.walk(listener, parser.classe());
                    ClasseNode classeNode = (ClasseNode)listener.getRoot();
                    areaRun.appendText("new execution ... \n\n");
                    MiniJajaInterpreter.getFactory().createFrom(memory,classeNode).interpret(new MJJInterpreterController(this));
                    areaRun.appendText("\n\n");
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
        this.backgroundThread1.execute(() -> {
            areaRun.appendText(str);
        });
    }

    @Override
    public void mjjWriteLn(String str) {
        this.backgroundThread1.execute(() -> {
            areaRun.appendText(str);
            areaRun.appendText("\n");
        });
    }

}