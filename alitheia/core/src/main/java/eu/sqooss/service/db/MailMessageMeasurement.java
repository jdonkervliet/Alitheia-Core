/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2008 - 2010 - Organization for Free and Open Source Software,  
 *                Athens, Greece.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package eu.sqooss.service.db;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Instances of this class represent a measurement made against a
 * specific mail message, as stored in the database
 */
@Entity
@Table(name="MAILMESSAGE_MEASUREMENT")
@XmlRootElement(name="mlmsg-measurement")
public class MailMessageMeasurement extends MetricMeasurement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MAILMESSAGE_MEASUREMENT_ID")
    @XmlElement(name = "id")
	private long id; 
	
	/**
     * The thread against which the measurement was made
     */ 
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity=MailMessage.class)
    @JoinColumn(name = "MAILMESSAGE_ID", referencedColumnName = "MAILMESSAGE_ID")
    private IMailMessage mail;
    
    public MailMessageMeasurement() {
        super();
    }
    
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

    /**
     * Convenience constructor to avoid having to call three methods
     * to set up sensible values in a measurement.
     * 
     * @param m Metric this measurement is from
     * @param mail Mail message this measurement is for
     * @param value (String) value representation of the measurement
     */
    public MailMessageMeasurement(Metric m, IMailMessage mail, String value) {
        super();
        setMetric(m);
        setMail(mail);
        setResult(value);
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    public IMailMessage getMail() {
        return mail;
    }

    public void setMail(IMailMessage mail) {
        this.mail = mail;
    }
    
    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
