package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IDEStackTest {

    IDEStack ideStack;
    IDEStack ideStack2;

    @Before
    public void init(){
        ideStack = new IDEStack();
    }

    @Test
    public void peekFirstTest(){
        ideStack.empiler(new Quadruplet("i",5,OBJ.VAR,SORTE.INT));
        ideStack.empiler(new Quadruplet("j",6,OBJ.VAR,SORTE.INT));

        Assert.assertEquals(ideStack.peekFirst("i").id(),"i");
        Assert.assertEquals(ideStack.peekFirst("i").type(),SORTE.INT);
        Assert.assertEquals(ideStack.peekFirst("i").val(),5);
        Assert.assertEquals(ideStack.peekFirst("i").nature(),OBJ.VAR);

        Assert.assertEquals(ideStack.peekFirst("j").id(),"j");
        Assert.assertEquals(ideStack.peekFirst("j").type(),SORTE.INT);
        Assert.assertEquals(ideStack.peekFirst("j").val(),6);
        Assert.assertEquals(ideStack.peekFirst("j").nature(),OBJ.VAR);
    }

    @Test
    public void echangerTest(){
       /* ideStack.empiler(new Quadruplet("i",5,OBJ.VAR,SORTE.INT));
        ideStack.empiler(new Quadruplet("j",6,OBJ.VAR,SORTE.INT));
        ideStack.echanger();
        ideStack.depiler();

        Assert.assertEquals(ideStack.peekFirst("i").id(),"i");
        Assert.assertEquals(ideStack.peekFirst("i").type(),SORTE.INT);
        Assert.assertEquals(ideStack.peekFirst("i").val(),5);
        Assert.assertEquals(ideStack.peekFirst("i").nature(),OBJ.VAR);

        Assert.assertEquals(ideStack.peekFirst("j"),null);*/

    }

    @Test
    public void toStringTest(){
        ideStack.empiler(new Quadruplet("i",5,OBJ.VAR,SORTE.INT));
        ideStack.empiler(new Quadruplet("j",6,OBJ.VAR,SORTE.INT));

        System.out.println(ideStack);
    }


    @Test
    public void peekFirstNull(){
        Assert.assertEquals(ideStack.peekFirst("i"),null);
    }

/**
 * private final SymbolDictionnary symbolDictionnary;
 *     private final ArrayList<Quadruplet> quads ;
 *     private int top;
 *
 *     public IDEStack(){
 *         symbolDictionnary = new SymbolDictionnary();
 *         quads = new ArrayList<>();
 *         top = -1;
 *     }
 *
 *
 *     @Override
 *     public Quadruplet depiler() {
 *         removeNulls();
 *         if (top >= 0) {
 *             Quadruplet quad = quads.get(top);
 *             symbolDictionnary.unregister(quad.id());
 *             quads.remove(top--);
 *             removeNulls();
 *             return quad;
 *         }
 *         return null;
 *     }
 *
 *     private void removeNulls(){
 *         Quadruplet quad = null;
 *         while (quad == null && top >= 0){
 *             quad = quads.get(top--);
 *         }
 *     }
 *
 *     @Override
 *     public void echanger() {
 *         Quadruplet q1 = depiler();
 *         Quadruplet q2 = depiler();
 *         empiler(q1);
 *         empiler(q2);
 *     }
 *
 *     @Override
 *     public void pushScope(String scope) {
 *         symbolDictionnary.pushScope(scope);
 *     }
 *
 *     @Override
 *     public void popScope() {
 *         symbolDictionnary.popScope();
 *     }
 *
 *     @Override
 *     public void identVal(String id, SORTE t, int s) throws IllegalArgumentException, IllegalStateException {
 *         if (s < 0) {
 *            throw new IllegalArgumentException("identVal doesn't treat negative depths");
 *         }
 *         int depth = s;
 *         removeNulls();
 *         int index = top;
 *         while(depth > 0) {
 *             if(index == -1) {
 *                 throw new IllegalArgumentException("identVal depth greater than stack size");
 *             }
 *             if (quads.get(index) != null) {
 *                 depth--;
 *             }
 *             index--;
 *         }
 *         Quadruplet quad = quads.get(index);
 *         quad.id(id);
 *         quad.type(t);
 *     }
 *
 *     @Override
 *     public Quadruplet removeFirst(String id) throws IllegalArgumentException {
 *         removeNulls();
 *         int index = symbolDictionnary.find(id);
 *         Quadruplet quadruplet = quads.get(index);
 *         symbolDictionnary.unregister(quadruplet.id());
 *         quads.remove(index);
 *         return quadruplet;
 *     }
 *
 *     @Override
 *     public boolean isEmpty() {
 *         removeNulls();
 *         if (top == 0) {
 *             return true;
 *         } else {
 *             return false;
 *         }
 *     }
 *
 *     @Override
 *     public String toString() {
 *         StringBuilder builder = new StringBuilder();
 *         removeNulls();
 *         Quadruplet quad;
 *         for (int i = top; i >= 0 ; i--) {
 *             quad = quads.get(i);
 *             if (quad != null) {
 *                 builder.append(quad.toString()+"\n");
 *             }
 *         }
 *         return builder.toString();
 *     }
 */

}