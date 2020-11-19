package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcInitNodeTest {
    @Before
    public void setup() {
        jcInitNode = JcInitNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcInitNode jcInitNode;
    private JcReturnNode jajaCodeNode;

    @Test
    public void check__JcInitNode__Line() {
        assertThat(jcInitNode.line(), is(30));
    }

    @Test
    public void check__JcInitNode__Column() {
        assertThat(jcInitNode.column(), is(15));
    }

    @Test
    public void check__JcInitNode__next() {
        assertThat(jcInitNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcInitNode__ChildrenMethod__FirstChild() {
        assertThat(jcInitNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcInitNode.children(2);
    }

}
