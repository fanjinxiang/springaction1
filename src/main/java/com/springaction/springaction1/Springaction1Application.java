package com.springaction.springaction1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		JdbcTemplateAutoConfiguration.class})
@ComponentScan
@Slf4j
public class Springaction1Application implements CommandLineRunner{


	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void test(String[] args) {
		SpringApplication.run(Springaction1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}

	private void showConnection() throws SQLException{
		log.info(dataSource.toString());
		Connection connection = dataSource.getConnection();
		log.info(connection.toString());
		connection.close();
	}

	private void showData(){
		jdbcTemplate.queryForList("select * from foo").forEach(row->log.info(row.toString()));
	}
}
