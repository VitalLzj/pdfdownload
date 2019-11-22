package cn.shuto.download;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.io.OutputStream;

class PdfChanger {

	static void change(OutputStream bos, String input)
			throws DocumentException, IOException {
		PdfReader reader = new PdfReader(input);
		PdfStamper stamper = new PdfStamper(reader, bos);
		int total = reader.getNumberOfPages() + 1;
		PdfContentByte content;
		BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		PdfGState gs = new PdfGState();
		for (int i = 1; i < total; i++) {
			content = stamper.getOverContent(i);// 在内容上方加水印
			gs.setFillOpacity(0.2f);
			content.beginText();
			content.setColorFill(BaseColor.LIGHT_GRAY);
			content.setFontAndSize(base, 50);
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！", 300, 350, 55);
			content.setColorFill(BaseColor.BLACK);
			content.setFontAndSize(base, 8);
			content.showTextAligned(Element.ALIGN_CENTER, "下载时间：" + "aa" + "", 300, 10, 0);
			content.endText();
		}
		stamper.close();
	}
}