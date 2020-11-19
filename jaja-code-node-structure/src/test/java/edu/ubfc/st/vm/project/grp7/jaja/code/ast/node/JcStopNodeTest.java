package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcStopNodeTest {
    @Before
    public void setup() {
        jcStopNode = JcStopNode.builder()
                .line(30)
                .column(15)
                .build();
    }

    private JcStopNode jcStopNode;
    private JcReturnNode jajaCodeNode;

    @Test
    public void check__JcStopNode__Line() {
        assertThat(jcStopNode.line(), is(30));
    }

    @Test
    public void check__JcStopNode__Column() {
        assertThat(jcStopNode.column(), is(15));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__Child() {
        jcStopNode.children(2);
    }
}
