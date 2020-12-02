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
    public Memory pushContext(String context) {
        stack.pushScope(context);
        return this;
    }

    @Override
    public Memory popContext() {
        stack.popScope();
        return this;
    }

    @Override
    public Memory empiler(Quadruplet q) {
        stack.empiler(q);
        return this;
    }

    @Override
    public Quadruplet depiler() {
        return stack.depiler();
    }

    @Override
    public Memory echanger() {
        stack.echanger();
        return this;
    }

    @Override
    public Memory identVal(String id, SORTE t, int s) throws IllegalStateException, IllegalArgumentException {
        stack.identVal(id, t, s);
        return this;
    }

    @Override
    public Memory declVar(String id, Object val, SORTE type) {
        stack.empiler(new Quadruplet(id, val, OBJ.VAR, type));
        return this;
    }

    @Override
    public Memory declCst(String id, Object val, SORTE type) {
        if (val == null){
            stack.empiler(new Quadruplet(id, val, OBJ.VCST ,type));
        }else{
            stack.empiler(new Quadruplet(id, val, OBJ.CST, type));
        }
        return this;
    }

    @Override
    public Memory declTab(String id, Object val, SORTE type) {
        stack.empiler(new Quadruplet(id, heap.CreerTas(val, type), OBJ.TAB, type));
        return this;
    }

    @Override
    public Memory declMeth(String id, Object val, SORTE type) {
        stack.empiler(new Quadruplet(id, val, OBJ.METH, type));
        return this;
    }

    @Override
    public Memory retirerDecl(String id) {
        if(stack.isEmpty()) {
            return this;
        }

        Quadruplet quad = stack.removeFirst(id);
        if (quad != null && quad.nature() == OBJ.TAB) {
            heap.retirerTas(quad.val(), quad.type());
        }

        return this;
    }

    /**
     *   AffecterVal(i, v, < i1, v1, o,t > .m) = Si i != i1 alors
     *   < i1, v1, o,t > .AffecterVal(i, v, m) sinon Si o == vcst alors < i, v, cst,t > .m
     *   sinon Si o == cst alors m sinon Si o == tab alors AjouterRef(v,t),
     *   RetirerTas(v1,t) sinon < i, v, o,t > .m
     */
    @Override
    public Memory affecterVal(String id, Object val) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if(quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        switch (quad.nature()) {
            case VCST:
                quad.val(val);
                quad.nature(OBJ.CST);
                break;
            case CST :
                throw new IllegalAccessException(id + " is a cst");
            case TAB :
                Object previousRef = quad.val();
                quad.val(heap.ajouterRef(val, quad.type()));
                heap.retirerTas(previousRef, quad.type());
                break;
            default  : // VAR
                quad.val(val);
                break;
        }
        return this;
    }

    /**
     *  AffecterValT(i, v, ind, < i1, v1, o,t > .m) = Si i != i1 alors
     *  < i1, v1, o,t > .AffecterValT(i, v, ind, m) sinon AffecterTas(v1, ind, v, m)
     */
    @Override
    public Memory affecterValT(String id, int index, Object val) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if(quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        if (quad.nature() != OBJ.TAB) {
            throw new IllegalAccessException(id + " is not an array");
        }
        heap.affecterTas(quad.val(), index, val);
        return this;
    }

    /**
    *   AffecterType(i,t, < i1, v1, o,t1 > .m) = Si i == i1 alors < i, v1, o,t > .m
    *   sinon < i1, v1, o,t1 > .AffecterType(i,t, m)
    */
    @Override
    public Memory affecterType(String id, SORTE type) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if (quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        quad.type(type);
        return this;
    }

    /**
    *   ExpParam(lexp, ent, m) = Si (lexp 6= exnil) et (ent 6= enil) alors
    *   ExpParam(le1, ents, DeclVar(i, v,t, m)) sinon m
    */
    @Override
    public Memory expParam(ListExpNode lexp, HeadersNode ent) {
        return null;
    }

    /**
    *   Paramètre(i, []) = w
    *   Paramètre(i, < i1, v1, o,t > .m) = Si i 6= i1 alors Paramètre(i, m) sinon
    *   Si o 6= meth alors w sinon
    *   Si v1 == méthode(t, i, ents, dv, is) alors ents sinon w
    */
    @Override
    public HeadersNode parametre(String id) {
        return null;
    }

    /**
    *   I Déclaration(i, []) = w
    *   Déclaration(i, < i1, v1, o,t > .m) = Si i 6= i1 alors Déclaration(i, m) sinon Si
    *   o 6= meth alors w sinon
    */
    @Override
    public VarsNode declaration(String id) {
        return null;
    }

    @Override
    public InstrsNode corps(String id) {
        return null;
    }

    @Override
    public Object val(String id) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if (quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        return quad.val();
    }
    /**
     *   Objet(i, []) = w
     *   Objet(i, < i1, v1, o,t > .m) = Si i == i1 alors o sinon Objet(i, m)
     */
    @Override
    public Object valT(String id, int indice) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if (quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        if (quad.nature() != OBJ.TAB) {
            throw new IllegalAccessException(id + " is not an array");
        }
        return heap.valeurTas(quad.val(), indice);
    }

    @Override
    public OBJ object(String id) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if (quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        return quad.nature();
    }

    @Override
    public SORTE sorte(String id) throws IllegalAccessException {
        Quadruplet quad = stack.peekFirst(id);
        if (quad == null){
            throw new IllegalAccessException(id + " has not been declared");
        }
        return quad.type();
    }

    @Override
    public Object classVar(Object val) {
        return stack.classVar(val);
    }

    @Override
    public String toString() {
        return stack.toString()
             + "\n\n"
             + heap.toString();
    }
}
