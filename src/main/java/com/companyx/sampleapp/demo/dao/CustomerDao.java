package com.companyx.sampleapp.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.companyx.sampleapp.demo.model.Customer;
import com.companyx.sampleapp.demo.util.CassandraConnectionManager;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import ch.qos.logback.classic.Logger;

@Component
@Primary
public class CustomerDao {
	
	CassandraConnectionManager connectionMgr  = new CassandraConnectionManager();
	
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String STATUS = "status";
    private Session session = null;
	
	public Customer findByID(int id) {
		session = connectionMgr.getSession();
		Customer customer = null;
		
		String selectSingleQuery = "select * from dev.CUSTOMER where id = ? ";
		
		PreparedStatement prepared = session.prepare(selectSingleQuery);
		BoundStatement bound = prepared.bind(id);
		ResultSet resultSet = session.execute(bound);
		if(!resultSet.isExhausted()) {
			Row row = resultSet.one();
			customer = new Customer();
			customer.setId(row.getInt(ID));
			customer.setName(row.getString(NAME));
			customer.setStatus(row.getString(STATUS));
		}
		
		return customer;
		
	}
	

}
