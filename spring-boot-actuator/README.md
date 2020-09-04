
# To get all actuators management
- `localhost:8080/manage`

## exposure of endpoints
```
management.endpoints.jmx.exposure.include=*
management.endpoints.jmx.exposure.include=health,info
```

## Exposure of endpoints through HTTP
```
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.exclude=env,beans
```

# Securing HTTP endpoints
```
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
                requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
        http.httpBasic();
    }
}
```

## Allow endpoints without security
```
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
            requests.anyRequest().permitAll());
    }
}
```

## bean cache live time
`management.endpoint.beans.cache.time-to-live=10s`

## Core support
```
management.endpoints.web.cors.allowed-origins=https://example.com
management.endpoints.web.cors.allowed-methods=GET,POST
```

## Writing Custom Endpoint Actuator
```
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
}
```

## ordering endpoints
`management.endpoint.health.status.order=fatal,down,out-of-service,unknown,up`