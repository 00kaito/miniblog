//package com.akademiakodu.blog.demo.model.entities.onetomany;
//
//import com.akademiakodu.blog.demo.model.entities.PostComment;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//public class Post {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    String title;
//
//    String content;
//
//    @Temporal(TemporalType.TIMESTAMP) //ta adnotacja mowi na co chcemy to zmapowac w bazie
//    private Date addedDate = new Date();
//
//    @OneToMany (cascade = CascadeType.PERSIST) //propaguje informacje od góry w dół, kiedy dodajemy post, który w momencie dodawania
//    // nie ma jeszcze id, wtedy ta funkcja jest potrzebna
//    @JoinColumn(name = "postId") //hibernate stworzy kolumne postId w tabeli postcomment z celu powiazania tabeli post z postcomment
//    List<PostComment> comments;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Date getAddedDate() {
//        return addedDate;
//    }
//
//    public Post(){}
//
//    public Post(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//    public Post(String title, String content, Date addedDate) {
//        this.title = title;
//        this.content = content;
//        this.addedDate = addedDate;
//    }
//
//    public List<PostComment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<PostComment> comments) {
//        this.comments = comments;
//    }
//
//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", addedDate=" + addedDate +
//                '}';
//    }
//}
