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
import java.util.ArrayList;
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
         ArrayList<HashMap<MiniJajaNode,Integer>> miniJajaNodes = new ArrayList<>();
        HashMap<MiniJajaNode,Integer>startingHash = new HashMap<>();
         startingHash.put(classe,1);
         stack.push(startingHash);
         miniJajaNodes.add(startingHash);

         compiler.setStack(stack);
         compiler.setMinijajaNodes(miniJajaNodes);
    }




}
