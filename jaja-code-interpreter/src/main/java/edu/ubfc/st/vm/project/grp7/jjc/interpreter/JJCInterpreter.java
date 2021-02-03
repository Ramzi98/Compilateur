package edu.ubfc.st.vm.project.grp7.jjc.interpreter;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import java.util.List;

public interface JJCInterpreter {
    static Factory getFactory() {
        return Factory.getFactory();
    }

    void interpret(JJCInterpreterController controller) throws Exception;

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

        public JJCInterpreter createFrom(Memory memory, List<JajaCodeNode> nodes) {
            return new JJCInterpreterImpl(memory, nodes);
        }
    }
}
