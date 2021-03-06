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
public class Personne {
    public static void afficheToutesPersonnes(Connection con)throws SQLException 
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Personne");
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

    public static class PersonneNotFoundException extends Exception 
    {

        public PersonneNotFoundException(String nom) {
            super("La Personne de nom \"" + nom + "\" n'existe pas");
        }
    }

    public static class PersonneAlreadyExistsException extends Exception 
    {

        public PersonneAlreadyExistsException(String nom) {
            super("La Personne de nom \"" + nom + "\" existe déjà");
        }
    }
    
    public static int trouvePersonne(Connection con, String nom)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Personne where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    
    public static void deletePersonne(Connection con,String nom) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from Personne where nom = ?")){
        pst.setString(1, nom);
        pst.executeUpdate();   
        //con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void ModifEmailPersonne (Connection con, String nom, String emailModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Personne
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
    
    public static void ModifmdpPersonne (Connection con, String nom, String mdpModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Personne
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
