package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import org.mockito.Spy;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor;
    private Memory memory;
    private MJJInterpreterController controller;
    @Spy
    private Deque<Object> deque;

    @Before
    public void init() {
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        deque = spy(new ArrayDeque<>());
        mjjVisitor = spy(new MiniJajaInterpreterVisitor(memory, controller,deque));
    }

    @Test
    public void nullDecls__classeNode__visit() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("C");
        MainNode main = mock(MainNode.class);
        ClasseNode classe = ClasseNode.builder()
                .identifier(ident)
                .decls(null)
                .methmain(main)
                .line(0)
                .column(0)
                .build();

        doCallRealMethod().when(mjjVisitor).switchOnRetrait();
        doCallRealMethod().when(mjjVisitor).switchOffRetrait();

        classe.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, mjjVisitor, main);

        inOrder.verify(memory).declVar("C", null, null);
        inOrder.verify(main).accept(mjjVisitor);
        inOrder.verify(mjjVisitor).switchOnRetrait();
        inOrder.verify(mjjVisitor).switchOffRetrait();
        inOrder.verify(memory).retirerDecl("C");
    }

    @Test
    public void nonNullDecls__classeNode__visit() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("C");

        MainNode main = mock(MainNode.class);

        DeclsNode decls = mock(DeclsNode.class);

        ClasseNode classe = ClasseNode.builder()
                .identifier(ident)
                .decls(decls)
                .methmain(main)
                .line(0)
                .column(0)
                .build();

        classe.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, decls, main, mjjVisitor);

        inOrder.verify(memory).declVar("C", null, null);
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(main).accept(mjjVisitor);
        inOrder.verify(mjjVisitor).switchOnRetrait();
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(mjjVisitor).switchOffRetrait();
        inOrder.verify(memory).retirerDecl("C");
    }

    @Test
    public void identNode__evalExists__visit() throws Exception {
        when(memory.val("i")).thenReturn(5);

        IdentNode ident = IdentNode.builder()
                .line(0)
                .column(0)
                .value("i")
                .build();

        ident.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, deque);

        inOrder.verify(memory).val("i");
        inOrder.verify(deque).push(5);
    }

    @Test(expected = IllegalStateException.class)
    public void identNode__evalDoesntExists__visit__ThrowException() throws Exception {
        when(memory.val("i")).thenThrow(IllegalStateException.class);

        IdentNode ident = IdentNode.builder()
                .line(0)
                .column(0)
                .value("i")
                .build();

        ident.accept(mjjVisitor);
    }

    // TODO: 30/11/2020
    @Test
    public void givenRetraitOff__VisitDECL__thenDECLS() throws Exception {
        DeclsNode childDecls = mock(DeclsNode.class);
        MiniJajaNode decl = mock(MiniJajaNode.class);
        DeclsNode decls = DeclsNode.builder()
                .decls(childDecls)
                .decl(decl)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        decls.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, decl, childDecls);

        inOrder.verify(decl).accept(mjjVisitor);
        inOrder.verify(childDecls).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__VisitDECL__thenDECLS() throws Exception {
        DeclsNode childDecls = mock(DeclsNode.class);
        MiniJajaNode decl = mock(MiniJajaNode.class);
        DeclsNode decls = DeclsNode.builder()
                .decls(childDecls)
                .decl(decl)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        decls.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, decl, childDecls);

        inOrder.verify(childDecls).accept(mjjVisitor);
        inOrder.verify(decl).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__nullDecls() throws Exception {
        MiniJajaNode decl = mock(MiniJajaNode.class);
        DeclsNode decls = DeclsNode.builder()
                .decls(null)
                .decl(decl)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        decls.accept(mjjVisitor);

        verify(decl).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__nullDecls() throws Exception {
        MiniJajaNode decl = mock(MiniJajaNode.class);
        DeclsNode decls = DeclsNode.builder()
                .decls(null)
                .decl(decl)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        decls.accept(mjjVisitor);

        verify(decl).accept(mjjVisitor);
    }

    @Test
    public void NumberNodeTest() throws Exception {

        NumberNode numberNode = NumberNode.builder()
                .line(1)
                .column(0)
                .value(5)
                .build();

        numberNode.accept(mjjVisitor);

        verify(deque).push(5);
    }

    @Test
    public void BooleanNodeTest() throws Exception {

        BooleanNode booleanNode = BooleanNode.builder()
                .line(1)
                .column(0)
                .value(true)
                .build();

        mjjVisitor.visit(booleanNode);

        verify(deque).push(true);
    }

    @Test
    public void DivNodeTest() throws Exception {
        NumberNode leftOp = mock(NumberNode.class);
        NumberNode rightOp = mock(NumberNode.class);
        when(leftOp.value()).thenReturn(6);
        when(rightOp.value()).thenReturn(2);

        DivNode divNode = DivNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(2);
        deque.push(6);

        divNode.accept(mjjVisitor);
        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(2);
        inOrder2.verify(deque).push(6);
        inOrder2.verify(deque).push(3);
    }

    @Test
    public void MultNodeTest() throws Exception {
        NumberNode leftOp = mock(NumberNode.class);
        NumberNode rightOp = mock(NumberNode.class);
        when(leftOp.value()).thenReturn(6);
        when(rightOp.value()).thenReturn(2);

        MultNode multNode = MultNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(2);
        deque.push(6);

        multNode.accept(mjjVisitor);
        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(2);
        inOrder2.verify(deque).push(6);
        inOrder2.verify(deque).push(12);
    }

    @Test
    public void PlusNodeTest() throws Exception {
        NumberNode leftOp = mock(NumberNode.class);
        NumberNode rightOp = mock(NumberNode.class);
        when(leftOp.value()).thenReturn(6);
        when(rightOp.value()).thenReturn(2);

        PlusNode plusNode = PlusNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(2);
        deque.push(6);

        plusNode.accept(mjjVisitor);
        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(2);
        inOrder2.verify(deque).push(6);
        inOrder2.verify(deque).push(8);

    }

    @Test
    public void MoinsNodeTest() throws Exception {
        NumberNode leftOp = mock(NumberNode.class);
        NumberNode rightOp = mock(NumberNode.class);
        when(leftOp.value()).thenReturn(6);
        when(rightOp.value()).thenReturn(2);

        SubNode subNode = SubNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(2);
        deque.push(6);

        subNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(2);
        inOrder2.verify(deque).push(6);
        inOrder2.verify(deque).push(4);
    }

    @Test
    public void GreaterNodeTest() throws Exception {
        NumberNode leftOp = mock(NumberNode.class);
        NumberNode rightOp = mock(NumberNode.class);
        when(leftOp.value()).thenReturn(6);
        when(rightOp.value()).thenReturn(2);

        GreaterNode greaterNode = GreaterNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(2);
        deque.push(6);

        greaterNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(2);
        inOrder2.verify(deque).push(6);
        inOrder2.verify(deque).push(true);
    }

    @Test
    public void AndNodeTest() throws Exception {
        BooleanNode leftOp = mock(BooleanNode.class);
        BooleanNode rightOp = mock(BooleanNode.class);
        when(leftOp.value()).thenReturn(true);
        when(rightOp.value()).thenReturn(false);

        AndNode andNode = AndNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(false);
        deque.push(true);

        andNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(false);
        inOrder2.verify(deque).push(true);
        inOrder2.verify(deque).push(false);
    }

    @Test
    public void OrNodeTest() throws Exception {
        BooleanNode leftOp = mock(BooleanNode.class);
        BooleanNode rightOp = mock(BooleanNode.class);
        when(leftOp.value()).thenReturn(true);
        when(rightOp.value()).thenReturn(false);

        OrNode orNode = OrNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(false);
        deque.push(true);

        orNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(false);
        inOrder2.verify(deque).push(true);
        inOrder2.verify(deque).push(true);
    }

    @Test
    public void EqualsNodeTest() throws Exception {
        BooleanNode leftOp = mock(BooleanNode.class);
        BooleanNode rightOp = mock(BooleanNode.class);
        when(leftOp.value()).thenReturn(true);
        when(rightOp.value()).thenReturn(false);

        EqualsNode equalsNode = EqualsNode.builder()
                .column(0)
                .line(1)
                .leftOperand(leftOp)
                .rightOperand(rightOp)
                .build();


        deque.push(false);
        deque.push(true);

        equalsNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(leftOp, rightOp);
        inOrder.verify(leftOp).accept(mjjVisitor);
        inOrder.verify(rightOp).accept(mjjVisitor);

        InOrder inOrder2 = inOrder(deque);
        inOrder2.verify(deque).push(false);
        inOrder2.verify(deque).push(true);
        inOrder2.verify(deque).push(false);
    }

    @Test
    public void MinusNodeTest() throws Exception {
        NumberNode numberNode = mock(NumberNode.class);

        deque.push(5);

        MinusNode minusNode = MinusNode.builder()
                .column(0)
                .line(1)
                .expression(numberNode)
                .build();


        minusNode.accept(mjjVisitor);

        verify(deque).push(-5);
    }

    @Test
    public void NotNodeTest() throws Exception {
        BooleanNode booleanNode = mock(BooleanNode.class);
        deque.push(false);

        NotNode notNode = NotNode.builder()
                .column(0)
                .line(1)
                .expression(booleanNode)
                .build();


        notNode.accept(mjjVisitor);

        verify(deque).push(true);
    }



    @Test
    public void arrayItemNodeTest() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("i");

        NumberNode numberNode = mock(NumberNode.class);
        when(numberNode.value()).thenReturn(5);
        deque.push(5);

        ArrayItemNode arrayItemNode =ArrayItemNode.builder()
                .line(1)
                .column(0)
                .identifier(identNode)
                .expression(numberNode)
                .build();

        when(memory.valT("i",5)).thenReturn(true);
        arrayItemNode.accept(mjjVisitor);
        verify(deque).push(true);
    }

    @Test
    public void returnNodeTest() throws Exception {
        NumberNode numberNode = mock(NumberNode.class);
        deque.push(5);

        ReturnNode returnNode = ReturnNode.builder()
                .line(1)
                .column(0)
                .ret(numberNode)
                .build();

        returnNode.accept(mjjVisitor);
        verify(memory).classVar(5);
    }

    @Test
    public void writeNodeTest() throws Exception {
        StringNode stringNode = mock(StringNode.class);
        deque.push("coucou");

        WriteNode writeNode = WriteNode.builder()
                .line(1)
                .column(0)
                .printable(stringNode)
                .build();

        writeNode.accept(mjjVisitor);
        verify(controller).write("coucou");
    }

    @Test
    public void writeLnNodeTest() throws Exception {
        StringNode stringNode = mock(StringNode.class);
        deque.push("coucou");

        WriteLnNode writeLnNode = WriteLnNode.builder()
                .line(1)
                .column(0)
                .printable(stringNode)
                .build();

        writeLnNode.accept(mjjVisitor);
        verify(controller).writeLn("coucou");
    }

    @Test
    public void StringNodeTest() throws Exception {
        StringNode stringNode = StringNode.builder()
                .line(1)
                .column(0)
                .value("coucou")
                .build();

        stringNode.accept(mjjVisitor);
        verify(deque).push("coucou");
    }

    


    /**


     @Override
     public void visit(IfNode node) throws Exception {
     node.expression().accept(this);
     if ((boolean)evals.pop()) {
     if(node.trueInstrs() != null) {
     node.trueInstrs().accept(this);
     }
     } else if(node.falseInstrs() != null) {
     node.falseInstrs().accept(this);
     }
     }

     @Override
     public void visit(WhileNode node) throws Exception {
     node.expression().accept(this);
     if ((boolean)evals.pop()) {
     if(node.instrs() != null) {
     node.instrs().accept(this);
     }
     }
     }

     @Override
     public void visit(ListExpNode node) throws Exception {
     // TODO: 29/11/2020
     throw new RuntimeException("Not implemented yet");
     }*/
}
