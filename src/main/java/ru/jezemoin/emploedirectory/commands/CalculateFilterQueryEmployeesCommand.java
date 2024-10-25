package ru.jezemoin.emploedirectory.commands;

import ru.jezemoin.emploedirectory.model.dao.EmployeeDAO;

public class CalculateFilterQueryEmployeesCommand implements Command{
    private final EmployeeDAO employeeDAO;

    public CalculateFilterQueryEmployeesCommand(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void execute(String[] args) {
        long startTime = System.nanoTime();
        employeeDAO.findFiltered();
        long endTime = System.nanoTime();

        System.out.println("Query executed in: " + (endTime - startTime) / 1000000 + " ms");
    }
}
