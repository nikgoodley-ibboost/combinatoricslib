package org.paukov.combinatorics.util;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;

/**
 * @author Dmytro Paukov
 * 
 */
public class ComplexCombinationGenerator<T> extends
		Generator<CombinatoricsVector<T>> {

	protected final CombinatoricsVector<T> _originalVector;
	protected final int _combinationLength;
	protected final boolean _isOrderImportant;
	protected final boolean _excludeEmptySet;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public ComplexCombinationGenerator(CombinatoricsVector<T> originalVector,
			int combinationsLength) {
		this(originalVector, combinationsLength, true, false);
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 * @param isOrderImportant
	 *            true if the order of the generated combinations is important
	 * 
	 */
	public ComplexCombinationGenerator(CombinatoricsVector<T> originalVector,
			int combinationsLength, boolean isOrderImportant) {
		this(originalVector, combinationsLength, isOrderImportant, false);
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 * @param isOrderImportant
	 *            true if the order of the generated combinations is important
	 * @param excludeEmptySet
	 *            true if the empty set has to be excluded from the generated
	 *            combinations
	 */
	public ComplexCombinationGenerator(CombinatoricsVector<T> originalVector,
			int combinationsLength, boolean isOrderImportant,
			boolean excludeEmptySet) {

		if (combinationsLength > originalVector.getSize())
			throw new RuntimeException(
					"Unable to generate complex combinations, the requested combination length is more then the size of the original vector, length: "
							+ combinationsLength
							+ ", originalVector: "
							+ originalVector);

		_originalVector = new CombinatoricsVector<T>(originalVector);
		_combinationLength = combinationsLength;
		_isOrderImportant = isOrderImportant;
		_excludeEmptySet = excludeEmptySet;
	}

	/**
	 * Returns the original vector wrapped into one more vector.
	 * 
	 * @return Returns the _originalVector.
	 */
	public CombinatoricsVector<CombinatoricsVector<T>> getCoreObject() {
		CombinatoricsVector<CombinatoricsVector<T>> result = new CombinatoricsVector<CombinatoricsVector<T>>();
		result.addValue(_originalVector);
		return result;
	}

	/**
	 * Returns length of the combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns number of generated combinations
	 */
	public long getNumberOfGeneratedObjects() {
		// TODO: needs to be implemented
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates an iterator of the complex combinations
	 */
	public Iterator<CombinatoricsVector<CombinatoricsVector<T>>> createIterator() {
		return new ComplexCombinationIterator<T>(this);
	}

	/**
	 * Is the order of the generated combinations important
	 */
	public boolean isOrderImportant() {
		return _isOrderImportant;
	}

	/**
	 * Returns true if the empty set has to be excluded from the generated
	 * combinations
	 */
	public boolean excludeEmptySet() {
		return _excludeEmptySet;
	}

	/**
	 * This method converts a composition into a string
	 * 
	 * @param vector
	 *            The vector
	 * @return A string representation of the given vector
	 */
	public static <V> String convert2String(
			CombinatoricsVector<CombinatoricsVector<V>> vector) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < vector.getSize(); i++) {
			sb.append(vector.getValue(i).getVector().toString());
			if (i != vector.getSize() - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();

	}
}
