import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserVarsTest extends MiniJajaParserBaseTest {
    @Test
    public void givenOneVar__thenParsing__thenDeclsOK() throws IOException {
        TestConstructor testConstructor = new TestConstructor("int b = 6;");
        parser = testConstructor.getParser();

        ParseTree tree = parser.vars();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        VarsNode vars = (VarsNode) listener.getRoot();

        assertThat(vars.line(), is(1));
        assertThat(vars.column(), is(0));

        assertThat(vars.vars(), is(nullValue()));

        VarNode var = (VarNode) vars.var();
        assertThat(var.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(var.identifier().value(), is("b"));
        assertThat(((NumberNode)var.expression()).value(), is(6d));
    }


    @Test
    public void givenOneArray__thenParsing__thenDeclsOK() {
        TestConstructor testConstructor = new TestConstructor("int tab[7];");
        parser = testConstructor.getParser();

        ParseTree tree = parser.vars();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        VarsNode vars = (VarsNode) listener.getRoot();

        assertThat(vars.line(), is(1));
        assertThat(vars.column(), is(0));

        assertThat(vars.vars(), is(nullValue()));

        ArrayNode array = (ArrayNode) vars.var();
        assertThat(array.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(array.identifier().value(), is("tab"));
        assertThat(((NumberNode)array.expression()).value(), is(7d));
    }

    @Test
    public void givenCst__thenParsing__thenDeclsOK() {
        TestConstructor testConstructor = new TestConstructor("final boolean flag = true;");
        parser = testConstructor.getParser();

        ParseTree tree = parser.vars();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        VarsNode vars = (VarsNode) listener.getRoot();

        assertThat(vars.line(), is(1));
        assertThat(vars.column(), is(0));

        assertThat(vars.vars(), is(nullValue()));

        CstNode array = (CstNode) vars.var();
        assertThat(array.type().value(), is(TypeNode.Type.BOOLEAN));
        assertThat(array.identifier().value(), is("flag"));
        assertThat(((BooleanNode)array.expression()).value(), is(true));
    }

    @Test
    public void givenMultiVars__thenParsing__thenDeclsOK() {
        TestConstructor testConstructor = new TestConstructor("final boolean flag;\nint i = 0;");
        parser = testConstructor.getParser();

        ParseTree tree = parser.vars();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        VarsNode vars = (VarsNode) listener.getRoot();

        assertThat(vars.line(), is(1));
        assertThat(vars.column(), is(0));

        CstNode array = (CstNode) vars.var();
        assertThat(array.type().value(), is(TypeNode.Type.BOOLEAN));
        assertThat(array.identifier().value(), is("flag"));
        assertThat(array.expression(), is(nullValue()));

        VarNode next = (VarNode) vars.vars().var();
        assertThat(next.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(next.identifier().value(), is("i"));
        assertThat(((NumberNode)next.expression()).value(), is(0d));
        assertThat(vars.vars().vars(), is(nullValue()));
    }
}
