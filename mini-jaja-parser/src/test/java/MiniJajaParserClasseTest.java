import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserClasseTest extends MiniJajaParserBaseTest {
    @Test
    public void givenEmptyClass__whenParsing__thenClassNodeOK() throws IOException {
        TestConstructor testConstructor = new TestConstructor("classe","emptyClass");
        parser = testConstructor.getParser();

        ParseTree tree = parser.classe();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        ClasseNode classe = (ClasseNode) listener.getRoot();
        assertThat(classe.identifier().value(),is("C"));
        assertThat(classe.line(),is(1));
        assertThat(classe.column(),is(0));
        assertThat(classe.decls(), is(nullValue()));
        assertThat(classe.methmain(), is(notNullValue()));
    }

    @Test
    public void givenDeclClass__whenParsing__thenClassNodeOK() throws IOException {
        TestConstructor testConstructor = new TestConstructor("classe","declsClass");
        parser = testConstructor.getParser();

        ParseTree tree = parser.classe();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        ClasseNode classe = (ClasseNode) listener.getRoot();
        assertThat(classe.identifier().value(),is("Test_01"));
        assertThat(classe.line(),is(1));
        assertThat(classe.column(),is(0));
        assertThat(classe.decls().decl().line(), is(2));
        assertThat(classe.decls().decl().column(), is(4));
        assertThat(classe.methmain().line(), is(5));
        assertThat(classe.methmain().column(), is(4));
    }
}
