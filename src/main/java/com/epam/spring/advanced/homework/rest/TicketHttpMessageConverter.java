package com.epam.spring.advanced.homework.rest;

import com.epam.spring.advanced.homework.domain.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Denes Toth
 */
public class TicketHttpMessageConverter implements HttpMessageConverter<Set<Ticket>> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return MediaType.APPLICATION_PDF.equals(mediaType) && aClass.isInstance(new HashSet<Ticket>());
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_PDF);
    }

    @Override
    public Set<Ticket> read(Class<? extends Set<Ticket>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Set<Ticket> tickets, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_PDF);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Table table = new Table(4);
            table.addCell("Ticket ID");
            table.addCell("Guest");
            table.addCell("Event");
            table.addCell("Seat No.");
            for (Ticket ticket : tickets) {
                table.addCell(ticket.getId().toString());
                table.addCell(ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName());
                table.addCell(ticket.getEvent().getName());
                table.addCell(Float.toString(ticket.getSeat()));
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        httpOutputMessage.getHeaders().setContentLength(byteArrayOutputStream.size());
        byteArrayOutputStream.writeTo(httpOutputMessage.getBody());
        httpOutputMessage.getBody().flush();
    }
}
