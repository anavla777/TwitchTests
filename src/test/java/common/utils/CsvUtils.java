package common.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import common.data.LanguageData;
import org.junit.jupiter.params.provider.Arguments;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CsvUtils {
    public static Stream<Arguments> provideLanguageData() {
        List<LanguageData> dataList = new CsvToBeanBuilder<LanguageData>(
                new InputStreamReader((Objects.requireNonNull(CsvUtils.class
                        .getResourceAsStream("/files/localization.csv"))), StandardCharsets.UTF_8))
                .withIgnoreQuotations(false)
                .withType(LanguageData.class)
                .withSeparator(',')
                .build()
                .parse();
        return dataList.stream().map(Arguments::of);
    }
}
