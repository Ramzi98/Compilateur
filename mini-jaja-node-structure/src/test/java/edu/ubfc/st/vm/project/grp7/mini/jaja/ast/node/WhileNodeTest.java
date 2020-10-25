package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WhileNodeTest {
    @Before
    public void setup() {
        whileNode = WhileNode.builder()
                .line(77)
                .column(0)
                .expression(expression)
                .instrs(instrs)
                .build();
    }

    private WhileNode whileNode;

    @Test
    public void check__WhileNode__Line() {
        assertThat(whileNode.line(), is(77));
    }

    @Test
    public void check__WhileNode__Column() {
        assertThat(whileNode.column(), is(0));
    }

    @Test
    public void check__WhileNode__Expression() {
        assertThat(whileNode.expression(), is(expression));
    }

    @Test
    public void check__WhileNode__TrueInstrs() {
        assertThat(whileNode.instrs(), is(instrs));
    }

    @Test
    public void check__WhileNode__DefaultBreakpoint() {
        assertThat(whileNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__WhileNode__Breakpoint() {
        whileNode = WhileNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(whileNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__WhileNode__ChildrenMethod__FirstChild() {
        assertThat(whileNode.children(0), is(expression));
    }

    @Test
    public void check__WhileNode__ChildrenMethod__SecondChild() {
        assertThat(whileNode.children(1), is(instrs));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__WhileNode__ChildrenMethod__OtherChild() {
        whileNode.children(2);
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

    private static final InstrsNode instrs = new InstrsNode() {
        @Override
        public MiniJajaNode instruction() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.STEP_BY_STEP;
        }

        @Override
        public int line() {
            return 195;
        }

        @Override
        public int column() {
            return 66;
        }
    };
}