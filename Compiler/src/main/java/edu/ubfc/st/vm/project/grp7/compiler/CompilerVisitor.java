package edu.ubfc.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcPopNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.CompilerException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CompilerVisitor extends MiniJajaASTVisitor {
    private ArrayList<JajaCodeNode> jajaCodeNodes = new ArrayList<>();
    private  ArrayList<HashMap<MiniJajaNode, Integer>> minijajaNodes= new ArrayList<HashMap<MiniJajaNode, Integer>>();
    private Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<HashMap<MiniJajaNode, Integer>>();
    private Mode compilemode = Mode.NORMALE;
    private int nheader;

    int n = 1;

    public  ArrayList<HashMap<MiniJajaNode, Integer>> getMinijajaNodes() {
        return minijajaNodes;
    }
    public ArrayList<JajaCodeNode> getJajaCodeNodes() {
        return jajaCodeNodes;
    }

    public void setStack(Stack<HashMap<MiniJajaNode, Integer>> stack) {
        this.stack = stack;
    }

    public void setMinijajaNodes(ArrayList<HashMap<MiniJajaNode, Integer>> minijajaNodes) {
        this.minijajaNodes = minijajaNodes;
    }

    public void setJajaCodeNodes(ArrayList<JajaCodeNode> jajaCodeNodes) {
        this.jajaCodeNodes = jajaCodeNodes;
    }

    @Override
    public void visit(ClasseNode node) throws CompilerException {
        MiniJajaNode nodeDecls = node.decls();
        MiniJajaNode nodeMain = node.methmain();
        HashMap<MiniJajaNode, Integer> newhashMap;

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        JcInitNode jcInitNode = JcInitNode.builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();
        jajaCodeNodes.add(jcInitNode);
        h.replace(node,n+1);
        minijajaNodes.set(minijajaNodes.indexOf(h),h);
        stack.set(stack.indexOf(h),h);

            try {

                nodeDecls.accept(this);
                //ndss
                newhashMap = stack.pop();
                int ndss = (int) newhashMap.values().toArray()[0];
                h.replace(node,n+ndss+1);
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);

                nodeMain.accept(this);
                //nmma
                newhashMap = stack.pop();
                int nmma = (int) newhashMap.values().toArray()[0];
                h.replace(node,n+ndss+nmma+1);
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);

                /********************Retrait***********************/
                compilemode = Mode.RETRAIT;

                nodeDecls.accept(this);
                //nrdss
                newhashMap = stack.pop();
                int nrdss = (int) newhashMap.values().toArray()[0];
                h.replace(node,ndss+nmma+nrdss+3);
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);

                compilemode = Mode.NORMALE;
                /*****************************************************/


            } catch (Exception e) {
                throw new CompilerException(e);
            }

        JcPopNode jcPopNode = JcPopNode.builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcPopNode);

        JcStopNode jcStopNode = JcStopNode.builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcStopNode);

        for (JajaCodeNode jcnode : jajaCodeNodes)
        {

            if(jcnode instanceof JcIfNode)
            {
                JajaCodeNode jcIfNode = jajaCodeNodes.get(((JcIfNode) jcnode).adresse() - 1);
                ((JcIfNode) jcnode).setIfNode(jcIfNode);
                jajaCodeNodes.set(jajaCodeNodes.indexOf(jcnode),jcnode);
            }
            else if(jcnode instanceof JcGotoNode)
            {
                JajaCodeNode jcGotoNode = jajaCodeNodes.get(((JcGotoNode) jcnode).adresse() - 1);
                ((JcGotoNode) jcnode).setGotonode(jcGotoNode);
                jajaCodeNodes.set(jajaCodeNodes.indexOf(jcnode),jcnode);

            }

        }




    }

    @Override
    public void visit(IdentNode node) throws IllFormedNodeException, IOException {

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n = (Integer)stack.peek().values().toArray()[0];
        h.put(node,1);
        minijajaNodes.add(h);
        stack.push(h);

        JcLoadNode jcLoadNode = JcLoadNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .identifier(node.value())
                .build();

        setNextNode(jcLoadNode);

    }

    @Override
    public void visit(DeclsNode node) throws CompilerException {

        MiniJajaNode nodeDecl = node.decl();
        MiniJajaNode nodeDecls = node.decls();

        HashMap<MiniJajaNode, Integer> newhashMap;

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();

        if(nodeDecl != null)
        {
            int n =  node_init(node,h);


            if(compilemode == Mode.NORMALE) {
                try {
                    nodeDecl.accept(this);
                    newhashMap = stack.pop();
                    int nds = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n + nds);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                    nodeDecls.accept(this);
                    newhashMap = stack.pop();
                    int ndss = (int) newhashMap.values().toArray()[0];
                    h.replace(node, nds + ndss);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);


                } catch (Exception e) {
                    throw new CompilerException(e);
                }
            }
            else if(compilemode == Mode.RETRAIT)
            {
                try {
                    nodeDecls.accept(this);
                    newhashMap = stack.pop();
                    int nrdss = (int) newhashMap.values().toArray()[0];
                    h.replace(node,n+nrdss);
                    minijajaNodes.set(minijajaNodes.indexOf(h),h);
                    stack.set(stack.indexOf(h),h);

                    nodeDecl.accept(this);
                    newhashMap = stack.pop();
                    int nrds = (int) newhashMap.values().toArray()[0];
                    h.replace(node,nrds+nrdss);
                    minijajaNodes.set(minijajaNodes.indexOf(h),h);
                    stack.set(stack.indexOf(h),h);
                } catch (Exception e) {
                    throw new CompilerException(e);
                }


            }
        }else
        {
            HashMap<MiniJajaNode, Integer> vnil = new HashMap<MiniJajaNode, Integer>();
            vnil.put(node, 0);
            minijajaNodes.add(vnil);
            stack.push(vnil);
        }



    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException, CompilerException {
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();

        if(node.var() != null) {
            MiniJajaNode nodeVar = node.var();
            MiniJajaNode nodeVars = node.vars();
            int n =  node_init(node,h);
            if (compilemode == Mode.NORMALE) {
                try {
                    nodeVar.accept(this);
                    newhashMap = stack.pop();
                    int ndv = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n + ndv);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                    nodeVars.accept(this);
                    newhashMap = stack.pop();
                    int ndvs = (int) newhashMap.values().toArray()[0];
                    h.replace(node, ndv + ndvs);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                } catch (Exception e) {
                    throw new CompilerException(e);
                }


            } else if (compilemode == Mode.RETRAIT) {
                try {
                    nodeVars.accept(this);
                    newhashMap = stack.pop();
                    int nrdvs = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n + nrdvs);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                    nodeVar.accept(this);
                    newhashMap = stack.pop();
                    int nrdv = (int) newhashMap.values().toArray()[0];
                    h.replace(node, nrdvs + nrdv);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                } catch (Exception e) {
                    throw new CompilerException(e);
                }
            }
        }
        else {
            HashMap<MiniJajaNode, Integer> vnil = new HashMap<MiniJajaNode, Integer>();
            vnil.put(node, 0);
            minijajaNodes.add(vnil);
            stack.push(vnil);
        }

    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(compilemode == Mode.NORMALE) {
            try {
                nodeExp.accept(this);
            } catch (Exception e) {
                throw new CompilerException(e);
            }

            newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node, ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h), h);
            stack.set(stack.indexOf(h), h);


            JcNewNode jcNewNode = JcNewNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.Var)
                    .type(getType(node.typeMeth().value()))
                    .adresse(0)
                    .build();

            setNextNode(jcNewNode);

        }else if(compilemode == Mode.RETRAIT)
        {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            int nf = 2;

            h.replace(node,nf);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
        }


    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);


        if(compilemode == Mode.NORMALE)
        {
            try {
                nodeExp.accept(this);
            }
            catch (Exception e) {
                throw new CompilerException(e);
            }

            newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node, ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h), h);
            stack.set(stack.indexOf(h), h);

            JcNewarrayNode jcNewarrayNode = JcNewarrayNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .type(node.typeMeth().value())
                    .build();
            setNextNode(jcNewarrayNode);

        }else if(compilemode == Mode.RETRAIT)
        {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            int nf = 2;

            h.replace(node,nf);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
        }
    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(compilemode == Mode.NORMALE) {
            try {
                nodeExp.accept(this);
            } catch (Exception e) {
                throw new CompilerException(e);
            }
            newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node, ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h), h);
            stack.set(stack.indexOf(h), h);

            JcNewNode jcNewNode = JcNewNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.Cst)
                    .type(getType(node.type().value()))
                    .adresse(0)
                    .build();

            setNextNode(jcNewNode);

        }else if(compilemode == Mode.RETRAIT)
        {

            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            int nf = 2;

            h.replace(node,nf);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
        }


    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeTypeMeth = node.typeMeth();
        MiniJajaNode nodeHeaders = node.headers();
        MiniJajaNode nodeVars = node.vars();
        MiniJajaNode nodeInstrs = node.instrs();

        int nr =0;
        int nh = getHeadersNumber(nodeHeaders);
        nheader = nh;
        boolean Void = false;
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(node.typeMeth().value() == TypeMethNode.TypeMeth.VOID)
        {
            Void = true;
            nr = 6;
        }
        else
        {
            Void = false;
            nr = 5;
        }

        if(compilemode == Mode.NORMALE) {
            try {
                JcPushNode jcPushNode = JcPushNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .valeur(JcNumberNode.builder().value(n+3).build())
                        .build();
                setNextNode(jcPushNode);

                JcNewNode jcNewNode = JcNewNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .identifier(node.identifier().value())
                        .sorte(JcNewNode.Sorte.Meth)
                        .type(getType(node.typeMeth().value()))
                        .adresse(0)
                        .build();
                setNextNode(jcNewNode);

                JcGotoNode jcGotoNode = JcGotoNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .build();
                setNextNode(jcGotoNode);

                nodeHeaders.accept(this);
                newhashMap = stack.pop();
                int nens = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+nens+3);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h), h);

                nodeVars.accept(this);
                newhashMap = stack.pop();
                int ndvs = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+nens+ndvs+3);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h), h);

                nodeInstrs.accept(this);
                newhashMap = stack.pop();
                int niss = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+nens+ndvs+niss+3);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h), h);

                if (Void) {
                    JcPushNode jcPushNode1 = JcPushNode.builder()
                            .line(jajaCodeNodes.size() + 1)
                            .column(1)
                            .valeur(JcNumberNode.builder().value(0).build())
                            .build();

                    setNextNode(jcPushNode1);
                }

                //Accept Retrait VArs
                /********************Retrait***********************/
                compilemode = Mode.RETRAIT;

                nodeVars.accept(this);
                newhashMap = stack.pop();
                int nrdvs = (int) newhashMap.values().toArray()[0];
                h.replace(node,nens + ndvs + niss + nrdvs + nr );
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);

                compilemode = Mode.NORMALE;
                /*****************************************************/

                JcSwapNode jcSwapNode = JcSwapNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .build();
                setNextNode(jcSwapNode);

                JcReturnNode jcReturnNode = JcReturnNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .build();
                setNextNode(jcReturnNode);

                jcGotoNode.setAdresse(n+ nens + ndvs + niss + nrdvs + nr);
            } catch (Exception e) {
                throw new CompilerException(e);
            }
        }else if(compilemode == Mode.RETRAIT)
        {

            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            h.replace(node,2);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);

        }
    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeVars = node.vars();
        MiniJajaNode nodeInstrs = node.instrs();

        HashMap<MiniJajaNode, Integer> newhashMap;

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);


        if(compilemode == Mode.NORMALE) {
            try {
                nodeVars.accept(this);
                newhashMap = stack.pop();
                int ndvs = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+ndvs);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h), h);

                nodeInstrs.accept(this);
                newhashMap = stack.pop();
                int niss = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+ndvs+niss+1);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h), h);

                JcPushNode jcPushNode = JcPushNode.builder()
                        .line(jajaCodeNodes.size()+1)
                        .column(1)
                        .valeur(JcNumberNode.builder().value(0).build())
                        .build();
                setNextNode(jcPushNode);

                /********************Retrait***********************/
                compilemode = Mode.RETRAIT;

                nodeVars.accept(this);
                newhashMap = stack.pop();
                int nrds = (int) newhashMap.values().toArray()[0];
                h.replace(node,ndvs+niss+nrds+1);
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);

                compilemode = Mode.NORMALE;
                /*****************************************************/

            }catch (Exception e) {
                throw new CompilerException(e);
            }
        }
    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeHeader = node.header();
        MiniJajaNode nodeHeaders = node.headers();

        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();

        if(node.header() != null) {
            int n =  node_init(node,h);
            if (compilemode == Mode.NORMALE) {
                try {

                    nodeHeaders.accept(this);
                    newhashMap = stack.pop();
                    int nens = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n + nens);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                    nodeHeader.accept(this);
                    newhashMap = stack.pop();
                    int nen = (int) newhashMap.values().toArray()[0];
                    h.replace(node, nen + nens);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);
                } catch (Exception e) {
                    throw new CompilerException(e);
                }
            }
        }
        else
        {
            HashMap<MiniJajaNode, Integer> enil = new HashMap<MiniJajaNode, Integer>();
            enil.put(node, 0);
            minijajaNodes.add(enil);
            stack.push(enil);
        }


    }

    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> newhashMap;
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(compilemode == Mode.NORMALE) {
            JcNewNode jcNewNode =  JcNewNode.builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.Var)
                    .type(getType(node.type().value()))
                    .adresse(nheader)
                    .build();
            setNextNode(jcNewNode);

            int nf = 1;
            h.replace(node,nf);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            nheader--;
        }

    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException, CompilerException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        if(node.instruction() != null) {
            MiniJajaNode nodeInstr = node.instruction();
            MiniJajaNode nodeInstrs = node.instrs();
            int n =  node_init(node,h);
            try {
                nodeInstr.accept(this);
                HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                int nis = (int) newhashMap.values().toArray()[0];
                h.replace(node, nis);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                nodeInstrs.accept(this);
                newhashMap = stack.pop();
                int niss = (int) newhashMap.values().toArray()[0];
                h.replace(node, nis + niss);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);

            } catch (Exception e) {
                throw new CompilerException(e);
            }
        }else{

            HashMap<MiniJajaNode, Integer> inil = new HashMap<MiniJajaNode, Integer>();
            inil.put(node, 0);
            minijajaNodes.add(inil);
            stack.push(inil);
        }

    }

        @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeIdent = node.identifier();
        MiniJajaNode nodeExp1 = node.expression();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

            if(nodeIdent instanceof ArrayItemNode) {
                try {
                    MiniJajaNode nodeExp = ((ArrayItemNode) nodeIdent).expression();
                    nodeExp.accept(this);
                    HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                    int ne = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n+ne);
                    nodeExp1.accept(this);
                    newhashMap = stack.pop();
                    int ne1 = (int) newhashMap.values().toArray()[0];
                    h.replace(node, ne + ne1 + 1);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h),h);

                } catch (Exception e) {
                    throw new CompilerException(e);
                }
                JcAstoreNode jcAstoreNode = JcAstoreNode
                        .builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .identifier(((ArrayItemNode)nodeIdent).identifier().value())
                        .build();

                setNextNode(jcAstoreNode);


            }else {
                    MiniJajaNode nodeExp = node.expression();
                    try {

                        nodeExp.accept(this);
                        HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                        int ne = (int) newhashMap.values().toArray()[0];
                        h.replace(node,ne + 1);
                        minijajaNodes.set(minijajaNodes.indexOf(h),h);


                        JcStoreNode jcStoreNode = JcStoreNode
                                .builder()
                                .line(jajaCodeNodes.size() + 1)
                                .column(1)
                                .identifier(((IdentNode)nodeIdent).value())
                                .build();

                        setNextNode(jcStoreNode);
                    } catch (Exception e) {
                        throw new CompilerException(e);
                    }
                }
    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeIdent = node.identifier();
        MiniJajaNode nodeExp1 = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(nodeIdent instanceof ArrayItemNode){

            try {
                MiniJajaNode nodeExp = ((ArrayItemNode) nodeIdent).expression();
                nodeExp.accept(this);
                HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                int ne = (int) newhashMap.values().toArray()[0];
                h.replace(node, n+ne);
                nodeExp1.accept(this);
                newhashMap = stack.pop();
                int ne1 = (int) newhashMap.values().toArray()[0];
                h.replace(node, ne + ne1 + 1);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h),h);
            } catch (Exception e) {
                throw new CompilerException(e);
            }

            JcAincNode jcAincNode = JcAincNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((ArrayItemNode)nodeIdent).identifier().value())
                    .build();

            setNextNode(jcAincNode);

        }else if(nodeIdent instanceof IdentNode){

            MiniJajaNode nodeExp = node.expression();
            h.put(node,n);
            minijajaNodes.add(h);
            stack.push(h);
            try {

                nodeExp.accept(this);
                HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                int ne = (int) newhashMap.values().toArray()[0];
                h.replace(node,ne + 1);
                minijajaNodes.set(minijajaNodes.indexOf(h),h);
                stack.set(stack.indexOf(h),h);
            } catch (Exception e) {
                throw new CompilerException(e);
            }
            JcIncNode jcIncNode = JcIncNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((IdentNode)nodeIdent).value())
                    .build();

            setNextNode(jcIncNode);

        }
    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeIdent = node.identifier();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        if(nodeIdent instanceof ArrayItemNode){

            try {

                MiniJajaNode nodeExp = ((ArrayItemNode) nodeIdent).expression();
                nodeExp.accept(this);
                HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
                int ne = (int) newhashMap.values().toArray()[0];
                h.replace(node, ne + 2);
                minijajaNodes.set(minijajaNodes.indexOf(h), h);
                stack.set(stack.indexOf(h),h);

                JcPushNode jcPushNode= JcPushNode
                        .builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .valeur(JcNumberNode.builder().value(1).build())
                        .build();

                setNextNode(jcPushNode);

                JcAincNode jcAincNode = JcAincNode
                        .builder()
                        .line(jajaCodeNodes.size() +1)
                        .column(1)
                        .identifier(((ArrayItemNode)nodeIdent).identifier().value())
                        .build();
                setNextNode(jcAincNode);

            } catch (Exception e) {
                throw new CompilerException(e);
            }

        }else if(nodeIdent instanceof   IdentNode){
            h.put(node,2);
            minijajaNodes.add(h);

            JcPushNode jcPushNode= JcPushNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .valeur(JcNumberNode.builder().value(1).build())
                    .build();

            setNextNode(jcPushNode);

            JcIncNode jcIncNode = JcIncNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((IdentNode)nodeIdent).value())
                    .build();

            setNextNode(jcIncNode);
        }
    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeLexp = node.listexp();
        MiniJajaNode nodeIdent = node.identifier();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {
            nodeLexp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int nlexp = (int) newhashMap.values().toArray()[0];


            JcInvokeNode jcInvokeNode = JcInvokeNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(((IdentNode)nodeIdent).value())
                    .build();

            setNextNode(jcInvokeNode);
            /********************Retrait***********************/
            compilemode = Mode.RETRAIT;

            nodeLexp.accept(this);
            newhashMap = stack.pop();
            int nrlexp = (int) newhashMap.values().toArray()[0];
            h.replace(node,nlexp + nrlexp + 2);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            compilemode = Mode.NORMALE;
            /*****************************************************/

            JcPopNode jcPopNode = JcPopNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcPopNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }
    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeExpReturn = node.ret();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {
            nodeExpReturn.accept(this);
        } catch (Exception e) {
            throw new CompilerException(e);
        }
        HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
        int ne = (int) newhashMap.values().toArray()[0];
        h.replace(node,ne);
        minijajaNodes.set(minijajaNodes.indexOf(h),h);
        stack.set(stack.indexOf(h),h);
    }


    @Override
    public void visit(WriteNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodePrintable = node.printable();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {
            nodePrintable.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);
        } catch (Exception e) {
            throw new CompilerException(e);
        }
        JcWriteNode jcWriteNode = JcWriteNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcWriteNode);

    }

    @Override
    public void visit(WriteLnNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(StringNode node) throws IllFormedNodeException, IOException {

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        h.put(node,1);
        minijajaNodes.add(h);
        stack.push(h);

        JcPushNode jcPushNode = JcPushNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .valeur(JcStringNode.builder().value(node.value()).build()).build();
        setNextNode(jcPushNode);
    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeis1 = node.trueInstrs();
        MiniJajaNode nodeis2 = node.falseInstrs();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            JcIfNode jcIfNode = JcIfNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcIfNode);
            nodeis2.accept(this);
            newhashMap = stack.pop();
            int ns1 = (int) newhashMap.values().toArray()[0];
            JcGotoNode jcGotoNode = JcGotoNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcGotoNode);
            h.replace(node,n+ne+ns1+2);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);
            nodeis1.accept(this);
            newhashMap= stack.pop();
            int ns = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne + ns1 + ns + 2);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

           jcIfNode.setAdresse(n+ne+ns1+2);
           jajaCodeNodes.set(jajaCodeNodes.indexOf(jcIfNode),jcIfNode);
           jcGotoNode.setAdresse(n+ne+ns1+ns+2);
           jajaCodeNodes.set(jajaCodeNodes.indexOf(jcGotoNode),jcGotoNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException, CompilerException {
        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeinstrs = node.instrs();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);
            try{
            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne+2);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            JcNotNode jcNotNode = JcNotNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcNotNode);

            JcIfNode jcIfNode = JcIfNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcIfNode);

            nodeinstrs.accept(this);
            newhashMap = stack.pop();
            int niss = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne+niss+3);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);
            JcGotoNode jcGotoNode = JcGotoNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .adresse(n)
                    .build();

            setNextNode(jcGotoNode);
            jcIfNode.setAdresse(n+ne+niss+3);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcIfNode),jcIfNode);
            jcGotoNode.setAdresse(n);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcGotoNode),jcGotoNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException, CompilerException {

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        HashMap<MiniJajaNode, Integer> newhashMap = new HashMap<MiniJajaNode, Integer>();

        if(node.expression() != null) {
            MiniJajaNode nodeExp = node.expression();
            MiniJajaNode nodeListExp = node.listexp();
            int n =  node_init(node,h);

            if (compilemode == Mode.NORMALE) {
                try {
                    nodeExp.accept(this);
                    newhashMap = stack.pop();
                    int nexp = (int) newhashMap.values().toArray()[0];
                    h.replace(node, n + nexp);
                    nodeListExp.accept(this);
                    newhashMap = stack.pop();
                    int nlexp = (int) newhashMap.values().toArray()[0];
                    h.replace(node, nexp + nlexp);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);
                    stack.set(stack.indexOf(h), h);

                } catch (Exception e) {
                    throw new CompilerException(e);
                }
            }
            else if(compilemode == Mode.RETRAIT)
            {
                try {
                    JcSwapNode jcSwapNode = JcSwapNode.builder()
                            .line(jajaCodeNodes.size()+1)
                            .column(1)
                            .build();
                    setNextNode(jcSwapNode);

                    JcPopNode jcPopNode = JcPopNode.builder()
                            .line(jajaCodeNodes.size()+1)
                            .column(1)
                            .build();
                    setNextNode(jcPopNode);
                    nodeListExp.accept(this);
                    newhashMap = stack.pop();
                    int nrlexp = (int) newhashMap.values().toArray()[0];
                    h.replace(node,nrlexp+2);
                    minijajaNodes.set(minijajaNodes.indexOf(h), h);

                } catch (Exception e) {
                    throw new CompilerException(e);
                }

            }
        }else{
            HashMap<MiniJajaNode, Integer> exnil = new HashMap<MiniJajaNode, Integer>();
            exnil.put(node, 0);
            minijajaNodes.add(exnil);
            stack.push(exnil);
        }

    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {
            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne +1 );
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            JcNotNode jcNotNode = JcNotNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcNotNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

        JcAndNode jcAndNode = JcAndNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcAndNode);
    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);
            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);


            JcOrNode jcOrNode = JcOrNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcOrNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }
    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);


        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);


            JcCmpNode jcCmpNode = JcCmpNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcCmpNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }



    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

        JcSupNode jcSupNode = JcSupNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcSupNode);

    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

        JcAddNode jcAddNode = JcAddNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcAddNode);
    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }
        JcSubNode jcSubNode = JcSubNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcSubNode);

    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);


        try {
            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node, ne +1 );
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);


            JcNegNode jcNegNode = JcNegNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .build();

            setNextNode(jcNegNode);

        } catch (Exception e) {
            throw new CompilerException(e);
        }
    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }
        JcMulNode jcMulNode = JcMulNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcMulNode);

    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            leftOperande.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne1 = (int) newhashMap.values().toArray()[0];
            h.replace(node,n+ne1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            rightOperande.accept(this);
            newhashMap = stack.pop();
            int ne2 = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne1+ne2+1);

            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

        JcDivNode jcDivNode = JcDivNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .build();

        setNextNode(jcDivNode);
    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeLexp = node.listexp();
        MiniJajaNode nodeIdent = node.identifier();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);

        try {

            nodeLexp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int nlexp = (int) newhashMap.values().toArray()[0];


            JcInvokeNode jcInvokeNode = JcInvokeNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .identifier(((IdentNode)nodeIdent).value())
                    .build();

            setNextNode(jcInvokeNode);

            //Retrait
            /********************Retrait***********************/
            compilemode = Mode.RETRAIT;

            nodeLexp.accept(this);
            newhashMap = stack.pop();
            int nrlexp = (int) newhashMap.values().toArray()[0];
            h.replace(node,nlexp + nrlexp +1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

            compilemode = Mode.NORMALE;
            /*****************************************************/


        } catch (Exception e) {
            throw new CompilerException(e);
        }
    }


    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {

        Boolean booleanNode = node.value();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n = (Integer)stack.peek().values().toArray()[0];
        h.put(node,1);
        minijajaNodes.add(h);
        stack.push(h);
        if(booleanNode){



            JcPushNode jcPushNode = JcPushNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .valeur(JcBooleanNode.builder().value(true).build())
                    .build();

            setNextNode(jcPushNode);


        }else{

            JcPushNode jcPushNode = JcPushNode
                    .builder()
                    .line(jajaCodeNodes.size()+1)
                    .column(1)
                    .valeur(JcBooleanNode.builder().value(false).build())
                    .build();

            setNextNode(jcPushNode);
        }
    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        h.put(node,1);
        minijajaNodes.add(h);
        stack.push(h);

        JcPushNode jcPushNode = JcPushNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .valeur(JcNumberNode.builder().value(node.value()).build())
                .build();

        setNextNode(jcPushNode);

    }

    @Override
    public void visit(ArrayItemNode node) throws IllFormedNodeException, IOException, CompilerException {

        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeIdent = node.identifier();

        HashMap<MiniJajaNode, Integer> h = new HashMap<MiniJajaNode, Integer>();
        int n =  node_init(node,h);
        try {

            nodeExp.accept(this);
            HashMap<MiniJajaNode, Integer> newhashMap = stack.pop();
            int ne = (int) newhashMap.values().toArray()[0];
            h.replace(node,ne+1);
            minijajaNodes.set(minijajaNodes.indexOf(h),h);
            stack.set(stack.indexOf(h),h);

        } catch (Exception e) {
            throw new CompilerException(e);
        }

        JcAloadNode jcAloadNode = JcAloadNode
                .builder()
                .line(jajaCodeNodes.size()+1)
                .column(1)
                .identifier(((IdentNode)nodeIdent).value())
                .build();

        setNextNode(jcAloadNode);

    }

    @Override
    public void visit(TypeMethNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(TypeNode node) throws IllFormedNodeException, IOException {

    }

    public JcNewNode.Type getType(TypeMethNode.TypeMeth type)
    {
        if(type == TypeMethNode.TypeMeth.INT)
        {
            return JcNewNode.Type.INT;
        }
        else if (type == TypeMethNode.TypeMeth.BOOLEAN)
        {
            return JcNewNode.Type.BOOLEAN;
        }
        else if (type == TypeMethNode.TypeMeth.VOID)
        {
            return JcNewNode.Type.VOID;
        }
        return null;
    }

    public JcNewNode.Type getType(TypeNode.Type type)
    {
        if(type == TypeNode.Type.INT)
        {
            return JcNewNode.Type.INT;
        }
        else if (type == TypeNode.Type.BOOLEAN)
        {
            return JcNewNode.Type.BOOLEAN;
        }
        return null;
    }


    public int getHeadersNumber(MiniJajaNode headearsnode) {
        int value = 0;

        while ( headearsnode.children(0) != null ) {
            value += 1;
            headearsnode = ((HeadersNode) headearsnode).headers();
        }
        return value;
    }

    public void setNextNode(JajaCodeNode node){

        JajaCodeNode parent = jajaCodeNodes.get(jajaCodeNodes.size()-1);
        parent.setNext(node);
        jajaCodeNodes.add(node);

    }


    public int node_init(MiniJajaNode node,HashMap<MiniJajaNode,Integer> h){

        int n = (Integer)stack.peek().values().toArray()[0];
        h.put(node,n);
        minijajaNodes.add(h);
        stack.push(h);

        return n;
    }

}
