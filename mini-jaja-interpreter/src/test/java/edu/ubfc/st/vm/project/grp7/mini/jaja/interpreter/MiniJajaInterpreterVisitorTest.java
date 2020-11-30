package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;
import org.mockito.Spy;

import java.util.ArrayDeque;
import java.util.Deque;


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

    @Test
    public void givenRetraitOff__VisitVAR__thenVARS() throws Exception {
        VarsNode childVars = mock(VarsNode.class);
        MiniJajaNode var = mock(MiniJajaNode.class);
        VarsNode vars = VarsNode.builder()
                .vars(childVars)
                .var(var)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        vars.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, var, childVars);

        inOrder.verify(var).accept(mjjVisitor);
        inOrder.verify(childVars).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__VisitVARS__thenVAR() throws Exception {
        VarsNode childVars = mock(VarsNode.class);
        MiniJajaNode var = mock(MiniJajaNode.class);
        VarsNode vars = VarsNode.builder()
                .vars(childVars)
                .var(var)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        vars.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, var, childVars);

        inOrder.verify(childVars).accept(mjjVisitor);
        inOrder.verify(var).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__nullVars() throws Exception {
        MiniJajaNode var = mock(MiniJajaNode.class);
        VarsNode vars = VarsNode.builder()
                .vars(null)
                .var(var)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        vars.accept(mjjVisitor);

        verify(var).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__nullVars() throws Exception {
        MiniJajaNode var = mock(MiniJajaNode.class);
        VarsNode vars = VarsNode.builder()
                .vars(null)
                .var(var)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        vars.accept(mjjVisitor);

        verify(var).accept(mjjVisitor);
    }


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
    public void givenRetraitOn__VisitDECLS__thenDECL() throws Exception {
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
    public void givenRetraitOn__visitVar() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("id");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeMethNode typeMeth = mock(TypeMethNode.class);
        when(typeMeth.value()).thenReturn(TypeMethNode.TypeMeth.INT);
        VarNode var = VarNode.builder()
                .identifier(identNode)
                .expression(exp)
                .typeMeth(typeMeth)
                .line(15)
                .column(26)
                .build();

        mjjVisitor.switchOnRetrait();
        var.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode);

        inOrder.verify(identNode).value();
        inOrder.verify(memory).retirerDecl("id");
    }

    @Test
    public void givenRetraitOff__visitVar() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("id");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeMethNode typeMeth = mock(TypeMethNode.class);
        when(typeMeth.value()).thenReturn(TypeMethNode.TypeMeth.INT);
        VarNode var = VarNode.builder()
                .identifier(identNode)
                .expression(exp)
                .typeMeth(typeMeth)
                .line(15)
                .column(26)
                .build();

        deque.push(5);

        mjjVisitor.switchOffRetrait();
        var.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode, exp, typeMeth, deque);

        inOrder.verify(exp).accept(mjjVisitor);
        inOrder.verify(identNode).value();
        inOrder.verify(deque).pop();
        inOrder.verify(typeMeth).value();
        inOrder.verify(memory).declVar("id", 5, SORTE.INT);
    }

    @Test
    public void givenRetraitOn__visitArray() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("T");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeMethNode typeMeth = mock(TypeMethNode.class);
        when(typeMeth.value()).thenReturn(TypeMethNode.TypeMeth.INT);
        ArrayNode array = ArrayNode.builder()
                .identifier(identNode)
                .expression(exp)
                .typeMeth(typeMeth)
                .line(15)
                .column(26)
                .build();

        mjjVisitor.switchOnRetrait();
        array.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode);

        inOrder.verify(identNode).value();
        inOrder.verify(memory).retirerDecl("T");
    }

    @Test
    public void givenRetraitOff__visitArray() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("T");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeMethNode typeMeth = mock(TypeMethNode.class);
        when(typeMeth.value()).thenReturn(TypeMethNode.TypeMeth.BOOLEAN);
        ArrayNode array = ArrayNode.builder()
                .identifier(identNode)
                .expression(exp)
                .typeMeth(typeMeth)
                .line(15)
                .column(26)
                .build();

        deque.push(10);

        mjjVisitor.switchOffRetrait();
        array.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode, exp, typeMeth, deque);

        inOrder.verify(exp).accept(mjjVisitor);
        inOrder.verify(identNode).value();
        inOrder.verify(deque).pop();
        inOrder.verify(typeMeth).value();
        inOrder.verify(memory).declTab("T", 10, SORTE.BOOLEAN);
    }

    @Test
    public void givenRetraitOn__visitCst() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("N");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeNode type = mock(TypeNode.class);
        when(type.value()).thenReturn(TypeNode.Type.BOOLEAN);
        CstNode cst = CstNode.builder()
                .identifier(identNode)
                .expression(exp)
                .type(type)
                .line(15)
                .column(2)
                .build();

        mjjVisitor.switchOnRetrait();
        cst.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode);

        inOrder.verify(identNode).value();
        inOrder.verify(memory).retirerDecl("N");
    }

    @Test
    public void givenRetraitOff__visitCst() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("N");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        TypeNode type = mock(TypeNode.class);
        when(type.value()).thenReturn(TypeNode.Type.INT);
        CstNode cst = CstNode.builder()
                .identifier(identNode)
                .expression(exp)
                .type(type)
                .line(15)
                .column(2)
                .build();

        deque.push(100);

        mjjVisitor.switchOffRetrait();
        cst.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, identNode, exp, type, deque);

        inOrder.verify(exp).accept(mjjVisitor);
        inOrder.verify(identNode).value();
        inOrder.verify(deque).pop();
        inOrder.verify(type).value();
        inOrder.verify(memory).declCst("N", 100, SORTE.INT);
    }

    @Test
    public void givenRetraitOn__MainNode__VarsNull() throws Exception {
        MainNode main = MainNode.builder()
                .vars(null)
                .instrs(null)
                .line(56)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        main.accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__MainNode__NonNullVars() throws Exception {
        VarsNode vars = mock(VarsNode.class);
        MainNode main = MainNode.builder()
                .vars(vars)
                .instrs(null)
                .line(56)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        main.accept(mjjVisitor);

        verify(vars).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__MainNode__NullVars__NullInstrs() throws Exception {
        MainNode main = MainNode.builder()
                .vars(null)
                .instrs(null)
                .line(56)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        main.accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__MainNode__NonNullVars__NullInstrs() throws Exception {
        VarsNode vars = mock(VarsNode.class);
        MainNode main = MainNode.builder()
                .vars(vars)
                .instrs(null)
                .line(56)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        main.accept(mjjVisitor);

        verify(vars).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__MainNode__NonNullVars__NonNullInstrs() throws Exception {
        VarsNode vars = mock(VarsNode.class);
        InstrsNode instrs = mock(InstrsNode.class);
        MainNode main = MainNode.builder()
                .vars(vars)
                .instrs(instrs)
                .line(56)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        main.accept(mjjVisitor);

        verify(vars).accept(mjjVisitor);
        verify(instrs).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__VisitHEADER__thenHEADERS() throws Exception {
        HeadersNode childHeaders = mock(HeadersNode.class);
        HeaderNode header = mock(HeaderNode.class);
        HeadersNode headers = HeadersNode.builder()
                .headers(childHeaders)
                .header(header)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        headers.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, header, childHeaders);

        inOrder.verify(header).accept(mjjVisitor);
        inOrder.verify(childHeaders).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__VisitHEADERS__thenHEADER() throws Exception {
        HeadersNode childHeaders = mock(HeadersNode.class);
        HeaderNode header = mock(HeaderNode.class);
        HeadersNode headers = HeadersNode.builder()
                .headers(childHeaders)
                .header(header)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        headers.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, header, childHeaders);

        inOrder.verify(childHeaders).accept(mjjVisitor);
        inOrder.verify(header).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__nullHeaders() throws Exception {
        HeaderNode header = mock(HeaderNode.class);
        HeadersNode headers = HeadersNode.builder()
                .headers(null)
                .header(header)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOnRetrait();
        headers.accept(mjjVisitor);

        verify(header).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOff__nullHeaders() throws Exception {
        HeaderNode header = mock(HeaderNode.class);
        HeadersNode headers = HeadersNode.builder()
                .headers(null)
                .header(header)
                .line(2)
                .column(8)
                .build();

        mjjVisitor.switchOffRetrait();
        headers.accept(mjjVisitor);

        verify(header).accept(mjjVisitor);
    }

    @Test
    public void givenRetraitOn__visitHeader() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("p1");
        TypeNode type = mock(TypeNode.class);
        when(type.value()).thenReturn(TypeNode.Type.BOOLEAN);
        HeaderNode header = HeaderNode.builder()
                .identifier(ident)
                .line(78)
                .column(15)
                .type(type)
                .build();

        mjjVisitor.switchOnRetrait();
        header.accept(mjjVisitor);

        verify(ident).value();
        verify(memory).retirerDecl("p1");
    }

    @Test
    public void givenNullInstrsChild__visitOnlyInstr() throws Exception {
        MiniJajaNode instr = mock(MiniJajaNode.class);
        InstrsNode instrs = InstrsNode.builder()
                .instruction(instr)
                .instrs(null)
                .line(14)
                .column(5)
                .build();

        instrs.accept(mjjVisitor);

        verify(instr).accept(mjjVisitor);
    }

    @Test
    public void givenNonNullInstrsChild__visitINSTR__thenINSTRS() throws Exception {
        InstrsNode childInstrs = mock(InstrsNode.class);
        MiniJajaNode instr = mock(MiniJajaNode.class);
        InstrsNode instrs = InstrsNode.builder()
                .instruction(instr)
                .instrs(childInstrs)
                .line(14)
                .column(5)
                .build();

        instrs.accept(mjjVisitor);

        InOrder inOrder = inOrder(instr, childInstrs);

        inOrder.verify(instr).accept(mjjVisitor);
        inOrder.verify(childInstrs).accept(mjjVisitor);
    }

    @Test
    public void givenIdentNodeAsIdentifier__thenVisitAssign() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("j");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        AssignNode assign = AssignNode.builder()
                .identifier(ident)
                .expression(exp)
                .line(35)
                .column(7)
                .build();

        deque.push(true);
        assign.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, deque, ident, exp);
        inOrder.verify(exp).accept(mjjVisitor);
        inOrder.verify(ident).value();
        inOrder.verify(deque).pop();
        inOrder.verify(memory).affecterVal("j", true);
    }

    @Test
    public void givenArrayItemNodeAsIdentifier__thenVisitAssign() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("T");
        MiniJajaNode exp = mock(MiniJajaNode.class);
        ArrayItemNode tab = mock(ArrayItemNode.class);
        when(tab.identifier()).thenReturn(ident);
        when(tab.expression()).thenReturn(exp);
        MiniJajaNode val = mock(MiniJajaNode.class);
        AssignNode assign = AssignNode.builder()
                .identifier(tab)
                .expression(val)
                .line(35)
                .column(7)
                .build();

        deque.push(666); // value to assign
        deque.push(2); // index of the array
        assign.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, deque, tab, exp, ident, val);
        inOrder.verify(val).accept(mjjVisitor);
        inOrder.verify(tab).expression();
        inOrder.verify(exp).accept(mjjVisitor);
        inOrder.verify(tab).identifier();
        inOrder.verify(ident).value();
        inOrder.verify(deque).pop();
        inOrder.verify(deque).pop();
        inOrder.verify(memory).affecterValT("T", 2, 666);
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

    @Test
    public void ifNodeTest() throws Exception {
        BooleanNode expression = mock(BooleanNode.class);
        deque.push(true);
        InstrsNode instrsNode = mock(InstrsNode.class);

        IfNode ifNode = IfNode.builder()
                .line(1)
                .column(0)
                .expression(expression)
                .trueInstrs(instrsNode)
                .falseInstrs(null)
                .build();

        ifNode.accept(mjjVisitor);
        verify(instrsNode).accept(mjjVisitor);
    }

    @Test
    public void ifElseNodeTest() throws Exception {
        BooleanNode expression = mock(BooleanNode.class);
        deque.push(false);
        InstrsNode instrsNode = mock(InstrsNode.class);

        IfNode ifNode = IfNode.builder()
                .line(1)
                .column(0)
                .expression(expression)
                .trueInstrs(null)
                .falseInstrs(instrsNode)
                .build();

        ifNode.accept(mjjVisitor);
        verify(instrsNode).accept(mjjVisitor);
    }

    @Test
    public void whileNodeTest() throws Exception {
        BooleanNode expression = mock(BooleanNode.class);
        deque.push(false);
        InstrsNode instrsNode = mock(InstrsNode.class);

        WhileNode whileNode = WhileNode.builder()
                .line(1)
                .column(0)
                .expression(expression)
                .instrs(instrsNode)
                .build();

        deque.push(false);
        deque.push(false);
        deque.push(false);
        deque.push(true);
        whileNode.accept(mjjVisitor);

        InOrder inOrder = inOrder(deque);
        inOrder.verify(deque).push(false);
        inOrder.verify(deque).push(false);
        inOrder.verify(deque).push(false);
        inOrder.verify(deque).push(false);
        inOrder.verify(deque).push(true);
    }

    @Test
    public void incrementArrayItemNodeTest() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("T");
        
        MiniJajaNode exp = mock(MiniJajaNode.class);
        ArrayItemNode tab = mock(ArrayItemNode.class);
        
        when(tab.identifier()).thenReturn(ident);
        when(tab.expression()).thenReturn(exp);

        IncrementNode incrementNode = IncrementNode.builder()
                .identifier(tab)
                .line(35)
                .column(7)
                .build();

        deque.push(5); // value to assign
        deque.push(2); // index of the array
        incrementNode.accept(mjjVisitor);

        verify(memory).affecterValT("T", 2, 6);
    }

    @Test
    public void incrementIdentTest() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("i");

        deque.push(5);

        IncrementNode incrementNode = IncrementNode.builder()
                .identifier(ident)
                .line(35)
                .column(7)
                .build();

        deque.push(5); // value to assign
        incrementNode.accept(mjjVisitor);

        verify(memory).affecterVal("i", 6);
    }

    @Test
    public void sumNodeIdentTest() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("i");

        NumberNode numberNode = NumberNode.builder()
                .value(6)
                .line(1)
                .column(0)
                .build();

        deque.push(6);//valeur de numberNode
        deque.push(5);//valeur de i


        SumNode sumNode = SumNode.builder()
                .identifier(ident)
                .expression(numberNode)
                .line(35)
                .column(7)
                .build();


        sumNode.accept(mjjVisitor);


        verify(memory).affecterVal("i", 11);
    }

    @Test
    public void sumNodeArrayItemTest() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("T");

        MiniJajaNode exp = mock(MiniJajaNode.class);
        ArrayItemNode tab = mock(ArrayItemNode.class);

        when(tab.identifier()).thenReturn(ident);
        when(tab.expression()).thenReturn(exp);

        NumberNode numberNode = NumberNode.builder()
                .value(6)
                .line(1)
                .column(0)
                .build();

        deque.push(6);//indice
        deque.push(5);//expression
        deque.push(4);//valeur de i


        SumNode sumNode = SumNode.builder()
                .identifier(tab)
                .expression(numberNode)
                .line(35)
                .column(7)
                .build();


        sumNode.accept(mjjVisitor);

        verify(memory).affecterValT("T", 6,9);
    }
}
