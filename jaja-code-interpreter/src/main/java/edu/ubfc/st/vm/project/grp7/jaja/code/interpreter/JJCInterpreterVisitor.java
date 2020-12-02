package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.Quadruplet;
import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;

public class JJCInterpreterVisitor extends JajaCodeASTVisitor {
    private final Memory memory;
    private final JJCInterpreterController controller;
    private int n = 0;

    public JJCInterpreterVisitor(Memory memory, JJCInterpreterController controller) {
        this.memory = memory;
        this.controller = controller;
    }

    //<m,a> |- init -> < [], a+1>
    @Override
    public void visit(JcInitNode node) throws Exception {
        if (n != 0) {
            throw new RuntimeException("Empty JajaCode structure to be interpreted");
        }
        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 + v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAddNode node) throws Exception {

    }

    //<< w, v, cst, w >.< w, ind, cst, w >.m,a> |- ainc(i) -> <AffecterValT(i,ind,ValT(i,ind,m)+v,m), a+1>
    @Override
    public void visit(JcAincNode node) throws Exception {

    }

    //<< w, ind, cst, w >.m,a> |- aload(i) -> << w, ValT(i, ind, m), cst,w>.m, a+1>
    @Override
    public void visit(JcAloadNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 && v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAndNode node) throws Exception {

    }

    //<< w, v, cst, w >.< w, ind, cst, w >.m,a> |- astore(i) -> < AffecterValT(i, ind, v, m), a+1>
    @Override
    public void visit(JcAstoreNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcCmpNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 / v2, cst,w>.m, a+1>
    @Override
    public void visit(JcDivNode node) throws Exception {

    }

    //<m,a> |- goto(a1) -> <m, a1 >
    @Override
    public void visit(JcGotoNode node) throws Exception {

    }

    //<< w,true, cst, w>.m,a> |- if(a1)⇣ <m,a1 > //ifTrue
    //<< w,false, cst, w>.m,a> |- if(a1)⇣ <m,a+1> //ifFalse
    @Override
    public void visit(JcIfNode node) throws Exception {

    }

    //<< w,v, cst,w>.m,a>|- inc(i) -> <AffecterVal(i,Val(i,m)+v,m), a+1>
    @Override
    public void visit(JcIncNode node) throws Exception {

    }

    //<m,a> |- invoke(i) -> << w, a+1,cst,w>.m, Val(i, m)>
    @Override
    public void visit(JcInvokeNode node) throws Exception {

    }

    //<m,a> |- load(i) -> << w, Val(i,m), cst,w>.m, a+1>
    @Override
    public void visit(JcLoadNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 * v2, cst,w>.m, a+1>
    @Override
    public void visit(JcMulNode node) throws Exception {

    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, neg v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNegNode node) throws Exception {

    }

    //<< w, v, cst,w>.m,a>|- newarray(i, t) -> <DeclTab(i, v, t, m), a+1>
    @Override
    public void visit(JcNewarrayNode node) throws Exception {
        String ident = node.identifier();
        JcNewarrayNode.Type type = node.type();
        Quadruplet quad = memory.depiler();
        memory.declTab(ident, quad.val(), SORTE.of(type));

        n++;
        node.next().accept(this);
    }

    // [newV] : <m,a> |- new(i,t,var,s) ⇣ <IdentVal(i,t,m,s),a+1>
    // [newC] : << w,v, cst,w>.m,a> |- new(i,t,cst,0) ⇣ <DeclCst(i,v,t,m),a+1>
    // [newM] : << w, v, cst,w>.m,a> |- new(i,t,meth,0) ⇣ <DeclMeth(i, v, t, m),a+1>
    @Override
    public void visit(JcNewNode node) throws Exception {
        String ident = node.identifier();
        JcNewNode.Sorte sorte = node.sorte();
        JcNewNode.Type type = node.type();
        int depth = node.depth();
        Quadruplet quad;
        switch (sorte) {
            case Var:
                memory.identVal(ident, SORTE.of(type), depth);
                break;
            case Cst:
                quad = memory.depiler();
                memory.declCst(ident, quad.val(), SORTE.of(type));
                break;
            case Meth:
                quad = memory.depiler();
                memory.declMeth(ident, quad.val(), SORTE.of(type));
                break;
        }

        n++;
        node.next().accept(this);
    }

    //<m,a> |- nop -> <m, a+1>
    @Override
    public void visit(JcNopNode node) throws Exception {
        n++;
        node.next().accept(this);
    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, not v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNotNode node) throws Exception {

    }

    ////<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 || v2, cst,w>.m, a+1>
    @Override
    public void visit(JcOrNode node) throws Exception {

    }

    //<q.m,a> |- pop -> < m, a+1>
    @Override
    public void visit(JcPopNode node) throws Exception {

    }

    //<m,a> |- push(v) -> << w, v, cst,w>.m, a+1>
    @Override
    public void visit(JcPushNode node) throws Exception {

    }

    //<< w, a1,cst,w>.m,a> |- return -> <m, a1 >
    @Override
    public void visit(JcReturnNode node) throws Exception {

    }

    //<m,a> |- jcstop -> <m, |_>
    @Override
    public void visit(JcStopNode node) throws Exception {
        // do nothing end of the program
    }


    //<< w, v, cst, w >.m,a> |- store(i) -> <AffecterVal(i,v,m), a+1>
    @Override
    public void visit(JcStoreNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 - v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSubNode node) throws Exception {

    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSupNode node) throws Exception {

    }

    //< q1.q2.m,a> |- swap -> < q2.q1.m, a+1>
    @Override
    public void visit(JcSwapNode node) throws Exception {
        n++;
        memory.echanger();
        node.next().accept(this);
    }

    //<< w, v,cst,w>.m,a> |- write -> <Afficher(v,m), a+1>
    @Override
    public void visit(JcWriteNode node) throws Exception {

    }

    //<< w, v,cst,w>.m,a> |- writeln -> <AfficherLn(v,m), a+1>
    @Override
    public void visit(JcWriteLnNode node) throws Exception {

    }
}