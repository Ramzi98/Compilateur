package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcGotoNode;
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

public class NodeMethodeCompileTest {
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
    public void NodeMethodeCompileVisitor() throws IOException, IllFormedNodeException {

        TypeMethNode typeMeth = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();

        IdentNode ident = IdentNode.builder().value("I").build();

        IdentNode identf = IdentNode.builder().value("f").build();

        NumberNode expression = NumberNode.builder().value(2).build();

        VarNode varNode = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth)
                .identifier(ident)
                .expression(expression)
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

        TypeNode type = TypeNode.builder()
                .line(1)
                .column(0)
                .value(TypeNode.Type.BOOLEAN)
                .build();


        HeaderNode headerNode = HeaderNode.builder()
                .line(1)
                .column(0)
                .type(type)
                .identifier(ident)
                .build();

        HeaderNode headerNode2 = HeaderNode.builder()
                .line(1)
                .column(0)
                .type(type)
                .identifier(ident2)
                .build();

        HeadersNode headersNode = HeadersNode.builder()
                .line(1)
                .column(10)
                .header(headerNode)
                .headers(enil)
                .build();

        HeadersNode headersNode2 = HeadersNode.builder()
                .line(1)
                .column(10)
                .header(headerNode2)
                .headers(headersNode)
                .build();

        TypeMethNode typeMeth2 = TypeMethNode.builder()
                .line(1)
                .column(0)
                .value(TypeMethNode.TypeMeth.INT)
                .build();



        VarNode varNode3 = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth2)
                .identifier(ident)
                .expression(expression)
                .build();

        VarNode varNode2 = VarNode.builder()
                .line(1)
                .column(0)
                .typeMeth(typeMeth2)
                .identifier(ident2)
                .expression(expression2)
                .build();

        VarsNode varsNode2 = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode2)
                .vars(vnil)
                .build();

        VarsNode varsNode3 = VarsNode.builder()
                .line(1)
                .column(10)
                .var(varNode3)
                .vars(varsNode2)
                .build();


        MethodNode methodNode = MethodNode.builder()
                .headers(headersNode2)
                .identifier(identf)
                .instrs(instsrNode)
                .typeMeth(typeMeth)
                .vars(varsNode3)
                .build();


        compiler.visit(methodNode);

        assertThat(compiler.getJajaCodeNodes().size(), is(17));
        assertThat(compiler.getMinijajaNodes().get(1).values().toArray()[0],is(17));

        JcGotoNode jcGotoNode = (JcGotoNode) compiler.getJajaCodeNodes().get(2);
        assertThat(jcGotoNode.adresse(), is(18));

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

    private static final HeadersNode enil = new HeadersNode() {
        @Override
        public HeaderNode header() {
            return null;
        }

        @Override
        public HeadersNode headers() {
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

}
