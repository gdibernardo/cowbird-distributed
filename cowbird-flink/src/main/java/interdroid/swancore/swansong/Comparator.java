package interdroid.swancore.swansong;


/**
 * The ways we know how to compare values.
 * 
 * @author roelof &lt;rkemp@cs.vu.nl&gt;
 * @author nick &lt;palmer@cs.vu.nl&gt;
 * 
 */
public enum Comparator implements ParseableEnum<Comparator> {

	/** greater than. */
	GREATER_THAN(0, ">"),
	/** less than. */
	LESS_THAN(1, "<"),
	/** greater than or equal to. */
	GREATER_THAN_OR_EQUALS(2, ">="),
	/** less than or equal to. */
	LESS_THAN_OR_EQUALS(3, "<="),
	/** equal to. */
	EQUALS(4, "=="),
	/** not equal to. */
	NOT_EQUALS(5, "!="),
	/** Regular Expression Match. */
	REGEX_MATCH(6, "regex"),
	/** String contains. */
	STRING_CONTAINS(7, "contains");

	/**
	 * The converted value for this enum.
	 */
	private final int mValue;

	/** The string version of the enum. */
	private String mName;

	/**
	 * Constructs a Comparator.
	 * 
	 * @param value
	 *            the convert value
	 * @param name
	 *            the name of the comparator
	 */
	private Comparator(final int value, final String name) {
		mValue = value;
		mName = name;
	}

	@Override
	public final String toString() {
		return mName;
	}

	@Override
	public int convert() {
		return mValue;
	}

	@Override
	public Comparator convertInt(final int val) {
		Comparator ret = null;
		for (Comparator comp : Comparator.values()) {
			if (comp.convert() == val) {
				ret = comp;
				break;
			}
		}
		return ret;
	}

	/**
	 * Parses a string and returns a Comparator.
	 * 
	 * @param val
	 *            a string to parse
	 * @return the parsed Comparator
	 */
	private Comparator parseString(final String val) {
		Comparator ret = null;
		for (Comparator comp : Comparator.values()) {
			if (comp.toParseString().equals(val)) {
				ret = comp;
				break;
			}
		}
		return ret;
	}

	/**
	 * Parse a string and return the value.
	 * 
	 * @param value
	 *            the value to parse
	 * @return the enum which matches the string.
	 */
	public static Comparator parse(final String value) {
		return GREATER_THAN.parseString(value);
	}

	/**
	 * Converts a persisted int to the matching enumeration value.
	 * 
	 * @param value
	 *            the value to get the enumeration for
	 * @return the enumeration matching this value
	 */
	public static Comparator convert(final int value) {
		return GREATER_THAN.convertInt(value);
	}

	@Override
	public String toParseString() {
		return mName;
	}


	private static Object promote(Object object) {
		if (object instanceof Integer) {
			return Long.valueOf((Integer) object);
		}
		if (object instanceof Float) {
			return Double.valueOf((Float) object);
		}
		return object;
	}

	public static TriState comparePair(final Comparator comparator,
									   Object left, Object right) {
		TriState result = TriState.FALSE;
		// promote types
		left = promote(left);
		right = promote(right);

		switch (comparator) {
			case LESS_THAN:
				if (((Comparable) left).compareTo(right) < 0) {
					result = TriState.TRUE;
				}
				break;
			case LESS_THAN_OR_EQUALS:
				if (((Comparable) left).compareTo(right) <= 0) {
					result = TriState.TRUE;
				}
				break;
			case GREATER_THAN:
				if (((Comparable) left).compareTo(right) > 0) {
					result = TriState.TRUE;
				}
				break;
			case GREATER_THAN_OR_EQUALS:
				if (((Comparable) left).compareTo(right) >= 0) {
					result = TriState.TRUE;
				}
				break;
			case EQUALS:
				if (((Comparable) left).compareTo(right) == 0) {
					result = TriState.TRUE;
				}
				break;
			case NOT_EQUALS:
				if (((Comparable) left).compareTo(right) != 0) {
					result = TriState.TRUE;
				}
				break;
			case REGEX_MATCH:
				if (((String) left).matches((String) right)) {
					result = TriState.TRUE;
				}
				break;
			case STRING_CONTAINS:
				if (((String) left).contains((String) right)) {
					result = TriState.TRUE;
				}
				break;
			default:
				throw new AssertionError("Unknown comparator '" + comparator
						+ "'. Should not happen");
		}

		return result;
	}

}
