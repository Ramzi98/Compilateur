package edu.ubfc.st.vm.project.grp7.memory;

public interface Stack {
     void empiler(Quadruplet q);
     void depiler();
     void echanger();
     void ajouterRef(Object ref, SORTE type);
     void pushStack(String scope);
     void popStack();
     Quadruplet removeFirst(String id);
     void identVal(String id, SORTE t, int s);
     boolean isEmpty();

     Object val(String id);
     Object valT(String id);
     OBJ object(String id);
     SORTE sorte(String id);

}
