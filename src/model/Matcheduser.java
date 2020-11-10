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
@Table(name = "MATCHEDUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matcheduser.findAll", query = "SELECT m FROM Matcheduser m")
    , @NamedQuery(name = "Matcheduser.findById", query = "SELECT m FROM Matcheduser m WHERE m.id = :id")
    , @NamedQuery(name = "Matcheduser.findByName", query = "SELECT m FROM Matcheduser m WHERE m.name = :name")
    , @NamedQuery(name = "Matcheduser.findByMatchsince", query = "SELECT m FROM Matcheduser m WHERE m.matchsince = :matchsince")
    , @NamedQuery(name = "Matcheduser.findNamesContaining", query = "SELECT m FROM Matcheduser m WHERE lower(m.name) LIKE :name")
    , @NamedQuery(name = "Matcheduser.findMatchedBeforeDate", query = "SELECT m FROM Matcheduser m WHERE m.matchsince < :matchsince")})
public class Matcheduser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "MATCHSINCE")
    @Temporal(TemporalType.DATE)
    private Date matchsince;

    public Matcheduser() {
    }

    public Matcheduser(Integer id) {
        this.id = id;
    }

    public Matcheduser(Integer id, String name, Date matchsince) {
        this.id = id;
        this.name = name;
        this.matchsince = matchsince;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMatchsince() {
        return matchsince;
    }

    public void setMatchsince(Date matchsince) {
        this.matchsince = matchsince;
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
        if (!(object instanceof Matcheduser)) {
            return false;
        }
        Matcheduser other = (Matcheduser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Matcheduser[ id=" + id + " ]";
    }
    
}
