package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
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

    @Test(expected = IllegalStateException.class)
    public void visitSUB_noIntegerOnMemoryTop__throwException() throws Exception {
        JcSubNode jcSub = JcSubNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSub.accept(jjcVisitor);
    }

    @Test
    public void visitSUB__actuallySUB() throws Exception {
        JcSubNode jcSub = JcSubNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 24, null, null);
        Quadruplet rhs = new Quadruplet(null, 7, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSub.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, 17, OBJ.CST, SORTE.INT));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitMUL_noIntegerOnMemoryTop__throwException() throws Exception {
        JcMulNode jcMul = JcMulNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcMul.accept(jjcVisitor);
    }

    @Test
    public void visitMUL__actuallyMUL() throws Exception {
        JcMulNode jcMul = JcMulNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 3, null, null);
        Quadruplet rhs = new Quadruplet(null, 9, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcMul.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, 27, OBJ.CST, SORTE.INT));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitDIV_noIntegerOnMemoryTop__throwException() throws Exception {
        JcDivNode jcDiv = JcDivNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcDiv.accept(jjcVisitor);
    }


    @Test
    public void visitDIV__actuallyDIV() throws Exception {
        JcDivNode jcDiv = JcDivNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 15, null, null);
        Quadruplet rhs = new Quadruplet(null, 3, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcDiv.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, 5, OBJ.CST, SORTE.INT));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = ArithmeticException.class)
    public void visitDIV__zeroDivision__throwException() throws Exception {
        JcDivNode jcDiv = JcDivNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 3, null, null);
        Quadruplet rhs = new Quadruplet(null, 0, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcDiv.accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitAND_noIntegerOnMemoryTop__throwException() throws Exception {
        JcAndNode jcAnd = JcAndNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcAnd.accept(jjcVisitor);
    }

    @Test
    public void visitAND__actuallyAND() throws Exception {
        JcAndNode jcAnd = JcAndNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, true, null, null);
        Quadruplet rhs = new Quadruplet(null, false, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcAnd.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, false, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitOR_noIntegerOnMemoryTop__throwException() throws Exception {
        JcOrNode jcOr = JcOrNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcOr.accept(jjcVisitor);
    }

    @Test
    public void visitOR__actuallyOR() throws Exception {
        JcOrNode jcOr = JcOrNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, true, null, null);
        Quadruplet rhs = new Quadruplet(null, false, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcOr.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, true, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitSUP_noIntegerOnMemoryTop__throwException() throws Exception {
        JcSupNode jcSup = JcSupNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSup.accept(jjcVisitor);
    }


    @Test
    public void visitSUP__whenSUP__true() throws Exception {
        JcSupNode jcSup = JcSupNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 15, null, null);
        Quadruplet rhs = new Quadruplet(null, 3, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSup.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, true, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitSUP__whenEQ__false() throws Exception {
        JcSupNode jcSup = JcSupNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 1, null, null);
        Quadruplet rhs = new Quadruplet(null, 3, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSup.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, false, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitSUP__whenINF__false() throws Exception {
        JcSupNode jcSup = JcSupNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 15, null, null);
        Quadruplet rhs = new Quadruplet(null, 30, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcSup.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, false, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitCMP_noIntegerOnMemoryTop__throwException() throws Exception {
        JcCmpNode jcCmp = JcCmpNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcCmp.accept(jjcVisitor);
    }

    @Test
    public void visitCMP__whenBOOLEAN__EQ__true() throws Exception {
        JcCmpNode jcCmp = JcCmpNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, true, null, null);
        Quadruplet rhs = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcCmp.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, true, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitCMP__whenBOOLEAN__NEQ__false() throws Exception {
        JcCmpNode jcCmp = JcCmpNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, true, null, null);
        Quadruplet rhs = new Quadruplet(null, false, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcCmp.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, false, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitCMP__whenINT__EQ__true() throws Exception {
        JcCmpNode jcCmp = JcCmpNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, 5, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcCmp.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, true, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitCMP__whenINT__NEQ__false() throws Exception {
        JcCmpNode jcCmp = JcCmpNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet lhs = new Quadruplet(null, 5, null, null);
        Quadruplet rhs = new Quadruplet(null, 6, null, null);
        when(memory.depiler()).thenReturn(rhs, lhs);

        jcCmp.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, false, OBJ.CST, SORTE.BOOLEAN));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }


}