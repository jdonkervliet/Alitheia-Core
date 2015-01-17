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

import eu.sqooss.core.AlitheiaCore;

/**
 * This class represents the primary data associated with a bug report
 */
@Entity
@Table(name = "BUG_REPORT_MESSAGE")
public class BugReportMessage extends DAObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUG_REPORT_MESSAGE_ID")
	@XmlElement
	private long id;

	/**
	 * The bug with which the message is associated
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "BUG_ID")
	private Bug bug;

	/**
	 * The original reporter of the bug
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "DEVELOPER_ID")
	private Developer reporter;

	/**
	 * The date this message was written
	 */
	@Column(name = "TIMESTAMP")
	private Date timestamp;

	/**
	 * Message text
	 */
	@Column(name = "TEXT", length = 255)
	private String text;

	public BugReportMessage() {
		// Nothing to do here
	}

	public BugReportMessage(Bug b) {
		this.bug = b;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public Developer getReporter() {
		return reporter;
	}

	public void setReporter(Developer reporter) {
		this.reporter = reporter;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean equals(Object b) {
		if (!(b instanceof BugReportMessage)) {
			return false;
		}
		BugReportMessage brm = (BugReportMessage) b;
		if (this.timestamp == null) {
			if (brm.timestamp != null) {
				return false;
			}
		} else {
			if (brm.timestamp == null) {
				return false;
			} else if (this.timestamp.getTime() != brm.timestamp.getTime()) {
				return false;
			}
		}
		if ((this.getBug() == null && brm.getBug() != null)
				|| (this.getBug() != null && brm.getBug() == null)) {
			return false;
		}
		if (this.getBug() != null && brm.getBug() != null) {
			if (!brm.getBug().getBugID().equals(this.getBug().getBugID()))
				return false;	
			if (brm.getBug().getProject().getId() != bug.getProject().getId())
				return false;
		}
		return true;
	}

	public static List<BugReportMessage> getAllReportMessageForBug(Bug bug) {
		DBService dbs = AlitheiaCore.getInstance().getDBService();
		String paramBug = "paramBug";
		String paramProject = "paramProject";
		String query = "SELECT brm " + "FROM Bug b, BugReportMessage brm"
				+ " WHERE b.id = :" + paramBug 
				+ " AND brm.bug = b"
				+ " AND b.project = :" + paramProject
				+ " order by brm.timestamp asc";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramBug, bug.getId());
		params.put(paramProject, bug.getProject());
		return (List<BugReportMessage>) dbs.doHQL(query, params);
	}

	public static List<BugReportMessage> getAllReportMessageForDeveloper(
			Developer dev) {
		DBService dbs = AlitheiaCore.getInstance().getDBService();
		String paramDev = "paramDev";
		String query = "SELECT brm " + "FROM BugReportMessage brm "
				+ "WHERE brm.reporter = :" + paramDev;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramDev, dev);
		return (List<BugReportMessage>) dbs.doHQL(query, params);
	}
}

// vi: ai nosi sw=4 ts=4 expandtab

