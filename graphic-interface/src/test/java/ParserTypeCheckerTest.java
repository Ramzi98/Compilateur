import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MethodNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerException;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeCheckerImpl;
import org.junit.Before;
import org.junit.Test;

public class ParserTypeCheckerTest extends BaseTest {


    protected Compiler compiler;
    protected TypeChecker typeChecker;



    @Test
    public void example1Test() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","class");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();


    }



    @Test
    public void exampleClassWithReturnTest__WithException() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","classWithReturnError");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();


    }



    @Test
    public void functionCallClassTest() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","classWithFunctionCall");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();


    }

    @Test
    public void Test3() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","classWithFunctionCall");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();


    }



    @Test(expected = TypeCheckerException.class)
    public void FunctionCallClassTest__WithException() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","classWithFunctionCallSignatureError");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeCheckerImpl(classeNode);
        typeChecker.typeCheck();


    }


}
