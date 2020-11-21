import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserIdentNodeTest extends MiniJajaParserBaseTest {
    @Test
    public void check__Ident__Correct() throws IOException {
        TestConstructor testConstructor = new TestConstructor("identifier","IdentNodeSimple");
        parser = testConstructor.getParser();

        ParseTree tree = parser.ident();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        IdentNode identNode = (IdentNode)listener.getRoot();
        assertThat(identNode.value(),is("Hello"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));
    }


}
