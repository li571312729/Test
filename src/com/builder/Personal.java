package com.builder;

public class Personal {
    
    private String name;
    private String sex;
    private int age;
    private int height;
    public int weight;
    public Address address;
    
    private Personal() {}

    public static class PersonalBuilder {
        
        Personal p = new Personal();

        public PersonalBuilder buildBasicInfo(String name, String sex, int age) {
            p.name = name;
            p.sex = sex;
            p.age = age;
            return this;
        }

        public PersonalBuilder buildHeight(int height) {
            p.height = height;
            return this;
        }

        public PersonalBuilder buildWeight(int weight) {
            p.weight = weight;
            return this;
        }

        public PersonalBuilder buildAdress(String street, String streetNo) {
            Address address = new Address(street, streetNo);
            p.address = address;
            return this;
        }

        public Personal build() {
            return p;
        }
    }

    @Override
    public String toString() {
        return "Personal [name=" + name + ", sex=" + sex + ", age=" + age + ", height=" + height + ", weight=" + weight
                + ", address=" + address + "]";
    }
}
