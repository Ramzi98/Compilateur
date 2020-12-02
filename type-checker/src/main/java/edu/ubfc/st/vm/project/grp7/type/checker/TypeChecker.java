package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class TypeChecker {
    private MiniJajaNode node;
    TypeCheckerVisitor typeCheckerVisitor;

    public TypeChecker(MiniJajaNode node) {
        this.node = node;
        typeCheckerVisitor = new TypeCheckerVisitor();
    }

    public void setsymbolDictionnary(SymbolDictionnary symbolDictionnary)
    {
        typeCheckerVisitor.setDataDictionnary(symbolDictionnary);
    }

    public void typeCheck() throws IllFormedNodeException {

        try {
            typeCheckerVisitor.setPass(Pass.FIRST_PASS);
            node.accept(this.typeCheckerVisitor);

            typeCheckerVisitor.setPass(Pass.SECOND_PASS);
            node.accept(this.typeCheckerVisitor);
        } catch (Exception e) {
            throw new IllFormedNodeException(node.line(), node.column(), e.toString());
        }


    }


}
