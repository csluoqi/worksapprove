package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "works")
public class Works implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2619913627921420031L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_id")
	private Long id;// 主键id
	@Column(name = "name", length = 256, unique = true, nullable = false)
	private String name;// 稿件名字
	@Column(name = "summary", length = 2048)
	private String summary;// 稿件摘要
	@Column(name = "file_path", length = 512)
	private String filepath;// 保存路径
	@Column(name = "file_size")
	private Long filesize;// 文件大小 kB
	// 多对一,一个作者对应多个作品,一个作品对应一个作者
	@ManyToOne(targetEntity = UserInfo.class)
	@JoinColumn(name = "author_id")
	//@Cascade(CascadeType.ALL)
	private UserInfo author;// 作者

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_Date")
	private Date createDate;// 创建时间

	@ManyToOne(targetEntity = WorksTheme.class)
	@JoinColumn(name = "theme_id",referencedColumnName="theme_id",nullable=false)
	private WorksTheme theme;// 主题
	@Column(name = "status", length = 2)
	private Integer status;// 状态,审批状态

	/**
	 * private WorksTheme theme;//主题 private String name;//稿件名字
	 * 
	 * 稿件（编号，文章标题，文章摘要，作者，所属主题）
	 * 
	 * id 编号
	 * private UserInfo author;// 作者 
	 * private Date createDate;//创建时间
	 * private Integer filesize;//文件大小
	 * private String filepath;//上传路径
	 * 
	 * private Long id;//主键id 
	 * private Integer status;//状态,审批状态
	 */
	/**
	 [id=4,
 name=qqww, 
summary=概论,
 filepath 保存路径
 filesize=11,
 author=UserInfo [id=1, username=qaasdf, password=111111, realName=qq, birthday=2017-01-07 23:26:16.0, registerDate=2017-01-07 23:26:16.0, gender=1, phoneNumber=13222222222, email=asdf, address=aa, profile=qwqwqwqw, userRole=com.worksApproval.admin.entity.UserRole@33a4d39e], 

createDate=2017-01-07 23:33:49.0, 

theme=WorksTheme [id=11, name=23, comment=23, createDate=2017-01-02 12:40:38.0], status=1],

id 编号
name 稿件名字
summary 稿件概述
filepath 保存路径,
filesize 文件大小
author;// 作者
 createDate;// 创建时间
theme;// 主题
status;// 状态,审批状态
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public UserInfo getAuthor() {
		return author;
	}

	public void setAuthor(UserInfo author) {
		this.author = author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public WorksTheme getTheme() {
		return theme;
	}

	public void setTheme(WorksTheme theme) {
		this.theme = theme;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Works [id=" + id + ", name=" + name + ", summary=" + summary
				+ ", filepath=" + filepath + ", filesize=" + filesize
				+ ", author=" + author + ", createDate=" + createDate
				+ ", theme=" + theme + ", status=" + status + "]";
	}

	
}
