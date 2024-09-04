package repository;
import contactManager.Contact;
import util.DataBaseUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {


    public boolean addContact(Contact contact) {
        try {
            Connection connection = DataBaseUtil.getConnection();
            String sql = "Insert into contact(name,surname,phone) Values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());

            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }


    public Contact getByPhone(String phone) {
        try {
            Contact contact = null;
            Connection connection = DataBaseUtil.getConnection();
            String sql = "select id,name,surname,phone from contact where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                contact = new Contact();
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String p = resultSet.getString("phone");

                contact.setId(id);
                contact.setName(name);
                contact.setSurname(surname);
                contact.setPhone(p);

            }

            return contact;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Contact> getList() {
        Connection connection = null;
        try {
            List<Contact> contactList = new LinkedList<>();
            connection = DataBaseUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from contact";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getString("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                contactList.add(contact);

            }

            return contactList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }

    }

    public int delete(String phone) {
        Connection connection = null;
        try {
            connection = DataBaseUtil.getConnection();
            PreparedStatement pr = connection.prepareStatement("delete from contact where phone  = ?");
            pr.setString(1, phone);
            return pr.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public List<Contact> search(String query){
        Connection connection = null;
        List<Contact> contactList = new LinkedList<>();
            try {
                 connection = DataBaseUtil.getConnection();
                 String sql = "select * from contact where lower(name) like ? or lower(surname) like ? or phone like ?";
                 PreparedStatement pr = connection.prepareStatement(sql);
                 String smb = "%"+query.toLowerCase()+"%";
                 pr.setString(1,smb);
                 pr.setString(2,smb);
                 pr.setString(3,smb);

                 ResultSet resultSet = pr.executeQuery();
                 while(resultSet.next()){
                     Contact contact = new Contact();
                     contact.setId(resultSet.getString("id"));
                     contact.setName(resultSet.getString("name"));
                     contact.setSurname(resultSet.getString("surname"));
                     contact.setPhone(resultSet.getString("phone"));

                     contactList.add(contact);

                 }

                 return contactList;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            finally {
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }


}
