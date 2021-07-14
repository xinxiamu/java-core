package com.ymu.study.office.word2html;

import cn.hutool.core.img.ImgUtil;
import fr.opensagres.poi.xwpf.converter.core.BasicURIResolver;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.xhtml.Base64EmbedImgManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * poi转。支持不是很好，样式会乱。不建议使用
 *
 */
public class OfficeConvertUtil {

    /**
     * 将word2003转换为html文件 2017-2-27
     *
     * @param wordPath word文件路径
     * @param wordName word文件名称无后缀
     * @param suffix   word文件后缀
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static String Word2003ToHtml(String wordPath, String wordName,
                                        String suffix) throws IOException, TransformerException,
            ParserConfigurationException {
        String htmlPath = wordPath + File.separator + "html"
                + File.separator;
        String htmlName = wordName + ".html";
        final String imagePath = htmlPath + "image" + File.separator;

        // 判断html文件是否存在，每次重新生成
        File htmlFile = new File(htmlPath + htmlName);
//      if (htmlFile.exists()) {
//          return htmlFile.getAbsolutePath();
//      }

        // 原word文档
        final String file = wordPath + File.separator + wordName + suffix;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());

        wordToHtmlConverter.setPicturesManager((content, pictureType, suggestedName, widthInches, heightInches) -> {
            BufferedImage bufferedImage = ImgUtil.toImage(content);
            String base64Img = ImgUtil.toBase64(bufferedImage, pictureType.getExtension());
            //  带图片的word，则将图片转为base64编码，保存在一个页面中
            StringBuilder sb = (new StringBuilder(base64Img.length() + "data:;base64,".length()).append("data:;base64,").append(base64Img));
            return sb.toString();
        });

        // 解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        // 生成html文件上级文件夹
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 生成html文件地址
        OutputStream outStream = new FileOutputStream(htmlFile);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);

        outStream.close();

        return htmlFile.getAbsolutePath();
    }

    /**
     * 2007版本word转换成html 2017-2-27
     *
     * @param wordPath word文件路径
     * @param wordName word文件名称无后缀
     * @param suffix   word文件后缀
     * @return
     * @throws IOException
     */
    public static String Word2007ToHtml(String wordPath, String wordName, String suffix)
            throws IOException {
        ZipSecureFile.setMinInflateRatio(-1.0d);
        String htmlPath = wordPath + File.separator + "html"
                + File.separator;
        String htmlName = wordName + ".html";
        String imagePath = htmlPath + "image" + File.separator;

        // 判断html文件是否存在
        File htmlFile = new File(htmlPath + htmlName);
//      if (htmlFile.exists()) {
//          return htmlFile.getAbsolutePath();
//      }

        // word文件
        File wordFile = new File(wordPath + File.separator + wordName + suffix);

        // 1) 加载word文档生成 XWPFDocument对象
        InputStream in = new FileInputStream(wordFile);
        XWPFDocument document = new XWPFDocument(in);

        // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imgFolder = new File(imagePath);
        //  带图片的word，则将图片转为base64编码，保存在一个页面中
        XHTMLOptions options = XHTMLOptions.create().indent(4).setImageManager(new Base64EmbedImgManager());
        // 3) 将 XWPFDocument转换成XHTML
        // 生成html文件上级文件夹
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        OutputStream out = new FileOutputStream(htmlFile);
        XHTMLConverter.getInstance().convert(document, out, options);

        return htmlFile.getAbsolutePath();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(Word2003ToHtml("D:\\temp\\word", "21", ".doc"));
//        System.out.println(Word2007ToHtml("F:\\", "contract", ".docx"));

        try {
            Word2007ToHtml("F:\\", "contract", ".docx", "F:\\");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * 2007版本word转换成html
     *
     * @param wordPath  word文件路径
     * @param wordName word文件名称无后缀
     * @param suffix   word文件后缀
     * @param htmlPath html存储地址
     * @return
     * @throws IOException
     */
    public static String Word2007ToHtml(String wordPath, String wordName, String suffix, String htmlPath)
            throws IOException {
        String htmlName = wordName + ".html";
        String imagePath = htmlPath + "image" + File.separator;
        // 判断html文件是否存在
        File htmlFile = new File(htmlPath + htmlName);
        if (htmlFile.exists()) {
            return htmlFile.getAbsolutePath();
        }
        // word文件
        File wordFile = new File(wordPath + File.separator + wordName + suffix);
        // 1) 加载word文档生成 XWPFDocument对象
        InputStream in = new FileInputStream(wordFile);
        XWPFDocument document = new XWPFDocument(in);
        // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imgFolder = new File(imagePath);
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(imgFolder));
        // html中图片的路径 相对路径
        options.URIResolver(new BasicURIResolver("image"));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        // 3) 将 XWPFDocument转换成XHTML
        // 生成html文件上级文件夹
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        OutputStream out = new FileOutputStream(htmlFile);
        XHTMLConverter.getInstance().convert(document, out, options);
        return htmlFile.getAbsolutePath();
    }

}
