import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestConstructor {

    private  TestConstructor(){}

    private  MiniJajaParser parser;

    public TestConstructor(String folder,String nameFile) throws IOException {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);//System.out.println(path);
        //CharStream codePointCharStream = CharStreams.fromFileName(path);
        //MiniJajaLexer lexer = new MiniJajaLexer(codePointCharStream);
        //parser = new MiniJajaParser(new CommonTokenStream(lexer));
    }

    public MiniJajaParser getParser(){
        return this.parser;
    }
}
