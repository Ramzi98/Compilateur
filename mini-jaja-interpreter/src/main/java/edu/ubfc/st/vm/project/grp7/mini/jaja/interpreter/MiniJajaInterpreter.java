package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

public interface MiniJajaInterpreter {
    static Factory getFactory() {
        return Factory.getFactory();
    }

    void interpret();

    class Factory {
        private static Factory instance;
        private Factory() {
        }
        public static Factory getFactory() {
            if(instance == null) {
                instance = new Factory();
            }
            return instance;
        }

        public MiniJajaInterpreter createFrom() {
            return new MiniJajaInterpreterImpl();
        }
    }
}
