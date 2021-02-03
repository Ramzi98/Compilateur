package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CompilerVisitorTest {
 public CompilerVisitor compiler;

    @Before
    public void start(){

         compiler = new CompilerVisitor();
         Stack<HashMap<MiniJajaNode,Integer>> stack = new Stack<>();
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
    public void compilerTest(){

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

        Compiler compilere = new CompilerImpl(classeNode);
        try {
            compilere.compile();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void compilerTest2() throws Exception {


        IdentNode identClasse = IdentNode.builder().value("test").build();
        IdentNode identI = IdentNode.builder().value("i").build();
        NumberNode five = NumberNode.builder().value(5).build();
        TypeMethNode integer = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        VarNode fiveVar = VarNode.builder().identifier(identI).typeMeth(integer).expression(five).build();
        DeclsNode decl1 = DeclsNode.builder().decl(fiveVar).decls(null).build();

        MainNode main = MainNode.builder().instrs(null).vars(null).build();
        ClasseNode classe = ClasseNode.builder().decls(decl1).identifier(identClasse).methmain(main).build();

        compiler.visit(classe);



    }

}
