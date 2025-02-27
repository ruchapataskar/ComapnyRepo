package org.rucha.EmployeeServices;

import org.rucha.companyEmployee.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {


    public Set<Integer> getAllManagersIds(List<Employee> empList) {
        return  empList.stream()
                .map(Employee::getManagerId)
                .collect(Collectors.toSet());
    }

    public List<Employee> getAllManagersExceptCEO(List<Employee> empList){
        Set<Integer> tempEmpSet= getAllManagersIds(empList);

        return empList.stream()
                .filter(emp->tempEmpSet.contains(emp.getEmpId()))
                .filter(emp->!isCEO(emp))
                .collect(Collectors.toList());
    }


    public Map<Integer,Employee> populateEmpMap(List<Employee> empList){
        Map<Integer,Employee> empMap=  new HashMap<>();
        for(Employee emp:empList){
            empMap.put(emp.getEmpId(),emp);
        }
        return empMap;
    }

    public OptionalDouble getAverageManagerSalary(List<Employee> empList){
        ArrayList<Employee> managers= (ArrayList<Employee>)getAllManagersExceptCEO(empList);
        return managers.stream().mapToDouble(Employee::getSalary).average();
    }

    public List<Employee> getLessEarningManagers(List<Employee> mgrList,double avgSalary){
        return mgrList.stream()
                .filter(emp->emp.getSalary()<avgSalary).collect(Collectors.toList());
    }

    public List<Employee> getHighEarningManagers(List<Employee> mgrList,double avgSalary){
        return mgrList.stream()
                .filter(emp->emp.getSalary()>avgSalary).collect(Collectors.toList());
    }

    public int getReportingLineLength(Employee e, Map<Integer,Employee> empMap) {

        if ( e.getManagerId()==0 || !empMap.containsKey(e.getManagerId())) {
            return 0;
        }
        return 1+ getReportingLineLength(empMap.get(e.getManagerId()), empMap);
    }

    public boolean isCEO(Employee emp){
        return emp.getManagerId()==0;
    }


}
