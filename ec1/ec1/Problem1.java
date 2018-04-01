package ec1;

import java.util.List;

public class Problem1 {
	/**
	 * Given the list of Airports and their possible (one-way!) flights,
	 * find the list of flights to take (in order) that gives the largest
	 * possible number of reward points, while visiting each airport at most once.
	 *
	 * Runtime: O(N^2 M^2).
	 * With N airports and M flights
	 */
	public List<Flight> solve() {
		// FIXME
		return null;
	}

	public List<Airport> airports;
	public List<Flight> flights;

	private static class Airport {
		public int id;
		public List<Flight> outgoing;
		public List<Flight> incoming;
	}

	private static class Flight {
		public Airport start;
		public Airport end;

		public double points;
	}
}
