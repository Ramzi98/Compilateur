package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MainNodeTest {
    @Before
    public void setup() {
        mainNode = MainNode.builder()
                .line(96)
                .column(4)
                .vars(vars)
                .instrs(instrs)
                .build();
    }

    private MainNode mainNode;

    @Test
    public void check__MainNode__Line() {
        assertThat(mainNode.line(), is(96));
    }

    @Test
    public void check__MainNode__Column() {
        assertThat(mainNode.column(), is(4));
    }

    @Test
    public void check__MainNode__Expression() {
        assertThat(mainNode.vars(), is(vars));
    }

    @Test
    public void check__MainNode__TypeMeth() {
        assertThat(mainNode.instrs(), is(instrs));
    }

    @Test
    public void check__MainNode__DefaultBreakpoint() {
        assertThat(mainNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__MainNode__Breakpoint() {
        mainNode = MainNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(mainNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__MainNode__ChildrenMethod__FirstChild() {
        assertThat(mainNode.children(0), is(vars));
    }

    @Test
    public void check__MainNode__ChildrenMethod__SecondChild() {
        assertThat(mainNode.children(1), is(instrs));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__MainNode__ChildrenMethod__OtherChild() {
        mainNode.children(2);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    
    private static final VarsNode vars = new VarsNode() {
        @Override
        public VarNode var() {
            return null;
        }

        @Override
        public VarsNode vars() {
            return null;
        }

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
            return 47;
        }

        @Override
        public int column() {
            return 8;
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
        public Breakpoint breakpoint() {
            return Breakpoint.STEP_BY_STEP;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 48;
        }

        @Override
        public int column() {
            return 2;
        }
    };
}