package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcDivNodeTest {

    @Before
    public void setup() {
        jcDivNode = JcDivNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcDivNode jcDivNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcDivNode__Line() {
        assertThat(jcDivNode.line(), is(30));
    }

    @Test
    public void check__JcDivNode__Column() {
        assertThat(jcDivNode.column(), is(15));
    }

    @Test
    public void check__JcDivNode__next() {
        assertThat(jcDivNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcDivNode__ChildrenMethod__FirstChild() {
        assertThat(jcDivNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcDivNode__ChildrenMethod__OtherChild() {
        jcDivNode.children(2);
    }
}
