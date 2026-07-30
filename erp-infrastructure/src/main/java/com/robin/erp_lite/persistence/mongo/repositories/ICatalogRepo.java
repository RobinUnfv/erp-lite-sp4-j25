package com.robin.erp_lite.persistence.mongo.repositories;

import com.robin.erp_lite.persistence.mongo.documents.CatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ICatalogRepo extends MongoRepository<CatalogDocument, String> {
}
