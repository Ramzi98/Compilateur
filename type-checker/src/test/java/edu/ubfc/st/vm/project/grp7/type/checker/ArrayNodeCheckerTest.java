package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ArrayNodeCheckerTest {

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
    public void ArrayNodeTypeCheck() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        typeChecker.visit(node);
    }

    @Test(expected = IllFormedNodeException.class)
    public void ArrayNodeTypeCheck__withException__TypeNodeOmega() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.VOID).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(node);
    }

    @Test(expected = IllFormedNodeException.class)
    public void ArrayNodeTypeCheck__withException__SizeNotInt() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        BooleanNode numberNode = BooleanNode.builder().value(false).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();

        typeChecker.visit(node);
    }

    @Test(expected = IllFormedNodeException.class)
    public void ArrayNodeTypeCheck__withException__NodeExists() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("i").build();
        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(2).build();
        VarNode varNode = VarNode.builder().identifier(identvar2).typeMeth(typeMethNode).expression(numberNode2).build();
        ArrayNode node = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();
        DeclsNode declsNode = DeclsNode.builder().decl(node).decls(declsnil).build();
        DeclsNode declsNode2 = DeclsNode.builder().decl(varNode).decls(declsNode).build();
        typeChecker.visit(declsNode2);
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
}
