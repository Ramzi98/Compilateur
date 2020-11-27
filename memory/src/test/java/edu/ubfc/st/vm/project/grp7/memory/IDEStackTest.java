package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IDEStackTest {
    IDEStack ideStack;
    SymbolDictionnary symbolDictionnary;

    @Before
    public void init() {
        symbolDictionnary = mock(SymbolDictionnary.class);
        ideStack = new IDEStack(symbolDictionnary);
    }

    @Test
    public void depilerNull(){
        assertThat(ideStack.depiler(),is(nullValue()));
    }

    @Test
    public void depilerNullWithBadId(){
        ideStack.empiler(new Quadruplet(null, true, null, null));
        Quadruplet q = ideStack.depiler();
        assertThat(q.nature(), is(nullValue()));
        assertThat(q.id(), is(nullValue()));
        assertThat(q.type(), is(nullValue()));
        assertThat(q.val(), is(true));
        assertThat(q.toString(),is("<w, true, w, w>"));
    }

    @Test
    public void peekFirstTest() {
        when(symbolDictionnary.find("i")).thenReturn(0);
        when(symbolDictionnary.find("j")).thenReturn(1);

        ideStack.empiler(new Quadruplet("i", 5, OBJ.VAR,SORTE.INT));
        ideStack.empiler(new Quadruplet("j", 6, OBJ.VAR,SORTE.INT));

        assertThat(ideStack.peekFirst("i").id(), is("i"));
        assertThat(ideStack.peekFirst("i").type(), is(SORTE.INT));
        assertThat(ideStack.peekFirst("i").val(), is(5));
        assertThat(ideStack.peekFirst("i").nature(), is(OBJ.VAR));

        assertThat(ideStack.peekFirst("j").id(), is("j"));
        assertThat(ideStack.peekFirst("j").type(), is(SORTE.INT));
        assertThat(ideStack.peekFirst("j").val(), is(6));
        assertThat(ideStack.peekFirst("j").nature(), is(OBJ.VAR));
    }

    @Test
    public void echangerTest() {
        ideStack.empiler(new Quadruplet("i", 5, OBJ.VAR, SORTE.INT));
        ideStack.empiler(new Quadruplet("j", 6, OBJ.VAR, SORTE.INT));
        ideStack.echanger();

        Quadruplet q = ideStack.depiler();
        assertThat(q.id(), is("i"));
        assertThat(q.val(), is(5));
        assertThat(q.type(), is(SORTE.INT));
        assertThat(q.nature(), is(OBJ.VAR));

        Quadruplet q2 = ideStack.depiler();
        assertThat(q2.id(), is("j"));
        assertThat(q2.val(), is(6));
        assertThat(q2.type(), is(SORTE.INT));
        assertThat(q2.nature(), is(OBJ.VAR));
    }

    @Test
    public void toStringTest() {
        ideStack.empiler(new Quadruplet("i", 5, OBJ.VAR, SORTE.INT));
        ideStack.empiler(new Quadruplet("j", 6, OBJ.VAR, SORTE.INT));

        System.out.println(ideStack);
    }

    @Test
    public void peekFirstNull() {
        //pas dans le dictionnaire de symbole
        when(symbolDictionnary.find("i")).thenReturn(-1);
        assertThat(ideStack.peekFirst("i"), is(nullValue()));
    }


    @Test
    public void removeFirstTest(){
       /* ideStack.empiler(new Quadruplet("i", 5, OBJ.VAR, SORTE.INT));
        ideStack.empiler(new Quadruplet("j", 6, OBJ.VAR, SORTE.INT));
        ideStack.empiler(new Quadruplet("k", 7, OBJ.VAR, SORTE.INT));
        ideStack.empiler(new Quadruplet("l", 8, OBJ.CST, SORTE.INT));

        ideStack.removeFirst("k");
        ideStack.removeFirst("j");

        Quadruplet q = ideStack.depiler();
        assertThat(q.id(), is("l"));
        assertThat(q.val(), is(8));
        assertThat(q.type(), is(SORTE.INT));
        assertThat(q.nature(), is(OBJ.CST));

        Quadruplet q2 = ideStack.depiler();
        assertThat(q.id(), is("i"));
        assertThat(q.val(), is(5));
        assertThat(q.type(), is(SORTE.INT));
        assertThat(q.nature(), is(OBJ.VAR));*/

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