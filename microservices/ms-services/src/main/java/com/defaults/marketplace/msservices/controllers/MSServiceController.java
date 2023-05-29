package com.defaults.marketplace.msservices.controllers;

import com.defaults.marketplace.msservices.exceptions.AlreadyExistException;
import com.defaults.marketplace.msservices.exceptions.NotFoundException;
import com.defaults.marketplace.msservices.models.entities.Country;
import com.defaults.marketplace.msservices.models.entities.Currency;
import com.defaults.marketplace.msservices.models.entities.Location;
import com.defaults.marketplace.msservices.models.entities.Provider;
import com.defaults.marketplace.msservices.models.entities.Question;
import com.defaults.marketplace.msservices.models.entities.ServiceC;
import com.defaults.marketplace.msservices.models.entities.ServiceRating;
import com.defaults.marketplace.msservices.models.enumerations.ServiceCategory;
import com.defaults.marketplace.msservices.services.ProviderService;
import com.defaults.marketplace.msservices.services.QuestionService;
import com.defaults.marketplace.msservices.services.ServiceService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(value = "/providers")
public class MSServiceController {
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private RestTemplate restTemplate;

    private static String urlRestCountries = "https://restcountries.com/v3.1/";
    private static String urlCoordinates = "https://maps.googleapis.com/maps/api/geocode/json?";

    //Api rest countries
    public Location getLocation(String name) {
        ResponseEntity<Country[]> response = restTemplate.getForEntity(urlRestCountries + "name/" + name + "?fields=capital,currencies,languages,region,population,flags", Country[].class);
        Country[] countries = response.getBody();
        
        if (countries != null && countries.length > 0) {
            Country country = countries[0];
            String capitals = "";
            String languages = "";
            String currencies = "";
            String f = "";
            for(String capital : country.getCapital()) {
                capitals += capital + ". ";
            }
            for(String language : country.getLanguages().values()) {
                languages += language + ". ";
            }
            for(Currency currency : country.getCurrencies().values()) {
                currencies += currency.getName() + ". ";
            }
            for(String flag : country.getFlags().values()) {
                f = flag;
                break;
            }
            Location location = new Location();
            location.setName(name);
            location.setCapital(capitals);
            location.setCurrencies(currencies);
            location.setLanguages(languages);
            location.setRegion(country.getRegion());
            location.setPopulation(String.valueOf(country.getPopulation()));
            location.setFlag(f);
            return location;
        } else {
            return null;
        }
    }

    //Api google maps get coordinates
    public String[] getCoordinates(String place, String apiKey) {        
        place.replace(" ", "+");
        place.replace("#", "%23");
        ResponseEntity<Object> response = restTemplate.getForEntity(urlCoordinates + "key=" + apiKey + "&address=" + place, Object.class);
        Object responseBody = response.getBody();
        
        Map<String, Object> responseMap = (Map<String, Object>) responseBody;
        ArrayList<Object> results = (ArrayList<Object>) responseMap.get("results");
        // System.out.println(results);
        Object result = results.get(0);
        // System.out.println(results.get(0));
        Map<String, Object> geometry = (Map<String, Object>) ((Map<String, Object>) result).get("geometry");
        LinkedHashMap<String, Object> location = (LinkedHashMap<String, Object>) geometry.get("location");

        String[] coordinates = new String[2];
        coordinates[0] = location.get("lat").toString();
        coordinates[1] = location.get("lng").toString();
        return coordinates;
    }

    //Api google maps show map
    public String getMap(String place){
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(".env.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = properties.getProperty("GOOGLE_API_KEY");
        
        String[] coordinates = getCoordinates(place, apiKey);

        String html = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <title>Map</title>\n" +
        "        <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\n" +
        "        <style type=\"text/css\">\n" +
        "            #map_canvas { height: 330px; width:550px; }\n" +
        "        </style>\n" +
        "        <script type=\"text/javascript\"\n" +
        "            src=\"http://maps.googleapis.com/maps/api/js?sensor=false&key=" + apiKey + "\">\n" +
        "        </script>\n" +
        "        <script type=\"text/javascript\">\n" +
        "            function initialize() {\n" +
        "                var latlng = new google.maps.LatLng(" + coordinates[0] + "," + coordinates[1] + ");\n" +
        "                var addressMarker = new google.maps.LatLng(" + coordinates[0] + "," + coordinates[1] + ");\n" +
        "                var myOptions = {\n" +
        "                    zoom: 15,\n" +
        "                    center: latlng,\n" +
        "                    mapTypeId: google.maps.MapTypeId.ROADMAP\n" +
        "                };\n" +
        "                var map = new google.maps.Map(document.getElementById(\"map_canvas\"), myOptions);\n" +
        "\n" +
        "                marker = new google.maps.Marker({ map:map, position: addressMarker });\n" +
        "            }\n" +
        "        </script>\n" +
        "    </head>\n" +
        "    <body onload=\"initialize()\">\n" +
        "        <div id=\"map_canvas\"></div>\n" +
        "        <div> [[json]] </div>\n" +
        "    </body>\n" +
        "</html>";

        return html;
    }

    //Api google maps show route
    public String getRoute(String origin, String destination){
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(".env.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = properties.getProperty("GOOGLE_API_KEY");
        
        String[] coordOrigin = getCoordinates(origin, apiKey);
        String[] coordDestin = getCoordinates(destination, apiKey);

        String html = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <title>Route</title>\n" +
        "        <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\n" +
        "        <style type=\"text/css\">\n" +
        "            #map_canvas { height: 330px; width:550px; }\n" +
        "        </style>\n" +
        "        <script type=\"text/javascript\"\n" +
        "            src=\"http://maps.googleapis.com/maps/api/js?key=" + apiKey + "&callback=initialize&libraries=&v=weekly\">\n" +
        "        </script>\n" +
        "        <script type=\"text/javascript\">\n" +
        "            function initialize() {\n" +
        "                const directionsRenderer = new google.maps.DirectionsRenderer();\n" +
        "                const directionsService = new google.maps.DirectionsService();\n" +
        "                const map = new google.maps.Map(document.getElementById(\"map_canvas\"), {\n" +
        "                    zoom: 15,\n" +
        "                    //center: { lat: 4.7314474, lng: -74.0694348 },\n" +
        "                });\n" +
        "                directionsRenderer.setMap(map);\n" +
        "                calculateAndDisplayRoute(directionsService, directionsRenderer);\n" +
        "            }\n" +
        "\n" +
        "            function calculateAndDisplayRoute(directionsService, directionsRenderer){\n" +
        "                directionsService.route(\n" +
        "                    {\n" +
        "                        origin: { lat: " + coordOrigin[0] + ", lng: " + coordOrigin[1] + " },\n" +
        "                        destination: { lat: " + coordDestin[0] + ", lng: " + coordDestin[1] + " },\n" +
        "                        travelMode: google.maps.TravelMode.DRIVING,\n" +
        "                    }).then((response) => {\n" +
        "                        directionsRenderer.setDirections(response);\n" +
        "                    }).catch((e) => window.alert(\"Directions request failed due to \" + e));\n" +
        "            }\n" +
        "        </script>\n" +
        "    </head>\n" +
        "    <body onload=\"initialize()\">\n" +
        "        <div id=\"map_canvas\"></div>\n" +
        "        <div> [[json]] </div>\n" +
        "    </body>\n" +
        "</html>";


        return html;
    }


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
    public ResponseEntity<?> saveService(@PathVariable Integer providerId, @RequestBody ServiceC serviceC, HttpServletRequest request) {
        String mediaType = request.getHeader(HttpHeaders.ACCEPT);
        if (mediaType != null && mediaType.contains(MediaType.TEXT_HTML_VALUE)) {
            if ((serviceC.getCategory() != ServiceCategory.LODGING) && (serviceC.getCategory() != ServiceCategory.TRANSPORTATION)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Accepted categories for html response: LODGING, TRANSPORTATION");
            }
        }
        serviceC.setProviderId(providerId);
        List<Location> locations = new ArrayList<Location>();
        // if (serviceC.getLocation() != null){
        //     String[] locationstr = serviceC.getLocation().split(", ");
        //     locations.add(getLocation(locationstr[0]));
        // }
        // if (serviceC.getDestination() != null){
        //     String[] locationstr = serviceC.getDestination().split(", ");
        //     locations.add(getLocation(locationstr[0]));
        // }
        // if (serviceC.getOrigin() != null){
        //     String[] locationstr = serviceC.getOrigin().split(", ");
        //     locations.add(getLocation(locationstr[0]));
        // }
        serviceC.setLocations(locations);
        ServiceC savedService = serviceService.saveService(serviceC);
    
        if (mediaType != null && mediaType.contains(MediaType.TEXT_HTML_VALUE)) {
            if (savedService.getCategory() == ServiceCategory.LODGING){
                String html = getMap(serviceC.getLocation());
                html = html.replace("[[json]]", savedService.toString());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_HTML);
                return new ResponseEntity<>(html, headers, HttpStatus.CREATED);
            } else if (savedService.getCategory() == ServiceCategory.TRANSPORTATION){
                String html = getRoute(serviceC.getOrigin(), serviceC.getDestination());
                html = html.replace("[[json]]", savedService.toString());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_HTML);
                return new ResponseEntity<>(html, headers, HttpStatus.CREATED);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(savedService, headers, HttpStatus.CREATED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(savedService, headers, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "services")
    public ResponseEntity<List<ServiceC>> getServices() {
        if (serviceService.getServices().isEmpty()) {
            throw new NotFoundException("No services created.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(serviceService.getServices());
    }
    
    @GetMapping(value = "services/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Integer id, HttpServletRequest request){
        String mediaType = request.getHeader(HttpHeaders.ACCEPT);
        ServiceC serviceC = serviceService.getServiceById(id);
        if (serviceC == null){
            throw new NotFoundException("Service with id " + id + " doesn't exist.");
        } else {
            if (mediaType != null && mediaType.contains(MediaType.TEXT_HTML_VALUE)) {
                if ((serviceC.getCategory() != ServiceCategory.LODGING) && (serviceC.getCategory() != ServiceCategory.TRANSPORTATION)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Accepted categories for html response: LODGING, TRANSPORTATION");
                }
                if (serviceC.getCategory() == ServiceCategory.LODGING){
                    String html = getMap(serviceC.getLocation());
                    html = html.replace("[[json]]", serviceC.toString());
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.TEXT_HTML);
                    return new ResponseEntity<>(html, headers, HttpStatus.FOUND);
                } else if (serviceC.getCategory() == ServiceCategory.TRANSPORTATION){
                    String html = getRoute(serviceC.getOrigin(), serviceC.getDestination());
                    html = html.replace("[[json]]", serviceC.toString());
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.TEXT_HTML);
                    return new ResponseEntity<>(html, headers, HttpStatus.FOUND);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return new ResponseEntity<>(serviceC, headers, HttpStatus.FOUND);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return new ResponseEntity<>(serviceC, headers, HttpStatus.FOUND);
            }
        }
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
