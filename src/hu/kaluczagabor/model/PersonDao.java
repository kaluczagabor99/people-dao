package hu.kaluczagabor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonDao implements Dao {
    public static final String PEOPLE_TABLE = "people";
    private final PreparedStatement getAllPeoplePstmt;
    private final PreparedStatement getPersonByIdPstmt;
    private final PreparedStatement addPersonPstmt;
    private final PreparedStatement removePersonPstmt;

    public PersonDao(Connection conn) throws SQLException {
        getAllPeoplePstmt = conn.prepareStatement("SELECT * FROM " + PEOPLE_TABLE);
        getPersonByIdPstmt = conn.prepareStatement("SELECT * FROM " + PEOPLE_TABLE + " WHERE id = ?");
        addPersonPstmt = conn.prepareStatement(
                "INSERT INTO " + PEOPLE_TABLE + " (id,name,age,gender) VALUES (?, ?, ?, ?)");
        removePersonPstmt = conn.prepareStatement("DELETE FROM " + PEOPLE_TABLE + " WHERE id = ?");
    }

    @Override
    public List<Person> getAllPeople() throws SQLException {
        List<Person> people = new ArrayList<>();
        ResultSet rs = getAllPeoplePstmt.executeQuery();

        while (rs.next()) {
            people.add(new Person(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4)));
        }
        rs.close();
        return people;
    }

    @Override
    public Person getPersonById(int id) throws SQLException {
        Person person = null;
        getPersonByIdPstmt.setInt(1, id);

        ResultSet rs = getPersonByIdPstmt.executeQuery();
        if (rs.next()) {
            person = new Person(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4));
        }
        rs.close();
        return person;
    }

    @Override
    public int addPerson(Person person) throws SQLException {
        addPersonPstmt.setInt(1, person.getId());
        addPersonPstmt.setString(2, person.getName());
        addPersonPstmt.setInt(3, person.getAge());
        addPersonPstmt.setString(4, person.getGender());

        return addPersonPstmt.executeUpdate();
    }

    @Override
    public int removePerson(Person person) throws SQLException {
        removePersonPstmt.setInt(1, person.getId());
        return removePersonPstmt.executeUpdate();
    }

}
