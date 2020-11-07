package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcOrNodeTest {

    @Before
    public void setup() {
        jcOrNode = JcOrNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcOrNode jcOrNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcOrNode__Line() {
        assertThat(jcOrNode.line(), is(30));
    }

    @Test
    public void check__JcOrNode__Column() {
        assertThat(jcOrNode.column(), is(15));
    }

    @Test
    public void check__JcOrNode__next() {
        assertThat(jcOrNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcOrNode__ChildrenMethod__FirstChild() {
        assertThat(jcOrNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcOrNode__ChildrenMethod__OtherChild() {
        jcOrNode.children(2);
    }
}
