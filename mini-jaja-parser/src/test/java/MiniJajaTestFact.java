import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ArrayItemNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaTestFact {
    MiniJajaParser parser;

    private MiniJajaListenerImpl listener;
    private ParseTreeWalker walker;

    @Before
    public void setup(){
        walker = new ParseTreeWalker();
        listener= new MiniJajaListenerImpl();
    }

    @Test
    public void check__Fact__Ident1() throws IOException {
        TestConstructor testConstructor = new TestConstructor("fact", "FactIdent1ArrayItemTest.txt");
        parser = testConstructor.getParser();

        ParseTree tree = parser.ident();
        walker.walk(listener, tree);

        parser.addParseListener(listener);

        ArrayItemNode arrayItemNode = (ArrayItemNode) listener.getRoot();

        assertThat(arrayItemNode.identifier().value(),is("tab"));
        IdentNode identNode = (IdentNode)arrayItemNode.expression();
        assertThat(identNode.value(),is("i"));
    }

    @Test
    public void check__Fact__AppelE() throws IOException {

    }
}
