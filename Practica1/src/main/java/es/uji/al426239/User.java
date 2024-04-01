package es.uji.al426239;

public class User {
    private String name;
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Username cannot be blank");
        } else {
            if (name.length() < 3) {
                throw new IllegalArgumentException("Username is too short");
            } else if (name.length() > 30) {
                throw new IllegalArgumentException("Username is too long");
            }
        }
        this.name = name;
    }
}
