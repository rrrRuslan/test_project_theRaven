package com.testproject;

import com.testproject.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
create table customers
(
	id BIGINT auto_increment,
	created BIGINT null,
	updated BIGINT null,
	full_name VARCHAR(50) null,
	email VARCHAR(100) null,
	phone VARCHAR(16) null,
	constraint customers_pk
		primary key (id)
);

 */


public class CustomerDao {

    private static final String URL_CONN = "jdbc:mysql://localhost:3306/test_proj?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "781227";

    private static final String SQL_INSERT_INTO_DB = "insert into customers (created,updated,full_name, email, phone)" + " values (?, ?, ?, ?, ?)";
    private static final String SQL_READ_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String SQL_READ_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id=?";
    private static final String SQL_UPDATE_CUSTOMER = "UPDATE customers SET full_name=?, phone=? , updated=? WHERE id = ?";
    private static final String SQL_DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?;";

    Connection connection = dbConn();


    public static Connection dbConn() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL_CONN, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connection to Store DB successful!");

        return conn;
    }


    public void sqlAddCustomer(Customer customer) {

        PreparedStatement preparedStmt = null;
        try {

            preparedStmt = connection.prepareStatement(SQL_INSERT_INTO_DB);

            if (!Validator.customerValidation(customer)) {
                System.out.println("Entered invalid data");
                return;
            }
//insert into customers (created,updated,full_name, email, phone)" + " values (?, ?, ?, ?, ?)

            preparedStmt.setLong(1, new Date().getTime());
            preparedStmt.setLong(2, new Date().getTime());
            preparedStmt.setString(3, customer.getFull_name());
            preparedStmt.setString(4, customer.getEmail());
            preparedStmt.setString(5, customer.getPhone());
            preparedStmt.execute();
            preparedStmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void sqlUpdateCustomer(Long id, String name, String phone) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(SQL_UPDATE_CUSTOMER);

            if (Validator.nameValidation(name)) {
                preparedStmt.setString(1, name);
            }
            if (Validator.phoneValidation(phone)) {
                preparedStmt.setString(2, phone);
            }

            preparedStmt.setLong(3, new Date().getTime() );
            preparedStmt.setLong(4, id);
            preparedStmt.execute();
            preparedStmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public List sqlGetAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_READ_ALL_CUSTOMERS);

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customers.add(new Customer(id, name, email, phone));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customers;
    }


    public Customer sqlFindCustomerById(Long id) {

        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        Customer searched = null;

        try {

            preparedStmt = connection.prepareStatement(SQL_READ_CUSTOMER_BY_ID);
            preparedStmt.setLong(1, id);
            rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Long resId = rs.getLong("id");
                String name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                searched = new Customer(resId, name, email, phone);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return searched;

    }

    public void sqlDeleteCustomer(Long id) {

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(SQL_DELETE_CUSTOMER);
            preparedStmt.setLong(1, id);
            preparedStmt.execute();
            preparedStmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
