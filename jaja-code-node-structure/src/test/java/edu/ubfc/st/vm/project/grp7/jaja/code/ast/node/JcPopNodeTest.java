package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcPopNodeTest {
    @Before
    public void setup() {
        jcPopNode = JcPopNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcPopNode jcPopNode;
    private JcGotoNode jajaCodeNode;

    @Test
    public void check__JcPopNode__Line() {
        assertThat(jcPopNode.line(), is(30));
    }

    @Test
    public void check__JcPopNode__Column() {
        assertThat(jcPopNode.column(), is(15));
    }

    @Test
    public void check__JcPopNode__next() {
        assertThat(jcPopNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcPopNode__ChildrenMethod__FirstChild() {
        assertThat(jcPopNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcPopNode.children(2);
    }
}
