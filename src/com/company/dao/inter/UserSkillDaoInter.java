package com.company.dao.inter;

import com.company.entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    public List<UserSkill> getUserSkillById(int userId);
}
