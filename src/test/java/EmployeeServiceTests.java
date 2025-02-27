import junit.framework.Assert;
import org.junit.Test;
import org.rucha.EmployeeServices.EmployeeService;
import org.rucha.companyEmployee.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeServiceTests {

    private EmployeeService employeeService = new EmployeeService();
    private final Map<Integer, Employee> empMap = new HashMap<>();
    private final List<Employee> empList = new ArrayList<>();
     {
         Employee e1=new Employee(123, "Joe", "Doe", 60000, 0);
         Employee e2=new Employee(124, "Martin", "Chekov", 45000, 123);
         Employee e3=new Employee(125, "Bob", "Ronstad", 47000, 123);
         Employee e4=new Employee(300, "Alice", "Hasacat", 50000, 124);
         Employee e5=new Employee(305, "Brett", "Hardleaf", 34000, 300);

         empMap.put(123,e1 );
         empMap.put(124, e2);
         empMap.put(125, e3);
         empMap.put(300, e4);
         empMap.put(305, e5);
         empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        empList.add( e4);
        empList.add(e5) ;
    }



    @Test
    public void testReportingLineLength() {
        Assert.assertEquals(0, employeeService.getReportingLineLength(empMap.get(123),empMap));
        Assert.assertEquals(3, employeeService.getReportingLineLength(empMap.get(305),empMap));
    }

    @Test
    public void testAvgSalaryCalculation() {
        double avgSalary=47500.0;
        Assert.assertEquals(avgSalary, employeeService.getAverageManagerSalary(empList).getAsDouble());
    }

    @Test
    public void testManagerWithLowSalary() {
         double lowSalary=45000.0;
        double avgSalary=47500.0;
        List<Employee> managers=employeeService.getAllManagersExceptCEO(empList);
        Assert.assertEquals(lowSalary, employeeService.getLessEarningManagers(managers,avgSalary).get(0).getSalary());
    }

    @Test
    public void testManagerWithHighSalary() {
        double sal=50000.0;
        double avgSalary=47500.0;
        List<Employee> managers=employeeService.getAllManagersExceptCEO(empList);
        Assert.assertEquals(sal, employeeService.getHighEarningManagers(managers,avgSalary).get(0).getSalary());
    }


}
