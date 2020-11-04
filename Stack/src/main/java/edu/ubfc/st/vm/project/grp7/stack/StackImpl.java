package edu.ubfc.st.vm.project.grp7.stack;

import java.util.*;

public class StackImpl implements Stack {
    private java.util.Stack stack;

    public StackImpl(){
        stack = new java.util.Stack<>();
    }

    public void push(Quadruplet quadruplet) throws InvalidQuadrupletException {
        if(findQuadrupletInStack(quadruplet.getIdent()) == null && quadruplet.getIdent() != "") {
            stack.push(quadruplet);
        }
        else {
            throw new InvalidQuadrupletException(quadruplet,"Error, the quadruplet is empty.");
        }
    }

    public Quadruplet pop(){
        return (Quadruplet) stack.pop();
    }

    public void swap() throws StackException {
        if(stack.size() > 1) {
            Quadruplet quadruplet1 = (Quadruplet) stack.pop();
            Quadruplet quadruplet2 = (Quadruplet) stack.pop();

            stack.push(quadruplet1);
            stack.push(quadruplet2);
        }
        else {
            throw new StackException(stack, "The stack doesn't contain enough entries to swap");
        }
    }

    public Quadruplet findQuadrupletInStack(String ident)  {

        for( Object stackQuad : stack) {
            if(((Quadruplet) stackQuad).getIdent() == ident){
                return (Quadruplet) stackQuad;
            }
        }
        return null;
    }

    public int findQuadrupletIndexInStack(String ident)  {

        for( int i =0;i<stack.size();i++ ){
            if(((Quadruplet) stack.get(i)).getIdent() == ident){
                return i;
            }
        }
        return -1;
    }

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet) throws StackException {
        int quadrupletAChanger = findQuadrupletIndexInStack(ident);
        if(quadrupletAChanger != -1) {
            stack.set(quadrupletAChanger,quadruplet);
        }
        else {
            throw new StackException(stack,"The quadruplet couldn't be found in the stack");
        }
    }

    public void print_stack() {
        System.out.println(Arrays.toString(stack.toArray()));
    }
}