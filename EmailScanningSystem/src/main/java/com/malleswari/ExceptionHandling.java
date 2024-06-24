package com.malleswari;


public class ExceptionHandling {

 public static void handle(Exception e,String usermsg){
     // log detailed exception message for debugging
     System.out.println("An Error Occured: "+e.getMessage());
     // print a user friendly message to console
 }
 public static void handle(Exception e){
     handle(e,"An unexpected error occured");
 }
 public static void showError(String s){
     System.out.println(s);
     System.exit(0);
 }

}
