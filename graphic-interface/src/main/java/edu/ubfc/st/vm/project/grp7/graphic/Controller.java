package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreter;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterController;
import edu.ubfc.st.vm.project.grp7.jaja.code.interpreter.JJCInterpreterListener;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterListener;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.ASTParsingException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaLexer;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaListenerImpl;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.MiniJajaParser;
import javafx.concurrent.Task;
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
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class Controller implements Initializable, MJJInterpreterListener, JJCInterpreterListener {
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

    private String currentFileMiniJaja;
    private String currentFileJajaCode;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Executor threadWrite = Executors.newSingleThreadExecutor();

    private CharStream codePointCharStream;
    private MiniJajaLexer lexer;
    private MiniJajaParser parser;
    private ParseTreeWalker walker = new ParseTreeWalker();
    private MiniJajaListenerImpl listener;

    private Memory memory;
    private BreakPoint currentBreakPoint;

    private BreakPoint breakPointMiniJaja;
    private BreakPoint breakPointJajaCode;

    private CodeArea currentArea;
    private Compiler compiler;
    private Waiter currentWaiter;
    PausableExecutor pausableExecutor;

    private List<JajaCodeNode> jcInitNode;

    private  boolean debug;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init_code_area(codeAreaMiniJaja);
        init_code_area(codeAreaJajaCode);

        currentArea = codeAreaMiniJaja;

        syntaxiqueColor(codeAreaJajaCode);
        syntaxiqueColor(codeAreaMiniJaja);

        jcInitNode = new ArrayList<>();

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

    private void init_code_area(CodeArea codeArea){
        IntFunction<Node> numberFactory = LineNumberFactory.get(codeArea);
        IntFunction<Node> graphicFactory;
        if (codeArea.equals(codeAreaMiniJaja)) {
            breakPointMiniJaja = new BreakPoint(codeArea.currentParagraphProperty());
            graphicFactory = line -> {
                HBox hbox = new HBox(
                        breakPointMiniJaja.apply(line),
                        numberFactory.apply(line));
                hbox.setAlignment(Pos.CENTER_LEFT);
                return hbox;
            };
        } else {
            breakPointJajaCode = new BreakPoint(codeArea.currentParagraphProperty());
            graphicFactory = line -> {
                HBox hbox = new HBox(
                        breakPointJajaCode.apply(line),
                        numberFactory.apply(line));
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
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Mini Jaja Files", "*.mjj"));
        File selectedFile = null;
        try {
            selectedFile = fileChooser.showOpenDialog(new Stage());
            setCurrentFile(selectedFile.getAbsolutePath());
        } catch (Exception e){
            //TODO trow choosen Exception or print
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
            //TODO trow choosen Exception or print
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
                //TODO trow choosen Exception or print
            }

            if (newFile != null) {
                saveTextToFile(currentArea.getText(), newFile);
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
        try {
            initParserAndLexerFromCurrentFile();
            try {
                walker.walk(listener, parser.classe());
                ClasseNode classeNode = (ClasseNode)listener.getRoot();
                compiler = new CompilerImpl(classeNode);
                compiler.compile();
                jcInitNode = compiler.jajaCodeNodes();
                JCCPrinter jccPrinter = new JCCPrinter(jcInitNode);
                codeAreaJajaCode.appendText(jccPrinter.toString());
                tabJajaCode.getTabPane().getSelectionModel().select(tabJajaCode);
            } catch (ASTParsingException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initParserAndLexerFromCurrentFile() throws IOException {
        codePointCharStream = CharStreams.fromPath(Paths.get(currentFileMiniJaja));
        lexer = new MiniJajaLexer(codePointCharStream);
        parser = new MiniJajaParser(new CommonTokenStream(lexer));
        listener = new MiniJajaListenerImpl();
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

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        currentArea = getCurrentCodeArea();
        String text = currentArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        currentArea = getCurrentCodeArea();
        currentArea.setStyleSpans(0, highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = Pattern.PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                    matcher.group("TYPE") != null ? "keywordType" :
                    matcher.group("PAREN") != null ? "paren" :
                    matcher.group("BRACE") != null ? "brace" :
                    matcher.group("SEMICOLON") != null ? "semicolon" :
                    matcher.group("STRING") != null ? "string" :
                    matcher.group("COMMENT") != null ? "comment" :
                    null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    @FXML
    public void runCode(ActionEvent actionEvent) {
        currentBreakPoint = null;
        saveFile(actionEvent);

        currentWaiter = new DebugOffWaiter();
        run();
    }

    @Override
    public void jjcWrite(String str) {
        this.threadWrite.execute(() -> {
            areaRun.appendText(str);
        });
    }

    @Override
    public void jjcWriteLn(String str) {
        this.threadWrite.execute(() -> {
            areaRun.appendText(str+"\n");
        });
    }

    @Override
    public void nop() {} // jc

    @Override
    public void debug(int line) throws InterruptedException {
        if (currentBreakPoint == null){
            currentWaiter.waitForUser(false);
        }else{
            currentWaiter.waitForUser(currentBreakPoint.returnCheckedLine().contains(line));
            areaDebug.clear();
            areaDebug.appendText(memory.toString());
            areaDebugTab.getTabPane().getSelectionModel().select(areaDebugTab);
        }
    }

    @FXML
    private void runWithDebug(ActionEvent actionEvent) throws Exception {

        currentWaiter = new DebugOnWaiter(pausableExecutor);
        if (getCurrentCodeArea().equals(codeAreaMiniJaja)) {
            currentBreakPoint = breakPointMiniJaja;
            breakPointMiniJaja.returnCheckedLine();
        } else {
            currentBreakPoint = breakPointJajaCode;
            breakPointJajaCode.returnCheckedLine();
        }

        run();
    }

    public void step(ActionEvent actionEvent) {
        System.out.println("step");
        currentWaiter.nextStep();
    }

    public void nextBreakPoint(ActionEvent actionEvent) {
        System.out.println("breapoint");
        currentWaiter.nextBreakpoint();
    }

    public void run(){
        pausableExecutor = new PausableExecutor();
        currentArea = getCurrentCodeArea();
        try {
            initParserAndLexerFromCurrentFile();
            try {
                memory = Memory.getInstance();
                if (currentArea.equals(codeAreaMiniJaja)){
                    walker.walk(listener, parser.classe());
                    ClasseNode classeNode = (ClasseNode)listener.getRoot();
                    pausableExecutor.execute(() -> {
                        try {
                            MiniJajaInterpreter.getFactory().createFrom(memory, classeNode).interpret(new MJJInterpreterController(this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    this.threadWrite.execute(() -> {
                        areaRun.appendText("\n----------------------------------\n");
                    });
                }else{
                    if(jcInitNode != null){
                        try{
                            pausableExecutor.execute(() -> {
                                try {
                                    JJCInterpreter.getFactory().createFrom(memory,jcInitNode).interpret(new JJCInterpreterController(this) );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            this.threadWrite.execute(() -> {
                                areaRun.appendText("\n----------------------------------\n");
                            });
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                }
                areaRunTab.getTabPane().getSelectionModel().select(areaRunTab);
            } catch (ASTParsingException e) {
                System.out.println(e.getMessage());
                areaError.clear();
                areaError.appendText(e.getMessage());
                areaErrorTab.getTabPane().getSelectionModel().select(areaErrorTab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}