package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();

        try (Connection c = connection()) {
            Statement stmt = c.createStatement();

            stmt.executeQuery("select * from skill");

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill ct = getSkill(rs);
                result.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
