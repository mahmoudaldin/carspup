package com.netty.receiver.handler;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.BitSet;

public class DataTypeUtil {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    
    private DataTypeUtil() {}

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static String hexToStringUtf8(String hex) {
    	
    	ByteBuffer buff = ByteBuffer.allocate(hex.length()/2);
    	for (int i = 0; i < hex.length(); i+=2) {
    	    buff.put((byte)Integer.parseInt(hex.substring(i, i+2), 16));
    	}
    	buff.rewind();
    	Charset cs = Charset.forName("UTF-8");                              // BBB
    	CharBuffer cb = cs.decode(buff);                                    // BBB
    //	System.out.println(cb.toString());                                  // CCC
    	
    	return cb.toString();
    }

    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        
        return output.toString();
    }
    
    static String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        //String bin = Integer.toBinaryString(i);
        String bin = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        return bin;
    }
    
    public static void main(String[] args) {
    	byte[] bytes = {(byte) 0x33};
//    	
//    	int x = 0x00 + 0x04;
//    	System.out.println(new String(bytes));
    	System.out.println(Integer.parseInt(bytesToHex(bytes),16));

//		System.out.println(hexToStringUtf8(bytesToHex(bytes)));
//	
//		  String asciiString = "www.baeldung.com";
//		    String hexEquivalent = 
//		      "7777772e6261656c64756e672e636f6d";
//		    
//		    System.out.println(hexToAscii(hexEquivalent).equals(asciiString));
//		    System.out.println(hexToAscii(bytesToHex(bytes)));
//		    
			
//			System.out.println(hexToBinary(bytesToHex(bytes)));
//			System.out.println(hexToBinary(bytesToHex(bytes)).toCharArray()[0]);
			System.out.println(hexToBinary(bytesToHex(bytes)).toCharArray());
			
		    
    }
    
    
}
