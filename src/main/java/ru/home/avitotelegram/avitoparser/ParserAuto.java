package ru.home.avitotelegram.avitoparser;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ParserAuto implements Parsable {

    private static  final Logger log = LoggerFactory.getLogger(ParserAuto.class);

    @Override
    public List<CarItem> parse(String... string) throws IOException {

        String url = "https://auto.ru/voronezh/cars/"+string[0]+"/"+string[1]+"/used/?price_to="+string[2]+"";

        Elements doc = Jsoup.connect(url).get().select("span[itemtype=\"http://schema.org/Car\"]");

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
