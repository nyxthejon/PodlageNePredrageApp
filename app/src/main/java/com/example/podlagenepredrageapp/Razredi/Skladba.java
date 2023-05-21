/***********************************************************************
 * Module:  Skladba.java
 * Author:  nayke
 * Purpose: Defines the Class Skladba
 ***********************************************************************/
package com.example.podlagenepredrageapp.Razredi;
import java.io.Serializable;

/** @pdOid b83e4f26-e73f-4357-98be-5f50242e669b */
public class Skladba implements Serializable {
   /** @pdOid abf0afc1-8e28-49af-b260-ea5437bfed47 */
   private int idSkladbe;
   /** @pdOid 9b4c09cb-39ec-4b3e-a4ce-07ffe4537708 */
   private int dolzina;
   /** @pdOid d5eb66b8-9aae-4e58-a8ee-443b64ae34a1 */
   private String Zvrst;
   /** @pdOid 864942c9-372b-4a07-88c9-727b3688d5a3 */
   private String pot;
   /** @pdOid c2972de7-eeaf-4ce4-b4a6-2105d8aa58a5 */
   private double Cena;
   /** @pdOid eb5cbe9f-e0ff-4fe3-b07c-e0f979f6846b */
   private String tipSkladbe;
   private String Pot_slike;
   private String Naslov;
   private double Cena_Ekskluzivno;
   private String Username_Avtorja;
   private String Status;
   /** @pdOid ae6ad0c7-ab30-462a-b7d3-f76c2a4b2c1c */


   public Skladba(int id,int dolzina, String zvrst, String Pot, double cena, String tip, String pot_s,String naslov,String username,double cena_eks, String s){
   idSkladbe = id;
   dolzina = this.dolzina;
   Zvrst = zvrst;
   pot = Pot;
   Cena = cena;
   tipSkladbe = tip;
   Pot_slike = pot_s;
   Naslov = naslov;
   Username_Avtorja = username;
   Cena_Ekskluzivno = cena_eks;
   Status = s;
   }


   public String vrniNaslov(){
      return Naslov;
   }
   public String vrniPotSlike(){
      return Pot_slike;
   }
   public String vrniStatus() {return Status;}
   public double VrniCeno(){ return Cena; }
   public String VrniAvtorja() {return  Username_Avtorja; }
   public String VrniZvrst(){return Zvrst;}
   public String VrniPot(){return pot;}
   public double VrniCenoEksluzivno(){return Cena_Ekskluzivno; }
   public String VrniUsername(){return Username_Avtorja;}


   public boolean equals(Skladba s) {
      if (this == s) {
         return true;
      }
      if (s == null || getClass() != s.getClass()) {
         return false;
      }
      Skladba other = (Skladba) s;
      return this.idSkladbe == other.idSkladbe && this.Naslov.equals(other.Naslov);
   }

}