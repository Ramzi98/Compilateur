package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
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
    public void CstNodeTypeCheck() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();

        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(cstNode);

    }


    @Test(expected = IllFormedNodeException.class)
    public void VarNodeTypeCheck__IncompatibleType__WithException() throws IOException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();
        TypeNode typeNode = TypeNode.builder().value(TypeNode.Type.BOOLEAN).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        CstNode cstNode = CstNode.builder().type(typeNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(cstNode);

    }
}
