package com.mttl.vlms.web.common;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "MyBean")
public class MyBean {
	private ProcessTypeList processTypeList;

	public enum ProcessTypeList {

		YARD_IN(0), YARD_OUT(1), DAILYOPERATION(2), OTHER(3);

		private final int id;

		ProcessTypeList(int newId) {
			this.id = newId;
		}

		public int getId() {
			return id;
		}
	}

	public ProcessTypeList[] getProcessTypeList() {
		return ProcessTypeList.values();
	}

	public void setProcessTypeList(ProcessTypeList processTypeList) {
		this.processTypeList = processTypeList;
	}

}