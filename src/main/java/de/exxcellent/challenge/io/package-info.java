/**
 * Package for handling input and output
 * <p>
 * Objects for specific data transfer object (DTO) formats such as CSV are here.
 * Support for reading and writing objects to other DTO formats, such as JSON,
 * should be added to this package.
 * <p>
 * The {@link de.exxcellent.challenge.io.StringFieldsIterator} interface is
 * base for accessing DTOs. Implementations convert a DTO format into an array
 * of strings, and a method that maps the name of the field to an index in the
 * array. The {@link de.exxcellent.challenge.io.CsvReader} serves as an
 * example.
 *
 * @author Scott Purcell <son_91egp@yahoo.com>
 */
package de.exxcellent.challenge.io;