package com.lec.beans;

public class FileDTO {
	private int uid; // bf_uid
	private String sourse; // bf_sourse
	private String file; // bf_file
	private boolean isImage; // 이미지 여부 
	
	public FileDTO() {
		super();
	}

	public FileDTO(int uid, String sourse, String file, boolean isImage) {
		super();
		this.uid = uid;
		this.sourse = sourse;
		this.file = file;
		this.isImage = isImage;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSourse() {
		return sourse;
	}

	public void setSourse(String sourse) {
		this.sourse = sourse;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	// boolean 타입 으로 만들경우에는 getter 가 is 로 
	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	
	
}
