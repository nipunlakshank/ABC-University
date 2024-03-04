/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.ExamDTO;
import com.abc.service.MySQL;
import java.sql.ResultSet;

/**
 *
 * @author nipun
 */
public class ExamDAO {

    public int save(ExamDTO exam) throws Exception {
        try (MySQL db = new MySQL()) {
            ResultSet rs = db.createConnection()
                    .createIUDStatement("INSERT INTO exam (`subject_id`) VALUES (?)")
                    .setInt(1, exam.getSubjectId())
                    .executeUpdate()
                    .getGeneratedKeys();

            if (!rs.next()) {
                db.rollback();
                return 0;
            }
            int id = rs.getInt(1);

            String code = id + "";

            while (code.length() < 2) {
                code = "0" + code;
            }
            SubjectDAO subDAO = new SubjectDAO();
            String subCode = subDAO.get(exam.getSubjectId()).getCode();
            code = subCode + " / EX " + code;

            db.createIUDStatement("UPDATE `exam` SET `code`=? WHERE `id`=?")
                    .setString(1, code)
                    .setInt(2, id)
                    .executeUpdate()
                    .commit();
            
            ExamStudentDAO examStudentDAO = new ExamStudentDAO();
            examStudentDAO.addStudents(id, exam.getSubjectId());

            return id;
        }
    }

    public void update(int id, Boolean status) throws Exception {
        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("UPDATE `exam` SET `status`=? WHERE `id`=?")
                    .setBoolean(1, status)
                    .setInt(2, id)
                    .executeUpdate()
                    .commit();
        }
    }

}
