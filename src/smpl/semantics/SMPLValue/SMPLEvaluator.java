/**
 * 
 */
package smpl.semantics.SMPLValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import smpl.semantics.Visitor;
import smpl.semantics.SMPLValue.Objects.SMPLBoolean;
import smpl.semantics.SMPLValue.Objects.SMPLCharacter;
import smpl.semantics.SMPLValue.Objects.SMPLDouble;
import smpl.semantics.SMPLValue.Objects.SMPLInteger;
import smpl.semantics.SMPLValue.Objects.SMPLLazyExpression;
import smpl.semantics.SMPLValue.Objects.SMPLList;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLNillObject;
import smpl.semantics.SMPLValue.Objects.SMPLNumber;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLPair;
import smpl.semantics.SMPLValue.Objects.SMPLProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLString;
import smpl.semantics.SMPLValue.Objects.SMPLSubVectorExpression;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.semantics.SMPLValue.Objects.SMPLVector;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLCarNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLCdrNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLEqivNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLEqualNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLIsPairNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLListNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLPairNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLSizeNativeFunction;
import smpl.semantics.SMPLValue.Objects.NativeFunctions.SMPLSubStrNativeFunction;
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
import smpl.syntax.ASTExpression;
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
import smpl.syntax.ASTStatement;
import smpl.syntax.ASTStatementSequence;
import smpl.syntax.ASTStatementSequenceExpression;
import smpl.syntax.ASTString;
import smpl.syntax.ASTVectorComprehensionExpression;
import smpl.syntax.ASTVectorCreationExpression;
import smpl.syntax.ASTVectorIndexAccessExpression;

/**
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SMPLEvaluator implements Visitor<SMPLEnvironment, SMPLValue> {
	private final static SMPLNativeProcedure sListNativeProcedure = new SMPLListNativeFunction();
	private final static SMPLCdrNativeFunction sCdrNativeProcedure = new SMPLCdrNativeFunction();
	private final static SMPLCarNativeFunction sCarNativeProcedure = new SMPLCarNativeFunction();
	private final static SMPLEqivNativeFunction sEquivNativeProcedure = new SMPLEqivNativeFunction();
	private final static SMPLEqualNativeFunction sEqualNativeProcedure = new SMPLEqualNativeFunction();
	private final static SMPLIsPairNativeFunction sIsPairNativeProcedure = new SMPLIsPairNativeFunction();
	private final static SMPLPairNativeFunction sPairNativeProcedure = new SMPLPairNativeFunction();
	private final static SMPLSizeNativeFunction sSizeNativeProcedure = new SMPLSizeNativeFunction();
	private final static SMPLSubStrNativeFunction sSubStrNativeProcedure = new SMPLSubStrNativeFunction();

	public SMPLEnvironment getGlobalEnvironment() {
		final SMPLEnvironment globalEnv = new SMPLEnvironment(null, null);
		globalEnv.addBinding("list", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sListNativeProcedure));
		globalEnv.addBinding("car", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sCarNativeProcedure));
		globalEnv.addBinding("cdr", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sCdrNativeProcedure));
		globalEnv.addBinding("pair", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sPairNativeProcedure));
		globalEnv.addBinding("pair?", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sIsPairNativeProcedure));
		globalEnv.addBinding("size", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sSizeNativeProcedure));
		globalEnv.addBinding("eqv?", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sEquivNativeProcedure));
		globalEnv.addBinding("equal?", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sEqualNativeProcedure));
		globalEnv.addBinding("substr", new SMPLValue<SMPLObject>(
				SMPLEvaluator.sSubStrNativeProcedure));
		return globalEnv;
	}

	@Override
	public SMPLValue<SMPLObject> visitProgram(final ASTProgram astProg,
			final SMPLEnvironment state) throws ASTException {
		final SMPLValue<SMPLObject> ret = astProg.getBody().visit(this, state);
		return ret;
	}

	@Override
	public SMPLValue<SMPLObject> visitStatementSequence(
			final ASTStatementSequence astSeq, final SMPLEnvironment state)
			throws ASTException {
		SMPLValue<SMPLObject> ret = null;
		boolean first = true;
		for (final ASTStatement<?> stmt : astSeq.getStatements()) {
			if (stmt instanceof ASTDynamicBindingsExpression)
				if (!first)
					throw new SMPLException(
							"The 'dynamic' statement must be the first sttement.");

			first = false;

			ret = stmt.visit(this, state);
		}
		return ret;
	}

	@Override
	public SMPLValue<SMPLCharacter> visitCharacter(final ASTCharacter astChar,
			final SMPLEnvironment state) throws ASTException {
		final SMPLCharacter smplChar = new SMPLCharacter(
				astChar.getCharacterValue());
		return new SMPLValue<SMPLCharacter>(smplChar);
	}

	@Override
	public SMPLValue<SMPLString> visitString(final ASTString astStr,
			final SMPLEnvironment state) throws ASTException {
		final SMPLString smplString = new SMPLString(astStr.getStringValue());
		return new SMPLValue<SMPLString>(smplString);
	}

	@Override
	public SMPLValue<SMPLObject> visitIdentifier(
			final ASTIdentifier astIdentifier, final SMPLEnvironment state)
			throws ASTException {

		final String identifierName = astIdentifier.getIdentifierName();
		if (!state.isBounded(identifierName))
			throw new SMPLException("The variable '" + identifierName
					+ "' is not defined.");
		final SMPLValue<SMPLObject> identifierValue = state
				.lookUp(identifierName);

		return identifierValue;
	}

	@Override
	public SMPLValue<SMPLInteger> visitInteger(final ASTInteger astInt,
			final SMPLEnvironment state) throws ASTException {
		final String strValue = astInt.getMatchedString();
		BigInteger parsedValue = null;
		try {
			if (strValue.length() > 2) {
				final String integerType = strValue.substring(0, 2)
						.toLowerCase();
				if ("#x".equals(integerType))
					parsedValue = new BigInteger(strValue.substring(2), 16);
				else if ("#b".equals(integerType))
					parsedValue = new BigInteger(strValue.substring(2), 2);
				else
					parsedValue = new BigInteger(strValue);
			} else
				parsedValue = new BigInteger(strValue);
		} catch (final NumberFormatException e) {
			throw e;
		}

		final SMPLInteger smplInt = new SMPLInteger(parsedValue);

		return new SMPLValue<SMPLInteger>(smplInt);
	}

	@Override
	public SMPLValue<SMPLDouble> visitDouble(final ASTDouble astDouble,
			final SMPLEnvironment state) throws ASTException {

		final String strValue = astDouble.getMatchedString();

		final SMPLDouble smplDbl = new SMPLDouble(new BigDecimal(strValue));

		return new SMPLValue<SMPLDouble>(smplDbl);
	}

	@Override
	public SMPLValue<SMPLObject> visitAliasDefinition(
			final ASTAliasDefinition astDefinition, final SMPLEnvironment state)
			throws ASTException {
		final ASTIdentifier identifier = astDefinition.getIdentifierNode();
		final ASTExpression<?> expression = astDefinition.getExpressionNode();

		final SMPLValue<SMPLObject> expressionEvaluatedValue = expression
				.visit(this, state);

		state.addBinding(identifier.getIdentifierName(),
				expressionEvaluatedValue);
		return expressionEvaluatedValue;
	}

	@Override
	public SMPLValue<SMPLObject> visitNoOperation(
			final ASTNoOperationNode astBlank, final SMPLEnvironment state)
			throws ASTException {
		return new SMPLValue<SMPLObject>(new SMPLObject("no_op"));
	}

	@Override
	public SMPLValue<SMPLNumber> visitAdd(
			final ASTAddExpression astAddExpression, final SMPLEnvironment state)
			throws ASTException {

		final ASTExpression<?> astOperand1 = astAddExpression.getOperand1();
		final ASTExpression<?> astOperand2 = astAddExpression.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Add operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;
		if (operand1 instanceof SMPLInteger && operand2 instanceof SMPLInteger) {

			final BigInteger resultValue = ((BigInteger) operand1.getNumber())
					.add((BigInteger) operand2.getNumber());

			smplResult = new SMPLInteger(resultValue);
		} else if (operand1 instanceof SMPLDouble) {

			final BigDecimal resultValue = ((BigDecimal) operand1.getNumber())
					.add(new BigDecimal((BigInteger) operand2.getNumber()));

			smplResult = new SMPLDouble(resultValue);
		} else {

			final BigDecimal resultValue = (new BigDecimal(
					(BigInteger) operand1.getNumber()))
					.add((BigDecimal) operand2.getNumber());

			smplResult = new SMPLDouble(resultValue);
		}
		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLNumber> visitNegation(
			final ASTNegationExpression astNegationExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astNegationExpression.getOperand();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);

		SMPLNumber operand1 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Negation operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;
		if (operand1 instanceof SMPLInteger) {

			final BigInteger resultValue = ((BigInteger) operand1.getNumber())
					.negate();

			smplResult = new SMPLInteger(resultValue);
		} else {

			final BigDecimal resultValue = (new BigDecimal(
					(BigInteger) operand1.getNumber())).negate();

			smplResult = new SMPLDouble(resultValue);
		}

		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLNumber> visitMinus(
			final ASTMinusExpression astMinusExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astMinusExpression.getOperand1();
		final ASTExpression<?> astOperand2 = astMinusExpression.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Minus operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;
		if (operand1 instanceof SMPLInteger && operand2 instanceof SMPLInteger) {

			final BigInteger resultValue = ((BigInteger) operand1.getNumber())
					.subtract((BigInteger) operand2.getNumber());

			smplResult = new SMPLInteger(resultValue);
		} else if (operand1 instanceof SMPLDouble) {

			final BigDecimal resultValue = ((BigDecimal) operand1.getNumber())
					.subtract(new BigDecimal((BigInteger) operand2.getNumber()));

			smplResult = new SMPLDouble(resultValue);
		} else {

			final BigDecimal resultValue = (new BigDecimal(
					(BigInteger) operand1.getNumber()))
					.subtract((BigDecimal) operand2.getNumber());

			smplResult = new SMPLDouble(resultValue);
		}
		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLNumber> visitMultiply(
			final ASTMultiplyExpression astMultiplyExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astMultiplyExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astMultiplyExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Multiply operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;
		if (operand1 instanceof SMPLInteger && operand2 instanceof SMPLInteger) {

			final BigInteger resultValue = ((BigInteger) operand1.getNumber())
					.multiply((BigInteger) operand2.getNumber());

			smplResult = new SMPLInteger(resultValue);
		} else if (operand1 instanceof SMPLDouble) {

			final BigDecimal resultValue = ((BigDecimal) operand1.getNumber())
					.multiply(new BigDecimal((BigInteger) operand2.getNumber()));

			smplResult = new SMPLDouble(resultValue);
		} else {

			final BigDecimal resultValue = (new BigDecimal(
					(BigInteger) operand1.getNumber()))
					.multiply((BigDecimal) operand2.getNumber());

			smplResult = new SMPLDouble(resultValue);
		}
		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLNumber> visitDivide(
			final ASTDivideExpression astDivideExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astDivideExpression.getOperand1();
		final ASTExpression<?> astOperand2 = astDivideExpression.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Division operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;
		if (operand1 instanceof SMPLInteger && operand2 instanceof SMPLInteger) {

			final BigInteger resultValue = ((BigInteger) operand1.getNumber())
					.divide((BigInteger) operand2.getNumber());

			smplResult = new SMPLInteger(resultValue);
		} else if (operand1 instanceof SMPLDouble) {

			final BigDecimal resultValue = ((BigDecimal) operand1.getNumber())
					.divide(new BigDecimal((BigInteger) operand2.getNumber()));

			smplResult = new SMPLDouble(resultValue);
		} else {

			final BigDecimal resultValue = (new BigDecimal(
					(BigInteger) operand1.getNumber()))
					.divide((BigDecimal) operand2.getNumber());

			smplResult = new SMPLDouble(resultValue);
		}
		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLNumber> visitModulus(
			final ASTModulusExpression astModulusExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astModulusExpression.getOperand1();
		final ASTExpression<?> astOperand2 = astModulusExpression.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLInteger operand1 = null;
		SMPLInteger operand2 = null;

		try {
			operand1 = (SMPLInteger) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLInteger) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Modulus operator can only operate on integers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLNumber smplResult;

		final BigInteger resultValue = operand1.getNumber().mod(
				operand2.getNumber());

		smplResult = new SMPLInteger(resultValue);

		return new SMPLValue<SMPLNumber>(smplResult);
	}

	@Override
	public SMPLValue<SMPLInteger> visitBitwiseAnd(
			final ASTBitwiseAndExpression astBitwiseAndExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astBitwiseAndExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astBitwiseAndExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLInteger operand1 = null;
		SMPLInteger operand2 = null;

		try {
			operand1 = (SMPLInteger) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLInteger) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Bitwise And operator can only operate on integers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final BigInteger resultValue = operand1.getNumber().and(
				operand2.getNumber());

		final SMPLInteger smplResult = new SMPLInteger(resultValue);

		return new SMPLValue<SMPLInteger>(smplResult);
	}

	@Override
	public SMPLValue<SMPLInteger> visitBitwiseOr(
			final ASTBitwiseOrExpression astBitwiseOrExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astBitwiseOrExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astBitwiseOrExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLInteger operand1 = null;
		SMPLInteger operand2 = null;

		try {
			operand1 = (SMPLInteger) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLInteger) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Bitwise Or operator can only operate on integers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final BigInteger resultValue = operand1.getNumber().or(
				operand2.getNumber());

		final SMPLInteger smplResult = new SMPLInteger(resultValue);

		return new SMPLValue<SMPLInteger>(smplResult);
	}

	@Override
	public SMPLValue<SMPLInteger> visitBitwiseComplement(
			final ASTBitwiseComplementExpression astBitwiseComplementExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astBitwiseComplementExpression
				.getOperand();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);

		SMPLInteger operand1 = null;

		try {
			operand1 = (SMPLInteger) operand1EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Complement operator can only operate on integers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final BigInteger resultValue = operand1.getNumber().not();

		final SMPLInteger smplResult = new SMPLInteger(resultValue);

		return new SMPLValue<SMPLInteger>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalEqual(
			final ASTRelationalEqualExpression astRelationalEqualExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalEqualExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalEqualExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		Boolean resultValue;
		try {
			final SMPLObject operand1 = operand1EvaluatedValue
					.getInternalValue(this, state);
			final SMPLObject operand2 = operand2EvaluatedValue
					.getInternalValue(this, state);

			resultValue = operand1.isEqualTo(operand2);
		} catch (final ClassCastException e1) {
			try {

				final SMPLObject operand1 = operand1EvaluatedValue
						.getInternalValue(this, state);
				final SMPLObject operand2 = operand2EvaluatedValue
						.getInternalValue(this, state);

				resultValue = operand1.isEqualTo(operand2);
			} catch (final ClassCastException e) {
				final SMPLException e2 = new SMPLException(
						"Relational Equal operator can only operate on numbers and booleans. \n Given: "
								+ operand1EvaluatedValue.getInternalValue(this,
										state).toRepr()
								+ " and "
								+ operand2EvaluatedValue.getInternalValue(this,
										state).toRepr());
				e2.setStackTrace(e.getStackTrace());

				throw e2;
			}
		}

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalLessThan(
			final ASTRelationalLessThanExpression astRelationalLessThanExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalLessThanExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalLessThanExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Relational Less Than operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = operand1.isLessThan(operand2);

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalGreaterThan(
			final ASTRelationalGreaterThanExpression astRelationalGreaterThanExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalGreaterThanExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalGreaterThanExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Relational Greater Than operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}
		final Boolean resultValue = operand1.isGreaterThan(operand2);

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalLessThanOrEqual(
			final ASTRelationalLessThanOrEqualExpression astRelationalLessThanOrEqualExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalLessThanOrEqualExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalLessThanOrEqualExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Relational Less Than Or Equal Than operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = operand1.isLessThan(operand2)
				|| operand1.isEqualTo(operand2);

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalGreaterThanOrEqual(
			final ASTRelationalGreaterThanOrEqualExpression astRelationalGreaterThanOrEqualExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalGreaterThanOrEqualExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalGreaterThanOrEqualExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLNumber operand1 = null;
		SMPLNumber operand2 = null;

		try {
			operand1 = (SMPLNumber) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLNumber) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Relational Greater Than or Equal operator can only operate on numbers. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = operand1.isGreaterThan(operand2)
				|| operand1.isEqualTo(operand2);

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitRelationalNotEqual(
			final ASTRelationalNotEqualExpression astRelationalNotEqualExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalNotEqualExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalNotEqualExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		Boolean resultValue;
		try {

			final SMPLObject operand1 = operand1EvaluatedValue
					.getInternalValue(this, state);
			final SMPLObject operand2 = operand2EvaluatedValue
					.getInternalValue(this, state);

			resultValue = !operand1.isEqualTo(operand2);
		} catch (final ClassCastException e1) {
			final SMPLException e2 = new SMPLException(
					"Relational Not Equal operator can only operate on numbers and booleans. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e1.getStackTrace());

			throw e2;
		}

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLPair> visitListCreation(
			final ASTListCreationExpression astListCreationExpression,
			final SMPLEnvironment state) throws ASTException {
		// THe list construct is equivalent to calling the list function with
		// the items as input.
		// If the function is redefined, the construct still needs to do
		// whatever the function evaluates t0.
		// To facilitate this, I will simulate the list function being called.

		final ASTIdentifier listFunctionIdentifier = new ASTIdentifier("list");
		final ASTArgumentList listFunctionArgs = new ASTArgumentList();

		final ASTExpressionList listElements = astListCreationExpression
				.getListExpressions();

		for (final ASTExpression<?> exp : listElements.getExpressions())
			listFunctionArgs.add(exp);

		final ASTCallProcedureExpression createListProcedure = new ASTCallProcedureExpression(
				listFunctionIdentifier, listFunctionArgs);

		return createListProcedure.visit(this, state);
	}

	@Override
	public SMPLValue<SMPLPair> visitListConcatenationExpression(
			final ASTListConcatExpression astRelationalListConcatExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astRelationalListConcatExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astRelationalListConcatExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLPair operand1 = null;
		SMPLPair operand2 = null;

		try {
			operand1 = (SMPLPair) operand1EvaluatedValue.getInternalValue(this,
					state);
			operand2 = (SMPLPair) operand2EvaluatedValue.getInternalValue(this,
					state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"List concatenation operator can only operate on pairs. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLList resultValue = new SMPLList(operand1.size()
				+ operand2.size());

		resultValue.addAll(operand1);
		resultValue.addAll(operand2);

		return new SMPLValue<SMPLPair>(resultValue);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitLogicalAnd(
			final ASTLogicalAndExpression astBitwiseAndExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astBitwiseAndExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astBitwiseAndExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLBoolean operand1 = null;
		SMPLBoolean operand2 = null;

		try {
			operand1 = (SMPLBoolean) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLBoolean) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Logical And operator can only operate on booleans. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = operand1.getBool().booleanValue()
				&& operand2.getBool().booleanValue();

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitLogicalNot(
			final ASTLogicalNotExpression astLogicalNotExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astLogicalNotExpression
				.getOperand();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);

		SMPLBoolean operand1 = null;

		try {
			operand1 = (SMPLBoolean) operand1EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Logical Not operator can only operate on booleans. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = !operand1.getBool().booleanValue();

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitLogicalOr(
			final ASTLogicalOrExpression astLogicalOrExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astOperand1 = astLogicalOrExpression
				.getOperand1();
		final ASTExpression<?> astOperand2 = astLogicalOrExpression
				.getOperand2();

		final SMPLValue<SMPLObject> operand1EvaluatedValue = astOperand1.visit(
				this, state);
		final SMPLValue<SMPLObject> operand2EvaluatedValue = astOperand2.visit(
				this, state);

		SMPLBoolean operand1 = null;
		SMPLBoolean operand2 = null;

		try {
			operand1 = (SMPLBoolean) operand1EvaluatedValue.getInternalValue(
					this, state);
			operand2 = (SMPLBoolean) operand2EvaluatedValue.getInternalValue(
					this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Logical Or operator can only operate on booleans. \n Given: "
							+ operand1EvaluatedValue.getInternalValue(this,
									state).toRepr()
							+ " and "
							+ operand2EvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final Boolean resultValue = operand1.getBool().booleanValue()
				|| operand2.getBool().booleanValue();

		final SMPLBoolean smplResult = new SMPLBoolean(resultValue);

		return new SMPLValue<SMPLBoolean>(smplResult);
	}

	@Override
	public SMPLValue<SMPLObject> visitVectorIndexAccess(
			final ASTVectorIndexAccessExpression astVectorIndexAccessExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astVector = astVectorIndexAccessExpression
				.getVectorExpression();

		final ASTExpression<?> astVectorIndex = astVectorIndexAccessExpression
				.getIndexExpression();

		final SMPLValue<SMPLObject> vectorEvaluatedValue = astVector.visit(
				this, state);
		final SMPLValue<SMPLObject> vectorIndexEvaluatedValue = astVectorIndex
				.visit(this, state);

		SMPLVector vectorEvaluated;
		SMPLInteger vectorIndex;

		try {
			vectorEvaluated = (SMPLVector) vectorEvaluatedValue
					.getInternalValue(this, state);
			vectorIndex = (SMPLInteger) vectorIndexEvaluatedValue
					.getInternalValue(this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Vector Index operator can only operate on vectors with integer arguments. \n Given: "
							+ vectorEvaluatedValue
									.getInternalValue(this, state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final int index = vectorIndex.getNumber().intValue();

		if (index < 0 || index > vectorEvaluated.size())
			throw new SMPLException("Vector index is out of range.");

		final SMPLValue<SMPLObject> vectorValue = vectorEvaluated
				.get(vectorIndex.getNumber().intValue());

		return vectorValue;
	}

	@Override
	public SMPLValue<SMPLVector> visitVectorCreation(
			final ASTVectorCreationExpression astVectorCreationExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpressionList astVectorExpressions = astVectorCreationExpression
				.getVectorExpressions();

		final SMPLValue<ArrayList<SMPLValue<?>>> smplVectorObjectsEvaluatedValue = astVectorExpressions
				.visit(this, state);

		final ArrayList<SMPLValue<?>> smplVectorObjectsEvaluated = smplVectorObjectsEvaluatedValue
				.getInternalValue(this, state);

		final SMPLVector smplVector = new SMPLVector(
				smplVectorObjectsEvaluated.size());

		for (final SMPLValue objValue : smplVectorObjectsEvaluated)
			if (SMPLSubVectorExpression.class.isAssignableFrom(objValue
					.internalType())) {
				final SMPLObject obj = (SMPLObject) objValue.getInternalValue(
						this, state);

				final BigInteger subVectorSize = ((SMPLSubVectorExpression) obj)
						.getSubVectorSize().getNumber();
				final SMPLProcedure subVectorProcedure = ((SMPLSubVectorExpression) obj)
						.getSubVectorFunction();
				for (BigInteger i = BigInteger.ZERO; i.compareTo(subVectorSize) < 0; i = i
						.add(BigInteger.ONE)) {

					final ArrayList<SMPLValue<SMPLObject>> args = new ArrayList<SMPLValue<SMPLObject>>();
					args.add(new SMPLValue<SMPLObject>(new SMPLInteger(i)));

					final SMPLEnvironment newFrame = subVectorProcedure
							.createFrame(state, args);

					final SMPLValue<SMPLObject> currentSubVectorEntry = subVectorProcedure
							.invoke(this, newFrame);

					smplVector.add(currentSubVectorEntry);
				}
			} else
				smplVector.add(objValue);

		return new SMPLValue<SMPLVector>(smplVector);
	}

	@Override
	public SMPLValue<SMPLSubVectorExpression> visitVectorComprehension(
			final ASTVectorComprehensionExpression astVectorComprehensionExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astSubVectorSize = astVectorComprehensionExpression
				.getVectorSizeExpression();
		final ASTExpression<?> astSubVectorFunction = astVectorComprehensionExpression
				.getElementProcedureExpression();

		final SMPLValue<SMPLObject> subVectorSizeEvaluatedValue = astSubVectorSize
				.visit(this, state);
		final SMPLValue<SMPLObject> subVectorFunctionEvaluatedValue = astSubVectorFunction
				.visit(this, state);

		SMPLInteger subVectorSize;
		SMPLProcedure subVectorFunction;

		try {
			subVectorSize = (SMPLInteger) subVectorSizeEvaluatedValue
					.getInternalValue(this, state);
			subVectorFunction = (SMPLProcedure) subVectorFunctionEvaluatedValue
					.getInternalValue(this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Vector Sub Expression can have an integer argument followed by a procedure. \n Given: "
							+ subVectorSizeEvaluatedValue.getInternalValue(
									this, state).toRepr()
							+ " and "
							+ subVectorFunctionEvaluatedValue.getInternalValue(
									this, state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final SMPLSubVectorExpression subVectorExpression = new SMPLSubVectorExpression(
				subVectorSize, subVectorFunction);

		return new SMPLValue<SMPLSubVectorExpression>(subVectorExpression);
	}

	@Override
	public SMPLValue<ArrayList<SMPLValue<?>>> visitExpressionList(
			final ASTExpressionList astListCreationExpression,
			final SMPLEnvironment state) throws ASTException {

		final ArrayList<SMPLValue<?>> expressionList = new ArrayList<SMPLValue<?>>(
				astListCreationExpression.numExpressions());
		for (final ASTExpression<?> astExp : astListCreationExpression
				.getExpressions()) {
			final SMPLValue<SMPLObject> expEvaluatedValue = astExp.visit(this,
					state);
			expressionList.add(expEvaluatedValue);
		}

		return new SMPLValue<ArrayList<SMPLValue<?>>>(expressionList);
	}

	@Override
	public SMPLValue<SMPLProcedure> visitCreateProcedure(
			final ASTCreateProcedureExpression astCreateProcedureExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTIdentifierList astFunctionParameters = astCreateProcedureExpression
				.getParameters();
		final ASTExpression<?> astFunctionBody = astCreateProcedureExpression
				.getBodyExpression();

		final SMPLValue<ArrayList<String>> functionParametersEvaluatedValue = astFunctionParameters
				.visit(this, state);

		final ArrayList<String> functionParameters = functionParametersEvaluatedValue
				.getInternalValue(this, state);

		final SMPLProcedure smplProcedure = new SMPLProcedure(
				functionParameters, astFunctionBody);

		smplProcedure.setClosingEnvironment(state);
		return new SMPLValue<SMPLProcedure>(smplProcedure);
	}

	@Override
	public SMPLValue<SMPLProcedure> visitCreateVariableAriyProcedure(
			final ASTCreateVariableAriyProcedureExpression astCreateVariableAriyProcedureExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTIdentifierList astFunctionParameters = astCreateVariableAriyProcedureExpression
				.getParameters();
		final ASTExpression<?> astFunctionBody = astCreateVariableAriyProcedureExpression
				.getBodyExpression();

		final ASTIdentifier astVArgIdentifier = astCreateVariableAriyProcedureExpression
				.getVArgIdentifier();
		final SMPLValue<ArrayList<String>> functionParametersEvaluatedValue = astFunctionParameters
				.visit(this, state);

		final ArrayList<String> functionParameters = functionParametersEvaluatedValue
				.getInternalValue(this, state);

		final SMPLProcedure smplProcedure = new SMPLProcedure(
				functionParameters, astFunctionBody, astVArgIdentifier);

		smplProcedure.setClosingEnvironment(state);

		return new SMPLValue<SMPLProcedure>(smplProcedure);
	}

	@Override
	public SMPLValue<SMPLProcedure> visitCreateOpenAriyProcedure(
			final ASTCreateOpenAriyProcedureExpression astCreateOpenAriyProcedureExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astFunctionBody = astCreateOpenAriyProcedureExpression
				.getBodyExpression();

		final ASTIdentifier astVArgIdentifier = astCreateOpenAriyProcedureExpression
				.getVArgIdentifier();

		final SMPLProcedure smplProcedure = new SMPLProcedure(astFunctionBody,
				astVArgIdentifier);

		smplProcedure.setClosingEnvironment(state);

		return new SMPLValue<SMPLProcedure>(smplProcedure);
	}

	@Override
	public SMPLValue<SMPLObject> visitSpecialCallProcedure(
			final ASTSpecialCallProcedureExpression astSpecialCallProcedureExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astProcedureExpression = astSpecialCallProcedureExpression
				.getProcedureExpression();
		final ASTExpression<?> astArgListExpression = astSpecialCallProcedureExpression
				.getArgListExpression();

		final SMPLValue<SMPLObject> smplProcedureEvaluatedValue = astProcedureExpression
				.visit(this, state);
		final SMPLValue<SMPLObject> smplArgListEvaluatedValue = astArgListExpression
				.visit(this, state);

		SMPLProcedure smplProcedure;
		SMPLList smplArgList;

		try {
			smplProcedure = (SMPLProcedure) smplProcedureEvaluatedValue
					.getInternalValue(this, state);
			smplArgList = (SMPLList) smplArgListEvaluatedValue
					.getInternalValue(this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Call Expression requries a procedure and a list. \n Given: "
							+ smplProcedureEvaluatedValue.getInternalValue(
									this, state).toRepr()
							+ " and "
							+ smplArgListEvaluatedValue.getInternalValue(this,
									state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}
		final ArrayList<SMPLValue<SMPLObject>> args = new ArrayList<SMPLValue<SMPLObject>>();
		args.addAll(smplArgList.subList(0, smplArgList.size() - 1));

		final SMPLEnvironment newFrame = smplProcedure.createFrame(state, args);

		final SMPLValue<SMPLObject> result = smplProcedure.invoke(this,
				newFrame);
		return result;
	}

	@Override
	public SMPLValue<SMPLLazyExpression> visitLazyEvaluation(
			final ASTLazyEvaluationExpression astLazyEvaluationExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astExpressionTOEvaluateLazily = astLazyEvaluationExpression
				.getExpression();

		final SMPLLazyExpression smplLazyExpression = new SMPLLazyExpression(
				astExpressionTOEvaluateLazily);
		return new SMPLValue<SMPLLazyExpression>(smplLazyExpression);
	}

	@Override
	public SMPLValue<ArrayList<SMPLValue<?>>> visitAliasDefinitionList(
			final ASTAliasDefinitionList astAliasDefinitionList,
			final SMPLEnvironment state) throws ASTException {
		final ArrayList<SMPLValue<?>> definitionResultList = new ArrayList<SMPLValue<?>>();
		for (final ASTAliasDefinition def : astAliasDefinitionList
				.getAliasDefinitions()) {
			final SMPLValue<?> defResult = def.visit(this, state);
			definitionResultList.add(defResult);
		}

		return new SMPLValue<ArrayList<SMPLValue<?>>>(definitionResultList);
	}

	@Override
	public SMPLValue<SMPLObject> visitLetAliasDefinitionExpansion(
			final ASTLetAliasDefinitionExpansionExpression astBodyAliasDefinitionExpansionExpression,
			final SMPLEnvironment state) throws ASTException {
		final SMPLEnvironment newEnvironment = new SMPLEnvironment(state, state);

		astBodyAliasDefinitionExpansionExpression.getDefinitions().visit(this,
				newEnvironment);

		return astBodyAliasDefinitionExpansionExpression.getBodyExpression()
				.visit(this, newEnvironment);
	}

	@Override
	public SMPLValue<SMPLObject> visitAliasAssignment(
			final ASTAliasAssignment astAliasAssignment,
			final SMPLEnvironment state) throws ASTException {
		final ASTIdentifier astIdentifier = astAliasAssignment
				.getIdentifierNode();
		final ASTExpression<?> astExpression = astAliasAssignment
				.getExpressionNode();

		final String identifierName = astIdentifier.getIdentifierName();

		/*
		 * I don't think this is a restriction if
		 * (!state.isBounded(identifierName)) throw new
		 * ASTException("The variable '" + identifierName +
		 * "' is not yet defined.");
		 */
		final SMPLValue<SMPLObject> smplExpression = astExpression.visit(this,
				state);

		state.addBinding(identifierName, smplExpression);

		return smplExpression;
	}

	@Override
	public SMPLValue<SMPLObject> visitMultipleAliasAssignment(
			final ASTMultipleAliasAssignment astMultipleAliasAssignment,
			final SMPLEnvironment state) throws ASTException {

		final ASTIdentifierList identifierList = astMultipleAliasAssignment
				.getIdentifierList();

		final ASTExpression<?> astExpression = astMultipleAliasAssignment
				.getExpressionNode();

		final SMPLValue<SMPLObject> smplExpression = astExpression.visit(this,
				state);

		for (final ASTIdentifier astIdentifier : identifierList
				.getIdentifiers()) {
			if (astIdentifier.isReference())
				throw new SMPLException(
						"Reference identifiers are limited to function parameters.");
			final String identifierName = astIdentifier.getIdentifierName();

			/*
			 * I don't think this is a restriction if
			 * (!state.isBounded(identifierName)) throw new
			 * ASTException("The variable '" + identifierName +
			 * "' is not yet defined.");
			 */
			state.addBinding(identifierName, smplExpression);
		}

		return smplExpression;
	}

	@Override
	public SMPLValue<SMPLObject> visitIfThenElse(
			final ASTIfThenElseExpression astIfThenElseExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astConditionExpression = astIfThenElseExpression
				.getConditionExpression();
		final ASTStatement<?> astConsequenceExpression = astIfThenElseExpression
				.getConsequenceExpression();
		final ASTStatement<?> astAlternativeExpression = astIfThenElseExpression
				.getAlternativeExpression();

		final SMPLValue<SMPLObject> smplConditionEvaluatedValue = astConditionExpression
				.visit(this, state);

		SMPLBoolean smplCondition;
		try {
			smplCondition = (SMPLBoolean) smplConditionEvaluatedValue
					.getInternalValue(this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"If Condition must be a boolean value. \n Given: "
							+ smplConditionEvaluatedValue.getInternalValue(
									this, state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		SMPLValue<SMPLObject> resultValue;
		if (smplCondition.getBool().booleanValue())
			resultValue = astConsequenceExpression.visit(this, state);
		else
			resultValue = astAlternativeExpression.visit(this, state);

		return resultValue;
	}

	@Override
	public SMPLValue<SMPLObject> visitCaseClasue(
			final ASTCaseClasue astCaseClasue, final SMPLEnvironment state)
			throws ASTException {
		return new SMPLValue<SMPLObject>(new SMPLObject("case_clause"));
	}

	@Override
	public SMPLValue<SMPLObject> visitCaseClauseList(
			final ASTCaseClauseList astCaseClauseList,
			final SMPLEnvironment state) throws ASTException {
		return new SMPLValue<SMPLObject>(new SMPLObject("case_clause_list"));
	}

	@Override
	public SMPLValue<SMPLObject> visitCase(
			final ASTCaseExpression astCaseExpression,
			final SMPLEnvironment state) throws ASTException {
		SMPLValue<SMPLObject> resultValue = null;
		for (final ASTCaseClasue astCaseClause : astCaseExpression
				.getClauseList().getClauses()) {
			final ASTExpression<?> astClausePredicate = astCaseClause
					.getPredicateExpression();
			final ASTExpression<?> astClauseExpression = astCaseClause
					.getClauseExpresion();

			final SMPLValue<SMPLObject> smplPredicateEvaluatedValue = astClausePredicate
					.visit(this, state);

			final SMPLBoolean smplPredicate;
			try {
				smplPredicate = (SMPLBoolean) smplPredicateEvaluatedValue
						.getInternalValue(this, state);
			} catch (final ClassCastException e) {
				final SMPLException e2 = new SMPLException(
						"Case Condition must be a boolean value. \n Given: "
								+ smplPredicateEvaluatedValue.getInternalValue(
										this, state).toRepr());
				e2.setStackTrace(e.getStackTrace());

				throw e2;
			}

			if (smplPredicate.getBool().booleanValue()) {
				resultValue = astClauseExpression.visit(this, state);
				return resultValue;
			}
		}

		return resultValue;
	}

	@Override
	public SMPLValue<SMPLObject> visitStatementSequenceExpression(
			final ASTStatementSequenceExpression astExpressionSequence,
			final SMPLEnvironment state) throws ASTException {
		final SMPLValue<SMPLObject> ret = astExpressionSequence.getStatements()
				.visit(this, state);
		return ret;
	}

	@Override
	public SMPLValue<SMPLObject> visitPrint(
			final ASTPrintExpression astPrintExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astExpressionToPrint = astPrintExpression
				.getExpression();
		final SMPLValue<SMPLObject> smplExpressionToPrintEvaluatedValue = astExpressionToPrint
				.visit(this, state);
		final SMPLObject smplExpressionToPrint = smplExpressionToPrintEvaluatedValue
				.getInternalValue(this, state);

		System.out.print(smplExpressionToPrint.toString());

		return new SMPLValue<SMPLObject>(new SMPLObject("print()"));
	}

	@Override
	public SMPLValue<SMPLObject> visitPrintLn(
			final ASTPrintLnExpression astPrintLnExpression,
			final SMPLEnvironment state) throws ASTException {
		final ASTExpression<?> astExpressionToPrint = astPrintLnExpression
				.getExpression();
		final SMPLValue<SMPLObject> smplExpressionToPrintEvaluatedValue = astExpressionToPrint
				.visit(this, state);
		final SMPLObject smplExpressionToPrint = smplExpressionToPrintEvaluatedValue
				.getInternalValue(this, state);

		System.out.println(smplExpressionToPrint.toString());

		return new SMPLValue<SMPLObject>(new SMPLObject("println()"));
	}

	@Override
	public SMPLValue<SMPLString> visitReadString(
			final ASTReadStringExpression astReadStringExpression,
			final SMPLEnvironment state) throws ASTException {

		final Scanner scan = new Scanner(System.in);
		final String line = scan.nextLine();
		scan.close();
		final SMPLString smplStringRead = new SMPLString(line);

		return new SMPLValue<SMPLString>(smplStringRead);
	}

	@Override
	public SMPLValue<SMPLInteger> visitReadInteger(
			final ASTReadIntegerExpression astReadIntegerExpression,
			final SMPLEnvironment state) throws ASTException {
		final Scanner scan = new Scanner(System.in);
		final BigInteger integerRead = scan.nextBigInteger();
		scan.close();
		final SMPLInteger smplIntegerRead = new SMPLInteger(integerRead);

		return new SMPLValue<SMPLInteger>(smplIntegerRead);
	}

	@Override
	public SMPLValue<ArrayList<String>> visitIdentifierList(
			final ASTIdentifierList astIdentifierList,
			final SMPLEnvironment state) throws ASTException {
		final ArrayList<String> identifiers = new ArrayList<String>();

		for (final ASTIdentifier iden : astIdentifierList.getIdentifiers())
			identifiers.add(iden.getIdentifierName());

		return new SMPLValue<ArrayList<String>>(identifiers);
	}

	@Override
	public SMPLValue<SMPLBoolean> visitBooleanConstant(
			final ASTBooleanConstantExpression astBooleanConstantExpression,
			final SMPLEnvironment state) throws ASTException {

		final SMPLBoolean smplBoolean = new SMPLBoolean(
				astBooleanConstantExpression.getBool());

		return new SMPLValue<SMPLBoolean>(smplBoolean);
	}

	@Override
	public SMPLValue<ArrayList<SMPLValue>> visitArgumentList(
			final ASTArgumentList astParameterList, final SMPLEnvironment state)
			throws ASTException {
		final ArrayList<SMPLValue> arguments = new ArrayList<SMPLValue>();

		for (final ASTExpression<?> astExp : astParameterList.getExpressions()) {
			final SMPLValue<SMPLObject> smplExp = astExp.visit(this, state);
			arguments.add(smplExp);
		}
		return new SMPLValue<ArrayList<SMPLValue>>(arguments);
	}

	@Override
	public SMPLValue<SMPLObject> visitCallProcedure(
			final ASTCallProcedureExpression astCallProcedureExpression,
			final SMPLEnvironment state) throws ASTException {

		final ASTExpression<?> astFunctionExpression = astCallProcedureExpression
				.getIdentifier();

		final SMPLValue<ArrayList<SMPLValue<SMPLObject>>> smplParametersEvaluatedValue = astCallProcedureExpression
				.getParameters().visit(this, state);
		final SMPLValue<SMPLObject> smplProcedureEvaluatedValue = astFunctionExpression
				.visit(this, state);

		final SMPLProcedure smplProcedure;
		try {
			smplProcedure = (SMPLProcedure) smplProcedureEvaluatedValue
					.getInternalValue(this, state);
		} catch (final ClassCastException e) {
			final SMPLException e2 = new SMPLException(
					"Function call must be invoked with a procedure object. \n Given: "
							+ smplProcedureEvaluatedValue.getInternalValue(
									this, state).toRepr());
			e2.setStackTrace(e.getStackTrace());

			throw e2;
		}

		final ArrayList<SMPLValue<SMPLObject>> args = smplParametersEvaluatedValue
				.getInternalValue(this, state);

		final SMPLEnvironment newFrame = smplProcedure.createFrame(state, args);

		final SMPLValue<SMPLObject> result;
		result = smplProcedure.invoke(this, newFrame);

		return result;
	}

	@Override
	public SMPLValue visitDynamicBindingExpression(
			final ASTDynamicBindingsExpression astDynamicIdentifiersExpression,
			final SMPLEnvironment state) throws ASTException {

		for (final ASTIdentifier iden : astDynamicIdentifiersExpression
				.getDynamicIdentifiers().getIdentifiers())
			if (iden.isReference())
				throw new SMPLException(
						"Reference identifiers are limited to function parameters.");
			else
				state.addDynamicBinding(iden.getIdentifierName());

		return new SMPLValue<SMPLObject>(new SMPLObject(
				"dynamic_identifiers_expression"));
	}

	@Override
	public SMPLValue<SMPLObject> visitNiiList(final ASTNillList astNill,
			final SMPLEnvironment state) throws ASTException {
		return new SMPLValue<SMPLObject>(new SMPLNillObject());
	}
}
