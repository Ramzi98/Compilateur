package edu.ubfc.st.vm.project.grp7.memory;

import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.HeadersNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.InstrsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ListExpNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.VarsNode;

public class IDEMemory implements Memory {
    private final Stack stack;
    private final Heap heap;

    public IDEMemory(Stack stack, Heap heap) {
        this.stack = stack;
        this.heap = heap;
    }

    @Override
    public Memory empiler(Quadruplet q) {
        stack.empiler(q);
        return this;
    }

    @Override
    public Memory depiler() {
        stack.depiler();
        return this;
    }

    @Override
    public Memory echanger() {
        stack.echanger();
        return this;
    }

    @Override
    public Memory identVal(String id, SORTE t, int s) {
        return null;
    }

    @Override
    public Memory declVar(String id, Object val, SORTE type) {
        return null;
    }

    @Override
    public Memory declCst(String id, Object val, SORTE type) {
        return null;
    }

    @Override
    public Memory declTab(String id, Object val, SORTE type) {
        return null;
    }

    @Override
    public Memory declMeth(String id, Object val, SORTE type) {
        return null;
    }

    @Override
    public Memory retirerDecl(String id) {
        return null;
    }

    @Override
    public Memory affecterVal(String id, Object val) {
        return null;
    }

    @Override
    public Memory affecterValT(String id, Object val, int index) {
        return null;
    }

    @Override
    public Memory affecterType(String id, SORTE type) {
        return null;
    }

    @Override
    public Memory expParam(ListExpNode lexp, HeadersNode ent) {
        return null;
    }

    @Override
    public HeadersNode parametre(String id) {
        return null;
    }

    @Override
    public VarsNode declaration(String id) {
        return null;
    }

    @Override
    public InstrsNode corps(String id) {
        return null;
    }

    @Override
    public Object val(String id) {
        return null;
    }

    @Override
    public Object valT(String id) {
        return null;
    }

    @Override
    public OBJ object(String id) {
        return null;
    }

    @Override
    public SORTE sorte(String id) {
        return null;
    }

    @Override
    public String toString() {
        return stack.toString()
             + "\n\n"
             + heap.toString();
    }
}
