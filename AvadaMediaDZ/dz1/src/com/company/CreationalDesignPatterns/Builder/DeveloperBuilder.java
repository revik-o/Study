package com.company.CreationalDesignPatterns.Builder;

public class DeveloperBuilder {
    Developer developer = new Developer();

    public DeveloperBuilder setName(String name){
        this.developer.name = name;
        return this;
    }

    public DeveloperBuilder setAge(int age) {
        this.developer.age = age;
        return this;
    }

    public DeveloperBuilder setYearsOfExperience(int yearsOfExperience) {
        this.developer.yearsOfExperience = yearsOfExperience;
        return this;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + developer.name + '\'' +
                ", age=" + developer.age +
                ", yearsOfExperience=" + developer.yearsOfExperience +
                '}';
    }
}