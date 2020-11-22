import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.WhileNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MiniJajaParserInstrTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Instr__WhileEmpty() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWhileEmptyTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WhileNode whileNode = (WhileNode) listener.getRoot();
        assertThat(whileNode.line(),is(1));
        assertThat(whileNode.column(),is(0));

        NumberNode numberNode = (NumberNode)whileNode.expression();
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(6));
        assertThat(numberNode.value(),is(1));

        assertThat(whileNode.instrs(),is(nullValue()));
    }

    @Test
    public void check__Instr__While() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWhileTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WhileNode whileNode = (WhileNode) listener.getRoot();
        assertThat(whileNode.line(), is(1));
        assertThat(whileNode.column(), is(0));

        NumberNode numberNode = (NumberNode) whileNode.expression();
        assertThat(numberNode.line(), is(1));
        assertThat(numberNode.column(), is(6));
        assertThat(numberNode.value(), is(1));


        InstrsNode instrsNode = whileNode.instrs();
        WhileNode whileNode1 = (WhileNode)instrsNode.instruction();
        assertThat(whileNode1.line(), is(2));
        assertThat(whileNode1.column(), is(4));

        NumberNode numberNode1 = (NumberNode) whileNode1.expression();
        assertThat(numberNode1.line(), is(2));
        assertThat(numberNode1.column(), is(10));
        assertThat(numberNode1.value(), is(1));

        assertThat(whileNode1.instrs(), is(nullValue()));
    }
}
