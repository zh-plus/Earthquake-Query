package Controller;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class LayoutController implements Initializable{
    @FXML
    private Text title;

    @FXML
    private JFXDatePicker fromDate;

    @FXML
    private JFXDatePicker toDate;

    @FXML
    private Slider magniSlider;

    @FXML
    private ComboBox<String> regionComboBox;

    //用于区域这个comboBox的信息
    ObservableList<String> regionComboBoxList = FXCollections.observableArrayList("China");

    @FXML
    private ImageView searchIcon;

    @FXML
    private Button searchBtn;

    @FXML
    private ImageView mapImg;

    @FXML
    private Tab dataTab;

    @FXML
    private Tab mapTab;

    @FXML
    private AnchorPane mapPane;

    @FXML
    private TableView<EarthquakeInTable> dataTable;

    //用于向图表中添加信息(new的信息用于测试样例， 可以删去)
    private ObservableList<EarthquakeInTable> dataTableList = FXCollections.observableArrayList(new EarthquakeInTable("region1", "2017/2/3", (float)111.1, (float)121.1,
            8, (float)7.2));

    @FXML
    private TableColumn<EarthquakeInTable, String> regionCol;

    @FXML
    private TableColumn<EarthquakeInTable, String> dateCol;

    @FXML
    private TableColumn<EarthquakeInTable, Float> latitudeCol;

    @FXML
    private TableColumn<EarthquakeInTable, Float> longitudeCol;

    @FXML
    private TableColumn<EarthquakeInTable, Integer> depthCol;

    @FXML
    private TableColumn<EarthquakeInTable, Float> magnitudeCol;

    @FXML
    private BarChart<String, Integer> magniChar;

    //magniChar的x轴不需要变动，所以直接在initChar()中完成对x轴的初始化
    @FXML
    private CategoryAxis magniX;

    @FXML
    private BarChart<String, Integer> dateChar;

    //dateChar的x轴需要变动
    @FXML
    private CategoryAxis dateX;

    //用于读取最大、最小值后更新x轴信息
    private ObservableList<String> XAxisNameList = FXCollections.observableArrayList();

    //用于所有的数据需要(ppj的servive里会返回List对象,可以转换为ObservableList)
    private List result;

    @FXML
    void handleSubmitButtonAction(ActionEvent event)
    {
        loadDataInTable();
        loadDataInMagniChar();
        loadDataInDateChar();
        loadDataInMap();
    }

    @FXML
    void handleRefreshButtonAction(ActionEvent event)
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initControllers();
        initCol();
        initChar();
        loadDataInTable();   //在控件和col被初始化之后，可以直接通过控件信息向dataInTable添加地震信息
        loadDataInMap();
        loadDataInMagniChar();
        loadDataInDateChar();
        System.out.println("this");
    }

    public void getResult()
    {
        //TODO 得到result
    }

    private void initControllers()
    {
        fromDate.setValue(LocalDate.of(2017, 10, 15));
        toDate.setValue(LocalDate.of(2017, 10, 15));
        magniSlider.setValue(1);

        loadDataInRegionComboBox();
        regionComboBox.setValue("Choose region");
    }

    private void initCol()
    {
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        latitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        depthCol.setCellValueFactory(new PropertyValueFactory<>("depth"));
        magnitudeCol.setCellValueFactory(new PropertyValueFactory<>("magnitude"));
    }

    private void initChar()
    {
        ObservableList<String> magniXList = FXCollections.observableArrayList();
        magniXList.add("Under 3.0");
        magniXList.add("3.0 to 4.0");
        magniXList.add("4.0 to 5.0");
        magniXList.add("5.0 to 6.0");
        magniXList.add("6.0 and over");
        magniX.setCategories(magniXList);
        loadDataInMagniChar();

        loadDataInDateChar();
    }

    private void loadDataInRegionComboBox()
    {
        //TODO 访问数据库的数据，再更改regionConboBoxList的值就好

        regionComboBox.getItems().setAll(regionComboBoxList);
    }

    //用于载入从数据库得到的数据到表格中(即用上面的observableArrayList)
    private void loadDataInTable()
    {
        //TODO
        //TODO dataTableList.add(new EarthquakeInTable(......))  用for循环一个个添加
        dataTable.getItems().setAll(dataTableList);
    }

    //因为magniChar的x轴已经被初始化且不再改动，因此不用再改变x轴，直接输入数据就行
    private void loadDataInMagniChar()
    {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        //TODO series.getData().add(new XYChart.Data<>(dateNames.get(i), dateCounter[i]));  for循环

        magniChar.getData().setAll(series);
    }

    //需要随着输入时间段的变化去改变x轴
    private void loadDataInDateChar()
    {
        ObservableList<String> dateXList = FXCollections.observableArrayList();
        LocalDate fromD = fromDate.getValue(), toD = toDate.getValue();
        //TODO 得到时间信息，然后早到晚排列
        dateX.setCategories(dateXList);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        //TODO series.getData().add(new XYChart.Data<>(dateNames.get(i), dateCounter[i]));  for循环

        magniChar.getData().setAll(series);
    }

    private void loadDataInMap()
    {
        //TODO
        Random random = new Random();
        addMarkToMap(random.nextInt(10) * 10,random.nextInt(15) * 10);
    }

    private void addMarkToMap(float x, float y)
    {
        Circle c = new Circle();
        c.setRadius(2);
        c.setCenterX(x);
        c.setCenterY(y);
        c.setStroke(Color.rgb(255, 192, 203));
        c.setFill(Color.rgb(208, 16, 76));

        mapPane.getChildren().add(c);
    }

    //用于在表格中的信息添加
    public class EarthquakeInTable
    {
        private SimpleStringProperty    region;
        private SimpleStringProperty    date;
        private SimpleFloatProperty     latitude;
        private SimpleFloatProperty     longitude;
        private SimpleIntegerProperty   depth;
        private SimpleFloatProperty     magnitude;

        public EarthquakeInTable(String region, String date, Float latitude,
                                 Float longitude, int depth, float magnitude)
        {
            this.region = new SimpleStringProperty(region);
            this.date = new SimpleStringProperty(date);
            this.latitude = new SimpleFloatProperty(latitude);
            this.longitude= new SimpleFloatProperty(longitude);
            this.depth = new SimpleIntegerProperty(depth);
            this.magnitude = new SimpleFloatProperty(magnitude);
        }

        public String getRegion() {
            return region.get();
        }

        public SimpleStringProperty regionProperty() {
            return region;
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public float getLatitude() {
            return latitude.get();
        }

        public SimpleFloatProperty latitudeProperty() {
            return latitude;
        }

        public float getLongitude() {
            return longitude.get();
        }

        public SimpleFloatProperty longitudeProperty() {
            return longitude;
        }

        public int getDepth() {
            return depth.get();
        }

        public SimpleIntegerProperty depthProperty() {
            return depth;
        }

        public float getMagnitude() {
            return magnitude.get();
        }

        public SimpleFloatProperty magnitudeProperty() {
            return magnitude;
        }
    }

}
