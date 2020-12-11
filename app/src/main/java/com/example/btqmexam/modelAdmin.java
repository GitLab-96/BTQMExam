package com.example.btqmexam;

public class modelAdmin {
    String Name,Classs,Section,Branch,MobileNo;

    public modelAdmin() {
    }

    public modelAdmin(String name, String classs, String section, String branch, String mobileNo) {
        Name = name;
        Classs = classs;
        Section = section;
        Branch = branch;
        MobileNo = mobileNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClasss() {
        return Classs;
    }

    public void setClasss(String classs) {
        Classs = classs;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
