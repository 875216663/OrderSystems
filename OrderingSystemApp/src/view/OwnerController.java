package view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.FoodController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Food;
import model.Order;
import tools.Dbinitializer;
import javafx.scene.control.TableColumn;

public class OwnerController {
	// Those on the Tab "Orders"
	@FXML
	private TableView<Order> ordersTableView;
	@FXML
	private TableColumn<Order,String> Order_ID;
	@FXML
	private TableColumn<Order, String> Customer_ID;
	@FXML
	private TableColumn<Order, String> Customer_Name;
	@FXML
	private TableColumn<Order, ArrayList<Food>> Order_Dishes;
	@FXML
	private TableColumn<Order, String> Status;
	
	// Those on the Tab "Dishes"
    @FXML
    private TableColumn<?, ?> Dishes;
    @FXML
    private GridPane dishGallery;

    @FXML
    private TextField dishName;
    @FXML
    private TextField price;
    @FXML
    private ImageView dishImage;
    @FXML
    private TextArea dishDescription;
    @FXML
    private TextArea Ingradient;
    
    @FXML
    private Button addDish;
    @FXML
    private Button removeDish;
    @FXML
    private Button editDish;

    // those one the top button bar
	@FXML
	private Button Get_an_order;
	@FXML
	private Button Call_helper;
	@FXML
	private Button Owner_exit;
	
	// popularizing the table view with existing orders upon initializing
	private ObservableList<Order> orderShowList = FXCollections.observableArrayList();
	public void initialize() {
		/***************************Populating the order tab page**********************************************/
		
		Order_ID.setCellValueFactory(new PropertyValueFactory<>("formattedOrderID"));
		Customer_ID.setCellValueFactory(new PropertyValueFactory<>("formattedCustomerID"));
		Customer_Name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		Order_Dishes.setCellValueFactory(new PropertyValueFactory<>("foodNameString"));
		Status.setCellValueFactory(new PropertyValueFactory<>("status"));
		/**************************** Sample list start **************************/
		 
		Food food1 = new Food(1, "Coke", "A popular soft drink", "coke.png",1.1);
		Food food2 = new Food(2, "Fenta", "A popular soft drink", "fenta.png",2.3);
		Food food3 = new Food(3, "Burger", "A popular main food", "burger.png",6.5);
		Food food4 = new Food(4, "Whopper", "from burger king", "whopper.png",7.8);
		//for (Food food:foodArray) {
			//food = new Food();
		//}
		
		ArrayList<Food> foodList1 = new ArrayList<Food>(); 
		foodList1.add(food1);
		foodList1.add(food2);
		String result = "";
		for (Food food:foodList1) {
			System.out.println(food.getName());
			result += food.getName();
			//result += " \n";
		}
		System.out.println(result);
		ArrayList<Food> foodList2 = new ArrayList<Food>(); 
		foodList2.add(food3);
		foodList2.add(food4);		
	    ArrayList<Order> orderList = new ArrayList<>();
	    
	    orderList.add(new Order(9,"Tailung",foodList1));
	    orderList.add(new Order(7,"Master Shifu",foodList2));
	    /**************************** Sample list end **************************/
	    orderShowList.addAll(orderList);
	    ordersTableView.setItems(orderShowList);
	    
	    /***************************Populating the dishes tab page**********************************************/
		List<Food> foodList = new ArrayList<>();
		FoodController fController = new FoodController();
		foodList = fController.getFood();
		int columnIndex=0, rowIndex=0;
	    for (Food food : foodList) {
	    	//add the Dish image to dishGallery
	    	Image image = new Image("/images/"+food.getImageName());
	    	ImageView imageView = new ImageView(image);
	    	Button button = new Button(food.getName());
	    	button.setWrapText(true);
	    	button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
	        StackPane stackPane = new StackPane();
	        //borderPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        stackPane.setMaxSize(110, 110);
	        stackPane.getChildren().addAll(imageView, button);
	        StackPane.setAlignment(button, Pos.BOTTOM_CENTER);
	        StackPane.setAlignment(imageView, Pos.CENTER);
	        imageView.fitWidthProperty().bind(stackPane.widthProperty());
	        imageView.fitHeightProperty().bind(stackPane.heightProperty());
	        button.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	dishName.setText(food.getName());
	            	price.setText(food.getPrice()+"");
	            	dishImage.setImage(image);
	            	dishDescription.setText(food.getDescription());
	            	//dishIngradient.setText(food.getIngradient());//Will update in the future
	            }
	        });
	    	//imageView.setPreserveRatio(true);
	        dishGallery.add(stackPane, columnIndex, rowIndex);
	        // update columnIndex and rowIndex for next image
	        if (columnIndex==5) {
	        	columnIndex=0;
	        	rowIndex++;
	        } else {
	        	columnIndex++;
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	}
	
	// Event Listener on Button[#Get_an_order].onAction
	@FXML
	public void get_an_order(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#Call_helper].onAction
	@FXML
	public void call_helper(ActionEvent event) {
		// TODO Autogenerated
	}

    @FXML
    void setImage(MouseEvent event) {

    }
	// Event Listener on Button[#Owner_exit].onAction
	@FXML	
	public void owner_exit(ActionEvent event) {
	   	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.close();
	}
	// Event Listener on Button[#addDish].onAction
	@FXML
	public void addDish(ActionEvent event) {
		// Step 1 connect to database, get food data
		Dbinitializer.initDatabase(); //Create an instance of database class 
		List<Food> foodList = new ArrayList<>();
		FoodController fController = new FoodController();
		foodList = fController.getFood();
		// Step 2 check whether the current dishName is already in the list
		boolean found = false;
		for (Food food:foodList) {
			if (food.getName().equals(dishName.getText())) {
				found = true;
				break;
			} 
		}
		// Step 3 if not in the current food list, add the food
		if (found==false) {
			String[] parts = dishImage.getImage().getUrl().split("/");
			String fileName = parts[parts.length - 1];
			Food food = new Food(foodList.size()+1, 
					dishName.getText(), 
					dishDescription.getText(), 
					fileName,  //!! Need to add a test on whether there is a image loaded to dishImage ImageView
					Double.parseDouble(price.getText()));
			fController.addFood(food);
		}
		// Reflesh the page to make the new food show
		initialize();
				
	}
	// Event Listener on Button[#removeDish].onAction
	@FXML
	public void removeDish(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#editDish].onAction
	@FXML
	public void editDish(ActionEvent event) {
		// Step 1: Create a new AnchorPane object
		AnchorPane anchorPane = new AnchorPane();
		// Step 2: Set the constraints of the AnchorPane object,
		// This will ensure that the AnchorPane object fills up the entire grid cell
		AnchorPane.setTopAnchor(anchorPane, 0.0); 
		AnchorPane.setLeftAnchor(anchorPane, 0.0); 
		AnchorPane.setRightAnchor(anchorPane, 0.0); 
		AnchorPane.setBottomAnchor(anchorPane, 0.0);
		// Step 3: Add the input fields to the AnchorPane object 
		anchorPane.getChildren().addAll(dishName, price, dishImage, dishDescription, Ingradient);
		// Step 4: Add a button to the AnchorPane object, and then set its position
		Button button = new Button("Add Dish");
		AnchorPane.setBottomAnchor(button, 10.0);
		AnchorPane.setRightAnchor(button, 10.0);
		// Step 5: Add the button to the AnchorPane object
		anchorPane.getChildren().add(button);
		// Step 6: Finally, add the AnchorPane object to the GridPane object 
		int columnNumber = -1;
		int rowNumber = -1;
		for (int i = 0; i < dishGallery.getColumnCount(); i++) {
			final int i_f =i;
		    for (int j = 0; j < dishGallery.getRowCount(); j++) {
		    	final int j_f =j;
		        if (dishGallery.getChildren().stream().noneMatch(node -> 
		        GridPane.getColumnIndex(node) == i_f 
		        && dishGallery.getRowIndex(node) == j_f)) {
		            columnNumber = i;
		            rowNumber = j;
		            break;
		        }
		    }
		    if (columnNumber != -1 && rowNumber != -1) {
		        break;
		    }
		}
		dishGallery.add(anchorPane, columnNumber, rowNumber);
		// Step 7: add a record item to database
		Dbinitializer.initDatabase(); //Create an instance of database class 
		FoodController fController = new FoodController();
		
		Food food = new Food(1, dishName.getText(), dishDescription.getText(), dishImage.getImage().getUrl(),Double.parseDouble(price.getText()));
		//Food food = new Food("food2", 10, "food2 des");//Create an instance of Food class 
		food.addFood(food);//Add the food item to database
	}
}
