package edu.ubfc.st.vm.project.grp7.mini.jaja.ast;

public class CompilerException extends Exception {
    public CompilerException(Exception e){
        super(e);
        e.printStackTrace();
    }
}
