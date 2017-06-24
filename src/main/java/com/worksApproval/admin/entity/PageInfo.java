package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "page_info")
public class PageInfo implements Comparable<PageInfo>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9221589604701191566L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "url", nullable = false, length = 250, unique = true)
	private String url;// 页面链接,例如index.jsp,只有这个文件的名字,其他的都是固定的
	@Column(name = "page_name", nullable = false, length = 250, unique = true)
	private String pageName;// 文件中文名字
	@Column(name = "notes", nullable = true, length = 512)
	private String notes;// 注释
	@Column(name = "use_state")
	private Boolean useState;// 使用状态,1启用,2未启用
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;// 创建时间

	@Column(name = "sort")//order是mysql关键字,不能用,用了在新增时报错
	private Integer order;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_time", nullable = true)
	private Date lastUpdatedTime;// 最近一次更新时间

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getUseState() {
		return useState;
	}

	public void setUseState(Boolean useState) {
		this.useState = useState;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/*
	 * public Integer getUseState() { return useState; }
	 * 
	 * public void setUseState(Integer useState) { this.useState = useState; }
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public int compareTo(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		// 这里我的实现是按照id进行排序
		if (pageInfo == null)
			return 1;
		if (this.order > pageInfo.order)
			return 1;
		else if (order == pageInfo.getOrder())
			return 0;
		else
			return -1;
	}

	
	@Override
	public String toString() {
		return "PageInfo [id=" + id + ", url=" + url + ", pageName=" + pageName
				+ ", notes=" + notes + ", useState=" + useState
				+ ", createTime=" + createTime + ", order=" + order
				+ ", lastUpdatedTime=" + lastUpdatedTime + "]";
	}

	
}
