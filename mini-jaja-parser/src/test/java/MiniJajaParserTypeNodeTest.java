import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserTypeNodeTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Type__Int() throws IOException {
        TestConstructor testConstructor = new TestConstructor("type","TypeInt");
        parser = testConstructor.getParser();

        ParseTree tree = parser.type();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        TypeNode typeNode = (TypeNode) listener.getRoot();
        assertThat(typeNode.value(),is(TypeNode.Type.INT));
        assertThat(typeNode.line(),is(1));
        assertThat(typeNode.column(),is(0));
    }

    @Test
    public void check__Type__Boolean() throws IOException {
        TestConstructor testConstructor = new TestConstructor("type","TypeBoolean");
        parser = testConstructor.getParser();

        ParseTree tree = parser.type();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        TypeNode typeNode = (TypeNode) listener.getRoot();
        assertThat(typeNode.value(),is(TypeNode.Type.BOOLEAN));
        assertThat(typeNode.line(),is(1));
        assertThat(typeNode.column(),is(0));
    }
}
