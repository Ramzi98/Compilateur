package edu.ubfc.st.vm.project.grp7.graphic;


import javafx.scene.control.TreeItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileModel {
    private CodeArea miniJaja;
    private CodeArea jajaCode;

    public FileModel(CodeArea miniJaja,CodeArea jajaCode){
        this.miniJaja = miniJaja;
        this.jajaCode = jajaCode;
    }

    public String saveFileAs(CodeArea codeArea) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = null;
        try {
            file = fileChooser.showSaveDialog(new Stage());
        } catch (Exception e){
            e.printStackTrace();
        }

        if (file != null) {
            saveTextToFile(codeArea.getText(), file);
            return file.getAbsolutePath();
        }
       return "";
    }


    public void saveFile(CodeArea codeArea, String currentFile) {
        if (codeArea.getText().trim() != "" && codeArea.getText().length() != 0) {
            if (currentFile == null) {
                saveFileAs(codeArea);
            }
            File file = null;
            File newFile = null;
            try {
                file = new File(currentFile);
                file.delete();
                newFile = new File(currentFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (newFile != null) {
                saveTextToFile(codeArea.getText(), newFile);
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


    public String openFile(CodeArea codeArea) {
        String currentFile = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Mini Jaja Files", "*.mjj"));
        File selectedFile = null;
        try {
            selectedFile = fileChooser.showOpenDialog(new Stage());
            currentFile = selectedFile.getAbsolutePath();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (selectedFile != null) {
            if (currentFile != selectedFile.getAbsolutePath() && currentFile != "" ) {
                saveFile(codeArea, currentFile);
            }
            codeArea.clear();
            try{
                InputStream flux = new FileInputStream(selectedFile.getAbsoluteFile());
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);
                String ligne;
                while ((ligne = buff.readLine()) != null){
                    codeArea.appendText(ligne);
                    codeArea.appendText(System.getProperty("line.separator"));
                }
                buff.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return currentFile;
    }


    public void newFile(CodeArea codeArea, String file) {
        if (codeArea.getText().trim() == "") {
            saveFile(codeArea,file);
        }
        codeArea.clear();
        codeArea.clear();
    }


    public TreeItem<File> openFolder() {
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("choose your directory");
        File dir = null;
        try {
            dir = folderChooser.showDialog(new Stage());
        } catch(Exception e){}

        if (dir != null) {
            FolderTreeView treeView = new FolderTreeView(dir);
            TreeItem<File> root = treeView.tree;
            return root;
        }
        return null;
    }


}
