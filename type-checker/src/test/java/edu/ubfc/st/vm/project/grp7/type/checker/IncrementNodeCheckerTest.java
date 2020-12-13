package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class IncrementNodeCheckerTest {
    TypeChecker typeChecker;
    SymbolDictionnary symbolDictionnary;
    TypeCheckerVisitor typeCheckerVisitor;

    @Before
    public void start(){
        typeCheckerVisitor = new TypeCheckerVisitor();
        symbolDictionnary = new SymbolDictionnary();
    }

    @Test(expected = IllFormedNodeException.class)
    public void IncrementNodeTypeCheker__Exception() throws IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();
        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();

        VarNode varNode = VarNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(booleanNode).build();
        VarsNode varsNode = VarsNode.builder().var(varNode).vars(null).build();

        IncrementNode incrementNode = IncrementNode.builder().identifier(identvar1).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();
    }

    @Test(expected = IllFormedNodeException.class)
    public void IncrementNodeTypeCheker__ArrayItem__Exception__Identifier__NOT_INT() throws IOException, IllFormedNodeException {

        IdentNode identvar1 = IdentNode.builder().value("i").build();

        TypeMethNode typeMethNode = TypeMethNode.builder().value(TypeMethNode.TypeMeth.BOOLEAN).build();

        BooleanNode booleanNode = BooleanNode.builder().value(Boolean.TRUE).build();

        NumberNode numberNode = NumberNode.builder().value(6).build();
        NumberNode numberNode2 = NumberNode.builder().value(2).build();

        ArrayNode arrayNode = ArrayNode.builder().typeMeth(typeMethNode).identifier(identvar1).expression(numberNode).build();
        ArrayItemNode arrayItemNode = ArrayItemNode.builder().identifier(identvar1).expression(numberNode2).build();

        VarsNode varsNode = VarsNode.builder().var(arrayNode).vars(null).build();


        IncrementNode incrementNode = IncrementNode.builder().identifier(arrayItemNode).build();

        InstrsNode instrsNode = InstrsNode.builder().instruction(incrementNode).instrs(null).build();


        MainNode mainNode = MainNode.builder().vars(varsNode).instrs(instrsNode).build();

        typeChecker = new TypeCheckerImpl(mainNode);
        typeChecker.typeCheck();

    }


}
