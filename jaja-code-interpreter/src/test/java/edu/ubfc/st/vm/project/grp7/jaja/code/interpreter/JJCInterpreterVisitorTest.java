package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

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
    public void visitDIV_notIntegersOnMemoryTop__throwException() throws Exception {
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
    public void visitOR_NotBooleansOnMemoryTop__throwException() throws Exception {
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
    public void visitSUP_notIntegersOnMemoryTop__throwException() throws Exception {
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
    public void visitCMP_differentTypesOnMemoryTop__throwException() throws Exception {
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

    @Test
    public void visitNOT__true__applyNOT__false() throws Exception {
        JcNotNode jcNot = JcNotNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcNot.accept(jjcVisitor);

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
    public void visitNOT__false__applyNOT__true() throws Exception {
        JcNotNode jcNot = JcNotNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, false, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcNot.accept(jjcVisitor);

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
    public void visitNOT_noBooleanOnMemoryTop__throwException() throws Exception {
        JcNotNode jcNot = JcNotNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, 5, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcNot.accept(jjcVisitor);
    }

    @Test
    public void visitNEG____applyNEG() throws Exception {
        JcNegNode jcNegNode = JcNegNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, 5, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcNegNode.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(memory).empiler(argThat(new ArgumentMatcher<Quadruplet>() {
            @Override public boolean matches(Object o) {
                return o.equals(new Quadruplet(null, -5, OBJ.CST, SORTE.INT));
            }
        }));
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitNEG_notIntegerOnMemoryTop__throwException() throws Exception {
        JcNegNode jcNeg = JcNegNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, false, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcNeg.accept(jjcVisitor);
    }

    @Test
    public void visitWrite__Write() throws Exception {
        final String test = "test";
        JcWriteNode jcWrite = JcWriteNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, test, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcWrite.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(controller).write(test);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitWriteLn__WriteLn() throws Exception {
        final String test = "test";
        JcWriteLnNode jcWrite = JcWriteLnNode.builder()
                .next(nextNode)
                .line(0)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, test, null, null);
        when(memory.depiler()).thenReturn(quad);

        jcWrite.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(0);
        orderedCalls.verify(controller).writeLn(test);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitNOP__nop() throws Exception {
        JcNopNode JcNop = JcNopNode.builder()
                .next(nextNode)
                .line(4)
                .column(1)
                .build();

        JcNop.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode);
        orderedCalls.verify(controller).debug(4);
        orderedCalls.verify(controller).nop();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitINC__noIntegerOnMemoryTop__throwException() throws Exception {
        final String id = "id";
        JcIncNode jcInc = JcIncNode.builder()
                .next(nextNode)
                .identifier(id)
                .line(6)
                .column(1)
                .build();

        Quadruplet val = new Quadruplet(null, true, null, null);
        when(memory.depiler()).thenReturn(val);

        jcInc.accept(jjcVisitor);
    }

    @Test
    public void visitINC__actuallyINC() throws Exception {
        final String id = "id";
        JcIncNode jcInc = JcIncNode.builder()
                .next(nextNode)
                .identifier(id)
                .line(6)
                .column(1)
                .build();

        Quadruplet val = new Quadruplet(null, 1, OBJ.CST, SORTE.INT);
        when(memory.depiler()).thenReturn(val);
        when(memory.val(id)).thenReturn(5);

        jcInc.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).val(id);
        orderedCalls.verify(memory).affecterVal(id, 6);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitPOP() throws Exception {
        JcPopNode jcPop = JcPopNode.builder()
                .next(nextNode)
                .line(6)
                .column(1)
                .build();

        Quadruplet val = new Quadruplet(null, 1, OBJ.CST, SORTE.INT);
        when(memory.depiler()).thenReturn(val);

        jcPop.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitPUSH() throws Exception {
        Object value = 1;
        JcPushNode jcPush = JcPushNode.builder()
                .next(nextNode)
                .valeur(value)
                .line(6)
                .column(1)
                .build();

        Quadruplet val = new Quadruplet(null, value, OBJ.CST, null);
        when(memory.depiler()).thenReturn(val);

        jcPush.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).empiler(val);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitSWAP() throws Exception {
        JcSwapNode jcSwap = JcSwapNode.builder()
                .next(nextNode)
                .line(6)
                .column(1)
                .build();

        jcSwap.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).echanger();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitSTOP() throws Exception {
        JcStopNode jcStop = JcStopNode.builder()
                .line(15)
                .column(1)
                .build();

        jcStop.accept(jjcVisitor);

        verify(controller).debug(15);
    }

    @Test(expected = IllegalStateException.class)
    public void visitReturn__notIntegerMemoryTop__throwException() throws Exception {
        Object addr = null;
        JcReturnNode jcReturn = JcReturnNode.builder()
                .next(nextNode)
                .line(6)
                .column(1)
                .build();

        Quadruplet top = new Quadruplet(null, addr, OBJ.CST, null);
        when(memory.depiler()).thenReturn(top);

        jcReturn.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test(expected = IllegalStateException.class)
    public void visitReturn__notIntegerInBounds__throwException() throws Exception {
        Object addr = 15;
        JcReturnNode jcReturn = JcReturnNode.builder()
                .next(nextNode)
                .line(6)
                .column(1)
                .build();

        Quadruplet top = new Quadruplet(null, addr, OBJ.CST, null);
        when(memory.depiler()).thenReturn(top);
        when(nodes.size()).thenReturn(10);

        jcReturn.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory, nodes);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(nodes).size();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitReturn__thenChangeAddr() throws Exception {
        Object addr = 6;
        JcReturnNode jcReturn = JcReturnNode.builder()
                .line(6)
                .column(1)
                .build();

        Quadruplet top = new Quadruplet(null, addr, OBJ.CST, null);
        when(memory.depiler()).thenReturn(top);
        doReturn(nextNode).when(nodes).get((int)addr);
        when(nodes.size()).thenReturn(10);

        jcReturn.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory, nodes);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(nodes).size();
        orderedCalls.verify(memory).popContext();
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }


/*
    @Test(expected = IllegalStateException.class)
    public void visitSTORE__butMemoryError__thenThrowException() throws Exception {
        JcStoreNode jcStore = JcStoreNode.builder()
                .next(nextNode)
                .identifier("id")
                .line(6)
                .column(1)
                .build();

        doThrow(new IllegalAccessException("exception")).when(memory).affecterVal("id", anyInt());

        jcStore.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(memory).affecterVal("id", 5);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }

    @Test
    public void visitSTORE() throws Exception {
        JcStoreNode jcStore = JcStoreNode.builder()
                .next(nextNode)
                .identifier("id")
                .line(6)
                .column(1)
                .build();

        Quadruplet quad = new Quadruplet(null, 5, OBJ.VAR, SORTE.INT);
        when(memory.depiler()).thenReturn(quad);

        jcStore.accept(jjcVisitor);

        InOrder orderedCalls = inOrder(controller, nextNode, memory);
        orderedCalls.verify(controller).debug(6);
        orderedCalls.verify(memory).depiler();
        orderedCalls.verify(memory).affecterVal("id", 5);
        orderedCalls.verify(nextNode).accept(jjcVisitor);
    }
    */
}