/***********************************************************************
 * Module:  KNakupSkladbe.java
 * Author:  nayke
 * Purpose: Defines the Class KNakupSkladbe
 ***********************************************************************/

package com.example.podlagenepredrageapp.KontrolniRazredi;

import com.example.podlagenepredrageapp.Razredi.Kupec;
import com.example.podlagenepredrageapp.Razredi.Nakup;
import com.example.podlagenepredrageapp.Razredi.Skladba;
import com.example.podlagenepredrageapp.MejniRazredi.SvPlacilniSistem_SIM;

import java.util.*;

public class KNakupSkladbe {

   private Nakup n;
   private SvPlacilniSistem_SIM sv;
   /** @pdOid dad1de1a-6db4-4c31-b7cc-d1af247387e6 */

   public String vrniPodatkeNakupa(){
      return n.vrniPodatkeNakupa();
   }
   
   /** @pdOid af122d29-f42d-413e-ba0b-54976ec2aa21 */
   public boolean vrniNaVoljo(){
      return n.vrniNaVoljo();
   }
   /** @pdOid 11f20895-9c1d-498d-b133-f3dfa9c0bb59 */
   public void ustvariNakup(Date datum, Double c, String s, String tip, Skladba sk, Kupec k) {
         n = new Nakup();
         n.ustvariNakup(datum,c,s,tip,sk,k);
      }

  public boolean IzvediNakup(String ime,String number, String exp ,String cvv){
    SvPlacilniSistem_SIM Simulacija = new SvPlacilniSistem_SIM();
    return Simulacija.izvediNakup(ime,number,exp,cvv);
  }
   public Double VrniCeno(){
       return n.VrniCeno();
   }
   /** @pdOid 6d111426-1f9e-4162-903e-bdd76d416925 */

   public Skladba VrniSkladbo(){
      return n.VrniSkladbo();
   }






/*
   public java.util.Iterator getIteratorSvPlacilniSistem() {
      if (svPlacilniSistem == null)
         svPlacilniSistem = new java.util.HashSet();
      return svPlacilniSistem.iterator();
   }


   public void setSvPlacilniSistem(java.util.Collection newSvPlacilniSistem) {
      removeAllSvPlacilniSistem();
      for (java.util.Iterator iter = newSvPlacilniSistem.iterator(); iter.hasNext();)
         addSvPlacilniSistem((SvPlacilniSistem)iter.next());
   }

   public void addSvPlacilniSistem(SvPlacilniSistem newSvPlacilniSistem) {
      if (newSvPlacilniSistem == null)
         return;
      if (this.svPlacilniSistem == null)
         this.svPlacilniSistem = new java.util.HashSet();
      if (!this.svPlacilniSistem.contains(newSvPlacilniSistem))
         this.svPlacilniSistem.add(newSvPlacilniSistem);
   }

   public void removeSvPlacilniSistem(SvPlacilniSistem oldSvPlacilniSistem) {
      if (oldSvPlacilniSistem == null)
         return;
      if (this.svPlacilniSistem != null)
         if (this.svPlacilniSistem.contains(oldSvPlacilniSistem))
            this.svPlacilniSistem.remove(oldSvPlacilniSistem);
   }

   public void removeAllSvPlacilniSistem() {
      if (svPlacilniSistem != null)
         svPlacilniSistem.clear();
   }

 */

/*
   public java.util.Iterator getIteratorNakup() {
      if (nakup == null)
         nakup = new java.util.HashSet();
      return nakup.iterator();
   }*/
   
   /*
   public void setNakup(java.util.Collection newNakup) {
      removeAllNakup();
      for (java.util.Iterator iter = newNakup.iterator(); iter.hasNext();)
         addNakup((Nakup)iter.next());
   }
   

   public void addNakup(Nakup newNakup) {
      if (newNakup == null)
         return;
      if (this.nakup == null)
         this.nakup = new java.util.HashSet();
      if (!this.nakup.contains(newNakup))
         this.nakup.add(newNakup);
   }
   

   public void removeNakup(Nakup oldNakup) {
      if (oldNakup == null)
         return;
      if (this.nakup != null)
         if (this.nakup.contains(oldNakup))
            this.nakup.remove(oldNakup);
   }

   public void removeAllNakup() {
      if (nakup != null)
         nakup.clear();
   }*/

}
