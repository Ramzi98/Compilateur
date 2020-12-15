package edu.ubfc.st.vm.project.grp7.compiler;

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
    private ArrayList<JajaCodeNode> jajaCodeNodes;
    private ArrayList<HashMap<MiniJajaNode, Integer>> minijajaNodes;
    private Stack<HashMap<MiniJajaNode, Integer>> stack;
    private Mode compilemode;
    private int nheader;
    int nClasse;

    //Constructor
    public CompilerVisitor() {
        nClasse = 1;
        jajaCodeNodes = new ArrayList<>();
        minijajaNodes = new ArrayList<>();
        stack = new Stack<>();
        stack = new Stack<>();
        compilemode = Mode.NORMALE;
    }

    //getter and setter
    public ArrayList<HashMap<MiniJajaNode, Integer>> getMinijajaNodes() {
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
    public void visit(ClasseNode node) throws IllFormedNodeException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = nClasse;
        h.put(node, n);
        minijajaNodes.add(h);
        stack.push(h);

        JcInitNode jcInitNode = JcInitNode.builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();
        jajaCodeNodes.add(jcInitNode);
         node_update(node,n+1,h);

        try {
            int ndss = 0;
            if (node.decls() != null) {
                node.decls().accept(this);
                ndss = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,n+ndss+1,h);

            node.methmain().accept(this);
            int nmma = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ndss + nmma + 1,h);

            compilemode = Mode.RETRAIT;

            int nrdss = 0;
            if (node.decls() != null) {
                node.decls().accept(this);
                nrdss = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,ndss + nmma + nrdss + 3,h);

            compilemode = Mode.NORMALE;
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcPopNode jcPopNode = JcPopNode.builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();
        setNextNode(jcPopNode);

        JcStopNode jcStopNode = JcStopNode.builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();
        setNextNode(jcStopNode);

        for (JajaCodeNode jcnode : jajaCodeNodes) {
            if (jcnode instanceof JcIfNode) {
                JajaCodeNode jcIfNode = jajaCodeNodes.get(((JcIfNode) jcnode).adresse() - 1);
                ((JcIfNode) jcnode).setIfNode(jcIfNode);
                jajaCodeNodes.set(jajaCodeNodes.indexOf(jcnode), jcnode);
            } else if (jcnode instanceof JcGotoNode) {
                JajaCodeNode jcGotoNode = jajaCodeNodes.get(((JcGotoNode) jcnode).adresse() - 1);
                ((JcGotoNode) jcnode).setGotonode(jcGotoNode);
                jajaCodeNodes.set(jajaCodeNodes.indexOf(jcnode), jcnode);
            }
        }
    }

    @Override
    public void visit(IdentNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        h.put(node, 1);
        minijajaNodes.add(h);
        stack.push(h);

        JcLoadNode jcLoadNode = JcLoadNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .identifier(node.value())
                .build();

        setNextNode(jcLoadNode);
    }

    @Override
    public void visit(DeclsNode node) throws IllFormedNodeException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);
        if (compilemode == Mode.NORMALE) {
            try {
                node.decl().accept(this);
                int nds = (int) stack.pop().values().toArray()[0];
                node_update(node,n + nds,h);

                int ndss = 0;
                if (node.decls() != null) {
                    node.decls().accept(this);
                    ndss = (int) stack.pop().values().toArray()[0];
                }

                node_update(node,nds + ndss,h);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        } else {
            try {
                int nrdss = 0;
                if (node.decls() != null) {
                    node.decls().accept(this);
                    nrdss = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n + nrdss,h);

                node.decl().accept(this);
                int nrds = (int) stack.pop().values().toArray()[0];
                node_update(node,nrds + nrdss,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        }
    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        MiniJajaNode nodeVar = node.var();
        MiniJajaNode nodeVars = node.vars();
        int n = node_init(node, h);
        if (compilemode == Mode.NORMALE) {
            try {
                nodeVar.accept(this);
                int ndv = (int) stack.pop().values().toArray()[0];
                node_update(node,n + ndv,h);

                int ndvs = 0;
                if (nodeVars != null) {
                    nodeVars.accept(this);
                    ndvs = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,ndv + ndvs,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        } else if (compilemode == Mode.RETRAIT) {
            try {
                int nrdvs = 0;
                if (nodeVars != null) {
                    nodeVars.accept(this);
                    nrdvs = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n + nrdvs,h);

                nodeVar.accept(this);
                int nrdv = (int) stack.pop().values().toArray()[0];
                node_update(node,nrdvs+nrdv,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        }
    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            int ne = 0;
            try {
                if(node.expression() != null)
                {
                    node.expression().accept(this);
                    ne = (int) stack.pop().values().toArray()[0];
                }
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
            node_update(node,ne + 1,h);


            JcNewNode jcNewNode = JcNewNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.VAR)
                    .type(getType(node.typeMeth().value()))
                    .depth(0)
                    .build();

            setNextNode(jcNewNode);

        } else if (compilemode == Mode.RETRAIT) {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            int nf = 2;

            node_update(node,nf,h);


        }
    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            try {
                node.expression().accept(this);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,ne + 1,h);

            JcNewarrayNode jcNewarrayNode = JcNewarrayNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .type(JcNewarrayNode.Type.of(node.typeMeth().value()))
                    .build();
            setNextNode(jcNewarrayNode);

        } else if (compilemode == Mode.RETRAIT) {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            int nf = 2;

            node_update(node,nf ,h);

        }
    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            try {
                node.expression().accept(this);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,ne + 1,h);

            JcNewNode jcNewNode = JcNewNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.CST)
                    .type(getType(node.type().value()))
                    .depth(0)
                    .build();

            setNextNode(jcNewNode);
        } else {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            node_update(node,2,h);

        }
    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException {
        nheader = 0;
        nheader = getHeadersNumber(node.headers());

        boolean Void = false;
        int nr = 5;
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        if (node.typeMeth().value() == TypeMethNode.TypeMeth.VOID) {
            Void = true;
            nr = 6;
        }

        if (compilemode == Mode.NORMALE) {
            try {

                JcPushNode jcPushNode = JcPushNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .valeur(n + 3)
                        .build();
                setNextNode(jcPushNode);

                JcNewNode jcNewNode = JcNewNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .identifier(node.identifier().value())
                        .sorte(JcNewNode.Sorte.METH)
                        .type(getType(node.typeMeth().value()))
                        .depth(0)
                        .build();
                setNextNode(jcNewNode);

                JcGotoNode jcGotoNode = JcGotoNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .build();
                setNextNode(jcGotoNode);

                int nens = 0;
                if (node.headers() != null) {
                    node.headers().accept(this);
                    nens = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n + nens + 3,h);

                int ndvs = 0;
                if (node.vars() != null) {
                    node.vars().accept(this);
                    ndvs = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n + nens + ndvs + 3,h);

                int niss = 0;
                if (node.instrs() != null) {
                    node.instrs().accept(this);
                    niss = (int) stack.pop().values().toArray()[0];
                }

                node_update(node,n + nens + ndvs + niss + 3,h);


                if (Void) {
                    JcPushNode jcPushNode1 = JcPushNode.builder()
                            .line(jajaCodeNodes.size() + 1)
                            .column(1)
                            .valeur(0)
                            .build();

                    setNextNode(jcPushNode1);
                }

                compilemode = Mode.RETRAIT;

                int nrdvs = 0;
                if (node.vars() != null) {
                    node.vars().accept(this);
                    nrdvs = (int) stack.pop().values().toArray()[0];
                }

                node_update(node,nens + ndvs + niss + nrdvs + nr,h);

                compilemode = Mode.NORMALE;

                JcSwapNode jcSwapNode = JcSwapNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .build();
                setNextNode(jcSwapNode);

                JcReturnNode jcReturnNode = JcReturnNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .build();
                setNextNode(jcReturnNode);

                jcGotoNode.setAdresse(n + nens + ndvs + niss + nrdvs + nr);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        } else if (compilemode == Mode.RETRAIT) {
            JcSwapNode jcSwapNode = JcSwapNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcSwapNode);

            JcPopNode jcPopNode = JcPopNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();
            setNextNode(jcPopNode);

            node_update(node,2,h);

        }
    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            try {
                int ndvs = 0;
                if (node.vars() != null) {
                    node.vars().accept(this);
                    ndvs = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n+ndvs,h);


                int niss = 0;
                if (node.instrs() != null) {
                    node.instrs().accept(this);
                    niss = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,n + ndvs + niss + 1,h);

                JcPushNode jcPushNode = JcPushNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .valeur(0)
                        .build();
                setNextNode(jcPushNode);

                compilemode = Mode.RETRAIT;

                int nrds = 0;
                if (node.vars() != null) {
                    node.vars().accept(this);
                    nrds = (int) stack.pop().values().toArray()[0];
                }

                node_update(node, ndvs + niss + nrds + 1,h);

                compilemode = Mode.NORMALE;
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        }
    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();

        int n = node_init(node, h);
        if (compilemode == Mode.NORMALE) {
            try {

                int nens = 0;
                if (node.headers() != null) {
                    node.headers().accept(this);
                    nens = (int) stack.pop().values().toArray()[0];
                }

                node_update(node,  n + nens,h);

                node.header().accept(this);
                int nen = (int) stack.pop().values().toArray()[0];
                node_update(node,  nen + nens,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        }
    }
    
    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            JcNewNode jcNewNode = JcNewNode.builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .sorte(JcNewNode.Sorte.VAR)
                    .type(getType(node.type().value()))
                    .depth(nheader)
                    .build();
            setNextNode(jcNewNode);

            int nf = 1;
            node_update(node,  nf,h);


            nheader--;
        }
    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        MiniJajaNode nodeInstr = node.instruction();
        MiniJajaNode nodeInstrs = node.instrs();
        int n = node_init(node, h);
        try {
            nodeInstr.accept(this);
            int nis = (int) stack.pop().values().toArray()[0];
            node_update(node,  n + nis,h);

            int niss = 0;
            if (nodeInstrs != null) {
                nodeInstrs.accept(this);
                niss = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,  nis + niss,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp1 = node.expression();

        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        if (node.identifier() instanceof ArrayItemNode) {
            ArrayItemNode arrayItemNode = (ArrayItemNode) node.identifier();

            try {
                MiniJajaNode nodeExp = (arrayItemNode.expression());

                nodeExp.accept(this);
                int ne = (int) stack.pop().values().toArray()[0];
                node_update(node,  n + ne,h);

                nodeExp1.accept(this);
                int ne1 = (int) stack.pop().values().toArray()[0];
                node_update(node,  ne + ne1 + 1,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

            JcAstoreNode jcAstoreNode = JcAstoreNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(arrayItemNode.identifier().value())
                    .build();

            setNextNode(jcAstoreNode);
        } else {
            IdentNode identNode = (IdentNode) node.identifier();
            MiniJajaNode nodeExp = node.expression();
            try {

                nodeExp.accept(this);
                int ne = (int) stack.pop().values().toArray()[0];
                node_update(node,  ne + 1,h);

                JcStoreNode jcStoreNode = JcStoreNode
                        .builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .identifier(identNode.value())
                        .build();

                setNextNode(jcStoreNode);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        }
    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeIdent = node.identifier();
        MiniJajaNode nodeExp1 = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        if (nodeIdent instanceof ArrayItemNode) {

            try {
                MiniJajaNode nodeExp = ((ArrayItemNode) nodeIdent).expression();

                nodeExp.accept(this);
                int ne = (int) stack.pop().values().toArray()[0];
                node_update(node,  n + ne,h);

                nodeExp1.accept(this);
                int ne1 = (int) stack.pop().values().toArray()[0];
                node_update(node,  ne + ne1 + 1,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

            JcAincNode jcAincNode = JcAincNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((ArrayItemNode) nodeIdent).identifier().value())
                    .build();

            setNextNode(jcAincNode);

        } else if (nodeIdent instanceof IdentNode) {

            MiniJajaNode nodeExp = node.expression();
            h.put(node, n);
            minijajaNodes.add(h);
            stack.push(h);
            try {
                nodeExp.accept(this);
                int ne = (int) stack.pop().values().toArray()[0];
                node_update(node,  ne + 1,h);
            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
            JcIncNode jcIncNode = JcIncNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((IdentNode) nodeIdent).value())
                    .build();

            setNextNode(jcIncNode);
        }
    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeIdent = node.identifier();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        if (nodeIdent instanceof ArrayItemNode) {

            try {

                MiniJajaNode nodeExp = ((ArrayItemNode) nodeIdent).expression();
                nodeExp.accept(this);
                int ne = (int) stack.pop().values().toArray()[0];
                node_update(node,  ne + 2,h);

                JcPushNode jcPushNode = JcPushNode
                        .builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .valeur(1)
                        .build();

                setNextNode(jcPushNode);

                JcAincNode jcAincNode = JcAincNode
                        .builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .identifier(((ArrayItemNode) nodeIdent).identifier().value())
                        .build();
                setNextNode(jcAincNode);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

        } else if (nodeIdent instanceof IdentNode) {
            h.put(node, 2);
            minijajaNodes.add(h);

            JcPushNode jcPushNode = JcPushNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .valeur(1)
                    .build();

            setNextNode(jcPushNode);

            JcIncNode jcIncNode = JcIncNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(((IdentNode) nodeIdent).value())
                    .build();

            setNextNode(jcIncNode);
        }
    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        try {
            int nlexp = 0;
            if (node.listexp() != null) {
                node.listexp().accept(this);
                nlexp = (int) stack.pop().values().toArray()[0];
            }

            JcInvokeNode jcInvokeNode = JcInvokeNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .build();

            setNextNode(jcInvokeNode);

            compilemode = Mode.RETRAIT;

            int nrlexp = 0;
            if (node.listexp() != null) {
                node.listexp().accept(this);
                nrlexp = (int) stack.pop().values().toArray()[0];
            }

            node_update(node,  nlexp + nrlexp + 2,h);

            compilemode = Mode.NORMALE;

            JcPopNode jcPopNode = JcPopNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcPopNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExpReturn = node.ret();

        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        try {
            nodeExpReturn.accept(this);
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
        int ne = (int) stack.pop().values().toArray()[0];
        node_update(node,  ne,h);

    }

    @Override
    public void visit(WriteNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodePrintable = node.printable();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);
        try{
            nodePrintable.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,  ne + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
        JcWriteNode jcWriteNode = JcWriteNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcWriteNode);
    }

    @Override
    public void visit(WriteLnNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeprintln = node.printable();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        try {
            nodeprintln.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,  ne+1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcWriteLnNode jcWritelnNode = JcWriteLnNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcWritelnNode);
    }

    @Override
    public void visit(StringNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        h.put(node, 1);
        minijajaNodes.add(h);
        stack.push(h);

        JcPushNode.Builder jcPushNodeBuilder = JcPushNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1);

        jcPushNodeBuilder.valeur(node.value());

        setNextNode(jcPushNodeBuilder.build());
    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeis1 = node.trueInstrs();
        MiniJajaNode nodeis2 = node.falseInstrs();

        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);


        try {

            nodeExp.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,  n + ne + 1,h);

            JcIfNode jcIfNode = JcIfNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcIfNode);
            int ns1 = 0;
            if (nodeis2 != null) {
                nodeis2.accept(this);
                ns1 = (int) stack.pop().values().toArray()[0];
            }

            JcGotoNode jcGotoNode = JcGotoNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcGotoNode);
            node_update(node,   n + ne + ns1 + 2,h);


            int ns = 0;
            if (nodeis2 != null) {
                nodeis1.accept(this);
                ns = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,   ne + ns1 + ns + 2,h);

            jcIfNode.setAdresse(n + ne + ns1 + 2);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcIfNode), jcIfNode);
            jcGotoNode.setAdresse(n + ne + ns1 + ns + 2);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcGotoNode), jcGotoNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeinstrs = node.instrs();

        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);
        try {
            nodeExp.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne + 2,h);

            JcNotNode jcNotNode = JcNotNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcNotNode);

            JcIfNode jcIfNode = JcIfNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .adresse(0)
                    .build();
            setNextNode(jcIfNode);

            int niss = 0;
            if (nodeinstrs != null) {
                nodeinstrs.accept(this);
                niss = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,ne + niss + 3,h);

            JcGotoNode jcGotoNode = JcGotoNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .adresse(n)
                    .build();

            setNextNode(jcGotoNode);
            jcIfNode.setAdresse(n + ne + niss + 3);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcIfNode), jcIfNode);
            jcGotoNode.setAdresse(n);
            jajaCodeNodes.set(jajaCodeNodes.indexOf(jcGotoNode), jcGotoNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();

        MiniJajaNode nodeExp = node.expression();
        MiniJajaNode nodeListExp = node.listexp();
        int n = node_init(node, h);

        if (compilemode == Mode.NORMALE) {
            try {
                nodeExp.accept(this);
                int nexp = (int) stack.pop().values().toArray()[0];
                h.replace(node, n + nexp);
                int nlexp = 0;
                if (nodeListExp != null) {
                    nodeListExp.accept(this);
                    nlexp = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,nexp + nlexp,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }
        } else if (compilemode == Mode.RETRAIT) {
            try {
                JcSwapNode jcSwapNode = JcSwapNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .build();
                setNextNode(jcSwapNode);

                JcPopNode jcPopNode = JcPopNode.builder()
                        .line(jajaCodeNodes.size() + 1)
                        .column(1)
                        .build();
                setNextNode(jcPopNode);
                int nrlexp = 0;
                if (nodeListExp != null) {
                    nodeListExp.accept(this);
                    nrlexp = (int) stack.pop().values().toArray()[0];
                }
                node_update(node,nrlexp + 2,h);

            } catch (Exception e) {
                throw new IllFormedNodeException(e.toString());
            }

        }
    }


    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        try {
            nodeExp.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,ne + 1,h);

            JcNotNode jcNotNode = JcNotNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcNotNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcAndNode jcAndNode = JcAndNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcAndNode);
    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

            JcOrNode jcOrNode = JcOrNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcOrNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

            JcCmpNode jcCmpNode = JcCmpNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcCmpNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcSupNode jcSupNode = JcSupNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcSupNode);
    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcAddNode jcAddNode = JcAddNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcAddNode);
    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
        JcSubNode jcSubNode = JcSubNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcSubNode);
    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode nodeExp = node.expression();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);


        try {
            nodeExp.accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,ne + 1,h);


            JcNegNode jcNegNode = JcNegNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .build();

            setNextNode(jcNegNode);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
        JcMulNode jcMulNode = JcMulNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcMulNode);
    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {
        MiniJajaNode leftOperande = node.leftOperand();
        MiniJajaNode rightOperande = node.rightOperand();
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        int n = node_init(node, h);

        try {

            leftOperande.accept(this);
            int ne1 = (int) stack.pop().values().toArray()[0];
            node_update(node,n + ne1,h);

            rightOperande.accept(this);
            int ne2 = (int) stack.pop().values().toArray()[0];
            node_update(node,ne1 + ne2 + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcDivNode jcDivNode = JcDivNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .build();

        setNextNode(jcDivNode);
    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);

        try {
            int nlexp = 0;
            if (node.listexp() != null) {
                node.listexp().accept(this);
                nlexp = (int) stack.pop().values().toArray()[0];
            }

            JcInvokeNode jcInvokeNode = JcInvokeNode
                    .builder()
                    .line(jajaCodeNodes.size() + 1)
                    .column(1)
                    .identifier(node.identifier().value())
                    .build();

            setNextNode(jcInvokeNode);

            //Retrait
            compilemode = Mode.RETRAIT;

            int nrlexp = 0;
            if (node.listexp() != null) {
                node.listexp().accept(this);
                nrlexp = (int) stack.pop().values().toArray()[0];
            }
            node_update(node,nlexp + nrlexp + 1,h);

            compilemode = Mode.NORMALE;
        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }
    }

    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        h.put(node, 1);
        minijajaNodes.add(h);
        stack.push(h);
        JcPushNode jcPushNode = JcPushNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .valeur(node.value())
                .build();

        setNextNode(jcPushNode);
    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        h.put(node, 1);
        minijajaNodes.add(h);
        stack.push(h);

        JcPushNode jcPushNode = JcPushNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .valeur(node.value())
                .build();

        setNextNode(jcPushNode);
    }

    @Override
    public void visit(ArrayItemNode node) throws IllFormedNodeException, IOException {
        HashMap<MiniJajaNode, Integer> h = new HashMap<>();
        node_init(node, h);
        try {

            node.expression().accept(this);
            int ne = (int) stack.pop().values().toArray()[0];
            node_update(node,ne + 1,h);

        } catch (Exception e) {
            throw new IllFormedNodeException(e.toString());
        }

        JcAloadNode jcAloadNode = JcAloadNode
                .builder()
                .line(jajaCodeNodes.size() + 1)
                .column(1)
                .identifier(node.identifier().value())
                .build();

        setNextNode(jcAloadNode);
    }

    @Override
    public void visit(TypeMethNode node) throws IllFormedNodeException, IOException {
    }

    @Override
    public void visit(TypeNode node) throws IllFormedNodeException, IOException {
    }

    public JcNewNode.Type getType(TypeMethNode.TypeMeth type) {
        if (type == TypeMethNode.TypeMeth.INT) {
            return JcNewNode.Type.INT;
        } else if (type == TypeMethNode.TypeMeth.BOOLEAN) {
            return JcNewNode.Type.BOOLEAN;
        } else if (type == TypeMethNode.TypeMeth.VOID) {
            return JcNewNode.Type.VOID;
        }
        return null;
    }

    public JcNewNode.Type getType(TypeNode.Type type) {
        if (type == TypeNode.Type.INT) {
            return JcNewNode.Type.INT;
        } else if (type == TypeNode.Type.BOOLEAN) {
            return JcNewNode.Type.BOOLEAN;
        }
        return null;
    }

    public int getHeadersNumber(MiniJajaNode headearsnode) {
        int value = 0;
        while (headearsnode != null && headearsnode.children(0) != null) {
            value += 1;
            headearsnode = ((HeadersNode) headearsnode).headers();
        }

        return value;
    }

    public void setNextNode(JajaCodeNode node) {
        JajaCodeNode parent = jajaCodeNodes.get(jajaCodeNodes.size() - 1);
        parent.setNext(node);
        jajaCodeNodes.add(node);
    }


    public int node_init(MiniJajaNode node, HashMap<MiniJajaNode, Integer> h) {
        int n = (Integer) stack.peek().values().toArray()[0];
        h.put(node, n);
        minijajaNodes.add(h);
        stack.push(h);

        return n;
    }


    public void node_update(MiniJajaNode node,int n,  HashMap<MiniJajaNode, Integer> h){
        h.replace(node, n);
        minijajaNodes.set(minijajaNodes.indexOf(h), h);
        stack.set(stack.indexOf(h), h);
    }
}