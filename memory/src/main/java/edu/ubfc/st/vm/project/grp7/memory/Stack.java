package edu.ubfc.st.vm.project.grp7.memory;

public interface Stack {
     void empiler(Quadruplet q);
     void depiler();
     void echanger();
     void ajouterRef(Object ref, SORTE type);
}
