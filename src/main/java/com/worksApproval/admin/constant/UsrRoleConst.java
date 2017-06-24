package com.worksApproval.admin.constant;

import java.util.HashMap;
import java.util.Map;

public class UsrRoleConst {
	public static Map<Long, String> roleConstantMap = null;// 角色,这个时基础的指定之后基本不变

	public static Long ROLE_AUTHOR = 1L;// 普通用户,作者
	public static Long ROLE_JUNIOR = 16L;// 初级审批员
	public static Long ROLE_MIDDLE = 17L;// 中级审批员
	public static Long ROLE_EDITOR = 18L;// 编辑
	public static Long ROLE_ADMIN = 19L;// 管理员
	public static Long ROLE_REGISTER_HALF = 20L;// 准注册

	// Quasi

	static {
		roleConstantMap = new HashMap<Long, String>();
		roleConstantMap.put(ROLE_AUTHOR, "作者");
		roleConstantMap.put(ROLE_JUNIOR, "初级评审");
		roleConstantMap.put(ROLE_MIDDLE, "中级评审");
		roleConstantMap.put(ROLE_EDITOR, "编辑");
		roleConstantMap.put(ROLE_ADMIN, "管理员");
		roleConstantMap.put(ROLE_REGISTER_HALF, "准注册");
	}

	public static String getRoleName(Integer roleId) {

		return roleConstantMap.get(roleId);
	}

	/**
	 * 有下载的权限
	 * @param roleid
	 * @return
	 */
	public static boolean hasPermission(Long roleid) {
		if (roleid == ROLE_JUNIOR || roleid == ROLE_MIDDLE
				|| roleid == ROLE_EDITOR || roleid == ROLE_ADMIN) {
			return true;
		}
		return false;
	}

	/**
	 * 有访问我的代办的权限
	 * @param roleid
	 * @return
	 */
	public static boolean hasPermissionApprovalTodo(Long roleid) {
		if (roleid == ROLE_JUNIOR || roleid == ROLE_MIDDLE
				|| roleid == ROLE_EDITOR) {
			return true;
		}
		return false;
	}
}
