package com.robin.erp_lite.persistence.jpa.repositories;

import com.robin.erp_lite.persistence.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface IProductRepo extends JpaRepository<ProductEntity, UUID> {
}
