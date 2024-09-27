package com.heimdallauth.utils.heimdallorchestrator.dm;

import com.heimdallauth.utils.heimdallorchestrator.models.dto.DomainRecordDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceRecordDataManager {
    void createCustomDomainRecord(DomainRecordDTO domainRecord);
    Optional<DomainRecordDTO> fetchDomainRecordById(String recordId);
    Optional<DomainRecordDTO> fetchDomainRecordByOrganizationId(String organizationId);
    void deleteDomainRecordById(String recordId);
    void deleteDomainByRecordId(List<String> recordIds);
    List<DomainRecordDTO> fetchAllExpiredDomainRecords();

}
