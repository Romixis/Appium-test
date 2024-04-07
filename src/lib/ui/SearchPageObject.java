package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageObject extends  MainPageObject {
    private static final String SearchInit = "//*[contains(@text, 'Поиск по Википедии')]";
    private static final String SearchInput = "//*[contains(@text, 'Поиск')]";
    private static final String SearchResult = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
    private static  final String SaveBtn = "//android.widget.TextView[@content-desc=\"Сохранить\"]";
    private static  final String BackBtn = "//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]";
    private static  final String SaveList = "//android.widget.FrameLayout[@content-desc=\"Сохранено\"]";
    private  static final String SaveListSaved = "//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/item_title_container\"]";
    private static final String Delete = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Удалить все из офлайн-режима\"]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        element.sendKeys(value);
    }

    private void waitForElementAndLongPress(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by ,errorMessage, timeoutInSeconds);
        TouchAction action = new TouchAction(driver);
        action.longPress(element).release().perform();
    }

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SearchInit), "Невозможно нажать на поле ввода", 15);
        this.waitForElementPresent(By.xpath(SearchInit), "Невозможно найти поле ввода", 15);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(By.xpath(SearchInput), searchLine,"Невозможно найти поле ввода", 15);
    }

    private static String getResultSearchElement(String substring){
        return SearchResult.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResultAndClick(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Невозможно найти: " + substring, 15);
    }

    public void saveToList(){
        this.waitForElementAndClick(By.xpath(SaveBtn), "Невозможно добавить в избранное", 15);
    }

    public void goToBack(){
        this.waitForElementAndClick(By.xpath(BackBtn), "Невозможно уйти назад", 15);
    }

    public void goToSaveList(){
        this.waitForElementAndClick(By.xpath(SaveList), "Невозможно найти сохраненное", 15);
    }

    public void goToSaveListInSavedAndGetOptions(){
        this.waitForElementAndLongPress(By.xpath(SaveListSaved), "Невозможно отрыть сохраненное в сохраненном", 15);
    }

    public void deleteSavedTitle() {
        this.waitForElementAndClick(By.xpath(Delete), "Невозможно удалить статью", 15);
    }
}