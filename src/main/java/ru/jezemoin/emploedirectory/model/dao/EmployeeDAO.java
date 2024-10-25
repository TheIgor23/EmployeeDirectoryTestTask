package ru.jezemoin.emploedirectory.model.dao;

import lombok.extern.log4j.Log4j2;
import ru.jezemoin.emploedirectory.model.entity.Employee;
import ru.jezemoin.emploedirectory.model.entity.SexEnum;
import ru.jezemoin.emploedirectory.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class EmployeeDAO {


    private static final String addEmployeeQuery = "INSERT INTO Employees (fullname, birthdate, sex) VALUES(?,?,?)";
    private static final String selectSortedEmployeeQuery = "SELECT DISTINCT ON (fullName, birthDate) fullname, birthdate, sex FROM Employees ORDER BY Employees.fullname";
    private static final String selectFilteredEmployeeQuery = "SELECT * FROM Employees " +
            "WHERE Employees.fullname LIKE 'F%'" +
            "AND Employees.sex = 'Male';";

    public void createMany(List<Employee> employees) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addEmployeeQuery)) {

            connection.setAutoCommit(false);

            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                preparedStatement.setString(1, emp.getFullName());
                preparedStatement.setDate(2, java.sql.Date.valueOf(emp.getBirthday()));
                preparedStatement.setString(3, emp.getSex().toString());
                preparedStatement.addBatch();

                if (i % 50000 == 0 || i == employees.size()-1) {
                    preparedStatement.executeBatch();
                }
            }

            connection.commit();

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }


    public void create(Employee employee)  {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addEmployeeQuery)) {

            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setDate(2, Date.valueOf(employee.getBirthday()));
            preparedStatement.setString(3, employee.getSex().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public void findFiltered() {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectFilteredEmployeeQuery)) {
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    public List<Employee> findAllEmployeesSortByFullName() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSortedEmployeeQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employees.add(
                        Employee.builder()
                                .fullName(resultSet.getString("fullname"))
                                .birthday(resultSet.getDate("birthdate").toLocalDate())
                                .sex(SexEnum.valueOf(resultSet.getString("sex")))
                                .build()
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return employees;
    }
}
