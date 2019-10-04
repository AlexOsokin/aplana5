import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTest extends DriverSettings {
    @Test
    public void sber() {
        findAndClick("//*[@aria-label='Меню Страхование']");
        findAndClick("//*[contains(@class,'sub-item') and ./a[text()='Страхование путешественников']]");
        findAndPrintText("//h1[text()='Страхование путешественников']");
        forvardWindowForClick("//p/a[@target='_blank']/img");
        findAndClick("//div[text() = 'Минимальная']/parent::div");
        findAndClick("//span[text()='Оформить']");
        setForm();
    }

    private void setForm(){
        ArrayList<String> names = new ArrayList<String>(Arrays.asList("insured0_surname", "insured0_name", "insured0_birthDate", "surname", "name", "middlename", "birthDate"));
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("Sidorov", "Ivan", "10.12.1993", "Смирнов", "Алексей", "Семенович", "20.10.1998"));
        setText(names, values);
        findAndClick("//input[@name='male']/parent::span");

        names = new ArrayList<String>(Arrays.asList("passport_series", "passport_number", "issueDate"));
        values = new ArrayList<String>(Arrays.asList("3617", "478589", "28.11.2018"));
        setTextPassport(names, values);
        sendElementKeys("//textarea[@name='issuePlace']", "УФМС по Леннинскому району города Кирова");

        findAndClick("//span[text()='Продолжить']");

        findAndPrintText("//div[text()='Заполнены не все обязательные поля' and @class='']");
    }
}
