package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcGotoNodeTest {
    @Before
    public void setup() {
        jcGotoNode = JcGotoNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .adresse(100)
                .build();

        jcGotoNode.setAdresse(20);
    }

    private JcGotoNode jcGotoNode;
    private JcPopNode jajaCodeNode;

    @Test
    public void check__JcGotoNode__Line() {
        assertThat(jcGotoNode.line(), is(30));
    }

    @Test
    public void check__JcGotoNode__Column() {
        assertThat(jcGotoNode.column(), is(15));
    }

    @Test
    public void check__JcGotoNode__next() {
        assertThat(jcGotoNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcGotoNode__adresse() {
        assertThat(jcGotoNode.adresse(), is(20));
    }

    @Test
    public void check__setGotoNodeJump__adresse() {
        assertThat(jcGotoNode.adresse(),is(20) );
    }

    @Test
    public void check__JcGotoNode__ChildrenMethod__FirstChild() {
        assertThat(jcGotoNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcGotoNode.children(2);
    }
}
