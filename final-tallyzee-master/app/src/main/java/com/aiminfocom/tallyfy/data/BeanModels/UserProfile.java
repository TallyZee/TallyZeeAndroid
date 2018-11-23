package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
@Entity(tableName = "UserProfile")
public class UserProfile  implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    private String password;
    private String cnpassword;
    private String phone;
    private String createdAt;
    private String createdBy;
    private String accountType;
    private String subscriptionDate;
    private String updatedAt;
    private String updatedBy;
    private boolean isEmailVerified;
    private String lastSync;



    public UserProfile()
    {

    }
    @Ignore
    public UserProfile(String name, String email, String password, String cnpassword, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpassword = cnpassword;
        this.phone = phone;
    }
    @Ignore
    public UserProfile(String email, String name)
    {
        this.email=email;
        this.name=name;
    }

    @Ignore
    public UserProfile(String email,String name,String phone)
    {
        this.email=email;
        this.name=name;
        this.phone=phone;
    }
    @Ignore
    public UserProfile(String name, String email, String password, String cnpassword, String phone,boolean isEmailVerified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpassword = cnpassword;
        this.phone = phone;
        this.isEmailVerified=isEmailVerified;
    }
    @Ignore
    public UserProfile(String name, String email, String password, String cnpassword, String phone, String createdAt, String createdBy, String accountType, String subscriptionDate, String updatedAt, String updatedBy) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpassword = cnpassword;
        this.phone = phone;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.accountType = accountType;
        this.subscriptionDate = subscriptionDate;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected UserProfile(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        cnpassword = in.readString();
        phone = in.readString();
        createdAt = in.readString();
        createdBy = in.readString();
        accountType = in.readString();
        subscriptionDate = in.readString();
        updatedAt = in.readString();
        updatedBy = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public void setUsername(String username)
    {
        this.name=username;
    }

    public String getUsername()
    {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnpassword() {
        return cnpassword;
    }

    public void setCnpassword(String cnpassword) {
        this.cnpassword = cnpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.email);
        parcel.writeString(this.password);
        parcel.writeString(this.cnpassword);
        parcel.writeString(this.phone);
        parcel.writeString(this.accountType);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.createdBy);
        parcel.writeString(this.subscriptionDate);
        parcel.writeString(this.updatedAt);
        parcel.writeString(this.updatedBy);
        parcel.writeBooleanArray(new boolean[]{this.isEmailVerified});

    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cnpassword='" + cnpassword + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", accountType='" + accountType + '\'' +
                ", subscriptionDate='" + subscriptionDate + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", lastSync='" + lastSync + '\'' +
                '}';
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getLastSync() {
        return lastSync;
    }

    public void setLastSync(String lastSync) {
        this.lastSync = lastSync;
    }
}
