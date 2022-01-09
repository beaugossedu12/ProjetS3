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
    private  int id;
    private  int idGM;
    private  String nom;
    private String description;
    private static List<Module> listeModule = new ArrayList();

    public static List<Module> getListeModule() {
        return listeModule;
    }

    public static void setListeModule(List<Module> listeModule) {
        Module.listeModule = listeModule;
    }
    public Module(int id, int idGM, String nom, String description) {
        this.id = id;
        this.idGM = idGM;
        this.nom = nom;
        this.description = description;
    }



        public Module() {

    }
     public Module (String nom) {

        this.nom=nom;
            }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGM() {
        return idGM;
    }

    public void setIdGM(int idGM) {
        this.idGM = idGM;
    }

    public  String getNom() {
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
    
    public static int trouveModule2(Connection con, String nom, int idGM)throws SQLException 
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Module where nom = ? and idGM=?")) {
            pst.setString(1, nom);
            pst.setInt(2, idGM);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    
    public static String trouveDescriptionModule(Connection con, String nom)throws SQLException 
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select description from Module where nom = ?")) {
            pst.setString(1, nom);
            
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return null;
            }
            return findP.getString(1);
        }
    }
    
    public static int trouveIdGMModule(Connection con, String nom)throws SQLException 
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
   /* public static class ModuleAlreadyExistsException extends Exception {

        public ModuleAlreadyExistsException(String nom) {
            super("Le module de nom \"" + nom + "\" existe déjà");
        }
    }
    public static class ModuleNotFoundException extends Exception 
    {

        public ModuleNotFoundException(String nom) {
            super("Le module de nom \"" + nom + "\" n'existe pas");
        }
    }*/

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
