package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ArrayItemNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import org.junit.Before;
import org.junit.Test;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor ;
    private Memory memory;
    private MJJInterpreterController controller;

    @Before
    public  void init(){
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        mjjVisitor = new MiniJajaInterpreterVisitor(memory, controller);
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