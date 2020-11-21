import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserFactTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Fact__Ident1() throws IOException {
        TestConstructor testConstructor = new TestConstructor("fact", "FactIdent1ArrayItemTest");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

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
    public void check__Fact__AppelE() throws IOException {
        TestConstructor testConstructor = new TestConstructor("fact", "FactAppelETest");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        AppelENode appelENode = (AppelENode) listener.getRoot();
        System.out.println(appelENode.identifier().value());
        assertThat(appelENode.identifier().value(),is("ident"));
        assertThat(appelENode.identifier().line(),is(1));
        assertThat(appelENode.identifier().column(),is(0));

        ListExpNode listExpNode = (ListExpNode )appelENode.listexp();
        assertThat(appelENode.identifier().value(),is("ident"));

        PlusNode plusNode = (PlusNode) listExpNode.children(0);
        NumberNode numberNode = (NumberNode) plusNode.leftOperand();
        NumberNode numberNode1 = (NumberNode) plusNode.rightOperand();
        assertThat(numberNode.value(),is(1.0));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(8));
        assertThat(numberNode1.value(),is(3.0));
        assertThat(numberNode1.line(),is(1));
        assertThat(numberNode1.column(),is(12));


        ListExpNode listExpNode1 = (ListExpNode) listExpNode.listexp();
        IdentNode identNode = (IdentNode) listExpNode1.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(16));
    }

    @Test
    public void check__Fact__True()  {
        TestConstructor testConstructor = new TestConstructor("true");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        BooleanNode booleanNode = (BooleanNode ) listener.getRoot();
        assertThat(booleanNode.value(),is(true));
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(0));

    }

    @Test
    public void check__Fact__False()  {
        TestConstructor testConstructor = new TestConstructor("false");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        BooleanNode booleanNode = (BooleanNode ) listener.getRoot();
        assertThat(booleanNode.value(),is(false));
        assertThat(booleanNode.line(),is(1));
        assertThat(booleanNode.column(),is(0));
    }

    @Test
    public void check__Fact__Number()  {
        TestConstructor testConstructor = new TestConstructor("9712.8217");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);
        parser.addParseListener(listener);

        NumberNode numberNode = (NumberNode) listener.getRoot();
        assertThat(numberNode.value(),is(9712.8217));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(0));
    }

    @Test
    public void check__Fact__Exp()  {
        TestConstructor testConstructor = new TestConstructor("(5)");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);
        parser.addParseListener(listener);

        NumberNode numberNode = (NumberNode) listener.getRoot();
        assertThat(numberNode.value(),is(5.0));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(1));
    }

    @Test(expected = java.lang.NumberFormatException.class )
    public void check__Fact__Crash() {
        TestConstructor testConstructor = new TestConstructor(",5");
        parser = testConstructor.getParser();

        ParseTree tree = parser.fact();
        walker.walk(listener, tree);
        parser.addParseListener(listener);
    }
}
