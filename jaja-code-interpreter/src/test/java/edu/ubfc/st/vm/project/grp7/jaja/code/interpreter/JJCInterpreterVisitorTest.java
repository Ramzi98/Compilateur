package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcAddNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcInitNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.Quadruplet;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class JJCInterpreterVisitorTest {
    private JJCInterpreterVisitor jjcVisitor;
    private Memory memory;
    private JJCInterpreterController controller;
    private ArrayList<JajaCodeNode> nodes;
    private JajaCodeNode nextNode;

    @Before
    public void init() {
        memory = mock(Memory.class);
        controller = mock(JJCInterpreterController.class);
        nodes = spy(new ArrayList<>());
        jjcVisitor = spy(new JJCInterpreterVisitor(memory, nodes, controller));

        nextNode = mock(JajaCodeNode.class);
    }

    @Test
    public void visitJcInit() throws Exception {
        JcInitNode jcInit = JcInitNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        jcInit.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitADD__noIntegerOnMemoryTop__throwException() throws Exception {
        JcAddNode jcAdd = JcAddNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcAdd.accept(jjcVisitor);
    }

    @Test
    public void visitADD__actuallyADD() throws Exception {
        JcAddNode jcAdd = JcAddNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, 7, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcAdd.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, 12, OBJ.CST, SORTE.INT));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }
}