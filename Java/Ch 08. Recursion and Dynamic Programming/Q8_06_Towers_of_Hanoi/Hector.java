package Q8_06_Towers_of_Hanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hector {
	
	private static class Tower extends Stack<Integer> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 300800043009424030L;
		
		private final String id;
		
		public Tower(String id) {
			super();
			this.id = id;
		}
		
		@Override
		public String toString() {
			return "Contents of Tower " + id + ": " + super.toString(); 
		}
	}
	
	private static class Movement {
		public Tower fromTower;
		private int disk;
		private Tower toTower;
		
		public Movement() {}
		
		public Movement(Tower fromTower, int disk, Tower toTower) {
			this.fromTower = fromTower;
			this.disk = disk;
			this.toTower = toTower;
		}
		
		@Override
		public String toString() {
			return "Move " + disk + " from " + fromTower.id + " to " + toTower.id;
		}
	}
	
	private static Movement move(Tower origin, Tower destination) {
		int disk = origin.pop();
		destination.push(disk);
		return new Movement(origin, disk, destination);
	}
	
	
	public static List<Movement> solve(Tower origin, Tower buffer, Tower destination) {
		List<Movement> solution = new ArrayList<>();
		solve(origin, buffer, destination, origin.size(), solution);
		return solution;
	}
	
	private static void solve(Tower origin, Tower buffer, Tower destination, int disks, List<Movement> movements) {
		if (disks == 1) {
			movements.add(move(origin, destination));
		} else {
			solve(origin, destination, buffer, disks - 1, movements);
			int previousSize = movements.size();
			movements.add(move(origin, destination)); // shift movement
			for (int i = 0; i < previousSize; i++) {
				Movement movement = movements.get(i);
				Movement newMovement = new Movement();
				newMovement.disk = movement.disk;
				
				if (movement.fromTower == origin) {
					newMovement.fromTower = buffer;
				} else if (movement.fromTower == buffer) {
					newMovement.fromTower = destination;
				} else {
					newMovement.fromTower = origin;
				}
				
				if (movement.toTower == origin) {
					newMovement.toTower = buffer;
				} else if (movement.toTower == buffer) {
					newMovement.toTower = destination;
				} else {
					newMovement.toTower = origin;
				}
				
				movements.add(newMovement);
			}
		}
	}
	
	public static void main(String[] args) {
		int N = 5;
		
		Tower origin = new Tower("s");
		for (int i = N - 1; i >= 0; i--) {
			origin.push(i);
		}
		
		Tower buffer = new Tower("b");
		Tower destination = new Tower("d");
		
		List<Movement> solution = solve(origin, buffer, destination);
		System.out.println(origin);
		for (Movement movement : solution) {
			System.out.println(movement);
		}
		System.out.println(destination);
	}
}
