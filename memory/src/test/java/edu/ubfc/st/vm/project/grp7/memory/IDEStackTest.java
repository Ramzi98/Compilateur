package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;

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
        // i absent du dictionnaire de symbole
        when(symbolDictionnary.find("i")).thenReturn(-1);
        assertThat(ideStack.peekFirst("i"), is(nullValue()));
    }

    @Test
    public void removeFirstTest(){
        ideStack.empiler(new Quadruplet("i", 5, OBJ.VAR, SORTE.INT));
        when(symbolDictionnary.find("i")).thenReturn(0);

        ideStack.empiler(new Quadruplet("j", null, OBJ.VCST, SORTE.INT));
        when(symbolDictionnary.find("j")).thenReturn(1);

        ideStack.empiler(new Quadruplet("k", true, OBJ.VAR, SORTE.BOOLEAN));
        when(symbolDictionnary.find("k")).thenReturn(2);

        ideStack.empiler(new Quadruplet("l", false, OBJ.CST, SORTE.BOOLEAN));
        when(symbolDictionnary.find("l")).thenReturn(3);

        ideStack.removeFirst("k");
        ideStack.removeFirst("j");

        Quadruplet q = ideStack.depiler();
        assertThat(q.id(), is("l"));
        assertThat(q.val(), is(false));
        assertThat(q.type(), is(SORTE.BOOLEAN));
        assertThat(q.nature(), is(OBJ.CST));

        Quadruplet q2 = ideStack.depiler();
        assertThat(q2.id(), is("i"));
        assertThat(q2.val(), is(5));
        assertThat(q2.type(), is(SORTE.INT));
        assertThat(q2.nature(), is(OBJ.VAR));
    }

    @Test
    public void pushScope__symbolDictionnaryIsRequired() {
        ideStack.pushScope("meth");
        verify(symbolDictionnary).pushScope("meth");
    }

    @Test
    public void popScope__symbolDictionnaryIsRequired() {
        ideStack.popScope();
        verify(symbolDictionnary).popScope();
    }

    @Test
    public void emptySack__IsEmpty__ReturnTrue() {
        assertThat(ideStack.isEmpty(), is(true));
    }

    @Test
    public void onElemInStack__isEmpty__ReturnFalse() {
        ideStack.empiler(new Quadruplet("oui", "zbeb", OBJ.TAB, SORTE.OMEGA));
        assertThat(ideStack.isEmpty(), is(false));
    }

    @Test
    public void fulledAndEmptied__isEmpty__ReturnFalse() {
        ideStack.empiler(new Quadruplet("oui", "zbeb", OBJ.TAB, SORTE.OMEGA));
        when(symbolDictionnary.find("oui")).thenReturn(0);
        ideStack.empiler(new Quadruplet("pop", false, OBJ.CST, SORTE.BOOLEAN));
        when(symbolDictionnary.find("pop")).thenReturn(1);
        ideStack.removeFirst("oui");
        ideStack.depiler();

        assertThat(ideStack.isEmpty(), is(true));
    }

    @Test
    public void unnamed__identVal__renamed() {
        ideStack.empiler(new Quadruplet(null, 1, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet(null, 2, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet(null, 3, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet(null, 4, OBJ.VAR, null));

        ideStack.identVal("i", SORTE.INT, 2);
        verify(symbolDictionnary).register("i", 1);
        when(symbolDictionnary.find("i")).thenReturn(1);

        Quadruplet quad = ideStack.removeFirst("i");
        assertThat(quad.id(), is("i"));
        assertThat(quad.nature(), is(OBJ.VAR));
        assertThat(quad.type(), is(SORTE.INT));
        assertThat(quad.val(), is(2));
    }

    @Test
    public void unnamed__HoledStructure__identVal__renamed() {
        ideStack.empiler(new Quadruplet(null, 1, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet(null, 2, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet("j", 3, OBJ.VAR, null));
        when(symbolDictionnary.find("j")).thenReturn(2);
        ideStack.empiler(new Quadruplet(null, 4, OBJ.VAR, null));
        ideStack.removeFirst("j");
        when(symbolDictionnary.find("j")).thenReturn(-1);

        ideStack.identVal("i", SORTE.INT, 1);
        verify(symbolDictionnary).register("i", 1);
        when(symbolDictionnary.find("i")).thenReturn(1);

        Quadruplet quad = ideStack.removeFirst("i");
        assertThat(quad.id(), is("i"));
        assertThat(quad.nature(), is(OBJ.VAR));
        assertThat(quad.type(), is(SORTE.INT));
        assertThat(quad.val(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void depthGTStackSize__identVal__throwException() {
        ideStack.empiler(new Quadruplet(null, 1, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet(null, 2, OBJ.VAR, null));
        ideStack.empiler(new Quadruplet("j", 3, OBJ.VAR, null));
        when(symbolDictionnary.find("j")).thenReturn(2);
        ideStack.empiler(new Quadruplet(null, 4, OBJ.VAR, null));
        ideStack.removeFirst("j");
        when(symbolDictionnary.find("j")).thenReturn(-1);

        ideStack.identVal("i", SORTE.INT, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullId__identVal__throwException() {
        ideStack.empiler(new Quadruplet(null, 1, OBJ.VAR, null));

        ideStack.identVal(null, SORTE.INT, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyId__identVal__throwException() {
        ideStack.empiler(new Quadruplet(null, 1, OBJ.VAR, null));

        ideStack.identVal(" ", SORTE.INT, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depth__neg__exception() {
        ideStack.identVal("i", SORTE.INT, -1);
    }

    @Test(expected = IllegalStateException.class)
    public void already__exist__exception() {
        ideStack.empiler(new Quadruplet("j", 1, OBJ.VAR, null));
        ideStack.identVal("i", SORTE.INT, 0);
    }
}