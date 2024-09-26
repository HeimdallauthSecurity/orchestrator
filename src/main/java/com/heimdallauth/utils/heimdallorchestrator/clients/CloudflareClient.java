package com.heimdallauth.utils.heimdallorchestrator.clients;

import com.heimdallauth.utils.heimdallorchestrator.models.cf.DNSZoneCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="cf", url="https://api.cloudflare.com/client/v4/zones")
public interface CloudflareClient {
    @PostMapping(value = "/{zoneId}/dns_records", headers = {})
    void createDNSRecord(@PathVariable("zoneId") String zoneId, @RequestBody DNSZoneCreateRequest dnsZoneCreateRequest, @RequestHeader("Authorization") String authToken);
}
