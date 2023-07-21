package learn.cms.data.mappers;

import learn.cms.controllers.ContactController;
import learn.cms.models.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {

    public ContactMapper() {
    }

    // only used for the data retrieval to map rows to java objects (contact)
    // not used for inserting data
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setContactID(resultSet.getInt("contact_id"));
        contact.setFirstName(resultSet.getString("first_name"));
        contact.setLastName(resultSet.getString("last_name"));
        contact.setEmail(resultSet.getString("email"));
        contact.setPhoneNumber(resultSet.getInt("phone_number"));

        return contact;
    }
}
