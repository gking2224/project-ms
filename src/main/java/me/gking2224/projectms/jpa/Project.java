package me.gking2224.projectms.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table (name="PROJECT")
public class Project implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;

    private Long id;
    
    private String name;
    
    private String location;

    public Project() {
        super();
    }
    
    public Project(String name) {
        this.name = name;
    }

    public Project(long id, String name) {
        this(name);
        this.id = id;
    }


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "project_id")
    @JsonProperty("_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @JsonInclude
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
