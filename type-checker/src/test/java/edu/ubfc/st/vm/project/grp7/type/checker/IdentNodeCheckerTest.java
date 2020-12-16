package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

public class IdentNodeCheckerTest {

    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){

        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();

    }

    @Test(expected = TypeCheckerException.class)
    public void IdentNodeTypeCheker__Second_Pass() throws TypeCheckerException {

        IdentNode identclasse = IdentNode.builder().value("C").build();
        IdentNode identvar1 = IdentNode.builder().value("i").build();
        IdentNode identvar4 = IdentNode.builder().value("k").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.INT).build();
        NumberNode numberNode = NumberNode.builder().value(2).build();
        NumberNode numberNode4 = NumberNode.builder().value(4).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar4).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();

        VarNode varNode1 = VarNode.builder().identifier(identvar1).typeMeth(typeMethNode).expression(numberNode4).build();
        VarsNode varsNode1 = VarsNode.builder().var(varNode1).vars(null).build();
        DeclsNode declsNode = DeclsNode.builder().decl(varsNode1).decls(null).build();



        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();
        ClasseNode classeNode1 = ClasseNode.builder().identifier(identclasse).decls(declsNode).methmain(mainNode).build();

        typeChecker = new TypeCheckerImpl(classeNode1);
        typeChecker.typeCheck();

    }


}
