package Sakila;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseQuery {

    public static void displayAllActorLastName(BasicDataSource dataSource){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pS = connection.prepareStatement(
                     "SELECT first_name, last_name FROM actor \n" +
                     "WHERE last_name LIKE ?")){
            pS.setString(1, "%" + getUserInput() + "%");
            try (ResultSet resultSet = pS.executeQuery()){
                if(resultSet.next()){
                    System.out.println("Your matches are: \n");
                    do {
                        System.out.printf("""
                            --------------------------
                            Actor
                            First name: %s
                            Last name: %s
                           
                            """,
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"));
                    }while(resultSet.next());
                }else {
                    System.out.println("No matches found!");
                }


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void moviesByActorName(BasicDataSource dataSource){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pS = connection.prepareStatement(
                     "SELECT title, a.actor_id, first_name, last_name FROM actor AS a \n" +
                             "JOIN film_actor ON a.actor_id = film_actor.actor_id \n" +
                             "JOIN film ON film_actor.film_id = film.film_id \n" +
                             "WHERE a.actor_id = ?; ")){
            pS.setInt(1, getActorId(dataSource) );
            try (ResultSet resultSet = pS.executeQuery()){
                if(resultSet.next()){
                    System.out.println("Your matches are: \n");
                    System.out.printf("Actor: %s %s\n-----------------\n", resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                    do {
                        System.out.printf(" %s\n", resultSet.getString("title"));
                    }while(resultSet.next());
                }else {
                    System.out.println("No matches found!");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int getActorId(BasicDataSource dataSource){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pS = connection.prepareStatement(
                     "SELECT actor_id, first_name, last_name FROM actor AS a \n" +
                             "WHERE first_name = ? AND last_name = ?")){
            System.out.println("Enter first name");
            pS.setString(1, getUserInput() );
            System.out.println("Enter last name");
            pS.setString(2, getUserInput());
            try (ResultSet resultSet = pS.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("actor_id");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    public static String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toUpperCase();
    }

}

