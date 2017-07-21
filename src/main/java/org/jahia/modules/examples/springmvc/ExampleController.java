package org.jahia.modules.examples.springmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A basic example of how to use Spring MVC annotated controllers with the Jahia CMS.
 */

@Controller
public class ExampleController {

    public class ComplexResult {
        private String firstName;
        private String lastName;

        public ComplexResult(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
    public static Connection getPostgresConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test","postgres", "qwerty123");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
 //   @RequestMapping(method= RequestMethod.GET,value="/hello",produces=MediaType.TEXT_PLAIN_VALUE)
    public String getHello() throws SQLException {
        Connection connection = getPostgresConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs =stmt.executeQuery("select id, city from public.address;");
        Map<Integer, String> entityList = new HashMap<Integer, String>();
        String bd=new String();

        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  address = rs.getString("city");
            bd+= Integer.toString(id)+" : " +address +"\n";
            entityList.put(id, address);

        }

        return bd;
    }
    public ArrayList<Address> getHello1() throws SQLException {
        Connection connection = getPostgresConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs =stmt.executeQuery("select id, city from public.address;");
        ArrayList<Address> list = new ArrayList<Address>();

        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  city = rs.getString("city");
            Address address= new Address();
            address.setId(id);
            address.setCity(city);
            list.add(address);
        }

        return list;
    }
     public String getHello( String world) {
        return "Hello " + world;
    }

    @RequestMapping(method= RequestMethod.GET,value="/complex",produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComplexResult getComplexResult() {
        return new ComplexResult("Serge", "Huber");
    }

}
