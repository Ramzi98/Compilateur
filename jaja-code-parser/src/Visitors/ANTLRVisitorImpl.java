// Generated from ParserJajaCode.g4 by ANTLR 4.7.2
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode.Type;

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
    @Override public JajaCodeNode visitClasse(ParserJajaCode.ClasseContext ctx) {

        JajaCodeNode n = null;
        if(ctx.children.size() == 4){

        }
        return n;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitInstr(ParserJajaCode.InstrContext ctx) {

        JajaCodeNode n = null;
        if (ctx.children.size() == 1) {
            switch (ctx.children.get(0).getText()) {
                case "INIT": {
                    n = (JajaCodeNode) new JcInitNode.Builder().build();
                    break;
                }

                case "SWAP": {
                    n = (JajaCodeNode) new JcSwapNode.Builder().build();
                    break;
                }

                case "RETURN": {
                    n = (JajaCodeNode) new JcReturnNode.Builder().build();
                    break;
                }

                case "WRITE": {
                    n = (JajaCodeNode) new JcWritelnNode.Builder().build();
                    break;
                }

                case "WRITELN": {
                    n = (JajaCodeNode) new JcWritelnNode.Builder().build();
                    break;
                }

                case "POP": {
                    n = (JajaCodeNode) new JcPopNode.Builder().build();
                    break;
                }

                case "oper": {
                    n = visitOper((ParserJajaCode.OperContext) ctx.children.get(0));
                    break;
                }

                case "NOP": {
                    n = (JajaCodeNode) new JcNopNode.Builder().build();
                    break;
                }

                case "STOP": {
                    n = (JajaCodeNode) new JcStopNode.Builder().build();
                    break;
                }

            }
        } else {
            if (ctx.children.size() == 4) {

                switch (ctx.children.get(0).getText()) {
                    case "INVOKE": {
                        n = (JajaCodeNode) new JcInvokeNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "PUSH": {
                        n = (JajaCodeNode) new JcPushNode.Builder().valeur(visitValeur((ParserJajaCode.ValeurContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "LOAD": {
                        n = (JajaCodeNode) new JcLoadNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "ALOAD": {
                        n = (JajaCodeNode) new JcAloadNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "STORE": {
                        n = (JajaCodeNode) new JcStoreNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "ASTORE": {
                        n = (JajaCodeNode) new JcAstoreNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "IF": {
                        n = (JajaCodeNode) new JcIfNode.Builder().adresse(visitAdresse((ParserJajaCode.AdresseContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "GOTO": {
                        n = (JajaCodeNode) new JcGotoNode.Builder().adresse(visitAdresse((ParserJajaCode.AdresseContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "INC": {
                        n = (JajaCodeNode) new JcIncNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                    case "AINC": {
                        n = (JajaCodeNode) new JcAincNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).build();
                        break;
                    }

                }

            }else{

                if(ctx.children.size() == 6){

                    if(ctx.children.get(0).getText().equals("NEWARRAY")){


                        n = (JajaCodeNode) new JcNewarrayNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).type(visitType((ParserJajaCode.TypeContext) ctx.children.get(4))).build();

                    }

                }else {
                    if (ctx.children.size() == 10) {

                        if (ctx.children.get(0).getText().equals("NEW")) {


                            n = (JajaCodeNode) new JcNewNode.Builder().identifier(visitIdent((ParserJajaCode.IdentContext) ctx.children.get(2))).type(visitType((ParserJajaCode.TypeContext) ctx.children.get(4))).sorte(visitSorte((ParserJajaCode.SorteContext) ctx.children.get(6))).adresse(visitAdresse((ParserJajaCode.AdresseContext) ctx.children.get(8))).build();

                        }

                    }
                }
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
    @Override public String visitIdent(ParserJajaCode.IdentContext ctx) {

        String identifier = null;
        if(ctx.children.get(0).getText().equals("identifier")){

            identifier =  ctx.children.get(0).getText();
        }

        return identifier;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JcPushNode.Valeur visitValeur(ParserJajaCode.ValeurContext ctx) {

        JcPushNode.Valeur val = null;
        switch(ctx.children.get(0).getText()){

            case "NumberLitteral" : {val =  JcPushNode.Valeur.INT;};
            case "BoolLitteral" : {val =  JcPushNode.Valeur.BOOLEAN;};
            case "StringLitteral" : {val =  JcPushNode.Valeur.INT;};

        }

        return val;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public int visitAdresse(ParserJajaCode.AdresseContext ctx) {

        return Integer.parseInt(ctx.children.get(0).getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JajaCodeNode visitOper(ParserJajaCode.OperContext ctx) {

        JajaCodeNode n = null;
        if(ctx.children.get(0).getText().equals("oper1")){

            n = visitOper1((ParserJajaCode.Oper1Context) ctx.children.get(0));
        }else{

            if(ctx.children.get(0).getText().equals("oper2")){

                n = visitOper2((ParserJajaCode.Oper2Context) ctx.children.get(0));
            }

        }

        return n;
    }
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
    @Override public Type visitType(ParserJajaCode.TypeContext ctx) {
        Type val = null;
        switch(ctx.children.get(0).getText()){

            case "INT" : {val =  Type.INT;};
            case "BOOLEAN" : {val = Type.BOOLEAN;};
            case "VOID" : {val =  Type.INT;};

        }

        return val;

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public JcNewNode.Sorte visitSorte(ParserJajaCode.SorteContext ctx) {

        JcNewNode.Sorte val = null;
        switch(ctx.children.get(0).getText()){

            case "CST" : {val =  JcNewNode.Sorte.Cst;};
            case "VAR" : {val = JcNewNode.Sorte.Var;};
            case "METH" : {val =  JcNewNode.Sorte.Meth;};

        }

        return val;
    }

}