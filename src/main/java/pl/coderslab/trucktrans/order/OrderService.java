package pl.coderslab.trucktrans.order;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.trucktrans.model.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String DIRECTORY = "/Users/monikamisiewicz/Desktop/reports/";
    private static final String FONT = "static/fonts/FreeSans.ttf";

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public boolean createPdf(List<Order> orders, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            File file = new File(DIRECTORY);
            boolean exists = file.exists();
            if (!exists) {
                boolean dirCreated = file.mkdir();
            }


            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "orders" + ".pdf"));
            document.open();


            Font titleFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 14, Font.NORMAL ,BaseColor.GRAY);
            Font dateFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10, Font.NORMAL ,BaseColor.GRAY);
            Font tableHeader = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10, Font.NORMAL ,BaseColor.WHITE);
            Font tableBody = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 8, Font.NORMAL ,BaseColor.BLACK);

            LocalDate localDate = LocalDate.now();
            Paragraph generateDate = new Paragraph("Generated: " + localDate, dateFont);
            generateDate.setAlignment(Element.ALIGN_LEFT);
            document.add(generateDate);

            Paragraph paragraph = new Paragraph("Order list", titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);


            float[] columnWidths = {2f, 3f, 4f, 3f, 3f, 3f, 3f, 3f, 3f, 3f};
            table.setWidths(columnWidths);


            PdfPCell orderNumber = new PdfPCell(new Paragraph("Order number", tableHeader));
            orderNumber.setBorderColor(BaseColor.BLACK);
            orderNumber.setPaddingLeft(10);
            orderNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
            orderNumber.setVerticalAlignment(Element.ALIGN_CENTER);
            orderNumber.setBackgroundColor(BaseColor.DARK_GRAY);
            orderNumber.setExtraParagraphSpace(5);
            table.addCell(orderNumber);

            PdfPCell date = new PdfPCell(new Paragraph("Date", tableHeader));
            date.setBorderColor(BaseColor.BLACK);
            date.setPaddingLeft(10);
            date.setHorizontalAlignment(Element.ALIGN_CENTER);
            date.setVerticalAlignment(Element.ALIGN_CENTER);
            date.setBackgroundColor(BaseColor.DARK_GRAY);
            date.setExtraParagraphSpace(5);
            table.addCell(date);

            PdfPCell contractor = new PdfPCell(new Paragraph("Contractor", tableHeader));
            contractor.setBorderColor(BaseColor.BLACK);
            contractor.setPaddingLeft(10);
            contractor.setHorizontalAlignment(Element.ALIGN_CENTER);
            contractor.setVerticalAlignment(Element.ALIGN_CENTER);
            contractor.setBackgroundColor(BaseColor.DARK_GRAY);
            contractor.setExtraParagraphSpace(5);
            table.addCell(contractor);


            PdfPCell directionFrom = new PdfPCell(new Paragraph("Direction from", tableHeader));
            directionFrom.setBorderColor(BaseColor.BLACK);
            directionFrom.setPaddingLeft(10);
            directionFrom.setHorizontalAlignment(Element.ALIGN_CENTER);
            directionFrom.setVerticalAlignment(Element.ALIGN_CENTER);
            directionFrom.setBackgroundColor(BaseColor.DARK_GRAY);
            directionFrom.setExtraParagraphSpace(5);
            table.addCell(directionFrom);

            PdfPCell directionTo = new PdfPCell(new Paragraph("Direction to", tableHeader));
            directionTo.setBorderColor(BaseColor.BLACK);
            directionTo.setPaddingLeft(10);
            directionTo.setHorizontalAlignment(Element.ALIGN_CENTER);
            directionTo.setVerticalAlignment(Element.ALIGN_CENTER);
            directionTo.setBackgroundColor(BaseColor.DARK_GRAY);
            directionTo.setExtraParagraphSpace(5);
            table.addCell(directionTo);

            PdfPCell goods = new PdfPCell(new Paragraph("Goods", tableHeader));
            goods.setBorderColor(BaseColor.BLACK);
            goods.setPaddingLeft(10);
            goods.setHorizontalAlignment(Element.ALIGN_CENTER);
            goods.setVerticalAlignment(Element.ALIGN_CENTER);
            goods.setBackgroundColor(BaseColor.DARK_GRAY);
            goods.setExtraParagraphSpace(5);
            table.addCell(goods);

            PdfPCell quantity = new PdfPCell(new Paragraph("Quantity", tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPaddingLeft(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.DARK_GRAY);
            quantity.setExtraParagraphSpace(5);
            table.addCell(quantity);


            PdfPCell unit = new PdfPCell(new Paragraph("Unit", tableHeader));
            unit.setBorderColor(BaseColor.BLACK);
            unit.setPaddingLeft(10);
            unit.setHorizontalAlignment(Element.ALIGN_CENTER);
            unit.setVerticalAlignment(Element.ALIGN_CENTER);
            unit.setBackgroundColor(BaseColor.DARK_GRAY);
            unit.setExtraParagraphSpace(5);
            table.addCell(unit);

            PdfPCell netValue = new PdfPCell(new Paragraph("Net value", tableHeader));
            netValue.setBorderColor(BaseColor.BLACK);
            netValue.setPaddingLeft(10);
            netValue.setHorizontalAlignment(Element.ALIGN_CENTER);
            netValue.setVerticalAlignment(Element.ALIGN_CENTER);
            netValue.setBackgroundColor(BaseColor.DARK_GRAY);
            netValue.setExtraParagraphSpace(5);
            table.addCell(netValue);

            PdfPCell drivers = new PdfPCell(new Paragraph("Drivers", tableHeader));
            drivers.setBorderColor(BaseColor.BLACK);
            drivers.setPaddingLeft(10);
            drivers.setHorizontalAlignment(Element.ALIGN_CENTER);
            drivers.setVerticalAlignment(Element.ALIGN_CENTER);
            drivers.setBackgroundColor(BaseColor.DARK_GRAY);
            drivers.setExtraParagraphSpace(5);
            table.addCell(drivers);

            for (Order orderDoc : orders) {

                PdfPCell orderNumberValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getOrderNumber()), tableBody));
                orderNumberValue.setBorderColor(BaseColor.BLACK);
                orderNumberValue.setPaddingLeft(10);
                orderNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                orderNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
                orderNumberValue.setBackgroundColor(BaseColor.WHITE);
                orderNumberValue.setExtraParagraphSpace(5);
                table.addCell(orderNumberValue);

                PdfPCell dateValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getDate()), tableBody));
                dateValue.setBorderColor(BaseColor.BLACK);
                dateValue.setPaddingLeft(10);
                dateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                dateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                dateValue.setBackgroundColor(BaseColor.WHITE);
                dateValue.setExtraParagraphSpace(5);
                table.addCell(dateValue);

                PdfPCell contractorValue = new PdfPCell(new Paragraph(orderDoc.getContractor().getName(), tableBody));
                contractorValue.setBorderColor(BaseColor.BLACK);
                contractorValue.setPaddingLeft(10);
                contractorValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                contractorValue.setVerticalAlignment(Element.ALIGN_CENTER);
                contractorValue.setBackgroundColor(BaseColor.WHITE);
                contractorValue.setExtraParagraphSpace(5);
                table.addCell(contractorValue);

                PdfPCell directionFromValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getDirectionFrom()), tableBody));
                directionFromValue.setBorderColor(BaseColor.BLACK);
                directionFromValue.setPaddingLeft(10);
                directionFromValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                directionFromValue.setVerticalAlignment(Element.ALIGN_CENTER);
                directionFromValue.setBackgroundColor(BaseColor.WHITE);
                directionFromValue.setExtraParagraphSpace(5);
                table.addCell(directionFromValue);

                PdfPCell directionToValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getDirectionTo()), tableBody));
                directionToValue.setBorderColor(BaseColor.BLACK);
                directionToValue.setPaddingLeft(10);
                directionToValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                directionToValue.setVerticalAlignment(Element.ALIGN_CENTER);
                directionToValue.setBackgroundColor(BaseColor.WHITE);
                directionToValue.setExtraParagraphSpace(5);
                table.addCell(directionToValue);

                PdfPCell goodsValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getGoods()), tableBody));
                goodsValue.setBorderColor(BaseColor.BLACK);
                goodsValue.setPaddingLeft(10);
                goodsValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                goodsValue.setVerticalAlignment(Element.ALIGN_CENTER);
                goodsValue.setBackgroundColor(BaseColor.WHITE);
                goodsValue.setExtraParagraphSpace(5);
                table.addCell(goodsValue);

                PdfPCell quantityValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getQuantity()), tableBody));
                quantityValue.setBorderColor(BaseColor.BLACK);
                quantityValue.setPaddingLeft(10);
                quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityValue.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityValue.setBackgroundColor(BaseColor.WHITE);
                quantityValue.setExtraParagraphSpace(5);
                table.addCell(quantityValue);

                PdfPCell unitValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getUnit()), tableBody));
                unitValue.setBorderColor(BaseColor.BLACK);
                unitValue.setPaddingLeft(10);
                unitValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                unitValue.setVerticalAlignment(Element.ALIGN_CENTER);
                unitValue.setBackgroundColor(BaseColor.WHITE);
                unitValue.setExtraParagraphSpace(5);
                table.addCell(unitValue);

                PdfPCell netValValue = new PdfPCell(new Paragraph(String.valueOf(orderDoc.getValue()), tableBody));
                netValValue.setBorderColor(BaseColor.BLACK);
                netValValue.setPaddingLeft(10);
                netValValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                netValValue.setVerticalAlignment(Element.ALIGN_CENTER);
                netValValue.setBackgroundColor(BaseColor.WHITE);
                netValValue.setExtraParagraphSpace(5);
                table.addCell(netValValue);

                PdfPCell driversValue = new PdfPCell(new Paragraph(orderDoc.getDrivers().toString(), tableBody));
                driversValue.setBorderColor(BaseColor.BLACK);
                driversValue.setPaddingLeft(10);
                driversValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                driversValue.setVerticalAlignment(Element.ALIGN_CENTER);
                driversValue.setBackgroundColor(BaseColor.WHITE);
                driversValue.setExtraParagraphSpace(5);
                table.addCell(driversValue);

            }

            document.add(table);
            document.close();
            writer.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }
}
