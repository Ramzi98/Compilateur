package edu.ubfc.st.vm.project.grp7.memory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HeapTest {
    @Test(expected = IllegalArgumentException.class)
    public void creerTas__nullValue__SorteINT__ThrowException() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(null, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__nullValue__SorteBOOL__ThrowException() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(null, SORTE.BOOLEAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__nullValue__SorteOMEGA__ThrowException() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(null, SORTE.OMEGA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__WrongValue__SorteBOOL__NotNullRef() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(null, SORTE.BOOLEAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__WrongValue__SorteOMEGA__NotNullRef() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(new int[2], SORTE.OMEGA);
    }

    @Test
    public void creerTas__IntValue__SorteINT__NotNullRef() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(2, SORTE.INT);
        assertThat(ref, is(notNullValue()));
    }

    @Test
    public void creerTas__IntValue__SorteBOOLEAN__NotNullRef() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(2, SORTE.BOOLEAN);
        assertThat(ref, is(notNullValue()));
    }

    @Test
    public void creerTas__IntValue__SorteOMEGA__NotNullRef() {
        Heap h = new IDEHeap();
        Object ref = h.CreerTas(2, SORTE.OMEGA);
        assertThat(ref, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__NegativeValue__SorteInt__ThrowException() {
        Heap h = new IDEHeap();
        h.CreerTas(-2, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creerTas__ZeroValue__SorteInt__ThrowException() {
        Heap h = new IDEHeap();
        h.CreerTas(0, SORTE.INT);
    }

    private Heap heap;
    private Object refInt2;
    private Object refBoolean5;
    private Object refInt15;
    private Object refBoolean20;
    @Before
    public void setup() {
        heap = new IDEHeap();
        refInt2 = heap.CreerTas(2, SORTE.INT);
        refBoolean5 = heap.CreerTas(5, SORTE.BOOLEAN);
        refInt15 = heap.CreerTas(15, SORTE.INT);
        refBoolean20 = heap.CreerTas(20, SORTE.BOOLEAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ajouterRef__Inexistant__ThrowException() {
        heap.ajouterRef(1, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ajouterRef__toSuppressedArray__ThrowException() {
        heap.retirerTas(refInt2, SORTE.INT);
        heap.ajouterRef(refInt2, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retirerTas__toInewistant__ThrowException() {
        heap.retirerTas(1, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retirerTas__toSuppressedArray__ThrowException() {
        heap.retirerTas(refInt2, SORTE.INT);
        heap.retirerTas(refInt2, SORTE.INT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void affecterTas__InexistantRef__throwException() {
        heap.affecterTas(8, 0, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void affecterTas__SuperiorIndex__throwException() {
        heap.affecterTas(refBoolean20, 20, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void affecterTas__NegativeIndex__throwException() {
        heap.affecterTas(refBoolean5, -1, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void valeurTas__InexistantRef__throwException() {
        heap.valeurTas(8, 0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void valeurTas__SuperiorIndex__throwException() {
        heap.valeurTas(refBoolean20, 20);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void valeurTas__NegativeIndex__throwException() {
        heap.valeurTas(refBoolean5, -1);
    }

    @Test
    public void valeurTas__UninitializedValueIsNull() {
        Object val = heap.valeurTas(refInt15, 5);
        assertThat(val, is(nullValue()));
    }

    @Test
    public void valeurTas__InitializedValue__INT() {
        heap.affecterTas(refInt15, 5, 89);
        Object val = heap.valeurTas(refInt15, 5);
        assertThat(val, is(89));
    }

    @Test
    public void valeurTas__InitializedValue__BOOLEAN() {
        heap.affecterTas(refInt15, 5, true);
        Object val = heap.valeurTas(refInt15, 5);
        assertThat(val, is(true));
    }

    @Test
    public void ajouterRef__RefIsdoubled() {
        heap.ajouterRef(refInt2, SORTE.INT);
        heap.affecterTas(refInt2, 1, 8);
        heap.retirerTas(refInt2, SORTE.INT);

        assertThat(heap.valeurTas(refInt2, 1), is(8));
    }

    @Test
    public void toString__print() {
        for (int i = 0; i < 14; i++) {
            heap.affecterTas(refInt15, i, i + 1);
            heap.affecterTas(refBoolean20, i, i%2 == 0);
        }
        heap.affecterTas(refInt2, 0, 798);
        heap.affecterTas(refInt2, 1, -35);

        System.out.println(heap);
    }
}