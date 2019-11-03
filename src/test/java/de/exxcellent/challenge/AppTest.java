package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Example JUnit4 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public class AppTest {

	private String successLabel = "not successful";

	@BeforeEach
	public void setUp() {
		successLabel = "successful";
	}

	@Test
	public void aPointlessTest() {
		assertEquals("successful", successLabel, "My expectations were not met");
	}

	@Test
	public void runFootballWithoutDups() throws IOException, URISyntaxException {
		PrintStream originalOut = System.out;
		Path path = Files.createTempFile("challenge", ".txt");
		File file = path.toFile();
		file.deleteOnExit();
		PrintStream out = new PrintStream(file);
		System.setOut(out);
		URI uri = AppTest.class.getResource("football-without-dups.csv").toURI();
		File f = new File(uri);
		App.main("--football", f.getAbsolutePath());
		System.setOut(originalOut);
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println(line);
			assertTrue(line.contains("Aston_Villa"));
		}
		s.close();	
	}

	@Test
	public void runFootballWithDups() throws IOException, URISyntaxException {
		PrintStream originalOut = System.out;
		Path path = Files.createTempFile("challenge", ".txt");
		File file = path.toFile();
		file.deleteOnExit();
		PrintStream out = new PrintStream(file);
		System.setOut(out);
		URI uri = AppTest.class.getResource("football-with-dups.csv").toURI();
		File f = new File(uri);
		App.main("--football", f.getAbsolutePath());
		System.setOut(originalOut);
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println(line);
			assertTrue(line.contains("West_Ham"));
			assertTrue(line.contains("Aston_Villa"));
			assertTrue(line.contains("Southampton"));
		}
		s.close();	
	}

	@Test
	public void runWeatherWithoutDups() throws IOException, URISyntaxException {
		PrintStream originalOut = System.out;
		Path path = Files.createTempFile("challenge", ".txt");
		File file = path.toFile();
		file.deleteOnExit();
		PrintStream out = new PrintStream(file);
		System.setOut(out);
		URI uri = AppTest.class.getResource("weather-without-dups.csv").toURI();
		File f = new File(uri);
		App.main("--weather", f.getAbsolutePath());
		System.setOut(originalOut);
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println(line);
			assertTrue(line.contains("14"));
		}
		s.close();	
	}

}