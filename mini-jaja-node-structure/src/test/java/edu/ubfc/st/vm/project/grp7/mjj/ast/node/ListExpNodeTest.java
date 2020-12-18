package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListExpNodeTest {
    @Before
    public void setup() {
        listExpNode = ListExpNode.builder()
                .line(16)
                .column(4)
                .expression(expression)
                .listexp(listExp)
                .build();
    }

    private ListExpNode listExpNode;

    @Test
    public void check__ListExpNode__Line() {
        assertThat(listExpNode.line(), is(16));
    }

    @Test
    public void check__ListExpNode__Column() {
        assertThat(listExpNode.column(), is(4));
    }

    @Test
    public void check__ListExpNode__Expression() {
        assertThat(listExpNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__ListExpNode__ListExp() {
        assertThat(listExpNode.listexp(), Matchers.is(listExp));
    }


    @Test
    public void check__ListExpNode__DefaultBreakpoint() {
        assertThat(listExpNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__ListExpNode__Breakpoint() {
        listExpNode = ListExpNode.builder()
                .breakpoint(Breakpoint.BREAKPOINT)
                .build();

        assertThat(listExpNode.breakpoint(), is(Breakpoint.BREAKPOINT));
    }

    @Test
    public void check__ListExpNode__ChildrenMethod__FirstChild() {
        assertThat(listExpNode.children(0), Matchers.is(expression));
    }

    @Test
    public void check__ListExpNode__ChildrenMethod__SecondChild() {
        assertThat(listExpNode.children(1), Matchers.is(listExp));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void check__ListExpNode__ChildrenMethod__OtherChild() {
        listExpNode.children(2);
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
            return Breakpoint.NONE;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 15;
        }

        @Override
        public int column() {
            return 2;
        }
    };
}