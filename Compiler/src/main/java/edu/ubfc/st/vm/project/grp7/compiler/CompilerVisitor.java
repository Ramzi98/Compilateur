package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcPopNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CompilerVisitor extends MiniJajaASTVisitor {
    private ArrayList<JajaCodeNode> jajaCodeNodes = new ArrayList<>();
    private ArrayList<HashMap<MiniJajaNode, Integer>> minijajaNodes= new ArrayList<HashMap<MiniJajaNode, Integer>>();
    private Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<HashMap<MiniJajaNode, Integer>>();
    int line = 1;
    int column = 0;
    int n = 1;


    @Override
    public void visit(ClasseNode node) {
        MiniJajaNode nodeDecls = node.decls();
        MiniJajaNode nodeMain = node.methmain();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        h.put(node,0);
        minijajaNodes.add(h);


        stack.push(h);

        JcInitNode jcInitNode = JcInitNode
                                .builder()
                                .line(jajaCodeNodes.size())
                                .column(1)
                                .build();

        JcPopNode jcPopNode = JcPopNode
                                .builder()
                                .column(1)
                                .build();

        JcStopNode jcStopNode = JcStopNode
                .builder()
                .column(1)
                .build();

        jajaCodeNodes.add(jcInitNode);

        try {

            nodeDecls.accept(this);
            nodeMain.accept(this);
            nodeDecls.accept(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        jajaCodeNodes.add(jcPopNode);
        jajaCodeNodes.add(jcStopNode);




    }

    @Override
    public void visit(IdentNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(DeclsNode node){

        MiniJajaNode nodeDecl = node.decl();
        MiniJajaNode nodeDecls = node.decls();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        h.put(node,0);
        minijajaNodes.add(h);
        stack.push(h);

        try {
            nodeDecl.accept(this);


            nodeDecls.accept(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp = node.expression();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        h.put(node,0);
        minijajaNodes.add(h);
        stack.push(h);

        try {

            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int topstack = (int) newhashMap.values().toArray()[0];
            h.replace(node,topstack+1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            //Works

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WriteNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WriteLnNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(StringNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        //Works
        h.put(node,1);
        minijajaNodes.add(h);
        stack.push(h);


    }

    @Override
    public void visit(ArrayItemNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(TypeMethNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(TypeNode node) throws IllFormedNodeException, IOException {

    }
}
