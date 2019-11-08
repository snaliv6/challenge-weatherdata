/**
 * The root package for the data challenge containing the entry point
 * {@link de.exxcellent.challenge.App} and containing the resource files.
 * <p>
 * The app is designed to accept CSV files of either weather of football team
 * standings files as input and to print summaries of the data as output The
 * weather summary comprises a list of days whose temperature spread is the
 * smallest in the file. The football standings summary is similar. The output
 * is a list of teams with the least absolute goal difference in the file.
 * <p>
 * The separation of concerns is organized first into packages. The packages
 * d.e.c.weather and d.e.c.football contain classes and interfaces are for
 * business logic. A UI for weather would use objects from the weather package
 * to achieve business goals such as displaying the list of days in a month that
 * have the lowest temperature spread.
 * <p>
 * The business objects are supported by objects from the d.e.c.io and
 * d.e.c.analysis packages. The IO package provides classes and interfaces for
 * handling data while the reducers package provides class and interfaces for
 * analyzing data.
 * <p>
 * For the challenge, the input data structure is CSV, but anticipates the use
 * of others formats such as JSON. The DTO package provides an interface for
 * reading data and an implementation for CSV files. The interface could easily
 * be extended for JSON other data transfer formats.
 * <p>
 * The analysis package is meant for objects that analyze data. At present the
 * package contains a single interfaces for finding objects with a minimum
 * value. Other analysis classes and interface would be added here.
 *
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge;