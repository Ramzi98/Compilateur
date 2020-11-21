import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;

public class TestConstructor {

    private  TestConstructor(){}

    private  MiniJajaParser parser;
    private final String resourceTest = "src/resourceTest/";

    public TestConstructor(String folder,String nameFile) throws IOException {
        CharStream codePointCharStream = CharStreams.fromPath(Paths.get(resourceTest+folder+"/"+nameFile+".txt"));
        MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        parser = new MiniJajaParser(new CommonTokenStream(lexer));
    }

    public TestConstructor(String toParse) {
        CharStream stream = CharStreams.fromString(toParse);
        parser = new MiniJajaParser(new CommonTokenStream(new MiniJajaLexer(stream)));
    }

    public MiniJajaParser getParser(){
        return this.parser;
    }
}
