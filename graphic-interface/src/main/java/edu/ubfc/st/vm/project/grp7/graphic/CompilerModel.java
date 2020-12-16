package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.ASTParsingException;

import java.io.IOException;

public class CompilerModel {
    InterpreterMiniJajaModel interpreterMiniJajaModel;
    InterpreterJajaCodeModel interpreterJajaCodeModel;
    CompilerImpl compiler;

    public CompilerModel(InterpreterJajaCodeModel interpreterJajaCodeModel, InterpreterMiniJajaModel interpreterMiniJajaModel){
        this.interpreterMiniJajaModel = interpreterMiniJajaModel;
        this.interpreterJajaCodeModel = interpreterJajaCodeModel;
    }

    public void compile(String file){
        try {
            interpreterMiniJajaModel.init(file);
            try {
                interpreterMiniJajaModel.build();
                compiler = new CompilerImpl(interpreterMiniJajaModel.getClasseNode());
                compiler.compile();
                interpreterJajaCodeModel.setNodes(compiler.jajaCodeNodes());
                JCCPrinter jccPrinter = new JCCPrinter(interpreterJajaCodeModel.getNodes());
                interpreterJajaCodeModel.setCode(jccPrinter.toString());
            } catch (ASTParsingException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
