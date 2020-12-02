package edu.ubfc.st.vm.project.grp7.memory;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcNewNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcNewarrayNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeNode;

public enum SORTE {
    INT{
        @Override
        public String toString() {
            return "int";
        }
    },
    BOOLEAN{
        @Override
        public String toString() {
            return "boolean";
        }
    },
    OMEGA{
        @Override
        public String toString() {
            return "w";
        }
    };


    public static SORTE of(TypeMethNode.TypeMeth value) {
        switch (value){
            case INT:
                return SORTE.INT;
            case BOOLEAN:
                return SORTE.BOOLEAN;
            case VOID:
                return SORTE.OMEGA;
            default :
                return null;
        }
    }

    public static SORTE of(TypeNode.Type value) {
        switch (value) {
             case INT:
                return SORTE.INT;
            case BOOLEAN:
                return SORTE.BOOLEAN;
            default :
                return null;
        }
    }

    public static SORTE of(JcNewNode.Type type) {
        switch (type) {
            case INT:
                return SORTE.INT;
            case BOOLEAN:
                return SORTE.BOOLEAN;
            default :
                return null;
        }
    }

    public static SORTE of(JcNewarrayNode.Type type) {
        switch (type) {
            case INT:
                return SORTE.INT;
            case BOOLEAN:
                return SORTE.BOOLEAN;
            default :
                return null;
        }
    }
}