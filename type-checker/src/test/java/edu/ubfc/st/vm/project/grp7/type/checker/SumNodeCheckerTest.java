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

public class SumNodeCheckerTest {

    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test(expected = IllFormedNodeException.class)
    public void SumNodeTypeCheker__Exception__Type_Identifier_NOT_INT() throws IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(booleanNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        SumNode sumNode = SumNode.builder().identifier(identvar1).expression(numberNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(instrsnil).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeChecker(mainNode);
        typeChecker.setsymbolDictionnary(symbolDictionnary);
        typeChecker.typeCheck();


    }

    @Test(expected = IllFormedNodeException.class)
    public void SumNodeTypeCheker__Exception__Type_Expression_NOT_INT() throws IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        TypeMethNode typeMethNode2 = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();
        NumberNode numberNode = NumberNode.builder().value(6).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode2).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(varnil).build();

        SumNode sumNode = SumNode.builder().identifier(identvar1).expression(booleanNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(sumNode).instrs(instrsnil).build();


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
}
