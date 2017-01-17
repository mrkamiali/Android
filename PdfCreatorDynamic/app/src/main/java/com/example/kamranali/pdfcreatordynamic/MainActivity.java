package com.example.kamranali.pdfcreatordynamic;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static String FILE = Environment.getExternalStorageDirectory()
            + "/HelloWorld.pdf";
    private Button pdfCreateButton;
    private TextView textViewMain;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfCreateButton = (Button) findViewById(R.id.button1);
        textViewMain = (TextView) findViewById(R.id.textViewMain);
        content = (EditText) findViewById(R.id.pdf_content);
        pdfCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.setVisibility(View.GONE);
                createPdf();
                pdfCreateButton.setVisibility(View.GONE);
                textViewMain.setText("Please Restart your APPLICATION !");

            }
        });

    }

    private void createPdf() {
        // create a new document
        PdfDocument document = new PdfDocument();

        // draw something on the page
        View content = findViewById(R.id.pdf_content);
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(), content.getHeight() - 20, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

//        View content = getContentView();
        content.draw(page.getCanvas());

        // finish the page
        document.finishPage(page);

        // saving pdf document to sdcard
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String pdfName = "pdfdemo"
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

        File outputFile = new File("/sdcard/", pdfName);

        // write the document content
        try {
            outputFile.createNewFile();
            OutputStream out = new FileOutputStream(outputFile);
            document.writeTo(out);
            // close the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private OutputStream getOutputStream() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(FILE);
            Toast.makeText(this, "SuccessFullyCreated", Toast.LENGTH_LONG).show();
            textViewMain.setText("PDF Created at " + FILE);

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "ErrorWhileCreatingFile", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return fileOutputStream;
    }

    private View getContentView() {
        View view = getLayoutInflater().inflate(R.layout.pdf, null);
        TextView txtView = (TextView) view.findViewById(R.id.textVew);
        txtView.setText("MyPDF sdfs fsd f sd sdf sdf sd fsd fsdf s f\n asdasd asd asd asd asd as gsd g df sfsd f \n" +
                "asd asdas d asd as das d asd as d asd asdas d d \n" +
                " asd as d asd as d as g d as d sa d sad  sad\n" +
                "sadasds sad as  d fas d as ds ad as d asd  wiqb n v \n" +
                "ENDED");
        return view;
    }
}
