package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    private static final String LOCALHOST = "127.0.0.1";
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";
    GeoService geoService = new GeoServiceImpl();

    @Test
    @DisplayName("проверка определения локации по ip")
    void byIpLocalHost() {
        Location location = geoService.byIp(LOCALHOST);
        Assertions.assertNull(location.getCountry());
        Assertions.assertNull(location.getCity());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    @DisplayName("проверка определения локации по ip Москва")
    void byIpMoscow() {
        Location location = geoService.byIp(MOSCOW_IP);
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals("Lenina", location.getStreet());
        Assertions.assertEquals(15, location.getBuiling());
    }

    @Test
    @DisplayName("проверка определения локации по ip Нью-Йорк")
    void byIpNewYork() {
        Location location = geoService.byIp(NEW_YORK_IP);
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(" 10th Avenue", location.getStreet());
        Assertions.assertEquals(32, location.getBuiling());
    }
}

