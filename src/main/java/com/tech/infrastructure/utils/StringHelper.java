package com.tech.infrastructure.utils;

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

}
