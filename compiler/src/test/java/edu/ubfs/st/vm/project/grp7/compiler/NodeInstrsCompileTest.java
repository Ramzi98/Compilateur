package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jjc.ast.node.JcInitNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.AssignNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.NumberNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeInstrsCompileTest {

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
        ArrayList<JajaCodeNode> jjnodes = new ArrayList<>();
        JcInitNode init = JcInitNode.builder().build();
        jjnodes.add(init);
        compiler.setJajaCodeNodes(jjnodes);

    }


    @Test
    public void NodeInstrsCompilerVisitor() throws IOException, IllFormedNodeException, CompilerException {

        IdentNode ident = IdentNode.builder().value("I").build();
        NumberNode expression = NumberNode.builder().value(2).build();
        AssignNode assignNode = AssignNode.builder().identifier(ident).expression(expression).build();

        IdentNode ident2 = IdentNode.builder().value("J").build();
        NumberNode expression2 = NumberNode.builder().value(3).build();
        AssignNode assignNode2 = AssignNode.builder().identifier(ident2).expression(expression2).build();
        InstrsNode instsrNode2 = InstrsNode.builder().line(1).column(0).instruction(assignNode2).instrs(null).build();
        InstrsNode instsrNode = InstrsNode.builder().line(1).column(0).instruction(assignNode).instrs(instsrNode2).build();

        compiler.visit(instsrNode);

        assertThat(compiler.getJajaCodeNodes().size(), is(5));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(4));

    }


}
