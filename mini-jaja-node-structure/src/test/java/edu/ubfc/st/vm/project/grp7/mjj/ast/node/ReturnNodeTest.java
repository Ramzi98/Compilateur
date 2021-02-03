package edu.ubfc.st.vm.project.grp7.mjj.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReturnNodeTest {
    @Before
    public void setup() {
        returnNode = ReturnNode.builder()
                .line(6)
                .column(4)
                .ret(ret)
                .build();
    }

    private ReturnNode returnNode;

    @Test
    public void check__ReturnNode__Line() {
        assertThat(returnNode.line(), is(6));
    }

    @Test
    public void check__ReturnNode__Column() {
        assertThat(returnNode.column(), is(4));
    }

    @Test
    public void check__ReturnNode__Ret() {
        assertThat(returnNode.ret(), Matchers.is(ret));
    }

    @Test
    public void check__ReturnNode__DefaultBreakpoint() {
        assertThat(returnNode.breakpoint(), is(Breakpoint.NONE));
    }

    @Test
    public void check__ReturnNode__Breakpoint() {
        returnNode = ReturnNode.builder()
                .breakpoint(Breakpoint.STEP_BY_STEP)
                .build();

        assertThat(returnNode.breakpoint(), is(Breakpoint.STEP_BY_STEP));
    }

    @Test
    public void check__ReturnNode__ChildrenMethod__FirstChild() {
        assertThat(returnNode.children(0), Matchers.is(ret));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__ReturnNode__ChildrenMethod__OtherChild() {
        returnNode.children(1);
    }


    // ---------------------------------- Parameters Nodes ----------------------------------

    private static final MiniJajaNode ret = new IdentNode() {
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
}