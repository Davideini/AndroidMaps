package com.sce3.thirdyear.classes;

import android.util.Patterns;

/*
In java, we can’t make Top level class static.
 Only nested classes can be static.
 So this is a simulation of a static class
 (just like the java math class is built)
 , i am building it as static because it useless to create
 an object reference every time we just want to validate
 a field.
 stas 4/5/2015.

 */
public final class InputValidator {
    private InputValidator () {}

    public static boolean EmptyField(String text)
    {
        return text.length()>0;
    }
    /*better version of the previous function*/
    public static boolean isEmpty(String text)
    {
        return text.length()==0;
    }
    public static boolean isBadForSQL(String text)
    {
        return (text.contains("'") || text.contains("\""));
    }
    /*For first and last names:
    only alphabetic and of length less than 30*/
    public static boolean Name(String text)
    {
        return text.matches("[a-zA-Z ]+") && text.length()<30;
    }
    /*
    Validate Email using built in Patterns*/
    public static boolean Email(String text) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }
    /*All kinds of phones, not perfect*/
    public static boolean Phone(String text) {
        return Patterns.PHONE.matcher(text).matches();
    }
    /*
    * Paswword only letters and numbers
    * length: between 8 and 15 including*/
    public static boolean Password(String text)
    {
        return text.matches("[a-zA-Z0-9 ]+") && (text.length()>=8 && text.length()<=15);
    }
    public static boolean AlphaNumerical(String text)
    {
        return text.matches("[a-zA-Z0-9 ]+");
    }
    /*min value smaller than max value
    * Author: Dj Arkash
     */
    public static boolean MinNotMax(int minP,int maxP) {
        if (minP > maxP) return true;
        return false;
    }
    /*No negative values
    * Author: Dj Arkash
     */
    public static boolean NotNegative(String value)
    {
        if(value.charAt(0) == '-') return true;
        return false;
    }

}
