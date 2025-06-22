package com.tech.infrastructure.utils;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public  class StringHelper {

    public static String isLong(String str){
        String res ="";
        try{
            Long.parseLong(str);
            res = str;
        }catch (Exception e){
            res ="-1";
        }
        return res;
    }

    public String isDouble(String str){
        String res ="";
        try{
            Double.parseDouble(str);
            res = str;
        }catch (Exception e){
            res ="0.0";
        }
        return res;
    }

    public String isInt(String str){
        String res ="";
        try{
            Integer.parseInt(str);
            res = str;
        }catch (Exception e){
            res ="0";
        }
        return res;
    }

    public static String isLocalDate(String str){
        String res ="";
        boolean success=false;

        try{
            LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            res ="yyyy-MM-dd";
            success=true;
        }catch (Exception e){
            res ="";
        }

        if(!success){
            try{
                LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                res ="dd-MM-yyyy";
            }catch (Exception e){
                res ="";
            }
        }

        return res;
    }

    public static String isBool(String str){
        String res ="";
        try{
            Boolean.parseBoolean(str);
            res = str;
        }catch (Exception e){
            res ="false";
        }
        return res;
    }

    public static boolean isBoolPlus(String str){
        boolean res = false;
        try{
            Boolean.parseBoolean(str);
            res = true;
        }catch (Exception e){
            res =false;
        }
        return res;
    }

}
