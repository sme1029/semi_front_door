package com.greedy.coffee.mypage.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.mypage.entity.Order;
import com.greedy.coffee.product.entity.Product;
import com.greedy.coffee.review.entity.RevBoard;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Page<Order> findByMember(Pageable pageable, Member member);
	
	int countByOrderCode(int orderCode);
	
	Optional<Order> findByOrderCode(Long orderCode);
	

}
