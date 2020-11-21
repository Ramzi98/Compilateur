package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class CompilerImpl implements Compiler {

    private final MiniJajaNode node;

    CompilerImpl(MiniJajaNode node){
        this.node = node;
    }

    @Override
    public void compile() throws Exception {

        CompilerVisitor visitor = new CompilerVisitor();
        node.accept(visitor);
    }
}
