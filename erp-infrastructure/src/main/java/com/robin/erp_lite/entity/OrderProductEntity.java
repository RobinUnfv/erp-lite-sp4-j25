package com.robin.erp_lite.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "order_products",
        schema = "public",
        indexes = {
                @Index(name = "idx_order_products_order_id",   columnList = "order_id"),
                @Index(name = "idx_order_products_product_id", columnList = "product_id")
        }
)
public class OrderProductEntity {
    // PK
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id", nullable = false, updatable = false,
            columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID id;

    // FK → orders  (ON DELETE CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_order_products_order",
                    foreignKeyDefinition = "FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE"
            )
    )
    private OrderEntity order;

    // FK → products  (ON DELETE RESTRICT)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_order_products_product",
                    foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT"
            )
    )
    private ProductEntity product;

    // Columnas
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "subtotal", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    // Helper de negocio
    public void recalculateSubtotal() {
        if (unitPrice != null && quantity != null) {
            this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
