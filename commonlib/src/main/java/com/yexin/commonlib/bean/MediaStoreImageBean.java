package com.yexin.commonlib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: zengven
 * date: 2019/1/21 17:39
 * desc: TODO
 */

public class MediaStoreImageBean implements Parcelable {

    public String bucketId;
    public String data;
    public String bucketDisplayName;
    public int orientation;
    public long dateAdded;
    public long dateModified;
    public long dateTaken;
    public String description;
    public String picasaId;
    public int isPrivate;
    public double latitude;
    public double longitude;
    public int miniThumbMagic;

    public MediaStoreImageBean() {
    }

    protected MediaStoreImageBean(Parcel in) {
        bucketId = in.readString();
        data = in.readString();
        bucketDisplayName = in.readString();
        orientation = in.readInt();
        dateAdded = in.readLong();
        dateModified = in.readLong();
        dateTaken = in.readLong();
        description = in.readString();
        picasaId = in.readString();
        isPrivate = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        miniThumbMagic = in.readInt();
    }

    public static final Creator<MediaStoreImageBean> CREATOR = new Creator<MediaStoreImageBean>() {
        @Override
        public MediaStoreImageBean createFromParcel(Parcel in) {
            return new MediaStoreImageBean(in);
        }

        @Override
        public MediaStoreImageBean[] newArray(int size) {
            return new MediaStoreImageBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bucketId);
        dest.writeString(data);
        dest.writeString(bucketDisplayName);
        dest.writeInt(orientation);
        dest.writeLong(dateAdded);
        dest.writeLong(dateModified);
        dest.writeLong(dateTaken);
        dest.writeString(description);
        dest.writeString(picasaId);
        dest.writeInt(isPrivate);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(miniThumbMagic);
    }

    @Override
    public String toString() {
        return "MediaStoreImageBean{" +
                "bucketId='" + bucketId + '\'' +
                ", data='" + data + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                ", orientation=" + orientation +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", dateTaken=" + dateTaken +
                ", description='" + description + '\'' +
                ", picasaId='" + picasaId + '\'' +
                ", isPrivate=" + isPrivate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", miniThumbMagic=" + miniThumbMagic +
                '}';
    }
}
