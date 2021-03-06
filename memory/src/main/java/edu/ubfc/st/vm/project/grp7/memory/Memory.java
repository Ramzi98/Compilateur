package edu.ubfc.st.vm.project.grp7.memory;

import edu.ubfc.st.vm.project.grp7.mjj.ast.node.HeadersNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.ListExpNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.VarsNode;

public interface Memory {
    Memory pushContext(String context);
    Memory popContext();

    Memory empiler(Quadruplet q);
    Quadruplet depiler();
    Memory echanger();

    Memory identVal(String id, SORTE t, int s);

    Memory declVar(String id, Object val, SORTE type);
    Memory declCst(String id, Object val, SORTE type);
    Memory declTab(String id, Object val, SORTE type);
    Memory declMeth(String id, Object val, SORTE type);

    Memory retirerDecl(String id);
    Memory affecterVal(String id, Object val) throws IllegalAccessException;
    Memory affecterValT(String id, int index, Object val) throws IllegalAccessException;
    Memory affecterType(String id, SORTE type) throws IllegalAccessException;

    //TODO: déplacer dans l'interpréteur pour enlever la dépendance de module
    Memory expParam(ListExpNode lexp, HeadersNode ent);
    HeadersNode parametre(String id);
    VarsNode declaration(String id);
    InstrsNode corps(String id);
    //Ces fonctions sont spécifiques au miniJaja pas à la mémoire de l'IDE

    Object val(String id) throws IllegalAccessException;
    Object valT(String id, int indice) throws IllegalAccessException;
    OBJ object(String id) throws IllegalAccessException;
    SORTE sorte(String id) throws IllegalAccessException;

    static Memory getInstance() {
        return new IDEMemory(new IDEStack(), new IDEHeap());
    }

    Object classVar(Object val);
}
