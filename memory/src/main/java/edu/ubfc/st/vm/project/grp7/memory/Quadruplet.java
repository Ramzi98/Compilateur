package edu.ubfc.st.vm.project.grp7.memory;

public class Quadruplet {
    private final String id;
    private final SORTE type;
    private final OBJ nature;
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

    public SORTE type() {
        return this.type;
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
