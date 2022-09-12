import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;

public class Demo {

    public static final String DEST = "/home/hoang/Downloads/SodaPDF.pdf";

    public static final String SRC = "/home/hoang/Downloads/SodaPDF-converted-new 2-Copy-1.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new Demo().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        PdfDictionary page = pdfDoc.getFirstPage().getPdfObject();

        PdfArray annots = page.getAsArray(PdfName.Annots);

        PdfDictionary sticky = annots.getAsDictionary(0);
        //hoi ngu nhung la cach cuoi cung
        Rectangle asRectangle = sticky.getAsRectangle(PdfName.Rect);
        float x = asRectangle.getX();
        float y = asRectangle.getY();
        sticky.clear();


        // Creating an ImageData object
        String imFile = "/home/hoang/Pictures/seal2.png";
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object
        Image image = new Image(data);
        image.setWidth(50);
        image.setHeight(50);

        image.setFixedPosition(x, y);
        doc.add(image);

        doc.close();
    }
}
