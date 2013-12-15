/**
 * 
 */
package smpl.semantics;

import smpl.syntax.ASTAddExpression;
import smpl.syntax.ASTAliasAssignment;
import smpl.syntax.ASTAliasDefinition;
import smpl.syntax.ASTAliasDefinitionList;
import smpl.syntax.ASTArgumentList;
import smpl.syntax.ASTBitwiseAndExpression;
import smpl.syntax.ASTBitwiseComplementExpression;
import smpl.syntax.ASTBitwiseOrExpression;
import smpl.syntax.ASTBooleanConstantExpression;
import smpl.syntax.ASTCallProcedureExpression;
import smpl.syntax.ASTCaseClasue;
import smpl.syntax.ASTCaseClauseList;
import smpl.syntax.ASTCaseExpression;
import smpl.syntax.ASTCharacter;
import smpl.syntax.ASTCreateOpenAriyProcedureExpression;
import smpl.syntax.ASTCreateProcedureExpression;
import smpl.syntax.ASTCreateVariableAriyProcedureExpression;
import smpl.syntax.ASTDivideExpression;
import smpl.syntax.ASTDouble;
import smpl.syntax.ASTDynamicBindingsExpression;
import smpl.syntax.ASTException;
import smpl.syntax.ASTExpressionList;
import smpl.syntax.ASTIdentifier;
import smpl.syntax.ASTIdentifierList;
import smpl.syntax.ASTIfThenElseExpression;
import smpl.syntax.ASTInteger;
import smpl.syntax.ASTLazyEvaluationExpression;
import smpl.syntax.ASTLetAliasDefinitionExpansionExpression;
import smpl.syntax.ASTListConcatExpression;
import smpl.syntax.ASTListCreationExpression;
import smpl.syntax.ASTLogicalAndExpression;
import smpl.syntax.ASTLogicalNotExpression;
import smpl.syntax.ASTLogicalOrExpression;
import smpl.syntax.ASTMinusExpression;
import smpl.syntax.ASTModulusExpression;
import smpl.syntax.ASTMultipleAliasAssignment;
import smpl.syntax.ASTMultiplyExpression;
import smpl.syntax.ASTNegationExpression;
import smpl.syntax.ASTNillList;
import smpl.syntax.ASTNoOperationNode;
import smpl.syntax.ASTPrintExpression;
import smpl.syntax.ASTPrintLnExpression;
import smpl.syntax.ASTProgram;
import smpl.syntax.ASTReadIntegerExpression;
import smpl.syntax.ASTReadStringExpression;
import smpl.syntax.ASTRelationalEqualExpression;
import smpl.syntax.ASTRelationalGreaterThanExpression;
import smpl.syntax.ASTRelationalGreaterThanOrEqualExpression;
import smpl.syntax.ASTRelationalLessThanExpression;
import smpl.syntax.ASTRelationalLessThanOrEqualExpression;
import smpl.syntax.ASTRelationalNotEqualExpression;
import smpl.syntax.ASTSpecialCallProcedureExpression;
import smpl.syntax.ASTStatementSequence;
import smpl.syntax.ASTStatementSequenceExpression;
import smpl.syntax.ASTString;
import smpl.syntax.ASTVectorComprehensionExpression;
import smpl.syntax.ASTVectorCreationExpression;
import smpl.syntax.ASTVectorIndexAccessExpression;

/**
 * 
 */
public interface Visitor<S, T> {

    public T visitProgram(ASTProgram astProg, S state) throws ASTException;

    public T visitStatementSequence(ASTStatementSequence astSeq, S state)
	    throws ASTException;

    public T visitCharacter(ASTCharacter astChar, S state) throws ASTException;

    public T visitString(ASTString astStr, S state) throws ASTException;

    public T visitIdentifier(ASTIdentifier astStr, S state) throws ASTException;

    public T visitInteger(ASTInteger astInt, S state) throws ASTException;

    public T visitDouble(ASTDouble astDouble, S state) throws ASTException;

    public T visitAliasDefinition(ASTAliasDefinition astDefinition, S state)
	    throws ASTException;

    public T visitNoOperation(ASTNoOperationNode astBlank, S state)
	    throws ASTException;

    public T visitAdd(ASTAddExpression astAddExpression, S state)
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

    public T visitBitwiseAnd(ASTBitwiseAndExpression astBitwiseAndExpression,
	    S state) throws ASTException;

    public T visitBitwiseOr(ASTBitwiseOrExpression astBitwiseOrExpression,
	    S state) throws ASTException;

    public T visitBitwiseComplement(
	    ASTBitwiseComplementExpression astBitwiseComplementExpression,
	    S state) throws ASTException;

    public T visitRelationalEqual(
	    ASTRelationalEqualExpression astRelationalEqualExpression, S state)
	    throws ASTException;

    public T visitRelationalLessThan(
	    ASTRelationalLessThanExpression astRelationalLessThanExpression,
	    S state) throws ASTException;

    public T visitRelationalGreaterThan(
	    ASTRelationalGreaterThanExpression astRelationalGreaterThanExpression,
	    S state) throws ASTException;

    public T visitRelationalLessThanOrEqual(
	    ASTRelationalLessThanOrEqualExpression astRelationalLessThanOrEqualExpression,
	    S state) throws ASTException;

    public T visitRelationalGreaterThanOrEqual(
	    ASTRelationalGreaterThanOrEqualExpression astRelationalGreaterThanOrEqualExpression,
	    S state) throws ASTException;

    public T visitRelationalNotEqual(
	    ASTRelationalNotEqualExpression astRelationalNotEqualExpression,
	    S state) throws ASTException;

    public T visitListCreation(
	    ASTListCreationExpression astListCreationExpression, S state)
	    throws ASTException;

    public T visitListConcatenationExpression(
	    ASTListConcatExpression astRelationalListConcatExpression, S state)
	    throws ASTException;

    public T visitLogicalAnd(ASTLogicalAndExpression astBitwiseAndExpression,
	    S state) throws ASTException;

    public T visitLogicalNot(ASTLogicalNotExpression astBitwiseNotExpression,
	    S state) throws ASTException;

    public T visitLogicalOr(ASTLogicalOrExpression astBitwiseOrExpression,
	    S state) throws ASTException;

    public T visitVectorIndexAccess(
	    ASTVectorIndexAccessExpression astVectorIndexAccessExpression,
	    S state) throws ASTException;

    public T visitVectorCreation(
	    ASTVectorCreationExpression astVectorCreationExpression, S state)
	    throws ASTException;

    public T visitVectorComprehension(
	    ASTVectorComprehensionExpression astVectorComprehensionExpression,
	    S state) throws ASTException;

    public T visitExpressionList(ASTExpressionList astListCreationExpression,
	    S state) throws ASTException;

    public T visitCreateProcedure(
	    ASTCreateProcedureExpression astCreateProcedureExpression, S state)
	    throws ASTException;

    public T visitCreateVariableAriyProcedure(
	    ASTCreateVariableAriyProcedureExpression astCreateVariableAriyProcedureExpression,
	    S state) throws ASTException;

    public T visitCreateOpenAriyProcedure(
	    ASTCreateOpenAriyProcedureExpression astCreateOpenAriyProcedureExpression,
	    S state) throws ASTException;

    public T visitSpecialCallProcedure(
	    ASTSpecialCallProcedureExpression astSpecialCallProcedureExpression,
	    S state) throws ASTException;

    public T visitLazyEvaluation(
	    ASTLazyEvaluationExpression astLazyEvaluationExpression, S state)
	    throws ASTException;

    public T visitAliasDefinitionList(
	    ASTAliasDefinitionList astAliasDefinitionList, S state)
	    throws ASTException;

    public T visitLetAliasDefinitionExpansion(
	    ASTLetAliasDefinitionExpansionExpression astBodyAliasDefinitionExpansionExpression,
	    S state) throws ASTException;

    public T visitAliasAssignment(ASTAliasAssignment astAliasAssignment, S state)
	    throws ASTException;

    public T visitMultipleAliasAssignment(
	    ASTMultipleAliasAssignment astMultipleAliasAssignment, S state)
	    throws ASTException;

    public T visitIfThenElse(ASTIfThenElseExpression astIfThenElseExpression,
	    S state) throws ASTException;

    public T visitCaseClasue(ASTCaseClasue astCaseClasue, S state)
	    throws ASTException;

    public T visitCaseClauseList(ASTCaseClauseList astCaseClauseList, S state)
	    throws ASTException;

    public T visitCase(ASTCaseExpression astCaseExpression, S state)
	    throws ASTException;

    public T visitStatementSequenceExpression(
	    ASTStatementSequenceExpression astExpressionSequence, S state)
	    throws ASTException;

    public T visitPrint(ASTPrintExpression astPrintExpression, S state)
	    throws ASTException;

    public T visitPrintLn(ASTPrintLnExpression astPrintLnExpression, S state)
	    throws ASTException;

    public T visitReadString(ASTReadStringExpression astReadStringExpression,
	    S state) throws ASTException;

    public T visitReadInteger(
	    ASTReadIntegerExpression astReadIntegerExpression, S state)
	    throws ASTException;

    public T visitIdentifierList(ASTIdentifierList astIdentifierList, S state)
	    throws ASTException;

    public T visitBooleanConstant(
	    ASTBooleanConstantExpression astBooleanConstantExpression, S state)
	    throws ASTException;

    public T visitArgumentList(ASTArgumentList astParameterList, S state)
	    throws ASTException;

    public T visitCallProcedure(
	    ASTCallProcedureExpression astCallProcedureExpression, S state)
	    throws ASTException;

    public T visitDynamicBindingExpression(
	    ASTDynamicBindingsExpression astDynamicIdentifiersExpression,
	    S state) throws ASTException;

    public T visitNiiList(ASTNillList astNill, S state) throws ASTException;
}
