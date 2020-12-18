package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;

public interface TypeChecker {


    static TypeChecker TypeChecker(MiniJajaNode node){

        return new TypeCheckerImpl(node);

    }
    default void typeCheck() throws TypeCheckerException { }


}
