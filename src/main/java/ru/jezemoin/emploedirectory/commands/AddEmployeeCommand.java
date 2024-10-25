package ru.jezemoin.emploedirectory.commands;

import ru.jezemoin.emploedirectory.model.dao.EmployeeDAO;
import ru.jezemoin.emploedirectory.model.entity.Employee;
import ru.jezemoin.emploedirectory.model.entity.SexEnum;

import java.time.LocalDate;

public class AddEmployeeCommand implements Command{

    private final EmployeeDAO employeeDAO;

    public AddEmployeeCommand(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Not enough args");
            return;
        }

        Employee employee = Employee.builder()
                .fullName(args[1])
                .birthday(LocalDate.parse(args[2]))
                .sex(SexEnum.valueOf(args[3]))
                .build();

        employeeDAO.create(employee);

        System.out.println("Employee added");
    }
}
