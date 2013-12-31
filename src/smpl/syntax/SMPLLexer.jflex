package smpl.syntax;
import java_cup.runtime.*;

// Jlex directives

%%

%cup
%public


%class SMPLLexer

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%char
%line

%{
    public int getChar() {
	return yychar + 1;
    }

    public int getLine() {
	return yyline + 1;
    }

    public String getText() {
	return yytext();
    }
%}

nl = [\n\r]|[\r\n]

cc = ([\b\f]|{nl})

ws = {cc}|[\t ]

alpha = [a-zA-Z_"$""#""?""@""~"]

num = [0-9]

alphanum = {alpha}|{num}


identifier = [^#\(\)\[\]\{\}\\\'\,\:" "\;0-9]+[^\(\)\[\]\{\}\\\'\,\:" "\;]*|[0-9]+[^\(\)\[\]\{\}\\\'\,\:" "\0-9]+[^\(\)\[\]\{\}\\\'\,\:" "\;]*
%%


<YYINITIAL> {ws}    {
                    // skip whitespace
                }
<YYINITIAL>	"."	{ return new Symbol(sym.DOT); }

<YYINITIAL>	"+"	{ return new Symbol(sym.PLUS); }
<YYINITIAL>	"-"	{ return new Symbol(sym.MINUS); }
<YYINITIAL>	"*"	{ return new Symbol(sym.MULT); }
<YYINITIAL>	"/"	{ return new Symbol(sym.DIV); }
<YYINITIAL>	"%"	{ return new Symbol(sym.MOD); }

<YYINITIAL>	"&"	{ return new Symbol(sym.BIT_AND); }
<YYINITIAL>	"|"	{ return new Symbol(sym.BIT_OR); }
<YYINITIAL>	"~"	{ return new Symbol(sym.BIT_COMP); }

<YYINITIAL>	"="	{ return new Symbol(sym.EQ); }
<YYINITIAL>	">"	{ return new Symbol(sym.GT); }
<YYINITIAL>	"<"	{ return new Symbol(sym.LT); }
<YYINITIAL>	"<=" { return new Symbol(sym.LTEQ); }
<YYINITIAL>	">="	{ return new Symbol(sym.GTEQ); }
<YYINITIAL>	"!="	{ return new Symbol(sym.NOT_EQ); }

<YYINITIAL>	"and" { return new Symbol(sym.LOGIC_AND); }
<YYINITIAL>	"or"	{ return new Symbol(sym.LOGIC_OR); }
<YYINITIAL>	"not"	{ return new Symbol(sym.LOGIC_NOT); }

<YYINITIAL>	"@"		{ return new Symbol(sym.CONCAT); }

<YYINITIAL>	{nl}	{
                    //skip newline, but reset char counter
                    yychar = 0;
                }

<YYINITIAL>	"//".*|"/*".*"*/"	{
                    // ignore comments
                 }

<YYINITIAL>    {num}+ {
         // INTEGER
         return new Symbol(sym.INTEGER,
         new Integer(yytext()));
         }

<YYINITIAL>   {num}*\.{num}+ {
         // REAL
         return new Symbol(sym.DOUBLE,
         new Double(yytext()));
         }


<YYINITIAL> \"[^\"\\]*(\\.[^\"\\]*)*\" {
                    return new Symbol(sym.STRING, yytext()
                        .substring(1, yytext().length() - 1));
                }



<YYINITIAL>	"#"\\[0-9a-fA-F]+	{
                    return new Symbol(sym.HEX_CHARACTER, yytext());
                 }
<YYINITIAL>	"'"(.|"\t"|"\n"|\\\\|"\f"|"\'")"'"	{
                    return new Symbol(sym.CHARACTER, yytext()
                        .substring(1, yytext().length() - 1));
                 }

<YYINITIAL>	"#t"		{ return new Symbol(sym.TRUE); }
<YYINITIAL>	"#f"		{ return new Symbol(sym.FALSE); }
<YYINITIAL>	"#e"		{ return new Symbol(sym.EMPTY_LIST); }

<YYINITIAL> 	"pair" 		{return new Symbol(sym.PAIR);}
<YYINITIAL> 	"pair?" 	{return new Symbol(sym.PAIR_QS);}
<YYINITIAL> 	"car" 		{return new Symbol(sym.CAR);}
<YYINITIAL>	"cdr" 		{return new Symbol(sym.CDR);}
<YYINITIAL>	"size" 		{return new Symbol(sym.SIZE);}
<YYINITIAL>	"eqv?" 		{return new Symbol(sym.EQV);}
<YYINITIAL>	"equal?" 		{return new Symbol(sym.EQ_COMPARE);}
<YYINITIAL>	"substr" 		{return new Symbol(sym.SUBSTR);}

<YYINITIAL>	"be"|"BE"		{ return new Symbol(sym.BE); }
<YYINITIAL>	"ref"|"REF"		{ return new Symbol(sym.REF); }
<YYINITIAL>	"dynamic"|"DYNAMIC"	{ return new Symbol(sym.DYNAMIC); }

<YYINITIAL>	"proc"|"PROC"	{ return new Symbol(sym.PROC); }
<YYINITIAL>	"call"|"CALL"	{ return new Symbol(sym.CALL); }
<YYINITIAL>	"lazy"|"LAZY"	{ return new Symbol(sym.LAZY); }
<YYINITIAL>	"let"|"LET"	{ return new Symbol(sym.LET); }
<YYINITIAL>	"def"|"DEF"	{ return new Symbol(sym.DEF); }

<YYINITIAL>	"if"|"IF"	{ return new Symbol(sym.IF); }
<YYINITIAL>	"then"|"THEN"	{ return new Symbol(sym.THEN); }
<YYINITIAL>	"else"|"ELSE"	{ return new Symbol(sym.ELSE); }

<YYINITIAL>	"case"	{ return new Symbol(sym.CASE); }

<YYINITIAL>	"print"	{ return new Symbol(sym.PRINT); }
<YYINITIAL>	"println"	{ return new Symbol(sym.PRINT_LN); }

<YYINITIAL>	"read"	{ return new Symbol(sym.READ); }
<YYINITIAL>	"readint"	{ return new Symbol(sym.READ_INT); }

<YYINITIAL>	"[:"	{ return new Symbol(sym.LVBRACKET); }
<YYINITIAL>	":]"	{ return new Symbol(sym.RVBRACKET); }

<YYINITIAL>	"["	{ return new Symbol(sym.LBRACKET); }
<YYINITIAL>	"]"	{ return new Symbol(sym.RBRACKET); }

<YYINITIAL>	"("	{ return new Symbol(sym.LPAREN); }
<YYINITIAL>	")"	{ return new Symbol(sym.RPAREN); }

<YYINITIAL>	"{"	{ return new Symbol(sym.LBRACE); }
<YYINITIAL>	"}"	{ return new Symbol(sym.RBRACE); }

<YYINITIAL>	":="	{ return new Symbol(sym.ASSIGN); }
<YYINITIAL>	":"	{ return new Symbol(sym.COLON); }
<YYINITIAL>	";"	{ return new Symbol(sym.SEMI_COLON); }
<YYINITIAL>	","	{ return new Symbol(sym.COMMA); }

<YYINITIAL>	{identifier}	{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
