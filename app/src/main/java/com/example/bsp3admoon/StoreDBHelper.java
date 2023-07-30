package com.example.bsp3admoon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StoreDBHelper extends SQLiteOpenHelper {
    public static final String BOOK_TABLE = "BOOK";
    public static final String COLUMN_BOOK_ID = "BOOK_ID";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_AUTHOR = "AUTHOR";
    public static final String COLUMN_GENRE = "GENRE";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_RATING = "RATING";
    public static final String COLUMN_STOCK_QUANTITY = "STOCK_QUANTITY";

    public StoreDBHelper(@Nullable Context context) {
        super(context, "bookStoreDb", null, 1);
    }
    //will be called the first time the DB is referenced
    //probably there will be a problem with the double cause it needs parameters Double(size,digit)
    //if an error occurred here make sure of the commas and spaces between the operators
    @Override
    public void onCreate(SQLiteDatabase db) {
        String bookTableStat = " CREATE TABLE " + BOOK_TABLE + " ( " + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_TITLE + " TEXT NOT NULL, " + COLUMN_AUTHOR + " TEXT NOT NULL, " + COLUMN_GENRE+ " TEXT, " + COLUMN_PRICE+ " DOUBLE NOT NULL, " +COLUMN_RATING+ " FLOAT, " + COLUMN_STOCK_QUANTITY+ " INTEGER NOT NULL)";
        db.execSQL(bookTableStat);

        //inserting values to the BOOK_TABLE
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(1,'Kafka on the Shore' , 'Haruki Murakami','Fiction', 160, 4.13, 15)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(2,'The Midnight Library' , 'Matt Haig','Science Fiction', 170, 4.05, 80)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(3,'A Little Life' , 'Hanya Yanagihara','Novel', 160, 4.31, 50)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(4,'When Breath Becomes Air' , 'Paul Kalanithi','Biography', 200, 4.36, 10)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(5,'Who Moves My Cheese?' , 'Spencer Johnson','Self-help', 120, 3.83, 35)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(6,'The Idiot Brain: What Your Head is Really up to' , 'Dean Burnett','Comedy', 50, 3.98, 17)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(7,'Happy Brain: Where Happiness Comes From' , 'Dean Burnett','Self-help', 55, 3.78 , 15)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(8,'Sapiens: A Brief History of Humankind' , 'Yuval Noah Harari','Non-Fiction', 70, 4.4, 25)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(9,'The Power of Habit' , 'Charles Duhigg','Self-help', 160, 4.11, 100)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(10,'Grokking Algorithms An Illustrated Guide For Programmers' , 'Aditya Y. Bhargava','Reference Book', 260, 4.41, 85)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(11,'We Were Liars' , 'E. Lockhart','Young Adult Fiction', 160, 3.81, 15)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(12,'10 Minutes 38 Seconds in This Strange World' , 'Elif Shafak','Phsycological Fiction', 160, 4.09, 166)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(13,'A Christmas Carol' , 'Charles Dickens','Fairytale', 185, 4.06, 19)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(14,'The Subtle Art of not Giving a f*ck' , 'Mark Manson','Self-help', 154, 3.92, 205)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(15,'The Kiss Quotient' , 'Helen Hoang','Romance', 200, 3.9, 145)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(16,'The Silent Patient' , 'Alex Michaelides','Thriller', 180, 4.11, 86)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(17,'Six of Crows' , 'Leigh Bardugo','Young Adult Fiction', 205, 4.46, 9)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(18,'Crooked Kingdom' , 'Leigh Bardugo','Young Adult Fiction', 215, 4.59, 16)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(19,'It Ends with Us' , 'Collen Hoover','Romance-Fiction', 150, 4.42, 23)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(20,'The Song of Achilles' , 'Madeline Miller','Romance', 210, 4.41, 56)");
        db.execSQL(" INSERT INTO " + BOOK_TABLE + " values(21,'The Seven Husbands of Evelyn Hugo' , 'Taylor Jenkis Reid','Romance', 167, 4.44, 150)");


        //creating the "Cart" table
        db.execSQL("CREATE TABLE Cart ( Useremail TEXT ,  BOOK_ID INTEGER REFERENCES BOOK (BOOK_ID))");





    }
    //when the version of the DB change
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Cursor AllCart(String userEmail)
    {
        SQLiteDatabase db8 = getReadableDatabase();
        String []arg ={userEmail};
        Cursor cursor= db8.rawQuery
                ("select  Cart.useremail, Book.BOOK_TITLE,  Book.AUTHOR, Book.Book_id, Book.PRICE from BOOK, Cart where cart.userEmail like ? and cart.book_id=book.book_id ", arg);
        if (cursor !=null)
        {
            cursor.moveToFirst();
        }

        db8.close();
        return cursor;
    }
    public boolean addtoCart(String useremail,int Bookid)
    {
        try {
            SQLiteDatabase db7 = this.getWritableDatabase();
            ContentValues cv2 = new ContentValues();
            cv2.put("BOOK_ID", Bookid);
            cv2.put("useremail", useremail);
            db7.insert("cart", null, cv2);
            db7.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public void removeOneCart(int bookID){
        SQLiteDatabase db9= this.getWritableDatabase();
        db9.delete("cart" , " BOOK_ID=' " + bookID + " ' ", null);
        db9.close();
    }
    public Cursor fetchOneCartItem (String name)
    {
        SQLiteDatabase db4= getReadableDatabase();
        String arg[]={"%"+name+"%"};
        Cursor cursor= db4.rawQuery
                ("select BOOK_ID from BOOK where BOOK_TITLE like ?", arg);

        cursor.moveToFirst();

        db4.close();
        return cursor;

    }
    //for the add book option in the admin
    public boolean addOne(Book book){
        SQLiteDatabase db6 = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        Cursor cursor= db6.rawQuery
                ("select MAX (BOOK_ID)from BOOK ", null);

        cursor.moveToFirst();
       int ID= Integer.parseInt( cursor.getString(0));

        cv1.put(COLUMN_BOOK_ID, ID+1);
        cv1.put(COLUMN_BOOK_TITLE, book.getTitle());
        cv1.put(COLUMN_AUTHOR, book.getAuthor());
        cv1.put(COLUMN_GENRE, book.getGenre());
        cv1.put(COLUMN_PRICE, book.getPrice());
        cv1.put(COLUMN_RATING, book.getRatings());
        cv1.put(COLUMN_STOCK_QUANTITY, book.getQuantity());
        long insert = db6.insert("BOOK", null, cv1);
        if(insert == -1) {
            db6.close();
            return false;
        }
        else {
            db6.close();
            return true;
        }

    }
    public void removeOne(int bookID){
        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.delete(BOOK_TABLE , " BOOK_ID=' " + bookID + " ' ", null);
        db1.close();
    }

    public void updateBookInfo(String oldBookTitle, String newTitle, String newAuthor, String newGenre, double newPrice, float newRating, int newStockQuantity){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues newInfo = new ContentValues();
        newInfo.put(COLUMN_BOOK_TITLE, newTitle);
        newInfo.put(COLUMN_AUTHOR, newAuthor);
        newInfo.put(COLUMN_GENRE, newGenre);
        newInfo.put(COLUMN_PRICE, newPrice);
        newInfo.put(COLUMN_RATING, newRating);
        newInfo.put(COLUMN_STOCK_QUANTITY, newStockQuantity);
        db2.update(BOOK_TABLE, newInfo, "BOOK_TITLE like ? " , new String[]{oldBookTitle});
        db2.close();
    }
    public Cursor fetchAllBooks()
    {
        SQLiteDatabase db3 = getReadableDatabase();
        String [] rowDetails={COLUMN_BOOK_ID, COLUMN_BOOK_TITLE, COLUMN_AUTHOR};
        Cursor cursor= db3.rawQuery
                ("select BOOK_ID , BOOK_TITLE, AUTHOR,GENRE, PRICE,RATING from BOOK", null);
        if (cursor !=null)
        {
            cursor.moveToFirst();
        }

        db3.close();
        return cursor;
    }
    public Cursor fetchOneBook(String name)
    {
        SQLiteDatabase db4= getReadableDatabase();
        String arg[]={"%"+name+"%"};
        Cursor cursor= db4.rawQuery
                ("select BOOK_ID , BOOK_TITLE, AUTHOR, GENRE, PRICE,RATING, STOCK_QUANTITY from BOOK where BOOK_TITLE like ?", arg);

            cursor.moveToFirst();

        db4.close();
        return cursor;

    }
    private SQLiteDatabase db;
public Cursor getBooks(String bookName)
    {
        db=getReadableDatabase();
        Cursor matchedBooks=db.rawQuery("select * from BOOK where BOOK_TITLE like ?"
                ,new String[]{"%"+bookName+"%"});

        if(matchedBooks.getCount()!=0)
        {
            matchedBooks.moveToFirst();
            db.close();
            return matchedBooks;
        }
        db.close();
        return null;
    }

    public Cursor getBookData(int bookID)
    {
        db=getReadableDatabase();
        Cursor bookDetails=db.query("BOOK", new String[]{"BOOK_TITLE","AUTHOR","GENRE","PRICE","RATING","STOCK_QUANTITY"},"BOOK_ID="+bookID,null,null,null,null);
        bookDetails.moveToFirst();
        db.close();
        return bookDetails;
    }
    //remaining methods: a method to do sth with the foreign key + edits on the addOne method
}

