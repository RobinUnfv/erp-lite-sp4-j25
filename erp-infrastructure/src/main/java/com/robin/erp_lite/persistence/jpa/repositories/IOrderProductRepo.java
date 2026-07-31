package com.robin.erp_lite.persistence.jpa.repositories;

import com.robin.erp_lite.persistence.jpa.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderProductRepo extends JpaRepository<OrderProductEntity, UUID> {
}
