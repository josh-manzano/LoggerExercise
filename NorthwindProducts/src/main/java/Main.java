import javax.print.DocFlavor;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        boolean mainmenu = true;


        while(mainmenu){
            System.out.println("\nWhat do you want to do?");
            System.out.println("""
                    1) Display all products
                    2) Display all customers
                    0) Exit
                    """);
            String choice = in.nextLine();


            switch(choice){
                case "1" -> displayAllProducts();
                case "2" -> System.out.println("customers");
                case "0" -> {
                    System.out.println("Have a great day");
                    return;
                }
                default -> System.out.println("Invalid input");
            }

        }

        //exercise 2

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup"
            );

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            //Print option 1
//            while(resultSet.next()){
//                System.out.println("\nid: " + resultSet.getString("ProductID"));
//                System.out.println("name: " + resultSet.getString("ProductName"));
//                System.out.println("price: " + resultSet.getString("UnitPrice"));
//                System.out.println("stock: " + resultSet.getString("UnitsInStock"));
//            }

            //Kind of option 2
            System.out.println("Id   Name      Price    Stock");
            System.out.println("---------------------------------");
            while(resultSet.next()){
                System.out.println(
                        resultSet.getString("ProductID") + "  "+
                                resultSet.getString("ProductName")+"      "+
                                String.format("$%.2f ", resultSet.getDouble("UnitPrice"))+
                                " | " + resultSet.getString("UnitsInStock")
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    static void displayAllProducts() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup"
            );


            preparedStatement = connection.prepareStatement(
                    "SELECT ProductName FROM products"
            );

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                System.out.println(resultSet.getString("ProductName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}
