package data;

public enum Language {
    RU("Регистрация"),
    EN("Sign Up");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
