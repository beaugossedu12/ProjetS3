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
public class GroupeModule {
    public static void afficheTousGroupesModules(Connection con)throws SQLException
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from GroupeModule");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                String description = res.getString("description");

                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                //java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : " + nom + " description " + description);
            }
        }
    }
    public static int trouveGroupeModule(Connection con, String nom)throws SQLException 
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from GroupeModule where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    public static class GroupeModuleNotFoundException extends Exception {

        public GroupeModuleNotFoundException(String nom) {
            super("Le groupe de modules de nom \"" + nom + "\" n'existe pas");
        }
    }

    public static class GroupeModuleAlreadyExistsException extends Exception {

        public GroupeModuleAlreadyExistsException(String nom) {
            super("Le groupe de modules de nom \"" + nom + "\" existe déjà");
        }
    }
}
