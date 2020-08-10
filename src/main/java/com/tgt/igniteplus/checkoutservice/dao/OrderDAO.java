package com.tgt.igniteplus.checkoutservice.dao;

import com.tgt.igniteplus.checkoutservice.model.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderDAO extends CassandraRepository<Order, String>{
}
