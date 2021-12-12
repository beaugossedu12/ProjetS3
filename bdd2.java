/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import fr.insa.zins.classe.Administrateur.AdministrateurAlreadyExistsException;
import static fr.insa.zins.classe.Administrateur.trouveAdministrateur;
import fr.insa.zins.classe.Etudiant.EtudiantAlreadyExistsException;
import static fr.insa.zins.classe.Etudiant.afficheTousEtudiants;
import static fr.insa.zins.classe.Etudiant.deleteEtudiant;
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
import static fr.insa.zins.classe.Voeux.chargeVoeux;

import fr.insa.zins.utils.Console;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;

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

    public static void createTableEtudiant (Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Etudiant(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 prenom varchar(50),
                 email varchar(50),
                 mdp varchar(50) 
               )
               """);
        }
    }
    public static void createTableAdministrateur (Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Administrateur(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 prenom varchar(50),
                 email varchar(50),
                 mdp varchar(50) 
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
        public static void createTableSemestre(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
                create table Semestre(
                id integer primary key generated always as identity,
                annee integer not null,
               numero integer
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
               create table Etudiant(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                prenom varchar(50),
                email varchar(50),
                mdp varchar(50) 
                
               )
               """);
            st.executeUpdate(
                    """
               create table Semestre(
                 id integer primary key generated always as identity,
                 annee integer not null,
                numero integer
                
               )
               """);
            st.executeUpdate(
                    """
               create table Administrateur(
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
               alter table Voeux
                 drop constraint fk_idEtudiant_id
               """);
            
            st.executeUpdate(
                    """
               alter table Voeux
                 drop constraint fk_idGM_id
               """);

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

    public static int createEtudiant(Connection con, String nom, String prenom, String email, String mdp)
            throws SQLException, EtudiantAlreadyExistsException {
        if (trouveEtudiant(con, nom) != -1) {
            throw new EtudiantAlreadyExistsException(nom);
        }
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Etudiant (nom,prenom,email,mdp)
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
    public static int createAdministrateur(Connection con, String nom, String prenom, String email, String mdp)
            throws SQLException, AdministrateurAlreadyExistsException {
        if (trouveAdministrateur(con, nom) != -1) {
            throw new AdministrateurAlreadyExistsException(nom);
        }
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Administrateur (nom,prenom,email,mdp)
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
    public static int createVoeux(Connection con, int idEtudiant,int idGM,int choix1,int choix2,int choix3) 
            throws SQLException{


        try ( PreparedStatement pst = con.prepareStatement( 
                """
        insert into Voeux (idEtudiant,idGM,choix1,choix2,choix3) 
          values (?,?,?,?,?)
        """,PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, idEtudiant);
            pst.setInt(2,idGM);
            pst.setInt(3,choix1);
            pst.setInt(4,choix2);
            pst.setInt(5,choix3);
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



    public static void creeDonneesTest(Connection con) throws SQLException {
        String[][] donnees = new String[][]{
            // forme : {nom,prenom, email,mdp}
            {"toto", "titi","email1","mdp1"},
            {"Marley", "bob", "email2","mdp2"},
            {"SansSurnom", "bob", "email3","mdp3"}};
        for (String[] p : donnees) {
           
            try {
                createEtudiant(con, p[0],p[1],p[2],p[3]);

            } catch (EtudiantAlreadyExistsException ex) {
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
            {"module4",null,"2"},
            {"module5","description module5","1"},
            {"module6","description module6","2"}};
            
        
        for (String[] p : donneesModule) {
            
            try {
                createModule(con, p[0],p[1],Integer.parseInt(p[2]));

            } catch (ModuleAlreadyExistsException ex) {
                throw new Error(ex);
            }
        }
        String [][] donneesVoeux = new String[][]{
            //forme :{idEtudiant,idGM, choix1,choix2,choix3
            {"1","1","1","2","5"},
            {"1","2","4","3","6"},
            {"2","1","2","1","5"}, 
            {"2","2","3","4","6"}};
        
        for (String[] p : donneesVoeux) {
       
            createVoeux(con,Integer.parseInt(p[0]),Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4]));
                

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

    /*public static void afficheCorrespondances(Connection con) throws SQLException {
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

    }*/

    public static void menuPrincipal(Connection con) throws SQLException {
        int rep = -1;
        while (rep != 0) {
            System.out.println("Menu Principal");
            System.out.println("--------------");
            System.out.println("1) (re)créer toute la BdD");
            System.out.println("2) ajouter un etudiant");
            System.out.println("3) ajouter un module");
            System.out.println("4) ajouter un groupe de module");
            System.out.println("5) afficher tous le étudiants");
            System.out.println("6) afficher tous les modules");
            System.out.println("7) afficher toutes les groupes de modules");
            System.out.println("8) tout supprimer");
            System.out.println("9) modifier nom d'un module"); 
            //System.out.println("10) trouver id module : ");
            System.out.println("11) effacer un module");
            System.out.println("12) effacer un etudiant");
            System.out.println("13) effacer un groupe de module");
            System.out.println("14) modifier description d'un module");
            
            rep = Console.entreeInt("Votre choix : ");

           if (rep == 1) {
                recreeTout(con);
           }else if (rep == 2) {
                String nom = Console.entreeString("nom : ");
                String prenom = Console.entreeString("prenom :");
                String email = Console.entreeString("email : ");
                String mdp = Console.entreeString("mdp: ");
                try {
                    createEtudiant(con, nom, prenom,email,mdp);
                } catch (EtudiantAlreadyExistsException ex) {
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
                afficheTousEtudiants(con);
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
                deleteEtudiant(con,nom);
            }else if (rep ==13){
                String nom = Console.entreeString("nom : ");
                deleteGroupeModule(con,nom);
            }else if (rep ==14){
                String nom = Console.entreeString("nom : ");
                String description = Console.entreeString("description à ajouter : ");
                ModifDescriptionModule(con, nom, description);   
           
            }
        }
    }

   public static void test() {
       try ( Connection con = testConnect()) {
         JFileChooser filechoose = new JFileChooser();
        // Créer un JFileChooser
        
        filechoose.setCurrentDirectory(new File(".")); // Le répertoire
        //source du JFileChooser est le répertoire d'où est lancé
        //notre programme
        String approve = new String("ENREGISTRER");
        // Le bouton pour valider l'enregistrement portera la
        //mention ENREGSITRER
        int resultatEnregistrer = filechoose.showDialog(filechoose, approve); // Pour afficher le JFileChooser...
        if (resultatEnregistrer ==JFileChooser.APPROVE_OPTION) // Si l'utilisateur clique
        //sur le bouton ENREGSITRER
        { String monFichier= new String(filechoose.getSelectedFile().toString());
        // Récupérer le nom du fichier qu'il a spécifié
            if(monFichier.endsWith(".txt")|| monFichier.endsWith(".TXT")) {;}
        // Si ce nom de fichier finit par .txt ou .TXT, ne rien faire et passer à
        //a suite
             // else{(monFichier = monFichier+ ".txt");}
        // Sinon renommer le fichier pour qu'il porte l'extension .txt
            { 
//BufferedWriter etudiants=new BufferedWriter(new FileWriter("Etudiants.txt", true));
                try{

                    //FileWriter lu=new FileWriter(monFichier);
                    BufferedWriter fichier1=new BufferedWriter(new FileWriter("monFichier.txt", true));//new FileWriter("treillis.txt", true));
                    //fichier1.write(creationFichier()); 
                    try ( Statement st = con.createStatement()) {
                        String sql = "select * from GroupeModule";
  
    
                        try(PreparedStatement pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);){
                          ResultSet rs = pstmt.executeQuery();
                            rs.last();
                            fichier1.write("Nombre de groupe de module:"+rs.getRow());
                            fichier1.newLine();
                           }
                        String sql2 = "select * from Module where idGM = '1'";
                         try(PreparedStatement pstmt = con.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);){
                               ResultSet rs = pstmt.executeQuery();

                              rs.last();
                              fichier1.write("Nombre de choix pour chaque groupe de module:"+rs.getRow()); 
                              fichier1.newLine();
                         }
                        fichier1.write("MODULES");
                        fichier1.newLine();
                        ResultSet res3 = st.executeQuery("select * from Module");
                        while (res3.next()) {
                            int id = res3.getInt("id");
                            int idGM = res3.getInt("idGM");

                            fichier1.write(id + ";" + idGM);
                            fichier1.newLine();
                        }
                        fichier1.write("FINMODULES");
                        fichier1.newLine();
                        fichier1.write("CHOIX");
                        fichier1.newLine();

                        /*ResultSet res4 = st.executeQuery(
                                """
                                select Etudiant.id, choix1,choix2, choix3 from Etudiant 
                                join Voeux on Etudiant.id=Voeux.idEtudiant 
                                """);*/
                        ResultSet res4 = st.executeQuery("select * from Voeux");
                       
                        while (res4.next()){ 
                            int id = res4.getInt("idEtudiant");
                            fichier1.write(id);
                             // on peut accéder à une colonne par son nom
;
                            int choix1=res4.getInt("choix1");
                            int choix2=res4.getInt("choix2");
                            int choix3=res4.getInt("choix3");
                            fichier1.write(";" + choix1+","+choix2+","+choix3);
                            res4.next();
                            int choix21=res4.getInt("choix1");
                            int choix22=res4.getInt("choix2");
                            int choix23=res4.getInt("choix3");
                            fichier1.write(";" + choix21+","+choix22+","+choix23);
                           
                            
                            fichier1.newLine(); 
                        }
                    }
                    fichier1.write("FINCHOIX");
                    fichier1.newLine();
                    fichier1.write("COUTS");
                    fichier1.newLine();
                    fichier1.write("FINCOUTS");
                    fichier1.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    
                //createPersonne(con,"Zins","Sabine","sabine.zins@insa-strasbourg.fr", "pass");
            menuPrincipal(con);
        } catch (Exception ex) {
            throw new Error("Probleme SQL : " + ex.getMessage(), ex);
       }
       
    }


       
}
