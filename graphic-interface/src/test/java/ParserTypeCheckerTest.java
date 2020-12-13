import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MethodNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
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
/*

    @Test
    public void exampleInstrs__WithException() throws Exception {
        TestConstructor testConstructor = new TestConstructor("b = 6;");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        InstrsNode classeNode = (InstrsNode) listener.getRoot();
        typeChecker = new TypeChecker(classeNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


    }


 */






}
