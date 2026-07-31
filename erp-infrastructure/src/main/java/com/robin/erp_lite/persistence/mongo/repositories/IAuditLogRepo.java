package com.robin.erp_lite.persistence.mongo.repositories;

import com.robin.erp_lite.persistence.mongo.documents.AuditLogDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAuditLogRepo extends MongoRepository<AuditLogDocument, ObjectId> {
}
