package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AppelINodeTest {
    @Before
    public void setup() {
        appelINode = AppelINode.builder()
                .line(72)
                .column(67)
                .identifier(ident)
                .listexp(listExp)
                .build();
    }

    private AppelINode appelINode;

    @Test
    public void check__AppelINode__Line() {
        assertThat(appelINode.line(), is(72));
    }

    @Test
    public void check__AppelINode__Column() {
        assertThat(appelINode.column(), is(67));
    }

    @Test
    public void check__AppelINode__Identifier() {
        assertThat(appelINode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__AppelINode__ListExp() {
        assertThat(appelINode.listexp(), Matchers.is(listExp));
    }

    @Test
    public void check__AppelINode__DefaultBreakpoint() {
        assertThat(appelINode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__AppelINode__Breakpoint() {
        appelINode = AppelINode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(appelINode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__AppelINode__ChildrenMethod__FirstChild() {
        assertThat(appelINode.children(0), Matchers.is(ident));
    }

    @Test
    public void check__AppelINode__ChildrenMethod__SecondChild() {
        assertThat(appelINode.children(1), Matchers.is(listExp));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AppelINode__ChildrenMethod__OtherChild() {
        appelINode.children(2);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final IdentNode ident = new IdentNode() {
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

    private static final ListExpNode listExp = new ListExpNode() {
        @Override
        public MiniJajaNode expression() {
            return null;
        }

        @Override
        public ListExpNode listexp() {
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