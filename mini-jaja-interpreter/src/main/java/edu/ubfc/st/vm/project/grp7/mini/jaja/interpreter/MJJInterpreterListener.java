package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.debug.DebugListener;

public interface MJJInterpreterListener extends DebugListener {
    void mjjWrite(String str);
    void mjjWriteLn(String str);
}
