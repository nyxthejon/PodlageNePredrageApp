/***********************************************************************
 * Module:  KNakupSkladbe.java
 * Author:  nayke
 * Purpose: Defines the Class KNakupSkladbe
 ***********************************************************************/

package com.example.podlagenepredrageapp;

import java.util.*;




public class KNakupSkladbe {
   /** @pdRoleInfo migr=no name=SvPlacilniSistem assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public SvPlacilniSistem sv;
   /** @pdRoleInfo migr=no name=Nakup assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public Nakup nakup;
   
   /** @pdOid dad1de1a-6db4-4c31-b7cc-d1af247387e6 */
   public int vrniPodatkeNakupa() {
      // TODO: implement
      return 0;
   }
   
   /** @pdOid af122d29-f42d-413e-ba0b-54976ec2aa21 */
   public int vrniNaVoljo() {
      // TODO: implement
      return 0;
   }
   
   /** @pdOid 11f20895-9c1d-498d-b133-f3dfa9c0bb59 */
   public int ustvariNakup() {
      // TODO: implement
      return 0;
   }
   
   /** @pdOid e1bd4261-d6c0-4b82-8610-b7228b39afb4 */
   public double izracunajCeno() {
      // TODO: implement
      return 0;
   }
   
   /** @pdOid 6d111426-1f9e-4162-903e-bdd76d416925 */
   public int zakljuciNakup() {
      // TODO: implement
      return 0;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection getSvPlacilniSistem() {
      if (sv == null)
          sv = new SvPlacilniSistem();
      return (Collection) sv;
   }
   /** @pdGenerated default getter */
   public java.util.Collection getNakup() {
      if (nakup == null)
         nakup = new Nakup();
      return (Collection) nakup;
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
