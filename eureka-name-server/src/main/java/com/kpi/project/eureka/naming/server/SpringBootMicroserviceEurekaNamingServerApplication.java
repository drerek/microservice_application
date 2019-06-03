package com.kpi.project.eureka.naming.server;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
public class SpringBootMicroserviceEurekaNamingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroserviceEurekaNamingServerApplication.class, args);
    }

}


@RestController
class ServiceInstanceRestontorller {

    private final PeerAwareInstanceRegistry registry;

    private final DiscoveryClient discoveryClient;

    public ServiceInstanceRestontorller(DiscoveryClient discoveryClient, PeerAwareInstanceRegistry registry) {
        this.discoveryClient = discoveryClient;
        this.registry = registry;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByAppName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @GetMapping("/all")
    public List<String> getAll(){
        System.out.println(this.discoveryClient.description());
        System.out.println(this.discoveryClient.getServices());
        return this.discoveryClient.getServices();
    }


    @GetMapping("/all2")
    public void getAll2(){
        Applications applications = registry.getApplications();

        System.out.println(applications.getAppsHashCode());
    }
}

