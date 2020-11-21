package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public interface Compiler {



    void compile() throws Exception;

    public static Compiler CompilerBuilder(MiniJajaNode node){

                return new CompilerImpl(node);

    }
}
