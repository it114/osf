package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.service.TagService;
import com.lvwang.osf.util.Dic;

@Repository("eventDao")
public class EventDAOImpl implements EventDAO{

	private static final String TABLE = "osf_events";
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	
	public int save(final Event event) {
		final String sql = "insert into " + TABLE + " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, event.getObject_type());
				ps.setInt(2, event.getObject_id());
				ps.setTimestamp(3, (Timestamp) event.getTs());
				ps.setInt(4, event.getUser_id());
				ps.setString(5, event.getUser_name());
				ps.setString(6, event.getUser_avatar());
				ps.setInt(7, event.getLike_count());
				ps.setInt(8, event.getShare_count());
				ps.setInt(9, event.getComment_count());
				ps.setString(10, event.getTitle());
				ps.setString(11, event.getSummary());
				ps.setString(12, event.getContent());
				ps.setString(13, TagService.toString(event.getTags()));
				ps.setInt(14, event.getFollowing_user_id());
				ps.setString(15, event.getFollowing_user_name());
				ps.setInt(16, event.getFollower_user_id());
				ps.setString(17, event.getFollower_user_name());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int savePostEvent(final Post post) {
		final String sql = "insert into " + TABLE + "(object_type, object_id, "
					+ "user_id, "
					+ "like_count, share_count, comment_count, "
					+ "title, summary, content,tags) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, Dic.OBJECT_TYPE_POST);
				ps.setInt(2, post.getId());
				ps.setInt(3, post.getPost_author());
				ps.setInt(4, post.getLike_count());
				ps.setInt(5, post.getShare_count());
				ps.setInt(6, post.getComment_count());
				ps.setString(7, post.getPost_title());
				ps.setString(8, post.getPost_excerpt());
				ps.setString(9, null);
				ps.setString(10, TagService.toString(post.getPost_tags()));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<Event> getEvents(List<Integer> event_ids) {
		String sql = "select * from " + TABLE + " where id in (:ids)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", event_ids);
		return namedParaJdbcTemplate.query(sql, paramMap, new RowMapper<Event>() {

			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event event = new Event();
				event.setComment_count(rs.getInt("comment_count"));
				event.setContent(rs.getString("content"));
				event.setFollower_user_id(rs.getInt("follower_user_id"));
				event.setFollower_user_name(rs.getString("follower_user_name"));
				event.setFollowing_user_id(rs.getInt("following_user_id"));
				event.setFollowing_user_name(rs.getString("following_user_name"));
				event.setId(rs.getInt("id"));
				event.setLike_count(rs.getInt("like_count"));
				event.setObject_id(rs.getInt("object_id"));
				event.setObject_type(rs.getInt("object_type"));
				event.setShare_count(rs.getInt("share_count"));
				event.setSummary(rs.getString("summary"));
				event.setTags(TagService.toList(rs.getString("tags")));
				event.setTitle(rs.getString("title"));
				event.setTs(rs.getTimestamp("ts"));
				event.setUser_avatar(rs.getString("user_avatar"));
				event.setUser_id(rs.getInt("user_id"));
				event.setUser_name(rs.getString("user_name"));
				return event;
			}
		});
		
	}
}