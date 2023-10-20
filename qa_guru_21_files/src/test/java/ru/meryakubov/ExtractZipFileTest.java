package ru.meryakubov;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExtractZipFileTest {

    ClassLoader cl = ExtractZipFileTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение и проверка CSV файла распакованного из zip")
    void csvExctractTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream("zip.zip");
             ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(zipStream))) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().equals("qa_guru.csv")) {

                    Reader reader = new InputStreamReader(zipInputStream);
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> content = csvReader.readAll();

                    Assertions.assertEquals(3, content.size());
                    Assertions.assertArrayEquals(new String[]{"Teacher", "lesson"}, content.get(0));;
                    Assertions.assertArrayEquals(new String[]{"Tuchs", "Files"}, content.get(1));;
                    Assertions.assertArrayEquals(new String[]{"Vasenkov", "REST Assured"}, content.get(2));;
                }
            }
        }
    }

    @DisplayName("Чтение и проверка содержимого XLSX-файла из ZIP архива")
    @Test
    void xlsxFromZipTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream("zip.zip");
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(zipStream))) {

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals("sample-xlsx-file.xlsx")) {
                    XLS xls = new XLS(zis);

                    Assertions.assertEquals("United States",
                            xls.excel.getSheetAt(0).
                                    getRow(1)
                                    .getCell(4)
                                    .getStringCellValue());
                }
            }
        }
    }

    @DisplayName("Чтение и проверка pdf файла распакованного из zip")
    @Test
    void pdfExctractTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream("zip.zip");
             ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(zipStream))) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().equals("Git шпора_)-1.pdf")) {
                    PDF pdf = new PDF(zipInputStream);
                    Assertions.assertTrue(pdf.text.contains("Шпаргалка по Git"));
                }
            }
        }
    }
}
