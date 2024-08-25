///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.ou.pojo;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.io.Serializable;
//import java.util.Set;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
///**
// *
// * @author ADMIN
// */
//@Entity
//@Table(name = "role")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
//    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
//    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")})
//public class Role implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "name")
//    private String name;
//    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<User> userSet;
//
//    public Role() {
//    }
//
//    public Role(Integer id) {
//        this.id = id;
//    }
//
//    public Role(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @XmlTransient
//    public Set<User> getUserSet() {
//        return userSet;
//    }
//
//    public void setUserSet(Set<User> userSet) {
//        this.userSet = userSet;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Role)) {
//            return false;
//        }
//        Role other = (Role) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.ou.pojo.Role[ id=" + id + " ]";
//    }
//
//}
package com.ou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")
})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;  // Changed to Long
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> userSet;

    public Role() {
    }

    public Role(Long id) {  // Changed to Long
        this.id = id;
    }

    public Role(Long id, String name) {  // Changed to Long
        this.id = id;
        this.name = name;
    }

    public Long getId() {  // Changed to Long
        return id;
    }

    public void setId(Long id) {  // Changed to Long
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Role[ id=" + id + " ]";
    }
}
