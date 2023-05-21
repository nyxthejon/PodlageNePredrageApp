package com.example.podlagenepredrageapp.MejniRazredi; /***********************************************************************
 * Module:  SvPlacilniSistem.java
 * Author:  nayke
 * Purpose: Defines the Class SvPlacilniSistem
 ***********************************************************************/
import java.util.*;

/** @pdOid d50003c6-1e09-41a8-8112-49b6e18397f8 */
public class SvPlacilniSistem_SIM {
   /** @pdOid 91539868-0e33-4079-8aa9-c0e224432312 */


   public boolean izvediNakup(String ime, String number, String exp, String cvv) {
      if(PreveriPodatke(ime,number,exp,cvv))
      return true;
      else
         return false;
   }

   public boolean PreveriPodatke(String ime,String number, String exp, String cvv){
      //preveri ce so stevila v imenu
      if(ime.matches(".*\\d.*"))
         return false;
      else if(number.length()!=19)
         return false;
      else if (!isValidDate(exp)) {
         return false;
      } else if (cvv.length() != 3) {
         return false;
      }
      else {
         return true;
      }


   }

   public boolean isValidDate(String input) {
      String[] parts = input.split("/");
      if (parts.length != 2) {
         return false;
      }
      String leftSide = parts[0].trim();
      String rightSide = parts[1].trim();
      try {
         int leftValue = Integer.parseInt(leftSide);
         int rightValue = Integer.parseInt(rightSide);

         if (leftValue <= 12 && rightValue >= 23) {
            return true;
         }
      } catch (NumberFormatException e) {
      }
      return false;
   }
}