/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pegawai.pegawaikantor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Lenovo
 */
@Embeddable
public class PegawaikantorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "nik")
    private int nik;

    public PegawaikantorPK() {
    }

    public PegawaikantorPK(int id, int nik) {
        this.id = id;
        this.nik = nik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) nik;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PegawaikantorPK)) {
            return false;
        }
        PegawaikantorPK other = (PegawaikantorPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.nik != other.nik) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pegawai.pegawaikantor.PegawaikantorPK[ id=" + id + ", nik=" + nik + " ]";
    }
    
}
