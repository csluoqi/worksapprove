package com.worksApproval.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;
import com.worksApproval.admin.dao.impl.UsersDao;
import com.worksApproval.admin.entity.UsersTest;

@Service
@Transactional
public interface IUserService extends BaseDao<UsersTest> { }
