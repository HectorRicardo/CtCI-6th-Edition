package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hector {
	
	static String[] buildOrder(String[] projects, String[][] dependencies) {
		
		// Tentatively assume that all projects are ready
		Set<String> projectsReady = new HashSet<>();
		for (String project : projects) {
			projectsReady.add(project);
		}
		
		// Lets start removing some of the projects that are not ready
		Map<String, Set<String>> independentToDependents = new HashMap<>();
		Map<String, Set<String>> dependentToIndependents = new HashMap<>();
		for (String[] dependencyPair : dependencies) {
			String independentProject = dependencyPair[0];
			String dependentProject = dependencyPair[1];
			
			// Remove dependentProject from projectsReady
			projectsReady.remove(dependentProject);
			
			// Independent to dependent projects map, used for quick lookup
			if (!independentToDependents.containsKey(independentProject)) {
				independentToDependents.put(independentProject, new HashSet<String>());
			}
			independentToDependents.get(independentProject).add(dependentProject);
			
			// Dependent to independent projects map
			if (!dependentToIndependents.containsKey(dependentProject)) {
				dependentToIndependents.put(dependentProject, new HashSet<String>());
			}
			dependentToIndependents.get(dependentProject).add(independentProject);
		}
		
		String[] order = new String[projects.length];
		int idx = 0;
		for (String independentProject : projectsReady) {
			order[idx++] = independentProject;
			for (String dependentProject : independentToDependents.get(independentProject)) {
				Set<String> currentDependencies = dependentToIndependents.get(dependentProject);
				currentDependencies.remove(independentProject);
				if (currentDependencies.isEmpty()) {
					projectsReady.add(dependentProject);
				}
			}
		}
		
		return order;
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
				System.out.println(s);
			}
		}
	}
}
