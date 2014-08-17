package com.frank.startup.portal.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

import com.frank.startup.portal.common.Constant;

/**
 * @ClassName:     BaseDto.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 10:47:46 
 */
public class BaseBean implements Serializable, Constant {
	private static final Logger logger = Logger.getLogger(BaseBean.class);
	private static final long serialVersionUID = 1L;

	protected int currentPage = 1;
	protected int pageSize = Constant.DEFAULT_PAGE_SIZE;

	/**
	 * Set the bean field to empty.
	 */
	public void clear() {
		Field[] fields = this.getClass().getDeclaredFields();

		if (fields != null) {
			for (Field f : fields) {
				if (f == null)
					continue;
				if (Modifier.isStatic(f.getModifiers()))
					continue;
				if (f.getType() == String.class) {
					try {
						f.setAccessible(true);
						f.set(this, "");
					} catch (IllegalArgumentException e) {
						logger.warn("exception:", e);
					} catch (IllegalAccessException e) {
						logger.warn("exception:", e);
					}

				}
			}
		}

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
