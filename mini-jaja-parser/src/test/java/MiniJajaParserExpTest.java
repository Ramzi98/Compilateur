import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserExpTest extends MiniJajaParserBaseTest{
    @Test
    public void check__Exp__not() throws IOException {
        TestConstructor testConstructor = new TestConstructor("!true");
        parser = testConstructor.getParser();

        ParseTree tree = parser.exp();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        NotNode notNode = (NotNode) listener.getRoot();
        BooleanNode booleanNode = (BooleanNode)notNode.expression();
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(1));

        assertThat(booleanNode.value(),is(true));
    }

    @Test
    public void check__Exp__And() throws IOException {
        TestConstructor testConstructor = new TestConstructor("6 && 5");
        parser = testConstructor.getParser();

        ParseTree tree = parser.exp();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        AndNode andNode = (AndNode) listener.getRoot();
        NumberNode left = (NumberNode)andNode.leftOperand();
        NumberNode right = (NumberNode)andNode.rightOperand();
        assertThat(andNode.line(),is(1));
        assertThat(andNode.column(),is(0));


        assertThat(left.value(),is(6.0));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5.0));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(5));
    }

    @Test
    public void check__Exp__Or() throws IOException {
        TestConstructor testConstructor = new TestConstructor("6 || 5");
        parser = testConstructor.getParser();

        ParseTree tree = parser.exp();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        OrNode orNode = (OrNode) listener.getRoot();
        NumberNode left = (NumberNode)orNode.leftOperand();
        NumberNode right = (NumberNode)orNode.rightOperand();
        assertThat(orNode.line(),is(1));
        assertThat(orNode.column(),is(0));


        assertThat(left.value(),is(6.0));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5.0));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(5));
    }

    @Test
    public void check__Exp__Exp1() throws IOException {
        TestConstructor testConstructor = new TestConstructor("(true)");
        parser = testConstructor.getParser();

        ParseTree tree = parser.exp();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        BooleanNode booleanNode = (BooleanNode) listener.getRoot();
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(1));

        assertThat(booleanNode.value(),is(true));
    }
}

