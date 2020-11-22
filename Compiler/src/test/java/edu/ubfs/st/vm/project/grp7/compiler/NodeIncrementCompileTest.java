package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.HeaderNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IncrementNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeIncrementCompileTest {
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
    public void NodeIncrementCompilerVisitor() throws IOException, IllFormedNodeException {

        IdentNode ident = IdentNode.builder()
                .value("B")
                .build();

        IncrementNode incrementNode = IncrementNode.builder()
                .line(1)
                .column(0)
                .identifier(ident)
                .build();

        compiler.visit(incrementNode);

        assertThat(compiler.getJajaCodeNodes().size(), is(2));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(2));


    }
}