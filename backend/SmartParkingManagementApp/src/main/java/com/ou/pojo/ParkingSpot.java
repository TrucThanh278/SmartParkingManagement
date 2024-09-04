/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OU
 */
@Entity
@Table(name = "parking_spot")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ParkingSpot.findAll", query = "SELECT p FROM ParkingSpot p"),
        @NamedQuery(name = "ParkingSpot.findById", query = "SELECT p FROM ParkingSpot p WHERE p.id = :id"),
        @NamedQuery(name = "ParkingSpot.findBySpotNumber", query = "SELECT p FROM ParkingSpot p WHERE p.spotNumber = :spotNumber"),
        @NamedQuery(name = "ParkingSpot.findByStatus", query = "SELECT p FROM ParkingSpot p WHERE p.status = :status") })
public class ParkingSpot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "spot_number")
    private String spotNumber;
    @Column(name = "status")
    private Boolean status;
    @JsonIgnore
    @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
    @ManyToOne
    private ParkingLot parkingLotId;
    @JsonIgnore
    @OneToMany(mappedBy = "parkingSpotId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookingInformation> bookingInformationList;

    public ParkingSpot() {
    }

    public ParkingSpot(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ParkingLot getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(ParkingLot parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    @XmlTransient
    public List<BookingInformation> getBookingInformationList() {
        return bookingInformationList;
    }

    public void setBookingInformationList(List<BookingInformation> bookingInformationList) {
        this.bookingInformationList = bookingInformationList;
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
        if (!(object instanceof ParkingSpot)) {
            return false;
        }
        ParkingSpot other = (ParkingSpot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ParkingSpot[ id=" + id + " ]";
    }

}
