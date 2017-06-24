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

/**
 * 审批流 主表
 * 
 * @author rocky
 *
 */
@Entity
@Table(name = "approval_manage")
public class ApprovalManage implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8610159546935450008L;

	/**
	 * 审批流 主表 稿件 初审 复审 编辑审 审批阶段,这个与稿件的状态类似
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "approval_mng_id")
	private Long Id;// id

	@ManyToOne(targetEntity = Works.class)
	@JoinColumn(name = "work_id", nullable = false)
	private Works works;// 稿件
	// junior middle senior
	@ManyToOne(targetEntity = ApprovalInfo.class)
	@JoinColumn(name = "junior_approval_id")
	private ApprovalInfo junior;// 初审

	@ManyToOne(targetEntity = ApprovalInfo.class)
	@JoinColumn(name = "middle_approval_id")
	private ApprovalInfo middle;// 复审

	@ManyToOne(targetEntity = ApprovalInfo.class)
	@JoinColumn(name = "senior_approval_id")
	private ApprovalInfo senior;// 编辑审核

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Works getWorks() {
		return works;
	}

	public void setWorks(Works works) {
		this.works = works;
	}

	public ApprovalInfo getJunior() {
		return junior;
	}

	public void setJunior(ApprovalInfo junior) {
		this.junior = junior;
	}

	public ApprovalInfo getMiddle() {
		return middle;
	}

	public void setMiddle(ApprovalInfo middle) {
		this.middle = middle;
	}

	public ApprovalInfo getSenior() {
		return senior;
	}

	public void setSenior(ApprovalInfo senior) {
		this.senior = senior;
	}

	@Override
	public String toString() {
		return "ApprovalManage [Id=" + Id + ", works=" + works + ", junior="
				+ junior + ", middle=" + middle + ", senior=" + senior + "]";
	}

  
}
