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
    //rewrite to use with pagination
    public ArrayList<Post> getAllPosts() throws SQLException {

        ArrayList<Post> posts = new ArrayList<Post>();

        PreparedStatement pstatement = connection.prepareStatement("select * from posts");
        ResultSet resultSet = pstatement.executeQuery();

        while(resultSet.next()){
            Post post = new Post(resultSet.getString("title"),
                                resultSet.getString("content"),
                                resultSet.getInt("author_id"));
            posts.add(post);
        }

        return posts;
    }
}
