package edu.ubfc.st.vm.project.grp7.mini.jaja.parser;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserTypeNodeTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Type__Int() throws IOException {
        TestConstructor testConstructor = new TestConstructor("type","TypeInt");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.type());

        TypeNode typeNode = (TypeNode) listener.getRoot();
        assertThat(typeNode.value(), is(TypeNode.Type.INT));
        assertThat(typeNode.line(), is(1));
        assertThat(typeNode.column(), is(0));
    }

    @Test
    public void check__Type__Boolean() throws IOException {
        TestConstructor testConstructor = new TestConstructor("type","TypeBoolean");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.type());

        TypeNode typeNode = (TypeNode) listener.getRoot();
        assertThat(typeNode.value(), is(TypeNode.Type.BOOLEAN));
        assertThat(typeNode.line(), is(1));
        assertThat(typeNode.column(), is(0));
    }
}
