package com.example.user.csv_app;

public class EmployeeData {
    private String id;
    private String name;
    private String skill , skill_1, skill_2 ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                ", skill_1='" + skill_1 + '\'' +
                ", skill_2='" + skill_2 + '\'' +
                '}';
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkill_1() {
        return skill_1;
    }

    public void setSkill_1(String skill_1) {
        this.skill_1 = skill_1;
    }

    public String getSkill_2() {
        return skill_2;
    }

    public void setSkill_2(String skill_2) {
        this.skill_2 = skill_2;
    }
}
