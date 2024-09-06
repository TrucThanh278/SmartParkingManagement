package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OU
 */
@Entity
@Table(name = "parking_lot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParkingLot.findAll", query = "SELECT p FROM ParkingLot p"),
    @NamedQuery(name = "ParkingLot.findById", query = "SELECT p FROM ParkingLot p WHERE p.id = :id"),
    @NamedQuery(name = "ParkingLot.findByName", query = "SELECT p FROM ParkingLot p WHERE p.name = :name"),
    @NamedQuery(name = "ParkingLot.findByAddress", query = "SELECT p FROM ParkingLot p WHERE p.address = :address"),
    @NamedQuery(name = "ParkingLot.findByTotalSpots", query = "SELECT p FROM ParkingLot p WHERE p.totalSpots = :totalSpots"),
    @NamedQuery(name = "ParkingLot.findByPricePerHour", query = "SELECT p FROM ParkingLot p WHERE p.pricePerHour = :pricePerHour"),
    @NamedQuery(name = "ParkingLot.findByStartTime", query = "SELECT p FROM ParkingLot p WHERE p.startTime = :startTime"),
    @NamedQuery(name = "ParkingLot.findByEndTime", query = "SELECT p FROM ParkingLot p WHERE p.endTime = :endTime")})
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Address cannot be blank")
    @NotBlank(message = "Address cannot be blank")
    @Column(name = "address")
    private String address;
    @NotNull(message = "Total spots cannot be blank")
    @Column(name = "total_spots")
    private Integer totalSpots;
    @NotNull(message = "Price per hour cannot be blank")
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price_per_hour")
    private Float pricePerHour;
    @Lob
    @Column(name = "description")
    private String description;
    @NotNull(message = "Start time cannot be blank")
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "start_time")
    private LocalTime startTime;
    @NotNull(message = "End time cannot be blank")
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "end_time")
    private LocalTime endTime;
    @OneToMany(mappedBy = "parkingLotId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ParkingSpot> parkingSpotList;

    public ParkingLot() {
    }

    public ParkingLot(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(Integer totalSpots) {
        this.totalSpots = totalSpots;
    }

    public Float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public void setParkingSpotList(List<ParkingSpot> parkingSpotList) {
        this.parkingSpotList = parkingSpotList;
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
        if (!(object instanceof ParkingLot)) {
            return false;
        }
        ParkingLot other = (ParkingLot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ParkingLot[ id=" + id + " ]";
    }
}
