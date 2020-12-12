import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MethodNode;
import edu.ubfc.st.vm.project.grp7.type.checker.TypeChecker;
import org.junit.Before;
import org.junit.Test;

public class ExampleTest1 extends BaseTest {


    protected Compiler compiler;
    protected TypeChecker typeChecker;
    protected SymbolDictionnary symbolDictionnary;

    @Before
    public void start(){
        symbolDictionnary = new SymbolDictionnary();
    }

    /*
    @Test
    public void example1Test() throws Exception {
        TestConstructor testConstructor = new TestConstructor("classe","class");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        typeChecker = new TypeChecker(classeNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


    }

     */
}
