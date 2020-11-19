package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcMulNodeTest {

    @Before
    public void setup() {
        jcMulNode = JcMulNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcMulNode jcMulNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcMulNode__Line() {
        assertThat(jcMulNode.line(), is(30));
    }

    @Test
    public void check__JcMulNode__Column() {
        assertThat(jcMulNode.column(), is(15));
    }

    @Test
    public void check__JcMulNode__next() {
        assertThat(jcMulNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcMulNode__ChildrenMethod__FirstChild() {
        assertThat(jcMulNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcMulNode__ChildrenMethod__OtherChild() {
        jcMulNode.children(2);
    }
}
