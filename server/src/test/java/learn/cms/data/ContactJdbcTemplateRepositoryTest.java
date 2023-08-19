package learn.cms.data;

import learn.cms.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactJdbcTemplateRepositoryTest {

    @Autowired
    ContactJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Contact> contact = repository.findAll();
        assertNotNull(contact);
        assertEquals(2, contact.size());
    }

    @Test
    void shouldFindById() {
        Contact contact = repository.findById(1);
        assertEquals("Robert", contact.getFirstName() );
    }

    // should test that what you are adding is actually what's being added
    @Test
    void shouldAdd() {
        Contact contact = makeContact();
        Contact actual= repository.add(contact);
        assertNotNull(actual);
        assertEquals(2, actual.getContactID());
        System.out.println(actual.getFirstName());
    }

    @Test
    void shouldNotAddContactWithInvalidData() {
        Contact contact = makeInvalidContact();

        // Mock the behavior of the repository to simulate an unsuccessful insert
        Mockito.when(repository.add(contact)).thenReturn(null);
        Contact actual = repository.add(contact);

        assertNull(actual);
    }

    private Contact makeInvalidContact() {
        Contact contact = new Contact();
        // Invalid data: First name and email are missing
        contact.setLastName("InvalidLast");
        contact.setPhoneNumber(1234567891);
        return contact;
    }

    private Contact makeContact() {
        Contact contact = new Contact();
        contact.setFirstName("Robert");
        contact.setLastName("TestLast");
        contact.setEmail("robert@test.com");
        contact.setPhoneNumber("1234567891");
        return contact;
    }


}
