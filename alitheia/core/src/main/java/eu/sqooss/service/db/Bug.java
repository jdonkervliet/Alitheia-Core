/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2008 - 2010 - Organization for Free and Open Source Software,  
 *                 Athens, Greece.
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import eu.sqooss.core.AlitheiaCore;

/**
 * This class represents the data relating to bugs, stored in the database
 * 
 * @assoc 1 - n BugReportMessage
 * @assoc 1 - n Developer
 *  
 */
@Entity
@Table(name="BUG")
public class Bug extends DAObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BUG_ID")
	@XmlElement
	private long id; 

	/** The project this bug belongs to */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=StoredProject.class)
	@JoinColumn(name="STORED_PROJECT_ID")
    private Project project;
    
    /** When this bug was last touched by the updater */
	@Column(name="UPDATE_RUN")
    private Date updateRun;
    
    /**
     * The bugID in the original bug tracking system. Used to correlate
     * entries to filesystem/other database bug reports.
     */
	@Column(name="BUG_EXTERNAL_ID")
    private String bugID;
    
    /** The bug resolution status. */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUG_STATUS_ID")
    private BugStatus status;
    
    /** Creation timestamp. */
    @Column(name="CREATION_TS")
    private Date creationTS;
    
    /** The timestamp of the last update. */
    @Column(name="DELTA_TS")
    private Date deltaTS;
    
    /** The user who reported this. */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REPORTER_ID")
    private Developer reporter;
    
    /** The bug's resolution status. */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUG_RESOLUTION_ID")
    private BugResolution resolution;
    
    /** The bug's resolution priority*/
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUG_PRIORITY_ID")
    private BugPriority priority;
    
    /** The bug's severity.*/
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUG_SEVERITY_ID")
    private BugSeverity severity;
    
    /** A short description of the bug. */
    @Column(name="SHORT_DESC")
    private String shortDesc;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    public String getBugID() {
        return bugID;
    }

    public void setBugID(String bugID) {
        this.bugID = bugID;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public Date getCreationTS() {
        return creationTS;
    }

    public void setCreationTS(Date creationTS) {
        this.creationTS = creationTS;
    }

    public Date getDeltaTS() {
        return deltaTS;
    }

    public void setDeltaTS(Date deltaTS) {
        this.deltaTS = deltaTS;
    }

    public Developer getReporter() {
        return reporter;
    }

    public void setReporter(Developer reporter) {
        this.reporter = reporter;
    }

    public BugResolution getResolution() {
        return resolution;
    }

    public void setResolution(BugResolution resolution) {
        this.resolution = resolution;
    }
    
    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }
    
    public BugSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(BugSeverity severity) {
        this.severity = severity;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getUpdateRun() {
        return updateRun;
    }

    public void setUpdateRun(Date updateRun) {
        this.updateRun = updateRun;
    }
    
    /**
     * Get the latest entry processed by the bug updater
     */
    @SuppressWarnings("unchecked")
    public static Bug getLastUpdate(Project sp) {
        DBService dbs = AlitheiaCore.getInstance().getDBService();

        if (sp == null)
            return null;
        
        String paramStoredProject = "storedProject";
        
        String query = " select b " +
            " from Bug b, StoredProject sp" +
            " where b.project=sp" +
            " and sp = :" + paramStoredProject + 
            " order by b.updateRun desc";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(paramStoredProject, sp);
        
        List<Bug> buglist = (List<Bug>) dbs.doHQL(query, params,1);
        
        if (buglist.isEmpty())
            return null;
        
        return buglist.get(0);
    }
    
    /**
     * Get the latest entry for the bug with the provided Id.
     */
    public static Bug getBug(String bugID, Project sp) {    
        DBService dbs = AlitheiaCore.getInstance().getDBService();
        
        String paramBugID = "paramBugID";
        String paramStoredProject = "stroredProject";
        
        String query = "select b " +
        	        "from Bug b " +
        	        "where b.bugID = :" + paramBugID + 
        	        " and b.project = :" + paramStoredProject ;
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(paramBugID, bugID);
        params.put(paramStoredProject, sp);
        
        List<Bug> bug = (List<Bug>) dbs.doHQL(query, params, 1);
        
        if (bug.isEmpty())
            return null;
        else 
            return bug.get(0);
    }
    
    /**
     * Gets all bugs for a given priority.
     * @param priority To filter the bugs by.
     * @return the list of bugs of the given priority.
     */
    public static List<Bug> getBugsForPriority(BugPriority priority) {
    	DBService dbs = AlitheiaCore.getInstance().getDBService();
    	String paramBugPriority = "paramBugPriority";
    	String query = "SELECT b FROM Bug b WHERE b.priority = :" + paramBugPriority;
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(paramBugPriority, priority);
    	return (List<Bug>) dbs.doHQL(query, params);
    }
    
    /**
     * Gets all bugs for a given resolution.
     * @param resolution To filter the bugs by.
     * @return the list of bugs of the given resolution.
     */
    public static List<Bug> getBugsForResolution(BugResolution resolution) {
    	DBService dbs = AlitheiaCore.getInstance().getDBService();
    	String paramBugResolution = "paramBugResolution";
    	String query = "SELECT b FROM Bug b WHERE b.resolution = :" + paramBugResolution;
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(paramBugResolution, resolution);
    	return (List<Bug>) dbs.doHQL(query, params);
    }
    
    /**
     * Gets all bugs for a given severity.
     * @param severity To filter the bugs by.
     * @return the list of bugs of the given severity.
     */
    public static List<Bug> getBugsForSeverity(BugSeverity severity) {
    	DBService dbs = AlitheiaCore.getInstance().getDBService();
    	String paramBugSeverity = "paramBugSeverity";
    	String query = "SELECT b FROM Bug b WHERE b.severity = :" + paramBugSeverity;
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(paramBugSeverity, severity);
    	return (List<Bug>) dbs.doHQL(query, params);
    }
    
    /**
     * Gets all bugs for a given status.
     * @param status To filter the bugs by.
     * @return the list of bugs of the given status.
     */
    public static List<Bug> getBugsForStatus(BugStatus status) {
    	DBService dbs = AlitheiaCore.getInstance().getDBService();
    	String paramBugStatus = "paramBugStatus";
    	String query = "SELECT b FROM Bug b WHERE b.status = :" + paramBugStatus;
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(paramBugStatus, status);
    	return (List<Bug>) dbs.doHQL(query, params);
    }
}

//vi: ai nosi sw=4 ts=4 expandtab

