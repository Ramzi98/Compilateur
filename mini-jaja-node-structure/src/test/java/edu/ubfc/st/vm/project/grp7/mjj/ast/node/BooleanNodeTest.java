package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BooleanNodeTest {
    @Before 
    public void setup() {
        booleanNode = BooleanNode.builder()
                .line(8)
                .column(34)
                .value(true)
                .build();   
    }
    
    private BooleanNode booleanNode;

    @Test
    public void check__BooleanNode__Line() {
        assertThat(booleanNode.line(), is(8));
    }

    @Test
    public void check__BooleanNode__Column() {
        assertThat(booleanNode.column(), is(34));
    }

    @Test
    public void check__BooleanNode__Value() {
        assertThat(booleanNode.value(), is(true));
    }

    @Test
    public void check__BooleanNode__DefaultBreakpoint() {
        assertThat(booleanNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__BooleanNode__Breakpoint() {
        booleanNode = BooleanNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(booleanNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__BooleanNode__ChildrenMethod() {
        booleanNode.children(0);
    }
}