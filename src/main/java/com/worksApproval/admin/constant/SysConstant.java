package com.worksApproval.admin.constant;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.worksApproval.admin.entity.SiteMessage;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.service.WorksService;
import com.worksApproval.admin.service.impl.WorksServiceImpl;

/**
 * 系统静态变量,业务相关的 放在map中可以通过Id获取,
 * 
 * @author rocky
 *
 */
@Component
public class SysConstant {
	
	@Resource
	private  WorksService  worksService ;
	/**
	 * 建议使用SessionUtil.SESSION_USR 
	 */
	@Deprecated
	public static String SESSION_USR = "usr";
	public static String  SERVER_PATH = "D:/TDDOWNLOAD";
	public static Map<Long, String> menuIconMap = null;

	static {
		menuIconMap = new HashMap<Long, String>();
		menuIconMap.put(11L, "icon-bar-chart");
		menuIconMap.put(12L, "icon-book");
		menuIconMap.put(13L, "icon-bookmark");
		menuIconMap.put(14L, "icon-calendar");
		menuIconMap.put(15L, "icon-credit-card");
		menuIconMap.put(16L, "icon-info-sign");
		menuIconMap.put(17L, "icon-edit");
		menuIconMap.put(18L, "icon-dashboard");
		menuIconMap.put(19L, "icon-flag");
		menuIconMap.put(20L, "icon-leaf");
		menuIconMap.put(21L, "icon-user");
		menuIconMap.put(22L, "icon-upload-alt");
		// 新家菜单L的时候要在这里加入,现在22个菜单,在加入10个这个项目应该够用,如果不够再加
		//
		menuIconMap.put(23L, "icon-paper-clip");
		menuIconMap.put(24L, "icon-th-large");
		menuIconMap.put(25L, "icon-twitter");
		menuIconMap.put(26L, "icon-github");
		menuIconMap.put(27L, "icon-sign-blank");
		menuIconMap.put(28L, "icon-screenshot");
		menuIconMap.put(29L, "icon-road");
		menuIconMap.put(30L, "icon-plane");
		menuIconMap.put(31L, "icon-inbox");
		menuIconMap.put(32L, "icon-magic");
		menuIconMap.put(33L, "icon-hdd");
	}

	
	private static String[] bgColor = new String[] { "bg_lb", "bg_lg", "bg_ly",
			"bg_ls", "bg_lo", "bg_lr", "bg_lv", "bg_lh", "bg_lb", "bg_lg",
			"bg_ly", "bg_ls", "bg_lo", "bg_lr", "bg_lv", "bg_lh", "bg_lb",
			"bg_lg", "bg_ly", "bg_ls", "bg_lo", "bg_lr", "bg_lv", "bg_lh" };

	public static String[] getBgColor() {
		return bgColor;
	}

	/**
	 * 在菜单中
	 * 
	 * @param key
	 * @return
	 */
	public static String getMenuIconMapByKey(long key) {
		return menuIconMap.get(key);
	}

	public static String MSG_POST_SUCCESS = "投递成功";
	public static String MSG_POST_FAIL = "投递失败";
	
	public static String MSG_SET_APPROVAL_FLOW = "进入审批环节";
	public static String MSG_DELETE_WORKS = "已经删除";
	//public static String MSG_APPROVAL_JUNIOR_NO_PASS = "未通过初审";
		
	public static String MSG_APPROVAL_JUNIOR_PASS = "通过初审";
	public static String MSG_APPROVAL_JUNIOR_NO_PASS = "未通过初审";
	
	public static String MSG_APPROVAL_MIDDLE_PASS = "通过复审";
	public static String MSG_APPROVAL_MIDDLE_NO_PASS = "未通过复审";

	public static String MSG_APPROVAL_EDITOR_PASS = "通过编辑审核,请关注公示";
	public static String MSG_APPROVAL_EDITOR_NO_PASS = "未通过编辑审核";
	public static String MSG_APPROVAL_NO_PASS = "未通过审核,已经退回";

	//消息类型
	//public static int MSG_TYPE_POST_SUCCESS = 1;
	//状态
	public static int MSG_STATUS_NOT_READ = 0;
	public static int MSG_STATUS_READ = 1;
	
	public static SiteMessage createMessage(UserInfo recipients,Works w,String msgContent)
	{
		StringBuffer msg = new StringBuffer();
		SiteMessage m = new SiteMessage();
		m.setRecipients(recipients);
		m.setCreateDate(new Date());
		m.setStatus(MSG_STATUS_NOT_READ);
		
		msg.append("稿件 " + w.getName() +" " + msgContent);
		m.setContent(msg.toString());
		return m;
	}

	
}
