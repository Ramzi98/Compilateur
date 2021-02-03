package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IfNodeTest {
    @Before
    public void setup() {
        ifNode = IfNode.builder()
                .line(89)
                .column(13)
                .expression(expression)
                .trueInstrs(trueInstrs)
                .falseInstrs(falseInstrs)
                .build();
    }

    private IfNode ifNode;

    @Test
    public void check__IfNode__Line() {
        assertThat(ifNode.line(), is(89));
    }

    @Test
    public void check__IfNode__Column() {
        assertThat(ifNode.column(), is(13));
    }

    @Test
    public void check__IfNode__Expression() {
        assertThat(ifNode.expression(), is(expression));
    }

    @Test
    public void check__IfNode__TrueInstrs() {
        assertThat(ifNode.trueInstrs(), Matchers.is(trueInstrs));
    }

    @Test
    public void check__IfNode__FalseInstrs() {
        assertThat(ifNode.falseInstrs(), Matchers.is(falseInstrs));
    }

    @Test
    public void check__IfNode__DefaultBreakpoint() {
        assertThat(ifNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__IfNode__Breakpoint() {
        ifNode = IfNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(ifNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__IfNode__ChildrenMethod__FirstChild() {
        assertThat(ifNode.children(0), is(expression));
    }

    @Test
    public void check__IfNode__ChildrenMethod__SecondChild() {
        assertThat(ifNode.children(1), Matchers.is(trueInstrs));
    }

    @Test
    public void check__IfNode__ChildrenMethod__ThirdChild() {
        assertThat(ifNode.children(2), Matchers.is(falseInstrs));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__IfNode__ChildrenMethod__OtherChild() {
        ifNode.children(3);
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

    private static final InstrsNode trueInstrs = new InstrsNode() {
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
    
    private static final InstrsNode falseInstrs = new InstrsNode() {
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
            return null;
        }

        @Override
        public int line() {
            return 20;
        }

        @Override
        public int column() {
            return 2;
        }
    };
}