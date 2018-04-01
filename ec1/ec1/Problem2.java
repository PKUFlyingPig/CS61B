package ec1;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Problem2 {
	/**
	 * Given a list of Cities and their pairwise distances, find the shortest cycle that
	 * visits every City exactly once.
	 *
	 * Return the list of cities visited in order.
	 * The first city must be the same as the last city.
	 *
	 * Use getDistance(city1, city2) to find the pairwise distance between two cities.
	 *
	 * Runtime: O(N^2 log N)
	 */
	public List<City> solve() {
		// FIXME
		return null;
	}

	public Map<CityPair, Double> distances;

	public double getDistance(City city1, City city2) {
		return distances.getOrDefault(new CityPair(city1.id, city2.id), Double.NaN);
	}

	private static class City {
		int id;
	}

	private static class CityPair {
		int city1;
		int city2;

		CityPair(int city1, int city2) {
			if (city1 < city2) {
				this.city1 = city1;
				this.city2 = city2;
			} else {
				this.city1 = city2;
				this.city2 = city1;
			}
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			CityPair cityPair = (CityPair) o;
			return city1 == cityPair.city1 && city2 == cityPair.city2;
		}

		@Override
		public int hashCode() {
			return Objects.hash(city1, city2);
		}
	}
}
