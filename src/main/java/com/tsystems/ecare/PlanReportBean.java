package com.tsystems.ecare;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tsystems.ecare.dto.Feature;
import com.tsystems.ecare.dto.PlanReport;
import com.tsystems.ecare.dto.Plans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ManagedBean
@SessionScoped
public class PlanReportBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long plan;

    private static Map<String,Object> plans;
    static{
        plans = new LinkedHashMap<String, Object>();
        Plans pls = WebServiceUtils.getPlans();
        pls.getPlans().forEach(p -> plans.put(p.getTitle(), p.getId()));
    }

    public Map<String,Object> getPlans() {
        return plans;
    }

    public long getPlan() {
        return plan;
    }

    public void setPlan(long plan) {
        this.plan = plan;
    }

    /**
     * Requests for chosen plan report data and generates PDF report.
     *
     * @throws Exception if something has gone wrong
     */
    public void generateReport() throws Exception {

        PlanReport report = WebServiceUtils.getReport(plan);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Date now = new Date();
        SimpleDateFormat ftshort = new SimpleDateFormat("yyyyMMdd_HHmmss");
        response.setHeader("Content-Disposition", "attachment;filename=" + report.getTitle() + "_" + ftshort.format(now) + ".pdf");

        OutputStream file = response.getOutputStream();

        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 25);
        Font fontText = new Font(Font.FontFamily.HELVETICA, 18);
        Font fontTextBold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Document document = new Document();
        PdfWriter.getInstance(document, file);

        Image image = Image.getInstance(new File(getClass().getClassLoader().getResource("T_Mobile.png").getFile()).getPath());
        document.open();
        float width = image.getPlainWidth();
        float height = image.getPlainHeight();
        float dWidth = document.getPageSize().getWidth() * 0.9f;
        float dHeight = dWidth / width * height;
        image.scaleAbsolute(dWidth, dHeight);
        document.add(image);

        Paragraph paragraph;
        Phrase phrase;

        paragraph = new Paragraph("Plan statistics", fontHeader);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(new Phrase("Title: ", fontTextBold));
        document.add(new Phrase(report.getTitle(), fontText));
        paragraph = new Paragraph("");
        paragraph.setSpacingAfter(5f);
        document.add(paragraph);
        document.add(new Phrase("Cost per month: ", fontTextBold));
        document.add(new Phrase("€" + report.getMonthlyFee(), fontText));
        paragraph = new Paragraph("");
        paragraph.setSpacingAfter(5f);
        document.add(paragraph);
        document.add(new Phrase("Count of contracts using plan: ", fontTextBold));
        document.add(new Phrase(String.valueOf(report.getTotalContracts()), fontText));
        paragraph = new Paragraph("");
        paragraph.setSpacingAfter(20f);
        document.add(paragraph);

        paragraph = new Paragraph("Avalilable options usage", fontHeader);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        float[] columnWidths = {3f, 1f, 1f, 2f, 2f};
        table.setWidths(columnWidths);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.addCell("Title");
        table.addCell("Add, €");
        table.addCell("Use, €");
        table.addCell("Usage");
        table.addCell("Usage, %");

        for (Feature feature : report.getAllowedFeatures()) {
            table.addCell(feature.getTitle());
            table.addCell(String.valueOf(feature.getAdditionFee()));
            table.addCell(String.valueOf(feature.getMonthlyFee()));
            long contracts = report.getFeaturesUsers().get(feature.getId());
            table.addCell(String.valueOf(contracts));
            table.addCell(String.format("%.2f%n", (float) contracts / (float) report.getTotalContracts() * 100));
        }

        document.add(table);
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));

        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");

        document.add(new Paragraph("Add - addition cost"));
        document.add(new Paragraph("Use - cost per month"));
        document.add(new Paragraph("Usage - count of contracts using this option"));
        document.add(new Paragraph("Usage, % - usage as % of total contracts using this plan count"));
        paragraph = new Paragraph("");
        paragraph.setSpacingAfter(5f);
        document.add(paragraph);
        document.add(new Paragraph("This report is generated " + ft.format(now)));

        document.close();
        file.close();
        FacesContext.getCurrentInstance().getResponseComplete();
    }
}
