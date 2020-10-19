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

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet) throws StackException {
        Quadruplet quadrupletAChanger = findQuadrupletInStack(ident);
        if(quadrupletAChanger != null) {
            stack.findQuadrupletInStack(ident).setIdent(quadrupletAChanger.getIdent());
            quadruplet.setVal(quadrupletAChanger.getVal());
            quadruplet.setSorte(quadrupletAChanger.getSorte());
            quadruplet.setType(quadrupletAChanger.getType());
        }
        else {
            throw new StackException(stack,"The quadruplet couldn't be found in the stack");
        }
    }

    public void print_stack() {
        System.out.println(Arrays.toString(stack.toArray()));
    }
}