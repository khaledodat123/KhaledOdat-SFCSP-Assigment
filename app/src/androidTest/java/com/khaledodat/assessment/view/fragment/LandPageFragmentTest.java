package com.khaledodat.assessment.view.fragment;

import org.junit.Test;

import static org.junit.Assert.*;

public class LandPageFragmentTest {

    @Test
    public void startSearching() {
        String keyWord = "1TestKeyword";
        assertFalse("Please dont search for article starts with digit", Character.isDigit(keyWord.charAt(0)));
    }
}