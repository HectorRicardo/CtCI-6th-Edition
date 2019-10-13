package Q4_07_Build_Order.DFS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hector {
	
	static String[] buildOrder(String[] projects, String[][] dependencies) {
		
		// Step 0. Tentatively assume that all projects are ready
		Set<String> projectsReady = new HashSet<>();
		for (String project : projects) {
			projectsReady.add(project);
		}
		
		// Step 1. Lets start removing some of the projects that are not ready
		// Also, build some maps for quick look up
		Map<String, Set<String>> independentToDependents = new HashMap<>();
		Map<String, Integer> projectDepedenciesCounts = new HashMap<>();
		
		for (String[] dependencyPair : dependencies) {
			String independentProject = dependencyPair[0];
			String dependentProject = dependencyPair[1];
			
			// Remove dependentProject from projectsReady
			projectsReady.remove(dependentProject);
			
			// Independent to dependent projects map for quick lookup
			if (!independentToDependents.containsKey(independentProject)) independentToDependents.put(independentProject, new HashSet<String>());
			independentToDependents.get(independentProject).add(dependentProject);
			
			// Dependent to independent projects map
			projectDepedenciesCounts.put(dependentProject, projectDepedenciesCounts.getOrDefault(dependentProject, 0) + 1);
		}
		
		String[] order = new String[projects.length];
		int idx = 0;
		
		// While there are projects ready to be built
		while (!projectsReady.isEmpty()) {

			// Lets see which dependencies are met with the next projects built
			Set<String> nextProjectsReady = new HashSet<>();
			
			for (String independentProject : projectsReady) {
				order[idx++] = independentProject;
				Set<String> dependents = independentToDependents.get(independentProject);
				if (dependents != null) {
					for (String dependentProject : dependents) {
						int newDependencyCount = projectDepedenciesCounts.get(dependentProject) - 1;
						projectDepedenciesCounts.put(dependentProject, newDependencyCount);
						
						if (newDependencyCount == 0) {
							projectDepedenciesCounts.remove(dependentProject);
							nextProjectsReady.add(dependentProject);
						}
					}
				}
			}
			projectsReady = nextProjectsReady;
		}
		return projectDepedenciesCounts.isEmpty() ? order : null;
	}
	
	public static void main(String[] args) {
		String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[][] dependencies = {
				{"a", "b"},
				{"b", "c"},
				{"a", "c"},
				{"d", "e"},
				{"b", "d"},
				{"e", "f"},
				{"a", "f"},
				{"h", "i"},
				{"h", "j"},
				{"i", "j"},
				{"g", "j"}};
		String[] buildOrder = buildOrder(projects, dependencies);
		if (buildOrder == null) {
			System.out.println("Circular Dependency.");
		} else {
			for (String s : buildOrder) {
				System.out.print(s + ", ");
			}
		}
	}
}
