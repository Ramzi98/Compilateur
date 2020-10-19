package edu.ubfc.st.vm.project.grp7.stack;

public interface Stack {

    public void push(Quadruplet quadruplet);

    public Quadruplet pop();

    public void swap();

    public Quadruplet findQuadrupletInStack(String ident);

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet);

    public void print_stack();
}
