import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import org.junit.Test;

public class ProjectExampleParserCompilerTest extends BaseTest{
    protected Compiler compiler;

    @Test
    public void TestClass1CompilerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
    }

    @Test
    public void TestClassFactCompilerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
    }

    @Test
    public void TestClassQuicksortCompilerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","quicksort");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
    }

    @Test
    public void TestClassSynonymieCompilerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
    }

    @Test
    public void TestClasstasCompilerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","tas");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        compiler = new CompilerImpl(classeNode);
        compiler.compile();
    }
}
