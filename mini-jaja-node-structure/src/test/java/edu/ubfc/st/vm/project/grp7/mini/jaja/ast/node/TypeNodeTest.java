package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TypeNodeTest {
    @Before
    public void setup() {
        typeNode = TypeNode.builder()
                .line(8)
                .column(34)
                .value(TypeNode.Type.BOOLEAN)
                .build();
    }

    TypeNode typeNode;

    @Test
    public void check__TypeNode__Line() {
        assertThat(typeNode.line(), is(8));
    }

    @Test
    public void check__TypeNode__Column() {
        assertThat(typeNode.column(), is(34));
    }

    @Test
    public void check__TypeNode__Value() {
        assertThat(typeNode.value(), is(TypeNode.Type.BOOLEAN));
    }

    @Test
    public void check__TypeNode__DefaultBreakpoint() {
        assertThat(typeNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__TypeNode__Breakpoint() {
        typeNode = TypeNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(typeNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__TypeNode__ChildrenMethod() {
        typeNode.children(0);
    }
}