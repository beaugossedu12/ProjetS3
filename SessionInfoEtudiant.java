/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import fr.insa.zins.classe.Etudiant;
import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author sabin
 */
public class SessionInfoEtudiant {

    private Optional<Etudiant> curUser;
    private Connection conBdD;

    public SessionInfoEtudiant() {
        this.curUser = Optional.empty();
        this.conBdD = null;
    }

    public boolean userConnected() {
        return this.curUser.isPresent();
    }

    public Optional<Etudiant> getCurUser() {
        return this.curUser;
    }

    public void setCurUser(Optional<Etudiant> curUser) {
        this.curUser = curUser;
    }

    public int getUserID() {
        return this.curUser.orElseThrow().getId();
    }

    /**
     * @return the conBdD
     */
    public Connection getConBdDE() {
        return conBdD;
    }

    /**
     * @param conBdD the conBdD to set
     */
    public void setConBdDE(Connection conBdD) {
        this.conBdD = conBdD;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return this.curUser.orElseThrow().getNom();
    }    
}
