package edu.ubfc.st.vm.project.grp7.mini.jaja.parser;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ListExpNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserListExpTest extends MiniJajaParserBaseTest {
    @Test
    public void check__ListExp__ListExp() {
        TestConstructor testConstructor = new TestConstructor("6, (6)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.listexp());

        ListExpNode listExpNode = (ListExpNode) listener.getRoot();
        assertThat(listExpNode.line(), is(1));
        assertThat(listExpNode.column(), is(0));

        NumberNode numberNode = (NumberNode) listExpNode.expression();
        assertThat(numberNode.line(), is(1));
        assertThat(numberNode.column(), is(0));
        assertThat(numberNode.value(), is(6));

        ListExpNode listExpNode1 = listExpNode.listexp();
        assertThat(listExpNode1.line(), is(1));
        assertThat(listExpNode1.column(), is(3));

        NumberNode numberNode1 = (NumberNode) listExpNode1.expression();
        assertThat(numberNode1.line(), is(1));
        assertThat(numberNode1.column(), is(4));
        assertThat(numberNode1.value(), is(6));
        assertThat(listExpNode1.listexp(), is(nullValue()));
    }

    @Test
    public void check__ListExp__Exp() {
        TestConstructor testConstructor = new TestConstructor("6");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.listexp());

        ListExpNode listExpNode = (ListExpNode) listener.getRoot();
        assertThat(listExpNode.line(), is(1));
        assertThat(listExpNode.column(), is(0));

        NumberNode numberNode = (NumberNode) listExpNode.expression();
        assertThat(numberNode.line(), is(1));
        assertThat(numberNode.column(), is(0));
        assertThat(numberNode.value(), is(6));

        ListExpNode listExpNode1 = listExpNode.listexp();
        assertThat(listExpNode1, is(nullValue()));
    }



}
