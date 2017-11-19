package com.kiwi.insurance.web.domain.repository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kiwi.insurance.InsuranceApplication;
import com.kiwi.insurance.fbs.service.OrderService;
import com.kiwi.insurance.web.domain.model.OrderDto;

import io.katharsis.client.KatharsisClient;
import io.katharsis.queryspec.QuerySpec;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = WebEnvironment.DEFINED_PORT,
	classes = InsuranceApplication.class,
	properties = {
		"security.ignored=/**",
		"spring.datasource.initialize=false"	
	}
)
public class OrderDtoRepositoryTests {
	
	@Autowired
	private OrderDtoRepository orderDtoRepository;
	
	@MockBean
	private OrderService orderService;
	
	@Before
	public void init() {
		
		// Given
		List<OrderDto> orders = new ArrayList<OrderDto>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		OrderDto order1 = new OrderDto();
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
		try {
			order1.setBirthDate(formatter.parse("19/02/1981"));	
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		orders.add(order1);
		
		OrderDto order2 = new OrderDto();
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
		orders.add(order2);
		
		Mockito.when(orderService.getOrders()).thenReturn(orders);
	}
	
	
	@Test
	public void testFindAll() {
		QuerySpec querySpec = new QuerySpec(OrderDto.class);
		List<OrderDto> orders = orderDtoRepository.findAll(querySpec);
		Assert.assertEquals(2, orders.size());
	}
	
	@Test
	public void testFindAllClient() throws Exception {
		KatharsisClient client = new KatharsisClient("http://localhost:9090/kiwi/api/orders");
		orderDtoRepository = client.getRepositoryForInterface(OrderDtoRepository.class);
		QuerySpec querySpec = new QuerySpec(OrderDto.class);
		List<OrderDto> orders = orderDtoRepository.findAll(querySpec);
		Assert.assertNotEquals(2, orders.size());
	}

}
