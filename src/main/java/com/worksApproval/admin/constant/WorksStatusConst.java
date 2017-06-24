package com.worksApproval.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 稿件状态常量
 * 
 * @author rocky
 *
 */
public class WorksStatusConst {

	public static Map<Integer, String> worksStatusMap = null;// 稿件状态关系表
	public static int WORKS_STATUS_POST_SUCCESS = 0;
	/**
	 * 初审
	 */
	public static int WORKS_STATUS_JUNIOR = 1;
	public static int WORKS_STATUS_MIDDLE = 2;
	public static int WORKS_STATUS_SENIOR = 3;
	public static int WORKS_STATUS_ACCESS = 4;
	public static int WORKS_STATUS_SEND_BACK = -1;
	public static int WORKS_STATUS_NOUSE_OR_DEL = -3;
	public static int WORKS_STATUS_POST_FAIL = -2;

	static {
		worksStatusMap = new HashMap<Integer, String>();
		worksStatusMap.put(WORKS_STATUS_POST_SUCCESS, "投递成功");// 用户上传之后,等待编辑安排评审
		worksStatusMap.put(WORKS_STATUS_JUNIOR, "初审");// 编辑已经安排评审,
		worksStatusMap.put(WORKS_STATUS_MIDDLE, "复审");// 初审已经通过,进入复审
		worksStatusMap.put(WORKS_STATUS_SENIOR, "编辑审核");// 复审通过,等待编辑审核
		worksStatusMap.put(WORKS_STATUS_ACCESS, "录用");// 编辑审核通过,已经录用,可以公示
		worksStatusMap.put(WORKS_STATUS_SEND_BACK, "退回");// 三次审核任何一次没有通过状态就要改成退回,退回之后,只能重新投递.
		worksStatusMap.put(WORKS_STATUS_NOUSE_OR_DEL, "废弃或删除");
		worksStatusMap.put(WORKS_STATUS_POST_FAIL, "投递失败");

	}

	private static String getWorksStageByKey(int key)// 参数细化,防止出现null为key的情况
	{
		return worksStatusMap.get(key);
	}

}
