package com.company.main;


import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserSkillDaoInter;


public class Main {


    public static void main(String[] args) {
//        UserSkillDaoInter userDao = Context.instanceUserSkillDao();
//
//        System.out.println(userDao.getUserSkillById(7));

//        EmploymentHistoryDaoInter dao = Context.instanceEmploymentHistoryDao();
//
//        System.out.println(dao.getEmploymentHistory(7));

//        CountryDaoInter dao = Context.instanceCountryDao();
//        System.out.println(dao.getAll());

        SkillDaoInter dao = Context.instanceSkillDao();
        System.out.println(dao.getAll());

    }

}

