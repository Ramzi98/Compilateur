package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.IDEStack;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IdentNodeCheckerTest {

    TypeCheckerVisitor typeChecker;
    SymbolDictionnary symbolDictionnary;

    @Before
    public void start(){

        typeChecker = new TypeCheckerVisitor();
        typeChecker.setPass(Pass.SECOND_PASS);
        symbolDictionnary = new SymbolDictionnary();
        typeChecker.setDataDictionnary(symbolDictionnary);

    }

    @Test(expected = IllFormedNodeException.class)
    public void IdentNodeTypeCheker__Second_Pass() throws IOException, IllFormedNodeException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("j").build();
        IdentNode identvar4 = IdentNode.builder().value("k").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar4).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(instrsnil).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar1).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(varnil).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode1).decls(declsnil).build();



        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();
        typeChecker.visit(classeNode1);

        assertThat(symbolDictionnary.find(identclasse.value()),is(0));
        assertThat(symbolDictionnary.find(identvar2.value()),is(-1));
        assertThat(symbolDictionnary.find(identvar1.value()),is(-1));
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
