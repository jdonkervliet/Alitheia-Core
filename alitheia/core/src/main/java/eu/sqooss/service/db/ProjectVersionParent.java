/*
 * Copyright 2010 Organization for Free and Open Source Software,  
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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Models the parent - child relationships of versions. In SVN like systems, a
 * version can only have one parent. This is not necessarily true for
 * distributed systems such as Git though.
 * 
 * @author Georgios Gousios <gousiosg@gmail.com>
 */
@XmlRootElement(name = "version-parents")
@Entity
@Table(name = "PROJECT_VERSION_PARENT")
public class ProjectVersionParent extends DAObject {

	private long id; //Purely for DAObject compatibility
	
    @EmbeddedId
    private ProjectVersionParentId pk;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity=ProjectVersion.class)
    @MapsId(value = "parentid")
    @JoinColumn(name = "PARENT_ID")
    private Version parent;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity=ProjectVersion.class)
    @MapsId(value = "childid")
    @JoinColumn(name = "CHILD_ID")
    private Version child;

    public ProjectVersionParent() {}

    public ProjectVersionParent(Version child, Version parent) {
        this.child = child;
        this.parent = parent;
        ProjectVersionParentId id = new ProjectVersionParentId();
        id.setChildid(child.getId());
        id.setParentid(parent.getId());
        pk = id;
    }

    public Version getParent() {
        return parent;
    }

    public void setParent(Version parent) {
        this.parent = parent;
    }

    public Version getChild() {
        return child;
    }

    public void setChild(Version child) {
        this.child = child;
    }

    public void setId(ProjectVersionParentId pk) {
        this.pk = pk;
    }

    public ProjectVersionParentId getPk() {
        return pk;
    }

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
}
