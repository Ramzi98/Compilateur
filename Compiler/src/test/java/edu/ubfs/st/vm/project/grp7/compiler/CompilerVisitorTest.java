package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.AssignNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
         HashMap<MiniJajaNode,Integer> startingHash = new HashMap<>();
         startingHash.put(classe,1);
         stack.push(startingHash);

         compiler.setStack(stack);
    }


    @Test
    public void NodeInstrsCompilerVisitor() throws IOException, IllFormedNodeException {

        IdentNode ident = IdentNode.builder().value("I").build();
        NumberNode expression = NumberNode.builder().value(2).build();
        AssignNode assignNode = AssignNode.builder().identifier(ident).expression(expression).build();

        InstrsNode instsrNode = InstrsNode.builder().line(1).column(0).instruction(assignNode).instrs(instrs).build();


        compiler.visit(instsrNode);

        for (HashMap<MiniJajaNode, Integer> mjjnodes : compiler.getMinijajaNodes() ){

            System.out.println(mjjnodes.entrySet());
        }

    }

    private static final InstrsNode instrs = new InstrsNode() {
        @Override
        public MiniJajaNode instruction() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 13;
        }

        @Override
        public int column() {
            return 10;
        }
    };

    @Test
    public void NodeVarCompilerVisitor() throws IOException, IllFormedNodeException {

        TypeMethNode typeMeth = TypeMethNode.builder().line(1).column(0).value(TypeMethNode.TypeMeth.INT).build();
        IdentNode ident = IdentNode.builder().value("I").build();
        NumberNode expression = NumberNode.builder().value(2).build();
        VarNode varNode = VarNode.builder().line(1).column(0).typeMeth(typeMeth).identifier(ident).expression(expression).build();

        compiler.visit(varNode);



    }
}
