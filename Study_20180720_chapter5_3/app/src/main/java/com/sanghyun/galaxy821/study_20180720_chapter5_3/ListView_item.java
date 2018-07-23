package com.sanghyun.galaxy821.study_20180720_chapter5_3;

public class ListView_item {

    String name;
    String mobile;
    int age;
    int resid;

    public ListView_item(String name, String mobile){
        this.name = name;
        this.mobile = mobile;
    }

    public ListView_item(String name, String mobile, int age, int resid){
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.resid = resid;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getResid(){
        return resid;
    }

    public void setResid(int resid){
        this.resid = resid;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
