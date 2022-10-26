package basicTest;

import org.fluentlenium.core.action.KeyboardActions;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExercice extends FluentWebTest {

    @Test(description = "Open the application and make sure a list of movie tiles is displayed.")
    public void Question1() {
        assertThat(find(By.cssSelector(".movie-card")).size()).as("Movie tiles list not present").isGreaterThan(0);
    }

    @Test(description = "Open the movie The Shawshank Redemption and make sure the release date is\n" +
            "correctly displayed." +
            "Retrieve the url of the image and open it in another tab then close the tab.")
    public void Question2And3() {
        List<FluentWebElement> tiles = find(By.cssSelector(".movie-card"));
        for (FluentWebElement tile : tiles){
            String title = tile.find(By.tagName("h2")).first().text();
            if (title.equals("The Shawshank Redemption")){
                tile.find(By.tagName("button")).first().click();
                await().explicitlyFor(2, TimeUnit.SECONDS);
                boolean isReleasedDateDisplayed = false;
                List<FluentWebElement> labels = find(By.tagName("label"));
                for (FluentWebElement label : labels){
                    if (label.text().equals("Released on")){
                        isReleasedDateDisplayed = true;
                        break;
                    }
                    assertThat(isReleasedDateDisplayed).as("Movie released date not displayed").isTrue();
                }

                String url = find(By.cssSelector(".movie-detail-image")).first().attribute("style").split("\"")[1];
                System.out.println(url);
                ((JavascriptExecutor)getDriver()).executeScript("window.open()");
                ArrayList<String> tabs = new ArrayList<> (getDriver().getWindowHandles());
                getDriver().switchTo().window(tabs.get(1));
                getDriver().get(url);
                await().explicitlyFor(2, TimeUnit.SECONDS);
                getDriver().close();
                await().explicitlyFor(2, TimeUnit.SECONDS);
                break;
            }
        }
    }

    @Test(description = "Search for Star Trek and make sure that the movie Star Trek: First Contact is displayed in\n" +
            "the search results and the movie The Shawshank Redemption is no longer visible.")
    public void Question4() {
        FluentWebElement search = find(By.tagName("input")).first();
        search.write("Star Trek");
        KeyboardActions keyboardActions = new KeyboardActions(getDriver());
        keyboardActions.sendKeys(Keys.ENTER);
        await().explicitlyFor(2, TimeUnit.SECONDS);
        List<FluentWebElement> tiles = find(By.cssSelector(".movie-card"));
        boolean isFirstContactDisplayed = false;
        boolean isShawshankDisplayed = false;
        for (FluentWebElement tile : tiles) {
            String title = tile.find(By.tagName("h2")).first().text();
            if (title.equals("Star Trek: First Contact")) {
                isFirstContactDisplayed = true;
            } else {
                if (title.equals("The Shawshank Redemption")) {
                    isShawshankDisplayed = true;
                }
            }
        }
        assertThat(isFirstContactDisplayed).as("First Contact is not displayed").isTrue();
        assertThat(isShawshankDisplayed).as("The shawshank redemption is still displayed").isFalse();
    }

    @Test(description = "Search for A New and verify that all the titles displayed contain the search phrase.")
    public void Question5() {
        FluentWebElement search = find(By.tagName("input")).first();
        search.write("A New");
        KeyboardActions keyboardActions = new KeyboardActions(getDriver());
        keyboardActions.sendKeys(Keys.ENTER);
        await().explicitlyFor(2, TimeUnit.SECONDS);
        List<FluentWebElement> tiles = find(By.cssSelector(".movie-card"));
        boolean titleContainsSearch = true;
        for (FluentWebElement tile : tiles) {
            String title = tile.find(By.tagName("h2")).first().text();
            System.out.println("title is " + title);
            if (!title.toLowerCase().contains("a") && !title.toLowerCase().contains("new")) {
                titleContainsSearch = false;
            }
        }
        assertThat(titleContainsSearch).as("First Contact is not displayed").isTrue();
    }

    @Test(description = "Take any movie you like and make sure the Released on, popularity, vote average\n" +
            "and vote count fields have the expected values.")
    public void Question6() {
        List<FluentWebElement> tiles = find(By.cssSelector(".movie-card"));
        tiles.get(0).find(By.tagName("button")).first().click();
        await().explicitlyFor(2, TimeUnit.SECONDS);
        List<FluentWebElement> inputs = find(By.tagName("input"));
        assertThat(inputs.get(1).value()).as("Movie's releasing date is incorrect").isEqualTo("1972-03-14");
        assertThat(inputs.get(2).value()).as("Movie's popularity is incorrect").isEqualTo("92.185");
        assertThat(inputs.get(3).value()).as("Movie's rating is incorrect").isEqualTo("8.7");
        assertThat(inputs.get(4).value()).as("Movie's average is incorrect").isEqualTo("16797");
    }

    @Test(description = "Did you encounter any bugs in the application? If so add a test that shows that bug.")
    public void Question7() {
        FluentWebElement search = find(By.tagName("input")).first();
        search.write(" ");
        KeyboardActions keyboardActions = new KeyboardActions(getDriver());
        keyboardActions.sendKeys(Keys.ENTER);
        await().explicitlyFor(2, TimeUnit.SECONDS);
        find(By.tagName("input")).first().write("Avengers");

        // Here we've got a bug. When we enter an empty string or string with only spaces in the search area, we've got a blank screen.
        // The search bar also disappears, which is not ax expected behavior
    }

    @Test(description = "Please describe what improvements you would ask a developer to make to the application to make" +
            "it better suited for automated testing.")
    public void Question8() {
        // In order to facilitate the writing of automated tests, developers should add additional data in the DOM of web pages,
        // allowing to uniquely identify each component, notably through an id attribute, a name attribute or a label attribute.
        // Moreover, these labels should be nominative with respect to the web element they designate,
        // and not alphanumeric codes as is currently the case (jss39 jss48 etc.).
    }

    @Override
    protected String getDefaultUrl() {
        return "https://top-movies-qhyuvdwmzt.now.sh/";
    }

}
