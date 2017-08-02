package com.skynet.wallstreet.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skynet.wallstreet.dao.IBaseDao;
import com.skynet.wallstreet.model.BaseEntity;
import com.skynet.wallstreet.service.interfaces.IBaseService;
import com.skynet.wallstreet.service.interfaces.QuerySettable;

//@Transactional
@Service("baseService")
public class IBaseServiceImpl<T extends BaseEntity, PK extends Serializable> implements IBaseService<T, PK> {

	private IBaseDao<T, PK> baseDao;  
	
	public IBaseDao<T, PK> getBaseDao() {  
        return baseDao;  
    }  
      
	@Resource
    public void setBaseDao(IBaseDao<T, PK> baseDao) {  
        this.baseDao = baseDao;  
    }  
  
    public T get(PK id) {  
        return baseDao.get(id);  
    }  
      
    public PK save(T entity) { 
    	entity.setCreateDate(new Date());
    	entity.setModifyDate(new Date());
        return baseDao.save(entity);  
    }  
    
    public List<T> list(String hql, QuerySettable callback) {
    	return baseDao.list(hql, callback);
    }
}
