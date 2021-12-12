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
public class Administrateur {
        public static void afficheTousAdministrateurs(Connection con)throws SQLException 
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Administrateur");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                String email = res.getString("email");
                String mdp = res.getString("mdp");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                //java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : " + nom + " prenom " + prenom+" email "+email+" mdp "+mdp);
            }
        }
    }

    public static class AdministrateurNotFoundException extends Exception 
    {

        public AdministrateurNotFoundException(String nom) {
            super("La Administrateur de nom \"" + nom + "\" n'existe pas");
        }
    }

    public static class AdministrateurAlreadyExistsException extends Exception 
    {

        public AdministrateurAlreadyExistsException(String nom) {
            super("La Administrateur de nom \"" + nom + "\" existe déjà");
        }
    }
    
    public static int trouveAdministrateur(Connection con, String nom)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Administrateur where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    
    public static void deleteAdministrateur(Connection con,String nom) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from Administrateur where nom = ?")){
        pst.setString(1, nom);
        pst.executeUpdate();   
        //con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void ModifEmailAdministrateur (Connection con, String nom, String emailModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Administrateur
                set email = ?
                where nom = ?
                """)){
            pst.setString(1, emailModifie);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
                        
    }
    
    public static void ModifmdpAdministrateur (Connection con, String nom, String mdpModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Administrateur
                set mdp = ?
                where nom = ?
                """)){
            pst.setString(1, mdpModifie);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
                        
    }
}
