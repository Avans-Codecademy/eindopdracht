package com.example.eindopdracht.gui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class mainGui extends Application {

    private static Scene mainScene;
    private static Scene studentScene;
    private static Scene courseScene;
    private static Scene entryScene;
    private static Scene certificationScene;

    @Override
    public void start(Stage window) {

        BorderPane mainMenuBorderPane = new BorderPane();

        // Main menu Scene
        VBox mainMenuNavigationMenu = createNavigationMenuBox(window);

        // Maakt de label aan dat aangeeft waar je zit
        Label mainMenuTopLabel = new Label("Main Menu");
        mainMenuTopLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        // maakt het design voor de randen van de TopBox
        BorderStroke topBoxBorderDesign = new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(0, 0, 5, 0));

        // maakt de rand aan met het topBoxBorderDesign
        Border topBoxBorder = new Border(topBoxBorderDesign);

        // Maakt een HBox aan zodat de label gecentreerd kan worden
        HBox mainMenuTopBox = new HBox();
        mainMenuTopBox.setAlignment(Pos.CENTER);
        mainMenuTopBox.setBorder(topBoxBorder);

        mainMenuTopBox.getChildren().add(mainMenuTopLabel);
        mainMenuBorderPane.setTop(mainMenuTopBox);
        mainMenuBorderPane.setLeft(mainMenuNavigationMenu);

        // Maakt scene aan voor het Main Menu
        mainScene = new Scene(mainMenuBorderPane);

        // Zet de main menu scene als aan, aangezien dat het hoofdmenu is
        window.setTitle("Codeacademy");
        window.setScene(mainScene);
        window.setFullScreen(true);
        window.show();

        // Student Scene

        BorderPane studentMenuBorderPane = new BorderPane();

        // Maakt de label aan dat aangeeft waar je zit
        Label studentMenuTopLabel = new Label("Student Menu");
        studentMenuTopLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        // Maakt een HBox aan zodat de label gecentreerd kan worden
        HBox studentMenuTopBox = new HBox();
        studentMenuTopBox.setAlignment(Pos.CENTER);
        studentMenuTopBox.setBorder(topBoxBorder);
        studentMenuTopBox.getChildren().add(studentMenuTopLabel);

        // kopieerd een navigatie menu
        VBox studentNavigationMenuBox = createNavigationMenuBox(window);

        // hier moet de general studentmenu informatie komen
        VBox generalStudentInformationBox = generateGeneralInformation();

        // hier moet het specifieke studentMenu informatie ding komen
        VBox preciseStudentInformatieonBox = generateStudentInformation(
                "Studenten Id Moet Hier meegegeven worden!!!!");

        // maakt de box aan voor de lijst met studenten
        VBox studentListBox = new VBox();
        ListView studentListView = generateStudentList();

        studentMenuBorderPane.setTop(studentMenuTopBox);
        studentMenuBorderPane.setLeft(studentNavigationMenuBox);
        studentMenuBorderPane.setCenter(generalStudentInformationBox);
        studentMenuBorderPane.setRight(studentListBox);

        // maakt de student scene aan
        studentScene = new Scene(studentMenuBorderPane);
    }

    private static void switchScenes(Scene changeTo, Stage currentStage) {
        try {
            currentStage.setScene(changeTo);
            currentStage.setFullScreen(true);
            currentStage.setTitle("Codeacademy");
        } catch (Exception e) {
            System.out.println("Switching Scenes failed");
        }

    }

    private VBox createNavigationMenuBox(Stage window) {
        // Maakt benodige buttons met functionalitijd aan
        Button studentMenuSwitchButton = new Button("Student");
        studentMenuSwitchButton.setMinWidth(300);
        studentMenuSwitchButton.setMinHeight(50);
        studentMenuSwitchButton.setOnAction((event) -> {
            switchScenes(studentScene, window); // scene toevoegen
        });
        Button cursisMenuSwitchButton = new Button("Course");
        cursisMenuSwitchButton.setMinWidth(300);
        cursisMenuSwitchButton.setMinHeight(50);
        cursisMenuSwitchButton.setOnAction((event) -> {
            switchScenes(courseScene, window);
        });
        Button entryMenuSwitchButton = new Button("Entries");
        entryMenuSwitchButton.setMinWidth(300);
        entryMenuSwitchButton.setMinHeight(50);
        entryMenuSwitchButton.setOnAction((event) -> {
            switchScenes(entryScene, window);
        });
        Button certificationMenuSwitchButton = new Button("Certifications");
        certificationMenuSwitchButton.setMinWidth(300);
        certificationMenuSwitchButton.setMinHeight(50);
        certificationMenuSwitchButton.setOnAction((event) -> {
            switchScenes(certificationScene, window);
        });

        // zet de buttons in een lijst
        VBox navigationMenu = new VBox();
        navigationMenu.setPadding(new Insets(5, 10, 5, 10));
        navigationMenu.getChildren().addAll(
                studentMenuSwitchButton,
                cursisMenuSwitchButton,
                entryMenuSwitchButton,
                certificationMenuSwitchButton);

        return navigationMenu;

    }

    // Moet gemaakt worden
    private VBox generateStudentInformation(String studentId) {
        VBox tijdelijkeVBox = new VBox();
        HBox studentActionButton = createStudentActionButtons();

        HBox userInformation = new HBox();
        userInformation.getChildren().add(new Label("Student Information"));
        userInformation.getChildren().add(new Label("StudentID: " + studentId));
        userInformation.getChildren().add(new Label("Student Email: " /* + getStudentEmail OID */ ));
        userInformation.getChildren().add(new Label("Student Birth date: " /* + getStudentBirthdate OID */ ));
        userInformation.getChildren().add(new Label("Student Gender: " /* + getStudentGender OID */ ));
        userInformation.getChildren().add(new Label("Student Address: " /* + getStudentAddress OID */ ));
        userInformation.getChildren().add(new Label("Student City: " /* + getStudentCity OID */ ));
        userInformation.getChildren().add(new Label("Student Country: " /* + getStudentCountry OID */ ));

        tijdelijkeVBox.getChildren().add(userInformation);

        // er moet meer toegevoed worden

        return tijdelijkeVBox;
    }

    // hier moet de standaard informatie gegenereerd worden!
    private VBox generateGeneralInformation() {
        VBox generalUserInformationBox = new VBox();
        HBox studentActionButton = createStudentActionButtons();

        HBox tijdelijkeInvullingA = new HBox();
        tijdelijkeInvullingA.getChildren().add(new Label("Iets qua data A"));
        tijdelijkeInvullingA.getChildren().add(new Label("Iets qua data A"));
        HBox tijdelijkeInvullingB = new HBox();
        tijdelijkeInvullingA.getChildren().add(new Label("Iets qua data B"));
        tijdelijkeInvullingA.getChildren().add(new Label("Iets qua data B"));
        generalUserInformationBox.getChildren().add(studentActionButton);
        generalUserInformationBox.getChildren().add(tijdelijkeInvullingA);
        generalUserInformationBox.getChildren().add(tijdelijkeInvullingB);

        return generalUserInformationBox;
    }

    private HBox createStudentActionButtons() {
        HBox hboxToReturn = new HBox();

        Button addStudentButton = new Button("Add Student");
        addStudentButton
                .setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.GREEN, new CornerRadii(0), Insets.EMPTY)));
        addStudentButton.setMinWidth(250);
        addStudentButton.setMinHeight(50);

        Button deleteStudentButton = new Button("Delete Student");
        deleteStudentButton
                .setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.BROWN, new CornerRadii(0), Insets.EMPTY)));
        deleteStudentButton.setMinWidth(250);
        deleteStudentButton.setMinHeight(50);

        Button generalOverviewButton = new Button("General Overview");
        generalOverviewButton
                .setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.LIGHTBLUE, new CornerRadii(0), Insets.EMPTY)));
        generalOverviewButton.setMinWidth(250);
        generalOverviewButton.setMinHeight(50);

        hboxToReturn.getChildren().add(addStudentButton);
        hboxToReturn.getChildren().add(deleteStudentButton);
        hboxToReturn.getChildren().add(generalOverviewButton);
        hboxToReturn.setPadding(new Insets(5, 0, 5, 0));

        return hboxToReturn;
    }

    private ListView generateStudentList() {
        ListView listViewToReturn = new ListView<>();
        ArrayList<String> studentIdArrayList = new ArrayList<>();
        // hier moet een lijst van alle studenten gemaakt worden
        return listViewToReturn;
    }

}
