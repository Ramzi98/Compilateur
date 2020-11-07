// Generated from ParserJajaCode.g4 by ANTLR 4.7.2
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link ParserJajaCodeVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <JajaCodeNode> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class ANTLRVisitorImpl<JajaCodeNode> extends AbstractParseTreeVisitor<JajaCodeNode> implements Visitor<JajaCodeNode> {
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @return
     */
    @Override public JajaCodeNode visitClasse(ParserJajaCode.ClasseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitInstr(ParserJajaCode.InstrContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitIdent(ParserJajaCode.IdentContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitValeur(ParserJajaCode.ValeurContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitAdresse(ParserJajaCode.AdresseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitOper(ParserJajaCode.OperContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @return
     */
    @Override public JajaCodeNode visitOper1(ParserJajaCode.Oper1Context ctx) {
        JajaCodeNode n = null;
        if(ctx.children.get(0).getText().equals("NEG")){
            n = (JajaCodeNode) new JcNegNode.Builder().build();
        }else{
            if(ctx.children.get(0).getText() == "NOT") {

                n = (JajaCodeNode) new JcNotNode.Builder().build();
            }
        }

        return n;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitOper2(ParserJajaCode.Oper2Context ctx) {
        JajaCodeNode n = null;
        if(ctx.children.get(0).getText().equals("ADD")){
            return (JajaCodeNode) new JcAddNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "SUB") {

            return (JajaCodeNode) new JcSubNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "MUL") {

            return (JajaCodeNode) new JcMulNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "DIV") {

           return (JajaCodeNode) new JcDivNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "CMP") {

            return (JajaCodeNode) new JcCmpNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "SUP") {

            return (JajaCodeNode) new JcSupNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "OR") {

            return (JajaCodeNode) new JcOrNode.Builder().build();
        }
        if(ctx.children.get(0).getText() == "AND") {

            return (JajaCodeNode) new JcAndNode.Builder().build();
        }


        return n;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitType(ParserJajaCode.TypeContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitSorte(ParserJajaCode.SorteContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitAdr(ParserJajaCode.AdrContext ctx) { return visitChildren(ctx); }
}