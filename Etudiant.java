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
import java.util.Optional;

/**
 *
 * @author sabin
 */
public class Etudiant {
    private final int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private Etudiant etudiant;
    private static List<Etudiant> listeEtudiant = new ArrayList();
  

    public Etudiant(int id,String nom, String prenom, String email, String mdp) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }
    public Etudiant(int id,String email, String mdp) {
        this.id=id;
        this.email = email;
        this.mdp = mdp;
    }
    public int getId() {
        return id;
    }


    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String toString() {
        return nom + " " + prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        //binder.readBean(etudiant);
    }
    
    public Etudiant getEtudiant() {
      return etudiant;
    }
    
    public static void afficheTousEtudiants(Connection con)throws SQLException 
    {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from Etudiant");
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

    public static class EtudiantNotFoundException extends Exception 
    {

        public EtudiantNotFoundException(String nom) {
            super("l'etudiant de nom \"" + nom + "\" n'existe pas");
        }
    }

    /*public static class EtudiantAlreadyExistsException extends Exception 
    {

        public EtudiantAlreadyExistsException(String nom) {
            super("l'etudiant de nom \"" + nom + "\" existe déjà");
        }
    }*/
    
    public static int trouveEtudiant(Connection con, String nom)throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from Etudiant where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    
    public static void deleteEtudiant(Connection con,String nom) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement( 
        "delete from Etudiant where nom = ?")){
        pst.setString(1, nom);
        pst.executeUpdate();   
        //con.commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void ModifEmailEtudiant (Connection con, String nom, String emailModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Etudiant
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
    
    public static void ModifmdpEtudiant (Connection con, String nom, String mdpModifie) throws SQLException
    {
        try ( PreparedStatement pst = con.prepareStatement(
                """
                update Etudiant
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
    public static List<Etudiant> tousLesEtudiants(Connection con) throws SQLException {
        List<Etudiant> res = new ArrayList<>();
        try ( PreparedStatement pst = con.prepareStatement(
                """
               select *
                 from Etudiant
               """
        )) {
            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    res.add(new Etudiant(rs.getInt("id"),
                            rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("mdp")));
                }
                return res;
            }
        }
    }
        public void saveEtudiant(Connection con, Etudiant etudiant) {
        try{
            if (etudiant == null) {
                System.err.println("Contact is null. Are you sure you have connected your form to the application?");
                return;
            }
        tousLesEtudiants(con).add(etudiant);
            } catch (Exception ex) {
                System.out.println(ex);
            }
    }
            public static Optional<Etudiant> login(Connection con, String email, String pass) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where email = ? and mdp = ?")) {
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return Optional.of(new Etudiant(res.getInt("id"), email, pass));
            } else {
                return Optional.empty();
            }
        }
    }
}
