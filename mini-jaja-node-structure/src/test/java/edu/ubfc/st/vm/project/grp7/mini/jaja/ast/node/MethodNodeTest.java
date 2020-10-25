package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MethodNodeTest {
    @Before
    public void setup() {
        methodNode = MethodNode.builder()
                .line(42)
                .column(7)
                .typeMeth(typeMeth)
                .identifier(ident)
                .headers(headers)
                .vars(vars)
                .instrs(instrs)
                .build();
    }

    private MethodNode methodNode;

    @Test
    public void check__MethodNode__Line() {
        assertThat(methodNode.line(), is(42));
    }

    @Test
    public void check__MethodNode__Column() {
        assertThat(methodNode.column(), is(7));
    }

    @Test
    public void check__MethodNode__TypeMeth() {
        assertThat(methodNode.typeMeth(), is(typeMeth));
    }

    @Test
    public void check__MethodNode__Identifier() {
        assertThat(methodNode.identifier(), is(ident));
    }

    @Test
    public void check__MethodNode__Headers() {
        assertThat(methodNode.headers(), is(headers));
    }

    @Test
    public void check__MethodNode__Vars() {
        assertThat(methodNode.vars(), is(vars));
    }

    @Test
    public void check__MethodNode__Instrs() {
        assertThat(methodNode.instrs(), is(instrs));
    }

    @Test
    public void check__MethodNode__DefaultBreakpoint() {
        assertThat(methodNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__MethodNode__Breakpoint() {
        methodNode = MethodNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(methodNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__MethodNode__ChildrenMethod__FirstChild() {
        assertThat(methodNode.children(0), is(typeMeth));
    }

    @Test
    public void check__MethodNode__ChildrenMethod__SecondChild() {
        assertThat(methodNode.children(1), is(ident));
    }

    @Test
    public void check__MethodNode__ChildrenMethod__ThirdChild() {
        assertThat(methodNode.children(2), is(headers));
    }

    @Test
    public void check__MethodNode__ChildrenMethod__FourthChild() {
        assertThat(methodNode.children(3), is(vars));
    }

    @Test
    public void check__MethodNode__ChildrenMethod__FifthChild() {
        assertThat(methodNode.children(4), is(instrs));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__MethodNode__ChildrenMethod__OtherChild() {
        methodNode.children(5);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------


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

    private static final VarsNode vars = new VarsNode() {
        @Override
        public VarNode var() {
            return null;
        }

        @Override
        public VarsNode vars() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return Breakpoint.STEP_BY_STEP;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 50;
        }

        @Override
        public int column() {
            return 7;
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
            return Breakpoint.STEP_BY_STEP;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 51;
        }

        @Override
        public int column() {
            return 0;
        }
    };
}