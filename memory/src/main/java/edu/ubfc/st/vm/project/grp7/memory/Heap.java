package edu.ubfc.st.vm.project.grp7.memory;

public interface Heap {
    void affecterTas(Object ref, int index, Object val);
    void retirerTas(Object ref, SORTE type);
    Object CreerTas(Object val, SORTE type);
    Object valeurTas(Object val, int index);
}
