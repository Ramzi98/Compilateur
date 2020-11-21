import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserExp1Test extends MiniJajaParserBaseTest{
    @Test
    public void check__Exp1__Equal() throws IOException {
        TestConstructor testConstructor = new TestConstructor("6 == 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp1());

        EqualsNode equalsNode = (EqualsNode) listener.getRoot();
        NumberNode left = (NumberNode)equalsNode.leftOperand();
        NumberNode right = (NumberNode)equalsNode.rightOperand();
        assertThat(equalsNode.line(),is(1));
        assertThat(equalsNode.column(),is(0));


        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(5));
    }

    @Test
    public void check__Exp1__Greater() throws IOException {
        TestConstructor testConstructor = new TestConstructor("6 > 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp1());

        GreaterNode greaterNode = (GreaterNode) listener.getRoot();
        NumberNode left = (NumberNode)greaterNode.leftOperand();
        NumberNode right = (NumberNode)greaterNode.rightOperand();
        assertThat(greaterNode.line(),is(1));
        assertThat(greaterNode.column(),is(0));


        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(4));
    }

    @Test
    public void check__Exp1__exp2() throws IOException {
        TestConstructor testConstructor = new TestConstructor("(6)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp1());

        NumberNode numberNode = (NumberNode) listener.getRoot();
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(1));

        assertThat(numberNode.value(),is(6));
    }

}
