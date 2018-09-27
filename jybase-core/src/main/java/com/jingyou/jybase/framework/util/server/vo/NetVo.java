package com.jingyou.jybase.framework.util.server.vo;

import java.util.List;

public class NetVo {
	private String name;
	private long in;
	private long out;
	private String description;
	private List<NetVo> nets;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<NetVo> getNets() {
		return nets;
	}
	public void setNets(List<NetVo> nets) {
		this.nets = nets;
	}
	@Override
	public String toString() {
		return "NetVo [name=" + name + ", in=" + in + ", out=" + out + ", description=" + description + "]";
	}
	public long getIn() {
		return in;
	}
	public void setIn(long in) {
		this.in = in;
	}
	public long getOut() {
		return out;
	}
	public void setOut(long out) {
		this.out = out;
	}
	
}

