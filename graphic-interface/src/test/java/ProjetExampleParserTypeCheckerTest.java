import edu.ubfc.st.vm.project.grp7.mjj.ast.node.ClasseNode;
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

    @Test
    public void Test_Class_test1Write_writlnTypeChecker_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","1_write_and_writeLn");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test2_op_number_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","2_op_number");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test3_op_boolean_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","3_op_boolean");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test5_op_boolean_advanced_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","5_op_boolean_advanced");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test6_function_int_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","6_function_int");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test7_function_boolean_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","7_function_boolean");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test8_while_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","8_while");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test9_Exception_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","9_Exception");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

    @Test
    public void Test_Class_test10_TypeCheckException_Parser() throws Exception {
        TestConstructor testConstructor = new TestConstructor("ProjectExample","10_TypeCheckException");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();
    }

}
