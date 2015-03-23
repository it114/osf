package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.dao.FeedDAO;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Follower;

@Service("feedService")
public class FeedService {

	@Autowired
	@Qualifier("followService")
	private FollowService followService;
	
	@Autowired
	@Qualifier("feedDao")
	private FeedDAO feedDao;
	
	@Autowired
	@Qualifier("eventDao")
	private EventDAO eventDao;
	
	public void push(int user_id, int event_id) {
		List<Follower> followers = followService.getFollowers(user_id);
		if(followers != null) {
			for(Follower follower: followers) {
				feedDao.save("feed:user:"+follower.getFollower_user_id(), event_id);
			}
		}
	}
	
	private List<Integer> getEventIDs(int user_id) {
		return feedDao.fetch("feed:user:"+user_id);
	}
	
	public List<Event> getFeeds(int user_id) {
		List<Integer> feeds = getEventIDs(user_id);
		if(feeds != null ) {
			List<Event> events = eventDao.getEvents(feeds);
			if(events == null)
				events = new ArrayList<Event>();
			return events;
		}
		else 
			return new ArrayList<Event>();
	}
		
	public void pull() {
		
	}
	
}