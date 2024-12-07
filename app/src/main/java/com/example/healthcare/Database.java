package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text , email text , password text)";
        sqLiteDatabase.execSQL(qry1);
        String qry2 = "create table cart(username text , product text , price float, otype text)";
        sqLiteDatabase.execSQL(qry2);
        String qry3 = "create table orderplace(username text , fullname text , address text, contactno text, pincode int, date text, time text,amount float, otype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username , String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username" , username);
        cv.put("email" , email);
        cv.put("password" , password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username, String password){
        int result = 0;
        String str[] = new String[2];
        str[0]= username;
        str[1]= password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if (c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void addCart(String username, String product, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("Username",username);
        cv.put("Product",product);
        cv.put("Price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0]= username;
        str[1]= product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product=?",str);

        if (c.moveToFirst()){
            result = 1;
        }
        db.close();
        return result;
    }
    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0]= username;
        str[1]= otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username = ? and otype = ?",str);
        db.close();
    }
    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0]= username;
        str[1]= otype;
        Cursor c= db.rawQuery("select * from cart where username = ? and otype = ?",str);
        if (c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public void addOrder(String username, String fullname, String address ,String contact ,int pincode,String date, String time , float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("Username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0]= username;
        Cursor c= db.rawQuery("select * from orderplace where username = ?",str);
        if (c.moveToFirst()){
            do {
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)
                        +"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8)+"$");
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppintmentExists(String username,String fullname, String address ,String contact ,String date, String time){
        int result = 0;
        String str[] = new String[6];
        str[0]= username;
        str[1]= fullname;
        str[2]= address;
        str[3]= contact;
        str[4]= date;
        str[5]= time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname=? and address = ? and contact = ? and date = ? and time = ?",str);
        if (c.moveToFirst()){
            result =1;
        }
        db.close();
        return result;
    }
}



//package com.example.healthcare;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class Database {
//    private Context context;
//    private DatabaseReference databaseReference;
//
//    public Database(Context context) {
//        this.context = context;
//        this.databaseReference = FirebaseDatabase.getInstance().getReference();
//    }
//
//    // User Registration
//    public void register(String username, String email, String password) {
//        DatabaseReference usersRef = databaseReference.child("users");
//        User user = new User(username, email, password); // Create a user object
//        usersRef.child(username).setValue(user)
//                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//
//    // User Login
//    public void login(String username, String password, LoginCallback callback) {
//        DatabaseReference usersRef = databaseReference.child("users").child(username);
//        usersRef.get().addOnSuccessListener(dataSnapshot -> {
//            if (dataSnapshot.exists()) {
//                User user = dataSnapshot.getValue(User.class);
//                if (user != null && user.password.equals(password)) {
//                    callback.onSuccess();
//                } else {
//                    callback.onFailure("Invalid credentials");
//                }
//            } else {
//                callback.onFailure("User not found");
//            }
//        }).addOnFailureListener(e -> callback.onFailure("Error: " + e.getMessage()));
//    }
//
//    public interface LoginCallback {
//        void onSuccess();
//        void onFailure(String errorMessage);
//    }
//
//    // Add Item to Cart
//    public void addCart(String username, String product, float price, String otype) {
//        DatabaseReference cartRef = databaseReference.child("cart").child(username);
//        String productId = cartRef.push().getKey();
//        CartItem cartItem = new CartItem(product, price, otype);
//        if (productId != null) {
//            cartRef.child(productId).setValue(cartItem)
//                    .addOnSuccessListener(aVoid -> Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show())
//                    .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//        }
//    }
//
//    // Get Cart Data
//    public void getCartData(String username, CartCallback callback) {
//        DatabaseReference cartRef = databaseReference.child("cart").child(username);
//        cartRef.get().addOnSuccessListener(dataSnapshot -> {
//            ArrayList<CartItem> cartItems = new ArrayList<>();
//            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                CartItem item = snapshot.getValue(CartItem.class);
//                if (item != null) {
//                    cartItems.add(item);
//                }
//            }
//            callback.onSuccess(cartItems);
//        }).addOnFailureListener(e -> callback.onFailure("Error: " + e.getMessage()));
//    }
//
//    public interface CartCallback {
//        void onSuccess(ArrayList<CartItem> cartItems);
//        void onFailure(String errorMessage);
//    }
//
//    // Remove Cart
//    public void removeCart(String username) {
//        DatabaseReference cartRef = databaseReference.child("cart").child(username);
//        cartRef.removeValue()
//                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Cart cleared", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//
//    // Add Order
//    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
//        DatabaseReference orderRef = databaseReference.child("orders").child(username);
//        String orderId = orderRef.push().getKey();
//        Order order = new Order(fullname, address, contact, pincode, date, time, price, otype);
//        if (orderId != null) {
//            orderRef.child(orderId).setValue(order)
//                    .addOnSuccessListener(aVoid -> Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show())
//                    .addOnFailureListener(e -> Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//        }
//    }
//
//    // Get Order Data
//    public void getOrderData(String username, OrderCallback callback) {
//        DatabaseReference orderRef = databaseReference.child("orders").child(username);
//        orderRef.get().addOnSuccessListener(dataSnapshot -> {
//            ArrayList<Order> orders = new ArrayList<>();
//            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                Order order = snapshot.getValue(Order.class);
//                if (order != null) {
//                    orders.add(order);
//                }
//            }
//            callback.onSuccess(orders);
//        }).addOnFailureListener(e -> callback.onFailure("Error: " + e.getMessage()));
//    }
//
//    public interface OrderCallback {
//        void onSuccess(ArrayList<Order> orders);
//        void onFailure(String errorMessage);
//    }
//}


