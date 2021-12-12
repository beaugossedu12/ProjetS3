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
import java.util.ArrayList;

/**
 *
 * @author sabin
 */
public class GroupeModule {
    private int id;
 
    private String nom;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
        private static GroupeModule rsToGroupeModule(ResultSet rs) throws SQLException {
        GroupeModule groupeModule = new GroupeModule();  // Creation d'un nouveau groupeModule
        groupeModule.setId(rs.getInt("id")); // Remplissage des attributs
        groupeModule.setNom(rs.getString("Nom"));
        groupeModule.setDescription(rs.getString("Description"));

        return groupeModule;
      }  
     public static ArrayList chargeGroupeModule (Connection con)throws SQLException {
        ResultSet rs = null;
        ArrayList liste = null;
        GroupeModule groupeModule = null;
        try ( Statement st = con.createStatement()) {
        rs = st.executeQuery("select * from GroupeModule");
        if (rs.next()) {  // S'il y a des donnees
          liste = new ArrayList();  // On construit une liste (vide pour l'instant)
          do {  // Et pour chaque ligne du resultSet
            groupeModule = rsToGroupeModule(rs);  // Recupère l'employe à partir du resultSet
            liste.add(groupeModule);  // Ajout de l'employé à la liste
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
      }
     
    public static void afficheTousGroupeModule(Connection con)throws SQLException
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

    public static void ModifNomGroupeModule (Connection con, String nom, String nomModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update GroupeModule
                set nom = ?
                where nom = ?
                """)){
            pst.setString(1, nomModifie);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
                        
    }
    
    public static void ModifDescriptionGroupeModule (Connection con, String nom, String description) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update GroupeModule
                set description = ?
                where nom= ?
                """)){
            pst.setString(1, description);
            pst.setString(2, nom);
            pst.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }                   
    }
    public static void deleteGroupeModule(Connection con,String nom) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from GroupeModule where nom = ?")){
        pst.setString(1, nom);
        pst.executeUpdate();   
        //con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
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
