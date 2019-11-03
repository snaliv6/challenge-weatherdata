package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import de.exxcellent.challenge.csv.CsvReader;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
    	if (args.length != 2) {
    		System.out.println("Usage: App --football <path to input file>");
    		System.exit(1);
    	}

    	List<String> teamsWithSmallestGoalSpread = new ArrayList<>();
    	int spread = Integer.MAX_VALUE;

        File file = new File(args[1]);
        try(Scanner scanner = new Scanner(file))
        {
        	CsvReader reader = new CsvReader();
        	Map<String, Integer> fieldNameToIndex = new HashMap<>();
        	int i = 0;
        	while (scanner.hasNextLine())
        	{
        		String line = scanner.nextLine();
    			String[] fields = reader.fields(line);
        		if (i == 0) {
					// The first line of the file has the names of the fields. Add them to the map.
					fieldNameToIndex = reader.fieldNameToIndexMap(line);
        		} else {
        			String team = fields[fieldNameToIndex.get("Team")];
        			int goals = Integer.parseInt(fields[fieldNameToIndex.get("Goals")]);
        			int goalsAllowed = Integer.parseInt(fields[fieldNameToIndex.get("Goals Allowed")]);
        			int diff = Math.abs(goals - goalsAllowed);
        			if (teamsWithSmallestGoalSpread.isEmpty())
        			{
						// This is the first team in the file, so it has the least goal difference. Add
						// it to the list.
        				teamsWithSmallestGoalSpread.add(team);
        				spread = diff;
        			}
        			else if (diff < spread)
        			{
        				// The current team has a smaller goal difference. Clear the list and add this team.
        				spread = diff;
        				teamsWithSmallestGoalSpread.clear();
        				teamsWithSmallestGoalSpread.add(team);
        			}
        			else if (diff == spread)
        			{
        				// The current team has an equally low spread. Add it to the list.
        				teamsWithSmallestGoalSpread.add(team);
        			}
        		}
        		i++;
        	}
        } catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

        System.out.printf("Team(s) with smallest goal spread       : %s%n", Arrays.toString(teamsWithSmallestGoalSpread.toArray()));
    }
}
