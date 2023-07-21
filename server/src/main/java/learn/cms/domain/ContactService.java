package learn.cms.domain;

import learn.cms.data.ContactRepository;
import learn.cms.models.Contact;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> findAll() {
        return null;
    }

    public List<Contact> findById(int contactId) {
        return null;
    }

    public Result<Contact> add(Contact contact) {
        Result<Contact> result = validate(contact);
        if (!result.isSuccess()) {
            return result;
        } else if (contact.getContactID() != 0) {
            result.addMessage("contactId cannot be set for add", ResultType.INVALID);
            return result;
        } else {
            contact = this.repository.add(contact);
            result.setPayload(contact);
            return result;
        }
    }

    private Result<Contact> validate(Contact contact) {
        Result<Contact> result = new Result<>();
        if (contact == null) {
            result.addMessage("contact is invalid", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(contact.getFirstName())) {
            result.addMessage("first name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(contact.getLastName())) {
            result.addMessage("last name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(contact.getEmail())) {
            result.addMessage("email is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(String.valueOf(contact.getPhoneNumber()))) {
            result.addMessage("phone number is required", ResultType.INVALID);
        }
        return result;
    }
}
