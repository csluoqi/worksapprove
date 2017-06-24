package com.worksApproval.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mchange.io.FileUtils;
import com.worksApproval.admin.constant.HttpAssist;
import com.worksApproval.admin.constant.SessionUtil;
import com.worksApproval.admin.constant.SysConstant;
import com.worksApproval.admin.constant.WorksStatusConst;
import com.worksApproval.admin.dao.impl.CommonService;
import com.worksApproval.admin.entity.SiteMessage;
import com.worksApproval.admin.entity.UserInfo;
import com.worksApproval.admin.entity.Works;
import com.worksApproval.admin.entity.WorksTheme;
import com.worksApproval.admin.service.SiteMessageService;
import com.worksApproval.admin.service.ThemeService;
import com.worksApproval.admin.service.WorksService;

/**
 * 稿件管理
 * 
 * @author rocky
 */
@Controller
public class WorksController {
	private static Logger log = Logger.getLogger(WorksController.class);
	@Resource
	private WorksService worksService;
	@Resource
	private CommonService commonService;
	@Resource
	private ThemeService themeService;
	@Resource
	private SiteMessageService siteMessageService;
	
	
	
	@RequestMapping(value = "/managementWorks.html")
	public String managementWorks(Model model) {
		List<Works> worksList = worksService.findAllFilterDeleted();
		log.info(worksList);
		model.addAttribute("worksList", worksList);
		// 下拉框可以提取到jsp页面,jsp应该自定义这些基础的数据
		List<WorksTheme> worksThemeList = themeService.findAll();
		model.addAttribute("worksThemeList", worksThemeList);
		return "managementWorks";
	}

	// @ResponseBody
	@RequestMapping(value = "/newWorks")
	public String newWorks(HttpServletRequest req, Works works,
			RedirectAttributes redirectAttributes) {
		works.setCreateDate(new Date());
		MultipartFile worksFile = ((MultipartRequest) req).getFile("worksFile");

		String fileName = worksFile.getOriginalFilename();
		File f = new File(SysConstant.SERVER_PATH, fileName);
		UserInfo author = SessionUtil.getUsrFromSession(req);
		works.setAuthor(author);
		works.setCreateDate(new Date());
		works.setName(fileName);
		works.setFilepath(f.getPath());
		try {
			worksFile.transferTo(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		works.setStatus(WorksStatusConst.WORKS_STATUS_POST_SUCCESS);
		works.setFilesize(new File(SysConstant.SERVER_PATH, fileName).length() / 1024L);
		worksService.create(works);

		siteMessageService.create(SysConstant.createMessage(works.getAuthor(), works, SysConstant.MSG_POST_SUCCESS));
		
		/*
		 * String fileName = multipartFile.getOriginalFilename();
		 * fileNames.add(fileName);
		 * 
		 * File imageFile = new File(servletRequest.getServletContext()
		 * .getRealPath("/image"), fileName); try {
		 * multipartFile.transferTo(imageFile); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		redirectAttributes.addFlashAttribute("message", "添加成功!");
		return HttpAssist.REDIRECT + "managementWorks.html";
	}

	@ResponseBody
	@RequestMapping(value = "/isRepeatWorks")
	public Boolean isRepeat(Works works,HttpServletRequest request) {
	UserInfo usr = SessionUtil.getUsrFromSession(request);
		log.info(works);
		//新增
		if (works.getId() == null) {
//TODO 这里的业务逻辑得重写
			// 新增
			// 这里在后面添加了新增页面之后需要改改成userID
			// /后期优化,在做了登录只有,可以在用户表里面建立一个serverFolder字段,来存贮和区分用户上传的文件
//			return true;
			 return !commonService.isRepeate("from Works c where c.name=?1 ",works.getName());
		} else {
			//更新
			return !commonService.isRepeate(
			 "from Works c where c.name=?1 and c.id != ?2 ",works.getName(), works.getId());
		}

	}

	@RequestMapping(value = "/updateWorks")
	public String updateWorks(Works works, HttpServletRequest req,
			RedirectAttributes redirectAttributes) {
		// 查询数据库中的这条数据
		Works worksdb = worksService.findOne(works.getId());
		works.setCreateDate(new Date());
		MultipartFile worksFile = ((MultipartRequest) req).getFile("worksFile");

		String fileName = worksFile.getOriginalFilename();
		// fileNames.add(fileName);
		// 文件路径写死,后面构建框架时建立起来
		String serverPath = "/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer";

		File f = new File(serverPath, fileName);
		UserInfo author =SessionUtil.getUsrFromSession(req);
		works.setAuthor(author);
		works.setCreateDate(new Date());
		works.setName(fileName);
		works.setFilepath(f.getPath());
		works.setStatus(worksdb.getStatus());
		try {
			if (!f.isDirectory()) {
				// 上传
				works.setName(fileName);
				works.setFilepath(f.getPath());
				worksFile.transferTo(f);
				works.setFilesize(new File(serverPath, fileName).length() / 1024L);
			} else {
				works.setName(worksdb.getName());
				works.setFilepath(worksdb.getFilepath());
				works.setFilesize(worksdb.getFilesize());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		worksService.create(works);

		redirectAttributes.addFlashAttribute("message", "更新成功!");
		return HttpAssist.REDIRECT + "managementWorks.html";
	}

	@ResponseBody()
	@RequestMapping(value = "/deleteWorks", method = RequestMethod.POST)
	public String[] deleteWorks(String ids,
			RedirectAttributes redirectAttributes) {
		// commonService.simpleDelete("delete from  Works where id in (:ids)",
		// ids, "ids");
		// 循环删除,并删除文件
		Works w = null;
		File f = null;
		for (String id : ids.split(",")) {
			w = worksService.findOne(Long.valueOf(id));
			try {
				w.setStatus(WorksStatusConst.WORKS_STATUS_NOUSE_OR_DEL);
				worksService.update(w);
				siteMessageService.create(SysConstant.createMessage(w.getAuthor(), w, SysConstant.MSG_DELETE_WORKS));
				//worksService.delete(w);
			} catch (Exception e) {
				return new String[] { "删除失败! 请联系管理员" };
			}
			// 删除文件
			f = new File(w.getFilepath());
			f.deleteOnExit();
		}
		return new String[] { "删除成功! 页面将自动跳转" };
	}

	@RequestMapping(value = "/downloadWorks")
	public ResponseEntity<byte[]> downloadWorks(HttpServletResponse response, String id,
			RedirectAttributes redirectAttributes) throws IOException {
		/**
		 * 异常本该统一处理的
		 */
/*		Long wId = null;
		try {
			wId = Long.valueOf(id);
		} catch (NumberFormatException e1) {
			redirectAttributes.addFlashAttribute("message", "参数不合法");
			return PageResponse.REDIRECT + "managementWorks.html";
		}
		Works works = worksService.findOne(wId);
		String path = works.getFilepath();
		// 截取文件地址，最后一个斜杠后面的文件名
		String filename = works.getName();

		// 设置以何种方式打开文件
		// 下载的图片名为中文的，修改编码
		try {
			response.setHeader("content-disposition", "attachement;filename ="
					+ URLEncoder.encode(filename, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("content-disposition", "attachement;filename"
				+ filename);
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(path);
			int len = 0;
			byte buffer[] = new byte[1024];
			out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		}catch (Exception e)
		{
			System.out.println(e);
		}
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/
		Long wId = null;
		try {
			wId = Long.valueOf(id);
		} catch (NumberFormatException e1) {
			log.error(e1.getMessage());
		}
		Works works = worksService.findOne(wId);
		   File file=new File(works.getFilepath());
		   HttpHeaders headers = new HttpHeaders();    
	        String fileName = null;
			try {
				fileName = new String(works.getName().getBytes("UTF-8"),"iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//为了解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
		  return new ResponseEntity<byte[]>(FileUtils.getBytes(file),    
                  headers, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "/downloadWorks2")
	public void downloadWorks2(HttpServletResponse response, String downloadId,String backurl,
			RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		log.info("downloadWorks2");
		Long wId = null;
		try {
			wId = Long.valueOf(downloadId);
		} catch (NumberFormatException e1) {
			redirectAttributes.addFlashAttribute("message", "参数不合法");
	//		return PageResponse.REDIRECT + "managementWorks.html";
		}
		log.info(HttpAssist.getBackUrl(backurl));
		Works works = worksService.findOne(wId);
		String path = works.getFilepath();
		// 截取文件地址，最后一个斜杠后面的文件名
		String filename = new String(works.getName().getBytes("UTF-8"),"iso-8859-1");

		// 设置以何种方式打开文件
		// 下载的图片名为中文的，修改编码
		try {
			response.setHeader("Content-Disposition", "attachment; filename ="
					+  new String(works.getName().getBytes("UTF-8"),"iso-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(path);
			int len = 0;
			byte buffer[] = new byte[1024];
			out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		}catch (Exception e)
		{
			log.error(e.getMessage());
		}
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		return null;
	}

	
	
	
	  @RequestMapping("download")    
	    public ResponseEntity<byte[]> download() throws IOException {    
	        String path="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\springMVC\\WEB-INF\\upload\\图片10（定价后）.xlsx";  
	        File file=new File(path);  
	        HttpHeaders headers = new HttpHeaders();    
	        String fileName=new String("你好.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(FileUtils.getBytes(file),    
	                                          headers, HttpStatus.CREATED);    
	    }    
	
	
	
}
