import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaTestTypeMethNode extends MiniJajaParserBaseTest{

    @Test
    public void check__TypeMeth__Int() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethInt");
        parser = testConstructor.getParser();

        ParseTree tree = parser.typemeth();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(),is(TypeMethNode.TypeMeth.INT));
        assertThat(typeMethNode.line(),is(1));
        assertThat(typeMethNode.column(),is(0));
    }

    @Test
    public void check__TypeMeth__Boolean() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethBoolean");
        parser = testConstructor.getParser();

        ParseTree tree = parser.typemeth();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(),is(TypeMethNode.TypeMeth.BOOLEAN));
        assertThat(typeMethNode.line(),is(1));
        assertThat(typeMethNode.column(),is(0));
    }

    @Test
    public void check__TypeMeth__Void() throws IOException {
        TestConstructor testConstructor = new TestConstructor("typeMeth","TypeMethVoid");
        parser = testConstructor.getParser();

        ParseTree tree = parser.typemeth();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        TypeMethNode typeMethNode = (TypeMethNode) listener.getRoot();

        assertThat(typeMethNode.value(),is(TypeMethNode.TypeMeth.VOID));
        assertThat(typeMethNode.line(),is(1));
        assertThat(typeMethNode.column(),is(0));
    }


}
