package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayItemNodeTest {
    @Before
    public void setup() {
        arrayItemNode = ArrayItemNode.builder()
                .line(30)
                .column(7)
                .identifier(ident)
                .expression(expression)
                .build();
    }

    private ArrayItemNode arrayItemNode;

    @Test
    public void check__ArrayItemNode__Line() {
        assertThat(arrayItemNode.line(), is(30));
    }

    @Test
    public void check__ArrayItemNode__Column() {
        assertThat(arrayItemNode.column(), is(7));
    }

    @Test
    public void check__ArrayItemNode__Identifier() {
        assertThat(arrayItemNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__ArrayItemNode__ListExp() {
        assertThat(arrayItemNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__ArrayItemNode__DefaultBreakpoint() {
        assertThat(arrayItemNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__ArrayItemNode__Breakpoint() {
        arrayItemNode = ArrayItemNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(arrayItemNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__ArrayItemNode__ChildrenMethod__FirstChild() {
        assertThat(arrayItemNode.children(0), Matchers.is(ident));
    }

    @Test
    public void check__ArrayItemNode__ChildrenMethod__SecondChild() {
        assertThat(arrayItemNode.children(1), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__ArrayItemNode__ChildrenMethod__OtherChild() {
        arrayItemNode.children(2);
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
}