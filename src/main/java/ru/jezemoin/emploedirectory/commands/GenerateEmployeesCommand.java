package ru.jezemoin.emploedirectory.commands;

import ru.jezemoin.emploedirectory.model.dao.EmployeeDAO;
import ru.jezemoin.emploedirectory.model.entity.Employee;
import ru.jezemoin.emploedirectory.model.entity.SexEnum;
import ru.jezemoin.emploedirectory.util.RandomBirthdayGenerator;
import ru.jezemoin.emploedirectory.util.names.NameGenerator;

import java.util.*;

public class GenerateEmployeesCommand implements Command {
    private final EmployeeDAO employeeDAO;

    public GenerateEmployeesCommand(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void execute(String[] args) {

        List<Employee> employees = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            SexEnum sex = random.nextInt() % 2 == 0 ? SexEnum.Female : SexEnum.Male;
            String fullName = NameGenerator.builder()
                    .sex(sex)
                    .build()
                    .generateFullName();

            employees.add(Employee.builder()
                    .fullName(fullName)
                    .birthday(RandomBirthdayGenerator.generateRandomBirthDate())
                    .sex(sex)
                    .build());
        }

        employeeDAO.createMany(employees);

        System.out.println("Employees generated");

    }

}
