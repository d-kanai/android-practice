package com.example.android_practice.test;

import io.cucumber.android.runner.CucumberAndroidJUnitRunner;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(glue = "com.example.android_practice.steps", tags = {"~@wip"}, features = "features")
public class CucumberTest extends CucumberAndroidJUnitRunner {
}
