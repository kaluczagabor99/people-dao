package hu.kaluczagabor;

import hu.kaluczagabor.model.Dao;
import hu.kaluczagabor.model.Person;
import hu.kaluczagabor.model.PersonDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;

public class Main {
    public static final String connString =
            "jdbc:sqlite:C:\\Users\\Kalucza Gábor\\IdeaProjects\\PeopleDao\\people.db";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(connString)) {
            Dao dao = new PersonDao(conn);

            dao.getAllPeople().stream().sorted(Comparator.comparingInt(Person::getId)).forEach(System.out::println);
//            Person anyonymous = new Person(5, "Anonymous", 999, "Female");
//            dao.addPerson(anyonymous);
//            dao.removePerson(anyonymous);
//
            System.out.println("----------------------");

            Person searchedPerson = dao.getPersonById(3);
            if (searchedPerson != null) {
                System.out.println("Person found: " + searchedPerson);
            } else {
                System.out.println("Nothing found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
