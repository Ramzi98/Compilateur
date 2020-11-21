import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserMainTest extends MiniJajaParserBaseTest {
    @Test
    public void emptyMain() {
        TestConstructor testConstructor = new TestConstructor("main { }");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methmain();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MainNode main = (MainNode) listener.getRoot();

        assertThat(main.line(), is(1));
        assertThat(main.column(), is(0));
        assertThat(main.vars(), is(nullValue()));
        assertThat(main.instrs(), is(nullValue()));
    }


    @Test
    public void instrsMain() {
        TestConstructor testConstructor = new TestConstructor("main {return 0;}");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methmain();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MainNode main = (MainNode) listener.getRoot();

        assertThat(main.line(), is(1));
        assertThat(main.column(), is(0));
        assertThat(main.vars(), is(nullValue()));

        assertThat(main.instrs().instrs(), is(nullValue()));
        ReturnNode retour = (ReturnNode)main.instrs().instruction();
        assertThat(((NumberNode)retour.ret()).value(), is(0d));
    }


    @Test
    public void methodArgsVarsInstrs() {
        TestConstructor testConstructor = new TestConstructor("main {\nint ret = 5;\nreturn ret;}");
        parser = testConstructor.getParser();

        ParseTree tree = parser.methmain();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        MainNode main = (MainNode) listener.getRoot();

        assertThat(main.line(), is(1));
        assertThat(main.column(), is(0));

        VarNode var = (VarNode) main.vars().var();
        assertThat(var.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(var.identifier().value(), is("ret"));
        assertThat(((NumberNode) var.expression()).value(), is(5d));
        assertThat(main.vars().vars(), is(nullValue()));

        ReturnNode retour = (ReturnNode) main.instrs().instruction();
        assertThat(((IdentNode) retour.ret()).value(), is("ret"));
        assertThat(main.instrs().instrs(), is(nullValue()));
    }
}
