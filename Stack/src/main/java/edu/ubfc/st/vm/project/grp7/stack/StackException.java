package edu.ubfc.st.vm.project.grp7.stack;

public class StackException extends Exception {
    private java.util.Stack<Quadruplet> stack;

    public StackException(java.util.Stack<Quadruplet> stack, String message){
        super(message);
        this.stack = stack;
    }

}
