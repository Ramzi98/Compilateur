package edu.ubfc.st.vm.project.grp7.mjj.parser;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.DivNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.MultNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.NumberNode;
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
        walker.walk(listener, parser.terme());

        MultNode multNode = (MultNode) listener.getRoot();
        NumberNode left = (NumberNode) multNode.leftOperand();
        NumberNode right = (NumberNode) multNode.rightOperand();
        assertThat(multNode.line(), is(1));
        assertThat(multNode.column(), is(0));


        assertThat(left.value(), is(6));
        assertThat(left.line(), is(1));
        assertThat(left.column(), is(0));

        assertThat(right.value(), is(5));
        assertThat(right.line(), is(1));
        assertThat(right.column(), is(4));
    }

    @Test
    public void check__Terme__Div() {
        TestConstructor testConstructor = new TestConstructor("6 / 5");
        parser = testConstructor.getParser();

        ParseTree tree = parser.terme();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        DivNode divNode = (DivNode) listener.getRoot();
        NumberNode left = (NumberNode) divNode.leftOperand();
        NumberNode right = (NumberNode) divNode.rightOperand();
        assertThat(divNode.line(), is(1));
        assertThat(divNode.column(), is(0));


        assertThat(left.value(), is(6));
        assertThat(left.line(), is(1));
        assertThat(left.column(), is(0));

        assertThat(right.value(), is(5));
        assertThat(right.line(), is(1));
        assertThat(right.column(), is(4));
    }

    @Test
    public void check__Terme__Fact() {
        TestConstructor testConstructor = new TestConstructor("fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.terme());

        IdentNode identNode = (IdentNode) listener.getRoot();
        assertThat(identNode.line(), is(1));
        assertThat(identNode.column(), is(0));
        assertThat(identNode.value(), is("fact"));
    }


}
