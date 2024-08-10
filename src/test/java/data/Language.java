package data;

public enum Language {
    RU("Просмотр", "Войти", "Регистрация", "Активные каналы", "В ЭФИРЕ"),
    EN("Browse", "Log In", "Sign Up", "Live channels", "Live");

    public final String login;
    public final String register;
    public final String browse;
    public final String active;
    public final String live;

    Language(String browse, String login, String register, String active, String live) {
        this.browse = browse;
        this.login = login;
        this.register = register;
        this.active = active;
        this.live = live;
    }
}
