package edu.ubfc.st.vm.project.grp7.mjj.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.ClasseNode;

public interface MiniJajaInterpreter {
    static Factory getFactory() {
        return Factory.getFactory();
    }

    void interpret(MJJInterpreterController controller) throws Exception;

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

        public MiniJajaInterpreter createFrom(Memory memory, ClasseNode ast) {
            return new MiniJajaInterpreterImpl(memory, ast);
        }
    }
}
