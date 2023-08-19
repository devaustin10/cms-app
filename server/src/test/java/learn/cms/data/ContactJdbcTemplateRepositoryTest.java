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
        assertEquals(actual.getLastName(), "TestLast");
    }

    @Test
    void shouldUpdateContactSuccessfully() {
        // assert
        Contact testContactInTestDB = repository.findById(1);
        // act
        testContactInTestDB.setFirstName("Robin");
        boolean updated = repository.update(testContactInTestDB);
        // assert
        assertTrue(updated);
        assertEquals(testContactInTestDB.getFirstName(), "Robin");
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
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
