package ec1;

import java.util.List;

public class Problem3 {
	/**
	 * Given the list of students and their neighbors, assign test versions to students
	 * such that no two students sitting next to each other have the same test version.
	 * Additionally, use the fewest number of distinct test versions possible.
	 *
	 * Assign the results to the students' 'version' field.
	 *
	 * You may assume that if A is a neighbor of B, B is a also a neighbor of A.
	 * A student may be sitting next to an arbitrary number of other students.
	 *
	 * Runtime: O(N^3)
	 */
	public void solve() {
		// FIXME
	}

	public List<Student> students;

	public static class Student {
		public int id;
		public int version;

		public List<Student> neighbors;
	}
}
