package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrNodeTest {
    @Before
    public void setup() {
        orNode = OrNode.builder()
                .line(1)
                .column(4)
                .leftOperand(leftOperand)
                .rightOperand(rightOperand)
                .build();
    }

    private OrNode orNode;

    @Test
    public void check__OrNode__Line() {
        assertThat(orNode.line(), is(1));
    }

    @Test
    public void check__OrNode__Column() {
        assertThat(orNode.column(), is(4));
    }

    @Test
    public void check__OrNode__DefaultBreakpoint() {
        assertThat(orNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__OrNode__LeftOperand() {
        assertThat(orNode.leftOperand(), is(leftOperand));
    }

    @Test
    public void check__OrNode__RightOperand() {
        assertThat(orNode.rightOperand(), is(rightOperand));
    }

    @Test
    public void check__OrNode__ChildrenMethod__FirstChild() {
        assertThat(orNode.children(0), is(leftOperand));
    }

    @Test
    public void check__OrNode__ChildrenMethod__SecondChild() {
        assertThat(orNode.children(1), is(rightOperand));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__OrNode__ChildrenMethod__OtherChild() {
        orNode.children(2);
    }

    @Test
    public void check__OrNode__Breakpoint() {
        orNode = OrNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(orNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }


    // ---------------------------- Parameter Nodes ----------------------------

    private static final MiniJajaNode leftOperand = new MiniJajaNode() {
        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.NONE;
        }

        @Override
        public int line() {
            return 2;
        }

        @Override
        public int column() {
            return 1;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            if (n == 1)
                return null;
            else
                throw new IndexOutOfBoundsException();
        }
    };

    private static final MiniJajaNode rightOperand = new MiniJajaNode() {
        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.NONE;
        }

        @Override
        public int line() {
            return 2;
        }

        @Override
        public int column() {
            return 6;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            if (n == 1)
                return null;
            else
                throw new IndexOutOfBoundsException();
        }
    };
}