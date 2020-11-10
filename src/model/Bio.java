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
@Table(name = "BIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bio.findAll", query = "SELECT b FROM Bio b")
    , @NamedQuery(name = "Bio.findById", query = "SELECT b FROM Bio b WHERE b.id = :id")
    , @NamedQuery(name = "Bio.findByContents", query = "SELECT b FROM Bio b WHERE b.contents = :contents")
    , @NamedQuery(name = "Bio.findByLastupdated", query = "SELECT b FROM Bio b WHERE b.lastupdated = :lastupdated")})
public class Bio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CONTENTS")
    private String contents;
    @Basic(optional = false)
    @Column(name = "LASTUPDATED")
    @Temporal(TemporalType.DATE)
    private Date lastupdated;

    public Bio() {
    }

    public Bio(Integer id) {
        this.id = id;
    }

    public Bio(Integer id, String contents, Date lastupdated) {
        this.id = id;
        this.contents = contents;
        this.lastupdated = lastupdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
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
        if (!(object instanceof Bio)) {
            return false;
        }
        Bio other = (Bio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bio[ id=" + id + " ]";
    }
    
}
