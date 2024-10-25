package ru.jezemoin.emploedirectory.util.names;

import org.yaml.snakeyaml.Yaml;
import ru.jezemoin.emploedirectory.model.entity.SexEnum;

import java.io.InputStream;
import java.util.*;

public class NamesReader {

    private static final Map<String, Map<String, List<String>>> nameData;


    static {
        InputStream inputStream = NamesReader.class
                .getClassLoader()
                .getResourceAsStream("name.yml");
        nameData = new Yaml().load(inputStream);

    }

    public static List<String> getNames(SexEnum sex, NameType nameType) {
        String genderKey = sex.toString().toLowerCase();
        return nameData.get(genderKey).get(nameType.toString().toLowerCase());
    }

}
