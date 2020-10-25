package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VarsNodeTest {
    @Before
    public void setup() {
        varsNode = VarsNode.builder()
                .line(47)
                .column(18)
                .var(var)
                .vars(vars)
                .build();
    }

    private VarsNode varsNode;

    @Test
    public void check__VarsNode__Line() {
        assertThat(varsNode.line(), is(47));
    }

    @Test
    public void check__VarsNode__Column() {
        assertThat(varsNode.column(), is(18));
    }

    @Test
    public void check__VarsNode__Var() {
        assertThat(varsNode.var(), is(var));
    }

    @Test
    public void check__VarsNode__Vars() {
        assertThat(varsNode.vars(), is(vars));
    }

    @Test
    public void check__VarsNode__DefaultBreakpoint() {
        assertThat(varsNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__VarsNode__Breakpoint() {
        varsNode = VarsNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(varsNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__VarsNode__ChildrenMethod__FirstChild() {
        assertThat(varsNode.children(0), is(var));
    }

    @Test
    public void check__VarsNode__ChildrenMethod__SecondChild() {
        assertThat(varsNode.children(1), is(vars));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void check__VarsNode__ChildrenMethod__OtherChild() {
        varsNode.children(3);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final VarNode var = new VarNode() {
        @Override
        public TypeMethNode typeMeth() {
            return null;
        }

        @Override
        public IdentNode identifier() {
            return null;
        }

        @Override
        public MiniJajaNode expression() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
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
            return 12;
        }

        @Override
        public int column() {
            return 34;
        }
    };
}