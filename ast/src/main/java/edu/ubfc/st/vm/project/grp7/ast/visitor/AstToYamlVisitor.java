package edu.ubfc.st.vm.project.grp7.ast.visitor;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AstToYamlVisitor implements ASTVisitor {
    private final YAMLGenerator generator;
    public AstToYamlVisitor(YAMLGenerator generator) throws IOException {
        this.generator = generator;
        if (this.generator == null) {
            throw new IOException("Impossible to serialize Yaml without a valid generator");
        }
    }

    @Override
    public void visit(ASTNode node) throws Exception {
        if (node == null) {
            generator.writeNull();
        }else if (node instanceof ASTLeaf) {
            visit((ASTLeaf)node);
        } else {
            generator.writeStartObject();
                generator.writeObjectFieldStart(node.getClass().getSimpleName());
                int i = 0;
                while (i < Integer.MAX_VALUE) {
                    try {
                        ASTNode child = node.children(i);
                        generator.writeFieldName("$" + ++i);
                        if (child == null) {
                            generator.writeNull();
                        } else {
                            visit(child);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
                generator.writeEndObject();
            generator.writeEndObject();
        }
    }

    public void visit(ASTLeaf leaf) throws Exception {
        if (leaf == null) {
            generator.writeNull();
        } else {
            generator.writeStartObject();
                generator.writeObjectFieldStart(leaf.getClass().getSimpleName());
                    generator.writeObjectField("value", leaf.value());
                generator.writeEndObject();
            generator.writeEndObject();
        }
    }


    static String astToYaml(ASTNode tree) throws Exception {
        String yaml;
        try (   ByteArrayOutputStream out = new ByteArrayOutputStream();
                YAMLGenerator generator = new YAMLFactory()
                        .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                        .createGenerator(out)
        ){
            AstToYamlVisitor visitor = new AstToYamlVisitor(generator);
            visitor.visit(tree);
            generator.flush();
            yaml = out.toString();
        }
        return yaml;
    }

}
