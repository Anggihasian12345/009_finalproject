/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegawai.pegawaikantor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "pegawaikantor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pegawaikantor.findAll", query = "SELECT p FROM Pegawaikantor p"),
    @NamedQuery(name = "Pegawaikantor.findById", query = "SELECT p FROM Pegawaikantor p WHERE p.pegawaikantorPK.id = :id"),
    @NamedQuery(name = "Pegawaikantor.findByName", query = "SELECT p FROM Pegawaikantor p WHERE p.name = :name"),
    @NamedQuery(name = "Pegawaikantor.findByNik", query = "SELECT p FROM Pegawaikantor p WHERE p.pegawaikantorPK.nik = :nik")})
public class Pegawaikantor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PegawaikantorPK pegawaikantorPK;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    public Pegawaikantor() {
    }

    public Pegawaikantor(PegawaikantorPK pegawaikantorPK) {
        this.pegawaikantorPK = pegawaikantorPK;
    }

    public Pegawaikantor(int id, int nik) {
        this.pegawaikantorPK = new PegawaikantorPK(id, nik);
    }

    public PegawaikantorPK getPegawaikantorPK() {
        return pegawaikantorPK;
    }

    public void setPegawaikantorPK(PegawaikantorPK pegawaikantorPK) {
        this.pegawaikantorPK = pegawaikantorPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pegawaikantorPK != null ? pegawaikantorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pegawaikantor)) {
            return false;
        }
        Pegawaikantor other = (Pegawaikantor) object;
        if ((this.pegawaikantorPK == null && other.pegawaikantorPK != null) || (this.pegawaikantorPK != null && !this.pegawaikantorPK.equals(other.pegawaikantorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pegawai.pegawaikantor.Pegawaikantor[ pegawaikantorPK=" + pegawaikantorPK + " ]";
    }
    
}
