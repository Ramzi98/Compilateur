package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcNopNodeTest {

    @Before
    public void setup() {
        jcNopNode = JcNopNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcNopNode jcNopNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcNopNode__Line() {
        assertThat(jcNopNode.line(), is(30));
    }

    @Test
    public void check__JcNopNode__Column() {
        assertThat(jcNopNode.column(), is(15));
    }

    @Test
    public void check__JcNopNode__next() {
        assertThat(jcNopNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcNopNode__ChildrenMethod__FirstChild() {
        assertThat(jcNopNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcNopNode__ChildrenMethod__OtherChild() {
        jcNopNode.children(2);
    }
}
