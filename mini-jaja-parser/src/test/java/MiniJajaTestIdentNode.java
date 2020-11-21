
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.io.IOException;

public class MiniJajaTestIdentNode extends MiniJajaParserBaseTest {
    @Test
    public void check__Ident__Correct() throws IOException {
        TestConstructor testConstructor = new TestConstructor("identifier","IdentifierVisitorTest1");
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
