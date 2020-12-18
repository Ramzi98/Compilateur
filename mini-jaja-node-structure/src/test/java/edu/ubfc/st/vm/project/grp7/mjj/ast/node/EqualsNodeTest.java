package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EqualsNodeTest {
    @Before
    public void setup() {
        equalsNode = EqualsNode.builder()
                .line(1)
                .column(4)
                .leftOperand(leftOperand)
                .rightOperand(rightOperand)
                .build();
    }

    private EqualsNode equalsNode;

    @Test
    public void check__EqualsNode__Line() {
        assertThat(equalsNode.line(), is(1));
    }

    @Test
    public void check__EqualsNode__Column() {
        assertThat(equalsNode.column(), is(4));
    }

    @Test
    public void check__EqualsNode__DefaultBreakpoint() {
        assertThat(equalsNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__EqualsNode__LeftOperand() {
        assertThat(equalsNode.leftOperand(), Matchers.is(leftOperand));
    }

    @Test
    public void check__EqualsNode__RightOperand() {
        assertThat(equalsNode.rightOperand(), Matchers.is(rightOperand));
    }

    @Test
    public void check__EqualsNode__ChildrenMethod__FirstChild() {
        assertThat(equalsNode.children(0), Matchers.is(leftOperand));
    }

    @Test
    public void check__EqualsNode__ChildrenMethod__SecondChild() {
        assertThat(equalsNode.children(1), Matchers.is(rightOperand));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__EqualsNode__ChildrenMethod__OtherChild() {
        equalsNode.children(2);
    }

    @Test
    public void check__EqualsNode__Breakpoint() {
        equalsNode = EqualsNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(equalsNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
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