package edu.ubfc.st.vm.project.grp7.jaja.code.ast.node;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JcAddNodeTest {

    @Before
    public void setup() {
        jcAddNode = JcAddNode.builder()
                .line(30)
                .column(15)
                .next(jajaCodeNode)
                .build();
    }

    private JcAddNode jcAddNode;
    private JajaCodeNode jajaCodeNode;

    @Test
    public void check__JcAddNode__Line() {
        assertThat(jcAddNode.line(), is(30));
    }

    @Test
    public void check__JcAddNode__Column() {
        assertThat(jcAddNode.column(), is(15));
    }

    @Test
    public void check__JcAddNode__next() {
        assertThat(jcAddNode.next(), is(jajaCodeNode));
    }

    @Test
    public void check__JcAddNode__ChildrenMethod__FirstChild() {
        assertThat(jcAddNode.children(0) , is(jajaCodeNode));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void check__AndNode__ChildrenMethod__OtherChild() {
        jcAddNode.children(2);
    }
}
