package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;


public class IDEMemoryTest {
    IDEStack ideStack;
    IDEHeap ideHeap;
    IDEMemory ideMemory;

    @Before
    public void init() {
        ideStack = mock(IDEStack.class);
        ideHeap = mock(IDEHeap.class);
        ideMemory = new IDEMemory(ideStack, ideHeap);
    }

    @Test
    public void pushContextTest() {
        ideMemory.pushContext("function");
        verify(ideStack).pushScope("function");
    }

    @Test
    public void popContextTest() {
        ideMemory.popContext();
        verify(ideStack).popScope();
    }

    @Test
    public void empilerTest() {
        Quadruplet quadruplet = new Quadruplet("i", 5, OBJ.VAR, SORTE.INT);
        ideMemory.empiler(quadruplet);
        verify(ideStack).empiler(quadruplet);
    }

    @Test
    public void depilerTest() {
        ideMemory.depiler();
        verify(ideStack).depiler();
    }

    @Test
    public void echangerTest() {
        ideMemory.echanger();
        verify(ideStack).echanger();
    }

    @Test
    public void identValTest() {
        ideMemory.identVal("i", SORTE.INT, 0);
        verify(ideStack).identVal("i", SORTE.INT, 0);
    }


    @Test
    public void declVarTest() {
        ideMemory.declVar("i", 5, (SORTE.INT));
        verify(ideStack).empiler(new Quadruplet("i", 5, OBJ.VAR, SORTE.INT));
    }

    @Test
    public void declCstNullTest() {
        ideMemory.declCst("i", null, SORTE.INT);
        verify(ideStack).empiler(new Quadruplet("i", null, OBJ.VCST, SORTE.INT));
    }

    @Test
    public void declCstTest() {
        ideMemory.declCst("i", 5, SORTE.INT);
        verify(ideStack).empiler(new Quadruplet("i", 5, OBJ.CST, SORTE.INT));
    }

    @Test
    public void declTabTest() {
        Object ref = "ref";
        when(ideHeap.CreerTas(any(), any())).thenReturn(ref);
        ideMemory.declTab("i", 5, SORTE.BOOLEAN);
        verify(ideHeap).CreerTas(any(), any());
        verify(ideStack).empiler(new Quadruplet("i", ref , OBJ.TAB, SORTE.BOOLEAN));
    }

    @Test
    public void declMethTest() {
        ideMemory.declMeth("i", 5, SORTE.BOOLEAN);
        verify(ideStack).empiler(new Quadruplet("i", 5, OBJ.METH, SORTE.BOOLEAN));
    }

    @Test
    public void retirerDeclEmptyStackTest() {
        // Check that method call doesn't throw any Exception
        when(ideStack.isEmpty()).thenReturn(true);
        ideMemory.retirerDecl("i");
    }

    @Test
    public void retirerDeclTabTest() {
        Object ref = "ref";
        when(ideStack.removeFirst("i")).thenReturn(new Quadruplet("i", ref, OBJ.TAB, SORTE.INT));
        ideMemory.retirerDecl("i");
        verify(ideStack).removeFirst("i");
        verify(ideHeap).retirerTas(ref, SORTE.INT);
    }

    @Test
    public void retirerDeclVarTest() {
        ideMemory.retirerDecl("i");
        verify(ideStack).removeFirst("i");
    }

    @Test(expected = IllegalAccessException.class)
    public void affecterValNullTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(null);
        ideMemory.affecterVal("i", 5);
        verify(ideStack).peekFirst("i");
    }

    @Test(expected = IllegalAccessException.class)
    public void affecterCstTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(new Quadruplet("i",null, OBJ.CST,SORTE.INT));
        ideMemory.affecterVal("i", 5);
    }

    @Test
    public void affecterVcstTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.VCST);
        when(mockedQuad.type()).thenReturn(SORTE.INT);
        when(mockedQuad.val()).thenReturn(null);

        when(ideStack.peekFirst("i")).thenReturn(mockedQuad); //<"i", w, VCST, INT>);
        ideMemory.affecterVal("i", 5);

        verify(mockedQuad).nature(OBJ.CST);
        verify(mockedQuad).val(5);
    }

    @Test
    public void affecterVarTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("var");
        when(mockedQuad.nature()).thenReturn(OBJ.VAR);
        when(mockedQuad.type()).thenReturn(SORTE.INT);
        when(mockedQuad.val()).thenReturn(null);

        when(ideStack.peekFirst("var")).thenReturn(mockedQuad); //<"var", w, VAR, INT>);
        ideMemory.affecterVal("var", 5);

        verify(mockedQuad).val(5);
    }

    @Test
    public void affecterTabTest() throws IllegalAccessException {
        Object refFrom = "refFrom", refTo = "refTo";
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.TAB);
        when(mockedQuad.type()).thenReturn(SORTE.BOOLEAN);
        when(mockedQuad.val()).thenReturn(refFrom);

        when(ideStack.peekFirst("i")).thenReturn(mockedQuad); //<"i", w, CST, INT>);
        ideMemory.affecterVal("i", refTo);

        verify(ideHeap).retirerTas(refFrom, SORTE.BOOLEAN);
        verify(ideHeap).ajouterRef(refTo, SORTE.BOOLEAN);
    }


    @Test(expected = IllegalAccessException.class )
    public void affecterValTNullQuadTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(null);
        ideMemory.affecterValT("i", 0, 5);

    }

    @Test(expected = IllegalAccessException.class )
    public void affecterValTNotTabTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);

        Object refFrom = "refFrom", refTo = "refTo";
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.VAR);
        when(mockedQuad.type()).thenReturn(SORTE.BOOLEAN);
        when(mockedQuad.val()).thenReturn(refFrom);
        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);
        ideMemory.affecterValT("i", 0, 5);
    }

    @Test
    public void affecterValTTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        Object refFrom = "refFrom", refTo = "refTo";
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.TAB);
        when(mockedQuad.type()).thenReturn(SORTE.BOOLEAN);
        when(mockedQuad.val()).thenReturn(refFrom);
        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);
        ideMemory.affecterValT("i", 0, 5);

        verify(ideHeap).affecterTas(refFrom,0,5);
    }

    @Test(expected = IllegalAccessException.class)
    public void affecterTypeNullQuadTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(null);
        ideMemory.affecterType("i", SORTE.BOOLEAN);
    }


    @Test
    public void affecterTypeTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);
        ideMemory.affecterType("i", SORTE.BOOLEAN);

        verify(mockedQuad).type(SORTE.BOOLEAN);
    }


    @Test(expected = IllegalAccessException.class)
    public void valNullQuadTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(null);
        ideMemory.affecterType("i", SORTE.BOOLEAN);
    }

    @Test
    public void valTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.VAR);
        when(mockedQuad.type()).thenReturn(SORTE.INT);
        when(mockedQuad.val()).thenReturn(5);

        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);

        assertThat(ideMemory.val("i"), is(5));
        verify(mockedQuad).val();
    }

    @Test(expected = IllegalAccessException.class)
    public void valTNullQuadTest() throws IllegalAccessException {
        when(ideStack.peekFirst("i")).thenReturn(null);
        ideMemory.valT("i",0);
    }

    @Test(expected = IllegalAccessException.class)
    public void valTNotTabTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.VAR);
        when(mockedQuad.type()).thenReturn(SORTE.INT);
        when(mockedQuad.val()).thenReturn(5);

        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);
        ideMemory.valT("i",0);
    }

    @Test
    public void valTTest() throws IllegalAccessException {
        Quadruplet mockedQuad = mock(Quadruplet.class);
        when(mockedQuad.id()).thenReturn("i");
        when(mockedQuad.nature()).thenReturn(OBJ.TAB);
        when(mockedQuad.type()).thenReturn(SORTE.INT);
        when(mockedQuad.val()).thenReturn(5);

        when(ideStack.peekFirst("i")).thenReturn(mockedQuad);
        ideMemory.valT("i",0);

        verify(ideHeap).valeurTas(5,0);
    }

     @Test
     public void sorte() throws IllegalAccessException {
        SORTE sorte = SORTE.INT;
        Quadruplet quad = new Quadruplet("gen", null, null, sorte);
        when(ideStack.peekFirst(any())).thenReturn(quad);

        assertThat(ideMemory.sorte("gen"), is(sorte));
     }

     @Test
     public void object() throws IllegalAccessException {
        OBJ obj = OBJ.TAB;
        Quadruplet quad = new Quadruplet("gen", null, obj, null);
        when(ideStack.peekFirst(any())).thenReturn(quad);

        assertThat(ideMemory.object("gen"), is(obj));
     }

     @Test
     public void toString__print() {
         when(ideStack.toString()).thenReturn("[]\n<i, w, VCST, BOOLEAN>\n<t, T, TAB, INT>");
         when(ideHeap.toString()).thenReturn("{\n\tT: [1, 2, 3]\n}");

         System.out.println(ideMemory);
     }
}