package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeclsNodeTest {
    @Before
    public void setup() {
        declsNode = DeclsNode.builder()
                .line(6)
                .column(4)
                .decl(decl)
                .decls(decls)
                .build();
    }

    private DeclsNode declsNode;

    @Test
    public void check__DeclsNode__Line() {
        assertThat(declsNode.line(), is(6));
    }

    @Test
    public void check__DeclsNode__Column() {
        assertThat(declsNode.column(), is(4));
    }

    @Test
    public void check__DeclsNode__ListExp() {
        assertThat(declsNode.decl(), is(decl));
    }

    @Test
    public void check__DeclsNode__Identifier() {
        assertThat(declsNode.decls(), is(decls));
    }

    @Test
    public void check__DeclsNode__DefaultBreakpoint() {
        assertThat(declsNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__DeclsNode__Breakpoint() {
        declsNode = DeclsNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(declsNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__DeclsNode__ChildrenMethod__FirstChild() {
        assertThat(declsNode.children(0), is(decl));
    }

    @Test
    public void check__DeclsNode__ChildrenMethod__SecondChild() {
        assertThat(declsNode.children(1), is(decls));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__DeclsNode__ChildrenMethod__OtherChild() {
        declsNode.children(2);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------


    private static final MiniJajaNode decl = new MiniJajaNode() {
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

    private static final DeclsNode decls = new DeclsNode() {
        @Override
        public MiniJajaNode decl() {
            return null;
        }

        @Override
        public DeclsNode decls() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
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
}
