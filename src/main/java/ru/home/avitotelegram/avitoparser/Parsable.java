package ru.home.avitotelegram.avitoparser;

import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.util.List;

public interface Parsable {
    List<CarItem> parse(String... string) throws IOException;
}
