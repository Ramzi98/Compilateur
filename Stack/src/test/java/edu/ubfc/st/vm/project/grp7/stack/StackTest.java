package edu.ubfc.st.vm.project.grp7.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StackTest {
    Quadruplet quadruplet_one;
    Quadruplet quadruplet_two;
    Quadruplet quadruplet_three;
    Quadruplet quadruplet_four;
    StackImpl stack;

    @Before
    public void Initialize_Quadruplet() {
        stack = new StackImpl();
        quadruplet_one = new Quadruplet("a",0, Sorte.Cst, "entier");
        quadruplet_two = new Quadruplet("a",1, Sorte.Meth, "double");
        quadruplet_three = new Quadruplet("b",0, Sorte.Cst, "entier");
        quadruplet_four = new Quadruplet("",0,Sorte.Var,"");
    }

    @Test
    public void Create_empty_stack() throws Exception {
        stack.print_stack();
    }

    @Test(expected = InvalidQuadrupletException.class)
    public void Push_stack_with_empty_ident_quadruplet() throws Exception {
        stack.push(quadruplet_four);
    }

    @Test(expected = InvalidQuadrupletException.class)
    public void Push_stack_with_dupplicate_ident_quadruplet() throws Exception {
        stack.push(quadruplet_one);
        stack.push(quadruplet_two);
    }

    @Test
    public void Push_stack_with_correct_quadruplet() throws Exception {
        stack.push(quadruplet_one);
        stack.push(quadruplet_three);
        stack.print_stack();
        assertEquals(stack.findQuadrupletInStack(quadruplet_three.getIdent()), quadruplet_three);
    }

    /*@Test(expected = StackException.class)
    public void Pop_empty_stack() throws Exception {
        stack.pop();
    }*/

    @Test
    public void Pop_correct() throws Exception {
        stack.push(quadruplet_one);
        stack.push(quadruplet_three);
        stack.pop();
        assertEquals(stack.findQuadrupletInStack(quadruplet_three.getIdent()), null);
    }

    @Test(expected = StackException.class)
    public void swap_empty_stack() throws Exception{
        stack.swap();
    }

    @Test
    public void swap_correct() throws Exception{

        stack.push(quadruplet_one);
        stack.push(quadruplet_three);
        stack.swap();
        assertEquals(stack.pop(),quadruplet_one);

    }

    @Test(expected = StackException.class)
    public void edit_quadruplet_stack_empty() throws Exception{

        stack.editQuadrupletInStack("1",quadruplet_three);

    }

    @Test
    public void edit_quadruplet_correct() throws Exception{

        stack.push(quadruplet_one);
        stack.editQuadrupletInStack(quadruplet_one.getIdent(),quadruplet_three);
        Assert.assertTrue(stack.pop() == quadruplet_three);

    }



}


