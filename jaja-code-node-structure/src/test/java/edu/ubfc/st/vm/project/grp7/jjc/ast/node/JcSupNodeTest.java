package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcSupNodeTest {


    @Before
    public void setup() {
        jcSupNode = JcSupNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcSupNode jcSupNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcSupNode__Line() {
        assertThat(jcSupNode.line(), is(30));
    }

    @Test
    public void check__JcSupNode__Column() {
        assertThat(jcSupNode.column(), is(15));
    }

    @Test
    public void check__JcSupNode__next() {
        assertThat(jcSupNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcSupNode__ChildrenMethod__FirstChild() {
        assertThat(jcSupNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcSupNode__ChildrenMethod__OtherChild() {
        jcSupNode.children(2);
    }
}
