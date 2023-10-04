package com.tha103.newview.post.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tha103.newview.likes.model.LikesVO;
import com.tha103.newview.postcategory.model.PostCategoryVO;
import com.tha103.newview.postmessage.model.PostMessageVO;
import com.tha103.newview.report.model.ReportVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "post")
public class PostVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<PostMessageVO> post;

	@OneToMany(mappedBy = "postVO", cascade = CascadeType.ALL)
	private Set<LikesVO> likesVOs;

	@OneToMany(mappedBy = "postVO", cascade = CascadeType.ALL)
	private Set<ReportVO> reportVOs;

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserVO user;

	@ManyToOne
	@JoinColumn(name = "postCategoryID", referencedColumnName = "postCategoryID")
	private PostCategoryVO postCategory;

	@Column(name = "postHeader")
	private String postHeader;

	@Column(name = "postDateTime")
	private Timestamp postDateTime;

	@Column(name = "lastEditedTime")
	private Timestamp lastEditedTime;

	@Column(name = "postContent")
	private String postContent;

	@Column(name = "disLikeCount")
	private Integer disLikeCount;

	@Column(name = "likeCount")
	private Integer likeCount;

	@Column(name = "postStatus", columnDefinition = "tinyint")
	private Integer postStatus;

	public PostVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostVO(Integer postID, Set<PostMessageVO> post, Set<LikesVO> likesVOs, Set<ReportVO> reportVOs, UserVO user,
			PostCategoryVO postCategory, String postHeader, Timestamp postDateTime, Timestamp lastEditedTime,
			String postContent, Integer disLikeCount, Integer likeCount, Integer postStatus) {
		super();
		this.postID = postID;
		this.post = post;
		this.likesVOs = likesVOs;
		this.reportVOs = reportVOs;
		this.user = user;
		this.postCategory = postCategory;
		this.postHeader = postHeader;
		this.postDateTime = postDateTime;
		this.lastEditedTime = lastEditedTime;
		this.postContent = postContent;
		this.disLikeCount = disLikeCount;
		this.likeCount = likeCount;
		this.postStatus = postStatus;
	}

	public Set<PostMessageVO> getPost() {
		return post;
	}

	public void setPost(Set<PostMessageVO> post) {
		this.post = post;
	}

	public UserVO getUser() {
		return user;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public PostCategoryVO getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(PostCategoryVO postCategory) {
		this.postCategory = postCategory;
	}

	public String getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(String postHeader) {
		this.postHeader = postHeader;
	}

	public Timestamp getPostDateTime() {
		return postDateTime;
	}

	public void setPostDateTime(Timestamp postDateTime) {
		this.postDateTime = postDateTime;
	}

	public Timestamp getLastEditedTime() {
		return lastEditedTime;
	}

	public void setLastEditedTime(Timestamp lastEditedTime) {
		this.lastEditedTime = lastEditedTime;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Integer getDisLikeCount() {
		return disLikeCount;
	}

	public void setDisLikeCount(Integer disLikeCount) {
		this.disLikeCount = disLikeCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(Integer postStatus) {
		this.postStatus = postStatus;
	}

	public Set<LikesVO> getLikesVOs() {
		return likesVOs;
	}

	public void setLikesVOs(Set<LikesVO> likesVOs) {
		this.likesVOs = likesVOs;
	}

	public Set<ReportVO> getReportVOs() {
		return reportVOs;
	}

	public void setReportVOs(Set<ReportVO> reportVOs) {
		this.reportVOs = reportVOs;
	}

	@Override
	public String toString() {
		return "PostVO [postID=" + postID + ", post=" + post + ", likesVOs=" + likesVOs + ", reportVOs=" + reportVOs
				+ ", user=" + user + ", postCategory=" + postCategory + ", postHeader=" + postHeader + ", postDateTime="
				+ postDateTime + ", lastEditedTime=" + lastEditedTime + ", postContent=" + postContent
				+ ", disLikeCount=" + disLikeCount + ", likeCount=" + likeCount + ", postStatus=" + postStatus + "]";
	}

}