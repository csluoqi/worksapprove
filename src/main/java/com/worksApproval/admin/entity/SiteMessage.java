package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
/**
 * 消息实体类
 * @author rocky
 *
 */
@Entity
@Table(name="site_message")
public class SiteMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2709506493822417975L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id",nullable=false)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;
	@Column(name="status",length=2)
	private Integer status;//已经读了,未读,已经删除了
	@Lob
	@Type(type="text")
	@Column(name="content")
	private String content;
	
	@ManyToOne(targetEntity=UserInfo.class)
	@JoinColumn(name="recipients_user_id")
	private UserInfo recipients;//收件人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserInfo getRecipients() {
		return recipients;
	}

	public void setRecipients(UserInfo recipients) {
		this.recipients = recipients;
	}
}
