package learn.cms.controllers;

import learn.cms.domain.ContactService;
import learn.cms.domain.Result;
import learn.cms.models.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contact> findAll() {
        return service.findAll();
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> findById(@PathVariable int contactId) {
        Contact result = service.findById(contactId);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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

    @PutMapping("/{contactId}")
    public ResponseEntity<Object> update(@PathVariable int contactId, @RequestBody Contact contact) {
        if (contactId != contact.getContactID()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Contact> result = service.update(contact);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

}
