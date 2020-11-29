package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor ;
    private Memory memory;
    private MJJInterpreterController controller;

    @Before
    public  void init(){
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        mjjVisitor = new MiniJajaInterpreterVisitor(memory, controller);
    }


}