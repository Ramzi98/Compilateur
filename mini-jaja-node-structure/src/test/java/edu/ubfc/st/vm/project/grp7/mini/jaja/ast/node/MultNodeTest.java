package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultNodeTest {
    @Before
    public void setup() {
        multNode = MultNode.builder()
                .line(1)
                .column(4)
                .leftOperand(leftOperand)
                .rightOperand(rightOperand)
                .build();
    }

    private MultNode multNode;

    @Test
    public void check__MultNode__Line() {
        assertThat(multNode.line(), is(1));
    }

    @Test
    public void check__MultNode__Column() {
        assertThat(multNode.column(), is(4));
    }

    @Test
    public void check__MultNode__DefaultBreakpoint() {
        assertThat(multNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__MultNode__LeftOperand() {
        assertThat(multNode.leftOperand(), is(leftOperand));
    }

    @Test
    public void check__MultNode__RightOperand() {
        assertThat(multNode.rightOperand(), is(rightOperand));
    }

    @Test
    public void check__MultNode__ChildrenMethod__FirstChild() {
        assertThat(multNode.children(0), is(leftOperand));
    }

    @Test
    public void check__MultNode__ChildrenMethod__SecondChild() {
        assertThat(multNode.children(1), is(rightOperand));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__MultNode__ChildrenMethod__OtherChild() {
        multNode.children(2);
    }

    @Test
    public void check__MultNode__Breakpoint() {
        multNode = MultNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(multNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
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