package User;

import java.io.Serializable;

public class User implements Serializable {
    private final String _userName;
    private final String _password;
    private boolean _loggedIn = false;

    public String get_password() {
        return _password;
    }

    public boolean is_loggedIn() {
        return _loggedIn;
    }

    public void set_loggedIn(boolean _isLoggedIn) {
        this._loggedIn = _isLoggedIn;
    }

    public User(String _userName, String _password) {
        this._userName = _userName;
        this._password = _password;
    }

    public String get_userName() {
        return _userName;
    }

    @Override
    public String toString() {
        return "\n    [ " + this.get_userName() + " " + this._password
                + " ]\n    [ is logged in: " + this._loggedIn + "]\n";
    }
}
