package com.example.effigosp.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.effigosp.entity.EmployeeEntity;
import com.example.effigosp.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public EmployeeServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public EmployeeEntity insertEmployee(EmployeeEntity employee) {
		Connection connection = null;
		EmployeeEntity emp = new EmployeeEntity();
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			
			CallableStatement call = connection.prepareCall("call insert_employee(?, ?, ?)");
			
			call.setString(1,employee.getName());
			call.setLong(2, employee.getAge().longValue());
			call.setString(3, employee.getRole());
			call.execute();
			
			//setting up the object 
			emp.setName(employee.getName());
			emp.setAge(employee.getAge());
			emp.setRole(employee.getRole());
			connection.commit();
			log.info("call statement successfully executed");
		}
		catch(SQLException e) {
			 e.printStackTrace();
	            if (connection != null) { 
	                try {
	                    connection.rollback();
	                } catch (SQLException rollbackException) {
	                    rollbackException.printStackTrace();
	                }
	            }
		}finally {
			if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		return emp;
	}
}
