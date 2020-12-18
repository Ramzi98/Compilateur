package edu.ubfc.st.vm.project.grp7.mjj.parser;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.TypeMethNode;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserTypeMethNodeTest extends MiniJajaParserBaseTest{

    @Test
    public void check__TypeMeth__Int() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethInt");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.typemeth());

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(), is(TypeMethNode.TypeMeth.INT));
        assertThat(typeMethNode.line(), is(1));
        assertThat(typeMethNode.column(), is(0));
    }

    @Test
    public void check__TypeMeth__Boolean() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethBoolean");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.typemeth());

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(), is(TypeMethNode.TypeMeth.BOOLEAN));
        assertThat(typeMethNode.line(), is(1));
        assertThat(typeMethNode.column(), is(0));
    }

    @Test
    public void check__TypeMeth__Void() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethVoid");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.typemeth());

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(), is(TypeMethNode.TypeMeth.VOID));
        assertThat(typeMethNode.line(), is(1));
        assertThat(typeMethNode.column(), is(0));
    }
}
