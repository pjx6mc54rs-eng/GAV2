package util;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Etudiant;
import java.io.FileOutputStream;
import java.util.List;

public class ExportPDF {
    public static void generer(List<Etudiant> etudiants, String chemin) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(chemin));
            document.open();
            document.add(new Paragraph("Liste des Étudiants et Absences"));
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(3);
            table.addCell("Nom");
            table.addCell("Prénom");
            table.addCell("CNE");
            
            for (Etudiant e : etudiants) {
                table.addCell(e.getNom());
                table.addCell(e.getPrenom());
                table.addCell(e.getCne());
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
