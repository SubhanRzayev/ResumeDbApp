package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;


import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int nationality_Id = rs.getInt("nationality_id");
        int birthplace_Id = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationality_Id, null, nationalityStr);
        Country birthplace = new Country(birthplace_Id, birthplaceStr, null);


        return new User(id, name, surname, phone, email, nationality, birthplace, birthdate);

    }


    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connection()) {

            Statement stmt = c.createStatement();
            stmt.executeQuery("select "
                    + " u.*, "
                    + " n.nationality"
                    + " c.name as birthplace"
                    + " from user u,"
                    + " left join country n on u.nationality_id = n.id "
                    + " left join country c on u.birthplace_id=c.id");

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);

                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connection()) {

            Statement stmt = c.createStatement();
            stmt.executeQuery("select "
                    + " u.*, "
                    + "n.nationality "
                    + " c.name as birthplace "
                    + "from user u, "
                    + "left join country n on u.nationality_id = n.id"
                    + "left join country c on u.birthplace_id=c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email) values (?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateUser(User u) {
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, u.getById());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(int id) {
        try (Connection c = connection()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
