package com.carta.llc.test.inttest;

import org.junit.experimental.categories.Category;

import com.carta.llc.test.IntegrationTest;
import com.carta.llc.test.RemoteTest;

/**
 * This integration serves as an integration smoke test that can run against any
 * remote instance to quickly verify the basic functionality
 *
 * skipped by default. only for manual testing
 */
@Category({ RemoteTest.class, IntegrationTest.class })
public class SmokeTest {

}
