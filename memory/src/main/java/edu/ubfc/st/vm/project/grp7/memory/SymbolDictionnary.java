package edu.ubfc.st.vm.project.grp7.memory;

import javafx.beans.binding.IntegerBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolDictionnary {
    private static final int GLOBAL_SCOPE = 0;
    private static final String NAME_GLOBAL_SCOPE ="global-"+GLOBAL_SCOPE;
    private int top = 0;
    private final ArrayList<String> scopes;
    private final HashMap<String, HashMap<String,Integer>> symbols;

    public void pushScope(String scope){
        String scopeIdent = scope+"-"+ ++top;
        symbols.put(scopeIdent,new HashMap<String,Integer>());
        scopes.add(scopeIdent);
    }

    public void popScope(){
        if ( top > 0 ){
            symbols.remove(scopes.remove(top--));
        }
    }

    public SymbolDictionnary(){
         scopes = new ArrayList();
         symbols = new HashMap<>();
         symbols.put(NAME_GLOBAL_SCOPE,new HashMap<>());
    }

    public void register(String ident,int indice) throws IllegalArgumentException {
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
        if (currentScope.entrySet().parallelStream().anyMatch(e-> e.getKey().equals(ident))){
            throw new IllegalArgumentException("The value is already in the list.");
        }else{
            currentScope.put(ident,indice);
        }
    }

    public void unregister(String ident)throws IllegalArgumentException{
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
        if (currentScope.entrySet().parallelStream().anyMatch(e-> e.getKey().equals(ident))){
            currentScope.remove(ident);
        }else{
            throw new IllegalArgumentException("The value does not exist.");
        }
    }

    public int find(String ident){
        HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
        if (currentScope.entrySet().parallelStream().anyMatch(e-> e.getKey().equals(ident))){
            return currentScope.get(ident);
        }

        if (top == GLOBAL_SCOPE){
            return  -1;
        }
        HashMap<String, Integer> globalScope = symbols.get(NAME_GLOBAL_SCOPE);
        if (globalScope.entrySet().parallelStream().anyMatch(e-> e.getKey().equals(ident))){
            return globalScope.get(ident);
        }

        return -1;
    }

}
