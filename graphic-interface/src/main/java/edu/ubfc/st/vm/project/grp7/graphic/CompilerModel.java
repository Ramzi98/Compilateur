package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;

public class CompilerModel {
    InterpreterMiniJajaModel interpreterMiniJajaModel;
    InterpreterJajaCodeModel interpreterJajaCodeModel;
    CompilerImpl compiler;

    public CompilerModel(InterpreterJajaCodeModel interpreterJajaCodeModel, InterpreterMiniJajaModel interpreterMiniJajaModel){
        this.interpreterMiniJajaModel = interpreterMiniJajaModel;
        this.interpreterJajaCodeModel = interpreterJajaCodeModel;
    }

    public void compile(String file) throws Exception {
            interpreterMiniJajaModel.init(file);
            interpreterMiniJajaModel.build();
            compiler = new CompilerImpl(interpreterMiniJajaModel.getClasseNode());
            compiler.compile();
            interpreterJajaCodeModel.setNodes(compiler.jajaCodeNodes());
            JCCPrinter jccPrinter = new JCCPrinter(interpreterJajaCodeModel.getNodes());
            interpreterJajaCodeModel.setCode(jccPrinter.toString());

    }
}
