package edu.ubfc.st.vm.project.grp7.mjj.parser;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MiniJajaParserInstrsTest extends MiniJajaParserBaseTest {
    @Test
    public void uniqueInstrsIncrement() {
        TestConstructor testConstructor = new TestConstructor("b++ ;");
        parser = testConstructor.getParser();

        ParseTree tree = parser.instrs();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        InstrsNode instrsNode = (InstrsNode) listener.getRoot();

        assertThat(instrsNode.line(), is(1));
        assertThat(instrsNode.column(), is(0));

        assertThat(instrsNode.instrs(), is(nullValue()));

        IncrementNode inc = (IncrementNode) instrsNode.instruction();
        assertThat(((IdentNode)inc.identifier()).value(), is("b"));
    }


    @Test
    public void uniqueInstrsAssign() {
        TestConstructor testConstructor = new TestConstructor("foo[1] = bar ;");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instrs());

        InstrsNode instrsNode = (InstrsNode) listener.getRoot();

        assertThat(instrsNode.line(), is(1));
        assertThat(instrsNode.column(), is(0));

        assertThat(instrsNode.instrs(), is(nullValue()));

        AssignNode assign = (AssignNode) instrsNode.instruction();
        assertThat(((ArrayItemNode)assign.identifier()).identifier().value(), is("foo"));
        assertThat(((NumberNode)((ArrayItemNode)assign.identifier()).expression()).value(), is(1));
    }

    @Test
    public void multiInstrs() {
        TestConstructor testConstructor = new TestConstructor("write(\"oui\") ;\nb++ ;");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instrs());

        InstrsNode instrsNode = (InstrsNode) listener.getRoot();

        assertThat(instrsNode.line(), is(1));
        assertThat(instrsNode.column(), is(0));

        WriteNode write = (WriteNode) instrsNode.instruction();
        assertThat(((StringNode) write.printable()).value(), is("oui"));

        IncrementNode inc = (IncrementNode) instrsNode.instrs().instruction();
        assertThat(((IdentNode)inc.identifier()).value(), is("b"));

        assertThat(instrsNode.instrs().instrs(), is(nullValue()));
    }
}
