package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor ;
    private Memory memory;
    private MJJInterpreterController controller;

    @Before
    public void init() {
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        mjjVisitor = new MiniJajaInterpreterVisitor(memory, controller);
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

        classe.accept(mjjVisitor);

        verify(memory).declVar("C", null, null);
        verify(main).accept(mjjVisitor);
        verify(memory).retirerDecl("C");
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

        InOrder inOrder = inOrder(memory, decls, main);

        inOrder.verify(memory).declVar("C", null, null);
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(main).accept(mjjVisitor);
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(memory).retirerDecl("C");
    }

    @Test
    public void arrayItemNodeTest() throws Exception {
       /* ArrayItemNode arrayItemNode = mock(ArrayItemNode.class);
        mjjVisitor.visit(arrayItemNode);
        when(arrayItemNode.identifier().value()).thenReturn("i");

        verify(memory).valT("i",1);
*/
        /**
         * @Override
         *      *     public void visit(ArrayItemNode node) throws Exception {
         *      *         node.expression().accept(this);
         *      *         Object e = memory.valT(node.identifier().value(), (int) evals.pop());
         *      *         evals.push(e);
         *      *     }
         */

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