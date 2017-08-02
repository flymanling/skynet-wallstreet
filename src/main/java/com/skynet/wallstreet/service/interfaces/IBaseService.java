package com.skynet.wallstreet.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IBaseService <T, PK extends Serializable>{

	 /** 
     * 根据ID获取实体对象. 
     * @param id 
     *            记录ID 
     * @return 实体对象 
     */  
    public T get(PK id);  
      
    /** 
     * 保存实体对象. 
     *  
     * @param entity 
     *            对象 
     * @return ID 
     */  
    public PK save(T entity); 
    
    public List<T> list(String hql, QuerySettable callback);
}
