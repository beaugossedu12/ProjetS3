/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import fr.insa.zins.testvaadin.ModifEtudiant.ModifEtudiant;
import fr.insa.zins.testvaadin.ModifGM.ModifGroupeModule;
import fr.insa.zins.testvaadin.ModifModule.ModifModule;
import fr.insa.zins.testvaadin.VuePrincipale;
import fr.insa.zins.testvaadin.test4;

import static fr.insa.zins.classe.bdd2.testConnect;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sabin
 */
public class Administrateur2 extends VerticalLayout {

    private VuePrincipale main;
    private Button etudiant;
    private Button module;
    private Button groupeModule;
     private final Button fichiertexte;
      private final Button voeux; 
    private TextField vtnom;
    public Administrateur2(VuePrincipale main) throws ClassNotFoundException{
        this.main = main;
       
        this.vtnom = new TextField("Nom du fichier");
            this.etudiant = new Button("Etudiant");
            this.add(this.etudiant);
            etudiant.setWidthFull();

            this.etudiant.addClickListener((e) -> {
                try ( Connection con = testConnect()) {
                    this.main.changeContenu(new ModifEtudiant(this.main));
                this.main.avancerBarre(this.main);  
                } catch (ClassNotFoundException|SQLException ex) {
                    throw new Error(ex);
                }
            });
            this.module = new Button("Module");
            this.add(this.module);
            module.setWidthFull();

            this.module.addClickListener((e) -> {
                try ( Connection con1 = testConnect()) {
                    this.main.changeContenu(new ModifModule(this.main));
                    this.main.avancerBarre(this.main);
                } catch (ClassNotFoundException|SQLException ex) {
                    throw new Error(ex);
                }
            });
            this.groupeModule = new Button("Groupe Module");
            this.add(this.groupeModule);
            groupeModule.setWidthFull();

            this.groupeModule.addClickListener((e) -> {
                try{
                this.main.changeContenu(new ModifGroupeModule(this.main));
                this.main.avancerBarre(this.main);
                }catch(ClassNotFoundException ex){
                    throw new Error(ex);
                }
            });
            this.add(this.vtnom);
             this.fichiertexte = new Button("Afficher le fichier texte");
            this.add(this.fichiertexte);
            fichiertexte.setWidthFull();
            fichiertexte.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            
            this.fichiertexte.addClickListener((e)->{
                try{

                    //FileWriter lu=new FileWriter(monFichier);
                    BufferedWriter fichier1=new BufferedWriter(new FileWriter(this.vtnom.getValue()+".txt", true));
                    //fichier1.write(creationFichier()); 
                    try ( Statement st = this.main.getConBdD().createStatement()) {
                      //  String sql = "select * from GroupeModule";
  
    
                       /* try(PreparedStatement pstmt = this.main.getConBdD().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);){
                          ResultSet rs = pstmt.executeQuery();
                            rs.last();*/
                            //fichier1.write("Groupes de Modules : "+rs.getRow());
                            fichier1.write("Groupes de Modules : "+GroupeModule.CompteGroupeModule(this.main.getConBdD()));
                            
                            fichier1.newLine();
                           //}
                        String sql2 = "select * from Module where idGM = '1'";
                         try(PreparedStatement pstmt = this.main.getConBdD().prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);){
                               ResultSet rs = pstmt.executeQuery();

                              rs.last();
                              fichier1.write("Modules : "+rs.getRow()); 
                                
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
                            fichier1.write(id+";");
                             // on peut accéder à une colonne par son nom

                            int choix1=res4.getInt("choix1");
                            int choix2=res4.getInt("choix2");
                            int choix3=res4.getInt("choix3");
                            fichier1.write(choix1+","+choix2+","+choix3);
                            res4.next();
                            int choix21=res4.getInt("choix1");
                            int choix22=res4.getInt("choix2");
                            int choix23=res4.getInt("choix3");
                            fichier1.write(";" + choix21+","+choix22+","+choix23);
                             res4.next();
                            int choix31=res4.getInt("choix1");
                            int choix32=res4.getInt("choix2");
                            int choix33=res4.getInt("choix3");
                            fichier1.write(";" + choix31+","+choix32+","+choix33);
                            
                            fichier1.newLine(); 
                        }
                    }
                    fichier1.write("FINCHOIX");
                    fichier1.newLine();
                    fichier1.write("COUTS");
                    fichier1.newLine();
                    fichier1.write("FINCOUTS");
                    fichier1.close();
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            Notification.show("Fichier enregistré");
            });

            this.voeux = new Button("Afficher les voeux des étudiants");
            this.add(this.voeux);
            this.voeux.addClickListener((e) ->{
              try {
                this.main.changeContenu(new test4(this.main));
                this.main.avancerBarre(this.main); 
                }catch(Exception ex){
                    throw new Error(ex);
                }
            });
            voeux.setWidthFull();
            voeux.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
    }

}
