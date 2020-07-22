package com.tgt.igniteplus.checkoutservice.dao;

import com.tgt.igniteplus.checkoutservice.model.Cart;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CartDAO extends CassandraRepository<Cart, String>{
}
