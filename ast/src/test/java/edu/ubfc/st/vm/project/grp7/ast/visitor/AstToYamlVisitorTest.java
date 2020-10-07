package edu.ubfc.st.vm.project.grp7.ast.visitor;

import edu.ubfc.st.vm.project.grp7.ast.ASTLeaf;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import org.junit.Test;


public class AstToYamlVisitorTest {
    @Test
    public void test() throws Exception {
        String yaml = AstToYamlVisitor.astToYaml(new ExampleNode());

        System.out.println("<================  YAML  ================>");
        System.out.println(yaml);
        System.out.println("<========================================>");
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
}