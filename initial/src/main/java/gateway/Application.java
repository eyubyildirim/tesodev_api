package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(p -> p
                        .path("/order-query/get-all-orders")
                        .uri("http://127.0.0.1:9090"))
                .route(p -> p
                        .path("/order-query/get-order")
                        .uri("http://127.0.0.1:9090"))
                .route(p -> p
                        .path("/order-query/get-orders-by-customer-id")
                        .uri("http://127.0.0.1:9090"))
                .route(p -> p
                        .path("/order-command/create-order")
                        .uri("http://127.0.0.1:9090"))
                .route(p -> p
                        .path("/order-command/update-order")
                        .uri("http://127.0.0.1:9090"))
                .route(p -> p
                        .path("/customers/get-all-customers")
                        .uri("http://127.0.0.1:5001"))
                .route(p -> p
                        .path("/customers/create-customer")
                        .uri("http://127.0.0.1:5001"))
                .route(p -> p
                        .path("/customers/get-customer")
                        .uri("http://127.0.0.1:5001"))
                .route(p -> p
                        .path("/customers/delete-customer")
                        .uri("http://127.0.0.1:5001"))
                .route(p -> p
                        .path("/customers/update-customer")
                        .uri("http://127.0.0.1:5001"))
                .build();
    }
}
