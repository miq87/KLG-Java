package pl.miq3l.klgjava;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.miq3l.klgjava.model.Person;
import pl.miq3l.klgjava.model.Place;
import pl.miq3l.klgjava.model.Reservation;
import pl.miq3l.klgjava.service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void getReservationsByPlaceName() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Person landlord = new Person("Jan Kowalski");
        Person tenant = new Person("Magdalena Nowak");
        Place place = new Place("Hilton", 400.0, 80.0, "Opis");

        Reservation reservation = Reservation.builder()
                .tenant(tenant).landlord(landlord)
                .dateFrom(dateFormat.parse("2022-05-01 12:00:00"))
                .dateTo(dateFormat.parse("2022-05-03 18:00:00"))
                .price(800.0).place(place).build();

        reservationService.save(reservation);

        Mockito.when(reservationService.getReservationsByPlaceName("Hilton"))
                .thenReturn(Collections.singletonList(reservation));

        this.mockMvc.perform(get("/api/reservation/place?name=Hilton"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].landlord.name", is("Jan Kowalski")))
                .andExpect(jsonPath("$[0].tenant.name", is("Magdalena Nowak")));

    }

}
