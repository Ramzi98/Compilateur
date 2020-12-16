package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CstNodeCheckerTest {

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
    public void CstNodeTypeCheck() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();

        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(cstNode);

    }


    @Test(expected = TypeCheckerException.class)
    public void VarNodeTypeCheck__IncompatibleType__WithException() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(cstNode);

    }

    @Test(expected = TypeCheckerException.class)
    public void VarNodeTypeCheck__ExistingSymbol__WithException() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identCst = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        TypeMethNode typeNode2 = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();

        NumberNode numberNode = NumberNode.builder().value(2).build();

        IdentNode identVar = IdentNode.builder().value("i").build();
        VarNode varNode = VarNode.builder().typeMeth(typeNode2).identifier(identVar).expression(numberNode).build();
        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identCst).expression(numberNode).build();
        VarsNode varsNode2 = VarsNode.builder().var(cstNode).vars(null).build();
        VarsNode node = VarsNode.builder().var(varNode).vars(varsNode2).build();
        typeChecker.visit(node);

    }




}
