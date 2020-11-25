import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MiniJajaParserFactTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Fact__Ident1() throws IOException {
        TestConstructor testConstructor = new TestConstructor("fact", "FactIdent1ArrayItemTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        ArrayItemNode arrayItemNode = (ArrayItemNode) listener.getRoot();
        assertThat(arrayItemNode.identifier().value(),is("tab"));
        assertThat(arrayItemNode.line(),is(1));
        assertThat(arrayItemNode.column(),is(0));

        IdentNode identNode = (IdentNode)arrayItemNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(4));
    }

    @Test
    public void check__Fact__AppelEEmpty() throws IOException {
        TestConstructor testConstructor = new TestConstructor("i()");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        AppelENode appelENode = (AppelENode) listener.getRoot();

        assertThat(appelENode.identifier().value(),is("i"));
        assertThat(appelENode.identifier().line(),is(1));
        assertThat(appelENode.identifier().column(),is(0));

        assertThat(appelENode.listexp(),is(nullValue()));

    }

    @Test
    public void check__Fact__AppelE() throws IOException {
        TestConstructor testConstructor = new TestConstructor("fact", "FactAppelETest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        AppelENode appelENode = (AppelENode) listener.getRoot();
        assertThat(appelENode.identifier().value(),is("ident"));
        assertThat(appelENode.identifier().line(),is(1));
        assertThat(appelENode.identifier().column(),is(0));

        ListExpNode listExpNode = appelENode.listexp();
        assertThat(appelENode.identifier().value(),is("ident"));

        PlusNode plusNode = (PlusNode) listExpNode.children(0);
        NumberNode numberNode = (NumberNode) plusNode.leftOperand();
        NumberNode numberNode1 = (NumberNode) plusNode.rightOperand();
        assertThat(numberNode.value(),is(1));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(8));
        assertThat(numberNode1.value(),is(3));
        assertThat(numberNode1.line(),is(1));
        assertThat(numberNode1.column(),is(12));


        ListExpNode listExpNode1 = listExpNode.listexp();
        IdentNode identNode = (IdentNode) listExpNode1.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(16));
    }

    @Test
    public void check__Fact__True() {
        TestConstructor testConstructor = new TestConstructor("true");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        BooleanNode booleanNode = (BooleanNode) listener.getRoot();
        assertThat(booleanNode.value(),is(true));
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(0));

    }

    @Test
    public void check__Fact__False() {
        TestConstructor testConstructor = new TestConstructor("false");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        BooleanNode booleanNode = (BooleanNode) listener.getRoot();
        assertThat(booleanNode.value(),is(false));
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(0));
    }

    @Test
    public void check__Fact__Number() {
        TestConstructor testConstructor = new TestConstructor("9712");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        NumberNode numberNode = (NumberNode) listener.getRoot();
        assertThat(numberNode.value(),is(9712));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(0));
    }

    @Test
    public void check__Fact__Exp()  {
        TestConstructor testConstructor = new TestConstructor("(5)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.fact());

        NumberNode numberNode = (NumberNode) listener.getRoot();
        assertThat(numberNode.value(),is(5));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(1));
    }
}
