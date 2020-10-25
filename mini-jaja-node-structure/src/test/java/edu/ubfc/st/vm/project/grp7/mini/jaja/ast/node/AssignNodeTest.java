package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AssignNodeTest {
    @Before
    public void setup() {
        assignNode = AssignNode.builder()
                .line(47)
                .column(18)
                .identifier(ident)
                .expression(expression)
                .build();
    }

    private AssignNode assignNode;

    @Test
    public void check__AssignNode__Line() {
        assertThat(assignNode.line(), is(47));
    }

    @Test
    public void check__AssignNode__Column() {
        assertThat(assignNode.column(), is(18));
    }

    @Test
    public void check__AssignNode__Identifier() {
        assertThat(assignNode.identifier(), is(ident));
    }

    @Test
    public void check__AssignNode__ListExp() {
        assertThat(assignNode.expression(), is(expression));
    }

    @Test
    public void check__AssignNode__DefaultBreakpoint() {
        assertThat(assignNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__AssignNode__Breakpoint() {
        assignNode = AssignNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(assignNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__AssignNode__ChildrenMethod__FirstChild() {
        assertThat(assignNode.children(0), is(ident));
    }

    @Test
    public void check__AssignNode__ChildrenMethod__SecondChild() {
        assertThat(assignNode.children(1), is(expression));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AssignNode__ChildrenMethod__OtherChild() {
        assignNode.children(3);
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