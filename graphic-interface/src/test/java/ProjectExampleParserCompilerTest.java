import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProjectExampleParserCompilerTest extends BaseTest{
    protected Compiler compiler;

    @Test
    public void Test_Class_1_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_1")));
    }

    @Test
    public void Test_Class_Fact_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_fact")));
    }

    @Test
    public void Test_Class_Quicksort_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","quicksort");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();

        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_quicksort")));
    }

    @Test
    public void Test_Class_Synonymie_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();

        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_synonymie")));

    }

    @Test
    public void Test_Class_tas_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","tas");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();

        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_tas")));
    }

    @Test
    public void Test_Class_test1_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","test1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();

        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_test1")));
    }


    @Test
    public void Test_Class_test2_Compiler_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","test2");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();

        JCCPrinter jccPrinter = new JCCPrinter(compiler.jajaCodeNodes());
        assertThat(jccPrinter.toString(),is(getExpectedFile("Expected_File_test2")));
    }


    private String getExpectedFile(String FileName) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get("src/resourceTest/ExpectedCompilerResults/"+FileName+".txt"));
        return  new String(encoded, StandardCharsets.UTF_8).replace("\r","");

    }
}
