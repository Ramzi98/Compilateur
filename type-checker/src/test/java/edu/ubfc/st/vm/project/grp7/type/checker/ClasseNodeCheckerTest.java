package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClasseNodeCheckerTest {
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
    public void ClasseNodeTypeCheker__FirstPass() throws IOException, IllFormedNodeException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("j").build();
        IdentNode identvar3 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        VarNode varNode2 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar3).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(varnil).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode1).decls(declsnil).build();

        MainNode mainNode = MainNode.builder().vars(varsNode2).instrs(instrsnil).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();
        typeChecker.visit(classeNode1);

        assertThat(symbolDictionnary.find(identclasse.value()),is(0));
        assertThat(symbolDictionnary.find(identvar3.value()),is(-1));
        assertThat(symbolDictionnary.find(identvar2.value()),is(-1));
        assertThat(symbolDictionnary.find(identvar1.value()),is(-1));
    }
/*
    @Test
    public void BClasseNodeTypeCheker__SecondePass() throws IOException, IllFormedNodeException {

    }
*/
    @Test(expected = IllFormedNodeException.class)
    public void ClasseNodeTypeCheker__FirstPass__withException__InMainScope() throws IOException, IllFormedNodeException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().line(2).column(5).value("j").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        VarNode varNode2 = VarNode.builder().line(5).column(9).typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode3 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode3 = VarsNode.builder().var(varNode3).vars(varsNode2).build();

        MainNode mainNode = MainNode.builder().vars(varsNode3).instrs(instrsnil).build();
        ClasseNode classeNode1 = ClasseNode.builder().line(1).column(1).identifier(identclasse).decls(declsnil).methmain(mainNode).build();

        typeChecker.visit(classeNode1);

    }

    @Test(expected = IllFormedNodeException.class)
    public void ClasseNodeTypeCheker__FirstPass__withException__InClassScope() throws IOException, IllFormedNodeException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("j").build();
        IdentNode identvar3 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar3).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        VarNode varNode2 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar2).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(varnil).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode2).decls(declsnil).build();

        MainNode mainNode = MainNode.builder().vars(varsNode1).instrs(instrsnil).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();
        typeChecker.visit(classeNode1);

    }

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
}
