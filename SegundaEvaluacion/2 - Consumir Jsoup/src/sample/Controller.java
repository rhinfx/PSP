package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Controller {

    @FXML
    private Label lbl_fb;

    @FXML
    private ImageView iv_1, iv_2, iv_3;


    public static boolean imgCheck(String http){
        String png = ".png";
        String jpg = ".jpg";
        String jpeg = "jpeg"; //
        int length = http.length();

        if (http.contains(png) || http.contains("gfycat") || http.contains(jpg)|| http.contains(jpeg)){
            return true;
        }
        else{
            return false;
        }



    }

    @FXML
    void searchTitle(ActionEvent event) throws IOException {

        Document doc = Jsoup.connect("https://old.reddit.com/r/aww/").get();

        String title = doc.title();
        lbl_fb.setText(title);

        Elements links = doc.select("a[href]");

        int n = 0;
        for (Element link: links) {
            //get value from href attribute
            String checkLink = link.attr("href");

            if (imgCheck(checkLink)) {
                System.out.println("link : " + link.attr("href"));
                
                switch (n) {
                    case 0: Image img = new Image(link.attr("href"));
                            iv_1.setImage(img);
                        break;
                    case 2: img = new Image(link.attr("href"));
                            iv_2.setImage(img);
                        break;
                    case 4: img = new Image(link.attr("href"));
                            iv_3.setImage(img);
                        break;
                }
                n++;
            }

        }


    }



}
