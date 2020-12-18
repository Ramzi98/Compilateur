package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WriteNodeTest {
    @Before
    public void setup() {
        writeNode = WriteNode.builder()
                .line(6)
                .column(4)
                .printable(printable)
                .build();
    }

    private WriteNode writeNode;

    @Test
    public void check__WriteNode__Line() {
        assertThat(writeNode.line(), is(6));
    }

    @Test
    public void check__WriteNode__Column() {
        assertThat(writeNode.column(), is(4));
    }

    @Test
    public void check__WriteNode__Printable() {
        assertThat(writeNode.printable(), Matchers.is(printable));
    }

    @Test
    public void check__WriteNode__DefaultBreakpoint() {
        assertThat(writeNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__WriteNode__Breakpoint() {
        writeNode = WriteNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(writeNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__WriteNode__ChildrenMethod__FirstChild() {
        assertThat(writeNode.children(0), Matchers.is(printable));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__WriteNode__ChildrenMethod__OtherChild() {
        writeNode.children(1);
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