package com.yexin.commonlib.utils;

/**
 * author: zengven
 * date: 2017/12/5 16:48
 * desc: super StringBuffer
 */
public class SuperStringBuffer {

    private final StringBuffer mSb;

    public SuperStringBuffer() {
        mSb = new StringBuffer();
    }

    public SuperStringBuffer append(String string) {
        mSb.append(string);
        return this;
    }

    public SuperStringBuffer append(CharSequence s) {
        mSb.append(s);
        return this;
    }

    public SuperStringBuffer append(StringBuffer sb) {
        mSb.append(sb);
        return this;
    }

    public SuperStringBuffer append(int i) {
        mSb.append(i);
        return this;
    }

    public SuperStringBuffer append(long l) {
        mSb.append(l);
        return this;
    }

    public SuperStringBuffer append(float f) {
        mSb.append(f);
        return this;
    }

    public SuperStringBuffer append(double d) {
        mSb.append(d);
        return this;
    }

    public SuperStringBuffer append(char ch) {
        mSb.append(ch);
        return this;
    }

    public SuperStringBuffer append(boolean b) {
        mSb.append(b);
        return this;
    }

    public SuperStringBuffer append(Object obj) {
        mSb.append(obj);
        return this;
    }

    public SuperStringBuffer append(char[] chars) {
        mSb.append(chars);
        return this;
    }

    public SuperStringBuffer append(char[] chars, int start, int length) {
        mSb.append(chars, start, length);
        return this;
    }

    public SuperStringBuffer append(CharSequence s, int start, int end) {
        mSb.append(s, start, end);
        return this;
    }

    public SuperStringBuffer appendCodePoint(int codePoint) {
        mSb.append(codePoint);
        return this;
    }

    public String toString() {
        return mSb.toString();
    }

    public SuperStringBuffer appendLine(String string) {
        mSb.append(string).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(CharSequence s) {
        mSb.append(s).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(int i) {
        mSb.append(i).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(long l) {
        mSb.append(l).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(float f) {
        mSb.append(f).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(double d) {
        mSb.append(d).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(char ch) {
        mSb.append(ch).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuffer appendLine(boolean b) {
        mSb.append(b).append(System.getProperty("line.separator"));
        return this;
    }

}
