package yekebagci.batu.model;

public class Guests {

    private String name;
    private String surName;

    private Integer age;

    public Guests()
    {

    }

    public Guests(String adi,String soy,Integer yas)
    {
        this.name = adi;
        this.surName = soy;
        this.age = yas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
