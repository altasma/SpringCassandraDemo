package com.companyx.sampleapp.demo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;

@Component
public class CassandraConnectionManager {
	
	@Value("cassandrausername")
	private String cassandraUserName;
	
	@Value("cassandraPassword")
	private String cassandraPassword;
	
	@Value("cassandraPort")
	private int cassandraPort;
	
	
	@Value("cassandraKeyspace")
	private String cassandraKS;
	
	@Value("cassandraConnectionTimeout")
	private String cassandraConnectionTimeout;
	
	@Value("cassandraHostlist")
	private String cassandraHostlist;
	
	private Cluster cluster  = null;
	private Session session = null;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConnectionManager.class);
	
	public CassandraConnectionManager() {
		
	}
	public void connect() {
		if(cluster == null) {
			cluster = connectToCassandra();
		}
	}
	private Cluster connectToCassandra() {
		int port = cassandraPort;
		String username = cassandraUserName;
		String password = cassandraPassword;
		String hostList = cassandraHostlist;
		
		String serverIP = "127.0.0.1";
		String keyspace = "system";

		Cluster cluster = Cluster.builder()
		.addContactPoints(serverIP)
		.build();

	/*	
		LOGGER.info("The value of the host is: "+ hostList);
		
		Collection<InetAddress> inetAddressList = new ArrayList<InetAddress>();
		for(String host : hostList.split(",")) {
			try {
				InetAddress addr = InetAddress.getByName(host);
				inetAddressList.add(addr);
				
			}
			catch(UnknownHostException e) {
				LOGGER.info("Unknown host: " + host, e );
			}
		}
		
		SocketOptions options = new SocketOptions();
		options.setConnectTimeoutMillis(30000);
		options.setReadTimeoutMillis(30000);
		options.setTcpNoDelay(true);
		
		PoolingOptions poolingOptions = new PoolingOptions();
		poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL, 32768);
		
		Cluster cluster = Cluster.builder().addContactPoints(inetAddressList)
				.withPort(port).withCredentials(username, password)
				.withSocketOptions(options)
				.withPoolingOptions(poolingOptions).build();*/
		return cluster;
	}
	
	
	public Session getSession() {
		String keyspace = cassandraKS;
		if(cluster == null) {
			connect();
		}
		if(session == null || session.isClosed()) {
			session = cluster.connect(keyspace);
		}
		
		return session;
	}
	

}
