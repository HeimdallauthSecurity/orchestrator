package com.heimdallauth.utils.heimdallorchestrator.dao;

import com.heimdallauth.utils.heimdallorchestrator.constants.CloudflareConstants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceRecordDeployment {
    @Id
    private String recordId;
    private String organizationId;
    private String cloudflareRecordComment;
    private CloudflareConstants recordType;
    private String fullyQualifiedDomainName;
}
