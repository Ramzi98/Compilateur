import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_1.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_fact.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_quicksort.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_synonymie.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_tas.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_test1.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
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
        StringBuilder expected = new StringBuilder();
        try {
            File file = new File("src/resourceTest/ExpectedComplierResult/Expected_File_test2.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                expected.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertThat(jccPrinter.toString(),is(expected.toString()));
    }
}
