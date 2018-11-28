package git.kwartem.blog;

public class RawUser {

    protected String login;

    public RawUser(){}

    public RawUser(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
