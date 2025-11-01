package com.github.itdachen.framework.boot.oss.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 获取文件头
 * Created by 王大宸 on 2023/02/17 9:09
 * Created with IntelliJ IDEA.
 */
public class FileHeaderUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileHeaderUtils.class);
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    static {
        FILE_TYPE_MAP.put("null", null); // null
        FILE_TYPE_MAP.put("424D", "bmp");
        FILE_TYPE_MAP.put("1F8B08", "gz");
        FILE_TYPE_MAP.put("FFD8FF", "jpg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("52494646", "jpg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("89504E47", "png"); // PNG (png)
        FILE_TYPE_MAP.put("47494638", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("2E6C6179", "css"); // css
        FILE_TYPE_MAP.put("75736167", "txt");
        FILE_TYPE_MAP.put("68747470", "txt");
        FILE_TYPE_MAP.put("000001B", "mpg"); //MPEG (mpg)，文件头：000001BA MPEG (mpg)，文件头：000001B3
        FILE_TYPE_MAP.put("57415645", "wav"); // Wave (wav)
        FILE_TYPE_MAP.put("41564920", "avi");
        FILE_TYPE_MAP.put("4D546864", "mid"); // MIDI (mid)
        FILE_TYPE_MAP.put("504B0304", "zip");
        FILE_TYPE_MAP.put("52617221", "rar");
        FILE_TYPE_MAP.put("7061636B", "java");// java文件
        FILE_TYPE_MAP.put("6D6F6F76", "mov"); // Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); // WordPerfect (wpd)
        FILE_TYPE_MAP.put("2142444E", "pst"); // Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); // Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); // Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); // Real Audio (ram)
        FILE_TYPE_MAP.put("2E524D46", "rm");
        FILE_TYPE_MAP.put("49492A00", "tif");
        FILE_TYPE_MAP.put("41433130", "dwg"); // CAD
        FILE_TYPE_MAP.put("38425053", "psd");
        FILE_TYPE_MAP.put("000001BA", "mpg");
        FILE_TYPE_MAP.put("000001B3", "mpg");
        FILE_TYPE_MAP.put("D0CF11E0", "doc");
        FILE_TYPE_MAP.put("3C3F786D6C", "xml");// xml文件
        FILE_TYPE_MAP.put("68746D6C3E", "html"); // HTML (html)
        FILE_TYPE_MAP.put("7B5C727466", "rtf"); // 日记本
        FILE_TYPE_MAP.put("255044462D312E", "pdf");
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
        FILE_TYPE_MAP.put("3026B2758E66CF11", "asf");

        FILE_TYPE_MAP.put("20202020202020202020", "LICENSE");
        FILE_TYPE_MAP.put("4E61636F730A436F7079", "NOTICE");
        FILE_TYPE_MAP.put("3132372E302E302E3120", "log");
        FILE_TYPE_MAP.put("68747470733A2F2F646F", "txt");
        FILE_TYPE_MAP.put("2031E38081E69FA5E79C", "txt");
        FILE_TYPE_MAP.put("6964656120E8B4A6E58F", "txt");
        FILE_TYPE_MAP.put("0D0AE8B685E7BAA7E8B4", "txt");
        FILE_TYPE_MAP.put("77696E646F777320E7B3", "txt");
        FILE_TYPE_MAP.put("E4B8AAE4BABA3A203139", "txt");
        FILE_TYPE_MAP.put("E69292E58f91E8BEBEE6", "css");
        FILE_TYPE_MAP.put("48544D4C207B0D0A0942", "css");
        FILE_TYPE_MAP.put("49492A00227105008037", "tif、tiff"); // TIFF (tif)
        FILE_TYPE_MAP.put("424D228C010000000000", "bmp(16色位图)"); // 16色位图(bmp)
        FILE_TYPE_MAP.put("424D8240090000000000", "bmp(24位位图)"); // 24位位图(bmp)
        FILE_TYPE_MAP.put("424D8E1B030000000000", "bmp(256色位图)"); // 256色位图(bmp)
        FILE_TYPE_MAP.put("424d8E1BE30000000000", "bmp(256色位图)"); // 256色位图(bmp)
        FILE_TYPE_MAP.put("41433130313500000000", "dwg(CAD)"); // CAD (dwg)
        FILE_TYPE_MAP.put("696B2E71623D696B2E71", "js"); // js
        FILE_TYPE_MAP.put("7B5C727466315C616E73", "rtf"); // Rich Text Format
        FILE_TYPE_MAP.put("2E524D46000000120001", "rmvb、rm"); // rmvb/rm相同
        FILE_TYPE_MAP.put("464C5601050000000900", "flv、f4v"); // flv与f4v相同
        FILE_TYPE_MAP.put("00000020667479706D70", "mp4");
        FILE_TYPE_MAP.put("00000020667479706973", "mp4");
        FILE_TYPE_MAP.put("49443303000000002176", "mp3");
        FILE_TYPE_MAP.put("49443303000000034839", "mp3");
        FILE_TYPE_MAP.put("4944330300000000025A", "mp3");
        FILE_TYPE_MAP.put("3026B2758E66CF11A6D9", "wmv、asf"); // wmv与asf相同
        FILE_TYPE_MAP.put("38425053000100000000", "psd"); // Photoshop (psd)
        FILE_TYPE_MAP.put("D0CF11E0A1B11AE10000", "doc、ppt、xls、wps、vsd(Visio)");// WPS文字wps、表格et、演示dps都是一样的， word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); // MS Access (mdb)
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("1F8B0800000000000000", "gz");
        FILE_TYPE_MAP.put("6C6F67346A2E726F6F74", "properties");
        FILE_TYPE_MAP.put("230A2320436F70797269", "properties");
        FILE_TYPE_MAP.put("CAFEBABE0000002E0041", "class");
        FILE_TYPE_MAP.put("49545346030000006000", "chm");
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");
        FILE_TYPE_MAP.put("504B0304140006000800", "docx、xlsx");
        FILE_TYPE_MAP.put("6431303A637265617465", "torrent");
        FILE_TYPE_MAP.put("504B03040A0000000000", "pptx、jar");
        FILE_TYPE_MAP.put("504B0304140008080800", "jar");
        FILE_TYPE_MAP.put("494E5345525420494E54", "sql");
        FILE_TYPE_MAP.put("2F2A0D0A204E61766963", "sql");
        FILE_TYPE_MAP.put("2F2A0A202A20436F7079", "sql");
        FILE_TYPE_MAP.put("7061636B616765207765", "java");// java文件
        FILE_TYPE_MAP.put("7061636B61676520636E", "java");// java文件
        FILE_TYPE_MAP.put("3C25402070616765206C", "jsp");// jsp文件
        FILE_TYPE_MAP.put("4D616E69666573742D56", "mf");// MF文件
        FILE_TYPE_MAP.put("FFD8FFE000104A464946", "jpg、jpeg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("52494646DCC101005745", "jpg"); // jpg
        FILE_TYPE_MAP.put("89504E470D0A1A0A0000", "png"); // PNG (png)
        FILE_TYPE_MAP.put("47494638396126026F01", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("3C21444F435459504520", "html"); // HTML (html)
        FILE_TYPE_MAP.put("3C21646F637479706520", "htm"); //HTM (htm)
        FILE_TYPE_MAP.put("46726F6D3A203D3F6762", "eml(Email)"); //Email [Outlook Express 6] (eml)
        FILE_TYPE_MAP.put("255044462D312E350D0A", "pdf");
        FILE_TYPE_MAP.put("255044462D312E330A25", "pdf");
        FILE_TYPE_MAP.put("255044462D312E340A25", "pdf");
        FILE_TYPE_MAP.put("255044462D312E370A25", "pdf");
        FILE_TYPE_MAP.put("255044462D312E370D25", "pdf");
        FILE_TYPE_MAP.put("000001BA210001000180", "mpg");
        FILE_TYPE_MAP.put("52494646E27807005741", "wav"); //Wave (wav)
        FILE_TYPE_MAP.put("52494646D07D60074156", "avi");
        FILE_TYPE_MAP.put("4D546864000000060001", "mid"); //MIDI (mid)
        FILE_TYPE_MAP.put("504B0304140000000800", "zip");
        FILE_TYPE_MAP.put("504B03040A0000080000", "zip");
        FILE_TYPE_MAP.put("504B0304140000000000", "zip");
        FILE_TYPE_MAP.put("526172211A0700CF9073", "rar");
        FILE_TYPE_MAP.put("504B03040A00000000008", "docx");
        FILE_TYPE_MAP.put("0A0A776F726B65725F70", "conf");
        FILE_TYPE_MAP.put("736572766572207B0D0A", "conf");
        FILE_TYPE_MAP.put("2D2D2D2D2D424547494E", "key、pem");
        FILE_TYPE_MAP.put("44656C69766572792D646174653A", "eml(Email)");

    }


    public static void main(String[] args) throws Exception {
        // System.err.println("ffd8ffe000104a464946".toUpperCase());
        // String type = getFileType("C:/test/eee.WMV");
        // System.out.println("eee.WMV : "+type);
        // System.out.println();

        String fileHeader = getFileHeader(new FileInputStream("D:/images/2013年教师讨论形成办园理念、办园追求的问卷.pptx"));
        String fileType = getFileType("D:/images/WeChat_20211221153140.mp4");

        System.out.println("fileHeader : " + fileHeader);
        System.out.println("fileType : " + fileType);
        System.out.println();

    }


    /***
     * 获取文件头
     *
     * @author 王大宸
     * @date 2023/1/4 10:04
     * @param file file
     * @return java.lang.String
     */
    public static String getFileHeader(MultipartFile file) {
        String value = null;
        try {
            value = getFileHeader(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getFileHeader(InputStream is) {
        String value = null;
        try {
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    private static String bytesToHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length == 0) {
            return null;
        }
        int v;
        String hv;
        for (byte value : b) {
            v = value & 0xFF;
            hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /***
     * 根据制定文件的文件头判断其文件类型
     *
     * @author 王大宸
     * @date 2023/1/4 14:15
     * @param filePath filePath
     * @return java.lang.String
     */
    public static String getFileType(String filePath) {
        String res = null;
        try {
            FileInputStream is = new FileInputStream(filePath);
            //  具体需要多大的数组要根据具体文件来判断，这里先定义大小为10，大部分文件定义3够用
            byte[] b = new byte[10];
            is.read(b, 0, b.length);
            String fileCode = Objects.requireNonNull(bytesToHexString(b)).toUpperCase();
            System.err.println("fileCode: " + fileCode);

            //  res = FILE_TYPE_MAP.get(fileCode);

            // 这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            String key;
            while (keyIter.hasNext()) {
                key = keyIter.next();
                if (key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())) {
                    res = FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getFileTypeByFileCode(String fileCode) {
        String res = null;
        try {
            // 这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            String key;
            while (keyIter.hasNext()) {
                key = keyIter.next();
                if (key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())) {
                    res = FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}
