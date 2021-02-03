package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SubNodeTest {
    @Before
    public void setup() {
        subNode = SubNode.builder()
                .line(1)
                .column(4)
                .leftOperand(leftOperand)
                .rightOperand(rightOperand)
                .build();
    }

    private SubNode subNode;

    @Test
    public void check__SubNode__Line() {
        assertThat(subNode.line(), is(1));
    }

    @Test
    public void check__SubNode__Column() {
        assertThat(subNode.column(), is(4));
    }

    @Test
    public void check__SubNode__DefaultBreakpoint() {
        assertThat(subNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__SubNode__LeftOperand() {
        assertThat(subNode.leftOperand(), Matchers.is(leftOperand));
    }

    @Test
    public void check__SubNode__RightOperand() {
        assertThat(subNode.rightOperand(), Matchers.is(rightOperand));
    }

    @Test
    public void check__SubNode__ChildrenMethod__FirstChild() {
        assertThat(subNode.children(0), Matchers.is(leftOperand));
    }

    @Test
    public void check__SubNode__ChildrenMethod__SecondChild() {
        assertThat(subNode.children(1), Matchers.is(rightOperand));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__SubNode__ChildrenMethod__OtherChild() {
        subNode.children(2);
    }

    @Test
    public void check__SubNode__Breakpoint() {
        subNode = SubNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(subNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
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