package ru.home.avitotelegram.avitoparser;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ParserAuto {

    private static  final Logger log = LoggerFactory.getLogger(ParserAuto.class);


    public List<CarItem> parse(String[] string, boolean isFirst) throws IOException {

        String url = "http://auto.ru/voronezh/cars/"+string[0]+"/"+string[1]+"/all/?price_to="+string[2]+"&top_days=1";
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        try {
            HtmlPage page = client.getPage("http://auto.ru/voronezh/cars/nissan/almera/all/?price_to=500000");
            HtmlElement body = page.getBody();
            DomNode domNode = body.querySelector("span[itemtype=\"http://schema.org/Car\"]");
            domNode.getPage();
            log.info("page: {}", domNode.getPage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isFirst) {
            url = "http://auto.ru/voronezh/cars/"+string[0]+"/"+string[1]+"/all/?price_to="+string[2];
        }
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("89.223.28.195", 3128));
        Elements doc = Jsoup.connect(url)
                .proxy(proxy)
                //.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                .get()
                .select("span[itemtype=\"http://schema.org/Car\"]");
        log.info("doc: {}", doc);
        List<CarItem> autoCollection = doc.parallelStream().map(el -> {
            String bodyType = el.select("meta[itemprop=\"bodyType\"]")
                    .attr("content");
            String brand = el.select("meta[itemprop=\"brand\"]")
                    .attr("content");
            String color = el.select("meta[itemprop=\"color\"]")
                    .attr("content");
            String fuelType = el.select("meta[itemprop=\"color\"]")
                    .attr("content");
            String image = el.select("meta[itemprop=\"image\"]")
                    .attr("content");
            String name = el.select("meta[itemprop=\"name\"]")
                    .attr("content");
            Integer price = Integer.parseInt(el.select("meta[itemprop=\"price\"]")
                    .attr("content"));
            String urlCar = el.select("meta[itemprop=\"url\"]")
                    .attr("content");

            return new CarItem(bodyType, brand, color, fuelType, image, name, price, urlCar);
        }).collect(Collectors.toCollection(ArrayList::new));

        log.info("Collection after parse =  {}", autoCollection);

        return autoCollection;
    }
}
