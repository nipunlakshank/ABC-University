/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class ExamDTO {
    private final int id;
    private final int subjectId;
    private final String code;
    private final Boolean status;

    public ExamDTO(int id, int subjectId, String code, Boolean status) {
        this.id = id;
        this.subjectId = subjectId;
        this.code = code;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }
}
