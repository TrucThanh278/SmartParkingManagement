/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OU
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "payment_status")
    private Boolean paymentStatus;
    @JsonIgnore
    @OneToOne(mappedBy = "bookingInfoId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Report report;

    @JoinColumn(name = "parking_spot_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ParkingSpot parkingSpotId;

    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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

    public boolean isSpotOccupied(LocalDateTime now) {
        LocalDateTime x = now;
        LocalDateTime a = this.endTime;
        LocalDateTime b = this.startTime;
        return !now.isAfter(a) && !now.isBefore(b);
    }

    @Override
    public String toString() {
        return "com.ou.pojo.BookingInformation[ id=" + id + " ]";
    }
    

public boolean isWithinParkingLotHours(ParkingLot parkingLot) {
    LocalTime parkingLotStartTime = parkingLot.getStartTime();
    LocalTime parkingLotEndTime = parkingLot.getEndTime();

    LocalTime bookingStartTime = this.startTime.toLocalTime();
    LocalTime bookingEndTime = this.endTime.toLocalTime();

    // Handle the case where parking lot hours span over midnight
    if (parkingLotEndTime.isBefore(parkingLotStartTime)) {
        // Parking lot closes after midnight
        return (bookingStartTime.isAfter(parkingLotStartTime) || bookingStartTime.equals(parkingLotStartTime)) &&
               (bookingEndTime.isBefore(parkingLotEndTime.plusHours(24)) || bookingEndTime.equals(parkingLotEndTime.plusHours(24)));
    } else {
        // Parking lot hours do not span over midnight
        return (bookingStartTime.isAfter(parkingLotStartTime) || bookingStartTime.equals(parkingLotStartTime)) &&
               (bookingEndTime.isBefore(parkingLotEndTime) || bookingEndTime.equals(parkingLotEndTime));
    }
}


}
