import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class MainSceneController
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane backgroundPane;    
    @FXML   private Button exitButton;
    @FXML   private ListView listView;

    public MainSceneController()          // The constructor method, called first when the scene is loaded.
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

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
            assert backgroundPane != null : "Can't find background pane.";
            assert exitButton != null : "Can't find exit button.";
            assert listView != null : "Can't find list box.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }
        
        /* Next, we load the list of fruit from the database and populate the listView. */
        System.out.println("Populating scene with items from the database...");        
        @SuppressWarnings("unchecked")
        List<Books> targetList = listView.getItems();  // Grab a reference to the listView's current item list.
        Books.readAll(targetList);                     // Hand over control to the books model to populate this list.
    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) we need
     * to setup event handlers. This happens after the constructor and the initialize methods. Once this is
     * complete, the scene is fully loaded and ready to use. */
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
    
    @FXML   void exitClicked()
    {
        System.out.println("Exit was clicked!");        
        Application.terminate();        // Call the terminate method in the main Application class.
    }

    /* This method, set in SceneBuilder to occur when the listView is clicked, establishes which
     * item in the view is currently selected (if any) and outputs it to the console. */    
    @FXML   void listViewClicked()
    {
        Books selectedItem = (Books) listView.getSelectionModel().getSelectedItem();
        System.out.println("List view was clicked with the ISBN " + selectedItem.ISBN);
        openNewScene(selectedItem.ISBN);  /* Passes the ISBN part of the selected row to the openNewScene method */
    }
    
    void openNewScene(String isbnRef)
    {
         FXMLLoader loader = new FXMLLoader(Application.class.getResource("JavaFXBooks.fxml"));

        try
        {
            Stage stage2 = new Stage();
            stage2.setTitle("Book Details");
            stage2.setScene(new Scene(loader.load()));
            stage2.show();           
            BooksSceneController controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);
                        
            controller2.setParent(this);
            if (isbnRef != "0") controller2.loadItem(isbnRef);  /* opens the second scene using the parameter value isbnRef */
            
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }    
    }
}