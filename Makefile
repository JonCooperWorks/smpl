CUP_PATH = lib/cup.jar
LEXER_PATH = src/smpl/syntax/SMPLLexer.jflex
PARSER_PATH = src/smpl/syntax/SMPLParser.cup
OLD_LEXER_PATH = src/smpl/syntax/SMPLLexer.java~
SYNTAX_DIR = src/smpl/syntax

cup:
	@echo "Generating SMPLParser.java and sym.java"
	@java -cp $(CUP_PATH) java_cup.Main -parser SMPLParser < $(PARSER_PATH)
	@echo "Moving SMPLParser.java and sym.java to" $(SYNTAX_DIR)
	@mv sym.java $(SYNTAX_DIR)
	@mv SMPLParser.java $(SYNTAX_DIR)
	@echo "Done"

jflex:
	@echo "Generating SMPLLexer.java and removing SMPLLexer.java~"
	@jflex $(LEXER_PATH)
	@rm $(OLD_LEXER_PATH)
	@echo "Done"

compile:
	@echo "Compiling Java"
	@javac -cp ":$(CUP_PATH)" src/smpl/*/*.java
	@echo "Done"

run:
	@java -cp "src:$(CUP_PATH)" smpl.sys.Repl smpl.semantics.SMPLEvaluator $(file)
