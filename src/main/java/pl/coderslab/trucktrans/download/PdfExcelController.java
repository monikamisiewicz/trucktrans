package pl.coderslab.trucktrans.download;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.trucktrans.invoice.InvoiceService;
import pl.coderslab.trucktrans.model.Invoice;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class PdfExcelController {

    private static final String DIRECTORY = "/Users/monikamisiewicz/Desktop/reports/";

    private final ServletContext servletContext;
    private final InvoiceService invoiceService;

    @GetMapping("/invoicePdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createPdf(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "invoice.pdf";
            filedownload(fullPath, response, "invoice.pdf" );
        }
    }

    @GetMapping("/invoiceExcel")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createExcel(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = DIRECTORY + "invoice.xls";
            filedownload(fullPath, response, "invoice.xls");
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

                byte[] buffer = new byte[4096];
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

