package ru.jezemoin.emploedirectory.commands;

import ru.jezemoin.emploedirectory.model.dao.EmployeeDAO;
import ru.jezemoin.emploedirectory.model.entity.Employee;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAllEmployeesCommand implements Command{
    private final EmployeeDAO employeeDAO;

    public ShowAllEmployeesCommand(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void execute(String[] args) {
        List<Employee> employees = employeeDAO.findAllEmployeesSortByFullName();
        String header = String.format("%-35s | %-10s | %-7s | %-3s", "Name", "Birthday", "Sex", "Age");
        String separator = String.join("", Collections.nCopies(64, "-"));

        String table = employees.stream()
                .map(employee -> String.format(
                        "%-35s | %-10s | %-7s | %-3d",
                        employee.getFullName(), employee.getBirthday(), employee.getSex(),
                        employee.calculateAge()
                ))
                .collect(Collectors.joining("\n"));

        System.out.printf("%s\n%s\n%s", header, separator, table);
    }
}
