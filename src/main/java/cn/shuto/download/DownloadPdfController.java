package cn.shuto.download;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lizj
 * DATE 2019/11/15 15:30
 * TODO
 */
@Controller
public class DownloadPdfController {

	@GetMapping("/download")
	public void downLoad(HttpServletResponse response) {
		download(response);
	}

	@GetMapping("/preview")
	public void preview(HttpServletResponse response) {
		String filePath = "G:\\深入理解ES6.pdf";
		try {
			OutputStream out = response.getOutputStream();
			try {
				PdfChanger.change(out, filePath);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void download(HttpServletResponse response) {
		String filePath = "G:\\深入理解ES6.pdf";
		try {
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);//得到文件名
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
			response.setContentType("application/octet-stream");//告诉浏览器输出内容为流
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);//Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
			OutputStream out = response.getOutputStream();
			try {
				PdfChanger.change(out, filePath);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
