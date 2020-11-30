package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.hamcrest.Matchers.in;
import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor;
    private Memory memory;
    private MJJInterpreterController controller;
    private Deque<Object> deque;

    @Before
    public void init() {
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        deque = mock(Deque.class);
        mjjVisitor = spy(new MiniJajaInterpreterVisitor(memory, controller, deque));
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

        when(deque.pop()).thenReturn(5);

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
    public void arrayItemNodeTest() throws Exception {
        IdentNode identNode = mock(IdentNode.class);
        when(identNode.value()).thenReturn("i");

        NumberNode numberNode = mock(NumberNode.class);
        when(numberNode.value()).thenReturn(5);

        ArrayItemNode arrayItemNode =ArrayItemNode.builder()
                .line(1)
                .column(0)
                .identifier(identNode)
                .expression(numberNode)
                .build();

        when(deque.pop()).thenReturn(5);
        mjjVisitor.visit(arrayItemNode);

        verify(memory).valT("i",5);
    }

    /**
     * @Override
     *     public void visit(OrNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((boolean)evals.pop() || (boolean)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(EqualsNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push(evals.pop() == evals.pop()); // maybe .equals() ?
     *     }
     *
     *     @Override
     *     public void visit(GreaterNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((int)evals.pop() > (int)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(PlusNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((int)evals.pop() + (int)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(SubNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((int)evals.pop() - (int)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(MinusNode node) throws Exception {
     *         node.expression().accept(this);
     *         evals.push(- (int) evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(MultNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((int)evals.pop() * (int)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(DivNode node) throws Exception {
     *         evaluateBinOp(node);
     *         evals.push((int)evals.pop() / (int)evals.pop());
     *     }
     *
     *     @Override
     *     public void visit(AppelENode node) throws Exception {
     *         // TODO: 29/11/2020
     *         throw new RuntimeException("Not implemented yet");
     *     }
     *
     *     @Override
     *     public void visit(BooleanNode node) throws Exception {
     *         evals.push(node.value());
     *     }
     *
     *     @Override
     *     public void visit(NumberNode node) throws Exception {
     *         evals.push(node.value());
     *     }
     *
     *     @Override
     *     public void visit(ArrayItemNode node) throws Exception {
     *         node.expression().accept(this);
     *         Object e = memory.valT(node.identifier().value(), (int) evals.pop());
     *         evals.push(e);
     *     }
     *
     *     @Override
     */
}