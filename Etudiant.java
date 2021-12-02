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
public class Etudiant {
        public static void afficheTousEtudiants(Connection con)throws SQLException 
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Etudiant");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                int idPersonne = res.getInt("fk_idPersonne_id");

                System.out.println(id + " : " + " id personne " + idPersonne);
            }
        }
    }

    public static class EtudiantNotFoundException extends Exception 
    {

        public EtudiantNotFoundException(String nom) {
            super("L' Etudiant de nom \"" + nom + "\" n'existe pas");
        }
    }

    public static class EtudiantAlreadyExistsException extends Exception 
    {

        public EtudiantAlreadyExistsException(String nom) {
            super("L' Etudiant de nom \"" + nom + "\" existe déjà");
        }
    }
    
    public static int trouveEtudiant(Connection con, String nom)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                select id from Etudiant
                join Personne on Personne.id=Etudiant.id
                where nom = ?
                        """)) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
}

