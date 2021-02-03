package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SumNodeTest {
    @Before
    public void setup() {
        sumNode = SumNode.builder()
                .line(47)
                .column(18)
                .identifier(ident)
                .expression(expression)
                .build();
    }

    private SumNode sumNode;

    @Test
    public void check__SumNode__Line() {
        assertThat(sumNode.line(), is(47));
    }

    @Test
    public void check__SumNode__Column() {
        assertThat(sumNode.column(), is(18));
    }

    @Test
    public void check__SumNode__Identifier() {
        assertThat(sumNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__SumNode__ListExp() {
        assertThat(sumNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__SumNode__DefaultBreakpoint() {
        assertThat(sumNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__SumNode__Breakpoint() {
        sumNode = SumNode.builder()
                .breakpoint(Breakpoint.BREAKPOINT)
                .build();

        assertThat(sumNode.breakpoint(), is(Breakpoint.BREAKPOINT));
    }

    @Test
    public void check__SumNode__ChildrenMethod__FirstChild() {
        assertThat(sumNode.children(0), Matchers.is(ident));
    }

    @Test
    public void check__SumNode__ChildrenMethod__SecondChild() {
        assertThat(sumNode.children(1), Matchers.is(expression));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void check__SumNode__ChildrenMethod__OtherChild() {
        sumNode.children(3);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final MiniJajaNode ident = new IdentNode() {
        @Override
        public String value() {
            return "i";
        }

        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.NONE;
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

    private static final MiniJajaNode expression = new MiniJajaNode() {

        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.NONE;
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