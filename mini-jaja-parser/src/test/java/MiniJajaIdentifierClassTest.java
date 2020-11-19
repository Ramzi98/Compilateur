/*import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class MiniJajaIdentifierClassTest extends TestCase {
    private final String ressourceTest = "src/ressourcesTest/identifier/";
    MiniJajaVisitorOverride myVisitor = new MiniJajaVisitorOverride();
    @Test
    public void  testIdentifierVisitor() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"IdentifierVisitorTest1.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ident();

        myVisitor.visitIdent((MiniJajaParser.IdentContext)tree);
        IdentNode identNode = (IdentNode) myVisitor.stack.peek();
        Assert.assertTrue(identNode.value().equals("Hello"));
        Assert.assertEquals(identNode.line(),1);
        Assert.assertEquals(identNode.column(),0);
    }
}

*/
