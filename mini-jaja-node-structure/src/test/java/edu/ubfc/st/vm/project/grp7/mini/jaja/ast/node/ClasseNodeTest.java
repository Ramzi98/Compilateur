package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClasseNodeTest {
    @Before
    public void setup() {
        classeNode = ClasseNode.builder()
                .line(0)
                .column(0)
                .identifier(ident)
                .decls(decls)
                .methmain(methMain)
                .build();
    }

    private ClasseNode classeNode;

    @Test
    public void check__ClasseNode__Line() {
        assertThat(classeNode.line(), is(0));
    }

    @Test
    public void check__ClasseNode__Column() {
        assertThat(classeNode.column(), is(0));
    }

    @Test
    public void check__ClasseNode__Identifier() {
        assertThat(classeNode.identifier(), is(ident));
    }

    @Test
    public void check__ClasseNode__Decls() {
        assertThat(classeNode.decls(), is(decls));
    }

    @Test
    public void check__ClasseNode__Methmain() {
        assertThat(classeNode.methmain(), is(methMain));
    }

    @Test
    public void check__ClasseNode__DefaultBreakpoint() {
        assertThat(classeNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__ClasseNode__Breakpoint() {
        classeNode = ClasseNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(classeNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__ClasseNode__ChildrenMethod__FirstChild() {
        assertThat(classeNode.children(0), is(ident));
    }

    @Test
    public void check__ClasseNode__ChildrenMethod__SecondChild() {
        assertThat(classeNode.children(1), is(decls));
    }

    @Test
    public void check__ClasseNode__ChildrenMethod__ThirdChild() {
        assertThat(classeNode.children(2), is(methMain));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__ClasseNode__ChildrenMethod__OtherChild() {
        classeNode.children(3);
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
            return 0;
        }

        @Override
        public int column() {
            return 6;
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

    private static final MainNode methMain = new MainNode() {
        @Override
        public VarsNode vars() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
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
            return 12;
        }

        @Override
        public int column() {
            return 35;
        }
    };

}