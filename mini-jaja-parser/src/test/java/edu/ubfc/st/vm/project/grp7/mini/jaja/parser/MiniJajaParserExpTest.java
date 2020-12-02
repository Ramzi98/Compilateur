package edu.ubfc.st.vm.project.grp7.mini.jaja.parser;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserExpTest extends MiniJajaParserBaseTest{
    @Test
    public void check__Exp__not() {
        TestConstructor testConstructor = new TestConstructor("!true");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp());

        NotNode notNode = (NotNode) listener.getRoot();
        BooleanNode booleanNode = (BooleanNode)notNode.expression();
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(1));

        assertThat(booleanNode.value(),is(true));
    }

    @Test
    public void check__Exp__And() {
        TestConstructor testConstructor = new TestConstructor("6 && 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp());

        AndNode andNode = (AndNode) listener.getRoot();
        NumberNode left = (NumberNode)andNode.leftOperand();
        NumberNode right = (NumberNode)andNode.rightOperand();
        assertThat(andNode.line(),is(1));
        assertThat(andNode.column(),is(0));

        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(5));
    }

    @Test
    public void check__Exp__Or() {
        TestConstructor testConstructor = new TestConstructor("6 || 5");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp());

        OrNode orNode = (OrNode) listener.getRoot();
        NumberNode left = (NumberNode)orNode.leftOperand();
        NumberNode right = (NumberNode)orNode.rightOperand();
        assertThat(orNode.line(),is(1));
        assertThat(orNode.column(),is(0));

        assertThat(left.value(),is(6));
        assertThat(left.line(),is(1));
        assertThat(left.column(),is(0));

        assertThat(right.value(),is(5));
        assertThat(right.line(),is(1));
        assertThat(right.column(),is(5));
    }

    @Test
    public void check__Exp__Exp1() {
        TestConstructor testConstructor = new TestConstructor("(true)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.exp());

        BooleanNode booleanNode = (BooleanNode) listener.getRoot();
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(1));

        assertThat(booleanNode.value(),is(true));
    }
}

