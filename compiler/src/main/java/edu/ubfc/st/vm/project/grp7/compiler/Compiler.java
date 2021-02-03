package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

import java.util.ArrayList;

public interface Compiler {

    void compile() throws Exception;

    static Compiler CompilerBuilder(MiniJajaNode node){

                return new CompilerImpl(node);

    }

    public JajaCodeNode firstJajaCodeNode();
    public ArrayList<JajaCodeNode> jajaCodeNodes();
}
