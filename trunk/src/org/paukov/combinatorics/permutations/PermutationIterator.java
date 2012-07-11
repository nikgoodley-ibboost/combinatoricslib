package org.paukov.combinatorics.permutations;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * Iterator of permutation generator
 * 
 * @author Dmytro.Paukov
 * @see ICombinatoricsVector
 * @see PermutationGenerator
 * @param <T>
 *            Type of elements of permutations
 */
public class PermutationIterator<T> extends Iterator<ICombinatoricsVector<T>> {

	/**
	 * Generator
	 */
	protected final Generator<T> _generator;

	/**
	 * Current permutation
	 */
	protected ICombinatoricsVector<T> _currentPermutation;

	/**
	 * Current index of current permutation
	 */
	protected long _currentIndex = 0;

	/**
	 * Number of elements in the permutations
	 */
	protected final int _length;

	/**
	 * Internal data
	 */
	private int[] _pZ = null;
	private int[] _pP = null;
	private int[] _pD = null;
	private int m = 0;
	private int w = 0;
	private int pm = 0;
	private int dm = 0;
	private int zpm = 0;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Permutation generator
	 */
	public PermutationIterator(Generator<T> generator) {
		_generator = generator;
		_length = generator.getOriginalVector().getSize();
		_currentPermutation = new CombinatoricsVector<T>(
				generator.getOriginalVector());
		_pZ = new int[_length + 2];
		_pP = new int[_length + 2];
		_pD = new int[_length + 2];
		init();
	}

	/**
	 * Initialize the iteration process
	 */
	private void init() {
		_currentIndex = 0;

		m = 0;
		w = 0;
		pm = 0;
		dm = 0;
		zpm = 0;

		for (int i = 1; i <= _length; i++) {
			_pP[i] = i;
			_pZ[i] = i;
			_pD[i] = -1;
		}
		_pD[1] = 0;
		_pZ[_length + 1] = m = _length + 1;
		_pZ[0] = _pZ[_length + 1];

	}

	/**
	 * Returns the current permutation
	 */
	@Override
	public ICombinatoricsVector<T> getCurrentItem() {
		return new CombinatoricsVector<T>(_currentPermutation);
	}

	/**
	 * Return true if the iteration process is finished
	 */
	@Override
	public boolean isDone() {
		return m == 1;
	}

	/**
	 * Moves to the next permutation
	 */
	@Override
	public ICombinatoricsVector<T> next() {

		for (int i = 1; i <= _length; i++) {
			int index = _pZ[i] - 1;
			_currentPermutation.setValue(i - 1, _generator.getOriginalVector()
					.getValue(index));
		}
		m = _length;
		while (_pZ[_pP[m] + _pD[m]] > m) {
			_pD[m] = -_pD[m];
			m--;
		}
		pm = _pP[m];
		dm = pm + _pD[m];
		w = _pZ[pm];
		_pZ[pm] = _pZ[dm];
		_pZ[dm] = w;
		zpm = _pZ[pm];
		w = _pP[zpm];
		_pP[zpm] = pm;
		_pP[m] = w;
		_currentIndex++;

		return getCurrentItem();
	}

	/**
	 * Returns the current permutation as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermutationIterator=[#" + _currentIndex + ", "
				+ _currentPermutation + "]";
	}

}
