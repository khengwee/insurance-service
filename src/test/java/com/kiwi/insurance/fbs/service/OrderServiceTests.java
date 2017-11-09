package com.kiwi.insurance.fbs.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kiwi.insurance.spl.domain.model.Order;
import com.kiwi.insurance.spl.domain.repository.OrderRepository;
import com.kiwi.insurance.web.domain.model.OrderDto;

@RunWith(SpringRunner.class)
@SpringBootTest(
	properties = "spring.datasource.initialize=false"
)
public class OrderServiceTests {
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
    private OrderRepository orderRepository;
	
	
	@Before
	public void init() {
		
		// Given
		List<Order> orders = new ArrayList<Order>();
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
		orders.add(order1);
		
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
		orders.add(order2);
		
		Mockito.when(orderRepository.findAll()).thenReturn(orders);
	}
	
	@Test
	public void testGetOrders() {

		Iterable<OrderDto> ordersResult = orderService.getOrders();
		Assert.assertNotNull("The orders is NULL!", ordersResult);
		Assert.assertEquals("FirstName is Kaz!", "Kaz", ordersResult.iterator().next().getFirstName());
	}

}
