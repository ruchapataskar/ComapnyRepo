import org.junit.Test;
import org.rucha.companyEmployee.Employee;
import org.rucha.mapper.InputCsvEmployeeMapper;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvMapperTest {


    @Test
    public void testReadingEmployeeObjectsFromCsvData() throws Exception {
        File testFile = new File("src/main/resources/input.csv");
        List<Employee> empList = InputCsvEmployeeMapper.readFile(testFile);
        Employee e=empList.get(0);
        assertEquals(5, empList.size());
        assertEquals("Joe", e.getFirstName());
        assertEquals("Doe", e.getLastName());
        assertEquals(123, e.getEmpId());
        assertEquals(0, e.getManagerId());

    }
}
