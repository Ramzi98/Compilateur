import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MethodNode;
import org.junit.Test;

public class ExampleTest1 extends BaseTest {

    @Test
    public void example1Test() {
        TestConstructor testConstructor = new TestConstructor("Class C {\t\n" +
                "int x = true;\n" +
                "int y = 0;\n" +
                "int x = 1;\n" +
                "}");

        parser = testConstructor.getParser();
        walker.walk(listener, parser.methode());
        ClasseNode classe = (ClasseNode) listener.getRoot();



    }
}
