package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcPushNodeTest {
    @Before
    public void setup() {
        jcPushNode = JcPushNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .valeur(JcNumberNode.builder().value(2.5).build())
                .build();

    }

    private JcPushNode jcPushNode;
    private JcIfNode jajaCodeNode;


    @Test
    public void check__JcPushNode__Line() {
        assertThat(jcPushNode.line(), is(30));
    }

    @Test
    public void check__JcPushNode__Column() {
        assertThat(jcPushNode.column(), is(15));
    }

    @Test
    public void check__JcPushNode__next() {
        assertThat(jcPushNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcPushNode__valeur() {
        assertThat(jcPushNode.valeur(), is(JcNumberNode.builder().value(2.5).build()));
    }

    @Test
    public void check__JcPushNode__ChildrenMethod__FirstChild() {
        assertThat(jcPushNode.children(0), is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcPushNode__ChildrenMethod__OtherChild() {
        jcPushNode.children(2);
    }
}
