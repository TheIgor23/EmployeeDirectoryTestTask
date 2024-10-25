package ru.jezemoin.emploedirectory.commands;

import ru.jezemoin.emploedirectory.model.dao.EmployeeDAO;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProcessor() {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        commands.put("1", new CreateTableCommand());
        commands.put("2", new AddEmployeeCommand(employeeDAO));
        commands.put("3", new ShowAllEmployeesCommand(employeeDAO));
        commands.put("4", new GenerateEmployeesCommand(employeeDAO));
        commands.put("5", new CalculateFilterQueryEmployeesCommand(employeeDAO));
    }

    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("No args provided");
            return;
        }

        Command command = commands.get(args[0]);
        if (command == null) {
            System.out.println("Unknown command");
            return;
        }

        command.execute(args);
    }
}
