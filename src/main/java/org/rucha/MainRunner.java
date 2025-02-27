package org.rucha;

import org.rucha.EmployeeServices.EmployeeService;
import org.rucha.companyEmployee.Employee;
import org.rucha.mapper.InputCsvEmployeeMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainRunner {

    public static void main(String[] args) {
        File inputFile = new File("src/main/resources/input.csv");
        try {
            if(inputFile.exists()) {
                List<Employee> empList = InputCsvEmployeeMapper.readFile(inputFile);
                EmployeeService empService = new EmployeeService();
                List<Employee> mgrList = empService.getAllManagersExceptCEO(empList);
                OptionalDouble optionalSal = empService.getAverageManagerSalary(empList);

                if (optionalSal.isPresent()) {
                    double avgSalary=optionalSal.getAsDouble();
                    getMinSalaryManager(empService,mgrList,avgSalary);
                    getMaxSalaryManager(empService,mgrList,avgSalary);
                }
                getMaxEmployeeReportingLineLength(empService,empList);

            }else {
                System.out.println("Input file does not exist");
            }
        }catch(IOException ioException){
            System.out.println("Exception while reading input file "+ioException.getMessage());
        }catch(Exception  exception){
            System.out.println("An exception has occurred"+ exception.getMessage());
        }
    }

    private static void getMaxSalaryManager(EmployeeService empService, List<Employee> mgrList, double avgSalary) {
        System.out.println("High earning managers are:");
        empService.getHighEarningManagers(mgrList,avgSalary)
                .forEach(e->System.out.println(e.getFirstName() + " " + e.getLastName() + " is earning "+ (e.getSalary()-avgSalary) + " more than expected"));

    }

    private static void getMinSalaryManager(EmployeeService empService, List<Employee> mgrList, double avgSalary) {
        System.out.println("Low earning managers are:");
        empService.getLessEarningManagers(mgrList,avgSalary)
                .forEach(e->System.out.println(e.getFirstName() + " " + e.getLastName() + " is earning "+ (avgSalary - e.getSalary()) + " less than expected"));
    }

    private static void getMaxEmployeeReportingLineLength(EmployeeService empService, List<Employee> empList) {
        Map<Integer, Employee> empMap= empService.populateEmpMap(empList);
        int max=0;
        Employee maxReportingLineEmployee=null;
        for(Employee e:empList) {
            int length= empService.getReportingLineLength(e, empMap);
            if(length>max) {
                max=length;
                maxReportingLineEmployee=e;
            }
        }
        System.out.println("Employee " + maxReportingLineEmployee.getFirstName() + " " + maxReportingLineEmployee.getLastName() + " has largest reporting line. The length is " + max);
    }
}