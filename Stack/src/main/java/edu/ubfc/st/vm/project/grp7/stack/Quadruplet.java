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

    public String getType() {
        return type;
    }

    public Sorte getSorte() {
        return sorte;
    }

    public double getVal() {
        return val;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSorte(Sorte sorte) {
        this.sorte = sorte;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
