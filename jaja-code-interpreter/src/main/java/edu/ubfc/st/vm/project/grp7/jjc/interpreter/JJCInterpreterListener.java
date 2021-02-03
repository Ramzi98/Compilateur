package edu.ubfc.st.vm.project.grp7.jjc.interpreter;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;

public interface    JJCInterpreterListener extends DebugListener {
    void jjcWrite(String str);
    void jjcWriteLn(String str);

    void nop();
}