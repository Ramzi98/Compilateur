package edu.ubfc.st.vm.project.grp7.memory;

public class Quadruplet {
    private final String id;
    private SORTE type;
    private OBJ nature;
    private Object val;

    public Quadruplet(String id, Object val, OBJ nature, SORTE type) {
        this.id = id;
        this.val = val;
        this.nature = nature;
        this.type = type;
    }

    public String id() {
        return this.id;
    }

    public Object val() {
        return this.val;
    }

    public void val(Object val) {
        this.val = val;
    }

    public OBJ nature() {
        return this.nature;
    }

    public void nature(OBJ nature) {
        this.nature = nature;
    }

    public SORTE type() {
        return this.type;
    }

    public void type(SORTE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "<%s, %s, %s, %s>",
                this.id,
                this.val,
                this.nature,
                this.type
        );
    }
}
