package edu.ubfc.st.vm.project.grp7.memory;

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
    }
}
