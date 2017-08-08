/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java7;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test to learn the usage of Try with resource featurw {@link https
 * ://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html}
 *
 * Basically 2 test that try to run some computation with a closeable resource. One finishes ok the other no.
 * The idea is to see what happens with the {@link Closeable#close()} method.
 *
 * @author Marcelo
 * @since Jul 28, 2015
 */
public final class TryWithReourcesTest {

    private Closeable closeableMock;

    @Before
    public void before() {
        closeableMock = mock(Closeable.class);
    }

    @Test
    public void close_should_be_called_on_normal_execution() throws IOException {
        try (Closeable closeable = closeableMock) {
            assertTrue(true); // whatever happens without error
        }

        verify_close_calls();
    }

    @Test
    public void close_should_be_called_on_abnormal_execution() throws IOException {
        try (Closeable closeable = closeableMock) {
            throw new RuntimeException("HEY YOU! This is an abnormal execution, but it doesn't matter 'cause is part of the test :)"); // error
        } catch (final Exception e) {
            e.printStackTrace();
        }
        verify_close_calls();
    }

    /** verifies the amount of calls to close method on the {@link #closeableMock} */
    private void verify_close_calls() throws IOException {
        /**
         * AutoCloseable objects are used in try(){} blocks, where close is actually called automatically and only once;
         * at the same time close() from Closeable interface method you always call manually and you can call it twice
         * accidentally or to make your code easy to read. In addition - Closeable extends AutoCloseable and it couldn't
         * weakens contract of close() method from AutoCloseable - it can only add requirements.
         * So, abstract situation when AutoCloseable required close() to be idempotent and extended interface canceled
         * this requirement would be just a bad design.
         * It's just a contract that programmer should take into account.
         * Like the contract between equals() and hashCode(). You can implement it in an inconsistent way and nobody will
         * tell you. You will just very likely have problems at runtime.
         */
        verify(closeableMock, times(1)).close();
    }

}
