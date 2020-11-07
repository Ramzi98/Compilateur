package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcSubNodeTest {

    @Before
    public void setup() {
        jcSubNode = JcSubNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcSubNode jcSubNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcSubNode__Line() {
        assertThat(jcSubNode.line(), is(30));
    }

    @Test
    public void check__JcSubNode__Column() {
        assertThat(jcSubNode.column(), is(15));
    }

    @Test
    public void check__JcSubNode__next() {
        assertThat(jcSubNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcSubNode__ChildrenMethod__FirstChild() {
        assertThat(jcSubNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcSubNode__ChildrenMethod__OtherChild() {
        jcSubNode.children(2);
    }
}
