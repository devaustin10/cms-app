package learn.cms.data;

import learn.cms.models.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactRepository {

    List<Contact> findAll();

    Contact findById(int contactId);

    Contact add(Contact contact);

    boolean update(Contact contact);

    @Transactional
    boolean deleteById(int contactId);

}
