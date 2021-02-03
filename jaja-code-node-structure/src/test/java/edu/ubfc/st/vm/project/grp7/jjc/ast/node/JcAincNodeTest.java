package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcAincNodeTest {
    @Before
    public void setup() {
        jcAincNode = JcAincNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("A")
                .build();
    }

    private JcAincNode jcAincNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcAincNode__Line() {
        assertThat(jcAincNode.line(), is(30));
    }

    @Test
    public void check__JcAincNode__Column() {
        assertThat(jcAincNode.column(), is(15));
    }

    @Test
    public void check__JcAincNode__Identifier() { assertThat(jcAincNode.identifier(), is("A"));
    }

    @Test
    public void check__JcAincNode__next() {
        assertThat(jcAincNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcAincNode__ChildrenMethod__FirstChild() {
        assertThat(jcAincNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcAincNode__ChildrenMethod__OtherChild() {
        jcAincNode.children(2);
    }
}
