package com.heimdallauth.utils.heimdallorchestrator.models.dto;

import lombok.*;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomainRecordDTO {
    private String recordId;
    private String organizationId;
    private String fullyQualifiedDomainName;
    private Instant domainValidityExpiry;
}
