package User;

public class User {
    private final String _userName;
    private final String _password;

    public User(String _userName, String _password) {
        this._userName = _userName;
        this._password = _password;
    }

    public String get_userName() {
        return _userName;
    }

    @Override
    public String toString() {
        return this.get_userName() +
                "    " + this._password;
    }
}
