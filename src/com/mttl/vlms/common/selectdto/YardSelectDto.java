package com.mttl.vlms.common.selectdto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.mttl.vlms.common.AbstractDto;

/**
 * YardSelectDto
 *
 * 
 *
 */
public class YardSelectDto extends AbstractDto {

	private static final long serialVersionUID = -6021133409790493772L;

	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		System.out.println("YardSelectDto" + this.id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("YardSelectDto Name" + this.name);
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
