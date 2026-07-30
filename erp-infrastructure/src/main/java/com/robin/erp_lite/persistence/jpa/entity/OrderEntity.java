package com.robin.erp_lite.persistence.jpa.entity;


import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "orderProducts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "orders",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_orders_order_number", columnNames = "order_number")
        }
)
public class OrderEntity {
        // PK
        @EqualsAndHashCode.Include
        @Id
        @GeneratedValue
        @UuidGenerator(style = UuidGenerator.Style.RANDOM)
        @Column(name = "id", nullable = false, updatable = false,
                columnDefinition = "uuid DEFAULT uuid_generate_v4()")
        private UUID id;

        // Columnas
        @Column(name = "order_number", nullable = false, length = 50, unique = true)
        private String orderNumber;

        @Column(name = "customer_id", nullable = false)
        private Long customerId;

        @Column(name = "customer_name", nullable = false, length = 200)
        private String customerName;

        @Column(name = "created_by", nullable = false, length = 100)
        private String createdBy;

        @Column(name = "order_date", nullable = false,
                columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime orderDate;

        @Builder.Default
        @Column(name = "status", nullable = false, length = 20)
        private String status = "PENDING";

        @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
        private BigDecimal totalAmount;

        @Builder.Default
        @Column(name = "currency", nullable = false, length = 3)
        private String currency = "USD";

        @Column(name = "created_at", nullable = false, updatable = false,
                columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime createdAt;

        @Column(name = "updated_at", nullable = false,
                columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime updatedAt;

        // Relaciones
        @Builder.Default
        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
                orphanRemoval = true, fetch = FetchType.LAZY)
        private List<OrderProductEntity> orderProducts = new ArrayList<>();

        // Hooks
        @PrePersist
        protected void onCreate() {
                LocalDateTime now = LocalDateTime.now();
                if (createdAt == null) createdAt = now;
                if (updatedAt == null) updatedAt = now;
                if (orderDate == null) orderDate = now;
        }

        @PreUpdate
        protected void onUpdate() {
                updatedAt = LocalDateTime.now();
        }

        // Helpers bidireccionales
        public void addOrderProduct(OrderProductEntity op) {
                orderProducts.add(op);
                op.setOrder(this);
        }

        public void removeOrderProduct(OrderProductEntity op) {
                orderProducts.remove(op);
                op.setOrder(null);
        }
}
