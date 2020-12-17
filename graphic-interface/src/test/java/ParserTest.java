import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MethodNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.VarNode;
import edu.ubfc.st.vm.project.grp7.tool.yaml.MiniJajaAstToYamlVisitor;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ParserTest extends BaseTest{

    private MiniJajaAstToYamlVisitor visitor;
    private OutputStream out;




    @Test
    public void parser1Test() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","1");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);

        compareOutputWithYamlString(getExpectedFile("1"));

    }

    @Test
    public void parserQuickSortTest() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","quicksort");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);

        compareOutputWithYamlString(getExpectedFile("quicksort"));

    }


    @Test
    public void parserSynonymieTest() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","synonymie");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);



        compareOutputWithYamlString(getExpectedFile("synonymie"));
    }

    @Test
    public void parserTasTest() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","tas");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);

        compareOutputWithYamlString(getExpectedFile("tas"));
    }

    @Test
    public void parserFactTest() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);


        compareOutputWithYamlString(getExpectedFile("fact"));
    }


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

    private void compareOutputWithYamlString(String yaml) throws Exception {
        this.generator.flush();
        assertThat(out.toString(), is(yaml));
    }
    
    private String getExpectedFile(String FileName) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get("src/resourceTest/ExpectedParserResults/"+FileName+".txt"));
        return  new String(encoded, StandardCharsets.UTF_8).replace("\r","");

    }

}
