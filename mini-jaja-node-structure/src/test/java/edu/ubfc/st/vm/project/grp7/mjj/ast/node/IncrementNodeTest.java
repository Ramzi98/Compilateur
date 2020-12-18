package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IncrementNodeTest {
    @Before
    public void setup() {
        incrementNode = IncrementNode.builder()
                .line(6)
                .column(4)
                .identifier(ident)
                .build();
    }

    private IncrementNode incrementNode;

    @Test
    public void check__IncrementNode__Line() {
        assertThat(incrementNode.line(), is(6));
    }

    @Test
    public void check__IncrementNode__Column() {
        assertThat(incrementNode.column(), is(4));
    }

    @Test
    public void check__IncrementNode__Identifier() {
        assertThat(incrementNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__IncrementNode__DefaultBreakpoint() {
        assertThat(incrementNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__IncrementNode__Breakpoint() {
        incrementNode = IncrementNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(incrementNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__IncrementNode__ChildrenMethod__FirstChild() {
        assertThat(incrementNode.children(0), Matchers.is(ident));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__IncrementNode__ChildrenMethod__OtherChild() {
        incrementNode.children(1);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final MiniJajaNode ident = new IdentNode() {
        @Override
        public String value() {
            return "id";
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public int line() {
            return 95;
        }

        @Override
        public int column() {
            return 26;
        }
    };
}