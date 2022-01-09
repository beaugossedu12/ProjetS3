/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sabin
 */
public class Voeux {
    private int id;
    private int idGM;
    private int idEtudiant;
    private int choix1;
    private int choix2;
  private int choix3;
   private static java.util.List<Voeux> listeVoeux = new ArrayList();

    public static java.util.List<Voeux> getListeVoeux() {
        return listeVoeux;
    }

    public static void setListeVoeux(java.util.List<Voeux> listeVoeux) {
        Voeux.listeVoeux = listeVoeux;
    }
   
    public Voeux(int id, int idGM, int idEtudiant, int choix1, int choix2, int choix3 ) {
        this.id = id;
        this.idGM = idGM;
        this.idEtudiant = idEtudiant;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        
    }
  

    public int getId() {
        return id;
    }

    public int getIdGM() {
        return idGM;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public int getChoix1() {
        return choix1;
    }

    public int getChoix2() {
        return choix2;
    }

    public int getChoix3() {
        return choix3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdGM(int idGM) {
        this.idGM = idGM;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public void setChoix1(int choix1) {
        this.choix1 = choix1;
    }

    public void setChoix2(int choix2) {
        this.choix2 = choix2;
    }

    public void setChoix3(int choix3) {
        this.choix3 = choix3;
    }
    
    private final String Select_voeux = "select * from Voeux";
    public static Voeux trouveVoeuxListe(Connection con, int idEtudiant)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select * from Voeux where idEtudiant = ?")) {
            pst.setInt(1, idEtudiant);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return null;
            }
            Voeux VoeuxTrouve= new Voeux(findP.getInt(1),findP.getInt(3),findP.getInt(2),findP.getInt(4),findP.getInt(5),findP.getInt(6));
            return VoeuxTrouve;
        }
    } 
    
        public static int trouveVoeux(Connection con, int idEtudiant)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Voeux where idEtudiant = ?")) {
            pst.setInt(1, idEtudiant);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
     /*rivate static Voeux rsToVoeux(ResultSet rs) throws SQLException {
        Voeux voeux = new Voeux();  // Creation d'un nouveau voeux
        voeux.setId(rs.getInt("id")); // Remplissage des attributs
        voeux.setIdGM(rs.getInt("idGM"));
        voeux.setIdEtudiant(rs.getInt("idEtudiant"));
        voeux.setChoix1(rs.getInt("Choix1"));
        voeux.setChoix2(rs.getInt("Choix2"));
        voeux.setChoix3(rs.getInt("Choix3"));
        return voeux;
      }  
     public static ArrayList chargeVoeux(Connection con)throws SQLException {
        ResultSet rs = null;
        ArrayList liste = null;
        Voeux voeux = null;
        try ( Statement st = con.createStatement()) {
        rs = st.executeQuery("select * from Voeux");
        if (rs.next()) {  // S'il y a des donnees
          liste = new ArrayList();  // On construit une liste (vide pour l'instant)
          do {  // Et pour chaque ligne du resultSet
            voeux = rsToVoeux(rs);  // Recupère l'employe à partir du resultSet
            liste.add(voeux);  // Ajout de l'employé à la liste
          }
          while(rs.next());
        }
         //... (fermer le resultset, le statement, enfin faire propore quoi)
         st.close();
         rs.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return liste;  // Retourne la liste des employés ou null s'il n'y en a aucun
      }*/
            

    public static void afficheTousVoeux(Connection con)throws SQLException 
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Voeux");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                int idGM = res.getInt("idGM");
                int idEtudiant = res.getInt("idEtudiant");
                int choix1 = res.getInt("choix1");       
                int choix2 = res.getInt("choix2");
                int choix3 = res.getInt("choix3");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                //java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : idGM: " + idGM + " idEtudiant: " + idEtudiant+" choix1: "+choix1+" choix2: "+choix2+" choix3: "+ choix3);
            }
        }
    }

 
    //cherche les voeux qui ont du groupe module demande
    public static int trouveIdGMVoeux(Connection con,int idGM)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select * from Voeux where idGM = ?")) {
            pst.setInt(1, idGM);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(2);
        }
    }
    
    public static void ModifChoix1AvecModule(Connection con,int idEtudiant,int idGM,int module)throws SQLException
    {
 
            try ( PreparedStatement pst2 = con.prepareStatement(
                """
                update Voeux
                set choix1 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst2.setInt(1, module);
            pst2.setInt(2, idEtudiant);
            pst2.setInt(3, idGM);
            pst2.executeUpdate();
            con.commit();
            } catch (Exception ex) {
                System.out.println(ex);
            } 
            //return findP.getInt(2);
        
        
    }
    
        public static void ModifChoix2AvecModule(Connection con,int idEtudiant,int idGM,int module)throws SQLException
    {

            try ( PreparedStatement pst2 = con.prepareStatement(
                """
                update Voeux
                set choix2 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst2.setInt(1, module);
            pst2.setInt(2, idEtudiant);
            pst2.setInt(3, idGM);
            pst2.executeUpdate();
            con.commit();
            } catch (Exception ex) {
                System.out.println(ex);
            } 
            //return findP.getInt(2);
        
        
    }
    
        public static void ModifChoix3AvecModule(Connection con,int idEtudiant,int idGM, int module)throws SQLException
    {

            try ( PreparedStatement pst2 = con.prepareStatement(
                """
                update Voeux
                set choix3 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst2.setInt(1, module);
            pst2.setInt(2, idEtudiant);
            pst2.setInt(3, idGM);
            pst2.executeUpdate();
            con.commit();
            } catch (Exception ex) {
                System.out.println(ex);
            } 
            //return findP.getInt(2);
        
        
    }
    
    public static void deleteVoeux(Connection con,int id) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from Voeux where idEtudiant = ?")){
        pst.setInt(1, id);
        pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void ModifChoix1 (Connection con, int idEtudiant, int choix1, int idGM) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Voeux
                set choix1 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst.setInt(1, choix1);
            pst.setInt(2, idEtudiant);
            pst.setInt(3, idGM);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    public static void ModifChoix2 (Connection con, int idEtudiant, int choix2, int idGM) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Voeux
                set choix2 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst.setInt(1, choix2);
            pst.setInt(2, idEtudiant);
            pst.setInt(3, idGM);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    public static void ModifChoix3 (Connection con, int idEtudiant, int choix3, int idGM) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Voeux
                set choix3 = ?
                where idEtudiant = ? and idGM = ?
                """)){
            pst.setInt(1, choix3);
            pst.setInt(2, idEtudiant);
            pst.setInt(3, idGM);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    
}
