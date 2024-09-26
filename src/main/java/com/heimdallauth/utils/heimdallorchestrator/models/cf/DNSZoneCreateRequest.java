package com.heimdallauth.utils.heimdallorchestrator.models.cf;

import com.heimdallauth.utils.heimdallorchestrator.constants.CloudflareConstants;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DNSZoneCreateRequest {
    private String comment;
    private String name;
    private boolean proxied;
    private int ttl;
    private String content;
    private CloudflareConstants type;
}
