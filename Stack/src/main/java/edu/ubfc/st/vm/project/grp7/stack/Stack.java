package edu.ubfc.st.vm.project.grp7.stack;

public interface Stack {

     void push(Quadruplet quadruplet) throws InvalidQuadrupletException;

     Quadruplet pop();

     void swap() throws StackException;

     Quadruplet findQuadrupletInStack(String ident) ;

     void editQuadrupletInStack(String ident, Quadruplet quadruplet) throws StackException;

}
