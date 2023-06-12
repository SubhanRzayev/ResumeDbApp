package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("user_id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);

    }

    @Override
    public List<UserSkill> getUserSkillById(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connection()) {

            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "u.*, us.skils_id, s.name AS skill_name, us.power"
                    + "FROM"
                    + "    user_skils us"
                    + "        LEFT JOIN"
                    + "    user u ON us.user_id = u.id"
                    + "        LEFT JOIN"
                    + "    skill s ON us.skils_id = s.id"
                    + "WHERE"
                    + "    us.user_id = 7; where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
