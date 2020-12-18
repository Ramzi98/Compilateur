package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HeaderNodeTest {
    @Before
    public void setup() {
        headerNode = HeaderNode.builder()
                .line(6)
                .column(4)
                .identifier(ident)
                .type(type)
                .build();
    }

    private HeaderNode headerNode;

    @Test
    public void check__HeaderNode__Line() {
        assertThat(headerNode.line(), is(6));
    }

    @Test
    public void check__HeaderNode__Column() {
        assertThat(headerNode.column(), is(4));
    }

    @Test
    public void check__HeaderNode__Type() {
        assertThat(headerNode.type(), Matchers.is(type));
    }

    @Test
    public void check__HeaderNode__Identifier() {
        assertThat(headerNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__HeaderNode__DefaultBreakpoint() {
        assertThat(headerNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__HeaderNode__Breakpoint() {
        headerNode = HeaderNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(headerNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__HeaderNode__ChildrenMethod__FirstChild() {
        assertThat(headerNode.children(0), Matchers.is(type));
    }

    @Test
    public void check__HeaderNode__ChildrenMethod__SecondChild() {
        assertThat(headerNode.children(1), Matchers.is(ident));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__HeaderNode__ChildrenMethod__OtherChild() {
        headerNode.children(2);
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

    private static final TypeNode type = new TypeNode() {
        @Override
        public Type value() {
            return Type.INT;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public int line() {
            return 21;
        }

        @Override
        public int column() {
            return 4;
        }
    };
}