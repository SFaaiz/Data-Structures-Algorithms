package com.faaiz;

enum week{
    MON, TUE, WED, THURS, FRI, SAT, SUN;
}

public class enums {
    public static void main(String[] args) {
//        week day = week.FRI;
//        System.out.println(day);
        for(week days: week.values()){
            System.out.println(days);
        }
    }


}
