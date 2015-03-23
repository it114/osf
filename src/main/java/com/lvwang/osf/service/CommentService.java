package com.lvwang.osf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.CommentDAO;
import com.lvwang.osf.model.Comment;
import com.lvwang.osf.util.Property;

@Service("commentService")
public class CommentService {

	public static final int COMMENT_TYPE_POST = 0;
	public static final int COMMENT_TYPE_PHOTO = 1;
	public static final int COMMENT_TYPE_ALBUM = 2;
	
	public static final String TYPE_POST = "post";
	public static final String TYPE_PHOTO = "photo";
	public static final String TYPE_ALBUM = "post";
	
	@Autowired
	@Qualifier("commentDao")
	private CommentDAO commentDao;
	
	public Map<String, String> newComment(Integer comment_object_type, Integer comment_object_id,
							 Integer comment_author, String comment_author_email, 
							 String comment_content, Integer comment_parent, String comment_parent_email){
		
		Map<String, String> ret = new HashMap<String, String>();
		if(comment_content == null || comment_content.length() == 0) {
			ret.put("status", Property.ERROR_COMMENT_EMPTY);
			return ret;
		}
		if(comment_object_type != COMMENT_TYPE_POST &&
		   comment_object_type != COMMENT_TYPE_PHOTO &&
		   comment_object_type != COMMENT_TYPE_ALBUM) {
			ret.put("status", Property.ERROR_COMMENT_TYPE);
			return ret;
		}
		Comment comment = new Comment();
		comment.setComment_object_type(comment_object_type);
		comment.setComment_object_id(comment_object_id);
		comment.setComment_author(comment_author);
		comment.setComment_author_email(comment_author_email);
		comment.setComment_content(comment_content);
		comment.setComment_parent(comment_parent);
		comment.setComment_parent_email(comment_parent_email);
		int id = commentDao.save(comment);
		ret.put("status", Property.SUCCESS_COMMENT_CREATE);
		ret.put("id", String.valueOf(id));
		return ret;
		
	}
	
	public Comment findCommentByID(int id) {
		return commentDao.getCommentByID(id);
	}
	
	public List<Comment> getComments(String type, int id) {
		if(type == null || type.length() == 0)
			return null;
		if(type.equals(TYPE_POST)) {
			return commentDao.getCommentsOfPost(id);
		} else if(type.equals(TYPE_PHOTO)) {
			return commentDao.getCommentsOfPhoto(id);
		} else if(type.equals(TYPE_ALBUM)){
			return commentDao.getCommentsOfAlbum(id);
		} 
		return null;
	}
}