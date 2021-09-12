package com.litejvm.rtdata.heap;

public class MethodDescriptorParser {
    String raw;
    int offset = 0;
    MethodDescriptor parsed;

    public MethodDescriptor parse(String descriptor) {
        raw = descriptor;
        parsed = new MethodDescriptor();
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
        return parsed;
    }

    private void finish() {
        if (offset != raw.length()) {
            throwException();
        }
    }

    private void endParams() {
        if (readChar() != ')') {
            throwException();
        }
    }

    private void parseParamTypes() {
        while (true) {
            String t = parseFieldType();
            if (!"".equals(t)) {
                parsed.addParameterType(t);
            } else {
                break;
            }
        }
    }

    private void startParams() {
        if (readChar() != '(') {
            throwException();
        }
    }

    private void parseReturnType() {
        if (readChar() == 'V') {
            parsed.returnType = "V";
            return;
        }

        unreadChar();
        String t = parseFieldType();
        if (!"".equals(t)) {
            parsed.returnType = t;
            return;
        }

        throwException();
    }

    private String parseFieldType() {
        char curChar = readChar();
        switch (curChar) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
                return String.valueOf(curChar);
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            default:
                unreadChar();
                return "";
        }
    }

    private String parseObjectType() {
        String unread = raw.substring(offset);
        int semicolonIndex = unread.indexOf(";");
        if (semicolonIndex == -1) {
            throwException();
            return "";
        } else {
            int objStart = offset - 1;
            int objEnd = offset + semicolonIndex + 1;
            offset = objEnd;
            return raw.substring(objStart, objEnd);
        }
    }

    private String parseArrayType() {
        int arrStart = offset - 1;
        parseFieldType();
        int arrEnd = offset;
        return raw.substring(arrStart, arrEnd);
    }

    private void throwException() {
        throw new RuntimeException(String.format("BAD descriptor: %s", raw));
    }

    private char readChar() {
        return raw.charAt(offset++);
    }

    private void unreadChar() {
        offset--;
    }
}
