package edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node;

import edu.ubfc.st.vm.project.grp7.ast.ASTNodeBreakpoint;
import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;

public class HeadersNodeImpl extends ASTNodeBreakpoint implements HeadersNode {
    private final HeaderNode header;
    private final HeadersNode headers;

    public HeadersNodeImpl(int line, int column, Breakpoint bp, HeaderNode header, HeadersNode headers) {
        super(line, column, bp);
        this.header = header;
        this.headers = headers;
    }

    @Override
    public HeaderNode header() {
        return this.header;
    }

    @Override
    public HeadersNode headers() {
        return this.headers;
    }

    @Override
    public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
        switch (n) {
            case 0 : return this.header;
            case 1 : return this.headers;
            default: throw new IndexOutOfBoundsException();
        }
    }
}
