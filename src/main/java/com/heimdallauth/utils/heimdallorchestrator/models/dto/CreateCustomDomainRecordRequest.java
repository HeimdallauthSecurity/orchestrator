package com.heimdallauth.utils.heimdallorchestrator.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCustomDomainRecordRequest {
    private String domainPrefix;
    private String organizationId;
    private Instant domainValidityExpiry;
}
