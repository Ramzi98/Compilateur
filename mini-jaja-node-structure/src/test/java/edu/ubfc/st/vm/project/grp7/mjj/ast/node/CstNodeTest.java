package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CstNodeTest {
    @Before
    public void setup() {
        cstNode = CstNode.builder()
                .line(26)
                .column(14)
                .type(type)
                .identifier(ident)
                .expression(expression)
                .build();
    }

    private CstNode cstNode;

    @Test
    public void check__CstNode__Line() {
        assertThat(cstNode.line(), is(26));
    }

    @Test
    public void check__CstNode__Column() {
        assertThat(cstNode.column(), is(14));
    }

    @Test
    public void check__CstNode__Identifier() {
        assertThat(cstNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__CstNode__ListExp() {
        assertThat(cstNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__CstNode__Type() {
        assertThat(cstNode.type(), Matchers.is(type));
    }

    @Test
    public void check__CstNode__DefaultBreakpoint() {
        assertThat(cstNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__CstNode__Breakpoint() {
        cstNode = CstNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(cstNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__CstNode__ChildrenMethod__FirstChild() {
        assertThat(cstNode.children(0), Matchers.is(type));
    }

    @Test
    public void check__CstNode__ChildrenMethod__SecondChild() {
        assertThat(cstNode.children(1), Matchers.is(ident));
    }

    @Test
    public void check__CstNode__ChildrenMethod__ThirdChild() {
        assertThat(cstNode.children(2), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__CstNode__ChildrenMethod__OtherChild() {
        cstNode.children(3);
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