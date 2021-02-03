package edu.ubfc.st.vm.project.grp7.memory;

public enum OBJ {
    CST {
        @Override
        public String toString() {
            return "cst";
        }
    },
    VCST{
        @Override
        public String toString() {
            return "vcst";
        }
    },
    VAR{
        @Override
        public String toString() {
            return "var";
        }
    },
    METH{
        @Override
        public String toString() {
            return "meth";
        }
    },
    TAB{
        @Override
        public String toString() {
            return "tab";
        }
    };
}
