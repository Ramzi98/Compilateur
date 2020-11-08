import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    public TreeView folderTreeView;
    @FXML
    public TextArea textAreaCode;
    @FXML
    public TextFlow areaRun;
    @FXML
    public TextFlow areaError;

    private String currentFile;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.currentFile="";
        folderTreeView.getSelectionModel().selectedItemProperty().addListener(
                    (v, oldValue, newValue) -> {
                    saveFile(null);
                    File f2 = new File(v.getValue().toString().split(" ")[3]);
                    if (f2 != null) {
                        try{
                            this.currentFile = f2.getAbsolutePath();
                            InputStream flux=new FileInputStream(f2.getAbsoluteFile());
                            InputStreamReader lecture=new InputStreamReader(flux);
                            BufferedReader buff=new BufferedReader(lecture);
                            String ligne;
                            textAreaCode.clear();
                            while ((ligne=buff.readLine())!=null){

                                textAreaCode.appendText(ligne);
                                textAreaCode.appendText( System.getProperty("line.separator"));
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
        currentFile= selectedFile.getAbsolutePath();
        if (selectedFile != null) {
            if (this.currentFile!= selectedFile.getAbsolutePath()){
                saveFile(actionEvent);
                textAreaCode.clear();
                try{
                    InputStream flux=new FileInputStream(selectedFile.getAbsoluteFile());
                    InputStreamReader lecture=new InputStreamReader(flux);
                    BufferedReader buff=new BufferedReader(lecture);
                    String ligne;
                    while ((ligne=buff.readLine())!=null){

                        textAreaCode.appendText(ligne);
                        textAreaCode.appendText( System.getProperty("line.separator"));
                    }
                    buff.close();
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }
            }

        }
    }


    @FXML
    public void openFolder(ActionEvent actionEvent) {
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("choose your directory");
        File dir= folderChooser.showDialog(new Stage());
        if (dir != null)
        {
            FolderTreeView treeView = new FolderTreeView(dir);
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
            saveTextToFile(textAreaCode.getText(), file);
        }
    }

    @FXML
    public void saveFile(ActionEvent actionEvent) {

        if (textAreaCode.getText().trim() != "" && textAreaCode.getText().length()!=0)
        {
            if (this.currentFile==""){
                saveFileAs(actionEvent);
            }
            File file = new File(this.currentFile);
            file.delete();
            File newFile = new File(this.currentFile);
            if (newFile != null) {
                saveTextToFile(textAreaCode.getText(), newFile);
            }
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void newFile(ActionEvent actionEvent) {
        if (textAreaCode.getText().trim()==""){
            saveFile(actionEvent);
        }
        textAreaCode.clear();
    }

    @FXML
    public void compile(ActionEvent actionEvent) {

    }

    @FXML
    public void run(ActionEvent actionEvent) {
    }

}
