package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
       int id = rs.getInt("id");
       String name = rs.getString("name");
       String nationality = rs.getString("nationality");

       return new Country(id,name,nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();

        try (Connection c = connection()) {
            Statement stmt = c.createStatement();

            stmt.executeQuery("select * from county");

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country ct = getCountry(rs);
                result.add(ct);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
