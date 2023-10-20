package ru.meryakubov;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CarsModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonParseTest {
    private static final ClassLoader cl = ExtractZipFileTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение и проверка содержимого файла .json")
    public void testJsonParsing() throws IOException {

        try (InputStream stream = cl.getResourceAsStream("glossary.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<CarsModel> carsModel = objectMapper.readValue(stream, new TypeReference<>() {
            });

            Assertions.assertThat(carsModel).hasSize(3);

            CarsModel firstCar = carsModel.get(0);
            Assertions.assertThat(firstCar.getId()).isEqualTo(1);
            Assertions.assertThat(firstCar.getCarBrand()).isEqualTo("BMW");
            Assertions.assertThat(firstCar.getCarModel()).isEqualTo("5");
            Assertions.assertThat(firstCar.getCountry()).isEqualTo("Germany");

            CarsModel secondCar = carsModel.get(1);
            Assertions.assertThat(secondCar.getId()).isEqualTo(2);
            Assertions.assertThat(secondCar.getCarBrand()).isEqualTo("FORD");
            Assertions.assertThat(secondCar.getCarModel()).isEqualTo("Mustang");
            Assertions.assertThat(secondCar.getCountry()).isEqualTo("United States");

            CarsModel thirdCar = carsModel.get(2);
            Assertions.assertThat(thirdCar.getId()).isEqualTo(3);
            Assertions.assertThat(thirdCar.getCarBrand()).isEqualTo("LADA");
            Assertions.assertThat(thirdCar.getCarModel()).isEqualTo("Granta");
            Assertions.assertThat(thirdCar.getCountry()).isEqualTo("Russia");
        }
    }
}
