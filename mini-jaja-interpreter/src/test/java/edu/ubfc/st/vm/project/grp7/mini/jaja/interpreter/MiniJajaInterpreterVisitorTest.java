package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.memory.IDEMemory;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.ClasseNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.MainNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class MiniJajaInterpreterVisitorTest {
    private MiniJajaInterpreterVisitor mjjVisitor ;
    private Memory memory;
    private MJJInterpreterController controller;

    @Before
    public void init() {
        memory = mock(IDEMemory.class);
        controller = mock(MJJInterpreterController.class);
        mjjVisitor = new MiniJajaInterpreterVisitor(memory, controller);
    }

    @Test
    public void nullDecls__classeNode__visit() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("C");

        MainNode main = mock(MainNode.class);

        ClasseNode classe = ClasseNode.builder()
                                .identifier(ident)
                                .decls(null)
                                .methmain(main)
                                .line(0)
                                .column(0)
                                .build();

        classe.accept(mjjVisitor);

        verify(memory).declVar("C", null, null);
        verify(main).accept(mjjVisitor);
        verify(memory).retirerDecl("C");
    }

    @Test
    public void nonNullDecls__classeNode__visit() throws Exception {
        IdentNode ident = mock(IdentNode.class);
        when(ident.value()).thenReturn("C");

        MainNode main = mock(MainNode.class);

        DeclsNode decls = mock(DeclsNode.class);

        ClasseNode classe = ClasseNode.builder()
                .identifier(ident)
                .decls(decls)
                .methmain(main)
                .line(0)
                .column(0)
                .build();

        classe.accept(mjjVisitor);

        InOrder inOrder = inOrder(memory, decls, main);

        inOrder.verify(memory).declVar("C", null, null);
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(main).accept(mjjVisitor);
        inOrder.verify(decls).accept(mjjVisitor);
        inOrder.verify(memory).retirerDecl("C");
    }
}