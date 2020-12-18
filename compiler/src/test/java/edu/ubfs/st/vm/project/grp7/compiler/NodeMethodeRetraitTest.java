package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.node.JcGotoNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeMethodeRetraitTest {
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
    public void NodeMethodeRetraitVisitor() throws IOException, IllFormedNodeException, CompilerException {

        TypeMethNode typeMeth = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.VOID)
                .build();

        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identf = IdentNode.builder().value("f").build();

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



        IdentNode ident2 = IdentNode.builder().value("I").build();
        NumberNode expression2 = NumberNode.builder().value(2).build();
        AssignNode assignNode = AssignNode.builder().identifier(ident2).expression(expression2).build();

        InstrsNode instsrNode = InstrsNode.builder()
                .line(1)
                .column(0)
                .instruction(assignNode)
                .instrs(null)
                .build();

        TypeNode type = TypeNode.builder()
                .line(1)
                .column(0)
                .value(TypeNode.Type.BOOLEAN)
                .build();


        HeaderNode headerNode = HeaderNode.builder()
                .line(1)
                .column(0)
                .type(type)
                .identifier(ident)
                .build();

        HeaderNode headerNode2 = HeaderNode.builder()
                .line(1)
                .column(0)
                .type(type)
                .identifier(ident2)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .line(1)
                .column(10)
                .header(headerNode)
                .headers(null)
                .build();

        HeadersNode headersNode2 = HeadersNode.builder()
                .line(1)
                .column(10)
                .header(headerNode2)
                .headers(headersNode)
                .build();

        TypeMethNode typeMeth2 = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();



        VarNode varNode3 = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth2)
                .identifier(ident)
                .expression(expression)
                .build();

        VarNode varNode2 = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth2)
                .identifier(ident2)
                .expression(expression2)
                .build();

        VarsNode varsNode2 = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode2)
                .vars(null)
                .build();

        VarsNode varsNode3 = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode3)
                .vars(varsNode2)
                .build();


        MethodNode methodNode = MethodNode.builder()
                .headers(headersNode2)
                .identifier(identf)
                .instrs(instsrNode)
                .typeMeth(typeMeth)
                .vars(varsNode3)
                .build();

        DeclsNode declsNode = DeclsNode.builder()
                .line(1)
                .column(0)
                .decl(methodNode)
                .decls(null)
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

        assertThat(compiler.getJajaCodeNodes().size(), is(30));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(30));

        JcGotoNode jcGotoNode = (JcGotoNode) compiler.getJajaCodeNodes().get(3);
        assertThat(jcGotoNode.adresse(), is(20));

    }

}
