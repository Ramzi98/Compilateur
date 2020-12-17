import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import org.junit.Test;

public class ProjetExampleParserTypeCheckerTest extends BaseTest {
    protected TypeChecker typeChecker;

    @Test
    public void Test_Class_fact_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }


    @Test
    public void Test_Class_Quicksort_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","quicksort");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }




    @Test
    public void Test_Class_1_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_Synonymie_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_Tas_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","tas");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }


    @Test
    public void Test_Class_test1_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","test1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test2_TypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","test2");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }


}
