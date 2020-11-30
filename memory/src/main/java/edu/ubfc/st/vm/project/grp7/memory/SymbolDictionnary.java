package edu.ubfc.st.vm.project.grp7.memory;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolDictionnary {
    private static final int GLOBAL_SCOPE = 0;
    private static final String NAME_GLOBAL_SCOPE = "global-" + GLOBAL_SCOPE;
    private int top = 0;
    private final ArrayList<String> scopes;
    private final HashMap<String, HashMap<String,Integer>> symbols;


    public SymbolDictionnary() {
        scopes = new ArrayList<>();
        symbols = new HashMap<>();
        symbols.put(NAME_GLOBAL_SCOPE,new HashMap<>());
        scopes.add(NAME_GLOBAL_SCOPE);
    }

    public void pushScope(String scope) {
        String scopeIdent = scope+ "-" + ++top;
        scopes.add(scopeIdent);
        symbols.put(scopeIdent, new HashMap<>());
    }

    public void popScope() {
        if ( top > 0 ){
            symbols.remove(scopes.remove(top--));
        }
    }

    public String peekScope() {
        if ( top >= 0 ){
            return scopes.get(top);
        }
        return null;
    }

    public void register(String ident,int indice) throws IllegalArgumentException {
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));

        if(ident == null || ident.trim().isEmpty()){
            throw new IllegalArgumentException("Can't replace with null Id");
        }
        if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
            throw new IllegalArgumentException("The value is already in the list.");
        }else{
            currentScope.put(ident,indice);
        }
    }

    public void unregister(String ident) throws IllegalArgumentException {
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
        if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
            currentScope.remove(ident);
        }else{
            throw new IllegalArgumentException("The value does not exist.");
        }
    }

    public int find(String ident) {
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
        if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
            return currentScope.get(ident);
        }

        if (top == GLOBAL_SCOPE) {
            return  -1;
        }

        HashMap<String, Integer> globalScope = symbols.get(NAME_GLOBAL_SCOPE);
        if (globalScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
            return globalScope.get(ident);
        }

        return -1;
    }


}