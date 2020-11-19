import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class MiniJajaVisitorFactTest extends TestCase {

    private final MiniJajaVisitorOverride myVisitor = new MiniJajaVisitorOverride();
    private final String ressourceTest = "src/ressourcesTest/fact/";
    @Test
    public void  testFactVisitor() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest1.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        BooleanNode booleanNode = (BooleanNode) myVisitor.stack.peek();
        Assert.assertTrue(booleanNode.value());
    }

    @Test
    public void  testFactVisitor2() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest2.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        BooleanNode booleanNode = (BooleanNode) myVisitor.stack.peek();
        Assert.assertFalse(booleanNode.value());
    }

    @Test
    public void  testFactVisitor3() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest3.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        NumberNode numberNode = (NumberNode) myVisitor.stack.peek();
        Assert.assertEquals(numberNode.value(),87126312,0.1);
    }

    @Test
    public void  testFactVisitor4() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest4.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        NumberNode numberNode = (NumberNode) myVisitor.stack.peek();
        Assert.assertEquals(numberNode.value(),671257.81762,0.4);
    }

    @Test
    public void  testFactVisitor5() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest5.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        IdentNode identNode = (IdentNode) myVisitor.stack.peek();
        Assert.assertEquals(identNode.value(),"Hello");
    }

    @Test
    public void  testFactVisitor6() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"factVisitorTest6.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.fact();

        myVisitor.visitFact((MiniJajaParser.FactContext)tree);
        ArrayItemNode arrayItemNode = (ArrayItemNode) myVisitor.stack.peek();
        Assert.assertEquals(arrayItemNode.identifier(),"tab");
        IdentNode identNode = (IdentNode)arrayItemNode.expression();
        //Assert.assertEquals(identNode.value(),"i");


    }


}
//-671257