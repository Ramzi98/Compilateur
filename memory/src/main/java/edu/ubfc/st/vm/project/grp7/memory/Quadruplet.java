package edu.ubfc.st.vm.project.grp7.memory;

public class Quadruplet {
    private String id;
    private SORTE type;
    private OBJ nature;
    private Object val;

    public Quadruplet(String id, Object val, OBJ nature, SORTE type) {
        this.id = id == null ? null : id.trim();
        this.val = val;
        this.nature = nature;
        this.type = type;
    }

    public String id() {
        return this.id;
    }

    public void id(String id) throws IllegalStateException, IllegalArgumentException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("null or empty name for an identVal");
        }
        if (this.id == null) {
            this.id.trim();
        } else {
            throw new IllegalStateException("Error modifying a named var");
        }
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
                this.id == null ? "w" : this.id,
                this.val == null ? "w" : this.toString(),
                this.nature == null ? "w" : this.toString(),
                this.type == null ? "w" : this.toString()
        );
    }
}
