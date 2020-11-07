package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcWriteNodeTest {
    @Before
    public void setup() {
        jcWriteNode = JcWriteNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcWriteNode jcWriteNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcWriteNode__Line() {
        assertThat(jcWriteNode.line(), is(30));
    }

    @Test
    public void check__JcWriteNode__Column() {
        assertThat(jcWriteNode.column(), is(15));
    }

    @Test
    public void check__JcWriteNode__next() {
        assertThat(jcWriteNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcWriteNode__ChildrenMethod__FirstChild() {
        assertThat(jcWriteNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcWriteNode.children(2);
    }
}
