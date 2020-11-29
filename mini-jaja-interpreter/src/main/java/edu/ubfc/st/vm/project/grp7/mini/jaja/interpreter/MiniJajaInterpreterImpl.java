package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;

public class MiniJajaInterpreterImpl implements MiniJajaInterpreter {
    private final Memory memory;
    private final ClasseNode ast;
    public MiniJajaInterpreterImpl(Memory memory, ClasseNode ast) {
        this.memory = memory;
        this.ast = ast;
    }

    @Override
    public void interpret(MJJInterpreterController controller) throws Exception {
        MiniJajaInterpreterVisitor visitor = new MiniJajaInterpreterVisitor(this.memory, controller);
        ast.accept(visitor);
    }
}
