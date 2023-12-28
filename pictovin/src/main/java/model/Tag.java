package model;

public class Tag {
	private int postId; // 기본키
	private String studio;
	private String concept;
	private String people;
	private String ageGroup;
	
	public Tag() {}
	
	public Tag(int postId, String studio, String concept, String people, String ageGroup) {
		super();
		this.postId = postId;
		this.studio = studio;
		this.concept = concept;
		this.people = people;
		this.ageGroup = ageGroup;
	}
	
	// 질의용. 추가
	public Tag(String studio, String concept, String people, String ageGroup) {
		super();
		this.studio = studio;
		this.concept = concept;
		this.people = people;
		this.ageGroup = ageGroup;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	@Override
	public String toString() {
		return "Tag [postId=" + postId + ", studio=" + studio + ", concept=" + concept + ", people=" + people
				+ ", ageGroup=" + ageGroup + "]";
	}
	

}
