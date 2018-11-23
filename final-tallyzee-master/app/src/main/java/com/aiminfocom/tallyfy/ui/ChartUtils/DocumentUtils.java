package com.aiminfocom.tallyfy.ui.ChartUtils;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GulshanPC on 25/07/2018.
 */

public class DocumentUtils {
//    private void createPdf() throws FileNotFoundException, DocumentException {
//
//        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
//        if (!pdfFolder.exists()) {
//            pdfFolder.mkdir();
//            Log.i(LOG_TAG, "Pdf Directory created");
//        }
//
//        //Create time stamp
//        Date date = new Date() ;
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
//
//        File myFile = new File(pdfFolder + timeStamp + ".pdf");
//
//        OutputStream output = new FileOutputStream(myFile);
//
//        //Step 1
//        Document document = new Document();
//
//        //Step 2
//        PdfWriter.getInstance(document, output);
//
//        //Step 3
//        document.open();
//
//        //Step 4 Add content
//        document.add(new Paragraph(mSubjectEditText.getText().toString()));
//        document.add(new Paragraph(mBodyEditText.getText().toString()));
//
//        //Step 5: Close the document
//        document.close();
//
//    }
//    Document getDocumentAsPDf(Context context)
//    {
//        // open a new document
//        PrintedPdfDocument document = new PrintedPdfDocument(context,
//                printAttributes);
//
//// start a page
//        PdfDocument.Page page = document.startPage(0);
//
//// draw something on the page
//        View content = context.getContentView();
//        content.draw(page.getCanvas());
//
//// finish the page
//        document.finishPage(page);
//. . .
//// add more pages
//. . .
//// write the document content
//        document.writeTo(getOutputStream());
//
////close the document
//        document.close();
//    }
}
