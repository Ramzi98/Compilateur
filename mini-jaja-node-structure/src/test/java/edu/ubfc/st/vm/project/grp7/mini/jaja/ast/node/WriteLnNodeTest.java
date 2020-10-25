package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WriteLnNodeTest {
    @Before
    public void setup() {
        writeLnNode = WriteLnNode.builder()
                .line(7)
                .column(4)
                .printable(printable)
                .build();
    }

    private WriteLnNode writeLnNode;

    @Test
    public void check__WriteLnNode__Line() {
        assertThat(writeLnNode.line(), is(7));
    }

    @Test
    public void check__WriteLnNode__Column() {
        assertThat(writeLnNode.column(), is(4));
    }

    @Test
    public void check__WriteLnNode__Printable() {
        assertThat(writeLnNode.printable(), is(printable));
    }

    @Test
    public void check__WriteLnNode__DefaultBreakpoint() {
        assertThat(writeLnNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__WriteLnNode__Breakpoint() {
        writeLnNode = WriteLnNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(writeLnNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__WriteLnNode__ChildrenMethod__FirstChild() {
        assertThat(writeLnNode.children(0), is(printable));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__WriteLnNode__ChildrenMethod__OtherChild() {
        writeLnNode.children(1);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final MiniJajaNode printable = new IdentNode() {
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