package com.akakim.bluehousereaderapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class BoardData implements Parcelable{


    public static final String BEST_BOARD_TAG           = "bestBoardItem";
    public static final String BOARD_ITEMS_KEY          = "boardItems";
    public static final String READY_ANSWER_BOARD_TAG   = "arriveBoardItem";
    public static final String NORMAL_BOARD_TAG         = "normalBoardItem";


    private String boardTag;
    private String numberOfContent;         // 0 인경우는 베스트 청원임.
    private String boardIdx;                // 게시판 순서
    private String category;                // 게시판 종류
    private String title;                   // 게시판 제목
    private String author;                  // 저자
    private String term;                    // 청원 기간
    private String numberOfJoinPeople;      // 청원에 참여한 인원
    private String link;                    // 게시판 링크

    String thumbnailContent;

    public BoardData(){
        this.boardIdx               = "";
        this.boardTag               = "";
        this.numberOfContent        = "";
        this.category               = "";
        this.title                  = "";
        this.author                 = "";
        this. term                  = "";
        this. numberOfJoinPeople    = "";
        this.link                   = "";
        this.thumbnailContent       = "";
    }
    protected BoardData(Parcel in) {
        boardTag            = in.readString();
        numberOfContent     = in.readString();
        boardIdx            = in.readString();
        category            = in.readString();
        title               = in.readString();
        author              = in.readString();
        term                = in.readString();
        numberOfJoinPeople  = in.readString();
        link                = in.readString();
        thumbnailContent    = in.readString();
    }

    public static final Creator<BoardData> CREATOR = new Creator<BoardData>() {
        @Override
        public BoardData createFromParcel(Parcel in) {
            return new BoardData(in);
        }

        @Override
        public BoardData[] newArray(int size) {
            return new BoardData[size];
        }
    };

    public String getBoardTag() {
        return boardTag;
    }

    public void setBoardTag(String boardTag) {
        this.boardTag = boardTag;
    }

    public String getNumberOfContent() {
        return numberOfContent;
    }

    public void setNumberOfContent(String numberOfContent) {
        this.numberOfContent = numberOfContent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getNumberOfJoinPeople() {
        return numberOfJoinPeople;
    }

    public void setNumberOfJoinPeople(String numberOfJoinPeople) {
        this.numberOfJoinPeople = numberOfJoinPeople;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getThumbnailContent() {
        return thumbnailContent;
    }

    public void setThumbnailContent(String thumbnailContent) {
        this.thumbnailContent = thumbnailContent;
    }

    public String getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(String boardIdx) {
        this.boardIdx = boardIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(boardTag);
        dest.writeString(numberOfContent);
        dest.writeString(boardIdx);
        dest.writeString(category);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(term);
        dest.writeString(numberOfJoinPeople);
        dest.writeString(link);
        dest.writeString(thumbnailContent);
    }

    @Override
    public String toString() {
       return   "[boardIdx] : " + boardIdx
              + "[boardTag]"+ boardTag
              + "[category]"+ category
               + "[numberOfContent]"+ numberOfContent
               + "[title]"+ title
               + "[author]"+ author
               + "[term]"+ term
               + "[numberOfJoinPeople]"+ numberOfJoinPeople
               + "[link]"+ link
               + "[thumbnailContent]"+ thumbnailContent               ;
    }
}
