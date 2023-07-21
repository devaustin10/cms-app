package learn.cms.controllers;

import learn.cms.domain.ContactService;
import learn.cms.domain.Result;
import learn.cms.models.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Contact contact) {
        // @RequestBody annotation automatically deserializes the JSON request body into the defined Java Object
        // Contact is a Data Transfer Object (DTO)
        Result<Contact> result = service.add(contact);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

}
