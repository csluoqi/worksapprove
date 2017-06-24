package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

/**
 * 角色类
 * 
 * @author rocky
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5883560518807842219L;
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "shortName")
	private String shortName;//名称
	@Column(name = "comment", length = 256)
	private String comment;//简介

	// 这个角色可以访问的页面的集合,且是按照顺序添加进去的
	@ManyToMany(targetEntity = PageInfo.class,fetch=FetchType.EAGER,cascade=javax.persistence.CascadeType.REMOVE)//立即加载
	@JoinTable(name = "role_page2", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"), inverseJoinColumns = @JoinColumn(name = "page_id", referencedColumnName = "id"))
	@OrderBy("sort")
	private Set<PageInfo> pageSet = new TreeSet<PageInfo>();

	public Set<PageInfo> getPageSet() {
		return pageSet;
	}

	public void setPageSet(Set<PageInfo> pageSet) {
		this.pageSet = pageSet;
	}

	public String getShortName() {
		return shortName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", shortName=" + shortName + ", comment="
				+ comment + ", pageSet=" + pageSet + "]";
	}
	
	
}
