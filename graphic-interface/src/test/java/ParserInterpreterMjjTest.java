import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreterVisitor;
import edu.ubfc.st.vm.project.grp7.tool.yaml.MiniJajaAstToYamlVisitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ParserInterpreterMjjTest extends BaseTest {
    private MiniJajaInterpreterVisitor mjjVisitor;
    private Memory memory;
    private MJJInterpreterController controller;
    @Spy
    private Deque<Object> deque;

    @Before
    public void init() {
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        deque = spy(new ArrayDeque<>());
        mjjVisitor = spy(new MiniJajaInterpreterVisitor(memory, controller,deque));
    }




    /*
    @Test
    public void parserInterpreterMjj__1__Test() throws Exception {

        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(mjjVisitor);



    }

     */


}
