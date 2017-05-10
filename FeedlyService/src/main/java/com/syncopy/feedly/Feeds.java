package com.syncopy.feedly;

import java.io.Serializable;

public class Feeds implements Serializable {
     
	private static final long serialVersionUID = 1L;
	
	private String feedId ="";

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	@Override
	public int hashCode() {
		return getFeedId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Feeds)) {
			return false;
		}
		return o.hashCode() == hashCode();
	}
	
}
