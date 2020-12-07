package edu.ubfc.st.vm.project.grp7.graphic;

import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
import edu.ubfc.st.vm.project.grp7.compiler.printer.JCCPrinter;
import edu.ubfc.st.vm.project.grp7.mini.jaja.parser.ASTParsingException;

import java.io.IOException;

public class CompilerGraphic {
    InterpreterMiniJaja interpreterMiniJaja;
    InterpreterJajaCode interpreterJajaCode;
    CompilerImpl compiler;

    public CompilerGraphic(InterpreterJajaCode interpreterJajaCode, InterpreterMiniJaja interpreterMiniJaja){
        this.interpreterMiniJaja = interpreterMiniJaja;
        this.interpreterJajaCode = interpreterJajaCode;
    }

    public void compile(String file){
        try {
            interpreterMiniJaja.init(file);
            try {
                interpreterMiniJaja.interpret();
                compiler = new CompilerImpl(interpreterMiniJaja.getClasseNode());
                compiler.compile();
                interpreterJajaCode.setNodes(compiler.jajaCodeNodes());
                JCCPrinter jccPrinter = new JCCPrinter(interpreterJajaCode.getNodes());
                interpreterJajaCode.setCode(jccPrinter.toString());
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
