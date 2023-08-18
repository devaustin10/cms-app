package learn.cms.domain;


import learn.cms.data.ContactRepository;
import learn.cms.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    ContactService service;

    @MockBean
    ContactRepository repository;

    @Test
    void addContactWillReturnSuccessWhenValid() {
        Contact newContact = new Contact();
        newContact.setFirstName("John");
        newContact.setLastName("Smith");
        newContact.setEmail("johnSmith@test.com");
        newContact.setPhoneNumber("1234567891");

        when(repository.add(newContact)).thenReturn(newContact);
        Result<Contact> result = service.add(newContact);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(newContact, result.getPayload());

    }

}
