package org.rucha.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.rucha.companyEmployee.Employee;

import java.io.File;
import java.util.List;

public class InputCsvEmployeeMapper {

    public static List<Employee> readFile(File inputCsv) throws Exception {

        CsvMapper mapper = new CsvMapper();

        CsvSchema empSchema = mapper.schemaFor(Employee.class)
                .withSkipFirstDataRow(true)
                .withColumnSeparator(',').withoutQuoteChar();

        try (MappingIterator<Employee> empItr = mapper
                .readerFor(Employee.class)
                .with(empSchema).readValues(inputCsv)) {

            return empItr.readAll();
        }
    }

}
