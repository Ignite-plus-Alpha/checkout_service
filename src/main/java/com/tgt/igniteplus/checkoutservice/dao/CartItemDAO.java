package com.tgt.igniteplus.checkoutservice.dao;

import com.tgt.igniteplus.checkoutservice.model.CartItem;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.List;

public interface CartItemDAO extends CassandraRepository<CartItem, String>{
    //fetch list of items belonging to an user by cartId
    List<CartItem> findByCartId(String cartId);
}
