import javax.print.DocFlavor;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        //exercise 1
//        try{
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/northwind",
//                    "root",
//                    "yearup"
//            );
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT ProductName FROM products"
//            );
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("ProductName"));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }



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
}
