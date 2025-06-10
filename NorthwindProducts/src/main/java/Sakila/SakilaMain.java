package Sakila;

import org.apache.commons.dbcp2.BasicDataSource;

public class SakilaMain {
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Need two arguments to work");
        }
        String username = args[0];
        String password = args[1];

        BasicDataSource dataS = new BasicDataSource();

        dataS.setUsername(username);
        dataS.setPassword(password);
        dataS.setUrl("jdbc:mysql://localhost:3306/sakila");

//        DatabaseQuery.displayAllActorLastName(dataS);
        DatabaseQuery.moviesByActorName(dataS);

    }


}
