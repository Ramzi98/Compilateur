package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcWriteLnNodeTest {

    @Before
    public void setup() {
        jcWritelnNode = JcWriteLnNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcWriteLnNode jcWritelnNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcWritelnNode__Line() {
        assertThat(jcWritelnNode.line(), is(30));
    }

    @Test
    public void check__JcWritelnNode__Column() {
        assertThat(jcWritelnNode.column(), is(15));
    }

    @Test
    public void check__JcWritelnNode__next() {
        assertThat(jcWritelnNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcWritelnNode__ChildrenMethod__FirstChild() {
        assertThat(jcWritelnNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcWritelnNode__ChildrenMethod__OtherChild() {
        jcWritelnNode.children(2);
    }
}
