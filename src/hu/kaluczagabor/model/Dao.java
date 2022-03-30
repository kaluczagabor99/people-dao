package hu.kaluczagabor.model;

import java.sql.SQLException;
import java.util.List;

public interface Dao {
    List<Person> getAllPeople() throws SQLException;
    Person getPersonById(int id) throws SQLException;
    int addPerson(Person person) throws SQLException;
    int removePerson(Person person) throws SQLException;
}
