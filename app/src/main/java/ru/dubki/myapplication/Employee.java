package ru.dubki.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

class Employee implements Parcelable {
    String fam;
    String name;
    String ot;
    String date;

    public Employee(String fam, String name, String ot, String date) {
        this.fam = fam;
        this.name = name;
        this.ot = ot;
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fam);
        dest.writeString(this.name);
        dest.writeString(this.ot);
        dest.writeString(this.date);
    }

    protected Employee(Parcel in) {
        this.fam = in.readString();
        this.name = in.readString();
        this.ot = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
