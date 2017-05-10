package com.syncopy.feedly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Feeds")
public class FeedController {

	@Autowired
	FeedRepository feedRepository;
	
	@RequestMapping(value = "/{feedid}", method = RequestMethod.GET)
	private List<Feeds> getFeedId(@PathVariable String feedid) {
		//private void getFeedId(String symbol) {
		return feedRepository.getFeedId(feedid);
     }
}
