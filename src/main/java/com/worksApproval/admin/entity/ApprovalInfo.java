package com.worksApproval.admin.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="approval_info")
public class ApprovalInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3037707414404351322L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "approval_id")
	private Long Id;
	@ManyToOne(targetEntity=UserInfo.class)
	@JoinColumn(name="user_id",nullable=false)
	private UserInfo approver;//当前审批人
	@Column(name = "comment")
	private String comment;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public UserInfo getApprover() {
		return approver;
	}
	public void setApprover(UserInfo approver) {
		this.approver = approver;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "ApprovalInfo [Id=" + Id + ", approver=" + approver
				+ ", comment=" + comment + "]";
	}
	
	
}
