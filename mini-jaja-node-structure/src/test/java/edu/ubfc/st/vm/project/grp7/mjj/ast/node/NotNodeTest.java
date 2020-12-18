package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NotNodeTest {
    @Before
    public void setup() {
        notNode = NotNode.builder()
                .line(15)
                .column(5)
                .expression(expression)
                .build();
    }

    private NotNode notNode;

    @Test
    public void check__NotNode__Line() {
        assertThat(notNode.line(), is(15));
    }

    @Test
    public void check__NotNode__Column() {
        assertThat(notNode.column(), is(5));
    }

    @Test
    public void check__NotNode__Expression() {
        assertThat(notNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__NotNode__DefaultBreakpoint() {
        assertThat(notNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__NotNode__Breakpoint() {
        notNode = NotNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(notNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__NotNode__ChildrenMethod__FirstChild() {
        assertThat(notNode.children(0), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__NotNode__ChildrenMethod__OtherChild() {
        notNode.children(1);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------


    private static final MiniJajaNode expression = new MiniJajaNode() {

        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.BREAKPOINT;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 12;
        }

        @Override
        public int column() {
            return 34;
        }
    };
}