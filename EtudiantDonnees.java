/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import com.vaadin.flow.component.grid.Grid;

import static fr.insa.zins.classe.bdd2.createEtudiant;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sabin
 */
public class EtudiantDonnees {//extends Grid<Etudiant>{
    	public static List<Etudiant> ensureTestData(Connection con) throws SQLException{
		//Connection con = testConnect();
                int id=0;
                String nom=null;
                String prenom=null;
                String email=null;
                String mdp=null;
			final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
					"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
					"Emily Stewart", "Corinne Davise", "Ryann Davis", "Yurem Jackson", "Kelly Gustavssone",
					"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
					"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martine",
					"Ann Andersson", "Remington Anderssone", "Rene Carlssone", "Elvis Olsen", "Solomon Olsene",
					"Jaydan Jacksone", "Bernard Nilsen" };
			//Random r = new Random(0);
                        List<Etudiant> EtudiantList = new ArrayList();
			for (String name : names) {
				String[] split = name.split(" ");
				Etudiant c = new Etudiant(id,nom,prenom,email,mdp);
				c.setPrenom(split[0]);
				c.setNom(split[1]);
				c.setEmail(split[0].toLowerCase() + "." + split[1].toLowerCase() + "@insa-strasbourg.fr");
				c.setMdp("pass");
                                
                            try {
                                int id1 =createEtudiant(con, split[1],split[0],split[0].toLowerCase() + "." + split[1].toLowerCase() + "@insa-strasbourg.fr","pass");
                                //createEtudiant(con, c1.getNom(),c1.getPrenom(),c1.getEmail(),c1.getMdp());
                                Etudiant c1 = new Etudiant(id1,c.getNom(),c.getPrenom(),c.getEmail(),c.getMdp());
                              EtudiantList.add(c1);
                            } catch (SQLException ex) {
                                 throw new Error(ex);
                             }
               // int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
                //c.setBirthDate(LocalDate.now().plusDays(daysOld));
				//save(c);*
                               
                                
			}
                        return EtudiantList;
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

                } catch (SQLException ex) {
                    throw new Error(ex);
                }
            }
         }
    
}

