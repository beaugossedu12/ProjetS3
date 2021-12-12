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
import java.util.List;

/**
 *
 * @author sabin
 */
public class Module {
    private static int id;
    private static int idGM;
    private static String nom;
    private static String description;

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdGM() {
        return idGM;
    }

    public void setIdGM(int idGM) {
        this.idGM = idGM;
    }

    public static String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    private static Module rsToModule(ResultSet rs) throws SQLException {
        Module module = new Module();  // Creation d'un nouveau module
        module.setId(rs.getInt("id")); // Remplissage des attributs
        module.setNom(rs.getString("nom"));
        module.setDescription(rs.getString("description"));
        module.setIdGM(rs.getInt("idGM"));
        return module;
      }  
     public static ArrayList chargeModule(Connection con)throws SQLException {
        ResultSet rs = null;
        ArrayList liste = null;
        Module module = null;
        try ( Statement st = con.createStatement()) {
            rs = st.executeQuery("select * from Module");
            if (rs.next()) {  // S'il y a des donnees
              liste = new ArrayList();  // On construit une liste (vide pour l'instant)
              do {  // Et pour chaque ligne du resultSet
                module = rsToModule(rs);  // Recupère l'employe à partir du resultSet
                liste.add(module);  // Ajout de l'employé à la liste
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
     
    public static void afficheTousModules(Connection con) throws SQLException
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Module");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                String description = res.getString("description");
                int idGM = res.getInt("idGM");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                //java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : " + nom + " description " + description +" idGM "+idGM);
            }
        }
    }
    
    public static int trouveModule(Connection con, String nom)throws SQLException 
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Module where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    
    public static void ModifNomModule (Connection con, String nom1, String nomModifier) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Module
                set nom = ?
                where nom = ?
                """)){
            pst.setString(1, nomModifier);
            pst.setString(2, nom1);
            pst.executeUpdate();
        con.commit();     
        } catch (Exception ex) {
            System.out.println(ex);
        }
                     
                        
    }
    public static void ModifDescriptionModule(Connection con, String nom, String description)
            throws SQLException
    { 
        
        try ( PreparedStatement pst = con.prepareStatement("update Module set description = ? where nom= ?")) {
            
            pst.setString(1, description);
            pst.setString(2, nom);

        pst.executeUpdate();
        con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }    
    public static void deleteModule(Connection con,String nom) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from Module where nom = ?")){
        pst.setString(1, nom);
        pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
            
       
        
    }
    public static class ModuleAlreadyExistsException extends Exception {

        public ModuleAlreadyExistsException(String nom) {
            super("Le module de nom \"" + nom + "\" existe déjà");
        }
    }
    public static class ModuleNotFoundException extends Exception 
    {

        public ModuleNotFoundException(String nom) {
            super("Le module de nom \"" + nom + "\" n'existe pas");
        }
    }

    public static int moduletrouveGroupeModule(Connection con, String nom)throws SQLException 
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select idGM from Module where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }

}
