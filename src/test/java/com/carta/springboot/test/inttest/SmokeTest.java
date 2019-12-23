package com.carta.springboot.test.inttest;

import org.junit.experimental.categories.Category;

import com.carta.springboot.test.IntegrationTest;
import com.carta.springboot.test.RemoteTest;

/**
 * This integration serves as an integration smoke test that can run against any
 * remote instance to quickly verify the basic functionality
 *
 * skipped by default. only for manual testing
 */
@Category({ RemoteTest.class, IntegrationTest.class })
public class SmokeTest {

}
