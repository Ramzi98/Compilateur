package java.edu.ubfc.st.vm.project.grp7.stack;

import java.util.*;
import java.util.Stack;

public class StackImpl {
    private java.util.Stack stack;

    public StackImpl(){
        stack = new java.util.Stack();
    }

    public void push(Quadruplet quadruplet){
        stack.push(quadruplet);
    }

    public void pop(){
        stack.pop();
    }

    public Quadruplet findQuadrupletInStack(String ident){
        
    }

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet){

    }

    public static void print_stack(Stack stack) {
        System.out.println(Arrays.toString(stack.toArray()));
    }
}