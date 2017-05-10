package com.syncopy.feedly;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minidev.json.JSONArray;

import org.springframework.util.NumberUtils;

import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.gson.GsonDecoder;


public class FeedDecoder extends GsonDecoder {
	
	@Override
	public Object decode(Response response, Type type) throws IOException,
			DecodeException, FeignException {

		Response.Body body = response.body();
		if (body == null) {
			return null;
		}
		
		 //System.out.println("printing body as a reader"+Util.toString(body.asReader()));

				Type typeOfListOfFeed = new TypeToken<List<Feeds>>() {
				}.getType();
				if (Feeds.class.equals(type) || typeOfListOfFeed.equals(type)) {
					return processBody(body);
				}

				return super.decode(response, type);

}

	private Object processBody(Response.Body body) throws IOException {
		ReadContext ctx = JsonPath.parse(body.asInputStream());	
		//System.out.println("Printing ctx    "+ctx.read("$.*"));
		JSONArray qs = ctx.read("$.results.[*]");
		// is this a single quote, or a collection?
		//System.out.println("Printing qs"+qs.toJSONString());
		int i = qs.size();
		//String s = Integer.toString(i-1);
		//int count = getInt(ctx, "$.results.*");
		if (i > 1) {
			return feedsFromJson(ctx);
		}

		return feedFromJson(ctx);
	}

	private Object feedFromJson(ReadContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Feeds> feedsFromJson(ReadContext ctx) {
		// TODO Auto-generated method stub
		
		
		ArrayList<Feeds> feeds = new ArrayList<Feeds>();

		JSONArray qs = ctx.read("$.results");
		for (int i = 0; i < qs.size(); i++) {
			Feeds f = new Feeds();
			//f.setFeedId((String) ctx.read("$.results.["+i+"].feedId"));
			String name = ctx.read("$.results.["+ i +"].feedId");
			if (name != null) {
				f.setFeedId(name);
			}
			feeds.add(f);
		}
		
		
		
		return feeds;
	}

	protected int getInt(ReadContext ctx, String path) {
		// TODO Auto-generated method stub
		Object o = ctx.read(path);
		if (o == null) {
			return 0;
		}
		return NumberUtils.parseNumber(o.toString(), Integer.class);
	}
}
