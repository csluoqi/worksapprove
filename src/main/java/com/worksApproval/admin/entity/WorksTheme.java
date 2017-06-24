package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import com.worksApproval.admin.constant.WorksStatusConst;

@Entity
@Table(name = "works_theme")
public class WorksTheme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3115440837298280469L;
	@Id
	@Column(name = "theme_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//编号
	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;//主题名字
	// text
	@Lob
	@Type(type = "text")
	@Column(name = "comment")
	private String comment;//简介

	@Column(name = "theme_order", columnDefinition = "default 0")
	private Integer order;
	//主题记号,暂时没有用
	@Column(name = "sign", unique=true)
	private String sign;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;//创建时间

	// 这里可以要用查询这个集合
	@OneToMany(targetEntity=Works.class,mappedBy="theme" ,fetch =FetchType.EAGER)
	@Where(clause= "status = 4")
	//@OneToMany(targetEntity=Works.class,mappedBy="theme" )
	@OrderBy("id")
	private Set<Works> works = new TreeSet<Works>();

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Set<Works> getWorks() {
		return works;
	}

	public void setWorks(Set<Works> works) {
		this.works = works;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "WorksTheme [id=" + id + ", name=" + name + ", comment="
				+ comment + ", createDate=" + createDate + "]";
	}

}
