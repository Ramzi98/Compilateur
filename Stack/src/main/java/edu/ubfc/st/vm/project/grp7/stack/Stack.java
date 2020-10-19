package edu.ubfc.st.vm.project.grp7.stack;

public interface Stack {

    public void push(Quadruplet quadruplet) throws InvalidQuadrupletException;

    public Quadruplet pop();

    public void swap() throws StackException;

    public Quadruplet findQuadrupletInStack(String ident) ;

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet) throws StackException;

    public void print_stack();
}
