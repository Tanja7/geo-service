package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {
    @Mock
    private GeoService geoService;
    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
    }

    @Test
    @DisplayName("проверка русского текста при Российском ip")
    void sendRussia() {
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.2.44.38");
        String expectedResult = messageSender.send(headers);
        String actualResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("проверка английского текста при USA ip")
    void sendUSA() {
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(
                new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.27.144.338");
        String expectedResult = messageSender.send(headers);
        String actualResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

}