package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.stack.Stack;

public interface MiniJajaInterpreter {
    static Factory getFactory() {
        return Factory.getFactory();
    }

    void interpret() throws Exception;

    class Factory {
        private static Factory instance;
        private Factory() {
        }
        public static Factory getFactory() {
            if(instance == null) {
                instance = new Factory();
            }
            return instance;
        }

        public MiniJajaInterpreter createFrom(Stack stack, ClasseNode ast) {
            return new MiniJajaInterpreterImpl(stack, ast);
        }
    }
}
