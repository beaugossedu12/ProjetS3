/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import fr.insa.zins.classe.Etudiant.EtudiantAlreadyExistsException;
import static fr.insa.zins.classe.Etudiant.trouveEtudiant;
import fr.insa.zins.classe.GroupeModule.GroupeModuleAlreadyExistsException;
import static fr.insa.zins.classe.GroupeModule.afficheTousGroupeModule;
import static fr.insa.zins.classe.GroupeModule.deleteGroupeModule;
import static fr.insa.zins.classe.GroupeModule.trouveGroupeModule;
import static fr.insa.zins.classe.Module.ModifDescriptionModule;
import static fr.insa.zins.classe.Module.ModifNomModule;
import fr.insa.zins.classe.Module.ModuleAlreadyExistsException;
import static fr.insa.zins.classe.Module.afficheTousModules;
import static fr.insa.zins.classe.Module.deleteModule;
import static fr.insa.zins.classe.Module.trouveModule;
import fr.insa.zins.classe.Personne.PersonneAlreadyExistsException;
import static fr.insa.zins.classe.Personne.afficheToutesPersonnes;
import static fr.insa.zins.classe.Personne.deletePersonne;
import static fr.insa.zins.classe.Personne.trouvePersonne;
import fr.insa.zins.utils.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sabin
 */
public class bdd2 {
public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
   public static Connection testConnect() {
        Connection con = null;
        try {
            // teste la pr�sence du driver postgresql
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");

            con.setAutoCommit(false);
                        con.commit();
        } catch (Exception ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {}
            }
            throw new Error(ex);
        }
        return con;

    }
    
//...

    /*public static void main1(String[] args) {
        try ( Connection con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "mypass")) {
            // testConnection(con);  // ici le programme
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Error(ex);
        }
    }*/

    public static void createTablePersonne(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Personne(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 prenom varchar(50),
                 email varchar(50),
                 mdp varchar(50) 
               )
               """);
        }
    }

    public static void createTableEtudiant(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Etudiant(
                 id integer primary key generated always as identity,
               )
               """);
        }
    }
    public static void createTableAdministrateur(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Administrateur(
                 id integer primary key generated always as identity,
               )
               """);
        }
    }
    public static void createTableModule(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Module(
                 id integer primary key generated always as identity,
                 nom varchar (50),
                 description TEXT   
               )
               """);
        }
    }
     public static void createTableGroupeModule(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table GroupeModule(
                 id integer primary key generated always as identity,
                 nom varchar (50),
                 description text   
               )
               """);
        }
    }   
    public static void createTableVoeux(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Voeux(
                 id integer primary key generated always as identity,
                 choix1 INTEGER,
                 choix2 INTEGER,
                 choix3 INTEGER
               )
               """);
        }
    } 
    public static void createSchema(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            // on veut que le schema soit entierement créé ou pas du tout
            // il nous faut plusieurs ordres pour créer le schema
            // on va donc explicitement gérer les connections
            con.setAutoCommit(false);
            st.executeUpdate(
                    """
               create table Personne(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                prenom varchar(50),
                email varchar(50),
                mdp varchar(50) 
                
               )
               """);
            st.executeUpdate(
                    """
               create table GroupeModule(
                id integer primary key generated always as identity,
                nom varchar (50),
                description text                   
               )
               """);
            st.executeUpdate(
                    """
               create table Module(
                id integer primary key generated always as identity,
                nom varchar (50),
                description text,
                idGM integer not null
              )
               """);
            st.executeUpdate(
                    """
               alter table Module 
                 add constraint fk_idGM_id
                 foreign key(idGM)
                 references GroupeModule(id)
                   on delete restrict
                   on update restrict
               """);
            

            st.executeUpdate(
                    """
               create table Etudiant(
                id integer primary key generated always as identity,
               idPersonne INTEGER not null
              )
               """);
            st.executeUpdate(
                    """
               alter table Etudiant 
                 add constraint fk_idPersonne_id
                 foreign key(idPersonne)
                 references Personne(id)
                   on delete restrict
                   on update restrict
               """);
            st.executeUpdate( 
                    """
               create table Administrateur( 
               id integer primary key generated always as identity,
               idPersonne integer not null
              )
               """);
            st.executeUpdate(
                    """
               alter table Administrateur 
                 add constraint fk_idPersonne_id
                 foreign key(idPersonne)
                 references Personne(id)
                   on delete restrict
                   on update restrict
               """);
            
            st.executeUpdate(
                    """
               create table Voeux(
                id integer primary key generated always as identity,
                idEtudiant INTEGER not null,
                idGM INTEGER not null, 
                choix1 INTEGER,
                choix2 INTEGER,
                choix3 INTEGER
               )
               """);
            st.executeUpdate(
                    """
               alter table Voeux 
                 add constraint fk_idEtudiant_id
                 foreign key(idEtudiant)
                 references Etudiant(id)
                   on delete restrict
                   on update restrict
               """);
            
            st.executeUpdate(
                    """
               alter table Voeux 
                 add constraint fk_idGM_id
                 foreign key(idGM)
                 references GroupeModule(id)
                   on delete restrict
                   on update restrict
               """);
            // si j'arrive ici, c'est que tout s'est bien passé
            // je valide la transaction
            con.commit();
        } catch (SQLException ex) {
            // si quelque chose se passe mal, j'annule la transaction
            // avant de resignaler l'exception
            con.rollback();
            throw ex;
        } finally {
            // pour s'assurer que le autoCommit(true) reste le comportement
            // par défaut (utile dans la plupart des "select"
            con.setAutoCommit(true);
        }
    }

    public static void deleteSchema(Connection con) throws SQLException
    {
        try ( Statement st = con.createStatement()) 
        {
            con.setAutoCommit(false);
            // pour être sûr de pouvoir supprimer les tables,
            // le plus simple est de supprimer d'abord toutes
            // les contraintes
            st.executeUpdate(
                    """
               alter table Module 
                 drop constraint fk_idGM_id
               """);
            st.executeUpdate(
                    """
               alter table Etudiant 
                 drop constraint fk_idPersonne_id
               """);
            
            st.executeUpdate(
                    """
               alter table Administrateur 
                 drop constraint fk_idPersonne_id
               """);
            st.executeUpdate(
                    """
               alter table Voeux
                 drop constraint fk_idEtudiant_id
               """);
            
            st.executeUpdate(
                    """
               alter table Voeux
                 drop constraint fk_idGM_id
               """);
            st.executeUpdate("drop table Personne");
           st.executeUpdate("drop table Module");
           st.executeUpdate("drop table GroupeModule");
           st.executeUpdate("drop table Voeux");
           st.executeUpdate("drop table Etudiant");
           st.executeUpdate("drop table Administrateur");
           
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static int createPersonne(Connection con, String nom, String prenom, String email, String mdp)
            throws SQLException, PersonneAlreadyExistsException {
        if (trouvePersonne(con, nom) != -1) {
            throw new PersonneAlreadyExistsException(nom);
        }
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Personne (nom,prenom,email,mdp)
          values (?,?,?,?)
        """,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, mdp);            
            pst.executeUpdate();
            // ci dessous : retrouver l'identificateur qui vient d'être crée
            ResultSet nouvellesCles = pst.getGeneratedKeys();
            // ici, il n'y a qu'une nouvelle clé.
            // s'il y avait plusieurs objets créés, on pourrait retrouver tous
            // les id correspondants en incluant le "next" dans un while
            nouvellesCles.next();
            return nouvellesCles.getInt(1);
        }
    }
    
    public static int createModule(Connection con, String nom, String description, int idGM) //idGM doit correspondre a un groupeModule existant, cmt?
            throws SQLException, ModuleAlreadyExistsException {
        if (trouveModule(con, nom) != -1) {
            throw new ModuleAlreadyExistsException(nom);
        }

        try ( PreparedStatement pst = con.prepareStatement( 
                """
        insert into Module (nom,description,idGM) 
          values (?,?,?)
        """,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, nom);
            pst.setString(2,description);
            pst.setInt(3, idGM);
           
            pst.executeUpdate();
            // ci dessous : retrouver l'identificateur qui vient d'être crée
            ResultSet nouvellesCles = pst.getGeneratedKeys();
            // ici, il n'y a qu'une nouvelle clé.
            // s'il y avait plusieurs objets créés, on pourrait retrouver tous
            // les id correspondants en incluant le "next" dans un while
            nouvellesCles.next();
            return nouvellesCles.getInt(1);
        }
    }
    //pas demandé
        public static int createGroupeModule(Connection con, String nom, String description) 
            throws SQLException, GroupeModuleAlreadyExistsException {
        if (trouveGroupeModule(con, nom) != -1) {
            throw new GroupeModuleAlreadyExistsException(nom);
        }

        try ( PreparedStatement pst = con.prepareStatement( 
                """
        insert into GroupeModule (nom,description) 
          values (?,?)
        """,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, nom);
            pst.setString(2,description);
            
           
            pst.executeUpdate();
            // ci dessous : retrouver l'identificateur qui vient d'être crée
            ResultSet nouvellesCles = pst.getGeneratedKeys();
            // ici, il n'y a qu'une nouvelle clé.
            // s'il y avait plusieurs objets créés, on pourrait retrouver tous
            // les id correspondants en incluant le "next" dans un while
            nouvellesCles.next();
            return nouvellesCles.getInt(1);
        }
    }
    public static int createEtudiant(Connection con, String nom, String prenom, String email, String mdp)
            throws SQLException, EtudiantAlreadyExistsException {
        if (trouveEtudiant(con, nom) != -1) {
            throw new EtudiantAlreadyExistsException(nom);
        }
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Personne (nom,prenom,email,mdp)
          values (?,?,?,?)
        """,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, mdp);            
            pst.executeUpdate();
            // ci dessous : retrouver l'identificateur qui vient d'être crée
            ResultSet nouvellesCles = pst.getGeneratedKeys();
            // ici, il n'y a qu'une nouvelle clé.
            // s'il y avait plusieurs objets créés, on pourrait retrouver tous
            // les id correspondants en incluant le "next" dans un while
            nouvellesCles.next();
            try(PreparedStatement pst1 = con.prepareStatement(
                """
                insert into Etudiant (idPersonne)
                   values (?)
                """,PreparedStatement.RETURN_GENERATED_KEYS)) {
                
                pst.setInt(1, nouvellesCles.getInt("Personne.id"));
               
            }
            return nouvellesCles.getInt(1);
        }
        
    }


    public static void creeDonneesTest(Connection con) throws SQLException {
        String[][] donnees = new String[][]{
            // forme : {nom,prenom, email,mdp}
            {"toto", "titi","email1","mdp1"},
            {"Marley", "bob", "email2","mdp2"},
            {"SansSurnom", "bob", "email3","mdp3"}};
        for (String[] p : donnees) {
            //java.sql.Date d = java.sql.Date.valueOf(p[1]);
            try {
                createPersonne(con, p[0],p[1],p[2],p[3]);

            } catch (PersonneAlreadyExistsException ex) {
                throw new Error(ex);
            }
        }
        String [][] donneesGroupeModule = new String[][]{
            //forme :{nom,description
            {"groupemodule1","description gmodule1"},
            {"groupemodule2","description gmodule2"},
            {"groupemodule3","description gmodule3"} };
        
        for (String[] p : donneesGroupeModule) {
            try {
                createGroupeModule(con, p[0],p[1]);

            } catch (GroupeModuleAlreadyExistsException ex) {
                throw new Error(ex);
            }
        }
        String [][] donneesModule = new String[][]{
            //forme :{nom,description, idgm
            {"module1","description module1","1"},
            {"module2","description module2","1"},
            {"module3","description module3","2"}, 
            {"module4",null,"2"}};
        
        for (String[] p : donneesModule) {
            //java.sql.Date d = java.sql.Date.valueOf(p[1]);
            try {
                createModule(con, p[0],p[1],Integer.parseInt(p[2]));

            } catch (ModuleAlreadyExistsException ex) {
                throw new Error(ex);
            }
        }
    }


    public static void recreeTout(Connection con) throws SQLException {
        try {
            deleteSchema(con);
        } catch (SQLException ex) {
            System.out.println("Schema non supprimé : première création ?");
        }
        createSchema(con);
        creeDonneesTest(con);
    }

    public static void afficheCorrespondances(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    """
                    select nom,surnom from Personne
                      join PersonSurnoms on Personne.id = idPersonne
                      join Surnom on Surnom.id = idSurnom
                    order by nom,surnom
                    """);
            while (res.next()) {
                String nom = res.getString(1);
                String surnom = res.getString(2);
                System.out.println(nom + " : " + surnom);
            }
        }

    }

    public static void menuPrincipal(Connection con) throws SQLException {
        int rep = -1;
        while (rep != 0) {
            System.out.println("Menu Principal");
            System.out.println("--------------");
            System.out.println("1) (re)créer toute la BdD");
            System.out.println("2) ajouter une personne");
            System.out.println("3) ajouter un module");
            System.out.println("4) ajouter un groupe de module");
            System.out.println("5) afficher toutes les personnes");
            System.out.println("6) afficher tous les modules");
            System.out.println("7) afficher toutes les groupes de modules");
            System.out.println("8) tout supprimer");
            System.out.println("9) modifier nom d'un module"); //marche pas 
            //System.out.println("10) trouver id module : ");
            System.out.println("11) effacer un module");
            System.out.println("12) effacer une personne");
            System.out.println("13) effacer un groupe de module");
            System.out.println("14) modifier description d'un module");
            System.out.println("15) ajouter un étudiant");
            rep = Console.entreeInt("Votre choix : ");

           if (rep == 1) {
                recreeTout(con);
           }else if (rep == 2) {
                String nom = Console.entreeString("nom : ");
                String prenom = Console.entreeString("prenom :");
                String email = Console.entreeString("email : ");
                String mdp = Console.entreeString("mdp: ");
                try {
                    createPersonne(con, nom, prenom,email,mdp);
                } catch (PersonneAlreadyExistsException ex) {
                    System.out.println("Impossible : le nom existe déjà");
                }
                
            } else if (rep == 3) {
                String nom = Console.entreeString("nom : ");
                String description = Console.entreeString("description : ");
                int idGM= Console.entreeInt("idGM : ");
                try {
                    createModule(con, nom, description,idGM);
                } catch (ModuleAlreadyExistsException ex) {
                    System.out.println("Impossible : le module existe déjà");
                }
                
            } else if (rep == 4) {
                String nom = Console.entreeString("nom : ");
                String description = Console.entreeString("description : ");
                try {
                    createGroupeModule(con, nom, description);
                } catch (GroupeModuleAlreadyExistsException ex) {
                    System.out.println("Impossible : le groupe de module existe déjà");
                }
            } else if (rep == 5) {
                afficheToutesPersonnes(con);
            } else if (rep == 6) {
                afficheTousModules(con);
            }else if (rep == 7) {
                afficheTousGroupeModule(con);
            }else if (rep == 8) {
                try {
                    deleteSchema(con);
                } catch (Exception ex) {
                    System.out.println("Impossible d'effacer le schema");
                }
            }else if (rep ==9){
                String nom = Console.entreeString("nom : ");
                String nomModifier = Console.entreeString("nom modifié : ");
                try{
                ModifNomModule(con, nom,nomModifier);
                } catch (Exception ex) {
                    System.out.println("Impossible de modifier le nom du module");
                }
            }else if (rep ==10){
                String nom = Console.entreeString("nom : ");
                trouveModule(con,nom);
            }else if (rep ==11){
                String nom = Console.entreeString("nom : ");
                deleteModule(con,nom);
            }else if (rep ==12){
                String nom = Console.entreeString("nom : ");
                deletePersonne(con,nom);
            }else if (rep ==13){
                String nom = Console.entreeString("nom : ");
                deleteGroupeModule(con,nom);
            }else if (rep ==14){
                String nom = Console.entreeString("nom : ");
                String description = Console.entreeString("description à ajouter : ");
                ModifDescriptionModule(con, nom, description);   
            }else if (rep ==15){
                String nom = Console.entreeString("nom : ");
                String prenom = Console.entreeString("prenom :");
                String email = Console.entreeString("email : ");
                String mdp = Console.entreeString("mdp: ");
                try {
                    createEtudiant(con, nom, prenom,email,mdp);
                } catch (EtudiantAlreadyExistsException ex) {
                    System.out.println("Impossible : le nom existe déjà");
                }
            }
        }
    }

    public static void main(String[] args) {
       try ( Connection con = testConnect()) {

                //createPersonne(con,"Zins","Sabine","sabine.zins@insa-strasbourg.fr", "pass");
            menuPrincipal(con);
        } catch (Exception ex) {
            throw new Error("Probleme SQL : " + ex.getMessage(), ex);
       }
       
    }


       
}
