/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class ExamViewDTO {
    private final int id;
    private final int subjectId;
    private final String code;
    private final String subject;
    private final int semester;
    private final String status;

    public ExamViewDTO(int id, int subjectId, String code, String subject, int semester, String status) {
        this.id = id;
        this.subjectId = subjectId;
        this.code = code;
        this.subject = subject;
        this.semester = semester;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public int getSubjectId(){
        return subjectId;
    }

    public String getCode() {
        return code;
    }

    public String getSubject() {
        return subject;
    }

    public int getSemester() {
        return semester;
    }

    public String getStatus() {
        return status;
    }
    
}

