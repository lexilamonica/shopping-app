/*
 * Cucumber Step up
 * Author: Lexi LaMonica
 * All rights reserved
 */
package edu.depaul.shoppingapp;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumberGherkin.feature")
public class cucumberSetup {
}
