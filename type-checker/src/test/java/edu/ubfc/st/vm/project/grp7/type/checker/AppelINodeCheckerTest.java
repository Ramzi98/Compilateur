package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AppelINodeCheckerTest {


    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start() {
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }


    @Test
    public void AppelINodeTypeCheker() throws IOException, IllFormedNodeException {
        IdentNode identvar1 = IdentNode.builder().value("i").build();

        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(varNode).build();
        InstrsNode instrsReturn = InstrsNode.builder().instruction(returnNode).instrs(instrsnil).build();
        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsReturn).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(enil)
                .build();



        ListExpNode listExpNode = ListExpNode.builder().expression(numberNode).listexp(expnil).build();
        AppelINode appelINode = AppelINode.builder().identifier(identfonction).listexp(listExpNode).build();
        InstrsNode instrs = InstrsNode.builder().instruction(appelINode).instrs(instrsnil).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();
        MainNode mainNode = MainNode.builder().vars(varnil).instrs(instrs).build();



        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(declsnil).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeChecker(classeNode1);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


        assertThat(symbolDictionnary.find(identvar1.value()), is(-1));
    }

    @Test(expected = IllFormedNodeException.class)
    public void AppelINodeTypeCheker__WithException_NoIdentDeclared() throws IOException, IllFormedNodeException {
        IdentNode identvar1 = IdentNode.builder().value("i").build();

        IdentNode identheader = IdentNode.builder().value("j").build();
        IdentNode identfonction = IdentNode.builder().value("fonction").build();
        IdentNode fakeidentfonction = IdentNode.builder().value("func").build();
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        TypeNode type = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        ReturnNode returnNode = ReturnNode.builder().ret(varNode).build();
        InstrsNode instrsReturn = InstrsNode.builder().instruction(returnNode).instrs(instrsnil).build();
        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsReturn).build();

        HeaderNode headerNode = HeaderNode.builder()
                .type(type)
                .identifier(identheader)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .header(headerNode)
                .headers(enil)
                .build();



        ListExpNode listExpNode = ListExpNode.builder().expression(numberNode).listexp(expnil).build();
        AppelINode appelINode = AppelINode.builder().identifier(fakeidentfonction).listexp(listExpNode).build();
        InstrsNode instrs = InstrsNode.builder().instruction(appelINode).instrs(instrsnil).build();

        MethodNode methodNode = MethodNode.builder().typeMeth(typeMethNode).vars(varsNode).identifier(identfonction).headers(headersNode).instrs(instrsNode).build();
        MainNode mainNode = MainNode.builder().vars(varnil).instrs(instrs).build();



        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(declsnil).build();

        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeChecker(classeNode1);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


        assertThat(symbolDictionnary.find(identvar1.value()), is(-1));
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
    ListExpNode expnil = new ListExpNode() {

        @Override
        public Breakpoint breakpoint() {
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

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public MiniJajaNode expression() {
            return null;
        }

        @Override
        public ListExpNode listexp() {
            return null;
        }
    } ;
}
