package git.kwartem.blog;

import java.util.ArrayList;

public class Post {

    private String title;
    private String content;
    private int author_id;

    private ArrayList<Tag> tags = new ArrayList<Tag>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    public Post(){}

    public Post(String title, String content, int author_id){
        this.title = title;
        this.content = content;
        this.author_id = author_id;
    }

}
