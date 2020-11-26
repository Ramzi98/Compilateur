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
    public IDEStack(SymbolDictionnary symbolDictionnary){
        this.symbolDictionnary = symbolDictionnary;
        quads = new ArrayList<>();
        top = -1;
    }

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
            symbolDictionnary.register(q.id(), ++top);
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

    @Override
    public void identVal(String id, SORTE t, int s) throws IllegalArgumentException, IllegalStateException {
        if (s < 0) {
           throw new IllegalArgumentException("identVal doesn't treat negative depths");
        }
        int depth = s;
        removeNulls();
        int index = top;
        while(depth > 0) {
            if(index == -1) {
                throw new IllegalArgumentException("identVal depth greater than stack size");
            }
            if (quads.get(index) != null) {
                depth--;
            }
            index--;
        }
        Quadruplet quad = quads.get(index);
        quad.id(id);
        quad.type(t);
    }

    @Override
    public Quadruplet removeFirst(String id) throws IllegalArgumentException {
        removeNulls();
        int index = symbolDictionnary.find(id);
        Quadruplet quadruplet = quads.get(index);
        symbolDictionnary.unregister(quadruplet.id());
        quads.remove(index);
        return quadruplet;
    }

    @Override
    public boolean isEmpty() {
        removeNulls();
        if (top == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        removeNulls();
        Quadruplet quad;
        for (int i = top; i >= 0 ; i--) {
            quad = quads.get(i);
            if (quad != null) {
                builder.append(quad.toString()+"\n");
            }
        }
        return builder.toString();
    }
}
