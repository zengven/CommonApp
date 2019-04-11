package com.yexin.commonlib.utils;

/**
 * author: zengven
 * date: 2017/12/5 17:19
 * desc: super StringBuilder
 */
public class SuperStringBuilder {

    private final StringBuilder mSb;

    public SuperStringBuilder() {
        mSb = new StringBuilder();
    }

    public SuperStringBuilder append(String string) {
        mSb.append(string);
        return this;
    }

    public SuperStringBuilder append(CharSequence s) {
        mSb.append(s);
        return this;
    }

    public SuperStringBuilder append(StringBuffer sb) {
        mSb.append(sb);
        return this;
    }

    public SuperStringBuilder append(int i) {
        mSb.append(i);
        return this;
    }

    public SuperStringBuilder append(long l) {
        mSb.append(l);
        return this;
    }

    public SuperStringBuilder append(float f) {
        mSb.append(f);
        return this;
    }

    public SuperStringBuilder append(double d) {
        mSb.append(d);
        return this;
    }

    public SuperStringBuilder append(char ch) {
        mSb.append(ch);
        return this;
    }

    public SuperStringBuilder append(boolean b) {
        mSb.append(b);
        return this;
    }

    public SuperStringBuilder append(Object obj) {
        mSb.append(obj);
        return this;
    }

    public SuperStringBuilder append(char[] chars) {
        mSb.append(chars);
        return this;
    }

    public SuperStringBuilder append(char[] chars, int start, int length) {
        mSb.append(chars, start, length);
        return this;
    }

    public SuperStringBuilder append(CharSequence s, int start, int end) {
        mSb.append(s, start, end);
        return this;
    }

    public SuperStringBuilder appendCodePoint(int codePoint) {
        mSb.append(codePoint);
        return this;
    }

    public String toString() {
        return mSb.toString();
    }

    public SuperStringBuilder appendLine(String string) {
        mSb.append(string).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(CharSequence s) {
        mSb.append(s).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(int i) {
        mSb.append(i).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(long l) {
        mSb.append(l).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(float f) {
        mSb.append(f).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(double d) {
        mSb.append(d).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(char ch) {
        mSb.append(ch).append(System.getProperty("line.separator"));
        return this;
    }

    public SuperStringBuilder appendLine(boolean b) {
        mSb.append(b).append(System.getProperty("line.separator"));
        return this;
    }

}
