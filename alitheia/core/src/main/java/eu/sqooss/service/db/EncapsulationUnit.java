package eu.sqooss.service.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A unit of data encapsulation. Until further notice, this only makes sense in
 * object oriented languages, where it represents a class. An encapsulation unit
 * is linked to a namespace (by a namespace/package declaration, depending on
 * the language) and a file (where it is defined).
 * 
 * @author Georgios Gousios <gousiosg@gmail.com>
 * 
 * @assoc 1 - n ExecutionUnit
 */
@XmlRootElement(name = "encapsulation-unit")
@Entity
@Table(name = "ENCAPSULATION_UNIT")
public class EncapsulationUnit extends DAObject implements EncapsUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENCAPSULATION_UNIT_ID")
    @XmlElement
    long id;

    @Column(name = "NAME")
    @XmlElement
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NAMESPACE_ID")
    private NameSpace namespace;

    @OneToMany(mappedBy = "encapsulationUnit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, targetEntity=ExecutionUnit.class)
    private Set<ExecUnit> execUnits;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity=ProjectFile.class)
    @JoinColumn(name = "PROJECT_FILE_ID")
    private IProjectFile file;
    
    /**
     * Measurements for this encapsulation unit.
     */
    @OneToMany(mappedBy = "encapsulationUnit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EncapsulationUnitMeasurement> measurements;

    public EncapsulationUnit(){}
    
    public EncapsulationUnit(IProjectFile pf){
        this.file = pf;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameSpace getNamespace() {
        return namespace;
    }

    public void setNamespace(NameSpace namespace) {
        this.namespace = namespace;
    }

    public Set<ExecUnit> getExecUnits() {
        if (execUnits == null)
            return new HashSet<ExecUnit>();
        return execUnits;
    }

    public void setExecUnits(Set<ExecUnit> execUnits) {
        this.execUnits = execUnits;
    }

    public void setFile(IProjectFile file) {
        this.file = file;
    }

    public IProjectFile getFile() {
        return file;
    }

    public void setMeasurements(Set<EncapsulationUnitMeasurement> measurements) {
        this.measurements = measurements;
    }

    public Set<EncapsulationUnitMeasurement> getMeasurements() {
        return measurements;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
