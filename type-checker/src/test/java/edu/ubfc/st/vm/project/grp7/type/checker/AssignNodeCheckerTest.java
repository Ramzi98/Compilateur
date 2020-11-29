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

public class AssignNodeCheckerTest {


    TypeChecker typeChecker;
    TypeCheckerVisitor typeCheckerVisitor;
    SymbolDictionnary symbolDictionnary;

    @Before
    public void start(){

        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();

    }

    @Test
    public void AssignNodeTypeCheck() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar1).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(instrsnil).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();
    }
    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithException__NotDeclared() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assign = AssignNode.builder().identifier(identvar).expression(numberNode2).build();
        typeChecker = new TypeChecker(assign);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


    }
    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__WithConstantException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(cstNode).vars(varnil).build();

        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(instrsnil).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();
        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();

    }

    @Test
    public void AssignNodeTypeCheck__WithMethodException() throws IOException, IllFormedNodeException {


        /* To see Later
        IdentNode identclasse = IdentNode.builder().value("C").build();

        TypeMethNode typeMeth = TypeMethNode.builder().line(1).column(0).value(TypeMethNode.TypeMeth.INT).build();

        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identf = IdentNode.builder().value("f").build();

        NumberNode expression = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().line(1).column(0).typeMeth(typeMeth).identifier(ident).expression(expression).build();

        IdentNode ident2 = IdentNode.builder().value("I").build();
        NumberNode expression2 = NumberNode.builder().value(2).build();

        TypeNode type = TypeNode.builder().line(1).column(0).value(TypeNode.Type.BOOLEAN).build();

        HeaderNode headerNode = HeaderNode.builder().line(1).column(0).type(type).identifier(ident).build();

        HeadersNode headersNode = HeadersNode.builder().line(1).column(10).header(headerNode).headers(enil).build();
        VarsNode varsNode3 = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode)
                .vars(varnil)
                .build();


        AssignNode assignNode = AssignNode.builder().identifier(ident2).expression(expression2).build();

        InstrsNode instsrNode = InstrsNode.builder().line(1).column(0).instruction(assignNode).instrs(instrsnil).build();


        MethodNode methodNode = MethodNode.builder()
                .headers(headersNode)
                .identifier(identf)
                .instrs(instsrNode)
                .typeMeth(typeMeth)
                .vars(varsNode3)
                .build();


        DeclsNode declsNode = DeclsNode.builder().decl(methodNode).decls(declsnil).build();

        AssignNode assignMethod = AssignNode.builder().identifier(identf).expression(expression).build();
        InstrsNode instrsMain = InstrsNode.builder().instruction(assignMethod).instrs(instrsnil).build();
        MainNode mainNode = MainNode.builder().vars(varnil).instrs(instrsMain).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();


        typeChecker = new TypeChecker(classeNode1);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();

        */


    }

    @Test(expected = IllFormedNodeException.class)
    public void AssignNodeTypeCheck__IncompatibleType__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        BooleanNode numberNode2 = BooleanNode.builder().value(false).build();
        AssignNode assignNode = AssignNode.builder().identifier(identvar1).expression(numberNode2).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(assignNode).instrs(instrsnil).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();
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

    private static final HeadersNode enil = new HeadersNode() {
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
