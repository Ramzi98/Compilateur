package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayNodeTest {
    @Before
    public void setup() {
        arrayNode = ArrayNode.builder()
                .line(47)
                .column(18)
                .identifier(ident)
                .expression(expression)
                .typeMeth(typeMeth)
                .build();
    }

    private ArrayNode arrayNode;

    @Test
    public void check__ArrayNode__Line() {
        assertThat(arrayNode.line(), is(47));
    }

    @Test
    public void check__ArrayNode__Column() {
        assertThat(arrayNode.column(), is(18));
    }

    @Test
    public void check__ArrayNode__Identifier() {
        assertThat(arrayNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__ArrayNode__ListExp() {
        assertThat(arrayNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__ArrayNode__TypeMeth() {
        assertThat(arrayNode.typeMeth(), Matchers.is(typeMeth));
    }

    @Test
    public void check__ArrayNode__DefaultBreakpoint() {
        assertThat(arrayNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__ArrayNode__Breakpoint() {
        arrayNode = ArrayNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(arrayNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__ArrayNode__ChildrenMethod__FirstChild() {
        assertThat(arrayNode.children(0), Matchers.is(typeMeth));
    }

    @Test
    public void check__ArrayNode__ChildrenMethod__SecondChild() {
        assertThat(arrayNode.children(1), Matchers.is(ident));
    }

    @Test
    public void check__ArrayNode__ChildrenMethod__ThirdChild() {
        assertThat(arrayNode.children(2), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__ArrayNode__ChildrenMethod__OtherChild() {
        arrayNode.children(3);
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

    private static final TypeMethNode typeMeth = new TypeMethNode() {
        @Override
        public TypeMeth value() {
            return TypeMeth.INT;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public int line() {
            return 12;
        }

        @Override
        public int column() {
            return 35;
        }
    };
}