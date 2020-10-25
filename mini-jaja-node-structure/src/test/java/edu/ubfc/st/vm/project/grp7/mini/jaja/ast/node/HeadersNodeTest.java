package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HeadersNodeTest {
    @Before
    public void setup() {
        headersNode = HeadersNode.builder()
                .line(6)
                .column(4)
                .header(header)
                .headers(headers)
                .build();
    }

    private HeadersNode headersNode;

    @Test
    public void check__HeadersNode__Line() {
        assertThat(headersNode.line(), is(6));
    }

    @Test
    public void check__HeadersNode__Column() {
        assertThat(headersNode.column(), is(4));
    }

    @Test
    public void check__HeadersNode__Identifier() {
        assertThat(headersNode.header(), is(header));
    }

    @Test
    public void check__HeadersNode__ListExp() {
        assertThat(headersNode.headers(), is(headers));
    }

    @Test
    public void check__HeadersNode__DefaultBreakpoint() {
        assertThat(headersNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__HeadersNode__Breakpoint() {
        headersNode = HeadersNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(headersNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__HeadersNode__ChildrenMethod__FirstChild() {
        assertThat(headersNode.children(0), is(header));
    }

    @Test
    public void check__HeadersNode__ChildrenMethod__SecondChild() {
        assertThat(headersNode.children(1), is(headers));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void check__HeadersNode__ChildrenMethod__OtherChild() {
        headersNode.children(3);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final HeaderNode header = new HeaderNode() {
        @Override
        public TypeNode type() {
            return null;
        }

        @Override
        public IdentNode identifier() {
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
            return 95;
        }

        @Override
        public int column() {
            return 26;
        }
    };

    private static final HeadersNode headers = new HeadersNode() {
        @Override
        public HeaderNode header() {
            return null;
        }

        @Override
        public HeadersNode headers() {
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