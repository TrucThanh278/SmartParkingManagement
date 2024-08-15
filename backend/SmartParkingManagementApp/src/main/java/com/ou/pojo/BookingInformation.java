/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trucn
 */
@Entity
@Table(name = "booking_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingInformation.findAll", query = "SELECT b FROM BookingInformation b"),
    @NamedQuery(name = "BookingInformation.findById", query = "SELECT b FROM BookingInformation b WHERE b.id = :id"),
    @NamedQuery(name = "BookingInformation.findByStartTime", query = "SELECT b FROM BookingInformation b WHERE b.startTime = :startTime"),
    @NamedQuery(name = "BookingInformation.findByEndTime", query = "SELECT b FROM BookingInformation b WHERE b.endTime = :endTime"),
    @NamedQuery(name = "BookingInformation.findByPaymentStatus", query = "SELECT b FROM BookingInformation b WHERE b.paymentStatus = :paymentStatus")})
public class BookingInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "payment_status")
    private Boolean paymentStatus;
    @OneToOne(mappedBy = "bookingInfoId",fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    private Report report;
    @JoinColumn(name = "parking_spot_id", referencedColumnName = "id")
    @ManyToOne
    private ParkingSpot parkingSpotId;
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @ManyToOne
    private Vehicle vehicleId;

    public BookingInformation() {
    }

    public BookingInformation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ParkingSpot getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(ParkingSpot parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
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
        if (!(object instanceof BookingInformation)) {
            return false;
        }
        BookingInformation other = (BookingInformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.BookingInformation[ id=" + id + " ]";
    }
    
}
