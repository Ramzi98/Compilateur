import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import org.junit.Test;

public class ProjetExampleParserTypeCheckerTest extends BaseTest {
    protected TypeChecker typeChecker;

    @Test
    public void TestClassfactTypeCheckerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }


    @Test
    public void TestClassQuicksortTypeCheckerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","quicksort");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }




    @Test
    public void TestClass1TypeCheckerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void TestClassSynonymieTypeCheckerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void TestClassTasTypeCheckerParser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","tas");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }


}
