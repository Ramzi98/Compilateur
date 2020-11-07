// Generated from ParserJajaCode.g4 by ANTLR 4.7.2
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcNewNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcNewarrayNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcPushNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode.Type;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParserJajaCode}.
 *
 * @param <JajaCodeNode> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Visitor<JajaCodeNode> extends ParseTreeVisitor<JajaCodeNode> {
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#classe}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JajaCodeNode visitClasse(ParserJajaCode.ClasseContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#instr}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JajaCodeNode visitInstr(ParserJajaCode.InstrContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#ident}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    String visitIdent(ParserJajaCode.IdentContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#valeur}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JcPushNode.Valeur visitValeur(ParserJajaCode.ValeurContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#adresse}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    int visitAdresse(ParserJajaCode.AdresseContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#oper}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JajaCodeNode visitOper(ParserJajaCode.OperContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#oper1}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JajaCodeNode visitOper1(ParserJajaCode.Oper1Context ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#oper2}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JajaCodeNode visitOper2(ParserJajaCode.Oper2Context ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#type}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    Type visitType(ParserJajaCode.TypeContext ctx);
    /**
     * Visit a parse tree produced by {@link ParserJajaCode#sorte}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    JcNewNode.Sorte visitSorte(ParserJajaCode.SorteContext ctx);

}