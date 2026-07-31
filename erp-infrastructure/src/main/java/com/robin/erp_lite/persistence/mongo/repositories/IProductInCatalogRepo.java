package com.robin.erp_lite.persistence.mongo.repositories;


import com.robin.erp_lite.persistence.mongo.documents.ProductInCatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductInCatalogRepo extends MongoRepository<ProductInCatalogDocument, String> {
}
