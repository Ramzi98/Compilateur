package edu.ubfc.st.vm.project.grp7.mjj.parser;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.IdentNode;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserIdentNodeTest extends MiniJajaParserBaseTest {
    @Test
    public void check__Ident__Correct() throws IOException {
        TestConstructor testConstructor = new TestConstructor("identifier","IdentNodeSimple");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.ident());

        IdentNode identNode = (IdentNode)listener.getRoot();
        assertThat(identNode.value(),is("Hello"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));
    }
}
