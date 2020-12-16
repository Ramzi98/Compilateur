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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ParserTest extends BaseTest{

    private MiniJajaAstToYamlVisitor visitor;
    private OutputStream out;




    @Test
    public void parserFactTest() throws Exception {
        stringRelatedVisitor__setUp();

        TestConstructor testConstructor = new TestConstructor("ProjectExample","fact");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.classe());

        ClasseNode classeNode = (ClasseNode) listener.getRoot();
        classeNode.accept(this.visitor);

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
}
