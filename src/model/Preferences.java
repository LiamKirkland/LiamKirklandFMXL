/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LeemKirk
 */
@Entity
@Table(name = "PREFERENCES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferences.findAll", query = "SELECT p FROM Preferences p")
    , @NamedQuery(name = "Preferences.findById", query = "SELECT p FROM Preferences p WHERE p.id = :id")
    , @NamedQuery(name = "Preferences.findByLastupdated", query = "SELECT p FROM Preferences p WHERE p.lastupdated = :lastupdated")
    , @NamedQuery(name = "Preferences.findByPreftype", query = "SELECT p FROM Preferences p WHERE p.preftype = :preftype")})
public class Preferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "LASTUPDATED")
    @Temporal(TemporalType.DATE)
    private Date lastupdated;
    @Basic(optional = false)
    @Column(name = "PREFTYPE")
    private String preftype;

    public Preferences() {
    }

    public Preferences(Integer id) {
        this.id = id;
    }

    public Preferences(Integer id, Date lastupdated, String preftype) {
        this.id = id;
        this.lastupdated = lastupdated;
        this.preftype = preftype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getPreftype() {
        return preftype;
    }

    public void setPreftype(String preftype) {
        this.preftype = preftype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferences)) {
            return false;
        }
        Preferences other = (Preferences) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Preferences[ id=" + id + " ]";
    }
    
}
