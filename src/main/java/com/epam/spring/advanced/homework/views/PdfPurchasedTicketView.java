package com.epam.spring.advanced.homework.views;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author Denes_Toth
 */
public class PdfPurchasedTicketView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        List<TicketView> tickets = (List<TicketView>) map.get("tickets");

        Table table = new Table(3);
        table.addCell("Event");
        table.addCell("Date");
        table.addCell("Seat");

        for (TicketView ticket : tickets) {
            table.addCell(ticket.getEvent());
            table.addCell(ticket.getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            table.addCell(Float.toString(ticket.getSeat()));
        }

        document.add(table);
    }
}
