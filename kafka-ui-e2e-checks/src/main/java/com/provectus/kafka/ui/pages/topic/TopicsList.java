package com.provectus.kafka.ui.pages.topic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.provectus.kafka.ui.extensions.WaitUtils;
import com.provectus.kafka.ui.helpers.TestConfiguration;
import com.provectus.kafka.ui.utils.BrowserUtils;
import io.qameta.allure.Step;
import lombok.experimental.ExtensionMethod;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.provectus.kafka.ui.extensions.WebUtils.isVisible;

@ExtensionMethod(WaitUtils.class)
public class TopicsList {

    private static final String path = "/ui/clusters/%s/topics";

    @Step
    public TopicsList goTo(String cluster) {
        Selenide.open(TestConfiguration.BASE_WEB_URL + String.format(path, cluster));
        return this;
    }

    @Step
    public TopicsList waitUntilScreenReady() {
        $(By.xpath("//*[contains(text(),'Loading')]")).shouldBe(Condition.disappear);
        $(By.xpath("//h1[text()='All Topics']")).shouldBe(Condition.visible, Duration.ofSeconds (60));
        return this;
    }

    @Step
    public TopicCreateEditSettingsView pressCreateNewTopic() {
        BrowserUtils.javaExecutorClick($x("//button[normalize-space(text()) ='Add a Topic']"));
        return new TopicCreateEditSettingsView();
    }

    @Step
    public boolean isTopicVisible(String topicName) {
        $(By.xpath("//table")).shouldBe(Condition.visible);
        return isVisible($x("//tbody//td//a[text()='" + topicName + "']"));
    }

    @Step
    public TopicView openTopic(String topicName) {
        $(By.linkText(topicName)).click();
        return new TopicView();
    }
}
