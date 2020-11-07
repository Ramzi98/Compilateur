package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcCmpNodeTest {

    @Before
    public void setup() {
        jcCmpNode = JcCmpNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcCmpNode jcCmpNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcCmpNode__Line() {
        assertThat(jcCmpNode.line(), is(30));
    }

    @Test
    public void check__JcCmpNode__Column() {
        assertThat(jcCmpNode.column(), is(15));
    }

    @Test
    public void check__JcCmpNode__next() {
        assertThat(jcCmpNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcCmpNode__ChildrenMethod__FirstChild() {
        assertThat(jcCmpNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcCmpNode__ChildrenMethod__OtherChild() {
        jcCmpNode.children(2);
    }
}
