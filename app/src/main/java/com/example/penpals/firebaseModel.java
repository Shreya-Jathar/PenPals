package com.example.penpals;

public class firebaseModel {

    static String Name;
    static String Age;
    static String Country;
    static String NativeLanguage;

    public firebaseModel(String name, String age, String country, String nativeLanguage) {
        Name = name;
        Age = age;
        Country = country;
        NativeLanguage = nativeLanguage;
    }

    public static String getName() {
        return Name;
    }

    public static String getAge() {
        return Age;
    }

    public static String getCountry() {
        return Country;
    }

    public static String getNativeLanguage() {
        return NativeLanguage;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setNativeLanguage(String nativeLanguage) {
        NativeLanguage = nativeLanguage;
    }
}
