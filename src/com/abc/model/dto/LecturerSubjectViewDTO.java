/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model.dto;

/**
 *
 * @author nipun
 */
public class LecturerSubjectViewDTO {
    private final int lecId;
    private final int subId;
    private final String subCode;
    private final String subTitle;
    private final int subSemester;

    public LecturerSubjectViewDTO(int lecId, int subId, String subCode, String subTitle, int subSemester) {
        this.lecId = lecId;
        this.subId = subId;
        this.subCode = subCode;
        this.subTitle = subTitle;
        this.subSemester = subSemester;
    }

    public int getLecId() {
        return lecId;
    }

    public int getSubId() {
        return subId;
    }

    public String getSubCode() {
        return subCode;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getSubSemester() {
        return subSemester;
    }
    
}
