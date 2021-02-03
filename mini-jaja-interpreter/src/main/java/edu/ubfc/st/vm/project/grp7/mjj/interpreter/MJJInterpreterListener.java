package edu.ubfc.st.vm.project.grp7.mjj.interpreter;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

public interface MJJInterpreterListener extends DebugListener {
    void mjjWrite(String str);
    void mjjWriteLn(String str);

    void setMemory(Memory memory);
}
