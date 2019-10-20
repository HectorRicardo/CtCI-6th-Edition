package Q6_07_The_Apocalypse;

import java.util.Random;

public class Hector {
	
	static class BoysAndGirls {
		int boys;
		int girls;
		BoysAndGirls(int boys, int girls) {
			this.boys = boys;
			this.girls = girls;
		}
		double getBoysToGirlsRatio() {
			return (double)boys / (double)girls;
		}
	}
	
	static BoysAndGirls simulate(int families) {
		int boys = 0;
		int girls = families;
		Random random = new Random();
		for (int i = 0; i < families; i++) {
			boolean isBoy = random.nextBoolean();
			while (isBoy) {
				boys++;
				isBoy = random.nextBoolean();
			}
		}
		return new BoysAndGirls(boys, girls);
		
	}
	
	public static void main(String[] args) {
		BoysAndGirls simulationResults =simulate(100000);
		System.out.println(simulationResults.getBoysToGirlsRatio());
	}
	

}
