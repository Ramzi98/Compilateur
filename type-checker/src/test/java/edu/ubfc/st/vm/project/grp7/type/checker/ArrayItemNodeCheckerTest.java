package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ArrayItemNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    SymbolDictionnary symbolDictionnary;

    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();
        typeChecker.setPass(Pass.FIRST_PASS);
        symbolDictionnary = new SymbolDictionnary();
        typeChecker.setDataDictionnary(symbolDictionnary);

    }

    @Test
    public void ArrayItemNodeTypeCheck() throws IOException, IllFormedNodeException {


        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        ArrayItemNode nodeArray = ArrayItemNode.builder().identifier(identvar).expression(numberNode).build();


        PlusNode plus = PlusNode.builder().leftOperand(nodeArray).rightOperand(numberNode).build();

        InstrsNode instrMain = InstrsNode.builder().instruction(plus).instrs(instrsnil).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(declsnil).build();
        MainNode mainNode = MainNode.builder().vars(varnil).instrs(instrMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();
        typeChecker.visit(classeNode1);
    }


    InstrsNode instrsnil = new InstrsNode() {
        @Override
        public MiniJajaNode instruction() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    };
    VarsNode varnil = new VarsNode() {
        @Override
        public MiniJajaNode var() {
            return null;
        }

        @Override
        public VarsNode vars() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    };

    HeadersNode enil = new HeadersNode() {
        @Override
        public HeaderNode header() {
            return null;
        }

        @Override
        public HeadersNode headers() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    };
    DeclsNode declsnil = new DeclsNode() {
        @Override
        public MiniJajaNode decl() {
            return null;
        }

        @Override
        public DeclsNode decls() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    } ;
}
