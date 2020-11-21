import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserMethodTest extends MiniJajaParserBaseTest {
    @Test
    public void emptyMethodNoArgs() {
        TestConstructor testConstructor = new TestConstructor("void fun(){ }");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methode();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MethodNode method = (MethodNode) listener.getRoot();

        assertThat(method.line(), is(1));
        assertThat(method.column(), is(0));
        assertThat(method.vars(), is(nullValue()));
        assertThat(method.instrs(), is(nullValue()));
        assertThat(method.typeMeth().value(), is(TypeMethNode.TypeMeth.VOID));
        assertThat(method.identifier().value(), is("fun"));
        assertThat(method.headers(), is(nullValue()));
    }

    @Test
    public void emptyMethodIntArgs() {
        TestConstructor testConstructor = new TestConstructor("int fun(int arg){ }");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methode();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MethodNode method = (MethodNode) listener.getRoot();

        assertThat(method.line(), is(1));
        assertThat(method.column(), is(0));
        assertThat(method.vars(), is(nullValue()));
        assertThat(method.instrs(), is(nullValue()));
        assertThat(method.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(method.identifier().value(), is("fun"));

        assertThat(method.headers().header().type().value(), is(TypeNode.Type.INT));
        assertThat(method.headers().header().identifier().value(), is("arg"));
        assertThat(method.headers().headers(), is(nullValue()));
    }


    @Test
    public void instrsMethodBooleanArgs() {
        TestConstructor testConstructor = new TestConstructor("int fun(boolean flag){return 0;}");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methode();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MethodNode method = (MethodNode) listener.getRoot();

        assertThat(method.line(), is(1));
        assertThat(method.column(), is(0));
        assertThat(method.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(method.identifier().value(), is("fun"));
        assertThat(method.vars(), is(nullValue()));

        assertThat(method.instrs().instrs(), is(nullValue()));
        ReturnNode retour = (ReturnNode)method.instrs().instruction();
        assertThat(((NumberNode)retour.ret()).value(), is(0d));

        assertThat(method.headers().header().type().value(), is(TypeNode.Type.BOOLEAN));
        assertThat(method.headers().header().identifier().value(), is("flag"));
        assertThat(method.headers().headers(), is(nullValue()));
    }


    @Test
    public void methodBooleanArgsVarsInstrs() {
        TestConstructor testConstructor = new TestConstructor("int fun(boolean flag){\nint ret = 5;\nreturn ret;}");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methode();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MethodNode method = (MethodNode) listener.getRoot();

        assertThat(method.line(), is(1));
        assertThat(method.column(), is(0));
        assertThat(method.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(method.identifier().value(), is("fun"));

        VarNode var = (VarNode) method.vars().var();
        assertThat(var.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(var.identifier().value(), is("ret"));
        assertThat(((NumberNode) var.expression()).value(), is(5d));
        assertThat(method.vars().vars(), is(nullValue()));
        ReturnNode retour = (ReturnNode) method.instrs().instruction();
        assertThat(((IdentNode) retour.ret()).value(), is("ret"));
        assertThat(method.instrs().instrs(), is(nullValue()));

        assertThat(method.headers().header().type().value(), is(TypeNode.Type.BOOLEAN));
        assertThat(method.headers().header().identifier().value(), is("flag"));
        assertThat(method.headers().headers(), is(nullValue()));
    }
}
