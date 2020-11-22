package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcGotoNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcIfNode;
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

public class NodeWhileCompileTest {

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
        startingHash.put(classe,35);
        stack.push(startingHash);
        miniJajaNodes.add(startingHash);
        compiler.setStack(stack);
        compiler.setMinijajaNodes(miniJajaNodes);

    }


    @Test
    public void NodeWhileCompileVisitor() throws IOException, IllFormedNodeException {

        NumberNode expNode = NumberNode.builder().value(2).build();
        IdentNode identNode = IdentNode.builder().value("J").build();
        NumberNode expression = NumberNode.builder().value(3).build();
        AssignNode assignNode = AssignNode.builder().identifier(identNode).expression(expression).build();
        InstrsNode instrsNode = InstrsNode.builder().line(1).column(0).instruction(assignNode).instrs(inil).build();

        WhileNode whileNode = WhileNode.builder().expression(expNode).instrs(instrsNode).build();
        compiler.visit(whileNode);
        assertThat(compiler.getJajaCodeNodes().size(), is(6));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(6));

        JcIfNode jcIfNode = (JcIfNode) (compiler.getJajaCodeNodes().get(2));
        assertThat(jcIfNode.adresse(),is(41));
        JcGotoNode jcGotoNode = (JcGotoNode) (compiler.getJajaCodeNodes().get(5));
        assertThat(jcGotoNode.adresse(),is(35));
        System.out.println(compiler.getJajaCodeNodes().toString());
    }
    private static final InstrsNode inil = new InstrsNode() {

        @Override
        public Breakpoint breakpoint() {
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

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public MiniJajaNode instruction() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
            return null;
        }
    };
}