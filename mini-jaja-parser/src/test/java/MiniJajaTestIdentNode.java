import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.AppelENode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.io.IOException;

public class MiniJajaTestIdentNode  {

    MiniJajaParser parser;

    private MiniJajaListenerImpl listener;
    private ParseTreeWalker walker;

    @Before
    public void setup(){
        walker = new ParseTreeWalker();
        listener= new MiniJajaListenerImpl();
    }

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
