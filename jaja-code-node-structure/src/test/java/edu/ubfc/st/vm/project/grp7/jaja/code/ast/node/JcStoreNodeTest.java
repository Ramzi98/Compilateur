package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcStoreNodeTest {
    @Before
    public void setup() {
        jcStoreNode = JcStoreNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("ident")
                .build();
    }

    private JcStoreNode jcStoreNode;
    private JcIfNode jajaCodeNode;

    @Test
    public void check__JcStoreNode__Line() {
        assertThat(jcStoreNode.line(), is(30));
    }

    @Test
    public void check__JcStoreNode__Column() {
        assertThat(jcStoreNode.column(), is(15));
    }

    @Test
    public void check__JcStoreNode__next() {
        assertThat(jcStoreNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcStoreNode__identifier() {
        assertThat(jcStoreNode.identifier(), is("ident"));
    }

    @Test
    public void check__JcStoreNode__ChildrenMethod__FirstChild() {
        assertThat(jcStoreNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcStoreNode.children(2);
    }
}
