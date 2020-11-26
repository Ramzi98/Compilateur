package edu.ubfc.st.vm.project.grp7.memory;

import java.util.ArrayList;

public class IDEStack implements Stack {
    private final SymbolDictionnary symbolDictionnary;
    private final ArrayList<Quadruplet> quads ;
    private int top;

    public IDEStack(){
        symbolDictionnary = new SymbolDictionnary();
        quads = new ArrayList<>();
        top = -1;
    }

    /**
     * while (quad == null && top > 0){
     *             quad = quads.get(top);
     *             top--;
     *         }
     */


    @Override
    public Quadruplet peekFirst(String id) {
        int index = symbolDictionnary.find(id);
        if (index == -1){
            return null;
        }else{
            return quads.get(index);
        }
    }

    @Override
    public void empiler(Quadruplet q) {
        quads.add(q);
        if (! q.id().isEmpty()) {
            symbolDictionnary.register(q.id(), top++);
        }
    }

    @Override
    public Quadruplet depiler() {
        removeNulls();
        if (top >= 0) {
            Quadruplet quad = quads.get(top);
            symbolDictionnary.unregister(quad.id());
            quads.remove(top--);
            removeNulls();
            return quad;
        }
        return null;
    }

    private void removeNulls(){
        Quadruplet quad = null;
        while (quad == null && top >= 0){
            quad = quads.get(top--);
        }
    }

    @Override
    public void echanger() {
        Quadruplet q1 = depiler();
        Quadruplet q2 = depiler();
        empiler(q1);
        empiler(q2);
    }

    @Override
    public void pushScope(String scope) {
        symbolDictionnary.pushScope(scope);
    }

    @Override
    public void popScope() {
        symbolDictionnary.popScope();
    }

    /*
    IdentVal(i,t, < i1, v1, o1,t1 > .m, s) = Si s == 0 alors < i, v1, var,t > .m
    sinon < i1, v1, o1,t1 >.IdentVal(i,t, m, s  1)
     */
    @Override
    public void identVal(String id, SORTE t, int s) {
        removeNulls();
        int index = symbolDictionnary.find(id);
        if (index == top) {

        } else {

        }
    }


    @Override
    public Quadruplet removeFirst(String id) throws IllegalArgumentException{
        removeNulls();
        int index = symbolDictionnary.find(id);
        Quadruplet quadruplet = quads.get(index);
        symbolDictionnary.unregister(quadruplet.id());
        quads.remove(index);
        return quadruplet;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
