package edu.ubfc.st.vm.project.grp7.compiler;

public class CompilerException extends Exception {
    public CompilerException(Exception e){
        super(e);
        e.printStackTrace();
    }
}
