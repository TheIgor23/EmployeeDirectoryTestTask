package ru.jezemoin.emploedirectory.util.names;


import lombok.Builder;
import ru.jezemoin.emploedirectory.model.entity.SexEnum;

import java.util.List;
import java.util.Random;

@Builder
public class NameGenerator {

    @Builder.Default
    private String lastNamePrefix = "";

    @Builder.Default
    private String firstNamePrefix = "";

    @Builder.Default
    private String middleNamePrefix = "";

    @Builder.Default
    private SexEnum sex = SexEnum.Female;


    private final Random random = new Random();

    public String generateFullName() {
        String randomFirstName = getRandomName(NameType.FIRST_NAME, firstNamePrefix);
        String randomLastName = getRandomName(NameType.LAST_NAME, lastNamePrefix);
        String randomMiddleName = getRandomName(NameType.MIDDLE_NAME, middleNamePrefix);

        return String.format("%s %s %s", randomLastName, randomFirstName, randomMiddleName);
    }

    public String getRandomName(NameType nameType, String prefix) {
        List<String> names = NamesReader.getNames(sex, nameType)
                .stream()
                .filter(name -> name.startsWith(prefix))
                .toList();

        return names.get(random.nextInt(names.size()));
    }
}
