package ru.jezemoin.emploedirectory.commands;

import lombok.extern.log4j.Log4j2;
import ru.jezemoin.emploedirectory.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class CreateTableCommand implements Command{
    @Override
    public void execute(String[] args) {
        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Employees (" +
                    "id SERIAL PRIMARY KEY, " +
                    "fullName VARCHAR(255), " +
                    "birthDate DATE, " +
                    "sex VARCHAR(6))";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
