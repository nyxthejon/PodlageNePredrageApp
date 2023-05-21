/***********************************************************************
 * Module:  Nakup.java
 * Author:  nayke
 * Purpose: Defines the Class Nakup
 ***********************************************************************/
package com.example.podlagenepredrageapp.Razredi;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.*;

/** @pdOid 7d202ad2-e89d-4495-b681-a50c59c22d49 */
public  class Nakup{
   /** @pdOid ff1588bd-982e-452d-b5f8-32f0dc6d3d80 */
   private Date datumNakupa;
   /** @pdOid 4d8de496-def9-49ab-a4f7-caf3832e720f */
   private Double cena;
   /** @pdOid 58280c8b-bff8-4c6f-8958-5f2752ec4880 */
   private String status;
   /** @pdOid 3007c66a-e408-4864-992f-bfd5c7d8c4ec */
   private String tipNakupa;
   private Skladba skladba;
   private Kupec kupec;


   public Skladba VrniSkladbo(){
       return skladba;
   }

   
   /** @pdOid ac07bb41-54e9-432e-b6f4-575eab983e4e */
   public void ustvariNakup(Date datum, Double c, String s, String tip,Skladba sk,Kupec k) {
     datumNakupa = datum;
     cena = c;
     status = s;
     tipNakupa = tip;
     skladba = sk;
     kupec = k;
   }


   
   /** @pdOid 865f27cc-3798-452f-9143-8dbaa30890ee */
   public String vrniPodatkeNakupa() {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       @SuppressLint("DefaultLocale") String orderInfo = String.format("Podlaga: %s\nDatum: %s\nCena: $%.2f\nTip nakupa: %s\nKupec: %s ",
              skladba.vrniNaslov(), sdf.format(datumNakupa), cena, tipNakupa, kupec.vrniImeinPriimek());
      return orderInfo;
   }


   public Double VrniCeno(){
       return cena;
   }


   /** @pdOid 6e74c9ed-7c53-45da-8922-fb105cb65aeb */
   public boolean vrniNaVoljo() {
     if(status.equals("Ni kupljen") || status.equals("Najet"))
         return true;
     else if(status.equals("Ekskluzivno kupljen"))
         return false;
     else
         return false;
   }
}