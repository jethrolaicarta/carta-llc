package com.carta.llc.core.data;

/**
 * base LLC data model
 * 
 * @author jethrolai
 *
 */
public interface LLCBaseData {

	String getId();

	void setId(final String id);

	Long getCreated();

	void setCreated(final Long created);

	Long getUpdated();

	void setUpdated(final Long updated);

	Long getDeleted();

	void setDeleted(final Long deleted);

//	protected String id;
//	protected Long created;
//	protected Long updated;
//	protected Long deleted;
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public Long getCreated() {
//		return created;
//	}
//
//	public void setCreated(Long created) {
//		this.created = created;
//	}
//
//	public Long getUpdated() {
//		return updated;
//	}
//
//	public void setUpdated(Long updated) {
//		this.updated = updated;
//	}
//
//	public Long getDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(Long deleted) {
//		this.deleted = deleted;
//	}

}