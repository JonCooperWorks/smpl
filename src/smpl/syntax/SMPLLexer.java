/* Specification for CrystalGen tokens */
// user customisations
package smpl.syntax;
import java_cup.runtime.*;
// Jlex directives


public class SMPLLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar() {
	return yychar + 1;
    }
    public int getLine() {
	return yyline + 1;
    }
    public String getText() {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public SMPLLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public SMPLLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private SMPLLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYSTRING = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		159
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"31:8,70:2,22,31,70,22,31:18,1,14,30,25,31,7,8,32,64,65,5,3,69,4,2,6,29:2,24" +
":8,62,68,13,11,12,31,21,46,35,27,43,36,38,31,58,48,31:2,53,47,45,51,50,31,3" +
"7,60,56,31:4,44,55,61,23,63,31:3,15,28,42,17,34,33,31,57,41,31:2,52,40,16,1" +
"8,49,31,19,59,20,31:3,26,39,54,66,9,67,10,31,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,218,
"0,1,2,3,4,5,6,7,1:7,3,8,1:3,9,1,9,1:16,9:2,10,1,9,1:4,11,9:6,1:2,12,9:3,13," +
"4,14,15,16,9:13,17,9:6,18,9,19,20,21,22,23,24,25,26,27,28,29,30,31,32,20,33" +
",34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,10,57" +
",15,58,19,59,60,61,17,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,23,77,78" +
",79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102" +
",70,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120" +
",121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,13" +
"9,140,141")[0];

	private int yy_nxt[][] = unpackFromString(142,71,
"1,2,3,63:2,87,91,87:10,177,87,179,202,87,4,87,5,61,87,203,94,5,86,87,90,87," +
"204,96,205,180,87:3,98,206,181,87:4,100,207,208,87,182,183,87:2,209,87:4,6," +
"7,8,9,10,11,12,13,14,62,-1:73,93,95,97,99,101,103,105,107,109,111,113,115,1" +
"17,119,121,-1,123,-1:2,125,-1:51,87:22,15,87:4,15,-1,87,-1,87:28,-1:9,87,-1" +
":2,108,110,112,114,116,118,120,122,124,126,128,130,185,210,211,87,186,87:2," +
"132,87:8,-1,87,-1,87:28,-1:9,87,-1:2,15,87:21,5,87:4,5,-1,87,-1,87:28,-1:9," +
"87,-1:62,23,-1:19,24,-1:51,25,-1:8,65,16:20,87,16:7,65,16,65,16:28,65:9,16," +
"-1:2,87:28,-1,87,-1,87:28,-1:9,87,-1:15,41,-1,41,-1:6,41,-1:2,41:3,-1:3,41:" +
"4,-1,41,-1:3,41:2,-1:2,41,-1:26,87:28,-1,87,-1,87:8,200,87:19,-1:9,87,-1:2," +
"87:28,-1,87,-1,87:19,176,87:8,-1:9,87,-1:20,17,-1:2,127,-1:2,129,-1,131,-1:" +
"4,18,19,-1:38,3,87:21,5,87:4,5,-1,87,-1,87:28,-1:9,87,-1:15,64,-1,64,-1:6,6" +
"4,-1:2,64:3,-1:3,64:4,-1,64,-1:3,64:2,-1:2,64,-1:25,65:21,-1,65:48,-1:32,42" +
",-1:39,86:22,133,86:6,21,86:40,-1:29,88,-1:42,151,102:3,142,102:16,87,102,1" +
"44:2,102:3,144,151,102,151,102:28,151:9,102,-1,135:21,-1,137,135:47,-1:2,87" +
":3,102,16,87:23,-1,87,-1,87:28,-1:9,87,-1,151:4,157,151:16,-1,151:48,-1,26," +
"-1:20,26,-1:47,26,-1:2,87:28,-1,87,-1,87,20,87:26,-1:9,87,-1,27,-1:20,27,-1" +
":47,27,-1:2,87:28,-1,87,-1,87:3,20,87:24,-1:9,87,-1,28,-1:20,28,-1:47,28,-1" +
":2,87:28,-1,87,-1,22,87:27,-1:9,87,-1,29,-1:20,29,-1:47,29,-1:2,87:28,-1,87" +
",-1,87:5,22,87:22,-1:9,87,-1,30,-1:20,30,-1:47,30,-1,31,-1:20,31,-1:47,31,-" +
"1:2,87:28,-1,87,-1,39,87:27,-1:9,87,-1,32,-1:20,32,-1:47,32,-1:2,87:13,146," +
"87:14,-1,87,-1,40,87:27,-1:9,87,-1,33,-1:20,33,-1:47,33,-1,26,87:20,66,87:7" +
",-1,87,-1,87:28,-1:9,66,-1,34,-1:20,34,-1:47,34,-1,27,87:20,67,87:7,-1,87,-" +
"1,87:28,-1:9,67,-1,35,-1:20,35,-1:47,35,-1,28,87:20,68,87:7,-1,87,-1,87:28," +
"-1:9,68,-1,36,-1:9,139,-1:10,36,-1:47,36,-1,29,87:20,69,87:7,-1,87,-1,87:28" +
",-1:9,69,-1,37,-1:9,141,-1:10,37,-1:47,37,-1,30,87:20,70,87:7,-1,87,-1,87:2" +
"8,-1:9,70,-1:11,143,-1:60,31,87:20,71,87:7,-1,87,-1,87:28,-1:9,71,-1:16,145" +
",-1:55,32,87:20,72,87:7,-1,87,-1,87:28,-1:9,72,-1:18,147,-1:53,33,87:20,73," +
"87:7,-1,87,-1,87:28,-1:9,73,-1:19,149,-1:52,34,87:20,74,87:7,-1,87,-1,87:28" +
",-1:9,74,-1,38,-1:20,38,-1:47,38,-1,35,87:20,75,87:7,-1,87,-1,87:28,-1:9,75" +
",-1,36,87:9,150,87:10,76,87:7,-1,87,-1,87:28,-1:9,76,-1,37,87:9,152,87:10,7" +
"7,87:7,-1,87,-1,87:28,-1:9,77,-1,38,87:20,78,87:7,-1,87,-1,87:28,-1:9,78,-1" +
",86:21,-1,86:48,-1:2,87:28,-1,87,-1,87:5,40,87:22,-1:9,87,-1:2,87:28,-1,87," +
"-1,87:5,39,87:22,-1:9,87,-1:16,135,-1:3,135,-1:2,135,-1:8,79,135,-1:39,87:1" +
"8,43,87:9,-1,87,-1,87:28,-1:9,87,-1,44,-1:20,44,-1:47,44,-1:2,87:28,-1,87,-" +
"1,87:23,43,87:4,-1:9,87,-1,45,-1:20,45,-1:47,45,-1,151,102:3,142,89,102:15," +
"87,102,144:2,102:3,144,151,102,151,102:28,151:9,102,-1,46,-1:20,46,-1:47,46" +
",-1,151,144:3,169,144:16,87,144:7,151,144,151,144:28,151:9,144,-1:17,153,-1" +
":55,87:15,48,87:12,-1,87,-1,87:28,-1:9,87,-1:20,155,-1:52,87:14,49,87:13,-1" +
",87,-1,87:28,-1:9,87,-1,47,-1:20,47,-1:47,47,-1,44,87:20,80,87:7,-1,87,-1,8" +
"7:28,-1:9,80,-1,45,87:20,81,87:7,-1,87,-1,87:28,-1:9,81,-1,55,-1:20,55,-1:4" +
"7,55,-1,46,87:20,82,87:7,-1,87,-1,87:28,-1:9,82,-1,56,-1:20,56,-1:47,56,-1," +
"47,87:20,83,87:7,-1,87,-1,87:28,-1:9,83,-1,151:4,157,92,151:15,-1,151:48,-1" +
":2,87:28,-1,87,-1,87:20,50,87:7,-1:9,87,1,-1:72,87:28,-1,87,-1,87,51,87:26," +
"-1:9,87,-1:2,87:28,-1,87,-1,87:3,51,87:24,-1:9,87,-1:2,87:28,-1,87,-1,87:19" +
",50,87:8,-1:9,87,-1:2,87:28,-1,87,-1,87,52,87:26,-1:9,87,-1:2,87:28,-1,87,-" +
"1,87:9,53,87:18,-1:9,87,-1:2,87:25,53,87:2,-1,87,-1,87:28,-1:9,87,-1:2,87:2" +
"8,-1,87,-1,87:6,54,87:21,-1:9,87,-1:2,87:28,-1,87,-1,87:11,54,87:16,-1:9,87" +
",-1:2,87:28,-1,87,-1,87:12,49,87:15,-1:9,87,-1,151,144:3,169,178,144:15,87," +
"144:7,151,144,151,144:28,151:9,144,-1,55,87:20,84,87:7,-1,87,-1,87:28,-1:9," +
"84,-1,56,87:20,85,87:7,-1,87,-1,87:28,-1:9,85,-1:2,87:18,57,87:9,-1,87,-1,8" +
"7:28,-1:9,87,-1:2,87:28,-1,87,-1,87:9,58,87:18,-1:9,87,-1:2,87:18,59,87:9,-" +
"1,87,-1,87:28,-1:9,87,-1:2,87:25,58,87:2,-1,87,-1,87:28,-1:9,87,-1:2,87:14," +
"60,87:13,-1,87,-1,87:28,-1:9,87,-1:2,87:28,-1,87,-1,87,104,87:4,216,87:21,-" +
"1:9,87,-1:2,87:28,-1,87,-1,87,106,87:26,-1:9,87,-1:2,87:28,-1,87,-1,87:3,13" +
"4,87:24,-1:9,87,-1:2,87:28,-1,87,-1,87:3,136,87:7,217,87:16,-1:9,87,-1:2,87" +
":13,193,87:14,-1,87,-1,87,138,87:26,-1:9,87,-1:2,87:28,-1,87,-1,87:3,140,87" +
":9,194,87:14,-1:9,87,-1:2,87:28,-1,87,-1,87,148,87:26,-1:9,87,-1:2,87:9,154" +
",87:18,-1,87,-1,87:28,-1:9,87,-1:2,87:17,156,87:10,-1,87,-1,87:28,-1:9,87,-" +
"1:2,87:28,-1,87,-1,87:20,158,87:7,-1:9,87,-1:2,87:28,-1,87,-1,87:26,160,87," +
"-1:9,87,-1:2,87:28,-1,87,-1,87:27,161,-1:9,87,-1:2,87:28,-1,87,-1,87:19,162" +
",87:6,163,87,-1:9,87,-1:2,87:16,164,87:11,-1,87,-1,87:8,198,87:19,-1:9,87,-" +
"1:2,87:28,-1,87,-1,87:18,165,87:9,-1:9,87,-1:2,87:28,-1,87,-1,87:21,166,87:" +
"6,-1:9,87,-1:2,87:28,-1,87,-1,87:22,167,87:5,-1:9,87,-1:2,87:28,-1,87,-1,87" +
":3,168,87:24,-1:9,87,-1:2,87:15,170,87:12,-1,87,-1,87:28,-1:9,87,-1:2,87:18" +
",171,87:9,-1,87,-1,87:28,-1:9,87,-1:2,87:14,172,87:13,-1,87,-1,87:28,-1:9,8" +
"7,-1:2,87:28,-1,87,-1,87:8,173,87:19,-1:9,87,-1:2,87:14,174,87:13,-1,87,-1," +
"87:28,-1:9,87,-1:2,87:28,-1,87,-1,87:15,175,87:12,-1:9,87,-1:2,87:28,-1,87," +
"-1,87:24,184,87:3,-1:9,87,-1:2,87:28,-1,87,-1,87:13,187,87:14,-1:9,87,-1:2," +
"87:28,-1,87,-1,87:19,188,87:8,-1:9,87,-1:2,87:28,-1,87,-1,87:20,189,87:7,-1" +
":9,87,-1:2,87:13,190,87:14,-1,87,-1,87:28,-1:9,87,-1:2,87:17,191,87:10,-1,8" +
"7,-1,87:28,-1:9,87,-1:2,87:28,-1,87,-1,87:4,192,87:23,-1:9,87,-1:2,87:28,-1" +
",87,-1,87:25,195,87:2,-1:9,87,-1:2,87:14,196,87:13,-1,87,-1,87:28,-1:9,87,-" +
"1:2,87:16,197,87:11,-1,87,-1,87:28,-1:9,87,-1:2,87:28,-1,87,-1,87:7,199,87:" +
"20,-1:9,87,-1:2,87:28,-1,87,-1,87:14,201,87:13,-1:9,87,-1:2,87:13,212,87:14" +
",-1,87,-1,87:28,-1:9,87,-1:2,87:28,-1,87,-1,87:13,213,87:14,-1:9,87,-1:2,87" +
":14,214,87:13,-1,87,-1,87:28,-1:9,87,-1:2,87:28,-1,87,-1,87:12,215,87:15,-1" +
":9,87");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
                    // skip whitespace
                }
					case -3:
						break;
					case 3:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -4:
						break;
					case 4:
						{
                    //skip newline, but reset char counter
                    yychar = 0;
                }
					case -5:
						break;
					case 5:
						{ return new Symbol(sym.INTEGER, yytext()); }
					case -6:
						break;
					case 6:
						{ return new Symbol(sym.LIT_LBRACKET); }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.LIT_COLON); }
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.LIT_RBRACKET); }
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.LIT_LPAREN); }
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.LIT_RPAREN); }
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.LIT_LBRACE); }
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.LIT_RBRACE); }
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.LIT_SEMI_COLON); }
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.LIT_COMMA); }
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.DOUBLE, yytext()); }
					case -16:
						break;
					case 16:
						{
                    // ignore comments
                 }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.KW_TRUE); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.KW_FALSE); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.KW_EMPTY_LIST); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.KW_BE); }
					case -21:
						break;
					case 21:
						{
                    return new Symbol(sym.STRING, yytext()
                        .substring(1, yytext().length() - 1));
                }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.KW_IF); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.LIT_LVBRACKET); }
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.LIT_ASSIGN); }
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.LIT_RVBRACKET); }
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.LIT_DOT); }
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.LIT_ADD); }
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.LIT_MINUS); }
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.LIT_MULTIPLY); }
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.LIT_DIVIDE); }
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.LIT_MODULUS); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.LIT_BIT_AND); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.LIT_BIT_OR); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.LIT_BIT_COMP); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.LIT_REL_EQ); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.LIT_REL_GT); }
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.LIT_REL_LT); }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.LIT_CONCAT); }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.KW_DEF); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.KW_REF); }
					case -41:
						break;
					case 41:
						{
                    return new Symbol(sym.HEX_CHARACTER, yytext());
                 }
					case -42:
						break;
					case 42:
						{
                    return new Symbol(sym.CHARACTER, yytext()
                        .substring(1, yytext().length() - 1));
                 }
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.KW_LET); }
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.LIT_REL_GTEQ); }
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.LIT_REL_LTEQ); }
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.LIT_REL_NOT_EQ); }
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.LIT_LOGIC_OR); }
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.KW_READ); }
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.KW_THEN); }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.KW_CALL); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.KW_ELSE); }
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.KW_CASE); }
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.KW_PROC); }
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.KW_LAZY); }
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.LIT_LOGIC_AND); }
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.LIT_LOGIC_NOT); }
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.KW_PRINT); }
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.KW_DYNAMIC); }
					case -59:
						break;
					case 59:
						{ return new Symbol(sym.KW_READ_INT); }
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.KW_PRINT_LN); }
					case -61:
						break;
					case 62:
						{
                    // skip whitespace
                }
					case -62:
						break;
					case 63:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -63:
						break;
					case 64:
						{ return new Symbol(sym.INTEGER, yytext()); }
					case -64:
						break;
					case 65:
						{
                    // ignore comments
                 }
					case -65:
						break;
					case 66:
						{ return new Symbol(sym.LIT_DOT); }
					case -66:
						break;
					case 67:
						{ return new Symbol(sym.LIT_ADD); }
					case -67:
						break;
					case 68:
						{ return new Symbol(sym.LIT_MINUS); }
					case -68:
						break;
					case 69:
						{ return new Symbol(sym.LIT_MULTIPLY); }
					case -69:
						break;
					case 70:
						{ return new Symbol(sym.LIT_DIVIDE); }
					case -70:
						break;
					case 71:
						{ return new Symbol(sym.LIT_MODULUS); }
					case -71:
						break;
					case 72:
						{ return new Symbol(sym.LIT_BIT_AND); }
					case -72:
						break;
					case 73:
						{ return new Symbol(sym.LIT_BIT_OR); }
					case -73:
						break;
					case 74:
						{ return new Symbol(sym.LIT_BIT_COMP); }
					case -74:
						break;
					case 75:
						{ return new Symbol(sym.LIT_REL_EQ); }
					case -75:
						break;
					case 76:
						{ return new Symbol(sym.LIT_REL_GT); }
					case -76:
						break;
					case 77:
						{ return new Symbol(sym.LIT_REL_LT); }
					case -77:
						break;
					case 78:
						{ return new Symbol(sym.LIT_CONCAT); }
					case -78:
						break;
					case 79:
						{
                    return new Symbol(sym.CHARACTER, yytext()
                        .substring(1, yytext().length() - 1));
                 }
					case -79:
						break;
					case 80:
						{ return new Symbol(sym.LIT_REL_GTEQ); }
					case -80:
						break;
					case 81:
						{ return new Symbol(sym.LIT_REL_LTEQ); }
					case -81:
						break;
					case 82:
						{ return new Symbol(sym.LIT_REL_NOT_EQ); }
					case -82:
						break;
					case 83:
						{ return new Symbol(sym.LIT_LOGIC_OR); }
					case -83:
						break;
					case 84:
						{ return new Symbol(sym.LIT_LOGIC_AND); }
					case -84:
						break;
					case 85:
						{ return new Symbol(sym.LIT_LOGIC_NOT); }
					case -85:
						break;
					case 87:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -86:
						break;
					case 88:
						{ return new Symbol(sym.INTEGER, yytext()); }
					case -87:
						break;
					case 89:
						{
                    // ignore comments
                 }
					case -88:
						break;
					case 91:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -89:
						break;
					case 92:
						{
                    // ignore comments
                 }
					case -90:
						break;
					case 94:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -91:
						break;
					case 96:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -92:
						break;
					case 98:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -93:
						break;
					case 100:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -94:
						break;
					case 102:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -95:
						break;
					case 104:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -96:
						break;
					case 106:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -97:
						break;
					case 108:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -98:
						break;
					case 110:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -99:
						break;
					case 112:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -100:
						break;
					case 114:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -101:
						break;
					case 116:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -102:
						break;
					case 118:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -103:
						break;
					case 120:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -104:
						break;
					case 122:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -105:
						break;
					case 124:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -106:
						break;
					case 126:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -107:
						break;
					case 128:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -108:
						break;
					case 130:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -109:
						break;
					case 132:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -110:
						break;
					case 134:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -111:
						break;
					case 136:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -112:
						break;
					case 138:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -113:
						break;
					case 140:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -114:
						break;
					case 142:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -115:
						break;
					case 144:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -116:
						break;
					case 146:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -117:
						break;
					case 148:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -118:
						break;
					case 150:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -119:
						break;
					case 152:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -120:
						break;
					case 154:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -121:
						break;
					case 156:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -122:
						break;
					case 158:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -123:
						break;
					case 160:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -124:
						break;
					case 161:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -125:
						break;
					case 162:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -126:
						break;
					case 163:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -127:
						break;
					case 164:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -128:
						break;
					case 165:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -129:
						break;
					case 166:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -130:
						break;
					case 167:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -131:
						break;
					case 168:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -132:
						break;
					case 169:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -133:
						break;
					case 170:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -134:
						break;
					case 171:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -135:
						break;
					case 172:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -136:
						break;
					case 173:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -137:
						break;
					case 174:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -138:
						break;
					case 175:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -139:
						break;
					case 176:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -140:
						break;
					case 177:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -141:
						break;
					case 178:
						{
                    // ignore comments
                 }
					case -142:
						break;
					case 179:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -143:
						break;
					case 180:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -144:
						break;
					case 181:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -145:
						break;
					case 182:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -146:
						break;
					case 183:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -147:
						break;
					case 184:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -148:
						break;
					case 185:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -149:
						break;
					case 186:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -150:
						break;
					case 187:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -151:
						break;
					case 188:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -152:
						break;
					case 189:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -153:
						break;
					case 190:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -154:
						break;
					case 191:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -155:
						break;
					case 192:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -156:
						break;
					case 193:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -157:
						break;
					case 194:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -158:
						break;
					case 195:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -159:
						break;
					case 196:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -160:
						break;
					case 197:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -161:
						break;
					case 198:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -162:
						break;
					case 199:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -163:
						break;
					case 200:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -164:
						break;
					case 201:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -165:
						break;
					case 202:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -166:
						break;
					case 203:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -167:
						break;
					case 204:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -168:
						break;
					case 205:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -169:
						break;
					case 206:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -170:
						break;
					case 207:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -171:
						break;
					case 208:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -172:
						break;
					case 209:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -173:
						break;
					case 210:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -174:
						break;
					case 211:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -175:
						break;
					case 212:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -176:
						break;
					case 213:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -177:
						break;
					case 214:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -178:
						break;
					case 215:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -179:
						break;
					case 216:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -180:
						break;
					case 217:
						{
                    return new Symbol(sym.IDENTIFIER, yytext());
                }
					case -181:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
