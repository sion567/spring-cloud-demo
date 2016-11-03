package cc.sion.service;


public class ServiceHelper {

    final static String calc_url = "http://CALC-SERVICE";
    final static String hello_url = "http://SAY-SERVICE";


    public int calcFallback(int a,int b){
        return -9999;
    }


    public String sayFallback(String name,Throwable e)
    {
        if(e!=null)
            e.printStackTrace();
        else
            System.out.println("not Throwable");

        return ":(";
    }
}
