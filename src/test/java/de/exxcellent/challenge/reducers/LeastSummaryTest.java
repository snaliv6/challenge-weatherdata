/**
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.reducers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Unit test of the least summary interface's default and static methods.
 */
class LeastSummaryTest {

	/**
	 * Verify that adding a single object produces a summary with only that object.
	 */
	@Test
	void testWithOneObject() {
		LeastSummary<Tester, Integer> summary = LeastSummary.comparing(Tester::getValue);
		Arrays.asList(new Tester("alice", 100)).forEach(summary::accept);
		assertEquals(Arrays.asList(new Tester("alice", 100)), summary.leasts());
	}

	/**
	 * Verify that adding two objects with different values to the summary returns
	 * the object with the lesser value.
	 */
	@Test
	void testWithTwoObjectsWithDifferentValues() {
		LeastSummary<Tester, Integer> summary = LeastSummary.comparing(Tester::getValue);
		Arrays.asList(new Tester("alice", 100), new Tester("bob", -100)).forEach(summary::accept);
		assertEquals(Arrays.asList(new Tester("bob", -100)), summary.leasts());
	}

	/**
	 * Verify that adding two objects with the same value to the summary returns
	 * both objects.
	 */
	@Test
	void testWithTwoObjectWithSameValue() {
		LeastSummary<Tester, Integer> summary = LeastSummary.comparing(Tester::getValue);
		Arrays.asList(new Tester("alice", 100), new Tester("bob", 100)).forEach(summary::accept);
		assertEquals(Arrays.asList(new Tester("alice", 100), new Tester("bob", 100)), summary.leasts());
	}

	/**
	 * Verify that adding two objects with the same value but different names
	 * produces a summary containing the object with the shortest name.
	 */
	@Test
	void testComparisonOfStringLength() {
		LeastSummary<Tester, Integer> summary = LeastSummary.comparing(tester -> tester.getName().length());
		Arrays.asList(new Tester("alice", 1), new Tester("bob", 1)).forEach(summary::accept);
		assertEquals(Arrays.asList(new Tester("bob", 1)), summary.leasts());
	}

	// Test class
	private static class Tester {
		private String name;
		private int value;

		public Tester(String name, int value) {
			super();
			this.name = name;
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + value;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tester other = (Tester) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (value != other.value)
				return false;
			return true;
		}
	}
}
