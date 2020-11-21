import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MultNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserTermeTest extends MiniJajaParserBaseTest{
    @Test
    public void check__Terme__Mult() throws IOException {
        TestConstructor testConstructor = new TestConstructor("6 * 5");
        parser = testConstructor.getParser();

        ParseTree tree = parser.terme();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        MultNode multNode = (MultNode) listener.getRoot();
        NumberNode left = (NumberNode)multNode.leftOperand();
        NumberNode right = (NumberNode)multNode.rightOperand();
        assertThat(left.value(),is(6.0));
        assertThat(((NumberNode)multNode.rightOperand()).value(),is(5.0));
        assertThat(multNode.line(),is(1));
        assertThat(multNode.column(),is(0));
    }
}
