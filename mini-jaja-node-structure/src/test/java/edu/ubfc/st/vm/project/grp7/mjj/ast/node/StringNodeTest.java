package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringNodeTest {
    @Before
    public void setup() {
        stringNode = StringNode.builder()
                .line(168)
                .column(31)
                .value("to print")
                .build();
    }

    StringNode stringNode;

    @Test
    public void check__StringNode__Line() {
        assertThat(stringNode.line(), is(168));
    }

    @Test
    public void check__StringNode__Column() {
        assertThat(stringNode.column(), is(31));
    }

    @Test
    public void check__StringNode__Value() {
        assertThat(stringNode.value(), is("to print"));
    }

    @Test
    public void check__StringNode__DefaultBreakpoint() {
        assertThat(stringNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__StringNode__Breakpoint() {
        stringNode = StringNode.builder()
                .breakpoint(Breakpoint.NONE)
                .build();

        assertThat(stringNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__StringNode__ChildrenMethod() {
        stringNode.children(0);
    }
}