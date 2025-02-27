import junit.framework.Assert;
import org.junit.Test;
import org.rucha.EmployeeServices.EmployeeService;
import org.rucha.companyEmployee.Employee;
import org.rucha.mapper.InputCsvEmployeeMapper;

import java.io.File;
import java.util.List;


public class EmployeeReaderTests {


    @Test
    public void testEmplyeeDataFromCsv() throws Exception{
        File csvFile=new File("src/test/java/testresource/input.csv");
        Assert.assertTrue(csvFile.exists());
        List<Employee>  list =InputCsvEmployeeMapper.readFile(csvFile);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(5,list.size());
        Employee emp1 = list.get(0);
        Assert.assertEquals(123,emp1.getEmpId());
        Assert.assertEquals("Joe",emp1.getFirstName());
        Assert.assertEquals("Doe",emp1.getLastName());
        Assert.assertEquals(0,emp1.getManagerId());

    }


}
