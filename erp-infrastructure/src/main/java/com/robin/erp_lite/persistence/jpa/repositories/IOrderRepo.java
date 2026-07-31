package com.robin.erp_lite.persistence.jpa.repositories;

import com.robin.erp_lite.persistence.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderRepo extends JpaRepository<OrderEntity, UUID> {
}
