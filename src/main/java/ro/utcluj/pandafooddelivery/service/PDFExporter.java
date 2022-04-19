package ro.utcluj.pandafooddelivery.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class PDFExporter {



    private final String title = "%s-menu.pdf ";

    public void export(Restaurant restaurant) {
        Document document = new Document();
        PdfPTable table = new PdfPTable(restaurant.getFoodItems().size());

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.format(title, restaurant.getName())));
        } catch (DocumentException e) {
            log.error("PDF file can't pe created. ");
        } catch (FileNotFoundException e) {
            log.error("File not found.");
        }

        document.open();
        addHeader(table);
        addRows(table, restaurant.getFoodItems());

        try {
            document.add(table);
        } catch (DocumentException e) {
            log.error("Cannot write in the file.");
        }
        document.close();
        log.info("PDF was created successfully.");
    }

    private void addHeader(PdfPTable table) {
        Stream.of("Name", "Price", "Category")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<FoodItem> foodItemList) {
        foodItemList.stream().forEach(f -> {
            table.addCell(f.getName());
            table.addCell(Float.toString(f.getPrice()));
            table.addCell(f.getCategory().toString());
        });
    }

}
