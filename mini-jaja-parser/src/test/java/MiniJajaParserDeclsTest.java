import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserDeclsTest extends MiniJajaParserBaseTest {
    @Test
    public void givenOneVar__whenParsing__thenDeclsOK() {
        TestConstructor testConstructor = new TestConstructor("int b = 6;");
        parser = testConstructor.getParser();

        ParseTree tree = parser.decls();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        DeclsNode decls = (DeclsNode) listener.getRoot();

        assertThat(decls.line(), is(1));
        assertThat(decls.column(), is(0));

        assertThat(decls.decls(), is(nullValue()));

        VarNode var = (VarNode) decls.decl();
        assertThat(var.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(var.identifier().value(), is("b"));
        assertThat(((NumberNode)var.expression()).value(), is(6d));
    }


    @Test
    public void givenOneMethod__whenParsing__thenDeclsOK() throws IOException {
        TestConstructor testConstructor = new TestConstructor("decls","methodDecls");
        parser = testConstructor.getParser();

        ParseTree tree = parser.decls();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        DeclsNode decls = (DeclsNode) listener.getRoot();

        assertThat(decls.line(), is(1));
        assertThat(decls.column(), is(0));

        assertThat(decls.decls(), is(nullValue()));

        MethodNode method = (MethodNode) decls.decl();
        assertThat(method.typeMeth().value(), is(TypeMethNode.TypeMeth.BOOLEAN));
        assertThat(method.identifier().value(), is("fun"));
    }


    @Test
    public void givenOneVarAndMethod__whenParsing__thenDeclsOK() throws IOException {
        TestConstructor testConstructor = new TestConstructor("decls","varAndMethodDecls");
        parser = testConstructor.getParser();

        ParseTree tree = parser.decls();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        DeclsNode decls = (DeclsNode) listener.getRoot();

        assertThat(decls.line(), is(1));
        assertThat(decls.column(), is(0));

        DeclsNode child = decls.decls();
        assertThat(child.decls(), is(nullValue()));
        assertThat(child.decl(), isA(MethodNode.class));

        VarNode var = (VarNode) decls.decl();
        assertThat(var.typeMeth().value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(var.identifier().value(), is("x"));
        assertThat(((NumberNode)var.expression()).value(), is(92.6d));
    }
}
