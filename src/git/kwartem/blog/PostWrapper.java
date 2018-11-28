package git.kwartem.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostWrapper {

    Connection connection;

    public PostWrapper(Connection connection){
        this.connection = connection;
    }

    //to do:
    //rewrite to use with pagination;
    public ArrayList<Post> getAllPosts() throws SQLException {

        ArrayList<Post> posts = new ArrayList<Post>();

        PreparedStatement pstatement = connection
                .prepareStatement("select posts.post_id, posts.title, posts.content, users.login " +
                                        "from posts, users where posts.author_id=users.user_id");
        ResultSet resultSet = pstatement.executeQuery();

        while(resultSet.next()){
            Post post = new Post(resultSet.getInt("post_id"),
                                resultSet.getString("title"),
                                resultSet.getString("content"),
                                resultSet.getString("login"));
            posts.add(post);
        }
        System.out.println(posts);
        return posts;
    }

    public RawPost isPostExist(Integer post_id) throws SQLException {

        PreparedStatement pstatement = connection
                .prepareStatement("select post_id, title, content, login, tags from posts, users " +
                                        "where user_id=author_id and post_id=?");
        pstatement.setInt(1, post_id);
        ResultSet resultSet = pstatement.executeQuery();
        System.out.println("0000post-exist: " + resultSet);////!

        if(resultSet.next()){
            RawPost post = new RawPost(resultSet.getInt("post_id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getString("login"),
                    resultSet.getString("tags"));
            return post;
        } else {
            return null;
        }
    }

    public Post getPostDetail(RawPost raw_post) throws SQLException {

        ArrayList<Comment> comments = new ArrayList<Comment>();

        int post_id = raw_post.id; //!?

        PreparedStatement pstatement = connection
                .prepareStatement("select comment_id, comments.content, login, email from comments, users " +
                                        "where comments.author_id=users.user_id and post_id=?");
        pstatement.setInt(1, post_id);
        ResultSet resultSet = pstatement.executeQuery();
        System.out.println("0000post-detail: " + resultSet);////!

        while(resultSet.next()){
            Comment comment = new Comment(resultSet.getInt("comment_id"),
                                            resultSet.getString("content"),
                                            resultSet.getString("login"),
                                            resultSet.getString("email"));
            comments.add(comment);
        }

        Post post = new Post(raw_post);
        post.setComments(comments);

        return post;
    }
}
