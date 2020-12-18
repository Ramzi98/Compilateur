package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VarNodeTest {
    @Before
    public void setup() {
        varNode = VarNode.builder()
                .line(6)
                .column(4)
                .identifier(ident)
                .expression(expression)
                .typeMeth(typeMeth)
                .build();
    }

    private VarNode varNode;

    @Test
    public void check__VarNode__Line() {
        assertThat(varNode.line(), is(6));
    }

    @Test
    public void check__VarNode__Column() {
        assertThat(varNode.column(), is(4));
    }

    @Test
    public void check__VarNode__Identifier() {
        assertThat(varNode.identifier(), Matchers.is(ident));
    }

    @Test
    public void check__VarNode__Expression() {
        assertThat(varNode.expression(), Matchers.is(expression));
    }

    @Test
    public void check__VarNode__TypeMeth() {
        assertThat(varNode.typeMeth(), Matchers.is(typeMeth));
    }

    @Test
    public void check__VarNode__DefaultBreakpoint() {
        assertThat(varNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__VarNode__Breakpoint() {
        varNode = VarNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(varNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__VarNode__ChildrenMethod__FirstChild() {
        assertThat(varNode.children(0), Matchers.is(typeMeth));
    }

    @Test
    public void check__VarNode__ChildrenMethod__SecondChild() {
        assertThat(varNode.children(1), Matchers.is(ident));
    }

    @Test
    public void check__VarNode__ChildrenMethod__ThirdChild() {
        assertThat(varNode.children(2), Matchers.is(expression));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__VarNode__ChildrenMethod__OtherChild() {
        varNode.children(3);
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
            return 21;
        }

        @Override
        public int column() {
            return 4;
        }
    };
}