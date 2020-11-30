package edu.ubfc.st.vm.project.grp7.ast.visitor;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class AstToYamlVisitorTest {
    @Test
    public void basicTree__PrettyPrinter() throws Exception {
        String yaml = AstToYamlVisitor.astToYaml(new ExampleNode());
        printYAML(yaml);

        String expectedOutput =
                "ExampleNode:\n" +
                "  $1:\n" +
                "    ExampleLeaf:\n" +
                "      value: \"plik\"";

        assertThat(expectedOutput, is(yaml));
    }

    @Test(expected = IOException.class)
    public void nullGenerator__throwsException() throws Exception {
        new AstToYamlVisitor(null);
    }

    @Test
    public void nullNode__throwsException() throws Exception {
        ExampleNode node = null;
        String yaml = AstToYamlVisitor.astToYaml(node);
        printYAML(yaml);

        String expectedOutput = "null";
        assertThat(expectedOutput, is(yaml));
    }

    @Test
    public void nullChildNode__throwsException() throws Exception {
        EmbeddingNullNode node = new EmbeddingNullNode();
        String yaml = AstToYamlVisitor.astToYaml(node);
        printYAML(yaml);

        String expectedOutput =
                "EmbeddingNullNode:\n" +
                "  $1: null";

        assertThat(expectedOutput, is(yaml));
    }

    @Test
    public void nullLeaf__throwsException() throws Exception {
        ExampleLeaf leaf = null;
        String yaml;
        String expectedOutput = "null";

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             YAMLGenerator generator = new YAMLFactory()
                     .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                     .createGenerator(out)
        ){
            AstToYamlVisitor visitor = new AstToYamlVisitor(generator);
            visitor.visit(leaf);
            generator.flush();
            yaml = out.toString();
        }

        assertThat(expectedOutput, is(yaml));

        printYAML(yaml);
    }


    public class EmbeddingNullNode implements ASTNode {
        @Override
        public int line() {
            return 1;
        }

        @Override
        public int column() {
            return 0;
        }

        @Override
        public ASTNode children(int n) throws IndexOutOfBoundsException {
            switch (n) {
                case 0 : return null;
                default: throw new IndexOutOfBoundsException();
            }
        }
    }

    public class ExampleNode implements ASTNode {
        @Override
        public int line() {
            return 1;
        }

        @Override
        public int column() {
            return 0;
        }

        @Override
        public ASTNode children(int n) throws IndexOutOfBoundsException {
            switch (n) {
                case 0 : return new ExampleLeaf();
                default: throw new IndexOutOfBoundsException();
            }
        }
    }

    public class ExampleLeaf implements ASTLeaf<String> {
        @Override
        public String value() {
            return "plik";
        }

        @Override
        public int line() {
            return 1;
        }

        @Override
        public int column() {
            return 4;
        }
    }


    public static void printYAML(String yaml) {
        System.out.println("<================  YAML  ================>");
        System.out.println(yaml);
        System.out.println("<========================================>");
    }
}