package git.kwartem.blog;

public class Comment {

    private Integer id;
    private String content;
    private String author_login;
    private String author_email;

    public Comment(String content, String author_login, String author_email){
        this.content = content;
        this.author_login = author_login;
        this.author_email = author_email;
    }

    public Comment(Integer comment_id, String content, String author_login, String author_email){
        this(content, author_login, author_email);
        this.id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor_login() {
        return author_login;
    }

    public String getAuthor_email() {
        return author_email;
    }
}
