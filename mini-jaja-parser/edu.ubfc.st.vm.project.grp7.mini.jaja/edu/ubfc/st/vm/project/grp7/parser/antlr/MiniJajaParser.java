// Generated from C:/Users/Vincent/Documents/git/m1comp7/mini-jaja-parser/src/resources\MiniJaja.g4 by ANTLR 4.8
package edu.ubfc.st.vm.project.grp7.parser.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJajaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, FINAL=2, MAIN=3, RETURN=4, WRITE=5, WRITELN=6, IF=7, ELSE=8, 
		WHILE=9, LBRACK=10, RBRACK=11, LPAR=12, RPAR=13, LBRACE=14, RBRACE=15, 
		COMMA=16, SEMI=17, ASSIGN=18, GT=19, LT=20, BANG=21, EQUAL=22, AND=23, 
		OR=24, INC=25, DEC=26, ADD=27, SUB=28, MUL=29, DIV=30, ADD_ASSIGN=31, 
		VOID=32, INT=33, BOOLEAN=34, STRING=35, BoolLitteral=36, StringLitteral=37, 
		Identifier=38, NumberLitteral=39, WS=40, COMMENT=41, LINE_COMMENT=42;
	public static final int
		RULE_classe = 0, RULE_ident = 1, RULE_decls = 2, RULE_decl = 3, RULE_vars = 4, 
		RULE_var = 5, RULE_vexp = 6, RULE_methode = 7, RULE_methmain = 8, RULE_entetes = 9, 
		RULE_entete = 10, RULE_instrs = 11, RULE_instr = 12, RULE_listexp = 13, 
		RULE_exp = 14, RULE_exp1 = 15, RULE_exp2 = 16, RULE_terme = 17, RULE_fact = 18, 
		RULE_ident1 = 19, RULE_typemeth = 20, RULE_type = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"classe", "ident", "decls", "decl", "vars", "var", "vexp", "methode", 
			"methmain", "entetes", "entete", "instrs", "instr", "listexp", "exp", 
			"exp1", "exp2", "terme", "fact", "ident1", "typemeth", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'final'", "'main'", "'return'", "'write'", "'writeln'", 
			"'if'", "'else'", "'while'", "'['", "']'", "'('", "')'", "'{'", "'}'", 
			"','", "';'", "'='", "'>'", "'<'", "'!'", "'=='", "'&&'", "'||'", "'++'", 
			"'--'", "'+'", "'-'", "'*'", "'/'", "'+='", "'void'", "'int'", "'boolean'", 
			"'string'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "FINAL", "MAIN", "RETURN", "WRITE", "WRITELN", "IF", "ELSE", 
			"WHILE", "LBRACK", "RBRACK", "LPAR", "RPAR", "LBRACE", "RBRACE", "COMMA", 
			"SEMI", "ASSIGN", "GT", "LT", "BANG", "EQUAL", "AND", "OR", "INC", "DEC", 
			"ADD", "SUB", "MUL", "DIV", "ADD_ASSIGN", "VOID", "INT", "BOOLEAN", "STRING", 
			"BoolLitteral", "StringLitteral", "Identifier", "NumberLitteral", "WS", 
			"COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniJaja.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJajaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ClasseContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MiniJajaParser.CLASS, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(MiniJajaParser.LBRACE, 0); }
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public MethmainContext methmain() {
			return getRuleContext(MethmainContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(MiniJajaParser.RBRACE, 0); }
		public ClasseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterClasse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitClasse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitClasse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClasseContext classe() throws RecognitionException {
		ClasseContext _localctx = new ClasseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_classe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(CLASS);
			setState(45);
			ident();
			setState(46);
			match(LBRACE);
			setState(47);
			decls();
			setState(48);
			methmain();
			setState(49);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MiniJajaParser.Identifier, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclsContext extends ParserRuleContext {
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
	 
		public DeclsContext() { }
		public void copyFrom(DeclsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyDeclsContext extends DeclsContext {
		public EmptyDeclsContext(DeclsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEmptyDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEmptyDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEmptyDecls(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiDeclsContext extends DeclsContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MiniJajaParser.SEMI, 0); }
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public MultiDeclsContext(DeclsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMultiDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMultiDecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMultiDecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decls);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
			case VOID:
			case INT:
			case BOOLEAN:
				_localctx = new MultiDeclsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				decl();
				setState(54);
				match(SEMI);
				setState(55);
				decls();
				}
				break;
			case MAIN:
				_localctx = new EmptyDeclsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public MethodeContext methode() {
			return getRuleContext(MethodeContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				var();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				methode();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarsContext extends ParserRuleContext {
		public VarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars; }
	 
		public VarsContext() { }
		public void copyFrom(VarsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyVarsContext extends VarsContext {
		public EmptyVarsContext(VarsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEmptyVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEmptyVars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEmptyVars(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiVarsContext extends VarsContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MiniJajaParser.SEMI, 0); }
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public MultiVarsContext(VarsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMultiVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMultiVars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMultiVars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarsContext vars() throws RecognitionException {
		VarsContext _localctx = new VarsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_vars);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
			case VOID:
			case INT:
			case BOOLEAN:
				_localctx = new MultiVarsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				var();
				setState(65);
				match(SEMI);
				setState(66);
				vars();
				}
				break;
			case RETURN:
			case WRITE:
			case WRITELN:
			case IF:
			case WHILE:
			case RBRACE:
			case Identifier:
				_localctx = new EmptyVarsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	 
		public VarContext() { }
		public void copyFrom(VarContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarNodeContext extends VarContext {
		public TypemethContext typemeth() {
			return getRuleContext(TypemethContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public VexpContext vexp() {
			return getRuleContext(VexpContext.class,0);
		}
		public VarNodeContext(VarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterVarNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitVarNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitVarNode(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayContext extends VarContext {
		public TypemethContext typemeth() {
			return getRuleContext(TypemethContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(MiniJajaParser.LBRACK, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(MiniJajaParser.RBRACK, 0); }
		public ArrayContext(VarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CstContext extends VarContext {
		public TerminalNode FINAL() { return getToken(MiniJajaParser.FINAL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public VexpContext vexp() {
			return getRuleContext(VexpContext.class,0);
		}
		public CstContext(VarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterCst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitCst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitCst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new VarNodeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				typemeth();
				setState(72);
				ident();
				setState(73);
				vexp();
				}
				break;
			case 2:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				typemeth();
				setState(76);
				ident();
				setState(77);
				match(LBRACK);
				setState(78);
				exp(0);
				setState(79);
				match(RBRACK);
				}
				break;
			case 3:
				_localctx = new CstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(FINAL);
				setState(82);
				type();
				setState(83);
				ident();
				setState(84);
				vexp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VexpContext extends ParserRuleContext {
		public VexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vexp; }
	 
		public VexpContext() { }
		public void copyFrom(VexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OmegaAssignContext extends VexpContext {
		public OmegaAssignContext(VexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterOmegaAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitOmegaAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitOmegaAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VexpAssignContext extends VexpContext {
		public TerminalNode ASSIGN() { return getToken(MiniJajaParser.ASSIGN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VexpAssignContext(VexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterVexpAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitVexpAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitVexpAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VexpContext vexp() throws RecognitionException {
		VexpContext _localctx = new VexpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_vexp);
		try {
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGN:
				_localctx = new VexpAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(ASSIGN);
				setState(89);
				exp(0);
				}
				break;
			case SEMI:
				_localctx = new OmegaAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodeContext extends ParserRuleContext {
		public TypemethContext typemeth() {
			return getRuleContext(TypemethContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public EntetesContext entetes() {
			return getRuleContext(EntetesContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(MiniJajaParser.LBRACE, 0); }
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public InstrsContext instrs() {
			return getRuleContext(InstrsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(MiniJajaParser.RBRACE, 0); }
		public MethodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMethode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMethode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMethode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodeContext methode() throws RecognitionException {
		MethodeContext _localctx = new MethodeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_methode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			typemeth();
			setState(94);
			ident();
			setState(95);
			match(LPAR);
			setState(96);
			entetes();
			setState(97);
			match(RPAR);
			setState(98);
			match(LBRACE);
			setState(99);
			vars();
			setState(100);
			instrs();
			setState(101);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethmainContext extends ParserRuleContext {
		public TerminalNode MAIN() { return getToken(MiniJajaParser.MAIN, 0); }
		public TerminalNode LBRACE() { return getToken(MiniJajaParser.LBRACE, 0); }
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public InstrsContext instrs() {
			return getRuleContext(InstrsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(MiniJajaParser.RBRACE, 0); }
		public MethmainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methmain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMethmain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMethmain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMethmain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethmainContext methmain() throws RecognitionException {
		MethmainContext _localctx = new MethmainContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_methmain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(MAIN);
			setState(104);
			match(LBRACE);
			setState(105);
			vars();
			setState(106);
			instrs();
			setState(107);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntetesContext extends ParserRuleContext {
		public EntetesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entetes; }
	 
		public EntetesContext() { }
		public void copyFrom(EntetesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiHeadersContext extends EntetesContext {
		public EnteteContext entete() {
			return getRuleContext(EnteteContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(MiniJajaParser.COMMA, 0); }
		public EntetesContext entetes() {
			return getRuleContext(EntetesContext.class,0);
		}
		public MultiHeadersContext(EntetesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMultiHeaders(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMultiHeaders(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMultiHeaders(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnitHeaderContext extends EntetesContext {
		public EnteteContext entete() {
			return getRuleContext(EnteteContext.class,0);
		}
		public UnitHeaderContext(EntetesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterUnitHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitUnitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitUnitHeader(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyHeaderContext extends EntetesContext {
		public EmptyHeaderContext(EntetesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEmptyHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEmptyHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEmptyHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntetesContext entetes() throws RecognitionException {
		EntetesContext _localctx = new EntetesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_entetes);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new MultiHeadersContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				entete();
				setState(110);
				match(COMMA);
				setState(111);
				entetes();
				}
				break;
			case 2:
				_localctx = new UnitHeaderContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				entete();
				}
				break;
			case 3:
				_localctx = new EmptyHeaderContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnteteContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public EnteteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEntete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEntete(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEntete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnteteContext entete() throws RecognitionException {
		EnteteContext _localctx = new EnteteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_entete);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			type();
			setState(118);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstrsContext extends ParserRuleContext {
		public InstrsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instrs; }
	 
		public InstrsContext() { }
		public void copyFrom(InstrsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiInstrsContext extends InstrsContext {
		public InstrContext instr() {
			return getRuleContext(InstrContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MiniJajaParser.SEMI, 0); }
		public InstrsContext instrs() {
			return getRuleContext(InstrsContext.class,0);
		}
		public MultiInstrsContext(InstrsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMultiInstrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMultiInstrs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMultiInstrs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyInstrsContext extends InstrsContext {
		public EmptyInstrsContext(InstrsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEmptyInstrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEmptyInstrs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEmptyInstrs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstrsContext instrs() throws RecognitionException {
		InstrsContext _localctx = new InstrsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_instrs);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
			case WRITE:
			case WRITELN:
			case IF:
			case WHILE:
			case Identifier:
				_localctx = new MultiInstrsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				instr();
				setState(121);
				match(SEMI);
				setState(122);
				instrs();
				}
				break;
			case RBRACE:
				_localctx = new EmptyInstrsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstrContext extends ParserRuleContext {
		public InstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr; }
	 
		public InstrContext() { }
		public void copyFrom(InstrContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnContext extends InstrContext {
		public TerminalNode RETURN() { return getToken(MiniJajaParser.RETURN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ReturnContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WriteStringContext extends InstrContext {
		public TerminalNode WRITE() { return getToken(MiniJajaParser.WRITE, 0); }
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public TerminalNode STRING() { return getToken(MiniJajaParser.STRING, 0); }
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public WriteStringContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterWriteString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitWriteString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitWriteString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseContext extends InstrContext {
		public TerminalNode IF() { return getToken(MiniJajaParser.IF, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<TerminalNode> LBRACE() { return getTokens(MiniJajaParser.LBRACE); }
		public TerminalNode LBRACE(int i) {
			return getToken(MiniJajaParser.LBRACE, i);
		}
		public List<InstrsContext> instrs() {
			return getRuleContexts(InstrsContext.class);
		}
		public InstrsContext instrs(int i) {
			return getRuleContext(InstrsContext.class,i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(MiniJajaParser.RBRACE); }
		public TerminalNode RBRACE(int i) {
			return getToken(MiniJajaParser.RBRACE, i);
		}
		public TerminalNode ELSE() { return getToken(MiniJajaParser.ELSE, 0); }
		public IfElseContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AppelIContext extends InstrContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public ListexpContext listexp() {
			return getRuleContext(ListexpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public AppelIContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterAppelI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitAppelI(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitAppelI(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends InstrContext {
		public Ident1Context ident1() {
			return getRuleContext(Ident1Context.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MiniJajaParser.ASSIGN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public AssignContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SumContext extends InstrContext {
		public Ident1Context ident1() {
			return getRuleContext(Ident1Context.class,0);
		}
		public TerminalNode ADD_ASSIGN() { return getToken(MiniJajaParser.ADD_ASSIGN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public SumContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitSum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitSum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WriteLnStringContext extends InstrContext {
		public TerminalNode WRITELN() { return getToken(MiniJajaParser.WRITELN, 0); }
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public TerminalNode STRING() { return getToken(MiniJajaParser.STRING, 0); }
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public WriteLnStringContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterWriteLnString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitWriteLnString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitWriteLnString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WriteIdentContext extends InstrContext {
		public TerminalNode WRITE() { return getToken(MiniJajaParser.WRITE, 0); }
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public WriteIdentContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterWriteIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitWriteIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitWriteIdent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends InstrContext {
		public TerminalNode WHILE() { return getToken(MiniJajaParser.WHILE, 0); }
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(MiniJajaParser.LBRACE, 0); }
		public InstrsContext instrs() {
			return getRuleContext(InstrsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(MiniJajaParser.RBRACE, 0); }
		public WhileContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends InstrContext {
		public TerminalNode IF() { return getToken(MiniJajaParser.IF, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(MiniJajaParser.LBRACE, 0); }
		public InstrsContext instrs() {
			return getRuleContext(InstrsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(MiniJajaParser.RBRACE, 0); }
		public IfContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WriteLnIdentContext extends InstrContext {
		public TerminalNode WRITELN() { return getToken(MiniJajaParser.WRITELN, 0); }
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public WriteLnIdentContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterWriteLnIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitWriteLnIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitWriteLnIdent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IncContext extends InstrContext {
		public Ident1Context ident1() {
			return getRuleContext(Ident1Context.class,0);
		}
		public TerminalNode INC() { return getToken(MiniJajaParser.INC, 0); }
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public IncContext(InstrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterInc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitInc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitInc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstrContext instr() throws RecognitionException {
		InstrContext _localctx = new InstrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_instr);
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new AssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				ident1();
				setState(128);
				match(ASSIGN);
				setState(129);
				exp(0);
				}
				break;
			case 2:
				_localctx = new SumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				ident1();
				setState(132);
				match(ADD_ASSIGN);
				setState(133);
				exp(0);
				}
				break;
			case 3:
				_localctx = new IncContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				ident1();
				setState(136);
				match(INC);
				setState(137);
				exp1(0);
				}
				break;
			case 4:
				_localctx = new AppelIContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				ident();
				setState(140);
				match(LPAR);
				setState(141);
				listexp();
				setState(142);
				match(RPAR);
				}
				break;
			case 5:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(144);
				match(RETURN);
				setState(145);
				exp(0);
				}
				break;
			case 6:
				_localctx = new WriteIdentContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
				match(WRITE);
				setState(147);
				match(LPAR);
				setState(148);
				ident();
				setState(149);
				match(RPAR);
				}
				break;
			case 7:
				_localctx = new WriteStringContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(151);
				match(WRITE);
				setState(152);
				match(LPAR);
				setState(153);
				match(STRING);
				setState(154);
				match(RPAR);
				}
				break;
			case 8:
				_localctx = new WriteLnIdentContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(155);
				match(WRITELN);
				setState(156);
				match(LPAR);
				setState(157);
				ident();
				setState(158);
				match(RPAR);
				}
				break;
			case 9:
				_localctx = new WriteLnStringContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(160);
				match(WRITELN);
				setState(161);
				match(LPAR);
				setState(162);
				match(STRING);
				setState(163);
				match(RPAR);
				}
				break;
			case 10:
				_localctx = new IfElseContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(164);
				match(IF);
				setState(165);
				exp(0);
				setState(166);
				match(LBRACE);
				setState(167);
				instrs();
				setState(168);
				match(RBRACE);
				setState(169);
				match(ELSE);
				setState(170);
				match(LBRACE);
				setState(171);
				instrs();
				setState(172);
				match(RBRACE);
				}
				break;
			case 11:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(174);
				match(IF);
				setState(175);
				exp(0);
				setState(176);
				match(LBRACE);
				setState(177);
				instrs();
				setState(178);
				match(RBRACE);
				}
				break;
			case 12:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(180);
				match(WHILE);
				setState(181);
				match(LPAR);
				setState(182);
				exp(0);
				setState(183);
				match(RPAR);
				setState(184);
				match(LBRACE);
				setState(185);
				instrs();
				setState(186);
				match(RBRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListexpContext extends ParserRuleContext {
		public ListexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listexp; }
	 
		public ListexpContext() { }
		public void copyFrom(ListexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnitListExpContext extends ListexpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public UnitListExpContext(ListexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterUnitListExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitUnitListExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitUnitListExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyListexpContext extends ListexpContext {
		public EmptyListexpContext(ListexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEmptyListexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEmptyListexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEmptyListexp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiListexpContext extends ListexpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(MiniJajaParser.COMMA, 0); }
		public ListexpContext listexp() {
			return getRuleContext(ListexpContext.class,0);
		}
		public MultiListexpContext(ListexpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMultiListexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMultiListexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMultiListexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListexpContext listexp() throws RecognitionException {
		ListexpContext _localctx = new ListexpContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listexp);
		try {
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new MultiListexpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				exp(0);
				setState(191);
				match(COMMA);
				setState(192);
				listexp();
				}
				break;
			case 2:
				_localctx = new UnitListExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				exp(0);
				}
				break;
			case 3:
				_localctx = new EmptyListexpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotContext extends ExpContext {
		public TerminalNode BANG() { return getToken(MiniJajaParser.BANG, 0); }
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public NotContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode OR() { return getToken(MiniJajaParser.OR, 0); }
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public OrContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode AND() { return getToken(MiniJajaParser.AND, 0); }
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public AndContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpIsExp1Context extends ExpContext {
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public ExpIsExp1Context(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterExpIsExp1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitExpIsExp1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitExpIsExp1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BANG:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(199);
				match(BANG);
				setState(200);
				exp1(0);
				}
				break;
			case LPAR:
			case BoolLitteral:
			case Identifier:
			case NumberLitteral:
				{
				_localctx = new ExpIsExp1Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(201);
				exp1(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(210);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new AndContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(204);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(205);
						match(AND);
						setState(206);
						exp1(0);
						}
						break;
					case 2:
						{
						_localctx = new OrContext(new ExpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(207);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(208);
						match(OR);
						setState(209);
						exp1(0);
						}
						break;
					}
					} 
				}
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Exp1Context extends ParserRuleContext {
		public Exp1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp1; }
	 
		public Exp1Context() { }
		public void copyFrom(Exp1Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EqualsContext extends Exp1Context {
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public TerminalNode EQUAL() { return getToken(MiniJajaParser.EQUAL, 0); }
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public EqualsContext(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThanContext extends Exp1Context {
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public TerminalNode GT() { return getToken(MiniJajaParser.GT, 0); }
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public GreaterThanContext(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterGreaterThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitGreaterThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitGreaterThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Exp1IsExp2Context extends Exp1Context {
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public Exp1IsExp2Context(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterExp1IsExp2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitExp1IsExp2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitExp1IsExp2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp1Context exp1() throws RecognitionException {
		return exp1(0);
	}

	private Exp1Context exp1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Exp1Context _localctx = new Exp1Context(_ctx, _parentState);
		Exp1Context _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_exp1, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new Exp1IsExp2Context(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(216);
			exp2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(224);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new EqualsContext(new Exp1Context(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp1);
						setState(218);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(219);
						match(EQUAL);
						setState(220);
						exp2(0);
						}
						break;
					case 2:
						{
						_localctx = new GreaterThanContext(new Exp1Context(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp1);
						setState(221);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(222);
						match(GT);
						setState(223);
						exp2(0);
						}
						break;
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Exp2Context extends ParserRuleContext {
		public Exp2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp2; }
	 
		public Exp2Context() { }
		public void copyFrom(Exp2Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SubContext extends Exp2Context {
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public TerminalNode SUB() { return getToken(MiniJajaParser.SUB, 0); }
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public SubContext(Exp2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Exp2IsTermeContext extends Exp2Context {
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public Exp2IsTermeContext(Exp2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterExp2IsTerme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitExp2IsTerme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitExp2IsTerme(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends Exp2Context {
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public TerminalNode ADD() { return getToken(MiniJajaParser.ADD, 0); }
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public PlusContext(Exp2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends Exp2Context {
		public Exp2Context exp2() {
			return getRuleContext(Exp2Context.class,0);
		}
		public TerminalNode SUB() { return getToken(MiniJajaParser.SUB, 0); }
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public MinusContext(Exp2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp2Context exp2() throws RecognitionException {
		return exp2(0);
	}

	private Exp2Context exp2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Exp2Context _localctx = new Exp2Context(_ctx, _parentState);
		Exp2Context _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_exp2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new Exp2IsTermeContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(230);
			terme(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(241);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new MinusContext(new Exp2Context(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp2);
						setState(232);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(233);
						match(SUB);
						setState(234);
						terme(0);
						}
						break;
					case 2:
						{
						_localctx = new PlusContext(new Exp2Context(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp2);
						setState(235);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(236);
						match(ADD);
						setState(237);
						terme(0);
						}
						break;
					case 3:
						{
						_localctx = new SubContext(new Exp2Context(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_exp2);
						setState(238);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(239);
						match(SUB);
						setState(240);
						terme(0);
						}
						break;
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TermeContext extends ParserRuleContext {
		public TermeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terme; }
	 
		public TermeContext() { }
		public void copyFrom(TermeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DivContext extends TermeContext {
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public TerminalNode DIV() { return getToken(MiniJajaParser.DIV, 0); }
		public FactContext fact() {
			return getRuleContext(FactContext.class,0);
		}
		public DivContext(TermeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TermeIsFactContext extends TermeContext {
		public FactContext fact() {
			return getRuleContext(FactContext.class,0);
		}
		public TermeIsFactContext(TermeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterTermeIsFact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitTermeIsFact(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitTermeIsFact(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulContext extends TermeContext {
		public TermeContext terme() {
			return getRuleContext(TermeContext.class,0);
		}
		public TerminalNode MUL() { return getToken(MiniJajaParser.MUL, 0); }
		public FactContext fact() {
			return getRuleContext(FactContext.class,0);
		}
		public MulContext(TermeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitMul(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermeContext terme() throws RecognitionException {
		return terme(0);
	}

	private TermeContext terme(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermeContext _localctx = new TermeContext(_ctx, _parentState);
		TermeContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_terme, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new TermeIsFactContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(247);
			fact();
			}
			_ctx.stop = _input.LT(-1);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(255);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MulContext(new TermeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_terme);
						setState(249);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(250);
						match(MUL);
						setState(251);
						fact();
						}
						break;
					case 2:
						{
						_localctx = new DivContext(new TermeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_terme);
						setState(252);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(253);
						match(DIV);
						setState(254);
						fact();
						}
						break;
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactContext extends ParserRuleContext {
		public FactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fact; }
	 
		public FactContext() { }
		public void copyFrom(FactContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AppelEContext extends FactContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public ListexpContext listexp() {
			return getRuleContext(ListexpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public AppelEContext(FactContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterAppelE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitAppelE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitAppelE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends FactContext {
		public TerminalNode NumberLitteral() { return getToken(MiniJajaParser.NumberLitteral, 0); }
		public NumberContext(FactContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RecExpContext extends FactContext {
		public TerminalNode LPAR() { return getToken(MiniJajaParser.LPAR, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(MiniJajaParser.RPAR, 0); }
		public RecExpContext(FactContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterRecExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitRecExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitRecExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanContext extends FactContext {
		public TerminalNode BoolLitteral() { return getToken(MiniJajaParser.BoolLitteral, 0); }
		public BooleanContext(FactContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FactIsIdent1Context extends FactContext {
		public Ident1Context ident1() {
			return getRuleContext(Ident1Context.class,0);
		}
		public FactIsIdent1Context(FactContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterFactIsIdent1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitFactIsIdent1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitFactIsIdent1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactContext fact() throws RecognitionException {
		FactContext _localctx = new FactContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_fact);
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new FactIsIdent1Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				ident1();
				}
				break;
			case 2:
				_localctx = new AppelEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				ident();
				setState(262);
				match(LPAR);
				setState(263);
				listexp();
				setState(264);
				match(RPAR);
				}
				break;
			case 3:
				_localctx = new BooleanContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(266);
				match(BoolLitteral);
				}
				break;
			case 4:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(267);
				match(NumberLitteral);
				}
				break;
			case 5:
				_localctx = new RecExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(268);
				match(LPAR);
				setState(269);
				exp(0);
				setState(270);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ident1Context extends ParserRuleContext {
		public Ident1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident1; }
	 
		public Ident1Context() { }
		public void copyFrom(Ident1Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayItemContext extends Ident1Context {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(MiniJajaParser.LBRACK, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(MiniJajaParser.RBRACK, 0); }
		public ArrayItemContext(Ident1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterArrayItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitArrayItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitArrayItem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Ident1IsIdentContext extends Ident1Context {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Ident1IsIdentContext(Ident1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterIdent1IsIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitIdent1IsIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitIdent1IsIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ident1Context ident1() throws RecognitionException {
		Ident1Context _localctx = new Ident1Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_ident1);
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new Ident1IsIdentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(274);
				ident();
				}
				break;
			case 2:
				_localctx = new ArrayItemContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				ident();
				setState(276);
				match(LBRACK);
				setState(277);
				exp(0);
				setState(278);
				match(RBRACK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypemethContext extends ParserRuleContext {
		public TypemethContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typemeth; }
	 
		public TypemethContext() { }
		public void copyFrom(TypemethContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeMethIsTypeContext extends TypemethContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeMethIsTypeContext(TypemethContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterTypeMethIsType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitTypeMethIsType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitTypeMethIsType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidContext extends TypemethContext {
		public TerminalNode VOID() { return getToken(MiniJajaParser.VOID, 0); }
		public VoidContext(TypemethContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitVoid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypemethContext typemeth() throws RecognitionException {
		TypemethContext _localctx = new TypemethContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typemeth);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				_localctx = new VoidContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				match(VOID);
				}
				break;
			case INT:
			case BOOLEAN:
				_localctx = new TypeMethIsTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeIsINTContext extends TypeContext {
		public TerminalNode INT() { return getToken(MiniJajaParser.INT, 0); }
		public TypeIsINTContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterTypeIsINT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitTypeIsINT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitTypeIsINT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeIsBooleanContext extends TypeContext {
		public TerminalNode BOOLEAN() { return getToken(MiniJajaParser.BOOLEAN, 0); }
		public TypeIsBooleanContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).enterTypeIsBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJajaListener ) ((MiniJajaListener)listener).exitTypeIsBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJajaVisitor ) return ((MiniJajaVisitor<? extends T>)visitor).visitTypeIsBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_type);
		try {
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				_localctx = new TypeIsINTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				match(INT);
				}
				break;
			case BOOLEAN:
				_localctx = new TypeIsBooleanContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				match(BOOLEAN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 15:
			return exp1_sempred((Exp1Context)_localctx, predIndex);
		case 16:
			return exp2_sempred((Exp2Context)_localctx, predIndex);
		case 17:
			return terme_sempred((TermeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp1_sempred(Exp1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp2_sempred(Exp2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean terme_sempred(TermeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0125\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4=\n\4\3\5\3\5\5\5A\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\5\6H\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\5\b^\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"v\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u0080\n\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\5\16\u00bf\n\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00c7\n\17"+
		"\3\20\3\20\3\20\3\20\5\20\u00cd\n\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00d5\n\20\f\20\16\20\u00d8\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u00e3\n\21\f\21\16\21\u00e6\13\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00f4\n\22\f\22\16\22\u00f7"+
		"\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0102\n\23\f"+
		"\23\16\23\u0105\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u0113\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u011b\n"+
		"\25\3\26\3\26\5\26\u011f\n\26\3\27\3\27\5\27\u0123\n\27\3\27\2\6\36 \""+
		"$\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\2\2\u0135\2.\3\2"+
		"\2\2\4\65\3\2\2\2\6<\3\2\2\2\b@\3\2\2\2\nG\3\2\2\2\fX\3\2\2\2\16]\3\2"+
		"\2\2\20_\3\2\2\2\22i\3\2\2\2\24u\3\2\2\2\26w\3\2\2\2\30\177\3\2\2\2\32"+
		"\u00be\3\2\2\2\34\u00c6\3\2\2\2\36\u00cc\3\2\2\2 \u00d9\3\2\2\2\"\u00e7"+
		"\3\2\2\2$\u00f8\3\2\2\2&\u0112\3\2\2\2(\u011a\3\2\2\2*\u011e\3\2\2\2,"+
		"\u0122\3\2\2\2./\7\3\2\2/\60\5\4\3\2\60\61\7\20\2\2\61\62\5\6\4\2\62\63"+
		"\5\22\n\2\63\64\7\21\2\2\64\3\3\2\2\2\65\66\7(\2\2\66\5\3\2\2\2\678\5"+
		"\b\5\289\7\23\2\29:\5\6\4\2:=\3\2\2\2;=\3\2\2\2<\67\3\2\2\2<;\3\2\2\2"+
		"=\7\3\2\2\2>A\5\f\7\2?A\5\20\t\2@>\3\2\2\2@?\3\2\2\2A\t\3\2\2\2BC\5\f"+
		"\7\2CD\7\23\2\2DE\5\n\6\2EH\3\2\2\2FH\3\2\2\2GB\3\2\2\2GF\3\2\2\2H\13"+
		"\3\2\2\2IJ\5*\26\2JK\5\4\3\2KL\5\16\b\2LY\3\2\2\2MN\5*\26\2NO\5\4\3\2"+
		"OP\7\f\2\2PQ\5\36\20\2QR\7\r\2\2RY\3\2\2\2ST\7\4\2\2TU\5,\27\2UV\5\4\3"+
		"\2VW\5\16\b\2WY\3\2\2\2XI\3\2\2\2XM\3\2\2\2XS\3\2\2\2Y\r\3\2\2\2Z[\7\24"+
		"\2\2[^\5\36\20\2\\^\3\2\2\2]Z\3\2\2\2]\\\3\2\2\2^\17\3\2\2\2_`\5*\26\2"+
		"`a\5\4\3\2ab\7\16\2\2bc\5\24\13\2cd\7\17\2\2de\7\20\2\2ef\5\n\6\2fg\5"+
		"\30\r\2gh\7\21\2\2h\21\3\2\2\2ij\7\5\2\2jk\7\20\2\2kl\5\n\6\2lm\5\30\r"+
		"\2mn\7\21\2\2n\23\3\2\2\2op\5\26\f\2pq\7\22\2\2qr\5\24\13\2rv\3\2\2\2"+
		"sv\5\26\f\2tv\3\2\2\2uo\3\2\2\2us\3\2\2\2ut\3\2\2\2v\25\3\2\2\2wx\5,\27"+
		"\2xy\5\4\3\2y\27\3\2\2\2z{\5\32\16\2{|\7\23\2\2|}\5\30\r\2}\u0080\3\2"+
		"\2\2~\u0080\3\2\2\2\177z\3\2\2\2\177~\3\2\2\2\u0080\31\3\2\2\2\u0081\u0082"+
		"\5(\25\2\u0082\u0083\7\24\2\2\u0083\u0084\5\36\20\2\u0084\u00bf\3\2\2"+
		"\2\u0085\u0086\5(\25\2\u0086\u0087\7!\2\2\u0087\u0088\5\36\20\2\u0088"+
		"\u00bf\3\2\2\2\u0089\u008a\5(\25\2\u008a\u008b\7\33\2\2\u008b\u008c\5"+
		" \21\2\u008c\u00bf\3\2\2\2\u008d\u008e\5\4\3\2\u008e\u008f\7\16\2\2\u008f"+
		"\u0090\5\34\17\2\u0090\u0091\7\17\2\2\u0091\u00bf\3\2\2\2\u0092\u0093"+
		"\7\6\2\2\u0093\u00bf\5\36\20\2\u0094\u0095\7\7\2\2\u0095\u0096\7\16\2"+
		"\2\u0096\u0097\5\4\3\2\u0097\u0098\7\17\2\2\u0098\u00bf\3\2\2\2\u0099"+
		"\u009a\7\7\2\2\u009a\u009b\7\16\2\2\u009b\u009c\7%\2\2\u009c\u00bf\7\17"+
		"\2\2\u009d\u009e\7\b\2\2\u009e\u009f\7\16\2\2\u009f\u00a0\5\4\3\2\u00a0"+
		"\u00a1\7\17\2\2\u00a1\u00bf\3\2\2\2\u00a2\u00a3\7\b\2\2\u00a3\u00a4\7"+
		"\16\2\2\u00a4\u00a5\7%\2\2\u00a5\u00bf\7\17\2\2\u00a6\u00a7\7\t\2\2\u00a7"+
		"\u00a8\5\36\20\2\u00a8\u00a9\7\20\2\2\u00a9\u00aa\5\30\r\2\u00aa\u00ab"+
		"\7\21\2\2\u00ab\u00ac\7\n\2\2\u00ac\u00ad\7\20\2\2\u00ad\u00ae\5\30\r"+
		"\2\u00ae\u00af\7\21\2\2\u00af\u00bf\3\2\2\2\u00b0\u00b1\7\t\2\2\u00b1"+
		"\u00b2\5\36\20\2\u00b2\u00b3\7\20\2\2\u00b3\u00b4\5\30\r\2\u00b4\u00b5"+
		"\7\21\2\2\u00b5\u00bf\3\2\2\2\u00b6\u00b7\7\13\2\2\u00b7\u00b8\7\16\2"+
		"\2\u00b8\u00b9\5\36\20\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\7\20\2\2\u00bb"+
		"\u00bc\5\30\r\2\u00bc\u00bd\7\21\2\2\u00bd\u00bf\3\2\2\2\u00be\u0081\3"+
		"\2\2\2\u00be\u0085\3\2\2\2\u00be\u0089\3\2\2\2\u00be\u008d\3\2\2\2\u00be"+
		"\u0092\3\2\2\2\u00be\u0094\3\2\2\2\u00be\u0099\3\2\2\2\u00be\u009d\3\2"+
		"\2\2\u00be\u00a2\3\2\2\2\u00be\u00a6\3\2\2\2\u00be\u00b0\3\2\2\2\u00be"+
		"\u00b6\3\2\2\2\u00bf\33\3\2\2\2\u00c0\u00c1\5\36\20\2\u00c1\u00c2\7\22"+
		"\2\2\u00c2\u00c3\5\34\17\2\u00c3\u00c7\3\2\2\2\u00c4\u00c7\5\36\20\2\u00c5"+
		"\u00c7\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2"+
		"\2\2\u00c7\35\3\2\2\2\u00c8\u00c9\b\20\1\2\u00c9\u00ca\7\27\2\2\u00ca"+
		"\u00cd\5 \21\2\u00cb\u00cd\5 \21\2\u00cc\u00c8\3\2\2\2\u00cc\u00cb\3\2"+
		"\2\2\u00cd\u00d6\3\2\2\2\u00ce\u00cf\f\5\2\2\u00cf\u00d0\7\31\2\2\u00d0"+
		"\u00d5\5 \21\2\u00d1\u00d2\f\4\2\2\u00d2\u00d3\7\32\2\2\u00d3\u00d5\5"+
		" \21\2\u00d4\u00ce\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\37\3\2\2\2\u00d8\u00d6\3\2\2"+
		"\2\u00d9\u00da\b\21\1\2\u00da\u00db\5\"\22\2\u00db\u00e4\3\2\2\2\u00dc"+
		"\u00dd\f\5\2\2\u00dd\u00de\7\30\2\2\u00de\u00e3\5\"\22\2\u00df\u00e0\f"+
		"\4\2\2\u00e0\u00e1\7\25\2\2\u00e1\u00e3\5\"\22\2\u00e2\u00dc\3\2\2\2\u00e2"+
		"\u00df\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5!\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\b\22\1\2\u00e8\u00e9"+
		"\5$\23\2\u00e9\u00f5\3\2\2\2\u00ea\u00eb\f\6\2\2\u00eb\u00ec\7\36\2\2"+
		"\u00ec\u00f4\5$\23\2\u00ed\u00ee\f\5\2\2\u00ee\u00ef\7\35\2\2\u00ef\u00f4"+
		"\5$\23\2\u00f0\u00f1\f\4\2\2\u00f1\u00f2\7\36\2\2\u00f2\u00f4\5$\23\2"+
		"\u00f3\u00ea\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f3\u00f0\3\2\2\2\u00f4\u00f7"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6#\3\2\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f8\u00f9\b\23\1\2\u00f9\u00fa\5&\24\2\u00fa\u0103\3"+
		"\2\2\2\u00fb\u00fc\f\5\2\2\u00fc\u00fd\7\37\2\2\u00fd\u0102\5&\24\2\u00fe"+
		"\u00ff\f\4\2\2\u00ff\u0100\7 \2\2\u0100\u0102\5&\24\2\u0101\u00fb\3\2"+
		"\2\2\u0101\u00fe\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104%\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0113\5(\25\2"+
		"\u0107\u0108\5\4\3\2\u0108\u0109\7\16\2\2\u0109\u010a\5\34\17\2\u010a"+
		"\u010b\7\17\2\2\u010b\u0113\3\2\2\2\u010c\u0113\7&\2\2\u010d\u0113\7)"+
		"\2\2\u010e\u010f\7\16\2\2\u010f\u0110\5\36\20\2\u0110\u0111\7\17\2\2\u0111"+
		"\u0113\3\2\2\2\u0112\u0106\3\2\2\2\u0112\u0107\3\2\2\2\u0112\u010c\3\2"+
		"\2\2\u0112\u010d\3\2\2\2\u0112\u010e\3\2\2\2\u0113\'\3\2\2\2\u0114\u011b"+
		"\5\4\3\2\u0115\u0116\5\4\3\2\u0116\u0117\7\f\2\2\u0117\u0118\5\36\20\2"+
		"\u0118\u0119\7\r\2\2\u0119\u011b\3\2\2\2\u011a\u0114\3\2\2\2\u011a\u0115"+
		"\3\2\2\2\u011b)\3\2\2\2\u011c\u011f\7\"\2\2\u011d\u011f\5,\27\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011d\3\2\2\2\u011f+\3\2\2\2\u0120\u0123\7#\2\2\u0121"+
		"\u0123\7$\2\2\u0122\u0120\3\2\2\2\u0122\u0121\3\2\2\2\u0123-\3\2\2\2\30"+
		"<@GX]u\177\u00be\u00c6\u00cc\u00d4\u00d6\u00e2\u00e4\u00f3\u00f5\u0101"+
		"\u0103\u0112\u011a\u011e\u0122";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}