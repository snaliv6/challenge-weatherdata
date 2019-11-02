package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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

    	String teamWithSmallestGoalSpread = "Some mediocre team";
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
        			int goals = Integer.parseInt(fields[fieldNameToIndex.get("Goals")]);
        			int goalsAllowed = Integer.parseInt(fields[fieldNameToIndex.get("Goals Allowed")]);
        			int diff = Math.abs(goals - goalsAllowed);
        			if (diff < spread)
        			{
        				spread = diff;
        				teamWithSmallestGoalSpread = fields[fieldNameToIndex.get("Team")];
        			}
        		}
        		i++;
        	}
        } catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
