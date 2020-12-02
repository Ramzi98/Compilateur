package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.memory.Memory;

import java.io.IOException;

public class JJCInterpreterVisitor extends JajaCodeASTVisitor {
    private final Memory memory;
    private final JJCInterpreterController controller;

    public JJCInterpreterVisitor(Memory memory, JJCInterpreterController controller) {
        this.memory = memory;
        this.controller = controller;
    }

    @Override
    public void visit(JcInitImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcAddImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcAincImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcAloadImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcAndImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcAstoreImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcCmpImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcDivImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcGotoImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcIfImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcIncImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcInvokeImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcLoadImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcMulImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcNegImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcNewarrayImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcNewImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcNopImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcNotImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcOrImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcPopImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcPushImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcReturnImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcStopImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcStoreImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcSubImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcSupImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcSwapImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcWriteImpl node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(JcWritelnImpl node) throws IllFormedNodeException, IOException {

    }
}