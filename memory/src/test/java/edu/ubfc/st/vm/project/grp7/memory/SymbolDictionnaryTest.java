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
    public void peekScopeTest(){
        symbolDictionnary.register("i",0);
        Assert.assertEquals(symbolDictionnary.find("i"),0);

        symbolDictionnary.pushScope("function");
        symbolDictionnary.register("i",1);
        Assert.assertEquals(symbolDictionnary.find("i"),1);

        Assert.assertEquals(symbolDictionnary.peekScope(),"function-1");
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
    public void registerWithNullId(){
        symbolDictionnary.register(null,1);
    }

    @Test(expected = IllegalArgumentException.class )
    public void registerWithEmptyId(){
        symbolDictionnary.register("",1);
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

    @Test
    public void dontFindInCurrentScope(){
        symbolDictionnary.pushScope("function");
        Assert.assertEquals(symbolDictionnary.find("i","function-1"),-1);
    }


}