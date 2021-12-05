/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sabin
 */
public class Voeux {
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
            return findP.getInt(1);
        }
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
    
    public static void ModifChoix1 (Connection con, String nom, int choix1) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Personne
                set choix1 = ?
                where nom = ?
                """)){
            pst.setInt(1, choix1);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    public static void ModifChoix2 (Connection con, String nom, int choix2) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Personne
                set choix2 = ?
                where nom = ?
                """)){
            pst.setInt(1, choix2);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    public static void ModifChoix3 (Connection con, String nom, int choix3) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Personne
                set choix3 = ?
                where nom = ?
                """)){
            pst.setInt(1, choix3);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
}
