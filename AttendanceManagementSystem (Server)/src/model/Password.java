package model;

public class Password {
    private String password;

    public Password(String password) throws IllegalArgumentException {
        isLegal(password);
        this.password = password;
    }

    private void isLegal(String password) throws IllegalArgumentException {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must have at least 6 characters");
        }
        int lower = 0;
        int upper = 0;
        int digit = 0;
        int special = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                digit++;
            } else if (Character.isLowerCase(ch) && Character.isLetter(ch)) {
                lower++;
            } else if (Character.isUpperCase(ch) && Character.isLetter(ch)) {
                upper++;
            } else if (ch == '_' || ch == '-') {
                special++;
            }
        }
        if (lower + upper + digit + special < password.length()) {
            throw new IllegalArgumentException("Password may only contain letters, digits, hyphens amd underscore characters");
        }
        if (lower == 0 || upper == 0 || digit == 0) {
            throw new IllegalArgumentException( "Password must contain at least one uppercase letter, " +
                    "at least one lowercase letter and at least one digit");
        }
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return password;
    }

}
