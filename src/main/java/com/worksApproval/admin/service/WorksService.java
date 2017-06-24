package com.worksApproval.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.entity.Works;

@Service
@Transactional
public interface WorksService extends BaseDao<Works> {
public boolean hasPermissionDownload(Long id, HttpServletRequest request);
List<Works> findAllFilterDeleted();
}