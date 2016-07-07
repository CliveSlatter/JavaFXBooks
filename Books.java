import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Books
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public String ISBN;
    public String title;
    public String authorfn;
    public String authorsn;
    public String genre;
    public int year;
    public String category;
    public String language;
    public float rating;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Books(String ISBN, String title, String authorfn, String authorsn, String genre, int year, String category, String language, float rating)
    {
        this.ISBN = ISBN;
        this.title = title;
        this.authorfn = authorfn;
        this.authorsn = authorsn;
        this.genre = genre;
        this.year = year;
        this.category = category;
        this.language = language;
        this.rating = rating;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return (ISBN + "\t" + title + "\t" + authorfn + "\t\t" + authorsn);
    }

    
    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Books> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("select isbn,title,authorfn,authorsn,genre,year,category,language,rating from books"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                                list.add(new Books(results.getString("ISBN"),
                                results.getString("title"),
                                results.getString("authorfn"), 
                                results.getString("authorsn"),
                                results.getString("genre"),
                                results.getInt("year"),
                                results.getString("category"),
                                results.getString("language"),
                                results.getFloat("rating")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Books getById(String isbnRef)
    {
        Books books = null;

        PreparedStatement statement = Application.database.newStatement("SELECT isbn, title, authorfn, authorsn, genre, year, category, language, rating FROM books WHERE isbn = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setString(1, isbnRef);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    books = new Books(results.getString("isbn"), results.getString("title"), results.getString("authorfn"), results.getString("authorsn"), results.getString("genre"), results.getInt("year"), results.getString("category"),results.getString("language"),results.getFloat("rating"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return books;
    }
    
    public void addNew()
    {
        try
        {
            PreparedStatement statement;
            
            statement = Application.database.newStatement("SELECT ISBN FROM books ORDER BY ISBN DESC");
            ResultSet results = Application.database.runQuery(statement);
            statement = Application.database.newStatement("INSERT INTO books (ISBN, title, authorfn, authorsn, year, genre, category, language, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");             
                        
            
            statement.setString(1, ISBN);
            statement.setString(2, title);
            statement.setString(3, authorfn);
            statement.setString(4, authorsn);
            statement.setInt(5, year);
            statement.setString(6, genre);
            statement.setString(7, category);
            statement.setString(8, language);
            statement.setFloat(9, rating);
            
            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }
    
    public void save()
    {
        try
        {
            PreparedStatement statement;    
        
            statement = Application.database.newStatement("SELECT ISBN FROM books ORDER BY ISBN DESC");
            ResultSet results = Application.database.runQuery(statement); 
            statement = Application.database.newStatement("UPDATE books SET isbn = ?, title = ? authorfn = ? authorsn = ? year = ? genre = ? category = ? language = ? rating = ? WHERE id = ?");             
            statement.setString(1, ISBN);
            statement.setString(2, title);
            statement.setString(3, authorfn);
            statement.setString(4, authorsn);
            statement.setInt(5, year);
            statement.setString(6, genre);
            statement.setString(7, category);
            statement.setString(8, language);
            statement.setFloat(9, rating);
            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }        
    }
    
}
