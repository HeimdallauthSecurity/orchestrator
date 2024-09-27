package com.heimdallauth.utils.heimdallorchestrator.dm;

import com.heimdallauth.utils.heimdallorchestrator.constants.CloudflareConstants;
import com.heimdallauth.utils.heimdallorchestrator.dao.ServiceRecordDeployment;
import com.heimdallauth.utils.heimdallorchestrator.models.dto.DomainRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MongoServiceRecordDataManager implements ServiceRecordDataManager{
    private final MongoTemplate mongoTemplate;
    private static final String DNS_RECORD_NAME_COLLECTION = "dns_record_name";

    @Autowired
    public MongoServiceRecordDataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createCustomDomainRecord(DomainRecordDTO domainRecord) {
        String recordId = UUID.randomUUID().toString();
        ServiceRecordDeployment recordDeployment = ServiceRecordDeployment.builder()
                .cloudflareRecordComment(recordId)
                .recordId(recordId)
                .recordType(CloudflareConstants.CNAME)
                .organizationId(domainRecord.getOrganizationId())
                .fullyQualifiedDomainName(domainRecord.getFullyQualifiedDomainName())
                .build();
        mongoTemplate.save(recordDeployment, DNS_RECORD_NAME_COLLECTION);
    }

    @Override
    public Optional<DomainRecordDTO> fetchDomainRecordById(String recordId) {
        Query selectionQuery = Query.query(Criteria.where("recordId").is(recordId));
        List<DomainRecordDTO> domainRecords = executeMongoQuery(selectionQuery);
        if(!domainRecords.isEmpty()) {
            return Optional.of(domainRecords.getFirst());
        }
        return Optional.empty();
    }

    @Override
    public Optional<DomainRecordDTO> fetchDomainRecordByOrganizationId(String organizationId) {
        Query selectionQuery = Query.query(Criteria.where("organizationId").is(organizationId));
        List<DomainRecordDTO> domainRecords = executeMongoQuery(selectionQuery);
        if(!domainRecords.isEmpty()) {
            return Optional.of(domainRecords.getFirst());
        }
        return Optional.empty();
    }

    @Override
    public void deleteDomainRecordById(String recordId) {
        Query deleteQuery = Query.query(Criteria.where("recordId").is(recordId));
        mongoTemplate.remove(deleteQuery, DNS_RECORD_NAME_COLLECTION);
    }

    @Override
    public void deleteDomainByRecordId(List<String> recordIds) {
        Query deleteQuery = Query.query(Criteria.where("recordId").in(recordIds));
        mongoTemplate.findAndRemove(deleteQuery, ServiceRecordDeployment.class, DNS_RECORD_NAME_COLLECTION);
    }

    @Override
    public List<DomainRecordDTO> fetchAllExpiredDomainRecords() {
        Instant currentTimestamp = Instant.now();
        Query selectionQuery = Query.query(Criteria.where("domainValidityExpiry").lt(currentTimestamp));
        return this.mongoTemplate.find(selectionQuery, DomainRecordDTO.class, DNS_RECORD_NAME_COLLECTION);
    }
    private List<DomainRecordDTO> executeMongoQuery(Query selectionQuery) {
        return mongoTemplate.find(selectionQuery, DomainRecordDTO.class, DNS_RECORD_NAME_COLLECTION);
    }
}
