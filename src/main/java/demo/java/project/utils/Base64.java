package demo.java.project.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {

    final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    //编码
    public String deCode(String str) throws UnsupportedEncodingException {
        final byte[] textByte = str.getBytes("UTF-8");
        final String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }
    //解码
    public  String enCode(String str) throws UnsupportedEncodingException {
        return new String(decoder.decode(str), "UTF-8");
    }
}
