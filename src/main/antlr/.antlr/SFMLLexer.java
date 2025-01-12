// Generated from d:\Repos\Minecraft\Forge\SuperFactoryManager\src\main\antlr\SFML.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SFMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, HAS=4, OVERALL=5, SOME=6, ONE=7, LONE=8, NO=9, TRUE=10, 
		FALSE=11, NOT=12, AND=13, OR=14, GT=15, LT=16, EQ=17, LE=18, GE=19, MOVE=20, 
		FROM=21, TO=22, INPUT=23, OUTPUT=24, WHERE=25, SLOTS=26, RETAIN=27, EACH=28, 
		TOP=29, BOTTOM=30, NORTH=31, EAST=32, SOUTH=33, WEST=34, SIDE=35, TICKS=36, 
		SECONDS=37, REDSTONE=38, PULSE=39, DO=40, WORLD=41, PROGRAM=42, END=43, 
		NAME=44, EVERY=45, COMMA=46, COLON=47, DASH=48, LPAREN=49, RPAREN=50, 
		IDENTIFIER=51, NUMBER=52, STRING=53, LINE_COMMENT=54, WS=55;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "HAS", "OVERALL", "SOME", "ONE", "LONE", "NO", 
			"TRUE", "FALSE", "NOT", "AND", "OR", "GT", "LT", "EQ", "LE", "GE", "MOVE", 
			"FROM", "TO", "INPUT", "OUTPUT", "WHERE", "SLOTS", "RETAIN", "EACH", 
			"TOP", "BOTTOM", "NORTH", "EAST", "SOUTH", "WEST", "SIDE", "TICKS", "SECONDS", 
			"REDSTONE", "PULSE", "DO", "WORLD", "PROGRAM", "END", "NAME", "EVERY", 
			"COMMA", "COLON", "DASH", "LPAREN", "RPAREN", "IDENTIFIER", "NUMBER", 
			"STRING", "LINE_COMMENT", "WS", "A", "B", "C", "D", "E", "F", "G", "H", 
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
			"W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "','", "':'", 
			"'-'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "THEN", "ELSE", "HAS", "OVERALL", "SOME", "ONE", "LONE", 
			"NO", "TRUE", "FALSE", "NOT", "AND", "OR", "GT", "LT", "EQ", "LE", "GE", 
			"MOVE", "FROM", "TO", "INPUT", "OUTPUT", "WHERE", "SLOTS", "RETAIN", 
			"EACH", "TOP", "BOTTOM", "NORTH", "EAST", "SOUTH", "WEST", "SIDE", "TICKS", 
			"SECONDS", "REDSTONE", "PULSE", "DO", "WORLD", "PROGRAM", "END", "NAME", 
			"EVERY", "COMMA", "COLON", "DASH", "LPAREN", "RPAREN", "IDENTIFIER", 
			"NUMBER", "STRING", "LINE_COMMENT", "WS"
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


	public SFMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SFML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u01fa\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3"+
		"*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3"+
		".\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\7\64"+
		"\u0196\n\64\f\64\16\64\u0199\13\64\3\64\5\64\u019c\n\64\3\65\6\65\u019f"+
		"\n\65\r\65\16\65\u01a0\3\66\3\66\3\66\3\66\7\66\u01a7\n\66\f\66\16\66"+
		"\u01aa\13\66\3\66\3\66\3\67\3\67\3\67\3\67\7\67\u01b2\n\67\f\67\16\67"+
		"\u01b5\13\67\3\67\3\67\5\67\u01b9\n\67\3\67\5\67\u01bc\n\67\3\67\3\67"+
		"\38\68\u01c1\n8\r8\168\u01c2\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3"+
		">\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3"+
		"J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R\2\2S\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q\2s\2u\2w\2"+
		"y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d"+
		"\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f"+
		"\2\u00a1\2\u00a3\2\3\2\"\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\3\2$$\4\2\f"+
		"\f\17\17\5\2\13\f\17\17\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4"+
		"\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPp"+
		"p\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2"+
		"YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u01e8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3\u00a5\3\2\2\2\5\u00a8"+
		"\3\2\2\2\7\u00ad\3\2\2\2\t\u00b2\3\2\2\2\13\u00b6\3\2\2\2\r\u00be\3\2"+
		"\2\2\17\u00c3\3\2\2\2\21\u00c7\3\2\2\2\23\u00cc\3\2\2\2\25\u00cf\3\2\2"+
		"\2\27\u00d4\3\2\2\2\31\u00da\3\2\2\2\33\u00de\3\2\2\2\35\u00e2\3\2\2\2"+
		"\37\u00e5\3\2\2\2!\u00e8\3\2\2\2#\u00eb\3\2\2\2%\u00ee\3\2\2\2\'\u00f1"+
		"\3\2\2\2)\u00f4\3\2\2\2+\u00f9\3\2\2\2-\u00fe\3\2\2\2/\u0101\3\2\2\2\61"+
		"\u0107\3\2\2\2\63\u010e\3\2\2\2\65\u0114\3\2\2\2\67\u011a\3\2\2\29\u0121"+
		"\3\2\2\2;\u0126\3\2\2\2=\u012a\3\2\2\2?\u0131\3\2\2\2A\u0137\3\2\2\2C"+
		"\u013c\3\2\2\2E\u0142\3\2\2\2G\u0147\3\2\2\2I\u014c\3\2\2\2K\u0152\3\2"+
		"\2\2M\u015a\3\2\2\2O\u0163\3\2\2\2Q\u0169\3\2\2\2S\u016c\3\2\2\2U\u0172"+
		"\3\2\2\2W\u017a\3\2\2\2Y\u017e\3\2\2\2[\u0183\3\2\2\2]\u0189\3\2\2\2_"+
		"\u018b\3\2\2\2a\u018d\3\2\2\2c\u018f\3\2\2\2e\u0191\3\2\2\2g\u019b\3\2"+
		"\2\2i\u019e\3\2\2\2k\u01a2\3\2\2\2m\u01ad\3\2\2\2o\u01c0\3\2\2\2q\u01c6"+
		"\3\2\2\2s\u01c8\3\2\2\2u\u01ca\3\2\2\2w\u01cc\3\2\2\2y\u01ce\3\2\2\2{"+
		"\u01d0\3\2\2\2}\u01d2\3\2\2\2\177\u01d4\3\2\2\2\u0081\u01d6\3\2\2\2\u0083"+
		"\u01d8\3\2\2\2\u0085\u01da\3\2\2\2\u0087\u01dc\3\2\2\2\u0089\u01de\3\2"+
		"\2\2\u008b\u01e0\3\2\2\2\u008d\u01e2\3\2\2\2\u008f\u01e4\3\2\2\2\u0091"+
		"\u01e6\3\2\2\2\u0093\u01e8\3\2\2\2\u0095\u01ea\3\2\2\2\u0097\u01ec\3\2"+
		"\2\2\u0099\u01ee\3\2\2\2\u009b\u01f0\3\2\2\2\u009d\u01f2\3\2\2\2\u009f"+
		"\u01f4\3\2\2\2\u00a1\u01f6\3\2\2\2\u00a3\u01f8\3\2\2\2\u00a5\u00a6\5\u0081"+
		"A\2\u00a6\u00a7\5{>\2\u00a7\4\3\2\2\2\u00a8\u00a9\5\u0097L\2\u00a9\u00aa"+
		"\5\177@\2\u00aa\u00ab\5y=\2\u00ab\u00ac\5\u008bF\2\u00ac\6\3\2\2\2\u00ad"+
		"\u00ae\5y=\2\u00ae\u00af\5\u0087D\2\u00af\u00b0\5\u0095K\2\u00b0\u00b1"+
		"\5y=\2\u00b1\b\3\2\2\2\u00b2\u00b3\5\177@\2\u00b3\u00b4\5q9\2\u00b4\u00b5"+
		"\5\u0095K\2\u00b5\n\3\2\2\2\u00b6\u00b7\5\u008dG\2\u00b7\u00b8\5\u009b"+
		"N\2\u00b8\u00b9\5y=\2\u00b9\u00ba\5\u0093J\2\u00ba\u00bb\5q9\2\u00bb\u00bc"+
		"\5\u0087D\2\u00bc\u00bd\5\u0087D\2\u00bd\f\3\2\2\2\u00be\u00bf\5\u0095"+
		"K\2\u00bf\u00c0\5\u008dG\2\u00c0\u00c1\5\u0089E\2\u00c1\u00c2\5y=\2\u00c2"+
		"\16\3\2\2\2\u00c3\u00c4\5\u008dG\2\u00c4\u00c5\5\u008bF\2\u00c5\u00c6"+
		"\5y=\2\u00c6\20\3\2\2\2\u00c7\u00c8\5\u0087D\2\u00c8\u00c9\5\u008dG\2"+
		"\u00c9\u00ca\5\u008bF\2\u00ca\u00cb\5y=\2\u00cb\22\3\2\2\2\u00cc\u00cd"+
		"\5\u008bF\2\u00cd\u00ce\5\u008dG\2\u00ce\24\3\2\2\2\u00cf\u00d0\5\u0097"+
		"L\2\u00d0\u00d1\5\u0093J\2\u00d1\u00d2\5\u0099M\2\u00d2\u00d3\5y=\2\u00d3"+
		"\26\3\2\2\2\u00d4\u00d5\5{>\2\u00d5\u00d6\5q9\2\u00d6\u00d7\5\u0087D\2"+
		"\u00d7\u00d8\5\u0095K\2\u00d8\u00d9\5y=\2\u00d9\30\3\2\2\2\u00da\u00db"+
		"\5\u008bF\2\u00db\u00dc\5\u008dG\2\u00dc\u00dd\5\u0097L\2\u00dd\32\3\2"+
		"\2\2\u00de\u00df\5q9\2\u00df\u00e0\5\u008bF\2\u00e0\u00e1\5w<\2\u00e1"+
		"\34\3\2\2\2\u00e2\u00e3\5\u008dG\2\u00e3\u00e4\5\u0093J\2\u00e4\36\3\2"+
		"\2\2\u00e5\u00e6\5}?\2\u00e6\u00e7\5\u0097L\2\u00e7 \3\2\2\2\u00e8\u00e9"+
		"\5\u0087D\2\u00e9\u00ea\5\u0097L\2\u00ea\"\3\2\2\2\u00eb\u00ec\5y=\2\u00ec"+
		"\u00ed\5\u0091I\2\u00ed$\3\2\2\2\u00ee\u00ef\5\u0087D\2\u00ef\u00f0\5"+
		"y=\2\u00f0&\3\2\2\2\u00f1\u00f2\5}?\2\u00f2\u00f3\5y=\2\u00f3(\3\2\2\2"+
		"\u00f4\u00f5\5\u0089E\2\u00f5\u00f6\5\u008dG\2\u00f6\u00f7\5\u009bN\2"+
		"\u00f7\u00f8\5y=\2\u00f8*\3\2\2\2\u00f9\u00fa\5{>\2\u00fa\u00fb\5\u0093"+
		"J\2\u00fb\u00fc\5\u008dG\2\u00fc\u00fd\5\u0089E\2\u00fd,\3\2\2\2\u00fe"+
		"\u00ff\5\u0097L\2\u00ff\u0100\5\u008dG\2\u0100.\3\2\2\2\u0101\u0102\5"+
		"\u0081A\2\u0102\u0103\5\u008bF\2\u0103\u0104\5\u008fH\2\u0104\u0105\5"+
		"\u0099M\2\u0105\u0106\5\u0097L\2\u0106\60\3\2\2\2\u0107\u0108\5\u008d"+
		"G\2\u0108\u0109\5\u0099M\2\u0109\u010a\5\u0097L\2\u010a\u010b\5\u008f"+
		"H\2\u010b\u010c\5\u0099M\2\u010c\u010d\5\u0097L\2\u010d\62\3\2\2\2\u010e"+
		"\u010f\5\u009dO\2\u010f\u0110\5\177@\2\u0110\u0111\5y=\2\u0111\u0112\5"+
		"\u0093J\2\u0112\u0113\5y=\2\u0113\64\3\2\2\2\u0114\u0115\5\u0095K\2\u0115"+
		"\u0116\5\u0087D\2\u0116\u0117\5\u008dG\2\u0117\u0118\5\u0097L\2\u0118"+
		"\u0119\5\u0095K\2\u0119\66\3\2\2\2\u011a\u011b\5\u0093J\2\u011b\u011c"+
		"\5y=\2\u011c\u011d\5\u0097L\2\u011d\u011e\5q9\2\u011e\u011f\5\u0081A\2"+
		"\u011f\u0120\5\u008bF\2\u01208\3\2\2\2\u0121\u0122\5y=\2\u0122\u0123\5"+
		"q9\2\u0123\u0124\5u;\2\u0124\u0125\5\177@\2\u0125:\3\2\2\2\u0126\u0127"+
		"\5\u0097L\2\u0127\u0128\5\u008dG\2\u0128\u0129\5\u008fH\2\u0129<\3\2\2"+
		"\2\u012a\u012b\5s:\2\u012b\u012c\5\u008dG\2\u012c\u012d\5\u0097L\2\u012d"+
		"\u012e\5\u0097L\2\u012e\u012f\5\u008dG\2\u012f\u0130\5\u0089E\2\u0130"+
		">\3\2\2\2\u0131\u0132\5\u008bF\2\u0132\u0133\5\u008dG\2\u0133\u0134\5"+
		"\u0093J\2\u0134\u0135\5\u0097L\2\u0135\u0136\5\177@\2\u0136@\3\2\2\2\u0137"+
		"\u0138\5y=\2\u0138\u0139\5q9\2\u0139\u013a\5\u0095K\2\u013a\u013b\5\u0097"+
		"L\2\u013bB\3\2\2\2\u013c\u013d\5\u0095K\2\u013d\u013e\5\u008dG\2\u013e"+
		"\u013f\5\u0099M\2\u013f\u0140\5\u0097L\2\u0140\u0141\5\177@\2\u0141D\3"+
		"\2\2\2\u0142\u0143\5\u009dO\2\u0143\u0144\5y=\2\u0144\u0145\5\u0095K\2"+
		"\u0145\u0146\5\u0097L\2\u0146F\3\2\2\2\u0147\u0148\5\u0095K\2\u0148\u0149"+
		"\5\u0081A\2\u0149\u014a\5w<\2\u014a\u014b\5y=\2\u014bH\3\2\2\2\u014c\u014d"+
		"\5\u0097L\2\u014d\u014e\5\u0081A\2\u014e\u014f\5u;\2\u014f\u0150\5\u0085"+
		"C\2\u0150\u0151\5\u0095K\2\u0151J\3\2\2\2\u0152\u0153\5\u0095K\2\u0153"+
		"\u0154\5y=\2\u0154\u0155\5u;\2\u0155\u0156\5\u008dG\2\u0156\u0157\5\u008b"+
		"F\2\u0157\u0158\5w<\2\u0158\u0159\5\u0095K\2\u0159L\3\2\2\2\u015a\u015b"+
		"\5\u0093J\2\u015b\u015c\5y=\2\u015c\u015d\5w<\2\u015d\u015e\5\u0095K\2"+
		"\u015e\u015f\5\u0097L\2\u015f\u0160\5\u008dG\2\u0160\u0161\5\u008bF\2"+
		"\u0161\u0162\5y=\2\u0162N\3\2\2\2\u0163\u0164\5\u008fH\2\u0164\u0165\5"+
		"\u0099M\2\u0165\u0166\5\u0087D\2\u0166\u0167\5\u0095K\2\u0167\u0168\5"+
		"y=\2\u0168P\3\2\2\2\u0169\u016a\5w<\2\u016a\u016b\5\u008dG\2\u016bR\3"+
		"\2\2\2\u016c\u016d\5\u009dO\2\u016d\u016e\5\u008dG\2\u016e\u016f\5\u0093"+
		"J\2\u016f\u0170\5\u0087D\2\u0170\u0171\5w<\2\u0171T\3\2\2\2\u0172\u0173"+
		"\5\u008fH\2\u0173\u0174\5\u0093J\2\u0174\u0175\5\u008dG\2\u0175\u0176"+
		"\5}?\2\u0176\u0177\5\u0093J\2\u0177\u0178\5q9\2\u0178\u0179\5\u0089E\2"+
		"\u0179V\3\2\2\2\u017a\u017b\5y=\2\u017b\u017c\5\u008bF\2\u017c\u017d\5"+
		"w<\2\u017dX\3\2\2\2\u017e\u017f\5\u008bF\2\u017f\u0180\5q9\2\u0180\u0181"+
		"\5\u0089E\2\u0181\u0182\5y=\2\u0182Z\3\2\2\2\u0183\u0184\5y=\2\u0184\u0185"+
		"\5\u009bN\2\u0185\u0186\5y=\2\u0186\u0187\5\u0093J\2\u0187\u0188\5\u00a1"+
		"Q\2\u0188\\\3\2\2\2\u0189\u018a\7.\2\2\u018a^\3\2\2\2\u018b\u018c\7<\2"+
		"\2\u018c`\3\2\2\2\u018d\u018e\7/\2\2\u018eb\3\2\2\2\u018f\u0190\7*\2\2"+
		"\u0190d\3\2\2\2\u0191\u0192\7+\2\2\u0192f\3\2\2\2\u0193\u0197\t\2\2\2"+
		"\u0194\u0196\t\3\2\2\u0195\u0194\3\2\2\2\u0196\u0199\3\2\2\2\u0197\u0195"+
		"\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u019c\3\2\2\2\u0199\u0197\3\2\2\2\u019a"+
		"\u019c\7,\2\2\u019b\u0193\3\2\2\2\u019b\u019a\3\2\2\2\u019ch\3\2\2\2\u019d"+
		"\u019f\t\4\2\2\u019e\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u019e\3\2"+
		"\2\2\u01a0\u01a1\3\2\2\2\u01a1j\3\2\2\2\u01a2\u01a8\7$\2\2\u01a3\u01a7"+
		"\n\5\2\2\u01a4\u01a5\7^\2\2\u01a5\u01a7\7$\2\2\u01a6\u01a3\3\2\2\2\u01a6"+
		"\u01a4\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2"+
		"\2\2\u01a9\u01ab\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab\u01ac\7$\2\2\u01ac"+
		"l\3\2\2\2\u01ad\u01ae\7/\2\2\u01ae\u01af\7/\2\2\u01af\u01b3\3\2\2\2\u01b0"+
		"\u01b2\n\6\2\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2"+
		"\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01bb\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6"+
		"\u01bc\7\2\2\3\u01b7\u01b9\7\17\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3"+
		"\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bc\7\f\2\2\u01bb\u01b6\3\2\2\2\u01bb"+
		"\u01b8\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\b\67\2\2\u01ben\3\2\2\2"+
		"\u01bf\u01c1\t\7\2\2\u01c0\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c0"+
		"\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\b8\3\2\u01c5"+
		"p\3\2\2\2\u01c6\u01c7\t\b\2\2\u01c7r\3\2\2\2\u01c8\u01c9\t\t\2\2\u01c9"+
		"t\3\2\2\2\u01ca\u01cb\t\n\2\2\u01cbv\3\2\2\2\u01cc\u01cd\t\13\2\2\u01cd"+
		"x\3\2\2\2\u01ce\u01cf\t\f\2\2\u01cfz\3\2\2\2\u01d0\u01d1\t\r\2\2\u01d1"+
		"|\3\2\2\2\u01d2\u01d3\t\16\2\2\u01d3~\3\2\2\2\u01d4\u01d5\t\17\2\2\u01d5"+
		"\u0080\3\2\2\2\u01d6\u01d7\t\20\2\2\u01d7\u0082\3\2\2\2\u01d8\u01d9\t"+
		"\21\2\2\u01d9\u0084\3\2\2\2\u01da\u01db\t\22\2\2\u01db\u0086\3\2\2\2\u01dc"+
		"\u01dd\t\23\2\2\u01dd\u0088\3\2\2\2\u01de\u01df\t\24\2\2\u01df\u008a\3"+
		"\2\2\2\u01e0\u01e1\t\25\2\2\u01e1\u008c\3\2\2\2\u01e2\u01e3\t\26\2\2\u01e3"+
		"\u008e\3\2\2\2\u01e4\u01e5\t\27\2\2\u01e5\u0090\3\2\2\2\u01e6\u01e7\t"+
		"\30\2\2\u01e7\u0092\3\2\2\2\u01e8\u01e9\t\31\2\2\u01e9\u0094\3\2\2\2\u01ea"+
		"\u01eb\t\32\2\2\u01eb\u0096\3\2\2\2\u01ec\u01ed\t\33\2\2\u01ed\u0098\3"+
		"\2\2\2\u01ee\u01ef\t\34\2\2\u01ef\u009a\3\2\2\2\u01f0\u01f1\t\35\2\2\u01f1"+
		"\u009c\3\2\2\2\u01f2\u01f3\t\36\2\2\u01f3\u009e\3\2\2\2\u01f4\u01f5\t"+
		"\37\2\2\u01f5\u00a0\3\2\2\2\u01f6\u01f7\t \2\2\u01f7\u00a2\3\2\2\2\u01f8"+
		"\u01f9\t!\2\2\u01f9\u00a4\3\2\2\2\f\2\u0197\u019b\u01a0\u01a6\u01a8\u01b3"+
		"\u01b8\u01bb\u01c2\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}