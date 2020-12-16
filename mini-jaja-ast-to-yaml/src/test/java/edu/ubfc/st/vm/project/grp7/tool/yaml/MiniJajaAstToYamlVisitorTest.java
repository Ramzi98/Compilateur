package edu.ubfc.st.vm.project.grp7.tool.yaml;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import edu.ubfc.st.vm.project.grp7.ast.ASTNode;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.ast.visitor.ASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import org.junit.After;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.*;

public class MiniJajaAstToYamlVisitorTest {
    private MiniJajaAstToYamlVisitor visitor;

    /******************************  Error Throwing Checks  ******************************/

    @Test
    public void withUnhandledASTNode__whenVisit__thenThrowException() throws Exception {
        errorThrowingVisitor__SetUp();
        ASTNode node = new ASTNode() {
            @Override
            public int line() {
                return 35;
            }

            @Override
            public int column() {
                return 62;
            }

            @Override
            public ASTNode children(int n) throws IndexOutOfBoundsException {
                return null;
            }
        };

        try {
            node.accept(this.visitor);
            throw new AssertionError("Expected an IllFormedNodeException");
        } catch (IllFormedNodeException e){
            assertThat(e.line(), is(35));
            assertThat(e.column(), is(62));
        }
    }

    private void errorThrowingVisitor__SetUp() throws IOException {
        visitor = new MiniJajaAstToYamlVisitor(new YAMLFactory().createGenerator(new OutputStream() {
            @Override
            public void write(int i) {}
        }));
    }


    /*********************  Simple Nodes Compared with Yaml entries  *********************/

    private OutputStream out;

    @Test
    public void withNumberNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        NumberNode number = NumberNode.builder()
                .value(17)
                .build();

        number.accept(this.visitor);

        compareOutputWithYamlString("NumberNode:\n  value: 17");
    }

/*
    @Test
    public void withVarsNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        NumberNode number = NumberNode.builder()
                .value(17)
                .build();

        number.accept(this.visitor);
        IdentNode ident = IdentNode.builder().value("hey").build();
        TypeMethNode type = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        VarNode var = VarNode.builder().identifier(ident).typeMeth(type).expression(number).build();
        VarsNode vars = VarsNode.builder().var(var).vars(null).build();
        vars.accept(this.visitor);
        flushAndDisplayYaml();
    }


*/

    /*
    @Test
    public void withAddNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        NumberNode number = NumberNode.builder()
                .value(17)
                .build();
        NumberNode number2 = NumberNode.builder().value(18).build();
        PlusNode plusNode = PlusNode.builder().leftOperand(number).rightOperand(number2).build();

        plusNode.accept(this.visitor);
        flushAndDisplayYaml();


       compareOutputWithYamlString("PlusNode:\n   leftOperand:\n    NumberNode:\n      value: 17\n  rightOperand:\n    NumberNode:\n      value: 18");
    }


     */

    @Test
    public void withTypeMethNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        TypeMethNode type = TypeMethNode.builder()
                .value(TypeMethNode.TypeMeth.VOID)
                .build();

        type.accept(this.visitor);

        compareOutputWithYamlString("TypeMethNode:\n  type: VOID");
    }

    @Test
    public void withTypeNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        TypeNode type = TypeNode.builder()
                .value(TypeNode.Type.INT)
                .build();

        type.accept(this.visitor);

        compareOutputWithYamlString("TypeNode:\n  type: INT");
    }

    @Test
    public void withIdentNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        IdentNode ident = IdentNode.builder()
                .value("plop")
                .build();

        ident.accept(this.visitor);

        compareOutputWithYamlString("IdentNode:\n  identifier: plop");
    }

    @Test
    public void withStringNode__whenVisit__thenYamlOK() throws Exception {
        stringRelatedVisitor__setUp();

        StringNode string = StringNode.builder()
                .value("kayak")
                .build();

        string.accept(this.visitor);

        compareOutputWithYamlString("StringNode:\n  chaine: \"kayak\"");
    }

    @Test
    public void withWriteNode__ofStringNode__whenVisit__thenYamlOk() throws Exception {
        stringRelatedVisitor__setUp();

        WriteNode node = WriteNode.builder()
                .printable(StringNode.builder()
                        .value("plik")
                        .build()
                ).build();

        node.accept(this.visitor);

        compareOutputWithYamlString("WriteNode:\n  printable:\n    StringNode:\n      chaine: \"plik\"");
    }

    @Test
    public void withWriteNode__ofIdentNode__whenVisit__thenYamlOk() throws Exception {
        stringRelatedVisitor__setUp();

        WriteNode node = WriteNode.builder()
                .printable(IdentNode.builder()
                        .value("plik")
                        .build()
                ).build();

        node.accept(this.visitor);

        compareOutputWithYamlString("WriteNode:\n  printable:\n    IdentNode:\n      identifier: plik");
    }

    @Test
    public void withWriteLnNode__ofIdentNode__whenVisit__thenYamlOk() throws Exception {
        stringRelatedVisitor__setUp();

        WriteLnNode node = WriteLnNode.builder()
                .printable(IdentNode.builder()
                        .value("plik")
                        .build()
                ).build();

        node.accept(this.visitor);

        compareOutputWithYamlString("WriteLnNode:\n  printable:\n    IdentNode:\n      identifier: plik");
    }


    /*****************  Complex Nodes Compared with Yaml resources files  ****************/



    /**************************  SetUp et Comparing functions  **************************/

    private YAMLGenerator generator;

    private void stringRelatedVisitor__setUp() throws IOException {
        this.out = new ByteArrayOutputStream();

        // closed in compareOutputWithYaml or compareOutputWithYamlString
        this.generator = new YAMLFactory()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .createGenerator(out);
        this.visitor = new MiniJajaAstToYamlVisitor(generator);
    }

    private void compareOutputWithYamlFile(String url) throws Exception {
        flushAndDisplayYaml();
        assertThat(out.toString(), is(resourceString(url)));
        closeIO();
    }

    private void compareOutputWithYamlString(String yaml) throws Exception {
        flushAndDisplayYaml();
        assertThat(out.toString(), is(yaml));
        closeIO();
    }

    private void flushAndDisplayYaml() throws IOException {
        this.generator.flush();
        System.out.println("<================  YAML  ================>");
        System.out.println(this.out.toString());
        System.out.println("<========================================>");
    }

    private void closeIO() throws IOException {
        // opened in stringRelatedVisitor__setUp
        this.generator.close();
        this.out.close();
    }

    static private String resourceString(String resource) throws IOException {
        StringBuilder result = new StringBuilder();
        try(InputStreamReader in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource))) {
            char[] buffer = new char[1024];
            for(int read = in.read(buffer) ; read != -1 ; read = in.read(buffer)) {
                result.append(buffer, 0, read);
            }
        }
        return result.toString().trim();
    }


    @After
    public void tearDown() {
        this.out = null;
        this.visitor = null;
        this.generator = null;
    }

}