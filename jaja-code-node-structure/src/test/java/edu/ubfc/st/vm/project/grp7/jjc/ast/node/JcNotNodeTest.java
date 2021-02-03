package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcNotNodeTest {

    @Before
    public void setup() {
        jcNotNode = JcNotNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcNotNode jcNotNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcNotNode__Line() {
        MatcherAssert.assertThat(jcNotNode.line(), is(30));
    }

    @Test
    public void check__JcNotNode__Column() {
        MatcherAssert.assertThat(jcNotNode.column(), is(15));
    }

    @Test
    public void check__JcNotNode__next() {
        assertThat(jcNotNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcNotNode__ChildrenMethod__FirstChild() {
        MatcherAssert.assertThat(jcNotNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcNotNode__ChildrenMethod__OtherChild() {
        jcNotNode.children(2);
    }
}
