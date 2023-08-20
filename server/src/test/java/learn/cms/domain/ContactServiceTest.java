package learn.cms.domain;


import learn.cms.data.ContactRepository;
import learn.cms.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    ContactService service;

    //  Instead of using the actual database repository implementation, you can use @MockBean
    //  to replace it with a mock implementation. This allows you to control the behavior of the
    //  repository methods in your tests, without needing to connect to a real database.
    @MockBean
    ContactRepository repository;

    @Test
    void addContactWillReturnSuccessWhenValid() {
        Contact invalidContact = new Contact();
        invalidContact.setFirstName("John");
        invalidContact.setLastName("Smith");
        invalidContact.setEmail("johnSmith@test.com");
        invalidContact.setPhoneNumber("1234567891");

        when(repository.add(invalidContact)).thenReturn(invalidContact);
        Result<Contact> result = service.add(invalidContact);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(invalidContact, result.getPayload());
    }

    @Test
    void addContactWillReturnInvalidWhenContactIdSet() {
        Contact invalidContact = new Contact();
        invalidContact.setContactId(123); // Setting a contact ID is not allowed for adding
        invalidContact.setFirstName("John");
        invalidContact.setLastName("Smith");
        invalidContact.setEmail("johnSmith@test.com");
        invalidContact.setPhoneNumber("1234567891");

        Result<Contact> result = service.add(invalidContact);

        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("contactId cannot be set for add", result.getMessages().get(0));
    }

    @Test
    void updateContactWillReturnSuccessWithValidInput() {
        Contact existingContact = new Contact();
        existingContact.setContactId(1); // Assuming an existing contact ID
        existingContact.setFirstName("UpdatedFirstName");
        existingContact.setLastName("UpdatedLastName");
        existingContact.setEmail("updated@test.com");
        existingContact.setPhoneNumber("9876543210");

        when(repository.update(existingContact)).thenReturn(true);

        Result<Contact> result = service.update(existingContact);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(existingContact, result.getPayload());
    }

    @Test
    void updateContactWillReturnNotFoundWhenContactNotFound() {
        Contact existingContact = new Contact();
        existingContact.setContactId(1);
        existingContact.setFirstName("UpdatedFirstName");
        existingContact.setLastName("UpdatedLastName");
        existingContact.setEmail("updated@test.com");
        existingContact.setPhoneNumber("9876543210");

        when(repository.update(existingContact)).thenReturn(false);

        Result<Contact> result = service.update(existingContact);

        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("contactId: %s, not found", existingContact.getContactId()), result.getMessages().get(0));
    }

    @Test
    void deleteContactByIdWillReturnTrueOnSuccessfulDelete() {
        int contactIdToDelete = 1;
        when(repository.deleteById(contactIdToDelete)).thenReturn(true);
        boolean result = service.deleteById(contactIdToDelete);
        assertTrue(result);
    }

    @Test
    void deleteContactByIdWillReturnFalseWhenDeleteFails() {
        int contactIdToDelete = 1;
        when(repository.deleteById(contactIdToDelete)).thenReturn(false);
        boolean result = service.deleteById(contactIdToDelete);
        assertFalse(result);
    }




}
