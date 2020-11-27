package edu.ubfc.st.vm.project.grp7.memory;

public interface Stack {
     void empiler(Quadruplet q);
     Quadruplet depiler();
     void echanger();
     void pushScope(String scope);
     void popScope();
     Quadruplet removeFirst(String id);
     Quadruplet peekFirst(String id);
     
     void identVal(String id, SORTE t, int s);
     boolean isEmpty();

     Object classVar(Object val);
}
