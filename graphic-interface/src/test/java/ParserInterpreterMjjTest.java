import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterController;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MJJInterpreterListener;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter.MiniJajaInterpreterVisitor;
import edu.ubfc.st.vm.project.grp7.tool.yaml.MiniJajaAstToYamlVisitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ParserInterpreterMjjTest extends BaseTest {
    private MiniJajaInterpreterVisitor mjjVisitor;
    private Memory memory;
    private MJJInterpreterController controller;
    private MJJInterpreterListener MJJlistener;
    @Spy
    private Deque<Object> deque;

    @Before
    public void init() {
        /*
        memory = Memory.getInstance();
        MJJlistener = mock(MJJInterpreterListener.class);
        controller = new MJJInterpreterController(MJJlistener);
        deque = spy(new ArrayDeque<>());
        mjjVisitor = spy(new MiniJajaInterpreterVisitor(memory, controller,deque));

         */

        memory = Memory.getInstance();
        controller = mock(MJJInterpreterController.class);
        deque = spy(new ArrayDeque<>());
        mjjVisitor = new MiniJajaInterpreterVisitor(memory, controller,deque);
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


    @Test
    public void parserInterpreterMjj__Synonymie__Test() throws Exception {
    /*
        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(mjjVisitor);
        assertThat(memory.depiler().toString(),is("<i, 4, var, int>"));*/
    }

}
