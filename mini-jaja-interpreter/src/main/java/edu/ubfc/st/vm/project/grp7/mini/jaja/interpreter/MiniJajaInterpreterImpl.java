package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.stack.Stack;

public class MiniJajaInterpreterImpl implements MiniJajaInterpreter {
    private final Stack stack;
    private final ClasseNode ast;
    public MiniJajaInterpreterImpl(Stack stack, ClasseNode ast) {
        this.stack = stack;
        this.ast = ast;
    }

    @Override
    public void interpret() throws Exception {
        MiniJajaInterpreterVisitor visitor = new MiniJajaInterpreterVisitor(this.stack);
        ast.accept(visitor);
    }
}
