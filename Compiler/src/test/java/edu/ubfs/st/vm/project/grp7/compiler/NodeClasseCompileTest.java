package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.CompilerException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeClasseCompileTest {
    public CompilerVisitor compiler;

    @Before
    public void start() {

        compiler = new CompilerVisitor();
        Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<>();
        MiniJajaNode classe = new MiniJajaNode() {
            @Override
            public Breakpoint breakpoint() {
                return null;
            }

            @Override
            public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public int line() {
                return 0;
            }

            @Override
            public int column() {
                return 0;
            }
        };
        ArrayList<HashMap<MiniJajaNode,Integer>> miniJajaNodes = new ArrayList<>();
        HashMap<MiniJajaNode,Integer>startingHash = new HashMap<>();
        startingHash.put(classe,1);
        stack.push(startingHash);
        miniJajaNodes.add(startingHash);
        compiler.setStack(stack);
        compiler.setMinijajaNodes(miniJajaNodes);

    }


    @Test
    public void NodeClasseCompilerVisitor() throws IOException, IllFormedNodeException, CompilerException {

        TypeMethNode typeMeth = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();

        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identclasse = IdentNode.builder().value("C").build();

        NumberNode expression = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth)
                .identifier(ident)
                .expression(expression)
                .build();

        VarsNode varsNode = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode)
                .vars(vnil)
                .build();

        DeclsNode declsNode = DeclsNode.builder()
                .line(1)
                .column(0)
                .decl(varNode)
                .decls(vnil1)
                .build();

        IdentNode ident2 = IdentNode.builder().value("I").build();
        NumberNode expression2 = NumberNode.builder().value(2).build();
        AssignNode assignNode = AssignNode.builder().identifier(ident2).expression(expression2).build();
        InstrsNode instsrNode = InstrsNode.builder()
                .line(1)
                .column(0)
                .instruction(assignNode)
                .instrs(instrs)
                .build();

        MainNode mainNode = MainNode.builder()
                .line(1)
                .column(0)
                .vars(varsNode)
                .instrs(instsrNode)
                .build();

        ClasseNode classeNode = ClasseNode.builder()
                .line(1)
                .column(0)
                .identifier(identclasse)
                .decls(declsNode)
                .methmain(mainNode)
                .build();

        compiler.visit(classeNode);

        assertThat(compiler.getJajaCodeNodes().size(), is(14));
        assertThat(compiler.getJajaCodeNodes().get(6),is(compiler.getJajaCodeNodes().get(5).children(0)));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(14));

    }

    private static final VarsNode vnil = new VarsNode() {
        @Override
        public MiniJajaNode var() {
            return null;
        }

        @Override
        public VarsNode vars() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    };

    private static final DeclsNode vnil1 = new DeclsNode() {
        @Override
        public MiniJajaNode decl() {
            return null;
        }

        @Override
        public DeclsNode decls() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            return null;
        }

        @Override
        public int line() {
            return 0;
        }

        @Override
        public int column() {
            return 0;
        }
    };

    private static final InstrsNode instrs = new InstrsNode() {
        @Override
        public MiniJajaNode instruction() {
            return null;
        }

        @Override
        public InstrsNode instrs() {
            return null;
        }

        @Override
        public Breakpoint breakpoint() {
            return null;
        }

        @Override
        public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int line() {
            return 13;
        }

        @Override
        public int column() {
            return 10;
        }
    };

}
