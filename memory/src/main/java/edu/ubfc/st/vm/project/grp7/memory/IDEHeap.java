package edu.ubfc.st.vm.project.grp7.memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: 26/11/2020 Implémentation réelle du temps prenant en compte le cours : blocs en puissances de 2, Garbage collector, ...
public class IDEHeap implements Heap {
    private final Map<UUID, Object[]> objects;
    private final Map<UUID, Integer> refs;
    public IDEHeap() {
        this.objects = new HashMap<>();
        this.refs = new HashMap<>();
    }

    @Override
    public Object ajouterRef(Object ref, SORTE type) throws IllegalArgumentException {
        if (ref instanceof UUID && refs.containsKey(ref)) {
            refs.put((UUID) ref, refs.get(ref) + 1);
            return ref;
        }
            throw new IllegalArgumentException("Unknown ref");
    }

    @Override
    public void affecterTas(Object ref, int index, Object val) throws IllegalArgumentException, IndexOutOfBoundsException {
           if (objects.containsKey(ref)) {
               objects.get(ref)[index] = val;
           } else {
               throw new IllegalArgumentException("Invalid ref on array");
           }
    }

    @Override
    public void retirerTas(Object ref, SORTE type) throws IllegalArgumentException {
        if (ref instanceof UUID) {
            if (refs.containsKey(ref)) {
                int descriptors = refs.get(ref);
                if (--descriptors == 0) {
                    objects.remove(ref);
                    refs.remove(ref);
                }
            } else {
                throw new IllegalArgumentException("No UUID match with this ref");
            }
        } else {
            throw new IllegalArgumentException("The ref doesn't exist in the heap");
        }
    }

    @Override
    public Object CreerTas(Object val, SORTE type) throws IllegalArgumentException {
        if (val instanceof Integer && (Integer) val > 0) {
            int size = (Integer) val;
            UUID uuid = UUID.randomUUID();
            refs.put(uuid, 1);
            objects.put(uuid, new Object[size]);
            return uuid;
        } else {
            throw new IllegalArgumentException("Array Size must be a positive integer");
        }
    }

    @Override
    public Object valeurTas(Object ref, int index) throws IllegalArgumentException, IndexOutOfBoundsException {
        if(ref instanceof UUID) {
            if (objects.containsKey(ref)) {
                return objects.get(ref)[index];
            } else {
                throw new IllegalArgumentException("Unknown ref");
            }
        } else {
            throw new IllegalArgumentException("Incorrect ref type");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{\n\t");
        objects.forEach((k, v) -> {
            builder.append(k.toString()).append(" : [");
            StringBuilder insideBuilder = new StringBuilder();
            for (Object e : v) {
                insideBuilder.append(e).append(", ");
            }
            int length = insideBuilder.length();
            builder.append(insideBuilder.delete(length - 2, length)).append("]\n\t");
        });
        int length = builder.length();
        return builder.delete(length-1, length).append("}\n").toString();
    }
}
