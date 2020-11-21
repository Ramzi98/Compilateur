import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.HeaderNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.HeadersNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MiniJajaParserHeadersTest extends MiniJajaParserBaseTest {
    @Test
    public void uniqueHeader() {
        TestConstructor testConstructor = new TestConstructor("int arg");
        parser = testConstructor.getParser();

        ParseTree tree = parser.entetes();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        HeadersNode headers = (HeadersNode) listener.getRoot();
        assertThat(headers.line(),is(1));
        assertThat(headers.column(),is(0));

        HeaderNode header = headers.header();
        assertThat(header.type().value(), is(TypeNode.Type.INT));
        assertThat(header.identifier().value(), is("arg"));

        assertThat(headers.headers(), is(nullValue()));
    }


    @Test
    public void multiHeaders() {
        TestConstructor testConstructor = new TestConstructor("int arg, boolean flag");
        parser = testConstructor.getParser();

        ParseTree tree = parser.entetes();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        HeadersNode headers = (HeadersNode) listener.getRoot();
        assertThat(headers.line(),is(1));
        assertThat(headers.column(),is(0));

        HeaderNode firstHeader = headers.header();
        assertThat(firstHeader.type().value(), is(TypeNode.Type.INT));
        assertThat(firstHeader.identifier().value(), is("arg"));

        HeaderNode secondHeader = headers.headers().header();
        assertThat(secondHeader.type().value(), is(TypeNode.Type.BOOLEAN));
        assertThat(secondHeader.identifier().value(), is("flag"));

        assertThat(headers.headers().headers(), is(nullValue()));
    }
}
