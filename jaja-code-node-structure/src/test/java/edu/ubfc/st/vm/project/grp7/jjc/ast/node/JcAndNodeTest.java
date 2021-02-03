package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcAndNodeTest {

    @Before
    public void setup() {
        jcAndNode = JcAndNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcAndNode jcAndNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcAndNode__Line() {
        assertThat(jcAndNode.line(), is(30));
    }

    @Test
    public void check__JcAndNode__Column() {
        assertThat(jcAndNode.column(), is(15));
    }

    @Test
    public void check__JcAndNode__next() {
        assertThat(jcAndNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcAndNode__ChildrenMethod__FirstChild() {
        assertThat(jcAndNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcAndNode__ChildrenMethod__OtherChild() {
        jcAndNode.children(2);
    }
}
