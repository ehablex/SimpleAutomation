package com.testautomation.TestRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.google.gson.JsonParser;

import cucumber.api.CucumberOptions;

import cucumber.api.testng.CucumberFeatureWrapper;

import cucumber.api.testng.TestNGCucumberRunner;

import cucumber.api.testng.*;



@CucumberOptions (

        features = "./features/"

        ,glue = {"com.testautomation.StepDef"}

        ,tags = {"@EhabTesting"}

        ,monochrome = true)

public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    

    @BeforeClass(alwaysRun = true)

    public void setUpClass() throws Exception {    	

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    }



    @Test(dataProvider = "features")    

    public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {

    	//testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

    	testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());

    }

    

    @DataProvider//(parallel=true)

    public Object[][] features() {

       // return testNGCucumberRunner.provideFeatures();    	

    	 return testNGCucumberRunner.provideScenarios();

    }

    @AfterClass(alwaysRun = true)

    public void tearDownClass() throws Exception {    	

        testNGCucumberRunner.finish();        

    }

}
