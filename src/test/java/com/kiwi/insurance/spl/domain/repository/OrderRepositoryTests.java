package com.kiwi.insurance.spl.domain.repository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kiwi.insurance.spl.domain.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@TestPropertySource(properties = {
	"spring.datasource.initialize=false"
})
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/create-order-schema.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop-order-schema.sql")
})
public class OrderRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void testFindByCustomerName() {
		// Given
		Order order = new Order();
		order.setTitle("MR");
		order.setFirstName("Kaz");
		order.setLastName("Henry");
		order.setNricPassNo("A1234567890");
		order.setNricPassIssueCountry("Singapore");
		order.setAddress("12 Pasir Ris Grove");
		order.setPostalCode(518213);
		order.setOccupation("IT Developer");
		order.setIncome(BigDecimal.valueOf(5000.55));
		order.setContactNo("65123456789");
		order.setEmail("abc@gmail.com");
		order.setCreatedDate(new Date());
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			order.setBirthDate(formatter.parse("19/02/1981"));	
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		entityManager.persist(order);
		entityManager.flush();
		
		// When
		Order orderResult = orderRepository.findByNricPassNo("A1234567890");
		
		// Then
		Assert.assertNotNull("The order is NULL!", orderResult);
		Assert.assertEquals("The lastName is not Henry!", "Henry", orderResult.getLastName());
	}
	
	@Test
	public void testFindById() {
		// Given
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Order order1 = new Order();
		order1.setTitle("MR");
		order1.setFirstName("Kaz");
		order1.setLastName("Henry");
		order1.setNricPassNo("A1234567890");
		order1.setNricPassIssueCountry("Singapore");
		order1.setAddress("12 Pasir Ris Grove");
		order1.setPostalCode(518213);
		order1.setOccupation("IT Developer");
		order1.setIncome(BigDecimal.valueOf(5000.55));
		order1.setContactNo("65123456789");
		order1.setEmail("abc@gmail.com");
		order1.setCreatedDate(new Date());
		try {
			order1.setBirthDate(formatter.parse("19/02/1981"));	
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		entityManager.persist(order1);
		
		Order order2 = new Order();
		order2.setTitle("MS");
		order2.setFirstName("Mary");
		order2.setLastName("Elizabeth");
		order2.setNricPassNo("A1234567899");
		order2.setNricPassIssueCountry("Singapore");
		try {
			order2.setBirthDate(formatter.parse("20/03/1980"));
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		order2.setAddress("Tampines Street 12");
		order2.setPostalCode(518215);
		order2.setOccupation("Promoter");
		order2.setIncome(BigDecimal.valueOf(2000));
		order2.setContactNo("65123456799");
		order2.setEmail("xyz@gmail.com");
		order2.setCreatedDate(new Date());
		entityManager.persist(order2);
		entityManager.flush();
		
		// When
		Order orderResult = orderRepository.findOne("ORD00002");
		
		// Then
		Assert.assertNotNull("The order is NULL!", orderResult);
		Assert.assertEquals("The lastName is not Elizabeth!", "Elizabeth", orderResult.getLastName());
	}
	
}
