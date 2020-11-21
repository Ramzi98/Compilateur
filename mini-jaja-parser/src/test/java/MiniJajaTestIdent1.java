import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ArrayItemNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaTestIdent1 {
    MiniJajaParser parser;

    private MiniJajaListenerImpl listener;
    private ParseTreeWalker walker;


    @Before
    public void setup(){
        walker = new ParseTreeWalker();
        listener= new MiniJajaListenerImpl();
    }

    @Test
    public void check__Ident1__Ident() throws IOException {
        TestConstructor testConstructor = new TestConstructor("ident1","Ident1IdentTest");
        parser = testConstructor.getParser();

        ParseTree tree = parser.ident1();
        walker.walk(listener,tree);

        parser.addParseListener(listener);

        IdentNode identNode = (IdentNode) listener.getRoot();

        assertThat(identNode.value(),is("Hello"));

    }

    @Test
    public void check__Ident1__ArrayItem() throws IOException {
        TestConstructor testConstructor = new TestConstructor("ident1","Ident1ArrayItemTest");
        parser = testConstructor.getParser();

        ParseTree tree = parser.ident1();
        walker.walk(listener,tree);

        parser.addParseListener(listener);
        //System.out.println(listener.getRoot().getClass());
        ArrayItemNode arrayItemNode = (ArrayItemNode) listener.getRoot();

        assertThat(arrayItemNode.identifier().value(),is("tab"));
        IdentNode identNode = (IdentNode)arrayItemNode.expression();
        assertThat(identNode.value(),is("i"));
    }

}
