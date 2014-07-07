package org.sliit.domain;


import org.sliit.service.DbSchema;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Item {
	
	private long mId = -1;
	private URL mLink;
	private String mGuid;
	private String mTitle;
	private String mDescription;
	private String mContent;
	private URL mImage = null;
	private Date mPubdate;
	private boolean mFavorite = false;
	private boolean mRead = false;
	private List<Enclosure> mEnclosures;
	
	public Item() {
		mEnclosures = new ArrayList<Enclosure>();
		mPubdate = new Date();
	}
	
	public void setId(long id) {
		this.mId = id;
	}
	
	public long getId() {
		return mId;
	}
	
	public void setLink(URL link) {
		this.mLink = link;
	}

	public URL getLink() {
		return this.mLink;
	}
	
	public void setGuid(String guid) {
		this.mGuid = guid;
	}
	
	public String getGuid() {
		return mGuid;
	}
	
	public void setTitle(String title) {
		this.mTitle = title;
	}
	
	public String getTitle() {
		return this.mTitle;
	}
	
	public void setDescription(String description) {
		this.mDescription = description;
	}

	public String getDescription() {
		return mDescription;
	}
	
	public void setContent(String content) {
		this.mContent = content;
	}

	public String getContent() {
		return mContent;
	}
	
	public void setImage(URL image) {
		this.mImage = image;
	}

	public URL getImage() {
		return this.mImage;
	}
	
	public void setPubdate(Date pubdate) {
		this.mPubdate = pubdate;
	}

	public Date getPubdate() {
		return this.mPubdate;
	}

	
	public void setFavorite(int state) {
        this.mFavorite = state != DbSchema.OFF;
	}
	
	public boolean isFavorite() {
		return this.mFavorite;
	}
	
	public void setRead(int state) {
        this.mRead = state != DbSchema.OFF;
	}
	
	public boolean isRead() {
		return this.mRead;
	}
	
	public void addEnclosure(Enclosure enclosure) {
		this.mEnclosures.add(enclosure);
	}
	
	public void setEnclosures(List<Enclosure> enclosures){
		this.mEnclosures = enclosures;
	}
	
	public List<Enclosure> getEnclosures() {
		return this.mEnclosures;
	}
	
	public String toString() {
		String s =  "{ID=" + this.mId + " link=" + this.mLink.toString() + " GUID=" + this.mGuid + " title=" + this.mTitle + " description=" + this.mDescription + " content=" + this.mContent + " image=" + this.mImage.toString() + " pubdate=" + this.mPubdate.toString() + " favorite=" + this.mFavorite + " read=" + this.mRead + "}";
		s = s + " items={";
        for (Enclosure mEnclosure : this.mEnclosures) {
            s = s + mEnclosure.toString();
        }
		s = s + "}}";
		return s;
	}
}
