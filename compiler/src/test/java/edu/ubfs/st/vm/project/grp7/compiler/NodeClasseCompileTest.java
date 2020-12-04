package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcGotoNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcIfNode;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeClasseCompileTest {
    public CompilerVisitor compiler;


    @Before
    public void start() {

        compiler = new CompilerVisitor();
        Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<>();
        MiniJajaNode classe = new MiniJajaNode() {
            @Override
            public Breakpoint breakpoint() {
                return null;
            }

            @Override
            public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public int line() {
                return 0;
            }

            @Override
            public int column() {
                return 0;
            }
        };
        ArrayList<HashMap<MiniJajaNode,Integer>> miniJajaNodes = new ArrayList<>();
        HashMap<MiniJajaNode,Integer>startingHash = new HashMap<>();
        startingHash.put(classe,1);
        stack.push(startingHash);
        miniJajaNodes.add(startingHash);
        compiler.setStack(stack);
        compiler.setMinijajaNodes(miniJajaNodes);



    }


    @Test
    public void NodeClasseCompilerVisitor() throws Exception {

        TypeMethNode typeMeth = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();

        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identclasse = IdentNode.builder().value("C").build();

        NumberNode expression = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth)
                .identifier(ident)
                .expression(expression)
                .build();

        VarsNode varsNode = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode)
                .vars(null)
                .build();

        DeclsNode declsNode = DeclsNode.builder()
                .line(1)
                .column(0)
                .decl(varNode)
                .decls(null)
                .build();

        IdentNode ident2 = IdentNode.builder().value("I").build();
        NumberNode expression2 = NumberNode.builder().value(2).build();
        AssignNode assignNode = AssignNode.builder().identifier(ident2).expression(expression2).build();
        IdentNode identIf = IdentNode.builder().value("I").build();
        NumberNode expNodeIf = NumberNode.builder().value(2).build();
        NumberNode expressionIf = NumberNode.builder().value(2).build();
        AssignNode assignNodeIf = AssignNode.builder().identifier(identIf).expression(expressionIf).build();

        IdentNode ident2If = IdentNode.builder().value("J").build();
        NumberNode expression2If = NumberNode.builder().value(3).build();
        AssignNode assignNode2If = AssignNode.builder().identifier(ident2If).expression(expression2If).build();
        InstrsNode instsrNode2If = InstrsNode.builder().line(1).column(0).instruction(assignNode2If).instrs(null).build();
        InstrsNode instsrNodeIf = InstrsNode.builder().line(1).column(0).instruction(assignNodeIf).instrs(instsrNode2If).build();

        IfNode ifNode = IfNode.builder().expression(expNodeIf).trueInstrs(instsrNodeIf).falseInstrs(instsrNode2If).build();

        InstrsNode InstrNodeIfGlobal = InstrsNode.builder().instruction(ifNode).instrs(null).build();
        InstrsNode instsrNode = InstrsNode.builder()
                .line(1)
                .column(0)
                .instruction(assignNode)
                .instrs(InstrNodeIfGlobal)
                .build();


        MainNode mainNode = MainNode.builder()
                .line(1)
                .column(0)
                .vars(varsNode)
                .instrs(instsrNode)
                .build();

        ClasseNode classeNode = ClasseNode.builder()
                .line(1)
                .column(0)
                .identifier(identclasse)
                .decls(declsNode)
                .methmain(mainNode)
                .build();

        compiler.visit(classeNode);

        assertThat(compiler.getJajaCodeNodes().size(), is(23));
        assertThat(compiler.getJajaCodeNodes().get(6),is(compiler.getJajaCodeNodes().get(5).children(0)));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(23));
        JcIfNode ifnode = (JcIfNode) compiler.getJajaCodeNodes().get(8);
        JcGotoNode gotonode = (JcGotoNode) compiler.getJajaCodeNodes().get(11);
        assertThat(ifnode.adresse(),is(13));
        assertThat(gotonode.adresse(),is(17));

        Compiler compiler1 = Compiler.CompilerBuilder(classeNode);

        compiler1.compile();
        JajaCodeNode jajaCodeNode = compiler1.firstJajaCodeNode();


        Compiler com = Compiler.CompilerBuilder(classeNode);
        com.compile();
    }



}
