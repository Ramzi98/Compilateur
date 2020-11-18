import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MiniJajaVisitorClassTest extends TestCase {

    private final MiniJajaVisitorOverride myVisitor = new MiniJajaVisitorOverride();

    MiniJajaNode identNode;
    MiniJajaNode declsNode;
    MiniJajaNode mainNode;
    MiniJajaNode classNode;
    MiniJajaParser.ClasseContext ctx;

    private final String ressourceTest = "src/ressourcesTest/class/";

        @Test
        public void  testClassVisitor() throws IOException {
            CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"classVisitorTest1.txt"));
            MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
            MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.classe();

            myVisitor.visitClasse((MiniJajaParser.ClasseContext)tree);
            ClasseNode  classeNode = (ClasseNode)myVisitor.stack.peek();
        }

    @Test
    public void  testClassVisitor2() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"classVisitorTest2.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.classe();

        myVisitor.visitClasse((MiniJajaParser.ClasseContext)tree);
        ClasseNode  classeNode = (ClasseNode)myVisitor.stack.peek();
    }

    @Test
    public void  testClassVisitor3() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"classVisitorTest3.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.classe();

        myVisitor.visitClasse((MiniJajaParser.ClasseContext)tree);
        ClasseNode  classeNode = (ClasseNode)myVisitor.stack.peek();
    }






}
