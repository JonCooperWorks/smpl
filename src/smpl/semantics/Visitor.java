/**
 * 
 */
package smpl.semantics;

import smpl.syntax.ASTAddExp;
import smpl.syntax.ASTAliasAssign;
import smpl.syntax.ASTAliasDefn;
import smpl.syntax.ASTAliasDefinitionList;
import smpl.syntax.ASTArgumentList;
import smpl.syntax.ASTBitwiseAndExp;
import smpl.syntax.ASTComplementExp;
import smpl.syntax.ASTBitwiseOrExp;
import smpl.syntax.ASTBooleanConstantExp;
import smpl.syntax.ASTCallProcedureExp;
import smpl.syntax.ASTCaseClause;
import smpl.syntax.ASTCaseClauseList;
import smpl.syntax.ASTCaseExp;
import smpl.syntax.ASTCharacter;
import smpl.syntax.ASTCreateOpenArityProcedureExp;
import smpl.syntax.ASTCreateProcedureExp;
import smpl.syntax.ASTCreateVariableArityProcedureExp;
import smpl.syntax.ASTDivideExpression;
import smpl.syntax.ASTDouble;
import smpl.syntax.ASTDynamicBindingsExp;
import smpl.syntax.ASTException;
import smpl.syntax.ASTExpressionList;
import smpl.syntax.ASTIdent;
import smpl.syntax.ASTIdentifierList;
import smpl.syntax.ASTIfThenElseExp;
import smpl.syntax.ASTInteger;
import smpl.syntax.ASTLazyEvaluationExp;
import smpl.syntax.ASTLetAliasDefinitionExpansionExp;
import smpl.syntax.ASTListConcatExp;
import smpl.syntax.ASTListCreationExp;
import smpl.syntax.ASTLogicalAndExp;
import smpl.syntax.ASTLogicalNotExp;
import smpl.syntax.ASTLogicalOrExp;
import smpl.syntax.ASTMinusExpression;
import smpl.syntax.ASTModulusExpression;
import smpl.syntax.ASTMultipleAliasAssignment;
import smpl.syntax.ASTMultiplyExpression;
import smpl.syntax.ASTNegationExpression;
import smpl.syntax.ASTNilList;
import smpl.syntax.ASTNoOp;
import smpl.syntax.ASTPrintExp;
import smpl.syntax.ASTPrintLnExp;
import smpl.syntax.ASTProgram;
import smpl.syntax.ASTReadIntegerExp;
import smpl.syntax.ASTReadStringExp;
import smpl.syntax.ASTEqualExp;
import smpl.syntax.ASTGreaterThanExp;
import smpl.syntax.ASTGreaterThanOrEqualExp;
import smpl.syntax.ASTLessThanExp;
import smpl.syntax.ASTLessThanOrEqualExp;
import smpl.syntax.ASTNotEqualExp;
import smpl.syntax.ASTSpecialCallProcedureExp;
import smpl.syntax.ASTStmtSequence;
import smpl.syntax.ASTStatementSequenceExp;
import smpl.syntax.ASTString;
import smpl.syntax.ASTVectorComprehensionExp;
import smpl.syntax.ASTVectorCreationExp;
import smpl.syntax.ASTVectorIndexAccessExp;

/**
 * 
 */
public interface Visitor<S, T> {

	public T visitProgram(ASTProgram astProg, S state) throws ASTException;

	public T visitStatementSequence(ASTStmtSequence astSeq, S state)
			throws ASTException;

	public T visitCharacter(ASTCharacter astChar, S state) throws ASTException;

	public T visitString(ASTString astStr, S state) throws ASTException;

	public T visitIdent(ASTIdent astIdent, S state) throws ASTException;

	public T visitInteger(ASTInteger astInt, S state) throws ASTException;

	public T visitDouble(ASTDouble astDouble, S state) throws ASTException;

	public T visitAliasDefn(ASTAliasDefn astDefinition, S state)
			throws ASTException;

	public T visitNoOp(ASTNoOp astBlank, S state)
			throws ASTException;

	public T visitAdd(ASTAddExp astAddExpression, S state)
			throws ASTException;

	public T visitNegation(ASTNegationExpression astNegationExpression, S state)
			throws ASTException;

	public T visitMinus(ASTMinusExpression astMinusExpression, S state)
			throws ASTException;

	public T visitMultiply(ASTMultiplyExpression astMultiplyExpression, S state)
			throws ASTException;

	public T visitDivide(ASTDivideExpression astDivideExpression, S state)
			throws ASTException;

	public T visitModulus(ASTModulusExpression astModulusExpression, S state)
			throws ASTException;

	public T visitBitwiseAnd(ASTBitwiseAndExp astBitwiseAndExpression,
			S state) throws ASTException;

	public T visitBitwiseOr(ASTBitwiseOrExp astBitwiseOrExp,
			S state) throws ASTException;

	public T visitBitwiseComplement(
			ASTComplementExp astComplementExp,
			S state) throws ASTException;

	public T visitEqual(
			ASTEqualExp astEqualExp, S state)
			throws ASTException;

	public T visitLessThan(
			ASTLessThanExp astLessThanExp,
			S state) throws ASTException;

	public T visitGreaterThan(
			ASTGreaterThanExp astGreaterThanExp,
			S state) throws ASTException;

	public T visitLessThanOrEqual(
			ASTLessThanOrEqualExp astLessThanOrEqualExp,
			S state) throws ASTException;

	public T visitGreaterThanOrEqual(
			ASTGreaterThanOrEqualExp astGreaterThanOrEqualExp,
			S state) throws ASTException;

	public T visitNotEqual(
			ASTNotEqualExp astNotEqualExp,
			S state) throws ASTException;

	public T visitListCreation(
			ASTListCreationExp astListCreationExp, S state)
			throws ASTException;

	public T visitListConcatExp(
			ASTListConcatExp astListConcatExp, S state)
			throws ASTException;

	public T visitLogicalAnd(ASTLogicalAndExp astBitwiseAndExpression,
			S state) throws ASTException;

	public T visitLogicalNot(ASTLogicalNotExp astBitwiseNotExp,
			S state) throws ASTException;

	public T visitLogicalOr(ASTLogicalOrExp astBitwiseOrExp,
			S state) throws ASTException;

	public T visitVectorIndexAccess(
			ASTVectorIndexAccessExp astVectorIndexAccessExp,
			S state) throws ASTException;

	public T visitVectorCreation(
			ASTVectorCreationExp astVectorCreationExp, S state)
			throws ASTException;

	public T visitVectorComprehension(
			ASTVectorComprehensionExp astVectorComprehensionExp,
			S state) throws ASTException;

	public T visitExpressionList(ASTExpressionList astListCreationExpression,
			S state) throws ASTException;

	public T visitCreateProcedure(
			ASTCreateProcedureExp astCreateProcedureExp, S state)
			throws ASTException;

	public T visitCreateVariableArityProcedure(
			ASTCreateVariableArityProcedureExp astCreateVariableArityProcedureExp,
			S state) throws ASTException;

	public T visitCreateOpenArityProcedure(
			ASTCreateOpenArityProcedureExp astCreateOpenArityProcedureExp,
			S state) throws ASTException;

	public T visitSpecialCallProcedure(
			ASTSpecialCallProcedureExp astSpecialCallProcedureExp,
			S state) throws ASTException;

	public T visitLazyEvaluation(
			ASTLazyEvaluationExp astLazyEvaluationExp, S state)
			throws ASTException;

	public T visitAliasDefinitionList(
			ASTAliasDefinitionList astAliasDefinitionList, S state)
			throws ASTException;

	public T visitLetAliasDefinitionExpansion(
			ASTLetAliasDefinitionExpansionExp astBodyAliasDefinitionExpansionExp,
			S state) throws ASTException;

	public T visitAliasAssignment(ASTAliasAssign astAliasAssignment, S state)
			throws ASTException;

	public T visitMultipleAliasAssignment(
			ASTMultipleAliasAssignment astMultipleAliasAssignment, S state)
			throws ASTException;

	public T visitIfThenElse(ASTIfThenElseExp astIfThenElseExp,
			S state) throws ASTException;

	public T visitCaseClasue(ASTCaseClause astCaseClause, S state)
			throws ASTException;

	public T visitCaseClauseList(ASTCaseClauseList astCaseClauseList, S state)
			throws ASTException;

	public T visitCase(ASTCaseExp astCaseExp, S state)
			throws ASTException;

	public T visitStatementSequenceExp(
			ASTStatementSequenceExp astExpressionSeq, S state)
			throws ASTException;

	public T visitPrint(ASTPrintExp astPrintExp, S state)
			throws ASTException;

	public T visitPrintLn(ASTPrintLnExp astPrintLnExp, S state)
			throws ASTException;

	public T visitReadString(ASTReadStringExp astReadStringExp,
			S state) throws ASTException;

	public T visitReadInteger(
			ASTReadIntegerExp astReadIntegerExp, S state)
			throws ASTException;

	public T visitIdentifierList(ASTIdentifierList astIdentifierList, S state)
			throws ASTException;

	public T visitBooleanConstant(
			ASTBooleanConstantExp astBooleanConstantExp, S state)
			throws ASTException;

	public T visitArgumentList(ASTArgumentList astParameterList, S state)
			throws ASTException;

	public T visitCallProcedure(
			ASTCallProcedureExp astCallProcedureExp, S state)
			throws ASTException;

	public T visitDynamicBindingExp(
			ASTDynamicBindingsExp astDynamicIdentifiersExp,
			S state) throws ASTException;

	public T visitNilList(ASTNilList astNil, S state) throws ASTException;
}
