package cc.sion.service;


public class ServiceHelper {

    final static String calc_url = "http://CALC-SERVICE";
    final static String hello_url = "http://SAY-SERVICE";

    final static String utils_url = "http://SYS-SERVICE";

    public int calcFallback(int a,int b){
        return -9999;
    }


    public String sayFallback(String name,Throwable e)
    {
        if(e!=null)
            e.printStackTrace();

        return ":(";
    }
}
