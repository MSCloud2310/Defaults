package com.defaults.marketplace.apigateway;

import java.util.List;

import com.defaults.marketplace.apigateway.models.orders.*;
import com.defaults.marketplace.apigateway.models.services.*;
import com.defaults.marketplace.apigateway.models.users.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class APIGatewayController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*          Utilities         */

    public void setJsonHttpConverter (){
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
    }

    @GetMapping("/users/extractUserEmail")
    public ResponseEntity<String> extractUserEmail(@RequestHeader("Authorization") String authorizationHeader) {
        HttpEntity<Object> requestBody = requestEmptyBody(authorizationHeader);
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ResponseEntity<String> response = restTemplate.exchange("http://ms-users/users/extractUserId",
                HttpMethod.GET,
                requestBody,
                new ParameterizedTypeReference<String>(){});
        return response;
    }

    public boolean mpVerifyToken(RestTemplate restTemplate, HttpEntity<Object> requestBody){
        try {
            ResponseEntity<Boolean> response = restTemplate.exchange("http://ms-users/users/validateAuth",
                    HttpMethod.GET,
                    requestBody,
                    new ParameterizedTypeReference<Boolean>(){});
            return Boolean.TRUE.equals(response.getBody());
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    public HttpEntity<Object> requestEmptyBody(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        String token = authorizationHeader.replace("Bearer ", "");
        headers.setBearerAuth(token);
        HttpEntity<Object> requestBody = new HttpEntity<>(null, headers);
        setJsonHttpConverter();
        return requestBody;
    }

    /*          Microservice Users         */

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> mpAuthenticate(@RequestBody AuthenticationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> requestBody = new HttpEntity<>(request, headers);
        //System.out.println("request: " + request);
        setJsonHttpConverter();

        try{
            ResponseEntity<AuthenticationResponse> response = restTemplate.exchange("http://ms-users/auth/authenticate",
                    HttpMethod.POST,
                    requestBody,
                    AuthenticationResponse.class);
            return response;
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> mpGetUsers(@RequestHeader("Authorization") String authorizationHeader) {
        HttpEntity<Object> requestBody = requestEmptyBody(authorizationHeader);
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ResponseEntity<List<UserDTO>> response = restTemplate.exchange("http://ms-users/users",
                HttpMethod.GET,
                requestBody,
                new ParameterizedTypeReference<List<UserDTO>>(){});
        return response;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> mpGetUserById(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        HttpEntity<Object> requestBody = requestEmptyBody(authorizationHeader);
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    "http://ms-users/users/{id}",
                    HttpMethod.GET,
                    requestBody,
                    new ParameterizedTypeReference<UserDTO>() {},
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping(value = "/users", params = "role")
    public ResponseEntity<List<UserDTO>> mpGetUsersByRole(@RequestParam String role, @RequestHeader("Authorization") String authorizationHeader) {
        HttpEntity<Object> requestBody = requestEmptyBody(authorizationHeader);
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ResponseEntity<List<UserDTO>> response = restTemplate.exchange("http://ms-users/users?role={role}",
                HttpMethod.GET,
                requestBody,
                new ParameterizedTypeReference<List<UserDTO>>(){},
                role);
        return response;
    }

    @PostMapping("/users")
    public ResponseEntity<AuthenticationResponse> mpCreateUser(@RequestBody RegisterRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RegisterRequest> requestBody = new HttpEntity<>(request, headers);
        //System.out.println("request: " + request);
        setJsonHttpConverter();

        try {
            ResponseEntity<AuthenticationResponse> response = restTemplate.exchange("http://ms-users/users",
                    HttpMethod.POST,
                    requestBody,
                    AuthenticationResponse.class);
            return response;
        } catch (HttpClientErrorException e) {
            headers = new HttpHeaders();
            headers.add("message", e.getMessage());
            HttpStatus status = (HttpStatus) e.getStatusCode();
            return new ResponseEntity<>(headers, status);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> mpUpdateUser(@PathVariable Integer id, @RequestBody User user, @RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        String token = authorizationHeader.replace("Bearer ", "");
        headers.setBearerAuth(token);
        HttpEntity<Object> requestBody = new HttpEntity<>(user, headers);
        setJsonHttpConverter();
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            ResponseEntity<User> response = restTemplate.exchange(
                    "http://ms-users/users/{id}",
                    HttpMethod.PUT,
                    requestBody,
                    new ParameterizedTypeReference<User>() {},
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> mpDeleteUser(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        HttpEntity<Object> requestBody = requestEmptyBody(authorizationHeader);
        boolean verifyAuth = mpVerifyToken(restTemplate, requestBody);
        if(!verifyAuth){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://ms-users/users/{id}",
                    HttpMethod.DELETE,
                    requestBody,
                    new ParameterizedTypeReference<String>() {},
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    /*          Microservice Orders         */

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> mpGetCarts() {
        ResponseEntity<List<Cart>> response = restTemplate.exchange("http://ms-orders/carts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cart>>(){});
        return response;
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> mpGetCartByUserId(@PathVariable Integer id) {
        try {
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{cartId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Cart>(){},
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @PostMapping("/carts/{userId}")
    public ResponseEntity<Cart> mpCreateCart(@PathVariable Integer userId) {
        try {
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}",
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<Cart>() {},
                    userId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else if (ex.getStatusCode() == HttpStatus.CONFLICT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> mpUpdateCartByUserId(@PathVariable Integer id, @RequestBody Cart cart) {
        try {
            HttpEntity<Cart> request = new HttpEntity<>(cart);
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{cartId}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Cart>() {},
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> mpDeleteCartByUserId(@PathVariable Integer id) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    "http://ms-orders/carts/{id}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/carts/{userId}/items")
    public ResponseEntity<List<Item>> mpGetCartItemsByUserId(@PathVariable Integer userId) {
        try {
            ResponseEntity<List<Item>> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Item>>() {
                    },
                    userId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }
    
    @PostMapping("/carts/{userId}/items")
    public ResponseEntity<Cart> mpAddCartItemByUserId(@PathVariable Integer userId, @RequestBody Item item) {
        try {
            HttpEntity<Item> request = new HttpEntity<>(item);
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Cart>() {},
                    userId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/carts/{userId}/items")
    public ResponseEntity<Cart> mpEmptyCart(@PathVariable Integer userId) {
        try {
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items",
                    HttpMethod.DELETE,
                    null,
                    Cart.class,
                    userId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/carts/{userId}/items/{position}")
    public ResponseEntity<Item> mpGetItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable Integer position) {
        try{
            ResponseEntity<Item> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items/{position}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Item>(){},
                    userId,
                    position);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/carts/{userId}/items/{position}")
    public ResponseEntity<Cart> mpUpdateItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable Integer position, @RequestBody Item item) {
        try {
            HttpEntity<Item> request = new HttpEntity<>(item);
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items/{position}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Cart>() {
                    },
                    userId,
                    position);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/carts/{userId}/items/{position}")
    public ResponseEntity<Cart> mpDeleteItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable Integer position) {
        try {
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items/{position}",
                    HttpMethod.DELETE,
                    null,
                    Cart.class,
                    userId,
                    position);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> mpGetPayments(@RequestParam(name = "userId", required = false) Integer userId) {
        ResponseEntity<List<Payment>> response = restTemplate.exchange(
                "http://ms-orders/payments?userId={userId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Payment>>(){},
                userId);
        return response;
        
    }

    @PostMapping("/payments")
    public ResponseEntity<Order> mpPayCart(@RequestParam("userId") Integer userId, @RequestBody Payment payment) {
        try{
            HttpEntity<Payment> request = new HttpEntity<>(payment);
            ResponseEntity<Order> response = restTemplate.exchange(
                    "http://ms-orders/payments?userId={userId}",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Order>(){},
                    userId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> mpGetPaymentById(@PathVariable String paymentId) {
        try {
            ResponseEntity<Payment> response = restTemplate.exchange(
                    "http://ms-orders/payments/{paymentId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Payment>() {
                    },
                    paymentId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> mpUpdatePayment(@PathVariable String paymentId, @RequestBody Payment payment) {
        try {
            HttpEntity<Payment> request = new HttpEntity<>(payment);
            ResponseEntity<Payment> response = restTemplate.exchange(
                    "http://ms-orders/payments/{paymentId}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Payment>() {
                    },
                    paymentId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    // @DeleteMapping("/payments/{paymentId}")
    // public ResponseEntity<Void> mpDeletePayment(@PathVariable String paymentId) {
    //     try {
    //         ResponseEntity<Void> response = restTemplate.exchange(
    //                 "http://ms-orders/payments/{paymentId}",
    //                 HttpMethod.DELETE,
    //                 null,
    //                 Void.class,
    //                 paymentId);
    //         return response;
    //     } catch (HttpClientErrorException ex) {
    //         if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
    //             HttpHeaders headers = new HttpHeaders();
    //             headers.add("message", ex.getMessage());
    //             return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    //         } else {
    //             throw ex;
    //         }
    //     }
    // }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> mpGetOrders(@RequestParam(name = "userId", required = false) Integer userId) {
        if (userId != null) {
            ResponseEntity<List<Order>> response = restTemplate.exchange(
                    "http://ms-orders/orders?userId={userId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Order>>() {
                    },
                    userId);
            return response;
        } else {
            ResponseEntity<List<Order>> response = restTemplate.exchange("http://ms-orders/orders",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Order>>(){});
            return response;
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> mpGetOrderById(@PathVariable String id) {
        try {
            ResponseEntity<Order> response = restTemplate.exchange(
                    "http://ms-orders/orders/{orderId}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Order>() {
                    },
                    id);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> mpUpdateOrderById(@PathVariable String orderId, @RequestBody Order order) {
        try {
            HttpEntity<Order> request = new HttpEntity<>(order);
            ResponseEntity<Order> response = restTemplate.exchange(
                    "http://ms-orders/orders/{orderId}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Order>() {
                    },
                    orderId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> mpDeleteOrderById(@PathVariable String orderId) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    "http://ms-orders/orders/{orderId}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
                    orderId);
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    /*          Microservice Services         */

    // Providers
    @PostMapping("/providers")
    public ResponseEntity<Provider> mpSaveProvider(@RequestBody Provider provider){
        try {
            HttpEntity<Provider> request = new HttpEntity<>(provider);
            ResponseEntity<Provider> response = restTemplate.exchange(
                    "http://ms-services/providers",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Provider>(){}
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers")
    public ResponseEntity<List<Provider>> mpGetProviders(){
        try {
            ResponseEntity<List<Provider>> response = restTemplate.exchange(
                    "http://ms-services/providers",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Provider>>(){}
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> mpGetProviderById(@PathVariable Integer id) {
        try {
            ResponseEntity<Provider> response = restTemplate.exchange(
                    "http://ms-services/providers/{id}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Provider>() {},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/providers/{id}")
    public ResponseEntity<Provider> mpUpdateProvider(@PathVariable Integer id, @RequestBody Provider provider){
        try {
            HttpEntity<Provider> request = new HttpEntity<>(provider);
            ResponseEntity<Provider> response = restTemplate.exchange(
                    "http://ms-services/providers/{id}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Provider>(){},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/providers/{id}")
    public ResponseEntity<String> mpDeleteProvider(@PathVariable Integer id) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://ms-services/providers/{id}",
                    HttpMethod.DELETE,
                    null,
                    new ParameterizedTypeReference<String>() {},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    // Services

    @PostMapping("/providers/{providerId}/services")
    public ResponseEntity<ServiceC> mpSaveService(@PathVariable Integer providerId, @RequestBody ServiceC serviceC){
        try {
            HttpEntity<ServiceC> request = new HttpEntity<>(serviceC);
            ResponseEntity<ServiceC> response = restTemplate.exchange(
                    "http://ms-services/providers/{providerId}/services",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<ServiceC>(){},
                    providerId
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers/services")
    public ResponseEntity<List<ServiceC>> mpGetServices(){
        try {
            ResponseEntity<List<ServiceC>> response = restTemplate.exchange(
                    "http://ms-services/providers/services",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ServiceC>>(){}
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers/services/{id}")
    public ResponseEntity<ServiceC> mpGetServiceById(@PathVariable Integer id){
        try {
            ResponseEntity<ServiceC> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{id}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ServiceC>(){},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/providers/services/{id}")
    public ResponseEntity<ServiceC> mpUpdateService(@PathVariable Integer id, @RequestBody ServiceC serviceC){
        try {
            HttpEntity<ServiceC> request = new HttpEntity<>(serviceC);
            ResponseEntity<ServiceC> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{id}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<ServiceC>(){},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/providers/services/{id}")
    public ResponseEntity<String> mpDeleteService(@PathVariable Integer id){
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{id}",
                    HttpMethod.DELETE,
                    null,
                    new ParameterizedTypeReference<String>() {},
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

    // Questions

    @PostMapping("/providers/services/{serviceId}/questions")
    public ResponseEntity<Question> mpSaveQuestion(@PathVariable Integer serviceId, @RequestBody Question question){
        try {
            HttpEntity<Question> request = new HttpEntity<>(question);
            ResponseEntity<Question> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Question>(){},
                    serviceId
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers/services/{serviceId}/questions")
    public ResponseEntity<List<Question>> mpGetQuestions(@PathVariable Integer serviceId){
        try {
            ResponseEntity<List<Question>> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Question>>(){},
                    serviceId
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }
    
    @GetMapping(value = "/providers/services/{serviceId}/questions", params = "solved")
    public ResponseEntity<List<Question>> mpGetQuestionsBySolved(@PathVariable Integer serviceId, @RequestParam Boolean solved){
        try {
            ResponseEntity<List<Question>> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions?solved={solved}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Question>>(){},
                    serviceId,
                    solved
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @GetMapping("/providers/services/{serviceId}/questions/{id}")
    public ResponseEntity<Question> mpGetQuestionById(@PathVariable Integer serviceId, @PathVariable Integer id){
        try {
            ResponseEntity<Question> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions/{id}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Question>(){},
                    serviceId,
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @PutMapping("/providers/services/{serviceId}/questions/{id}")
    public ResponseEntity<Question> mpUpdateQuestion(@PathVariable Integer serviceId, @PathVariable Integer id, @RequestBody Question question){
        try {
            HttpEntity<Question> request = new HttpEntity<>(question);
            ResponseEntity<Question> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions/{id}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Question>(){},
                    serviceId,
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            } else {
                throw ex;
            }
        }
    }

    @DeleteMapping("/providers/services/{serviceId}/questions/{id}")
    public ResponseEntity<String> mpDeleteQuestion(@PathVariable Integer serviceId, @PathVariable Integer id){
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://ms-services/providers/services/{serviceId}/questions/{id}",
                    HttpMethod.DELETE,
                    null,
                    new ParameterizedTypeReference<String>() {},
                    serviceId,
                    id
            );
            return response;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", ex.getMessage());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            } else {
                throw ex;
            }
        }
    }

}