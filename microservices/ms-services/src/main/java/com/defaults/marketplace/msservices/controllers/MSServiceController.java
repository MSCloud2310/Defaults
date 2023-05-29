package com.defaults.marketplace.msservices.controllers;

import com.defaults.marketplace.msservices.exceptions.AlreadyExistException;
import com.defaults.marketplace.msservices.exceptions.NotFoundException;
import com.defaults.marketplace.msservices.models.entities.Provider;
import com.defaults.marketplace.msservices.models.entities.Question;
import com.defaults.marketplace.msservices.models.entities.ServiceC;
import com.defaults.marketplace.msservices.models.entities.ServiceRating;
import com.defaults.marketplace.msservices.services.ProviderService;
import com.defaults.marketplace.msservices.services.QuestionService;
import com.defaults.marketplace.msservices.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/providers")
public class MSServiceController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private QuestionService questionService;

    /*
     * Métodos relacionados al CRUD de providers
     */
    @PostMapping
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider provider) {
        if (providerService.providerAlreadyExist(provider.getPublicName())) {
            throw new AlreadyExistException("Provider already exist!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.saveProvider(provider));
    }

    @GetMapping
    public ResponseEntity<List<Provider>> getProviders() {
        if (providerService.getProviders().isEmpty()) {
            throw new NotFoundException("No providers created.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(providerService.getProviders());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Integer id) {
        if (providerService.getProviderById(id) == null) {
            throw new NotFoundException("Provider with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(providerService.getProviderById(id));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Integer id, @RequestBody Provider provider) {
        if (providerService.getProviderById(id) == null) {
            throw new NotFoundException("Provider with id " + id + " doesn't exist.");
        }
        provider.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(providerService.updateProvider(provider));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable Integer id) {
        if (providerService.getProviderById(id) == null) {
            throw new NotFoundException("Provider with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(providerService.deleteProvider(id));
    }

    /*
     * Métodos relacionados al CRUD de services
     */
    @PostMapping(value = "{providerId}/services")
    public ResponseEntity<ServiceC> saveService(@PathVariable Integer providerId, @RequestBody ServiceC serviceC) {
        serviceC.setProviderId(providerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.saveService(serviceC));
    }

    @GetMapping(value = "services")
    public ResponseEntity<List<ServiceC>> getServices() {
        if (serviceService.getServices().isEmpty()) {
            throw new NotFoundException("No services created.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(serviceService.getServices());
    }

    @GetMapping(value = "services/{id}")
    public ResponseEntity<ServiceC> getServiceById(@PathVariable Integer id) {
        if (serviceService.getServiceById(id) == null) {
            throw new NotFoundException("Service with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(serviceService.getServiceById(id));
    }

    @PutMapping(value = "services/{id}")
    public ResponseEntity<ServiceC> updateService(@PathVariable Integer id, @RequestBody ServiceC serviceC) {
        if (serviceService.getServiceById(id) == null) {
            throw new NotFoundException("Service with id " + id + " doesn't exist.");
        }
        serviceC.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.updateService(serviceC));
    }

    @DeleteMapping(value = "services/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        if (serviceService.getServiceById(id) == null) {
            throw new NotFoundException("Service with id " + id + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.deleteById(id));
    }

    /*
     * Métodos relacionados al CRUD de questions
     */
    @PostMapping(value = "services/{serviceId}/questions")
    public ResponseEntity<Question> saveQuestion(@PathVariable Integer serviceId, @RequestBody Question question) {
        if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        question.setServiceId(serviceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(question));
    }

    @GetMapping(value = "services/{serviceId}/questions")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Integer serviceId) {
        if (questionService.getQuestionsByServiceId(serviceId).isEmpty()) {
            throw new NotFoundException("No questions for service with id " + serviceId);
        } else if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getQuestionsByServiceId(serviceId));
    }

    @GetMapping(value = "services/{serviceId}/questions", params = "solved")
    public ResponseEntity<List<Question>> getQuestionsBySolved(@PathVariable Integer serviceId,
            @RequestParam Boolean solved) {
        if (questionService.getQuestionsBySolved(solved).isEmpty()) {
            throw new NotFoundException("No questions with solved value " + solved.toString());
        } else if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getQuestionsBySolved(solved));
    }

    @GetMapping(value = "services/{serviceId}/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer serviceId, @PathVariable Integer id) {
        if (questionService.getQuestionById(id, serviceId) == null) {
            throw new NotFoundException("Question with id " + id + " doesn't exist.");
        } else if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getQuestionById(id, serviceId));
    }

    @PutMapping(value = "services/{serviceId}/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer serviceId, @PathVariable Integer id,
            @RequestBody Question question) {
        if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        } else if (questionService.getQuestionById(id, serviceId) == null) {
            throw new NotFoundException("Question with id " + id + " doesn't exist.");
        }
        List<Question> questions = questionService.getQuestionsByServiceId(serviceId);
        Question existingQuestion = null;
        for (Question q : questions) {
            if (q.getId().equals(id)) {
                existingQuestion = q;
            }
        }
        if (existingQuestion == null) {
            throw new NotFoundException(
                    "Question with id " + id + " doesn't have asociate the service with id " + serviceId);
        }
        question.setServiceId(serviceId);
        question.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestion(question, existingQuestion));
    }

    @DeleteMapping(value = "services/{serviceId}/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer serviceId, @PathVariable Integer id) {
        if (questionService.getQuestionById(id, serviceId) == null) {
            throw new NotFoundException("Question with id " + id + " doesn't exist.");
        } else if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        } else if (!questionService.deleteQuestion(id, serviceId)) {
            throw new NotFoundException(
                    "Question with id " + id + " doesn't have asociate the service with id " + serviceId);
        }
        questionService.deleteQuestion(id, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body("Question deleted");
    }

    // Ratings methods

    @GetMapping(value = "services/{serviceId}/ratings")
    public ResponseEntity<List<ServiceRating>> getRatings(@PathVariable int serviceId) {
        if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }      
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getRatings(serviceId));
    }

    @PostMapping(value = "services/{serviceId}/ratings")
    public ResponseEntity<ServiceC> addRating(@PathVariable int serviceId, @RequestBody ServiceRating rating) {
        if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.addRating(serviceId, rating));
    }

    @DeleteMapping(value = "services/{serviceId}/ratings/{userId}") 
    public ResponseEntity<Boolean> deleteRating(@PathVariable int serviceId, @PathVariable int userId) {
        if (serviceService.getServiceById(serviceId) == null) {
            throw new NotFoundException("Service with id " + serviceId + " doesn't exist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.deleteRating(serviceId, userId));
    }

}
