package common.data;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class LanguageData {

    @CsvBindByName
    private String languageCode;

    @CsvBindByName
    private String browse;

    @CsvBindByName
    private String login;

    @CsvBindByName
    private String register;

    @CsvBindByName
    private String active;

    @CsvBindByName
    private String live;

    @CsvBindByName
    private String signUpButton;

    @CsvBindByName
    private String loginButton;

    @CsvBindByName
    private String browseLink;

    @CsvBindByName
    private String linkToActive;

    @CsvBindByName
    private String liveIndicator;
}
