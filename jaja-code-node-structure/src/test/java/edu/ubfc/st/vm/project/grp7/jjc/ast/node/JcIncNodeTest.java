package edu.ubfc.st.vm.project.grp7.jjc.ast.node;

import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcIncNodeTest {
    @Before
    public void setup() {
        jcIncNode = JcIncNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .identifier("A")
                .build();
    }

    private JcIncNode jcIncNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcIncNode__Line() {
        assertThat(jcIncNode.line(), is(30));
    }

    @Test
    public void check__JcIncNode__Column() {
        assertThat(jcIncNode.column(), is(15));
    }

    @Test
    public void check__JcIncNode__Identifier() { assertThat(jcIncNode.identifier(), is("A"));
    }

    @Test
    public void check__JcIncNode__next() {
        assertThat(jcIncNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcIncNode__ChildrenMethod__FirstChild() {
        assertThat(jcIncNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__JcIncNode__ChildrenMethod__OtherChild() {
        jcIncNode.children(2);
    }
}
