package com.defaults.marketplace.marketplaceserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.defaults.marketplace.marketplaceserver.models.orders.Cart;
import com.defaults.marketplace.marketplaceserver.models.orders.Item;
import com.defaults.marketplace.marketplaceserver.models.orders.Order;
import com.defaults.marketplace.marketplaceserver.models.orders.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/marketplace")
public class MarketplaceController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> mpGetOrders() {
        ResponseEntity<List<Order>> response = restTemplate.exchange("http://ms-orders/orders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>(){});
        return response;
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> mpGetOrderById(@PathVariable Integer id) {
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

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> mpUpdateOrderById(@PathVariable Integer id, @RequestBody Order order) {
        try {
            ResponseEntity<Order> response = restTemplate.exchange(
                    "http://ms-orders/orders/{orderId}",
                    HttpMethod.PUT,
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

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> mpDeleteOrderById(@PathVariable Integer id) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    "http://ms-orders/orders/{id}",
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

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> mpGetCarts() {
        ResponseEntity<List<Cart>> response = restTemplate.exchange("http://ms-orders/carts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cart>>(){});
        return response;
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> mpGetCartById(@PathVariable Integer id) {
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

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> mpUpdateCartById(@PathVariable Integer id, @RequestBody Cart cart) {
        try {
            HttpEntity<Cart> request = new HttpEntity<>(cart);
            ResponseEntity<Cart> response = restTemplate.exchange(
                    "http://ms-orders/carts/{cartId}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Cart>() {
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

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> mpDeleteCartById(@PathVariable Integer id) {
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
    public ResponseEntity<List<Cart>> mpGetCartItemsByUserId(@PathVariable Integer userId) {
        try {
            ResponseEntity<List<Cart>> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Cart>>() {
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
                    new ParameterizedTypeReference<Cart>() {
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
    public ResponseEntity<Item> mpUpdateItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable Integer position, @RequestBody Item item) {
        try {
            HttpEntity<Item> request = new HttpEntity<>(item);
            ResponseEntity<Item> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items/{position}",
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<Item>() {
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
    public ResponseEntity<Void> mpDeleteItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable Integer position) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    "http://ms-orders/carts/{userId}/items/{position}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
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
        HttpEntity<Payment> request = new HttpEntity<>(payment);
        ResponseEntity<Order> response = restTemplate.exchange(
                "http://ms-orders/payments?userId={userId}",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Order>(){},
                userId);
        return response;
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

    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<Void> mpDeletePayment(@PathVariable String paymentId) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    "http://ms-orders/payments/{paymentId}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
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
}