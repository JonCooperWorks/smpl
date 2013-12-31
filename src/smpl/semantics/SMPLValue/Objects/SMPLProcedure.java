/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.util.ArrayList;
import java.util.List;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.syntax.ASTException;
import smpl.syntax.ASTExpression;
import smpl.syntax.ASTIdentifier;

/**
 * 
 */
public class SMPLProcedure extends SMPLObject {

	private final ArrayList<String> mParameterList;
	private final ASTExpression<?> mBody;
	private final ASTIdentifier mVArgIdentifier;

	private SMPLEnvironment mClosingEnvironment;

	private final int mFlags;

	public SMPLProcedure(final ASTExpression<?> body,
			final ASTIdentifier varialeArityIdentifier) {
		this(new ArrayList<String>(), body, varialeArityIdentifier,
				Flags.VariableArity);
	}

	public SMPLProcedure(final ArrayList<String> params,
			final ASTExpression<?> body,
			final ASTIdentifier excessArityIdentifier) {
		this(params, body, excessArityIdentifier, Flags.VariableArityToPRest);
	}

	public SMPLProcedure(final ArrayList<String> params,
			final ASTExpression<?> body) {
		this(params, body, null, Flags.None);
	}

	protected SMPLProcedure(final ArrayList<String> params,
			final ASTExpression<?> body, final ASTIdentifier vArgIdentifier,
			final int flags) {
		super("proc");

		mParameterList = params;
		mBody = body;
		mFlags = flags;

		mVArgIdentifier = vArgIdentifier;
	}

	public SMPLEnvironment getClosingEnvironment() {
		return mClosingEnvironment;
	}

	public void setClosingEnvironment(final SMPLEnvironment closingEnv) {
		mClosingEnvironment = closingEnv;
	}

	private boolean isVariableArity() {
		return mFlags == Flags.VariableArity;
	}

	private boolean isVariableArityToVArg() {
		return mFlags == Flags.VariableArityToPRest;
	}

	@Override
	public String toRepr() {
		return toString();
	}

	@Override
	public String toString() {
		return "proc(" + mParameterList.toString() + ")";
	}

	protected static interface Flags {
		int None = 0;
		int VariableArityToPRest = 1;
		int VariableArity = 2;
	}

	public SMPLEnvironment createFrame(final SMPLEnvironment state,
			final ArrayList<SMPLValue<SMPLObject>> args) throws SMPLException {
		final SMPLEnvironment newFrame = new SMPLEnvironment(
				getClosingEnvironment(), state);

		final int specifiedArity = mParameterList.size();
		final int passedArity = args.size();
		if (passedArity > specifiedArity)
			if (isVariableArity()) {
				final SMPLList argList = new SMPLList(passedArity);
				argList.addAll(args);
				final String vArgIdentifier = mVArgIdentifier
						.getIdentifierName();
				newFrame.addBinding(vArgIdentifier, new SMPLValue<SMPLObject>(
						argList));
			} else if (isVariableArityToVArg()) {
				final SMPLList argList = new SMPLList(passedArity
						- specifiedArity);
				final List<SMPLValue<SMPLObject>> extraArgs = args.subList(
						specifiedArity, passedArity);
				argList.addAll(extraArgs);
				final String vArgIdentifier = mVArgIdentifier
						.getIdentifierName();
				newFrame.addBinding(vArgIdentifier, new SMPLValue<SMPLObject>(
						argList));
				int i = 0;
				for (final String formalParameter : mParameterList) {
					newFrame.addBinding(formalParameter, args.get(i));
					i++;
				}
			} else
				throw new SMPLException("Function called with '" + args.size()
						+ "' arguments but '" + specifiedArity + "' exprected.");
		else if (passedArity == specifiedArity) {
			int i = 0;
			for (final String formalParameter : mParameterList) {
				newFrame.addBinding(formalParameter, args.get(i));
				i++;
			}
		} else
			throw new SMPLException("Function called with '" + args.size()
					+ "' arguments but '" + specifiedArity + "' exprected.");

		return newFrame;
	}

	@SuppressWarnings("unchecked")
	public SMPLValue<SMPLObject> invoke(final SMPLEvaluator smplEvaluator,
			final SMPLEnvironment newFrame) throws ASTException {
		return mBody.visit(smplEvaluator, newFrame);
	}
}
