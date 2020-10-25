package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TypeMethNodeTest {
    @Before
    public void setup() {
        typeMethNode = TypeMethNode.builder()
                .line(8)
                .column(34)
                .value(TypeMethNode.TypeMeth.INT)
                .build();
    }

    TypeMethNode typeMethNode;

    @Test
    public void check__TypeMethNode__Line() {
        assertThat(typeMethNode.line(), is(8));
    }

    @Test
    public void check__TypeMethNode__Column() {
        assertThat(typeMethNode.column(), is(34));
    }

    @Test
    public void check__TypeMethNode__Value() {
        assertThat(typeMethNode.value(), is(TypeMethNode.TypeMeth.INT));
    }

    @Test
    public void check__TypeMethNode__DefaultBreakpoint() {
        assertThat(typeMethNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__TypeMethNode__Breakpoint() {
        typeMethNode = TypeMethNode.builder()
                .breakpoint(Breakpoint.BREAKPOINT)
                .build();

        assertThat(typeMethNode.breakpoint(), is(Breakpoint.BREAKPOINT));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__TypeMethNode__ChildrenMethod() {
        typeMethNode.children(0);
    }
}