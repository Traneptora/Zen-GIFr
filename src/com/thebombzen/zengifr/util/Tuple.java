package com.thebombzen.zengifr.util;

import java.util.Objects;

/**
 * This represents a tuple of two elements, i.e. an ordered pair. The purpose is
 * to use a Tuple in a Map or a Set, so we can can create a set of ordered pairs
 * or a Map whose domain is a set of ordered pairs.
 *
 * There are no restrictions on the components, other than restrictions enforced
 * by the generic typing.
 * 
 * @param <E>
 *            This is the type of the first component of the ordered pair.
 * @param <F>
 *            This is the type of the second component of the ordered pair.
 */
public class Tuple<X, Y> {
	public X x;
	public Y y;

	/**
	 * Construct an ordered pair of two elements.
	 * 
	 * @param e
	 *            the first component of the ordered pair
	 * @param f
	 *            the second component of the ordered pair
	 */
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The hash code function calls the hash code of its two components. Because
	 * this class is designed for easy usage in HashMaps and HashSets, it is
	 * advised to use X and Y that implement some form of hashCode that isn't
	 * the identity hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * The equals function calls the equals function of its two components. An
	 * ordered pair is equal to another if and only if the components equal
	 * their respective counterparts, according to E and F's equals() method.
	 * Please override those so they are realistic and do not use ==.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Tuple)) {
			return false;
		}
		Tuple<?, ?> other = (Tuple<?, ?>)obj;
		return Objects.deepEquals(x, other.x) && Objects.deepEquals(y, other.y);
	}



	/**
	 * This method returns a string of the form "(x, y)" where x and y are the
	 * first and second components, respectively.
	 */
	@Override
	public String toString() {
		return "(" + Objects.toString(x) + ", " + Objects.toString(y) + ")";
	}

}
