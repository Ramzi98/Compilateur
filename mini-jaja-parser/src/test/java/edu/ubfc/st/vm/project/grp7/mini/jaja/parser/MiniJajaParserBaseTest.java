package edu.ubfc.st.vm.project.grp7.mini.jaja.parser;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;

public class MiniJajaParserBaseTest {
    protected MiniJajaParser parser;
    protected MiniJajaListenerImpl listener;
    protected ParseTreeWalker walker;

    @Before
    public void setup() {
        walker = new ParseTreeWalker();
        listener = new MiniJajaListenerImpl();
    }
}
