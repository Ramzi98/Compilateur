package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClasseNodeCheckerTest {
    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){

        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();

    }

    @Test
    public void ClasseNodeTypeCheker__FirstPass() throws TypeCheckerException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("j").build();
        IdentNode identvar3 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode2 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar3).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode1).decls(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode2).instrs(null).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();
        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

        assertThat(symbolDictionnary.find(identclasse.value()),is(-1));
        assertThat(symbolDictionnary.find(identvar2.value()),is(-1));

    }

    @Test(expected = TypeCheckerException.class)
    public void ClasseNodeTypeCheker__FirstPass__withException__InMainScope() throws TypeCheckerException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().line(2).column(5).value("j").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode2 = VarNode.builder().line(5).column(9).typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode3 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar2).expression(numberNode2).build();
        VarsNode varsNode3 = VarsNode.builder().var(varNode3).vars(varsNode2).build();

        MainNode mainNode = MainNode.builder().vars(varsNode3).instrs(null).build();
        ClasseNode classeNode1 = ClasseNode.builder().line(1).column(1).identifier(identclasse).decls(null).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();
    }

    @Test(expected = TypeCheckerException.class)
    public void ClasseNodeTypeCheker__FirstPass__withException__InClassScope() throws TypeCheckerException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar2 = IdentNode.builder().value("j").build();
        IdentNode identvar3 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode2 = NumberNode.builder().value(3).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar3).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        VarNode varNode2 = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode2).build();
        VarsNode varsNode2 = VarsNode.builder().var(varNode2).vars(varsNode).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar2).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode2).decls(null).build();

        MainNode mainNode = MainNode.builder().vars(varsNode1).instrs(null).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }


}
