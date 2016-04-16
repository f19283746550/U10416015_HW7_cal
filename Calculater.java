import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
public class Calculater extends Application {
	private static double num;//暫存值
	private static int op;//運算的判斷
	private static int p=0;//小數點使用與否
  @Override
  public void start(Stage primaryStage) {
	  GridPane pane = new GridPane();
	  pane.setStyle("-fx-alignment:CENTER;-fx-layoutX:7.0;-fx-layoutY:54.0;-fx-prefHeight:200.0;-fx-prefWidth:200.0;-fx-scaleX:1.0;-fx-scaleY:1.0;-fx-translateX:0.0;");
	  TextField t = new TextField("0");
	  t.setFont(new Font(20));
	  t.setAlignment(Pos.CENTER_RIGHT);
	  pane.setVgap(10);
	  pane.setHgap(10);
	  Button[] b = new Button[19];
	  for (int i=0; i<=9; i++) {
	      b[i] = new Button(Integer.toString(i));
	  }
	  b[10]=new Button(".");
	  b[11]=new Button("=");
	  b[12]=new Button("+");
	  b[13]=new Button("-");
	  b[14]=new Button("*");
	  b[15]=new Button("/");
	  b[16]=new Button("C");
	  b[17]=new Button("Del");
	  b[18]=new Button("+/-");
	  for(int i=0; i<=18; i++) {
		  b[i].setStyle("-fx-font-size: 30px;");
		  b[i].setMaxWidth(Double.MAX_VALUE);
	  }
	  pane.add(b[17],1, 1,1,1);
	  pane.add(b[7], 2, 1,1,1);
	  pane.add(b[8], 3, 1,1,1);
	  pane.add(b[9], 4, 1,1,1);
	  pane.add(b[12],5, 1,1,1);
	  pane.add(b[18],1, 2,1,1);
	  pane.add(b[4], 2, 2,1,1);
	  pane.add(b[5], 3, 2,1,1);
	  pane.add(b[6], 4, 2,1,1);
	  pane.add(b[13],5, 2,1,1);
	  pane.add(b[16],1, 3,1,1);
	  pane.add(b[1], 2, 3,1,1);
	  pane.add(b[2], 3, 3,1,1);
	  pane.add(b[3], 4, 3,1,1);
	  pane.add(b[14],5, 3,1,1);
	  pane.add(b[11],1, 4,1,1);
	  pane.add(b[0], 2, 4,2,1);
	  
	  pane.add(b[10],4, 4,1,1);
	  pane.add(b[15],5, 4,1,1);
	  pane.add(t,1,0,5,1);
	  
	  for(int i=0;i<=9;i++) {
		  final int x=i;
		  b[i].setOnAction(e -> {
			  if(p!=1&&Long.parseLong(t.getText())==0) {
				  t.setText("");
			  }//為了避免跑出0123之類的情況
			  t.setText(t.getText()+b[x].getText());
		  });
	  }
	  b[10].setOnAction(e -> {
		  t.setText(t.getText()+".");
		  p=1;
	  });
	  for(int i=0;i<=3;i++) {
		  final int x=i;
		  b[i+12].setOnAction(e -> {
			  num=Double.parseDouble(t.getText());
			  op=x;
			  t.setText("0");
			  p=0;
		  });
	  }
	  b[11].setOnAction(e -> {
		  double result=Double.parseDouble(t.getText());
		  switch(op) {
			case 0:
				num+=result;
				break;
			case 1:
				num-=result;
				break;
			case 2:
				num*=result;
				break;
			case 3:
				num/=result;
				break;
			default:
		  }
		  result=0L;
		  op=0;
		  t.setText(Double.toString(num));
		  p=1;//因使用double,所以就算是整數,其結尾也會是.0
	  });
	  b[16].setOnAction(e -> {
		  t.setText("0");
		  p=0;
	  });
	  b[17].setOnAction(e -> {
		  char n[]=new char[t.getLength()];
		  for(int i=0;i<t.getLength()-1;i++)n[i] = t.getText().charAt(i);
		  if(t.getLength()-1==0) {
			  t.setText("0");
		  }//避免扣過頭
		  else {
			  t.setText(String.valueOf(n));
		  }
		  if(t.getText().charAt(t.getLength()-1)==".".charAt(0)) {
			  p=0;
		  }
	  });
	  b[18].setOnAction(e -> {
		  t.setText(Integer.toString(Integer.parseInt(t.getText())*-1));
	  });
	  Scene scene = new Scene(pane, 400, 360);
	  primaryStage.setTitle("ShowCircleCentered");
	  primaryStage.setScene(scene);
	  primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
