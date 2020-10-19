package edu.ubfc.st.vm.project.grp7.stack;

import java.util.*;

public class StackImpl implements Stack {
    private java.util.Stack stack;

    public StackImpl(){
        stack = new java.util.Stack<>();
    }

    public void push(Quadruplet quadruplet){
        stack.push(quadruplet);
    }

    public Quadruplet pop(){
        return (Quadruplet) stack.pop();
    }

    public void swap() {
        Quadruplet quadruplet1 = (Quadruplet) stack.pop();
        Quadruplet quadruplet2 = (Quadruplet) stack.pop();

        stack.push(quadruplet1);
        stack.push(quadruplet2);
    }

    public Quadruplet findQuadrupletInStack(String ident){
        for( Object stackQuad : stack) {
            if(((Quadruplet) stackQuad).getIdent() == ident){
                return (Quadruplet) stackQuad;
            }
        }
        return null;
    }

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet){
        Quadruplet quadrupletAChanger = findQuadrupletInStack(ident);
        if(quadrupletAChanger != null) {
            quadruplet.setIdent(quadrupletAChanger.getIdent());
            quadruplet.setVal(quadrupletAChanger.getVal());
            quadruplet.setSorte(quadrupletAChanger.getSorte());
            quadruplet.setType(quadrupletAChanger.getType());
        }
    }

    public void print_stack() {
        System.out.println(Arrays.toString(stack.toArray()));
    }
}