/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.classe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;

/**
 *
 * @author sabin
 */
public class FichierTexte {
        void EnregistrerUnFichier(java.awt.event.ActionEvent evt,Connection con)
            throws SQLException {                                      

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
                        ResultSet res = st.executeQuery("select count(*) from GroupeModule");
                        fichier1.write("Nombre de groupe de module:"+res);
                        fichier1.newLine();
                        ResultSet res2 = st.executeQuery("select count(*) from Module where idGM = '1'");
                        fichier1.write("Nombre de choix pour chaque groupe de module:"+res); 
                        fichier1.newLine();
                        fichier1.write("MODULES");
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
                        ResultSet res4 = st.executeQuery(
                                """
                                select Etudiant.id, choix1,choix2, choix3,GroupeModule.id from Etudiant 
                                join Voeux on Etudiant.id=Voeux.idEtudiant 
                                join GroupeModule on GroupeModule.id=Voeux.idGM 
                                """);
                        // ligne d'avant inutile
                        while (res4.next()) {
                        // on peut accéder à une colonne par son nom
                            int id = res3.getInt("id");
                            int idGM = res3.getInt("idGM");

                            fichier1.write(id + ";" + idGM);
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
        
    } 
}
