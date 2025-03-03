package org.example.APITesting_TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Example_groups {
    @Test(groups = {"sanity", "qa", "peprod"})
    public void sanityRun() {
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }

    @Test(groups = {"qa", "prepod", "reg"})
    public void RegRun() {
        System.out.println("Reg");
        Assert.assertTrue(false);
    }

    @Test(groups = {"dev", "stage"})
    public void SmokeRun() {
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }

    @Test(groups = {"sanity", "qa", "prepod"})
    public void sanityRun1() {
        System.out.println("Smoke");
        Assert.assertTrue(true);
    }

    @Test(groups = {"qa", "prepod", "reg"})
    public void RegRun2() {
        System.out.println("Reg");
        Assert.assertTrue(false);
    }

    @Test(groups = {"dev", "stage"})
    public void SmokeRun1() {
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }
}