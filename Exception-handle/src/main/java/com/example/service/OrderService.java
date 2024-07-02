package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.exception.NoOrderFoundException;
import com.example.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}
	
	public Order findOrderById(Long id) {
		Optional<Order> order = orderRepo.findById(id);
		if(order.isPresent()) {
			Order order2 = order.get();
			return order2;
		}else{
			throw new NoOrderFoundException("order","id",id);
		}
	}
	
	

	
}
