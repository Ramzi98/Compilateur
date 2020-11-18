package edu.ubfc.st.vm.project.grp7.stack;

public class InvalidQuadrupletException extends Exception {
    private Quadruplet quadruplet;

    public InvalidQuadrupletException(Quadruplet quadruplet, String message){
        super(message);
        this.quadruplet = quadruplet;
    }

}
