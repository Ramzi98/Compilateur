package edu.ubfc.st.vm.project.grp7.jjc.interpreter;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import java.util.List;

public class JJCInterpreterImpl implements JJCInterpreter {
    private final Memory memory;
    private final List<JajaCodeNode> nodes;
    public JJCInterpreterImpl(Memory memory, List<JajaCodeNode> nodes) {
        this.memory = memory;
        this.nodes = nodes;
    }

    @Override
    public void interpret(JJCInterpreterController controller) throws Exception {
        if (nodes.isEmpty()) {
            throw new RuntimeException("Empty JajaCode structure to be interpreted");
        }
        JJCInterpreterVisitor visitor = new JJCInterpreterVisitor(this.memory, nodes, controller);
        nodes.get(0).accept(visitor);
    }
}
