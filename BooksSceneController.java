import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class BooksSceneController
{
    private Stage stage;
    private MainSceneController parent;
    private Books books;
    
    @FXML   private TitledPane backgroundPane;    
    @FXML   private Button saveID;
    @FXML   private Button deleteID;
    @FXML   private Button nextID;
    @FXML   private Button previousID;
    @FXML   private Button addID;
    @FXML   private Button backButton;
    @FXML   private TextField titleID;
    @FXML   private TextField authorID;
    @FXML   private TextField publisherID;
    @FXML   private TextField yearID;
    @FXML   private TextField genreID;
    @FXML   private TextField categoryID;
    @FXML   private TextField isbnID;
    @FXML   private TextField languageID;
    @FXML   private TextField ratingID;
    @FXML   private Label titleLabel;
    @FXML   private Label authorLabel;
    @FXML   private Label publisherLabel;
    @FXML   private Label yearLabel;
    @FXML   private Label genreLabel;
    @FXML   private Label categoryLabel;
    @FXML   private Label isbnLabel;
    @FXML   private Label languageLabel;
    @FXML   private Label ratingLabel;

    public BooksSceneController()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        
        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    @FXML void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
            assert backgroundPane != null: "Could not find ";    
            assert saveID != null: "Could not find ";
            assert deleteID != null: "Could not find ";
            assert addID != null: "Could not find ";
            assert nextID != null: "Could not find ";
            assert previousID != null: "Could not find ";
            assert titleID != null: "Could not find ";
            assert authorID != null: "Could not find ";
            assert publisherID != null: "Could not find ";
            assert yearID != null: "Could not find ";
            assert genreID != null: "Could not find ";
            assert categoryID != null: "Could not find ";
            assert isbnID != null: "Could not find ";
            assert languageID != null: "Could not find ";
            assert ratingID != null: "Could not find ";
            assert titleLabel != null: "Could not find ";
            assert authorLabel != null: "Could not find ";
            assert publisherLabel != null: "Could not find ";
            assert yearLabel != null: "Could not find ";
            assert genreLabel != null: "Could not find ";
            assert categoryLabel != null: "Could not find ";
            assert isbnLabel != null: "Could not find ";
            assert languageLabel != null: "Could not find ";
            assert ratingLabel != null: "Could not find ";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }
        /* Next, we load the list of fruit from the database and populate the listView. */
        //System.out.println("Populating scene with items from the database...");        
        //@SuppressWarnings("unchecked")
        //List<Books> targetList = listView.getItems();  // Grab a reference to the listView's current item list.
        //Books.readAll(targetList);                     // Hand over control to the fruit model to populate this list.
    }

    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    Application.terminate();
                }
            });
    }
    
    public void setParent(MainSceneController parent)
    {
        this.parent = parent;
    }
    
    /*
     * The loadItem call from the mainSceneController class (openNewScene method) using the ISBN reference.
     * This in turn calls the getById method in the Books class using the ISBN via the isbnRef parameter. This then
     * returns the record that matches the ISBN number and stores it in an objects called books made from the Books class
     */
    
    public void loadItem(String isbnRef)
    {        
        books = Books.getById(isbnRef);
        titleID.setText(books.title);
        authorID.setText(books.authorfn + " " + books.authorsn);
        genreID.setText(books.genre);
        categoryID.setText(books.category);
        languageID.setText(books.language);
        //ratingID.setFloat(books.rating);
        isbnID.setText(books.ISBN);
        
        /*List<Category> targetList = categoryChoiceBox.getItems();

        for(Category c : targetList)
        {
            if (c.id == thing.categoryId)
            {
                categoryChoiceBox.getSelectionModel().select(c);
            }                
        }
        */
    }
    
    @FXML void saveClicked() 
    {
        System.out.println("Save button was clicked");    
    }
    
    @FXML void addClicked() 
    {
        System.out.println("Add button was clicked");
    }
    
    @FXML void deleteClicked() 
    {
        System.out.println("Delete button was clicked");
    }
    
    @FXML void nextClicked() 
    {
        System.out.println("Next button was clicked");
    }
    
    @FXML void previousClicked() 
    {
        System.out.println("Previous button was clicked");
    }    
    @FXML void backClicked()
    {
        System.out.println("Back button was clicked");
        stage.close();
    }
}