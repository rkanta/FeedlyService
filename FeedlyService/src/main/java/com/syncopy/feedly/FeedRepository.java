package com.syncopy.feedly;

import org.springframework.stereotype.Repository;
import java.util.List;
import feign.RequestLine;
import feign.Param;

@Repository
public interface FeedRepository {

	@RequestLine("GET /v3/search/feeds?query={query}")
	List<Feeds> getFeedId(@Param("query") String query);
}
