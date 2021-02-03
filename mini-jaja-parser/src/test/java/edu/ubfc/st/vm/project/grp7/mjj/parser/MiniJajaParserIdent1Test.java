package edu.ubfc.st.vm.project.grp7.mjj.parser;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.ArrayItemNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.IdentNode;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaParserIdent1Test extends MiniJajaParserBaseTest{

    @Test
    public void check__Ident1__Ident() throws IOException {
        TestConstructor testConstructor = new TestConstructor("ident1","Ident1IdentTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.ident1());

        parser.addParseListener(listener);

        IdentNode identNode = (IdentNode) listener.getRoot();

        assertThat(identNode.value(),is("Hello"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));

    }

    @Test
    public void check__Ident1__ArrayItem() throws IOException {
        TestConstructor testConstructor = new TestConstructor("ident1","Ident1ArrayItemTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.ident1());

        ArrayItemNode arrayItemNode = (ArrayItemNode) listener.getRoot();

        assertThat(arrayItemNode.identifier().value(),is("tab"));
        assertThat(arrayItemNode.line(),is(1));
        assertThat(arrayItemNode.column(),is(0));

        IdentNode identNode = (IdentNode) arrayItemNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(4));
    }
}
