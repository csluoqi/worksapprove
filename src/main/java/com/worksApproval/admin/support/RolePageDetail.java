package com.worksApproval.admin.support;

import com.worksApproval.admin.entity.PageInfo;

/**
 * 在角色管理页面执行更新操作时用到的数据结构
 * 
 * @author rocky
 *
 */
public class RolePageDetail {

	private PageInfo page;
	private int status;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}
/**
 * // 1选中,0,未选中
 * @return
 */
	public int getStatus() {
		return status;
	}
/**
 * // 1选中,0,未选中
 * @param status
 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RolePageDetail [page=" + page + ", status=" + status + "]";
	}

}
