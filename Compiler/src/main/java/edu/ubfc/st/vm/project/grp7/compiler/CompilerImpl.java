package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CompilerImpl implements Compiler {

    private final MiniJajaNode node;
    private CompilerVisitor visitor;
    private Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<>();
    private ArrayList<HashMap<MiniJajaNode,Integer>> miniJajaNodes = new ArrayList<>();
    private HashMap<MiniJajaNode,Integer>startingHash = new HashMap<>();


    CompilerImpl(MiniJajaNode node){
        this.node = node;
    }

    @Override
    public void compile() throws Exception {

        visitor = new CompilerVisitor();
        visitor.setStack(stack);
        stack.push(startingHash);
        visitor.setStack(stack);
        visitor.setMinijajaNodes(miniJajaNodes);
        node.accept(visitor);
        
    }

    @Override
    public JajaCodeNode firstJajaCodeNode() {
        return visitor.getJajaCodeNodes().get(0);
    }

    @Override
    public ArrayList<JajaCodeNode> jajaCodeNodes() {
        return visitor.getJajaCodeNodes();
    }


}
