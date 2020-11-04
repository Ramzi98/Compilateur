package edu.ubfc.st.vm.project.grp7.stack;

public class Quadruplet {
    private String ident, type;
    private Sorte sorte;
    private double val;

    public Quadruplet(String ident, double val, Sorte sorte, String type) {
        this.ident = ident;
        this.val = val;
        this.sorte = sorte;
        this.type = type;
    }

    public String getIdent() {
        return ident;
    }
}
