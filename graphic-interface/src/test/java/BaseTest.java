import edu.ubfc.st.vm.project.grp7.mjj.parser.MiniJajaListenerImpl;
import edu.ubfc.st.vm.project.grp7.mjj.parser.MiniJajaParser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;

public class BaseTest {

    protected MiniJajaParser parser;
    protected MiniJajaListenerImpl listener;
    protected ParseTreeWalker walker;

    @Before
    public void setup() {
        walker = new ParseTreeWalker();
        listener = new MiniJajaListenerImpl();

    }
}
