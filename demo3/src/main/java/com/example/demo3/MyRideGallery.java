package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyRideGallery extends Application {

    private String[] imageUrls;
    private int currentIndex = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //setting title for the stage
        primaryStage.setTitle("My Rides Gallery");

        //Inserting and Arranging grid pane for thumbnails
        GridPane gridPane = new GridPane();
        //adjusting spaces between the thumbnails
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        //setting white background colour for the gridpane
        gridPane.setStyle("-fx-background-color: yellow");
        gridPane.setPadding(new Insets(5));

        //inserting thumbnails
        imageUrls = new String[]{
                "/images/aq81.jpg",
                "/images/aq82.jpg",
                "/images/aq83.jpg",
                "/images/idm1.jpg",
                "/images/idm2.jpg",
                "/images/idm3.jpg",
                "/images/lrd1.jpg",
                "/images/lrd2.jpg",
                "/images/lrd3.jpg",
                "/images/rrs1.jpg",
                "/images/rrs2.jpg",
                "/images/rrs3.jpg",

        };

        int col = 0;
        int row = 0;


        for (String imageUrl : imageUrls) {
            Image image = new Image(getClass().getResourceAsStream(imageUrl));
            ImageView thumbnailImageView = new ImageView(image);
            //setting thumbnails dimensions
            thumbnailImageView.setFitWidth(247);
            thumbnailImageView.setFitHeight(200);


            //styles for hover effect and stylish grid
            thumbnailImageView.setOnMouseEntered(event -> thumbnailImageView.setStyle(
                    "-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.8), 10, 0, 0, 0);"
                            + "-fx-transform: scale(1.4);"
                            + "-fx-transition: 0.5s ease-in-out;"
            ));


            thumbnailImageView.getStyleClass().add("thumbnail-box");

            //Include styles.css
            thumbnailImageView.getStyleClass().addAll("thumbnail", "thumbnail-box");


            // click event handler to show full-size image
            thumbnailImageView.setOnMouseClicked(event -> showFullSizeImage(image));

            gridPane.add(thumbnailImageView, col, row);

            // Adjust column and row indices
            col++;
            //to diplay each car make on its row, adding 3 angles of the car
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        //creating a scrollpane
        ScrollPane scroll=new ScrollPane();
        scroll.setPrefSize(595,200);
        
        Scene scene = new Scene(gridPane, 765, 765); // Setting  the size of the scene
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);

        //to display the application
        primaryStage.show();
    }

    //navigation for the maximized images using if statement
    public void displayPrevImage(){
        if(currentIndex > 0){
            currentIndex--;
            showFullSizeImage(new Image(imageUrls[currentIndex]));}
    }
    public void displayNextImage(){
        if(currentIndex < imageUrls.length - 1){
            currentIndex++;
            showFullSizeImage(new Image(imageUrls[currentIndex]));

        }


    }
    // Function to show full-size image in a new window
    private void showFullSizeImage(Image image) {
        Stage fullSizeStage = new Stage();
        ImageView fullSizeImageView = new ImageView(image);
        fullSizeImageView.setFitWidth(550); // Set the width of the full-size image
        fullSizeImageView.setFitHeight(550); // Set the height of the full-size image

        //Create navigation controls using Horizontal box
        HBox navigationBox = new HBox(400);
        navigationBox.setPadding(new Insets(10));

        //Add navigation buttons
        Button previousButton = new Button("< prev");
        previousButton.setOnAction(event -> displayPrevImage());
        Button nextButton = new Button("next >");
        nextButton.setOnAction(event -> displayNextImage());
        previousButton.getStyleClass().add("nav-button");
        nextButton.getStyleClass().add("nav-button");

        //Positioning the buttons within the HBox
        navigationBox.getChildren().addAll(previousButton, nextButton);

        StackPane stackPane = new StackPane(fullSizeImageView,navigationBox);
        stackPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Scene fullSizeScene = new Scene(stackPane);

        fullSizeStage.setScene(fullSizeScene);
        fullSizeStage.setTitle("Full Size Image");
        fullSizeStage.show();
    }
}