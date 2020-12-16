package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.VarNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class VarNodeCheckerTest {

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
    public void VarNodeTypeCheck() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(varNode);

    }

    @Test(expected = TypeCheckerException.class)
    public void VarNodeTypeCheck__TypeOmega__WithException() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.VOID).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(varNode);

    }

    @Test(expected = TypeCheckerException.class)
    public void VarNodeTypeCheck__IncompatibleType__WithException() throws IOException, TypeCheckerException, IllFormedNodeException {

        IdentNode identvar = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        NumberNode numberNode = NumberNode.builder().value(5).build();


        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar).expression(numberNode).build();
        typeChecker.visit(varNode);

    }

}

