import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.AppelENode;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.io.IOException;

public class MiniJajaTestIdentNode  {

    MiniJajaParser parser;


    @Test
    public void check__Ident__Correct() throws IOException {

        TestConstructor testConstructor = new TestConstructor("identifier","IdentidierVisitorText1");
        parser = testConstructor.getParser();
        parser.ident();

        //assertThat(parser.ident(),is("Hello"));

    }
}
