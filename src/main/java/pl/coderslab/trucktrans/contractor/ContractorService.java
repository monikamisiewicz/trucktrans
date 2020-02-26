package pl.coderslab.trucktrans.contractor;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.trucktrans.model.Contractor;

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
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private static final String DIRECTORY = "/Users/monikamisiewicz/Desktop/reports/";
    private static final String FONT = "static/fonts/Goldoni-65vo.ttf";

    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    public boolean createPdf(List<Contractor> contractors, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            File file = new File(DIRECTORY);
            boolean exists = file.exists();
            if (!exists) {
                boolean dirCreated = file.mkdir();
            }


            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "contractors" + ".pdf"));
            document.open();


            Font titleFont = FontFactory.getFont(FONT, 14, BaseColor.GRAY);
            Font dateFont = FontFactory.getFont(FONT, 10, BaseColor.GRAY);
            Font tableHeader = FontFactory.getFont(FONT, 10, BaseColor.WHITE);
            Font tableBody = FontFactory.getFont(FONT, 9, BaseColor.BLACK);

            LocalDate localDate = LocalDate.now();
            Paragraph generateDate = new Paragraph("Generated: " + localDate, dateFont);
            generateDate.setAlignment(Element.ALIGN_LEFT);
            document.add(generateDate);

            Paragraph paragraph = new Paragraph("Contractor list", titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);


            float[] columnWidths = {4f, 2f, 2f, 2f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell name = new PdfPCell(new Paragraph("Name", tableHeader));
            name.setBorderColor(BaseColor.BLACK);
            name.setPaddingLeft(10);
            name.setHorizontalAlignment(Element.ALIGN_CENTER);
            name.setVerticalAlignment(Element.ALIGN_CENTER);
            name.setBackgroundColor(BaseColor.DARK_GRAY);
            name.setExtraParagraphSpace(5);
            table.addCell(name);

            PdfPCell nip = new PdfPCell(new Paragraph("NIP", tableHeader));
            nip.setBorderColor(BaseColor.BLACK);
            nip.setPaddingLeft(10);
            nip.setHorizontalAlignment(Element.ALIGN_CENTER);
            nip.setVerticalAlignment(Element.ALIGN_CENTER);
            nip.setBackgroundColor(BaseColor.DARK_GRAY);
            nip.setExtraParagraphSpace(5);
            table.addCell(nip);

            PdfPCell street = new PdfPCell(new Paragraph("Street", tableHeader));
            street.setBorderColor(BaseColor.BLACK);
            street.setPaddingLeft(10);
            street.setHorizontalAlignment(Element.ALIGN_CENTER);
            street.setVerticalAlignment(Element.ALIGN_CENTER);
            street.setBackgroundColor(BaseColor.DARK_GRAY);
            street.setExtraParagraphSpace(5);
            table.addCell(street);

            PdfPCell streetNumber = new PdfPCell(new Paragraph("Street number", tableHeader));
            streetNumber.setBorderColor(BaseColor.BLACK);
            streetNumber.setPaddingLeft(10);
            streetNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
            streetNumber.setVerticalAlignment(Element.ALIGN_CENTER);
            streetNumber.setBackgroundColor(BaseColor.DARK_GRAY);
            streetNumber.setExtraParagraphSpace(5);
            table.addCell(streetNumber);

            PdfPCell postCode = new PdfPCell(new Paragraph("Post code", tableHeader));
            postCode.setBorderColor(BaseColor.BLACK);
            postCode.setPaddingLeft(10);
            postCode.setHorizontalAlignment(Element.ALIGN_CENTER);
            postCode.setVerticalAlignment(Element.ALIGN_CENTER);
            postCode.setBackgroundColor(BaseColor.DARK_GRAY);
            postCode.setExtraParagraphSpace(5);
            table.addCell(postCode);

            PdfPCell place = new PdfPCell(new Paragraph("Place", tableHeader));
            place.setBorderColor(BaseColor.BLACK);
            place.setPaddingLeft(10);
            place.setHorizontalAlignment(Element.ALIGN_CENTER);
            place.setVerticalAlignment(Element.ALIGN_CENTER);
            place.setBackgroundColor(BaseColor.DARK_GRAY);
            place.setExtraParagraphSpace(5);
            table.addCell(place);

            for (Contractor contractorDoc : contractors) {
                PdfPCell nameValue = new PdfPCell(new Paragraph(contractorDoc.getName(), tableBody));
                nameValue.setBorderColor(BaseColor.BLACK);
                nameValue.setPaddingLeft(10);
                nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
                nameValue.setBackgroundColor(BaseColor.WHITE);
                nameValue.setExtraParagraphSpace(5);
                table.addCell(nameValue);

                PdfPCell nipValue = new PdfPCell(new Paragraph(String.valueOf(contractorDoc.getNip()), tableBody));
                nipValue.setBorderColor(BaseColor.BLACK);
                nipValue.setPaddingLeft(10);
                nipValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                nipValue.setVerticalAlignment(Element.ALIGN_CENTER);
                nipValue.setBackgroundColor(BaseColor.WHITE);
                nipValue.setExtraParagraphSpace(5);
                table.addCell(nipValue);

                PdfPCell streetValue = new PdfPCell(new Paragraph(String.valueOf(contractorDoc.getStreet()), tableBody));
                streetValue.setBorderColor(BaseColor.BLACK);
                streetValue.setPaddingLeft(10);
                streetValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                streetValue.setVerticalAlignment(Element.ALIGN_CENTER);
                streetValue.setBackgroundColor(BaseColor.WHITE);
                streetValue.setExtraParagraphSpace(5);
                table.addCell(streetValue);

                PdfPCell streetNumberValue = new PdfPCell(new Paragraph(contractorDoc.getStreetNumber(), tableBody));
                streetNumberValue.setBorderColor(BaseColor.BLACK);
                streetNumberValue.setPaddingLeft(10);
                streetNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                streetNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
                streetNumberValue.setBackgroundColor(BaseColor.WHITE);
                streetNumberValue.setExtraParagraphSpace(5);
                table.addCell(streetNumberValue);

                PdfPCell postCodeValue = new PdfPCell(new Paragraph(String.valueOf(contractorDoc.getPostCode()), tableBody));
                postCodeValue.setBorderColor(BaseColor.BLACK);
                postCodeValue.setPaddingLeft(10);
                postCodeValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                postCodeValue.setVerticalAlignment(Element.ALIGN_CENTER);
                postCodeValue.setBackgroundColor(BaseColor.WHITE);
                postCodeValue.setExtraParagraphSpace(5);
                table.addCell(postCodeValue);

                PdfPCell placeValue = new PdfPCell(new Paragraph(String.valueOf(contractorDoc.getPlace()), tableBody));
                placeValue.setBorderColor(BaseColor.BLACK);
                placeValue.setPaddingLeft(10);
                placeValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                placeValue.setVerticalAlignment(Element.ALIGN_CENTER);
                placeValue.setBackgroundColor(BaseColor.WHITE);
                placeValue.setExtraParagraphSpace(5);
                table.addCell(placeValue);

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
