import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import javax.management.ValueExp;
import java.io.IOException;
import java.nio.file.Paths;

public class MiniJajaVisitorDeclsTest extends TestCase {


    private final MiniJajaVisitorOverride myVisitor = new MiniJajaVisitorOverride();
    private final String ressourceTest = "src/ressourcesTest/decls/";

    @Test
    public void  testDeclsVisitor() throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(ressourceTest+"declsVisitorTest1.txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        MiniJajaParser parser = new MiniJajaParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.decls();

        myVisitor.visitDecls((MiniJajaParser.DeclsContext)tree);
        DeclsNode declsNode = (DeclsNode) myVisitor.stack.peek();
        MiniJajaNode varNode = declsNode.children(0);

        //int i = 5;
        Assert.assertEquals(varNode.getClass(),VarNodeImpl.class);
        Assert.assertEquals(varNode.children(0).getClass(), TypeMethNodeImpl.class);
        Assert.assertEquals(varNode.children(1).getClass(), IdentNodeImpl.class);

        TypeMethNode typeMethNode = (TypeMethNode) varNode.children(0);
        Assert.assertEquals(typeMethNode.value(), TypeMethNode.TypeMeth.INT);

        IdentNode identNode = (IdentNode) varNode.children(1);
        Assert.assertTrue(identNode.value().equals("i"));
        Assert.assertFalse(identNode.value().equals("j"));

        //ValueExp valueExp = (ValueExp) varNode.children(2);
        //Assert.assertEquals(valueExp);




    }

}
