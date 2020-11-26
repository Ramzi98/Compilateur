package edu.ubfc.st.vm.project.grp7.memory;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SymbolDictionnaryTest  {

     private SymbolDictionnary symbolDictionnary;
    @Before
    public void init(){
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test
    public void registerInGlobalScope(){
        symbolDictionnary.register("i",0);
        int i = symbolDictionnary.find("i");
        Assert.assertEquals(i,0);
    }

    @Test
    public void registerInTwoScope(){
        symbolDictionnary.register("i",0);
        Assert.assertEquals(symbolDictionnary.find("i"),0);

        symbolDictionnary.pushScope("function");
        symbolDictionnary.register("i",1);
        Assert.assertEquals(symbolDictionnary.find("i"),1);
    }

    @Test
    public void popScopeTest(){
        symbolDictionnary.register("i",0);
        Assert.assertEquals(symbolDictionnary.find("i"),0);

        symbolDictionnary.pushScope("function");
        symbolDictionnary.register("i",1);
        Assert.assertEquals(symbolDictionnary.find("i"),1);

        symbolDictionnary.popScope();
        Assert.assertEquals(symbolDictionnary.find("i"),0);
    }

    @Test
    public void registerAndUnregister(){
        symbolDictionnary.register("i",0);
        Assert.assertEquals(symbolDictionnary.find("i"),0);

        symbolDictionnary.pushScope("function");
        symbolDictionnary.register("i",1);
        symbolDictionnary.register("j",2);
        Assert.assertEquals(symbolDictionnary.find("i"),1);
        Assert.assertEquals(symbolDictionnary.find("j"),2);

        symbolDictionnary.unregister("i");
        Assert.assertEquals(symbolDictionnary.find("i"),0);
        Assert.assertEquals(symbolDictionnary.find("j"),2);
    }

    @Test(expected = IllegalArgumentException.class )
    public void alreadyRegisted(){
        symbolDictionnary.register("i",1);
        symbolDictionnary.register("i",2);
    }

    @Test(expected = IllegalArgumentException.class )
    public void noRegistered(){
        symbolDictionnary.unregister("i");
    }

    @Test
    public void dontFindInGlobalScope(){
        Assert.assertEquals(symbolDictionnary.find("i"),-1);
    }

    @Test
    public void dontFindInOtherScope(){
        symbolDictionnary.pushScope("function");
        Assert.assertEquals(symbolDictionnary.find("i"),-1);
    }





    /**

     * public class SymbolDictionnary {
     *     private static final int GLOBAL_SCOPE = 0;
     *     private static final String NAME_GLOBAL_SCOPE = "global-"+GLOBAL_SCOPE;
     *     private int top = 0;
     *     private final ArrayList<String> scopes;
     *     private final HashMap<String, HashMap<String,Integer>> symbols;
     *
     *     public void pushScope(String scope){
     *         String scopeIdent = scope+ "-" + ++top;
     *         symbols.put(scopeIdent, new HashMap<>());
     *         scopes.add(scopeIdent);
     *     }
     *
     *     public void popScope(){
     *         if ( top > 0 ){
     *             symbols.remove(scopes.remove(top--));
     *         }
     *     }
     *
     *     public SymbolDictionnary(){
     *          scopes = new ArrayList<>();
     *          symbols = new HashMap<>();
     *          symbols.put(NAME_GLOBAL_SCOPE,new HashMap<>());
     *     }
     *
     *     public void register(String ident,int indice) throws IllegalArgumentException {
     *         HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
     *         if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
     *             throw new IllegalArgumentException("The value is already in the list.");
     *         }else{
     *             currentScope.put(ident,indice);
     *         }
     *     }
     *
     *     public void unregister(String ident)throws IllegalArgumentException{
     *         HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
     *         if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
     *             currentScope.remove(ident);
     *         }else{
     *             throw new IllegalArgumentException("The value does not exist.");
     *         }
     *     }
     *
     *     public int find(String ident){
     *         HashMap<String, Integer> currentScope = symbols.get(scopes.get(top));
     *         if (currentScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
     *             return currentScope.get(ident);
     *         }
     *
     *         if (top == GLOBAL_SCOPE){
     *             return  -1;
     *         }
     *         HashMap<String, Integer> globalScope = symbols.get(NAME_GLOBAL_SCOPE);
     *         if (globalScope.entrySet().parallelStream().anyMatch(e -> e.getKey().equals(ident))){
     *             return globalScope.get(ident);
     *         }
     *
     *         return -1;
     *     }
     */
}