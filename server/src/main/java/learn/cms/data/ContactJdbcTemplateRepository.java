package learn.cms.data;

import learn.cms.data.mappers.ContactMapper;
import learn.cms.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ContactJdbcTemplateRepository implements ContactRepository {
    // Constructor based dependency injection
        // POJO is the JdbcTemplate
        // Controller: ContactJdbcTemplateRepository
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Contact> findAll() {
        final String sql = "select * " + "from contact limit 100;";
        return jdbcTemplate.query(sql, new ContactMapper());
    }

    @Override
    public Contact findById(int contactId) {
        final String sql = "select contact_id, first_name, last_name, email, phone_number " +
                "from contact where contact_id = ?;";
        return jdbcTemplate.query(sql, new ContactMapper(), contactId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Contact add(Contact contact) {
        final String sql = "insert into contact (first_name, last_name, email, phone_number) "
                + " values (?,?,?,?);";
        // allows you to retrieve auto-generated keys from the db after insert operation
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // executes an insert statement on the db, returns number of rows affected
        // this.jdbcTemplate.update(callback, keyHolder)
        int rowsAffected = this.jdbcTemplate.update((connection) -> {
            // defines a call back function that receives a connection object, representing the db connection
                // takes two arguments, a callback function and the `KeyHolder` instance
                    // the lambda expression acts as the callback function, taking `Connection` object as input
                    // connection object is used to create PreparedStatement & set its parameters based on contact
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getEmail());
            ps.setString(4, String.valueOf(contact.getPhoneNumber()));
            return ps;
        }, keyHolder);
        // if <= 0 rows were affected, contact was not added successfully therefore return null
        if (rowsAffected <= 0) {
            return null;
        // otherwise, set the newly added contact's ID to the keyholder value (the auto-incremented id from db)
        } else {
            contact.setContactID(Objects.requireNonNull(keyHolder.getKey()).intValue());
            return contact;
        }
    }

    @Override
    public boolean update(Contact contact) {
        final String sql = "update contact set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phone_number = ? "
                + "where contact_id = ?;";
        return jdbcTemplate.update(sql,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getContactID()) > 0;
    }

    @Override
    public boolean deleteById(int contactId) {
        return jdbcTemplate.update("delete from contact where contact_id = ?;", contactId) > 0;
    }
}
