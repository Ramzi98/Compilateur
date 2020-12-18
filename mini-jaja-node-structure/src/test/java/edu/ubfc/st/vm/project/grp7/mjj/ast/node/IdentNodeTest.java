package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IdentNodeTest {
    @Before
    public void setup() {
        identNode = IdentNode.builder()
                .line(8)
                .column(34)
                .value("identifiant")
                .build();
    }

    IdentNode identNode;

    @Test
    public void check__IdentNode__Line() {
        assertThat(identNode.line(), is(8));
    }

    @Test
    public void check__IdentNode__Column() {
        assertThat(identNode.column(), is(34));
    }

    @Test
    public void check__IdentNode__Value() {
        assertThat(identNode.value(), is("identifiant"));
    }

    @Test
    public void check__IdentNode__DefaultBreakpoint() {
        assertThat(identNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__IdentNode__Breakpoint() {
        identNode = IdentNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(identNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__IdentNode__ChildrenMethod() {
        identNode.children(0);
    }
}