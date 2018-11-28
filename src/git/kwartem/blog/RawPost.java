package git.kwartem.blog;

public class RawPost {

    protected Integer id;
    protected String title;
    protected String content;
    protected String author;
    protected String tags;

    public RawPost(){}

    public RawPost(Integer post_id, String title, String content, String author){
        this.id = post_id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public RawPost(Integer post_id, String title, String content, String author, String tags){
        this(post_id, title, content, author);
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() { return author; }

    public String getContent() {
        return content;
    }

    public String getContent(int length) {
        if(length > content.length() - 5){
            length = length - 5;
        }
        return content.substring(0, length).concat("...");
    }

    public String getTags() { return tags; }
}
