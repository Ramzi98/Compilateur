package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InstrsNodeTest {
    @Before
    public void setup() {
        instrsNode = InstrsNode.builder()
                .line(6)
                .column(4)
                .instruction(instruction)
                .instrs(instrs)
                .build();
    }

    private InstrsNode instrsNode;

    @Test
    public void check__InstrsNode__Line() {
        assertThat(instrsNode.line(), is(6));
    }

    @Test
    public void check__InstrsNode__Column() {
        assertThat(instrsNode.column(), is(4));
    }

    @Test
    public void check__InstrsNode__Expression() {
        assertThat(instrsNode.instruction(), is(instruction));
    }

    @Test
    public void check__InstrsNode__TypeMeth() {
        assertThat(instrsNode.instrs(), is(instrs));
    }

    @Test
    public void check__InstrsNode__DefaultBreakpoint() {
        assertThat(instrsNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__InstrsNode__Breakpoint() {
        instrsNode = InstrsNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(instrsNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__InstrsNode__ChildrenMethod__FirstChild() {
        assertThat(instrsNode.children(0), is(instruction));
    }

    @Test
    public void check__InstrsNode__ChildrenMethod__SecondChild() {
        assertThat(instrsNode.children(1), is(instrs));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__InstrsNode__ChildrenMethod__OtherChild() {
        instrsNode.children(2);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final MiniJajaNode instruction = new MiniJajaNode() {
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
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 13;
        }

        @Override
        public int column() {
            return 10;
        }
    };
}