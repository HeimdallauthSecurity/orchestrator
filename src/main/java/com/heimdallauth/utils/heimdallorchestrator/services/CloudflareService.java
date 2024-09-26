package com.heimdallauth.utils.heimdallorchestrator.services;

import com.heimdallauth.utils.heimdallorchestrator.clients.CloudflareClient;
import com.heimdallauth.utils.heimdallorchestrator.constants.CloudflareConstants;
import com.heimdallauth.utils.heimdallorchestrator.models.cf.DNSZoneCreateRequest;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CloudflareService {
    private final CloudflareClient cfClient;
    private final String zoneId;
    private final String authHeader;

    public CloudflareService(CloudflareClient cfClient, @Value("${cloudflare.zoneid}") String zoneId,@Value("${cloudflare.authtoken}") String authHeader) {
        this.cfClient = cfClient;
        this.zoneId = zoneId;
        this.authHeader = authHeader;
    }

    private void createCNAMERecord(String requestedPrefix, String resolveName){
        cfClient.createDNSRecord(zoneId, DNSZoneCreateRequest.builder()
                        .name(resolveName)
                        .type(CloudflareConstants.CNAME)
                        .ttl(1)
                        .proxied(false)
                        .comment("Created by Heimdall")
                        .content(String.format("%s.heimdallauth.com", requestedPrefix))
                .build(), authHeader);
    }
}
