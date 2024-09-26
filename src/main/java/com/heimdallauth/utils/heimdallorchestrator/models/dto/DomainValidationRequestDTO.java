package com.heimdallauth.utils.heimdallorchestrator.models.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomainValidationRequestDTO {
    private String domainPrefixRequested;
    private String organizationId;
}
