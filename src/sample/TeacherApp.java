// CodeReview 06

package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TeacherApp extends Application {
    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;
    private TextField idtxt;
    private TextField nametxt;
    private TextField surnametxt;
    private TextField email;
    private ListView<ClassRooms>classRoomsListView;
    private ObservableList<ClassRooms>dataClass;
    private TeacherDataAccess dbaccess;
    private ClassesDataAccess dbaccess2;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        try {
            dbaccess = new TeacherDataAccess();
            dbaccess2 = new ClassesDataAccess();
        } catch (Exception e) {
            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
            dbaccess.closeDb();
            dbaccess2.closeDb();
        } catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle("School Statistics of Teachers");

        // layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // list view
        listView = new ListView<>();
        listView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        data = getDbData();
        listView.setItems(data);
        grid.add(listView, 1, 1);

        // rooms
        classRoomsListView = new ListView<>();
        classRoomsListView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        dataClass = getDbData2();
        classRoomsListView.setItems(dataClass);

        VBox clvbox = new VBox();
        clvbox.getChildren().addAll(classRoomsListView);
        VBox teacherclasses = new VBox();
        teacherclasses.setSpacing(10);
        teacherclasses.getChildren().addAll(clvbox);
        grid.add(teacherclasses,3,1);

        Label headlbl = new Label("<-- Staff | selected teacher | teaches -->");
        Label idlbl = new Label("ID");
        idtxt = new TextField();
        idtxt.setMinHeight(25);
        idtxt.setPrefColumnCount(20);
        Label namelbl = new Label("Name");
        nametxt = new TextField();
        nametxt.setMinHeight(25);
        nametxt.setPrefColumnCount(20);
        Label surnamelbl = new Label("Surname");
        surnametxt = new TextField();
        surnametxt.setMinHeight(25);
        surnametxt.setPrefColumnCount(20);
        Label emaillbl = new Label("Email");
        email = new TextField();
        email.setMinHeight(25);
        email.setPrefColumnCount(20);
        VBox vlable = new VBox();
        vlable.setSpacing(10);
        vlable.getChildren().addAll(headlbl, idlbl,idtxt,namelbl,nametxt,surnamelbl,surnametxt, emaillbl,email);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(vlable);

        grid.add(vbox, 2, 1);

        Scene scene = new Scene(grid, 800, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        listView.getSelectionModel().selectFirst();
        classRoomsListView.getSelectionModel().selectFirst();

    }
    private class ListSelectChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {
            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {
                return;
            }
            Teacher teacher = data.get(new_val.intValue());

            idtxt.setText(Integer.toString(teacher.getTeacherId()));
            nametxt.setText(teacher.getTeacherName());
            surnametxt.setText(teacher.getTeacherSurname());
            email.setText(teacher.getTeacherEmail());
            classRoomsListView.setItems(dataClass);
        }
    }

    private ObservableList<Teacher> getDbData() {
        List<Teacher> list = null;

        try {
            list = dbaccess.getAllRows();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Teacher> dbData = FXCollections.observableList(list);
        return dbData;
    }

    private ObservableList<ClassRooms> getDbData2() {
        List<ClassRooms> listClass = null;

        try {
            listClass = dbaccess2.getAllClass();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<ClassRooms> dbData2 = FXCollections.observableList(listClass);
        return dbData2;
    }

    private void displayException (Exception e){
        System.out.println("Hoppala...something wrong?! X-D");
        e.printStackTrace();
        System.exit(0);
    }
}


