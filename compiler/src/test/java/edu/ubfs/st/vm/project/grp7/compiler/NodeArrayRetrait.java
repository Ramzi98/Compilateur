package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
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

public class NodeArrayRetrait {
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
    public void NodeArrayRetrait() throws IOException, IllFormedNodeException, CompilerException {

        TypeMethNode typeMeth = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();



        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identclasse = IdentNode.builder().value("C").build();

        NumberNode expression = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder()
                .identifier(ident)
                .typeMeth(typeMeth)
                .expression(expression)
                .build();


        VarsNode varsNode = VarsNode.builder()
                .var(varNode)
                .vars(null)
                .build();

        ArrayNode arrayNode = ArrayNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth)
                .identifier(ident)
                .expression(expression)
                .build();

        DeclsNode declsNode = DeclsNode.builder()
                .line(1)
                .column(0)
                .decl(arrayNode)
                .decls(null)
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

        assertThat(compiler.getJajaCodeNodes().size(), is(14));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(14));

    }

}
