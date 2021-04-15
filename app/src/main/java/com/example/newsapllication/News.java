package com.example.newsapllication;

public class News {

    String header, image, link;


    public News() {
    }

    public News(String header, String image, String link) {
        this.header = header;
        this.image = image;
        this.link = link;
    }

    @Override
    public String toString() {
        return "News{" +
                "header='" + header + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
