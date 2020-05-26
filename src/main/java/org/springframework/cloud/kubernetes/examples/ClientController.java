package org.springframework.cloud.kubernetes.examples;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    @Autowired
    private final RestTemplate restTemplate;

    private final String resourceUrl = "http://echo-boot:8080";

    @Autowired
    private DiscoveryClient discoveryClient;

    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String load() {

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        String serviceList = "";
        if (discoveryClient != null) {
            List<String> services = this.discoveryClient.getServices();

            for (String service : services) {

                List<ServiceInstance> instances = this.discoveryClient.getInstances(service);

                serviceList += ("[" + service + " : " + ((!CollectionUtils.isEmpty(instances)) ? instances.size() : 0)
                        + " instances ]");
                if (instances.size() > 0) {
                    String instanceList = "";
                    for (ServiceInstance instance : instances) {
                        instanceList += instance.getInstanceId() + ", ";

                    }
                    serviceList += "(" + instanceList + ")";
                }

            }
        }

        return String.format("Message from backend is: %s <br/> Services : %s", response.getBody(), serviceList);

    }
}
