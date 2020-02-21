package pl.coderslab.trucktrans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.trucktrans.invoice.InvoiceService;
import pl.coderslab.trucktrans.model.Invoice;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class PdfExcelController {

    private final InvoiceService invoiceService;
    private final ServletContext servletContext;


    @GetMapping("/createPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createPdf(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "invoices" + ".pdf");
            filedownload(fullPath, response, "invoices.pdf");
        }
    }

    @GetMapping("/createExcel")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        boolean isFlag = invoiceService.createExcel(invoices, servletContext, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "invoices" + ".xls");
            filedownload(fullPath, response, "invoices.xls");
        }
    }

    //download logic
    private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = servletContext.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment;filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
