package pl.coderslab.trucktrans.download;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.trucktrans.contractor.ContractorService;
import pl.coderslab.trucktrans.invoice.InvoiceService;
import pl.coderslab.trucktrans.model.Contractor;
import pl.coderslab.trucktrans.model.Invoice;
import pl.coderslab.trucktrans.model.Item;
import pl.coderslab.trucktrans.model.Order;
import pl.coderslab.trucktrans.order.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class PdfExcelController {

    private static final String DIRECTORY = "/Users/monikamisiewicz/Desktop/reports/";

    private final ServletContext servletContext;
    private final InvoiceService invoiceService;
    private final ContractorService contractorService;
    private final OrderService orderService;

    @GetMapping("/invoicesPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createPdf(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "invoices.pdf";
            filedownload(fullPath, response, "invoices.pdf" );
        }
    }

    @GetMapping("/invoicePdf/{id}")
    public void createPdfInvoiceDetails(@PathVariable(name = "id") long invoiceId, HttpServletRequest request, HttpServletResponse response) {
       Invoice invoice = invoiceService.getById(invoiceId);
       List<Item> items = invoiceService.getAllItems();
        boolean isFlag = invoiceService.createPdfInvoiceDetails(invoice, items, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "invoice.pdf";
            filedownload(fullPath, response, "invoice.pdf" );
        }
    }

    @GetMapping("/invoicesExcel")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createExcel(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "invoices.xlsx";
            filedownload(fullPath, response, "invoices.xlsx");
        }
    }

    @GetMapping("/contractorsPdf")
    public void createPdfContractors(HttpServletRequest request, HttpServletResponse response) {
        List<Contractor> contractors = contractorService.getAllContractors();
        boolean isFlag = contractorService.createPdf(contractors, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "contractors.pdf";
            filedownload(fullPath, response, "contractors.pdf" );
        }
    }

    @GetMapping("/ordersPdf")
    public void createPdfOrders(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = orderService.getAllOrders();
        boolean isFlag = orderService.createPdf(orders, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "orders.pdf";
            filedownload(fullPath, response, "orders.pdf" );
        }
    }



    //download logic
    private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {

                MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

                // Content-Type
                // application/pdf
                response.setContentType(mediaType.getType());

                // Content-Disposition
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

                // Content-Length
                response.setContentLength((int) file.length());

                BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = 0;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                outStream.flush();
                inStream.close();
                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

