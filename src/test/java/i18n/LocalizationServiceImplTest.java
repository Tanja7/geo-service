package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;


public class LocalizationServiceImplTest {

    LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    @DisplayName("проверка возвращаемого текста на русском")
    void localeRussia() {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("проверка возвращаемого текста на английском")
    void localeOther() {
        Assertions.assertEquals("Welcome", localizationService.locale(Country.GERMANY));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }
}