/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dao;

import com.abc.model.dto.SubjectDTO;
import com.abc.service.MySQL;
import java.util.List;

/**
 *
 * @author nipun
 */
public class ExamStudentDAO {

    public void addStudents(int examId, int subjectId) throws Exception {

        SubjectDAO subDAO = new SubjectDAO();
        DegreeStudentDAO degStuDAO = new DegreeStudentDAO();

        SubjectDTO subject = subDAO.get(subjectId);
        int degId = subject.getDegreeId();
        List<Integer> studentIds = degStuDAO.getStudentIds(degId);

        try (MySQL db = new MySQL()) {
            db.createConnection()
                    .createIUDStatement("INSERT INTO `student_has_exam` (`student_id`,`exam_id`) VALUES (?, ?)");
            for (int studentId : studentIds) {
                db.setInt(1, studentId)
                        .setInt(2, examId)
                        .executeUpdate();
            }
            db.commit();
        }
    }
}
