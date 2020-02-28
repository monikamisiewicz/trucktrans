package pl.coderslab.trucktrans.invoice;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.trucktrans.item.ItemRepository;
import pl.coderslab.trucktrans.model.Invoice;
import pl.coderslab.trucktrans.model.Item;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ItemRepository itemRepository;
    private static final String DIRECTORY = "/Users/monikamisiewicz/Desktop/reports/";
    private static final String FONT = "static/fonts/FreeSans.ttf";
    private static final String IMG = "static/images/logo3.png";

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Invoice getById(long id) {
        return invoiceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public boolean createPdf(List<Invoice> invoices, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            File file = new File(DIRECTORY);
            boolean exists = file.exists();
            if (!exists) {
                boolean dirCreated = file.mkdir();
            }


            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "invoices" + ".pdf"));
            document.open();

            Font titleFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 14, Font.NORMAL ,BaseColor.GRAY);
            Font dateFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10, Font.NORMAL ,BaseColor.GRAY);
            Font tableHeader = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10, Font.NORMAL ,BaseColor.WHITE);
            Font tableBody = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 9, Font.NORMAL ,BaseColor.BLACK);
            Font redFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 9, Font.NORMAL ,BaseColor.RED);
            Font greenFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 9, Font.NORMAL ,BaseColor.GREEN);

            LocalDate localDate = LocalDate.now();
            Paragraph generateDate = new Paragraph("Generated: " + localDate, dateFont);
            generateDate.setAlignment(Element.ALIGN_LEFT);
            document.add(generateDate);

            Paragraph paragraph = new Paragraph("Invoice list", titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);


            float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell invoiceNumber = new PdfPCell(new Paragraph("Invoice number", tableHeader));
            invoiceNumber.setBorderColor(BaseColor.BLACK);
            invoiceNumber.setPaddingLeft(10);
            invoiceNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceNumber.setVerticalAlignment(Element.ALIGN_CENTER);
            invoiceNumber.setBackgroundColor(BaseColor.DARK_GRAY);
            invoiceNumber.setExtraParagraphSpace(5);
            table.addCell(invoiceNumber);

            PdfPCell invoiceDate = new PdfPCell(new Paragraph("Invoice date", tableHeader));
            invoiceDate.setBorderColor(BaseColor.BLACK);
            invoiceDate.setPaddingLeft(10);
            invoiceDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceDate.setVerticalAlignment(Element.ALIGN_CENTER);
            invoiceDate.setBackgroundColor(BaseColor.DARK_GRAY);
            invoiceDate.setExtraParagraphSpace(5);
            table.addCell(invoiceDate);

            PdfPCell paymentDate = new PdfPCell(new Paragraph("Payment date", tableHeader));
            paymentDate.setBorderColor(BaseColor.BLACK);
            paymentDate.setPaddingLeft(10);
            paymentDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            paymentDate.setVerticalAlignment(Element.ALIGN_CENTER);
            paymentDate.setBackgroundColor(BaseColor.DARK_GRAY);
            paymentDate.setExtraParagraphSpace(5);
            table.addCell(paymentDate);

            PdfPCell contractor = new PdfPCell(new Paragraph("Contractor", tableHeader));
            contractor.setBorderColor(BaseColor.BLACK);
            contractor.setPaddingLeft(10);
            contractor.setHorizontalAlignment(Element.ALIGN_CENTER);
            contractor.setVerticalAlignment(Element.ALIGN_CENTER);
            contractor.setBackgroundColor(BaseColor.DARK_GRAY);
            contractor.setExtraParagraphSpace(5);
            table.addCell(contractor);

            PdfPCell netAmount = new PdfPCell(new Paragraph("Net amount", tableHeader));
            netAmount.setBorderColor(BaseColor.BLACK);
            netAmount.setPaddingLeft(10);
            netAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            netAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            netAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            netAmount.setExtraParagraphSpace(5);
            table.addCell(netAmount);

            PdfPCell vatRate = new PdfPCell(new Paragraph("Vat rate %", tableHeader));
            vatRate.setBorderColor(BaseColor.BLACK);
            vatRate.setPaddingLeft(10);
            vatRate.setHorizontalAlignment(Element.ALIGN_CENTER);
            vatRate.setVerticalAlignment(Element.ALIGN_CENTER);
            vatRate.setBackgroundColor(BaseColor.DARK_GRAY);
            vatRate.setExtraParagraphSpace(5);
            table.addCell(vatRate);

            PdfPCell vatAmount = new PdfPCell(new Paragraph("Vat amount", tableHeader));
            vatAmount.setBorderColor(BaseColor.BLACK);
            vatAmount.setPaddingLeft(10);
            vatAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            vatAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            vatAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            vatAmount.setExtraParagraphSpace(5);
            table.addCell(vatAmount);

            PdfPCell grossAmount = new PdfPCell(new Paragraph("Gross amount", tableHeader));
            grossAmount.setBorderColor(BaseColor.BLACK);
            grossAmount.setPaddingLeft(10);
            grossAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            grossAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            grossAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            grossAmount.setExtraParagraphSpace(5);
            table.addCell(grossAmount);

            PdfPCell status = new PdfPCell(new Paragraph("Status", tableHeader));
            status.setBorderColor(BaseColor.BLACK);
            status.setPaddingLeft(10);
            status.setHorizontalAlignment(Element.ALIGN_CENTER);
            status.setVerticalAlignment(Element.ALIGN_CENTER);
            status.setBackgroundColor(BaseColor.DARK_GRAY);
            status.setExtraParagraphSpace(5);
            table.addCell(status);

            for (Invoice invoiceDoc : invoices) {
                PdfPCell invoiceNumberValue = new PdfPCell(new Paragraph(invoiceDoc.getInvoiceNumber(), tableBody));
                invoiceNumberValue.setBorderColor(BaseColor.BLACK);
                invoiceNumberValue.setPaddingLeft(10);
                invoiceNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                invoiceNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
                invoiceNumberValue.setBackgroundColor(BaseColor.WHITE);
                invoiceNumberValue.setExtraParagraphSpace(5);
                table.addCell(invoiceNumberValue);

                PdfPCell invoiceDateValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getInvoiceDate()), tableBody));
                invoiceDateValue.setBorderColor(BaseColor.BLACK);
                invoiceDateValue.setPaddingLeft(10);
                invoiceDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                invoiceDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                invoiceDateValue.setBackgroundColor(BaseColor.WHITE);
                invoiceDateValue.setExtraParagraphSpace(5);
                table.addCell(invoiceDateValue);

                PdfPCell paymentDateValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getPaymentDate()), tableBody));
                paymentDateValue.setBorderColor(BaseColor.BLACK);
                paymentDateValue.setPaddingLeft(10);
                paymentDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                paymentDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                paymentDateValue.setBackgroundColor(BaseColor.WHITE);
                paymentDateValue.setExtraParagraphSpace(5);
                table.addCell(paymentDateValue);

                PdfPCell contractorValue = new PdfPCell(new Paragraph(invoiceDoc.getContractor().getName(), tableBody));
                contractorValue.setBorderColor(BaseColor.BLACK);
                contractorValue.setPaddingLeft(10);
                contractorValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                contractorValue.setVerticalAlignment(Element.ALIGN_CENTER);
                contractorValue.setBackgroundColor(BaseColor.WHITE);
                contractorValue.setExtraParagraphSpace(5);
                table.addCell(contractorValue);

                PdfPCell netAmountValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getTotalNetAmount()), tableBody));
                netAmountValue.setBorderColor(BaseColor.BLACK);
                netAmountValue.setPaddingLeft(10);
                netAmountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                netAmountValue.setVerticalAlignment(Element.ALIGN_CENTER);
                netAmountValue.setBackgroundColor(BaseColor.WHITE);
                netAmountValue.setExtraParagraphSpace(5);
                table.addCell(netAmountValue);

                PdfPCell vatRateValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getVatRateInPercent() + "%"), tableBody));
                vatRateValue.setBorderColor(BaseColor.BLACK);
                vatRateValue.setPaddingLeft(10);
                vatRateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                vatRateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                vatRateValue.setBackgroundColor(BaseColor.WHITE);
                vatRateValue.setExtraParagraphSpace(5);
                table.addCell(vatRateValue);

                PdfPCell vatAmountValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getTotalVatAmount()), tableBody));
                vatAmount.setBorderColor(BaseColor.BLACK);
                vatAmount.setPaddingLeft(10);
                vatAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
                vatAmount.setVerticalAlignment(Element.ALIGN_CENTER);
                vatAmount.setBackgroundColor(BaseColor.WHITE);
                vatAmount.setExtraParagraphSpace(5);
                table.addCell(vatAmountValue);

                PdfPCell grossAmountValue = new PdfPCell(new Paragraph(String.valueOf(invoiceDoc.getTotalGrossAmount()), tableBody));
                grossAmountValue.setBorderColor(BaseColor.BLACK);
                grossAmountValue.setPaddingLeft(10);
                grossAmountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                grossAmountValue.setVerticalAlignment(Element.ALIGN_CENTER);
                grossAmountValue.setBackgroundColor(BaseColor.WHITE);
                grossAmountValue.setExtraParagraphSpace(5);
                table.addCell(grossAmountValue);


                Paragraph isPaidVal;
                PdfPCell statusValue;
                if (invoiceDoc.getIsPaid()) {
                    isPaidVal = new Paragraph("Paid");
                    statusValue = new PdfPCell(new Paragraph(String.valueOf(isPaidVal), greenFont));
                } else {
                    isPaidVal = new Paragraph("Not Paid");
                    statusValue = new PdfPCell(new Paragraph(String.valueOf(isPaidVal), redFont));
                }

                statusValue.setBorderColor(BaseColor.BLACK);
                statusValue.setPaddingLeft(10);
                statusValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                statusValue.setVerticalAlignment(Element.ALIGN_CENTER);
                statusValue.setBackgroundColor(BaseColor.WHITE);
                statusValue.setExtraParagraphSpace(5);
                table.addCell(statusValue);

            }

            document.add(table);
            document.close();
            writer.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean createPdfInvoiceDetails(Invoice invoice, List<Item> items, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            File file = new File(DIRECTORY);
            boolean exists = file.exists();
            if (!exists) {
                boolean dirCreated = file.mkdir();
            }


            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "invoice" + ".pdf"));
            document.open();

//            Image logo = Image.getInstance(IMG);
//            document.add(logo);


            Font titleFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 14, Font.NORMAL ,BaseColor.GRAY);
            Font tableHeader = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10, Font.NORMAL ,BaseColor.WHITE);
            Font tableBody = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 9, Font.NORMAL ,BaseColor.BLACK);


            Paragraph paragraph = new Paragraph("Invoice no. " + invoice.getInvoiceNumber(), titleFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);


            PdfPTable detailsTable = new PdfPTable(2);
            detailsTable.setWidthPercentage(100);
            detailsTable.setSpacingBefore(20f);
            detailsTable.setSpacingAfter(10);

            float[] columnWidth = {4f, 4f};
            detailsTable.setWidths(columnWidth);


            PdfPCell supplier = new PdfPCell(new Paragraph("SUPPLIER", tableHeader));
            supplier.setBorderColor(BaseColor.BLACK);
            supplier.setPaddingLeft(10);
            supplier.setHorizontalAlignment(Element.ALIGN_CENTER);
            supplier.setVerticalAlignment(Element.ALIGN_CENTER);
            supplier.setBackgroundColor(BaseColor.DARK_GRAY);
            supplier.setExtraParagraphSpace(5);
            detailsTable.addCell(supplier);

            PdfPCell customer = new PdfPCell(new Paragraph("CUSTOMER", tableHeader));
            customer.setBorderColor(BaseColor.BLACK);
            customer.setPaddingLeft(10);
            customer.setHorizontalAlignment(Element.ALIGN_CENTER);
            customer.setVerticalAlignment(Element.ALIGN_CENTER);
            customer.setBackgroundColor(BaseColor.DARK_GRAY);
            customer.setExtraParagraphSpace(5);
            detailsTable.addCell(customer);


            PdfPCell supplierName = new PdfPCell(new Paragraph("Name: " + invoice.getCompany().getName(), tableBody));
            detailsTable.addCell(supplierName);
            PdfPCell customerName = new PdfPCell(new Paragraph("Name: " + invoice.getContractor().getName(), tableBody));
            detailsTable.addCell(customerName);

            PdfPCell supplierNip = new PdfPCell(new Paragraph("NIP: " + invoice.getCompany().getNip(), tableBody));
            detailsTable.addCell(supplierNip);
            PdfPCell customerNip = new PdfPCell(new Paragraph("NIP: " + invoice.getContractor().getNip(), tableBody));
            detailsTable.addCell(customerNip);

            PdfPCell supplierStreet = new PdfPCell(new Paragraph(invoice.getCompany().getStreet() + " " + invoice.getCompany().getStreetNumber(), tableBody));
            detailsTable.addCell(supplierStreet);
            PdfPCell customerStreet = new PdfPCell(new Paragraph(invoice.getContractor().getStreet() + " " + invoice.getContractor().getStreetNumber(), tableBody));
            detailsTable.addCell(customerStreet);

            PdfPCell supplierPlace = new PdfPCell(new Paragraph(invoice.getCompany().getPostCode() + " " + invoice.getCompany().getPlace(), tableBody));
            detailsTable.addCell(supplierPlace);
            PdfPCell customerPlace = new PdfPCell(new Paragraph(invoice.getContractor().getPostCode() + " " + invoice.getContractor().getPlace(), tableBody));
            detailsTable.addCell(customerPlace);

            document.add(detailsTable);

            PdfPTable paymentTable = new PdfPTable(2);
            paymentTable.setWidthPercentage(100);
            paymentTable.setSpacingBefore(10f);
            paymentTable.setSpacingAfter(10);

            paymentTable.setWidths(columnWidth);

            PdfPCell paymentData = new PdfPCell(new Paragraph("", tableHeader));
            paymentData.setBorderColor(BaseColor.BLACK);
            paymentData.setPaddingLeft(10);
            paymentData.setHorizontalAlignment(Element.ALIGN_CENTER);
            paymentData.setVerticalAlignment(Element.ALIGN_CENTER);
            paymentData.setBackgroundColor(BaseColor.DARK_GRAY);
            paymentData.setExtraParagraphSpace(5);
            paymentTable.addCell(paymentData);

            PdfPCell invoiceData = new PdfPCell(new Paragraph("PAYMENT DETAILS", tableHeader));
            invoiceData.setBorderColor(BaseColor.BLACK);
            invoiceData.setPaddingLeft(10);
            invoiceData.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceData.setVerticalAlignment(Element.ALIGN_CENTER);
            invoiceData.setBackgroundColor(BaseColor.DARK_GRAY);
            invoiceData.setExtraParagraphSpace(5);
            paymentTable.addCell(invoiceData);

            PdfPCell issuePlace = new PdfPCell(new Paragraph("Place of issue: " + invoice.getPlace(), tableBody));
            paymentTable.addCell(issuePlace);
            PdfPCell paymentDate = new PdfPCell(new Paragraph("Payment date: " + invoice.getPaymentDate(), tableBody));
            paymentTable.addCell(paymentDate);

            PdfPCell invoiceDate = new PdfPCell(new Paragraph("Date of invoice: " + invoice.getInvoiceDate(), tableBody));
            paymentTable.addCell(invoiceDate);
            PdfPCell paymentMethod = new PdfPCell(new Paragraph("Payment method: " + invoice.getPaymentMethod(), tableBody));
            paymentTable.addCell(paymentMethod);

            PdfPCell serviceDate = new PdfPCell(new Paragraph("Date of service: " + invoice.getServiceDate(), tableBody));
            paymentTable.addCell(serviceDate);
            PdfPCell bank = new PdfPCell(new Paragraph("Bank: " + invoice.getCompany().getBank(), tableBody));
            paymentTable.addCell(bank);

            PdfPCell empty = new PdfPCell(new Paragraph("", tableBody));
            paymentTable.addCell(empty);
            PdfPCell iban = new PdfPCell(new Paragraph("Account number: " + invoice.getCompany().getIban(), tableBody));
            paymentTable.addCell(iban);

            document.add(paymentTable);

            PdfPCell cell = new PdfPCell();

            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            float[] columnWidths = {1f, 3f, 1f, 2f, 2f, 2f, 1f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell num = new PdfPCell(new Paragraph("#", tableHeader));
            num.setBorderColor(BaseColor.BLACK);
            num.setPaddingLeft(10);
            num.setHorizontalAlignment(Element.ALIGN_CENTER);
            num.setVerticalAlignment(Element.ALIGN_CENTER);
            num.setBackgroundColor(BaseColor.DARK_GRAY);
            num.setExtraParagraphSpace(5);
            table.addCell(num);

            PdfPCell serviceDescription = new PdfPCell(new Paragraph("Goods/Service description", tableHeader));
            serviceDescription.setBorderColor(BaseColor.BLACK);
            serviceDescription.setPaddingLeft(10);
            serviceDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
            serviceDescription.setVerticalAlignment(Element.ALIGN_CENTER);
            serviceDescription.setBackgroundColor(BaseColor.DARK_GRAY);
            serviceDescription.setExtraParagraphSpace(5);
            table.addCell(serviceDescription);

            PdfPCell unit = new PdfPCell(new Paragraph("Unit", tableHeader));
            unit.setBorderColor(BaseColor.BLACK);
            unit.setPaddingLeft(10);
            unit.setHorizontalAlignment(Element.ALIGN_CENTER);
            unit.setVerticalAlignment(Element.ALIGN_CENTER);
            unit.setBackgroundColor(BaseColor.DARK_GRAY);
            unit.setExtraParagraphSpace(5);
            table.addCell(unit);

            PdfPCell quantity = new PdfPCell(new Paragraph("Quantity", tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPaddingLeft(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.DARK_GRAY);
            quantity.setExtraParagraphSpace(5);
            table.addCell(quantity);

            PdfPCell unitPrice = new PdfPCell(new Paragraph("Unit price", tableHeader));
            unitPrice.setBorderColor(BaseColor.BLACK);
            unitPrice.setPaddingLeft(10);
            unitPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
            unitPrice.setVerticalAlignment(Element.ALIGN_CENTER);
            unitPrice.setBackgroundColor(BaseColor.DARK_GRAY);
            unitPrice.setExtraParagraphSpace(5);
            table.addCell(unitPrice);

            PdfPCell netAmount = new PdfPCell(new Paragraph("Net amount", tableHeader));
            netAmount.setBorderColor(BaseColor.BLACK);
            netAmount.setPaddingLeft(10);
            netAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            netAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            netAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            netAmount.setExtraParagraphSpace(5);
            table.addCell(netAmount);

            PdfPCell vatRate = new PdfPCell(new Paragraph("Vat rate %", tableHeader));
            vatRate.setBorderColor(BaseColor.BLACK);
            vatRate.setPaddingLeft(10);
            vatRate.setHorizontalAlignment(Element.ALIGN_CENTER);
            vatRate.setVerticalAlignment(Element.ALIGN_CENTER);
            vatRate.setBackgroundColor(BaseColor.DARK_GRAY);
            vatRate.setExtraParagraphSpace(5);
            table.addCell(vatRate);

            PdfPCell vatAmount = new PdfPCell(new Paragraph("Vat amount", tableHeader));
            vatAmount.setBorderColor(BaseColor.BLACK);
            vatAmount.setPaddingLeft(10);
            vatAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            vatAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            vatAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            vatAmount.setExtraParagraphSpace(5);
            table.addCell(vatAmount);

            PdfPCell grossAmount = new PdfPCell(new Paragraph("Gross amount", tableHeader));
            grossAmount.setBorderColor(BaseColor.BLACK);
            grossAmount.setPaddingLeft(10);
            grossAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            grossAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            grossAmount.setBackgroundColor(BaseColor.DARK_GRAY);
            grossAmount.setExtraParagraphSpace(5);
            table.addCell(grossAmount);

            int i = 1;
            for (Item item : invoice.getItems()) {
                PdfPCell numValue = new PdfPCell(new Paragraph(String.valueOf(i++), tableBody));
                numValue.setBorderColor(BaseColor.BLACK);
                numValue.setPaddingLeft(10);
                numValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                numValue.setVerticalAlignment(Element.ALIGN_CENTER);
                numValue.setBackgroundColor(BaseColor.WHITE);
                numValue.setExtraParagraphSpace(5);
                table.addCell(numValue);

                PdfPCell goodsValue = new PdfPCell(new Paragraph(item.getServiceDescription(), tableBody));
                goodsValue.setBorderColor(BaseColor.BLACK);
                goodsValue.setPaddingLeft(10);
                goodsValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                goodsValue.setVerticalAlignment(Element.ALIGN_CENTER);
                goodsValue.setBackgroundColor(BaseColor.WHITE);
                goodsValue.setExtraParagraphSpace(5);
                table.addCell(goodsValue);

                PdfPCell unitValue = new PdfPCell(new Paragraph(String.valueOf(item.getUnit()), tableBody));
                unitValue.setBorderColor(BaseColor.BLACK);
                unitValue.setPaddingLeft(10);
                unitValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                unitValue.setVerticalAlignment(Element.ALIGN_CENTER);
                unitValue.setBackgroundColor(BaseColor.WHITE);
                unitValue.setExtraParagraphSpace(5);
                table.addCell(unitValue);

                PdfPCell quantityValue = new PdfPCell(new Paragraph(String.valueOf(item.getQuantity()), tableBody));
                quantityValue.setBorderColor(BaseColor.BLACK);
                quantityValue.setPaddingLeft(10);
                quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityValue.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityValue.setBackgroundColor(BaseColor.WHITE);
                quantityValue.setExtraParagraphSpace(5);
                table.addCell(quantityValue);

                PdfPCell unitPriceValue = new PdfPCell(new Paragraph(String.valueOf(item.getUnitPrice() + " zł"), tableBody));
                unitPriceValue.setBorderColor(BaseColor.BLACK);
                unitPriceValue.setPaddingLeft(10);
                unitPriceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                unitPriceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                unitPriceValue.setBackgroundColor(BaseColor.WHITE);
                unitPriceValue.setExtraParagraphSpace(5);
                table.addCell(unitPriceValue);

                PdfPCell netAmountValue = new PdfPCell(new Paragraph(String.valueOf(item.getNetAmount() + " zł"), tableBody));
                netAmountValue.setBorderColor(BaseColor.BLACK);
                netAmountValue.setPaddingLeft(10);
                netAmountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                netAmountValue.setVerticalAlignment(Element.ALIGN_CENTER);
                netAmountValue.setBackgroundColor(BaseColor.WHITE);
                netAmountValue.setExtraParagraphSpace(5);
                table.addCell(netAmountValue);

                PdfPCell vatRateValue = new PdfPCell(new Paragraph(String.valueOf(item.getVatRateInPercent() + "%"), tableBody));
                vatRateValue.setBorderColor(BaseColor.BLACK);
                vatRateValue.setPaddingLeft(10);
                vatRateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                vatRateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                vatRateValue.setBackgroundColor(BaseColor.WHITE);
                vatRateValue.setExtraParagraphSpace(5);
                table.addCell(vatRateValue);

                PdfPCell vatAmountValue = new PdfPCell(new Paragraph(String.valueOf(item.getVatAmount() + " zł"), tableBody));
                vatAmount.setBorderColor(BaseColor.BLACK);
                vatAmount.setPaddingLeft(10);
                vatAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
                vatAmount.setVerticalAlignment(Element.ALIGN_CENTER);
                vatAmount.setBackgroundColor(BaseColor.WHITE);
                vatAmount.setExtraParagraphSpace(5);
                table.addCell(vatAmountValue);

                PdfPCell grossAmountValue = new PdfPCell(new Paragraph(String.valueOf(item.getGrossAmount() + " zł"), tableBody));
                grossAmountValue.setBorderColor(BaseColor.BLACK);
                grossAmountValue.setPaddingLeft(10);
                grossAmountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                grossAmountValue.setVerticalAlignment(Element.ALIGN_CENTER);
                grossAmountValue.setBackgroundColor(BaseColor.WHITE);
                grossAmountValue.setExtraParagraphSpace(5);
                table.addCell(grossAmountValue);
            }

            document.add(table);


            PdfPTable total = new PdfPTable(1);
            detailsTable.setWidthPercentage(100);
            detailsTable.setSpacingBefore(10f);
            detailsTable.setSpacingAfter(30);

            PdfPCell totalHeader = new PdfPCell(new Paragraph("TOTAL AMOUNT TO PAY", tableHeader));
            totalHeader.setBorderColor(BaseColor.BLACK);
            totalHeader.setPaddingLeft(10);
            totalHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalHeader.setVerticalAlignment(Element.ALIGN_CENTER);
            totalHeader.setBackgroundColor(BaseColor.DARK_GRAY);
            totalHeader.setExtraParagraphSpace(5);
            total.addCell(totalHeader);

            PdfPCell totalValue = new PdfPCell(new Paragraph(String.valueOf(invoice.getTotalGrossAmount() + " zł"), tableBody));
            total.addCell(totalValue);
            document.add(total);


            Paragraph annotations = new Paragraph("Annotations ", titleFont);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(30);
            Paragraph annotationsValue = new Paragraph(invoice.getAnnotations(), tableBody);
            document.add(annotations);
            document.add(annotationsValue);

            document.close();
            writer.close();
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean createExcel(List<Invoice> invoices, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);

        File file = new File(DIRECTORY);
        boolean exists = file.exists();
        if (!exists) {
            boolean dirCreated = file.mkdirs();
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "invoices" + ".xls");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet workSheet = workbook.createSheet("invoices");
            workSheet.setDefaultColumnWidth(30);

            XSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = workbook.createFont();
            font.setFontName(FONT);
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerCellStyle.setFont(font);

            XSSFRow headerRow = workSheet.createRow(0);

            XSSFCell invoiceNumber = headerRow.createCell(0);
            invoiceNumber.setCellValue("Invoice number");
            invoiceNumber.setCellStyle(headerCellStyle);

            XSSFCell invoiceDate = headerRow.createCell(1);
            invoiceDate.setCellValue("Invoice date");
            invoiceDate.setCellStyle(headerCellStyle);

            XSSFCell paymentDate = headerRow.createCell(2);
            paymentDate.setCellValue("Payment date");
            paymentDate.setCellStyle(headerCellStyle);

            XSSFCell contractor = headerRow.createCell(3);
            contractor.setCellValue("Contractor");
            contractor.setCellStyle(headerCellStyle);

            XSSFCell netAmount = headerRow.createCell(4);
            netAmount.setCellValue("Net amount");
            netAmount.setCellStyle(headerCellStyle);

            XSSFCell vatRate = headerRow.createCell(5);
            vatRate.setCellValue("Vat rate %");
            vatRate.setCellStyle(headerCellStyle);

            XSSFCell vatAmount = headerRow.createCell(6);
            vatAmount.setCellValue("Vat amount");
            vatAmount.setCellStyle(headerCellStyle);

            XSSFCell grossAmount = headerRow.createCell(7);
            grossAmount.setCellValue("Gross amount");
            grossAmount.setCellStyle(headerCellStyle);

            XSSFCell status = headerRow.createCell(8);
            status.setCellValue("Status");
            status.setCellStyle(headerCellStyle);

            int i = 1;
            for (Invoice invoice : invoices) {
                XSSFRow bodyRow = workSheet.createRow(i);
                XSSFCellStyle bodyCellStyle = workbook.createCellStyle();
                bodyCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

                XSSFCell invoiceNumberValue = bodyRow.createCell(0);
                invoiceNumberValue.setCellValue(invoice.getInvoiceNumber());
                invoiceNumberValue.setCellStyle(bodyCellStyle);

                XSSFCell invoiceDateValue = bodyRow.createCell(1);
                invoiceDateValue.setCellValue(invoice.getPaymentDate());
                invoiceDateValue.setCellStyle(bodyCellStyle);

                XSSFCell paymentDateValue = bodyRow.createCell(2);
                paymentDateValue.setCellValue(invoice.getPaymentDate());
                paymentDateValue.setCellStyle(bodyCellStyle);

                XSSFCell contractorValue = bodyRow.createCell(3);
                contractorValue.setCellValue((RichTextString) invoice.getContractor());
                contractorValue.setCellStyle(bodyCellStyle);

                XSSFCell netAmountValue = bodyRow.createCell(4);
                netAmountValue.setCellValue(invoice.getTotalNetAmount());
                netAmountValue.setCellStyle(bodyCellStyle);

                XSSFCell vatRateValue = bodyRow.createCell(5);
                vatRateValue.setCellValue(invoice.getVatRateInPercent());
                vatRateValue.setCellStyle(bodyCellStyle);

                XSSFCell vatAmountValue = bodyRow.createCell(6);
                vatAmountValue.setCellValue(invoice.getTotalVatAmount());
                vatAmountValue.setCellStyle(bodyCellStyle);

                XSSFCell grossAmountValue = bodyRow.createCell(7);
                grossAmountValue.setCellValue(invoice.getTotalGrossAmount());
                grossAmountValue.setCellStyle(bodyCellStyle);

                XSSFCell statusValue = bodyRow.createCell(8);
                statusValue.setCellValue(invoice.getIsPaid());
                statusValue.setCellStyle(bodyCellStyle);

                i++;
            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
            document.close();
            return true;

        } catch (
                Exception e) {
            return false;
        }
    }

}





