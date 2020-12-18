package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MinusNodeTest {
    @Before
    public void setup() {
        minusNode = MinusNode.builder()
                .line(15)
                .column(5)
                .expression(expression)
                .build();
    }

    private MinusNode minusNode;

    @Test
    public void check__MinusNode__Line() {
        assertThat(minusNode.line(), is(15));
    }

    @Test
    public void check__MinusNode__Column() {
        assertThat(minusNode.column(), is(5));
    }

    @Test
    public void check__MinusNode__Expression() {
        assertThat(minusNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__MinusNode__DefaultBreakpoint() {
        assertThat(minusNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__MinusNode__Breakpoint() {
        minusNode = MinusNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(minusNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__MinusNode__ChildrenMethod__FirstChild() {
        assertThat(minusNode.children(0), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__MinusNode__ChildrenMethod__OtherChild() {
        minusNode.children(1);
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