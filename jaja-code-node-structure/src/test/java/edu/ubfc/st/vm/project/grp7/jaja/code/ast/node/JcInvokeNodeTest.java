package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcInvokeNodeTest {
    @Before
    public void setup() {
        jcInvokeNode = JcInvokeNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("max")
                .build();

    }

    private JcInvokeNode jcInvokeNode;
    private JcReturnNode jajaCodeNode;


    @Test
    public void check__JcInvokeNode__Line() {
        assertThat(jcInvokeNode.line(), is(30));
    }

    @Test
    public void check__JcInvokeNode__Column() {
        assertThat(jcInvokeNode.column(), is(15));
    }

    @Test
    public void check__JcInvokeNode__next() {
        assertThat(jcInvokeNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcInvokeNode__identifier() {
        assertThat(jcInvokeNode.identifier(), is("max"));
    }

    @Test
    public void check__JcInvokeNode__ChildrenMethod__FirstChild() {
        assertThat(jcInvokeNode.children(0), is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcInvokeNode__ChildrenMethod__OtherChild() {
        jcInvokeNode.children(2);
    }
}
