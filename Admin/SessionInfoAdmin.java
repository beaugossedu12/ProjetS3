/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Admin;

import fr.insa.zins.classe.Administrateur;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author sabin
 */
public class SessionInfoAdmin {
    private Optional<Administrateur> curUser;
    private Connection conBdD;

    public SessionInfoAdmin() {
        this.curUser = Optional.empty();
        this.conBdD = null;
    }

    public boolean userConnected() {
        return this.curUser.isPresent();
    }

    public Optional<Administrateur> getCurUser() {
        return this.curUser;
    }

    public void setCurUserA(Optional<Administrateur> curUser) {
        this.curUser = curUser;
    }

    public int getUserID() {
        return this.curUser.orElseThrow().getId();
    }

    /**
     * @return the conBdD
     */
    public Connection getConBdDA() {
        return conBdD;
    }

    /**
     * @param conBdD the conBdD to set
     */
    public void setConBdDA(Connection conBdD) {
        this.conBdD = conBdD;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return this.curUser.orElseThrow().getNom();
    }    
}

