package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumberNodeTest {
    @Before
    public void setup() {
        numberNode = NumberNode.builder()
                .line(8)
                .column(34)
                .value(12d)
                .build();
    }

    private NumberNode numberNode;

    @Test
    public void check__NumberNode__Line() {
        assertThat(numberNode.line(), is(8));
    }

    @Test
    public void check__NumberNode__Column() {
        assertThat(numberNode.column(), is(34));
    }

    @Test
    public void check__NumberNode__Value() {
        assertThat(numberNode.value(), is(12d));
    }

    @Test
    public void check__NumberNode__DefaultBreakpoint() {
        assertThat(numberNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__NumberNode__Breakpoint() {
        numberNode = NumberNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(numberNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__NumberNode__ChildrenMethod() {
        numberNode.children(0);
    }
}