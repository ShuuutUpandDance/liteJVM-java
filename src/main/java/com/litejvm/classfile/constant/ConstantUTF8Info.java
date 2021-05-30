package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.ClassReader;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ConstantUTF8Info extends AbstractConstantInfo{
    private String content;

    @Override
    public void readInfo(ClassReader reader) {
        ClassFile.Hex16 hex16 = reader.readHex16();
        int byteLength = Integer.parseInt(new String(hex16.getContent()), 16);

        byte[] combined;
        try {
            byte[] contentInByte = reader.readBytes(byteLength);
            byte[] lengthInByte = Hex.decodeHex(hex16.getContent());
            combined = new byte[contentInByte.length + lengthInByte.length];

            System.arraycopy(lengthInByte, 0, combined, 0, lengthInByte.length);
            System.arraycopy(contentInByte, 0, combined, lengthInByte.length, contentInByte.length);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        // combined = length + content
        try(DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(combined))) {
            this.content = dataInputStream.readUTF();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getContent() {
        return content;
    }
}
