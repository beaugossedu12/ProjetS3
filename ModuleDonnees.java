/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import static fr.insa.zins.classe.bdd2.createAdministrateur;
import static fr.insa.zins.classe.bdd2.createGroupeModule;
import static fr.insa.zins.classe.bdd2.createModule;
import static fr.insa.zins.classe.bdd2.createVoeux;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sabin
 */
public class ModuleDonnees {
    public static List<Module> ensureTestDataM(Connection con) throws SQLException{
		//Connection con = testConnect();
                int id=0;
                int idGM=0;
                String nom=null;
                String description=null;
                
	        String [][] donneesModule = new String[][]{
            //forme :{nom,description, idgm
            {"Algèbre","description module1","1"},
            {"Analyse","description module2","1"},
            {"Physique","description module3","1"},
            {"Musique","description module4","2"}, 
            {"Danse",null,"2"},
            {"Théâtre",null,"2"},
            {"Kayak","description module7","3"},
            {"Aviron","description module8","3"},
            {"Course à pied","description module9","3"}};
            
        List<Module> ModuleList = new ArrayList();
        for (String[] p : donneesModule) {
            Module c = new Module(id,idGM,nom,description);
            c.setNom(p[0]);
            c.setDescription(p[1]);
            c.setIdGM(Integer.parseInt(p[2]));
            try {
                int id1=createModule(con, p[0],p[1],Integer.parseInt(p[2]));
                Module c1 = new Module(id1,c.getIdGM(),c.getNom(),c.getDescription());
                ModuleList.add(c1);
            } catch (Exception ex) {
                throw new Error(ex);
            }
             
        }
	return ModuleList;		
                        
			
            }
        public static List<GroupeModule> ensureTestDataGM(Connection con) throws SQLException{
		//Connection con = testConnect();
                int id=0;
               
                String nom=null;
                String description=null;
                
	        String [][] donneesGroupeModule = new String[][]{
            //forme :{nom,description, idgm
            {"Science","description gmodule1"},
            {"Art","description gmodule2"},
            {"Sport","description gmodule3"} };
            
        List<GroupeModule> GroupeModuleList = new ArrayList();
        for (String[] p : donneesGroupeModule) {
            GroupeModule c = new GroupeModule(id,nom,description);
            c.setNom(p[0]);
            c.setDescription(p[1]);
           
            try {
                int id1=createGroupeModule(con, p[0],p[1]);
                GroupeModule c1 = new GroupeModule(id1,c.getNom(),c.getDescription());
                GroupeModuleList.add(c1);
            } catch (Exception ex) {
                throw new Error(ex);
            }
             
        }
	return GroupeModuleList;
        }
        
        public static List<Voeux> ensureTestDataV(Connection con) throws SQLException{
		//Connection con = testConnect();
                int id=0;
                int idEtudiant = 0;
                int idGM=0;
                int choix1=0;
                int choix2=0;
                int choix3=0;
                
	      String [][] donneesVoeux = new String[][]{
            //forme :{idEtudiant,idGM, choix1,choix2,choix3
            {"1","1","1","2","3"},
            {"1","2","4","5","6"},
            {"1","3","7","8","9"},
            {"2","1","2","1","3"}, 
            {"2","2","5","4","6"},
            {"2","3","7","9","8"}};
         List<Voeux> VoeuxList = new ArrayList(); 
        for (String[] p : donneesVoeux) {
             Voeux c = new Voeux(id,idGM,idEtudiant, choix1,choix2,choix3);
            c.setIdEtudiant(Integer.parseInt(p[0]));
            c.setIdGM(Integer.parseInt(p[1]));
            c.setChoix1(Integer.parseInt(p[2]));
            c.setChoix2(Integer.parseInt(p[3]));
            c.setChoix3(Integer.parseInt(p[4]));
            try {
            int id1=createVoeux(con,Integer.parseInt(p[0]),Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4]));
                Voeux c1 = new Voeux(id1,c.getIdGM(),c.getIdEtudiant(),c.getChoix1(),c.getChoix2(),c.getChoix3());
                VoeuxList.add(c1);
            } catch (Exception ex) {
                throw new Error(ex);
            }    

        }

	return VoeuxList;
        }
        public static List<Administrateur> ensureTestDataA(Connection con) throws SQLException{
                 int id=0;
                String nom=null;
                String prenom=null;
                String email=null;
                String mdp=null;
		        String[][] donneesAdmin = new String[][]{
            // forme : {nom,prenom, email,mdp}
            {" admin1", "admin","email1","mdp1"}};
          List<Administrateur> AdminList = new ArrayList(); 

        for (String[] p : donneesAdmin) {
           Administrateur c = new Administrateur(id,nom,prenom,email,mdp);
            c.setPrenom(p[0]);
            c.setNom(p[1]);
            c.setEmail(p[2]);
	     c.setMdp(p[3]);;
            try {
                int id1=createAdministrateur(con, p[0],p[1],p[2],p[3]);
                Administrateur c1 = new Administrateur(id1,c.getNom(),c.getPrenom(),c.getEmail(),c.getMdp());
                AdminList.add(c1);
            } catch (Administrateur.AdministrateurAlreadyExistsException ex) {
                throw new Error(ex);
            }
        }
        return AdminList;
        }

}
