package com.kiwi.insurance.spl.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.kiwi.insurance.spl.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

	Order findByNricPassNo(String nricPassNo);
}
