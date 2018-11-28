package git.kwartem.blog;

import java.util.ArrayList;

public class Post extends RawPost{

    //private ArrayList<Tag> tags = new ArrayList<Tag>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    public Post(){}

    public Post(Integer post_id, String title, String content, String author){
        super(post_id, title, content, author);
    }

    public Post(RawPost rawPost){
        super(rawPost.id, rawPost.title, rawPost.content, rawPost.author);
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
