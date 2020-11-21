import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserExp2Test extends MiniJajaParserBaseTest{
    @Test
    public void check__Exp2__Div() {
        TestConstructor testConstructor = new TestConstructor("6 + 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp2());

        PlusNode plusNode = (PlusNode) listener.getRoot();
        NumberNode left = (NumberNode)plusNode.leftOperand();
        NumberNode right = (NumberNode)plusNode.rightOperand();
        assertThat(plusNode.line(),is(1));
        assertThat(plusNode.column(),is(0));


        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(4));
    }

    @Test
    public void check__Exp2__Sub() {
        TestConstructor testConstructor = new TestConstructor("6 - 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp2());

        SubNode subNode = (SubNode) listener.getRoot();
        NumberNode left = (NumberNode)subNode.leftOperand();
        NumberNode right = (NumberNode)subNode.rightOperand();
        assertThat(subNode.line(),is(1));
        assertThat(subNode.column(),is(0));


        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(4));
    }

    @Test
    public void check__Exp2__Minus() {
        TestConstructor testConstructor = new TestConstructor("-6");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp2());

        MinusNode minusNode = (MinusNode) listener.getRoot();
        NumberNode exp = (NumberNode)minusNode.expression();

        assertThat(exp.value(),is(6));
        assertThat(exp.line(),is(1));
        assertThat(exp.column(),is(1));
    }

    @Test
    public void check__Exp2__Termes() {
        TestConstructor testConstructor = new TestConstructor("(6)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp2());

        NumberNode numberNode = (NumberNode) listener.getRoot();

        assertThat(numberNode.value(),is(6));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(1));
    }

}
