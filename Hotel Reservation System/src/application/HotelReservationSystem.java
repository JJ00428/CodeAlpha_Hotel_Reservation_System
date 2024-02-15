package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HotelReservationSystem extends Application{
	
	private static Stage window;
	private List<Room> rooms;
	private Label title;
	private Label labeltype;
	private ComboBox<String> selecttype;
	private Label labeldate;
	public DatePicker checkin;
	public DatePicker checkout;
	private Button reserverooms;
	private Label labelprice;
	private double totalprice;
	private Button AddToCart;
	private Button ShowCart;
	private Button booking;
	private Button back;
	private Button back1;
	private List<Room> roomsincart;
	private List<Room> bookedrooms;
	private Label warning;
	private Label success;
	private Scene scene1,scene2,scene3;
	private VBox cartBox;
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	

	





	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		
		rooms = new ArrayList<>();
		rooms.add(new Room("Single",500));
		rooms.add(new Room("Twin",900));
		rooms.add(new Room("Suite",1400));
		
		roomsincart = new ArrayList<>();
		
		bookedrooms = new ArrayList<>();
		
		totalprice = 0;
		
		
		title = new Label("Hotel Rooms Reservation");
		
		labeltype = new Label("Please Select Room Type:");
		selecttype = new ComboBox<>(FXCollections.observableArrayList("Single", "Twin", "Suite"));
		selecttype.setValue("Single");
		
		labeldate = new Label("Please enter the Date of Your Stay:");
		checkin = new DatePicker();
		checkout = new DatePicker();
		
	
		
		labelprice = new Label("Total Price:$" + totalprice);
		
		
		AddToCart = new Button ("Add To Cart");
		ShowCart = new Button ("Show Cart");
		
		reserverooms = new Button("Pay & Reserve Room(s)");
		
		warning = new Label();
		success = new Label();
		
		booking = new Button("Show Your Booking");
		
		back = new Button("Back");
		
		back1 = new Button("Back");
		
		
		
		
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.BLACK);
		shadow.setRadius(5);
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		
		
		title.setEffect(shadow);
		
		
		
		VBox V= new VBox(10);
		V.setPadding(new Insets(10));
		V.getChildren().addAll(title,labeltype,selecttype,labeldate,checkin,checkout,labelprice,AddToCart,ShowCart,reserverooms,booking,warning,success);
		V.setAlignment(Pos.CENTER);
		
		
		
		
		
		
		cartBox = new VBox(10);
        cartBox.setAlignment(Pos.CENTER);
       // cartBox.getChildren().add(back);
        
        
        VBox Z=new VBox(10);
		Z.setPadding(new Insets(10));
		Z.getChildren().addAll(cartBox,back);
		Z.setAlignment(Pos.CENTER);
		
	
		
	
		ScrollPane s1=new ScrollPane(Z);
		
		
		
		VBox W=new VBox(10);
		W.setPadding(new Insets(10));
		W.getChildren().addAll(s1);
		W.setAlignment(Pos.CENTER);
		
		
		
		scene2 = new Scene(W,1000,800);
		
		
		VBox bookingBox = new VBox(10);
		bookingBox.setAlignment(Pos.CENTER);
		
		
		
		VBox B=new VBox(10);
		B.setPadding(new Insets(10));
		B.getChildren().addAll(bookingBox,back1);
		B.setAlignment(Pos.CENTER);
		
		ScrollPane s2=new ScrollPane(B);
		
		
		VBox A=new VBox(10);
		A.setPadding(new Insets(10));
		A.getChildren().addAll(s2);
		A.setAlignment(Pos.CENTER);
		
		
		scene3 = new Scene(A,1000,800);

		
		
		
		AddToCart.setOnAction(e -> {
		    if (checkin.getValue() == null || checkout.getValue() == null) {
		        warning.setText("Please Select The Date Of Your Stay");
		    } 
		    else if(checkin.getValue().isAfter(checkout.getValue())){
		    	warning.setText("Invalid Date Period");
		    }else {
		        LocalDate startDate = checkin.getValue();
		        LocalDate endDate = checkout.getValue();
		        boolean isAvailable = true;

		        for (Room r : rooms) {
		        	if(r.getRoomtype().equals(selecttype.getValue()) ){
		            //if (r.isIsreserved()) {
		                List<LocalDate> reservedDates = r.getReservedDates();

		                for (LocalDate date : reservedDates) {
		                    if (!(date.isAfter(endDate) || date.isBefore(startDate) )) {
		                        isAvailable = false;
		                        break;
		                    }
		                }//}

		                if (!isAvailable) {
		                    break;
		                }
		            }
		        }

		        if (isAvailable) {
		            Room selected = rooms.stream().filter(room -> room.getRoomtype().equals(selecttype.getValue())).findFirst().orElse(null);
		            success.setText("");
		            if (selected != null /*&& !selected.isIsreserved()*/) {
		                selected.reserveroom(checkin.getValue(),checkout.getValue());
		                selected.addReservedDate(startDate);
		                selected.addReservedDate(endDate);
		                
		                Label roomLabel = new Label(selected.showdetails(startDate,endDate));
				        Button remove = new Button("Remove From Cart");

				        remove.setStyle("-fx-background-color: #2993B2; -fx-text-fill: white;");
				        remove.setOnMouseEntered(e1 -> {
				            remove.setStyle("-fx-background-color: #7EABB8; -fx-text-fill: white;");
				            remove.setEffect(shadow);
				        });
				        remove.setOnMouseExited(e1 -> {
				            remove.setStyle("-fx-background-color: #2993B2; -fx-text-fill: white;");
				            remove.setEffect(null);
				        });

				        roomLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #0D4758;");

				        remove.setOnAction(event -> {
				            roomsincart.remove(selected);
				            selected.unreserveroom(startDate, endDate);
				            cartBox.getChildren().removeAll(roomLabel, remove);
				            Integer numofdays = startDate.until(endDate).getDays();
				            totalprice -= (selected.getRoomprice() * numofdays);
				            labelprice.setText("Total Price:$" + totalprice);
				        });
				        cartBox.getChildren().addAll(roomLabel, remove);
		                
		                
		                warning.setText("");
		                roomsincart.add(selected);
		                gettotalPrice(selecttype.getValue(),checkin.getValue(),checkout.getValue());
		                labelprice.setText("Total Price:$" + totalprice);
		            } else {
		                warning.setText("No Available Rooms For Selected Requirments.");
		            }
		        } else {
		            warning.setText("Room is not available for selected dates.");
		        }
		    }
		});
		
		
		reserverooms.setOnAction(e -> { 
			warning.setText("");
		//	Integer count = roomsincart.size();
			
			/*for(int i = 0;i<count;i++){
				roomsincart.get(i).reserveroom();
			}*/
			
			if(totalprice==0){warning.setText("Please Select Rooms To Reserve");}
			else{
			warning.setText("");
			success.setText("");
			success.setText("Payment Of $" + totalprice + " Is Successful,Rooms Reserved Succefully.");
			totalprice=0;
			labelprice.setText("Total Price:$" + totalprice);
			
			int i =-2;
			for(Room r : roomsincart){
				bookedrooms.add(r);
				i=i+2;
				Label l = new Label(r.showdetails2(i));
				l.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: Black;");
				bookingBox.getChildren().add(l);
				
			}
			roomsincart.clear();
			cartBox.getChildren().clear();
			
			
		
			}
		});
		
		
	
		booking.setOnAction(event ->{
			window.setScene(scene3);
		});
		
		
		
		
		ShowCart.setOnAction(e -> { 
			
           
			window.setScene(scene2);
			//window.setFullScreen(true); 
			});
		
		back.setOnAction(e -> { 
			window.setScene(scene1);
			//window.setFullScreen(true); 
			});
		
		
		back1.setOnAction(e -> { 
			window.setScene(scene1);
			//window.setFullScreen(true); 
			});
		
		
		
		
		
		back.setStyle("-fx-background-color: #0E83A5; -fx-text-fill: white;");
		back.setOnMouseEntered(e -> {
		    back.setStyle("-fx-background-color: #7EABB8; -fx-text-fill: white;");
		    back.setEffect(shadow);
		});
		back.setOnMouseExited(e -> {
		    back.setStyle("-fx-background-color: #0E83A5; -fx-text-fill: white;");
		    back.setEffect(null);
		});

		back1.setStyle("-fx-background-color: #0E83A5; -fx-text-fill: white;");
		back1.setOnMouseEntered(e -> {
		    back1.setStyle("-fx-background-color: #7EABB8; -fx-text-fill: white;");
		    back1.setEffect(shadow);
		});
		back1.setOnMouseExited(e -> {
		    back1.setStyle("-fx-background-color: #0E83A5; -fx-text-fill: white;");
		    back1.setEffect(null);
		});
		
		
		
		
		Image bg= new Image("kk.jpg");
		BackgroundSize bgSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
		BackgroundImage bg1= new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT, bgSize);
		V.setBackground(new Background(bg1));
		W.setBackground(new Background(bg1));
		A.setBackground(new Background(bg1));
		
		
		
		
		
		
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: White;");
		labeltype.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: White;");
		labeldate.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: White;");
		labelprice.setStyle("-fx-font-weight: bold; -fx-font-size: 25px; -fx-text-fill: White;-fx-underline: true;");
		warning.setStyle("-fx-font-size: 20px;; -fx-font-weight: bold;;-fx-text-fill: #D74A48  ");
		success.setStyle("-fx-font-size: 20px;; -fx-font-weight: bold;;-fx-text-fill: #0F4F08  ");
		
		
		
		scene1 = new Scene(V,1000,800);
		window.setScene(scene1);
		
		
		
		window.setTitle("Hotel Reservation System");
		window.show();
		
	}
	
	
	private void gettotalPrice(String roomtype, LocalDate checkin, LocalDate checkout){
		Room selected = rooms.stream().filter(room -> room.getRoomtype().equals(roomtype)).findFirst().orElse(null);
		
		if (selected != null){ 
			Integer numofdays = checkin.until(checkout).getDays();
			 totalprice += (selected.getRoomprice()*numofdays);
		}
	}
	
	

}












