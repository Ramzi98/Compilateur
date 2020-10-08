package java.edu.ubfc.st.vm.project.grp7.stack;

public interface Stack {

    public void push(Quadruplet quadruplet);

    public void pop();

    public void swap();

    public Quadruplet findQuadrupletInStack(String ident);

    public void editQuadrupletInStack(String ident, Quadruplet quadruplet);
}
