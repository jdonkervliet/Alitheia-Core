package eu.sqooss.service.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.sqooss.core.AlitheiaCore;

/**
 * Instances of this class represent a measurement made against a
 * namespace version, as stored in the database
 * 
 * @author Georgios Gousios <gousiosg@gmail.com>
 */
@Entity
@Table(name="NAMESPACE_MEASUREMENT")
@XmlRootElement(name="ns-measurement")
public class NameSpaceMeasurement extends MetricMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NAMESPACE_MEASUREMENT_ID")
    @XmlElement(name = "id")
    private long id; 

    /**
     * The namespace against which the measurement was made
     */ 
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "NAMESPACE_ID", referencedColumnName = "NAMESPACE_ID")
    private NameSpace namespace;
    
    /**
     * The metric to which this result belongs
     */
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="METRIC_ID", referencedColumnName="METRIC_ID")
    private Metric metric;

    /**
     * A representation of the calculation result
     */
    @Column(name="RESULT")
    private String result;

    public NameSpaceMeasurement() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setNamespace(NameSpace namespace) {
        this.namespace = namespace;
    }

    public NameSpace getNamespace() {
        return namespace;
    }
    
	public static List<NameSpaceMeasurement> getMeasurementsByNameSpace(NameSpace ns) {
    	DBService dbs = AlitheiaCore.getInstance().getDBService();
    	String paramNS = "paramNS";
    	String query = "SELECT nsm FROM NameSpaceMeasurement nsm WHERE nsm.namespace = :" + paramNS;
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(paramNS, ns);
    	return (List<NameSpaceMeasurement>) dbs.doHQL(query, params);
	}
}
