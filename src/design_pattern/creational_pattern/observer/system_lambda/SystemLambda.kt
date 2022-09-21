package design_pattern.creational_pattern.observer.system_lambda

import java.io.*
import java.lang.reflect.Field
import java.net.InetAddress
import java.security.Permission
import java.util.*
import java.util.concurrent.Callable
import java.lang.Class.forName
import java.lang.System.*
import java.nio.charset.Charset.defaultCharset
import java.util.Arrays.stream
import java.util.Collections.singletonMap
import java.util.stream.Collectors.joining

// https://github.com/stefanbirkner/system-lambda

/**
 * `SystemLambda` is a collection of functions for testing code
 * that uses `java.lang.System`.
 *
 * <h2>System.exit</h2>
 *
 * Command-line applications terminate by calling `System.exit` with
 * some status code. If you test such an application then the JVM that runs the
 * test exits when the application under test calls `System.exit`. You can
 * avoid this with the method
 * [catchSystemExit][.catchSystemExit] which also returns the
 * status code of the `System.exit` call.
 *
 * <pre>
 * &#064;Test
 * void application_exits_with_status_42(
 * ) throws Exception {
 * int statusCode = catchSystemExit(()-&gt; {
 * System.exit(42);
 * });
 * assertEquals(42, statusCode);
 * }
</pre> *
 *
 * The method `catchSystemExit` throws an `AssertionError` if the
 * code under test does not call `System.exit`. Therefore your test fails
 * with the failure message "System.exit has not been called."
 *
 * <h2>Environment Variables</h2>
 *
 *
 * The method
 * [withEnvironmentVariable][.withEnvironmentVariable]
 * allows you to set environment variables within your test code that are
 * removed after your code under test is executed.
 * <pre>
 * &#064;Test
 * void execute_code_with_environment_variables(
 * ) throws Exception {
 * List&lt;String&gt; values = withEnvironmentVariable("first", "first value")
 * .and("second", "second value")
 * .execute(()-&gt; asList(
 * System.getenv("first"),
 * System.getenv("second")
 * ));
 * assertEquals(
 * asList("first value", "second value"),
 * values
 * );
 * }</pre>
 *
 * <h2>System Properties</h2>
 *
 *
 * The function
 * [restoreSystemProperties][.restoreSystemProperties]
 * guarantees that after executing the test code each System property has the
 * same value like before. Therefore you can modify System properties inside of
 * the test code without having an impact on other tests.
 * <pre>
 * &#064;Test
 * void execute_code_that_manipulates_system_properties(
 * ) throws Exception {
 * restoreSystemProperties(()-&gt; {
 * System.setProperty("some.property", "some value");
 * //code under test that reads properties (e.g. "some.property") or
 * //modifies them.
 * });
 * }
</pre> *
 *
 * <h2>System.out and System.err</h2>
 *
 * Command-line applications usually write to the console. If you write such
 * applications you need to test the output of these applications. The methods
 * [tapSystemErr][.tapSystemErr],
 * [tapSystemErrNormalized][.tapSystemErrNormalized],
 * [tapSystemOut][.tapSystemOut],
 * [tapSystemOutNormalized][.tapSystemOutNormalized],
 * [tapSystemErrAndOut][.tapSystemErrAndOut] and
 * [tapSystemErrAndOutNormalized][.tapSystemErrAndOutNormalized]
 * allow you
 * to tap the text that is written to `System.err`/`System.out`. The
 * methods with the suffix `Normalized` normalize line breaks to
 * `\n` so that you can run tests with the same assertions on different
 * operating systems.
 *
 * <pre>
 * &#064;Test
 * void application_writes_text_to_System_err(
 * ) throws Exception {
 * String text = tapSystemErr(()-&gt; {
 * System.err.print("some text");
 * });
 * assertEquals(text, "some text");
 * }
 *
 * &#064;Test
 * void application_writes_mutliple_lines_to_System_err(
 * ) throws Exception {
 * String text = tapSystemErrNormalized(()-&gt; {
 * System.err.println("first line");
 * System.err.println("second line");
 * });
 * assertEquals(text, "first line\nsecond line\n");
 * }
 *
 * &#064;Test
 * void application_writes_text_to_System_out(
 * ) throws Exception {
 * String text = tapSystemOut(()-&gt; {
 * System.out.print("some text");
 * });
 * assertEquals(text, "some text");
 * }
 *
 * &#064;Test
 * void application_writes_mutliple_lines_to_System_out(
 * ) throws Exception {
 * String text = tapSystemOutNormalized(()-&gt; {
 * System.out.println("first line");
 * System.out.println("second line");
 * });
 * assertEquals(text, "first line\nsecond line\n");
 * }
 *
 * &#064;Test
 * void application_writes_text_to_System_err_and_out(
 * ) throws Exception {
 * String text = tapSystemErrAndOut(()-&gt; {
 * System.err.print("text from err");
 * System.out.print("text from out");
 * });
 * assertEquals("text from errtext from out", text);
 * }
 *
 * &#064;Test
 * void application_writes_mutliple_lines_to_System_err_and_out(
 * ) throws Exception {
 * String text = tapSystemErrAndOutNormalized(()-&gt; {
 * System.err.println("text from err");
 * System.out.println("text from out");
 * });
 * assertEquals("text from err\ntext from out\n", text);
 * }</pre>
 *
 *
 * You can assert that nothing is written to
 * `System.err`/`System.out` by wrapping code with the function
 * [ assertNothingWrittenToSystemErr][.assertNothingWrittenToSystemErr]/[ assertNothingWrittenToSystemOut][.assertNothingWrittenToSystemOut]. E.g. the following tests fail:
 * <pre>
 * &#064;Test
 * void fails_because_something_is_written_to_System_err(
 * ) throws Exception {
 * assertNothingWrittenToSystemErr(()-&gt; {
 * System.err.println("some text");
 * });
 * }
 *
 * &#064;Test
 * void fails_because_something_is_written_to_System_out(
 * ) throws Exception {
 * assertNothingWrittenToSystemOut(()-&gt; {
 * System.out.println("some text");
 * });
 * }
</pre> *
 *
 *
 * If the code under test writes text to
 * `System.err`/`System.out` then it is intermixed with the output
 * of your build tool. Therefore you may want to avoid that the code under test
 * writes to `System.err`/`System.out`. You can achieve this with
 * the function [ muteSystemErr][.muteSystemErr]/[muteSystemOut][.muteSystemOut]. E.g. the
 * following tests don't write anything to
 * `System.err`/`System.out`:
 * <pre>
 * &#064;Test
 * void nothing_is_written_to_System_err(
 * ) throws Exception {
 * muteSystemErr(()-&gt; {
 * System.err.println("some text");
 * });
 * }
 *
 * &#064;Test
 * void nothing_is_written_to_System_out(
 * ) throws Exception {
 * muteSystemOut(()-&gt; {
 * System.out.println("some text");
 * });
 * }
</pre> *
 *
 * <h2>System.in</h2>
 *
 *
 * Interactive command-line applications read from `System.in`. If you
 * write such applications you need to provide input to these applications. You
 * can specify the lines that are available from `System.in` with the
 * method [withTextFromSystemIn][.withTextFromSystemIn]
 * <pre>
 * &#064;Test
 * void Scanner_reads_text_from_System_in(
 * ) throws Exception {
 * withTextFromSystemIn("first line", "second line")
 * .execute(()-&gt; {
 * Scanner scanner = new Scanner(System.in);
 * scanner.nextLine();
 * assertEquals("first line", scanner.nextLine());
 * });
 * }
</pre> *
 *
 *
 * For complete test coverage you may also want to simulate `System.in`
 * throwing exceptions when the application reads from it. You can specify such
 * an exception (either `RuntimeException` or `IOException`) after
 * specifying the text. The exception will be thrown by the next `read`
 * after the text has been consumed.
 * <pre>
 * &#064;Test
 * void System_in_throws_IOException(
 * ) throws Exception {
 * withTextFromSystemIn("first line", "second line")
 * .andExceptionThrownOnInputEnd(new IOException())
 * .execute(()-&gt; {
 * Scanner scanner = new Scanner(System.in);
 * scanner.nextLine();
 * scanner.nextLine();
 * assertThrows(
 * IOException.class,
 * ()-&gt; scanner.readLine()
 * );
 * });
 * }
 *
 * &#064;Test
 * void System_in_throws_RuntimeException(
 * ) throws Exception {
 * withTextFromSystemIn("first line", "second line")
 * .andExceptionThrownOnInputEnd(new RuntimeException())
 * .execute(()-&gt; {
 * Scanner scanner = new Scanner(System.in);
 * scanner.nextLine();
 * scanner.nextLine();
 * assertThrows(
 * RuntimeException.class,
 * ()-&gt; scanner.readLine()
 * );
 * });
 * }
</pre> *
 *
 *
 * You can write a test that throws an exception immediately by not providing
 * any text.
 * <pre>
 * withTextFromSystemIn()
 * .andExceptionThrownOnInputEnd(...)
 * .execute(()-&gt; {
 * Scanner scanner = new Scanner(System.in);
 * assertThrows(
 * ...,
 * ()-&gt; scanner.readLine()
 * );
 * });
</pre> *
 *
 * <h2>Security Manager</h2>
 *
 *
 * The function
 * [withSecurityManager][.withSecurityManager]
 * lets you specify the `SecurityManager` that is returned by
 * `System.getSecurityManger()` while your code under test is executed.
 * <pre>
 * &#064;Test
 * void execute_code_with_specific_SecurityManager(
 * ) throws Exception {
 * SecurityManager securityManager = new ASecurityManager();
 * withSecurityManager(
 * securityManager,
 * ()-&gt; {
 * //code under test
 * //e.g. the following assertion is met
 * assertSame(
 * securityManager,
 * System.getSecurityManager()
 * );
 * }
 * );
 * }
</pre> *
 *
 * After `withSecurityManager(...)` is executed
 * `System.getSecurityManager()` returns the original security manager
 * again.
 */
object SystemLambda {
	private val AUTO_FLUSH = true
	private val DEFAULT_ENCODING = defaultCharset().name()

	/**
	 * Executes the statement and fails (throws an `AssertionError`) if
	 * the statement tries to write to `System.err`.
	 *
	 * The following test fails
	 * <pre>
	 * &#064;Test
	 * void fails_because_something_is_written_to_System_err(
	 * ) throws Exception {
	 * assertNothingWrittenToSystemErr(()-&gt; {
	 * System.err.println("some text");
	 * });
	 * }
	</pre> *
	 * The test fails with the failure "Tried to write 's' to System.err
	 * although this is not allowed."
	 *
	 * @param statement an arbitrary piece of code.
	 * @throws AssertionError if the statements tries to write to
	 * `System.err`.
	 * @throws Exception any exception thrown by the statement.
	 * @see .assertNothingWrittenToSystemOut
	 * @since 1.0.0
	 */
	fun assertNothingWrittenToSystemErr(
		statement: () -> Unit
	) {
		executeWithSystemErrReplacement(
			DisallowWriteStream(),
			statement
		)
	}

	/**
	 * Executes the statement and fails (throws an `AssertionError`) if
	 * the statement tries to write to `System.out`.
	 *
	 * The following test fails
	 * <pre>
	 * &#064;Test
	 * void fails_because_something_is_written_to_System_out(
	 * ) throws Exception {
	 * assertNothingWrittenToSystemOut(()-&gt; {
	 * System.out.println("some text");
	 * });
	 * }
	</pre> *
	 * The test fails with the failure "Tried to write 's' to System.out
	 * although this is not allowed."
	 *
	 * @param statement an arbitrary piece of code.
	 * @throws AssertionError if the statements tries to write to
	 * `System.out`.
	 * @throws Exception any exception thrown by the statement.
	 * @see .assertNothingWrittenToSystemErr
	 * @since 1.0.0
	 */
	fun assertNothingWrittenToSystemOut(
		statement: () -> Unit
	) {
		executeWithSystemOutReplacement(
			DisallowWriteStream(),
			statement
		)
	}

	/**
	 * Executes the statement and returns the status code that is provided to
	 * `System.exit(int)` within the statement. Additionally it avoids
	 * that the JVM is shut down because of a call to `System.exit(int)`.
	 * <pre>
	 * @Test
	 * void application_exits_with_status_42(
	 * ) throws Exception {
	 * int statusCode = catchSystemExit(()-&gt; {
	 * System.exit(42);
	 * });
	 * assertEquals(42, statusCode);
	 * }
	</pre> *
	 * @param statement an arbitrary piece of code.
	 * @return the status code provided to `System.exit(int)`.
	 * @throws AssertionError if the statement does not call
	 * `System.exit(int)`.
	 * @throws Exception any exception thrown by the statement.
	 * @since 1.0.0
	 */
	fun catchSystemExit(
		statement: () -> Unit
	): Int {
		val noExitSecurityManager = NoExitSecurityManager(getSecurityManager())
		try {
			withSecurityManager(noExitSecurityManager, statement)
		} catch (ignored: CheckExitCalled) {
		}
		return checkSystemExit(noExitSecurityManager)
	}

	/**
	 * Executes the statement and suppresses the output of the statement to
	 * `System.err`. Use this to avoid that the output of your build tool
	 * gets mixed with the output of the code under test.
	 * <pre>
	 * &#064;Test
	 * void nothing_is_written_to_System_err(
	 * ) throws Exception {
	 * muteSystemErr(()-&gt; {
	 * System.err.println("some text");
	 * }
	 * );
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @throws Exception any exception thrown by the statement.
	 * @see .muteSystemOut
	 * @since 1.0.0
	 */
	fun muteSystemErr(
		statement: () -> Unit
	) {
		executeWithSystemErrReplacement(
			NoopStream(),
			statement
		)
	}

	/**
	 * Executes the statement and suppresses the output of the statement to
	 * `System.out`. Use this to avoid that the output of your build tool
	 * gets mixed with the output of the code under test.
	 * <pre>
	 * &#064;Test
	 * void nothing_is_written_to_System_out(
	 * ) throws Exception {
	 * muteSystemOut(()-&gt; {
	 * System.out.println("some text");
	 * });
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @throws Exception any exception thrown by the statement.
	 * @see .muteSystemErr
	 * @since 1.0.0
	 */
	fun muteSystemOut(
		statement: () -> Unit
	) {
		executeWithSystemOutReplacement(
			NoopStream(),
			statement
		)
	}

	/**
	 * Executes the statement and restores the system properties after the
	 * statement has been executed. This allows you to set or clear system
	 * properties within the statement without affecting other tests.
	 * <pre>
	 * &#064;Test
	 * void execute_code_that_manipulates_system_properties(
	 * ) throws Exception {
	 * System.clearProperty("some property");
	 * System.setProperty("another property", "value before test");
	 *
	 * restoreSystemProperties(()-&gt; {
	 * System.setProperty("some property", "some value");
	 * assertEquals(
	 * "some value",
	 * System.getProperty("some property")
	 * );
	 *
	 * System.clearProperty("another property");
	 * assertNull(
	 * System.getProperty("another property")
	 * );
	 * });
	 *
	 * //values are restored after test
	 * assertNull(
	 * System.getProperty("some property")
	 * );
	 * assertEquals(
	 * "value before test",
	 * System.getProperty("another property")
	 * );
	 * }
	</pre> *
	 * @param statement an arbitrary piece of code.
	 * @throws Exception any exception thrown by the statement.
	 * @since 1.0.0
	 */
	fun restoreSystemProperties(
		statement: () -> Unit
	) {
		val originalProperties = getProperties()
		setProperties(copyOf(originalProperties))
		try {
			statement()
		} finally {
			setProperties(originalProperties)
		}
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.err` by the statement.
	 * <pre>
	 * &#064;Test
	 * void application_writes_text_to_System_err(
	 * ) throws Exception {
	 * String textWrittenToSystemErr = tapSystemErr(()-&gt; {
	 * System.err.print("some text");
	 * });
	 * assertEquals("some text", textWrittenToSystemErr);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.err` by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemOut
	 * @see .tapSystemErrAndOut
	 * @see .tapSystemErrAndOutNormalized
	 * @since 1.0.0
	 */
	fun tapSystemErr(
		statement: () -> Unit
	): String {
		val tapStream = TapStream()
		executeWithSystemErrReplacement(
			tapStream,
			statement
		)
		return tapStream.textThatWasWritten()
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.err` by the statement. New line characters are replaced
	 * with a single `\n`.
	 * <pre>
	 * &#064;Test
	 * void application_writes_mutliple_lines_to_System_err(
	 * ) throws Exception {
	 * String textWrittenToSystemErr = tapSystemErrNormalized(()-&gt; {
	 * System.err.println("some text");
	 * });
	 * assertEquals("some text\n", textWrittenToSystemErr);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.err` by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemOut
	 * @see .tapSystemErrAndOut
	 * @see .tapSystemErrAndOutNormalized
	 * @since 1.0.0
	 */
	fun tapSystemErrNormalized(
		statement: () -> Unit
	): String {
		return tapSystemErr(statement)
			.replace(lineSeparator(), "\n")
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.err` and `System.out` by the statement.
	 * <pre>
	 * &#064;Test
	 * void application_writes_text_to_System_err_and_out(
	 * ) throws Exception {
	 * String text = tapSystemErrAndOut(()-&gt; {
	 * System.err.print("text from err");
	 * System.out.print("text from out");
	 * });
	 * assertEquals("text from errtext from out", text);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.err` and `System.out`
	 * by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemErrAndOutNormalized
	 * @see .tapSystemErr
	 * @see .tapSystemErrNormalized
	 * @see .tapSystemOut
	 * @see .tapSystemOutNormalized
	 * @since 1.2.0
	 */
	fun tapSystemErrAndOut(
		statement: () -> Unit
	): String {
		val tapStream = TapStream()
		executeWithSystemErrReplacement(tapStream) {
			executeWithSystemOutReplacement(tapStream, statement)
		}
		return tapStream.textThatWasWritten()
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.err` and `System.out` by the statement. New line
	 * characters are replaced with a single `\n`.
	 * <pre>
	 * &#064;Test
	 * void application_writes_mutliple_lines_to_System_err_and_out(
	 * ) throws Exception {
	 * String text = tapSystemErrAndOutNormalized(()-&gt; {
	 * System.err.println("text from err");
	 * System.out.println("text from out");
	 * });
	 * assertEquals("text from err\ntext from out\n", text);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.err` and `System.out`
	 * by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemErrAndOut
	 * @see .tapSystemErr
	 * @see .tapSystemErrNormalized
	 * @see .tapSystemOut
	 * @see .tapSystemOutNormalized
	 * @since 1.2.0
	 */
	fun tapSystemErrAndOutNormalized(
		statement: () -> Unit
	): String {
		return tapSystemErrAndOut(statement)
			.replace(lineSeparator(), "\n")
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.out` by the statement.
	 * <pre>
	 * &#064;Test
	 * void application_writes_text_to_System_out(
	 * ) throws Exception {
	 * String textWrittenToSystemOut = tapSystemOut(()-&gt; {
	 * System.out.print("some text");
	 * });
	 * assertEquals("some text", textWrittenToSystemOut);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.out` by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemErr
	 * @see .tapSystemErrAndOut
	 * @see .tapSystemErrAndOutNormalized
	 * @since 1.0.0
	 */
	fun tapSystemOut(
		statement: () -> Unit
	): String {
		val tapStream = TapStream()
		executeWithSystemOutReplacement(
			tapStream,
			statement
		)
		return tapStream.textThatWasWritten()
	}

	/**
	 * Executes the statement and returns the text that was written to
	 * `System.out` by the statement. New line characters are replaced
	 * with a single `\n`.
	 * <pre>
	 * &#064;Test
	 * void application_writes_mutliple_lines_to_System_out(
	 * ) throws Exception {
	 * String textWrittenToSystemOut = tapSystemOutNormalized(()-&gt; {
	 * System.out.println("some text");
	 * });
	 * assertEquals("some text\n", textWrittenToSystemOut);
	 * }
	</pre> *
	 *
	 * @param statement an arbitrary piece of code.
	 * @return text that is written to `System.out` by the statement.
	 * @throws Exception any exception thrown by the statement.
	 * @see .tapSystemErr
	 * @see .tapSystemErrAndOut
	 * @see .tapSystemErrAndOutNormalized
	 * @since 1.0.0
	 */
	fun tapSystemOutNormalized(
		statement: () -> Unit
	): String {
		return tapSystemOut(statement)
			.replace(lineSeparator(), "\n")
	}

	/**
	 * Executes the statement with the specified environment variables. All
	 * changes to environment variables are reverted after the statement has
	 * been executed.
	 * <pre>
	 * &#064;Test
	 * void execute_code_with_environment_variables(
	 * ) throws Exception {
	 * List&lt;String&gt; values = withEnvironmentVariable("first", "first value")
	 * .and("second", "second value")
	 * .and("third", null)
	 * .execute(()-&gt; asList(
	 * System.getenv("first"),
	 * System.getenv("second"),
	 * System.getenv("third")
	 * ));
	 * assertEquals(
	 * asList("first value", "second value", null),
	 * values
	 * );
	 * }
	</pre> *
	 *
	 * You cannot specify the value of an an environment variable twice. An
	 * `IllegalArgumentException` is thrown when you try.
	 *
	 * **Warning:** This method uses reflection for modifying internals of the
	 * environment variables map. It fails if your `SecurityManager` forbids
	 * such modifications.
	 * @param name the name of the environment variable.
	 * @param value the value of the environment variable.
	 * @return an [WithEnvironmentVariables] instance that can be used to
	 * set more variables and run a statement with the specified environment
	 * variables.
	 * @since 1.0.0
	 * @see WithEnvironmentVariables.and
	 * @see WithEnvironmentVariables.execute
	 * @see WithEnvironmentVariables.execute
	 */
	fun withEnvironmentVariable(
		name: String,
		value: String?
	): WithEnvironmentVariables {
		return WithEnvironmentVariables(
			singletonMap(name, value)
		)
	}

	/**
	 * Executes the statement with the provided security manager.
	 * <pre>
	 * &#064;Test
	 * void execute_code_with_specific_SecurityManager(
	 * ) throws Exception {
	 * SecurityManager securityManager = new ASecurityManager();
	 * withSecurityManager(
	 * securityManager,
	 * ()-&gt; {
	 * //code under test
	 * //e.g. the following assertion is met
	 * assertSame(securityManager, System.getSecurityManager())
	 * }
	 * );
	 * }
	</pre> *
	 * The specified security manager is only present during the test.
	 * @param securityManager the security manager that is used while the
	 * statement is executed.
	 * @param statement an arbitrary piece of code.
	 * @throws Exception any exception thrown by the statement.
	 * @since 1.0.0
	 */
	fun withSecurityManager(
		securityManager: SecurityManager?,
		statement: () -> Unit
	) {
		val originalSecurityManager = getSecurityManager()
		setSecurityManager(securityManager)
		try {
			statement()
		} finally {
			setSecurityManager(originalSecurityManager)
		}
	}

	/**
	 * Executes the statement and lets `System.in` provide the specified
	 * text during the execution. In addition several Exceptions can be
	 * specified that are thrown when `System.in#read` is called.
	 *
	 * <pre>
	 * &#064;Test
	 * void Scanner_reads_text_from_System_in(
	 * ) throws Exception {
	 * withTextFromSystemIn("first line", "second line")
	 * .execute(()-&gt; {
	 * Scanner scanner = new Scanner(System.in);
	 * scanner.nextLine();
	 * assertEquals("first line", scanner.nextLine());
	 * });
	 * }
	</pre> *
	 *
	 * <h3>Throwing Exceptions</h3>
	 *
	 * You can also simulate a `System.in` that throws an
	 * `IOException` or `RuntimeException`. Use
	 *
	 * <pre>
	 * &#064;Test
	 * void System_in_throws_IOException(
	 * ) throws Exception {
	 * withTextFromSystemIn()
	 * .andExceptionThrownOnInputEnd(new IOException())
	 * .execute(()-&gt; {
	 * assertThrows(
	 * IOException.class,
	 * ()-&gt; new Scanner(System.in).readLine())
	 * );
	 * )};
	 * }
	 *
	 * &#064;Test
	 * void System_in_throws_RuntimeException(
	 * ) throws Exception {
	 * withTextFromSystemIn()
	 * .andExceptionThrownOnInputEnd(new RuntimeException())
	 * .execute(()-&gt; {
	 * assertThrows(
	 * RuntimeException.class,
	 * ()-&gt; new Scanner(System.in).readLine())
	 * );
	 * )};
	 * }
	</pre> *
	 *
	 * If you provide text as parameters of `withTextFromSystemIn(...)`
	 * in addition then the exception is thrown after the text has been read
	 * from `System.in`.
	 * @param lines the lines that are available from `System.in`.
	 * @return an [SystemInStub] instance that is used to execute a
	 * statement with its [execute][SystemInStub.execute]
	 * method. In addition it can be used to specify an exception that is thrown
	 * after the text is read.
	 * @since 1.0.0
	 * @see SystemInStub.execute
	 * @see SystemInStub.andExceptionThrownOnInputEnd
	 * @see SystemInStub.andExceptionThrownOnInputEnd
	 */
	fun withTextFromSystemIn(
		vararg lines: String?
	): SystemInStub {
		val text: String = stream(lines)
			.map({ line -> line.toString() + lineSeparator() })
			.collect(joining())
		return SystemInStub(text)
	}

	private fun copyOf(
		source: Properties
	): Properties {
		val copy = Properties()
		copy.putAll(source)
		return copy
	}

	private fun executeWithSystemErrReplacement(
		replacementForErr: OutputStream,
		statement: () -> Unit
	) {
		val originalStream: PrintStream = err
		try {
			setErr(wrap(replacementForErr))
			statement()
		} finally {
			setErr(originalStream)
		}
	}

	private fun executeWithSystemOutReplacement(
		replacementForOut: OutputStream,
		statement: () -> Unit
	) {
		val originalStream: PrintStream = System.out
		try {
			setOut(wrap(replacementForOut))
			statement()
		} finally {
			setOut(originalStream)
		}
	}

	@Throws(UnsupportedEncodingException::class)
	private fun wrap(
		outputStream: OutputStream
	): PrintStream {
		return PrintStream(
			outputStream,
			AUTO_FLUSH,
			DEFAULT_ENCODING
		)
	}

	private fun checkSystemExit(
		securityManager: NoExitSecurityManager
	): Int {
		return if (securityManager.isCheckExitCalled) securityManager.statusOfFirstCheckExitCall else throw AssertionError(
			"System.exit has not been called."
		)
	}

	private class DisallowWriteStream() : OutputStream() {
		override fun write(
			b: Int
		) {
			throw AssertionError(
				"Tried to write '"
						+ b.toChar() + "' although this is not allowed."
			)
		}
	}

	private class NoopStream() : OutputStream() {
		override fun write(
			b: Int
		) {
		}
	}

	/**
	 * A stub that defines the text provided by `System.in`. The methods
	 * [.andExceptionThrownOnInputEnd] and
	 * [.andExceptionThrownOnInputEnd] can be used to
	 * simulate a `System.in` that throws an exception.
	 *
	 * The specified behaviour of `System.in` is applied to an
	 * arbitrary piece of code that is provided to [.execute].
	 */
	class SystemInStub(
		private val text: String
	) {
		private var ioException: IOException? = null
		private var runtimeException: RuntimeException? = null

		/**
		 * Sets an exception that is thrown after the text is read.
		 * @param exception the `IOException` to be thrown.
		 * @return the `SystemInStub` itself.
		 * @throws IllegalStateException if a `RuntimeException` was
		 * already set by
		 * [.andExceptionThrownOnInputEnd]
		 */
		fun andExceptionThrownOnInputEnd(
			exception: IOException?
		): SystemInStub {
			if (runtimeException != null) throw IllegalStateException(
				("You cannot call"
						+ " andExceptionThrownOnInputEnd(IOException) because"
						+ " andExceptionThrownOnInputEnd(RuntimeException) has"
						+ " already been called.")
			)
			ioException = exception
			return this
		}

		/**
		 * Sets an exception that is thrown after the text is read.
		 * @param exception the `RuntimeException` to be thrown.
		 * @return the `SystemInStub` itself.
		 * @throws IllegalStateException if an `IOException` was already
		 * set by [.andExceptionThrownOnInputEnd]
		 */
		fun andExceptionThrownOnInputEnd(
			exception: RuntimeException?
		): SystemInStub {
			if (ioException != null) throw IllegalStateException(
				("You cannot call"
						+ " andExceptionThrownOnInputEnd(RuntimeException) because"
						+ " andExceptionThrownOnInputEnd(IOException) has already"
						+ " been called.")
			)
			runtimeException = exception
			return this
		}

		/**
		 * Executes the statement and lets `System.in` provide the
		 * specified text during the execution. After the text was read it
		 * throws and exception when `System.in#read` is called and an
		 * exception was specified by
		 * [.andExceptionThrownOnInputEnd] or
		 * [.andExceptionThrownOnInputEnd].
		 * @param statement an arbitrary piece of code.
		 * @throws Exception any exception thrown by the statement.
		 */
		@Throws(Exception::class)
		fun execute(
			statement: () -> Unit
		) {
			val stubStream: InputStream = ReplacementInputStream(
				text, ioException, runtimeException
			)
			val originalIn = System.`in`
			try {
				setIn(stubStream)
				statement()
			} finally {
				setIn(originalIn)
			}
		}

		private class ReplacementInputStream internal constructor(
			text: String?,
			ioException: IOException?,
			runtimeException: RuntimeException?
		) : InputStream() {
			private val reader: StringReader
			private val ioException: IOException?
			private val runtimeException: RuntimeException?

			init {
				reader = StringReader(text)
				this.ioException = ioException
				this.runtimeException = runtimeException
			}

			@Throws(IOException::class)
			override fun read(): Int {
				val character = reader.read()
				if (character == -1) handleEmptyReader()
				return character
			}

			@Throws(IOException::class)
			private fun handleEmptyReader() {
				if (ioException != null) throw ioException else if (runtimeException != null) throw runtimeException
			}

			@Throws(IOException::class)
			override fun read(
				buffer: ByteArray,
				offset: Int,
				len: Int
			): Int {
				if (buffer == null) throw NullPointerException() else if ((offset < 0) || (len < 0) || (len > buffer.size - offset)) throw IndexOutOfBoundsException() else return if (len == 0) 0 else readNextLine(
					buffer,
					offset,
					len
				)
			}

			@Throws(IOException::class)
			private fun readNextLine(
				buffer: ByteArray,
				offset: Int,
				len: Int
			): Int {
				val c = read()
				if (c == -1) return -1
				buffer[offset] = c.toByte()
				var i = 1
				while ((i < len) && !isCompleteLineWritten(buffer, i - 1)) {
					val read = read().toByte()
					if (read.toInt() == -1) break else buffer[offset + i] = read
					++i
				}
				return i
			}

			private fun isCompleteLineWritten(
				buffer: ByteArray,
				indexLastByteWritten: Int
			): Boolean {
//				val separator: ByteArray = System.getProperty("line.separator")
//					.getBytes(defaultCharset())
//				val indexFirstByteOfSeparator = indexLastByteWritten
//				-separator.size + 1
//				return (indexFirstByteOfSeparator >= 0
//						&& contains(buffer, separator, indexFirstByteOfSeparator))
				return true
			}

			private fun contains(
				array: ByteArray,
				pattern: ByteArray,
				indexStart: Int
			): Boolean {
				for (i in pattern.indices) if (array[indexStart + i] != pattern[i]) return false
				return true
			}
		}
	}

	private class TapStream() : OutputStream() {
		val text = ByteArrayOutputStream()
		override fun write(
			b: Int
		) {
			text.write(b)
		}

		fun textThatWasWritten(): String {
			return text.toString()
		}
	}

	/**
	 * A collection of values for environment variables. New values can be
	 * added by [.and]. The `EnvironmentVariables`
	 * object is then used to execute an arbitrary piece of code with these
	 * environment variables being present.
	 */
	class WithEnvironmentVariables(
		private val variables: Map<String, String?>
	) {
		/**
		 * Creates a new `WithEnvironmentVariables` object that
		 * additionally stores the value for an additional environment variable.
		 *
		 * You cannot specify the value of an environment variable twice. An
		 * `IllegalArgumentException` when you try.
		 * @param name the name of the environment variable.
		 * @param value the value of the environment variable.
		 * @return a new `WithEnvironmentVariables` object.
		 * @throws IllegalArgumentException when a value for the environment
		 * variable `name` is already specified.
		 * @see .withEnvironmentVariable
		 * @see .execute
		 */
		fun and(
			name: String,
			value: String
		): WithEnvironmentVariables {
			validateNotSet(name, value)
			val moreVariables = HashMap(
				variables
			)
			moreVariables[name] = value
			return WithEnvironmentVariables(moreVariables)
		}

		private fun validateNotSet(
			name: String,
			value: String
		) {
			if (variables.containsKey(name)) {
				val currentValue = variables[name]
				throw IllegalArgumentException(
					("The environment variable '" + name + "' cannot be set to "
							+ format(value) + " because it was already set to "
							+ format(currentValue) + ".")
				)
			}
		}

		private fun format(
			text: String?
		): String {
			return if (text == null) "null" else "'$text'"
		}

		/**
		 * Executes a `Callable` with environment variable values
		 * according to what was set before. It exposes the return value of the
		 * `Callable`. All changes to environment variables are reverted
		 * after the `Callable` has been executed.
		 * <pre>
		 * &#064;Test
		 * void execute_code_with_environment_variables(
		 * ) throws Exception {
		 * List&lt;String&gt; values = withEnvironmentVariable("first", "first value")
		 * .and("second", "second value")
		 * .and("third", null)
		 * .execute(()-&gt; asList(
		 * System.getenv("first"),
		 * System.getenv("second"),
		 * System.getenv("third")
		 * ));
		 * assertEquals(
		 * asList("first value", "second value", null),
		 * values
		 * );
		 * }
		</pre> *
		 *
		 * **Warning:** This method uses reflection for modifying internals of the
		 * environment variables map. It fails if your `SecurityManager` forbids
		 * such modifications.
		 * @param <T> the type of `callable`'s result
		 * @param callable an arbitrary piece of code.
		 * @return the return value of `callable`.
		 * @throws Exception any exception thrown by the callable.
		 * @since 1.1.0
		 * @see .withEnvironmentVariable
		 * @see .and
		 * @see .execute
		</T> */
		@Throws(Exception::class)
		fun <T> execute(
			callable: Callable<T>
		): T {
			val originalVariables: Map<String?, String?> = HashMap<String?, String?>(getenv())
			try {
				setEnvironmentVariables()
				return callable.call()
			} finally {
				restoreOriginalVariables(originalVariables)
			}
		}

		/**
		 * Executes a statement with environment variable values according to
		 * what was set before. All changes to environment variables are
		 * reverted after the statement has been executed.
		 * <pre>
		 * &#064;Test
		 * void execute_code_with_environment_variables(
		 * ) throws Exception {
		 * withEnvironmentVariable("first", "first value")
		 * .and("second", "second value")
		 * .and("third", null)
		 * .execute(()-&gt; {
		 * assertEquals(
		 * "first value",
		 * System.getenv("first")
		 * );
		 * assertEquals(
		 * "second value",
		 * System.getenv("second")
		 * );
		 * assertNull(
		 * System.getenv("third")
		 * );
		 * });
		 * }
		</pre> *
		 *
		 * **Warning:** This method uses reflection for modifying internals of the
		 * environment variables map. It fails if your `SecurityManager` forbids
		 * such modifications.
		 * @param statement an arbitrary piece of code.
		 * @throws Exception any exception thrown by the statement.
		 * @since 1.0.0
		 * @see .withEnvironmentVariable
		 * @see WithEnvironmentVariables.and
		 * @see .execute
		 */
		@Throws(Exception::class)
		fun execute(
			statement: () -> Unit
		) {
			val originalVariables: Map<String?, String?> = HashMap<String?, String?>(getenv())
			try {
				setEnvironmentVariables()
				statement()
			} finally {
				restoreOriginalVariables(originalVariables)
			}
		}

		private fun setEnvironmentVariables() {
			overrideVariables(
				editableMapOfVariables
			)
			overrideVariables(
				theCaseInsensitiveEnvironment
			)
		}

		private fun overrideVariables(
			existingVariables: MutableMap<String?, String?>?
		) {
			if (existingVariables != null) //theCaseInsensitiveEnvironment may be null
				variables.forEach(
					{ name: String, value: String? ->
						set(
							existingVariables,
							name,
							value
						)
					}
				)
		}

		private operator fun set(
			variables: MutableMap<String?, String?>,
			name: String,
			value: String?
		) {
			if (value == null) variables.remove(name) else variables[name] = value
		}

		fun restoreOriginalVariables(
			originalVariables: Map<String?, String?>?
		) {
			restoreVariables(
				editableMapOfVariables,
				originalVariables
			)
			restoreVariables(
				theCaseInsensitiveEnvironment,
				originalVariables
			)
		}

		fun restoreVariables(
			variables: MutableMap<String?, String?>?,
			originalVariables: Map<String?, String?>?
		) {
			if (variables != null) { //theCaseInsensitiveEnvironment may be null
				variables.clear()
				variables.putAll((originalVariables)!!)
			}
		}

		companion object {
			private val editableMapOfVariables: MutableMap<String?, String?>
				private get() {
					val classOfMap: Class<*> = getenv().javaClass
					try {
						return getFieldValue(classOfMap, getenv(), "m")
					} catch (e: IllegalAccessException) {
						throw RuntimeException(
							("System Rules cannot access the field"
									+ " 'm' of the map System.getenv()."), e
						)
					} catch (e: NoSuchFieldException) {
						throw RuntimeException(
							("System Rules expects System.getenv() to"
									+ " have a field 'm' but it has not."), e
						)
					}
				}//this field is only available for Windows

			/*
           * The names of environment variables are case-insensitive in Windows.
           * Therefore it stores the variables in a TreeMap named
           * theCaseInsensitiveEnvironment.
           */
			private val theCaseInsensitiveEnvironment: MutableMap<String?, String?>?
				private get() {
					try {
						val processEnvironment: Class<*> = forName("java.lang.ProcessEnvironment")
						return getFieldValue(
							processEnvironment, null, "theCaseInsensitiveEnvironment"
						)
					} catch (e: ClassNotFoundException) {
						throw RuntimeException(
							("System Rules expects the existence of"
									+ " the class java.lang.ProcessEnvironment but it does not"
									+ " exist."), e
						)
					} catch (e: IllegalAccessException) {
						throw RuntimeException(
							("System Rules cannot access the static"
									+ " field 'theCaseInsensitiveEnvironment' of the class"
									+ " java.lang.ProcessEnvironment."), e
						)
					} catch (e: NoSuchFieldException) {
						//this field is only available for Windows
						return null
					}
				}

			@Throws(NoSuchFieldException::class, IllegalAccessException::class)
			private fun getFieldValue(
				klass: Class<*>,
				`object`: Any?,
				name: String
			): MutableMap<String?, String?> {
				val field: Field = klass.getDeclaredField(name)
				field.setAccessible(true)
				return field.get(`object`) as MutableMap<String?, String?>
			}
		}
	}

	private object CheckExitCalled : SecurityException() {
		private val serialVersionUID = 159678654L
	}

	/**
	 * A `NoExitSecurityManager` throws a [CheckExitCalled]
	 * exception whenever [.checkExit] is called. All other method
	 * calls are delegated to the original security manager.
	 */
	private class NoExitSecurityManager internal constructor(
		private val originalSecurityManager: SecurityManager?
	) : SecurityManager() {
		private var statusOfFirstExitCall: Int? = null
		override fun checkExit(
			status: Int
		) {
			if (statusOfFirstExitCall == null) statusOfFirstExitCall = status
//			throw CheckExitCalled()
		}

		val isCheckExitCalled: Boolean
			get() = statusOfFirstExitCall != null
		val statusOfFirstCheckExitCall: Int
			get() {
				return if (isCheckExitCalled) (statusOfFirstExitCall)!! else throw IllegalStateException(
					"checkExit(int) has not been called."
				)
			}
//		val inCheck: Boolean
//			get() = ((originalSecurityManager != null)
//					&& originalSecurityManager.getInCheck()) // getInCheck

		override fun getSecurityContext(): Any {
			return if ((originalSecurityManager == null)) super.getSecurityContext() else originalSecurityManager.securityContext
		}

		override fun checkPermission(
			perm: Permission?
		) {
			originalSecurityManager?.checkPermission(perm)
		}

		override fun checkPermission(
			perm: Permission?,
			context: Any?
		) {
			originalSecurityManager?.checkPermission(perm, context)
		}

		override fun checkCreateClassLoader() {
			originalSecurityManager?.checkCreateClassLoader()
		}

		override fun checkAccess(
			t: Thread
		) {
			originalSecurityManager?.checkAccess(t)
		}

		override fun checkAccess(
			g: ThreadGroup
		) {
			originalSecurityManager?.checkAccess(g)
		}

		override fun checkExec(
			cmd: String
		) {
			originalSecurityManager?.checkExec(cmd)
		}

		override fun checkLink(
			lib: String
		) {
			originalSecurityManager?.checkLink(lib)
		}

		override fun checkRead(
			fd: FileDescriptor
		) {
			originalSecurityManager?.checkRead(fd)
		}

		override fun checkRead(
			file: String
		) {
			originalSecurityManager?.checkRead(file)
		}

		override fun checkRead(
			file: String,
			context: Any
		) {
			originalSecurityManager?.checkRead(file, context)
		}

		override fun checkWrite(
			fd: FileDescriptor
		) {
			originalSecurityManager?.checkWrite(fd)
		}

		override fun checkWrite(
			file: String
		) {
			originalSecurityManager?.checkWrite(file)
		}

		override fun checkDelete(
			file: String
		) {
			originalSecurityManager?.checkDelete(file)
		}

		override fun checkConnect(
			host: String,
			port: Int
		) {
			originalSecurityManager?.checkConnect(host, port)
		}

		override fun checkConnect(
			host: String,
			port: Int,
			context: Any
		) {
			originalSecurityManager?.checkConnect(host, port, context)
		}

		override fun checkListen(
			port: Int
		) {
			originalSecurityManager?.checkListen(port)
		}

		override fun checkAccept(
			host: String,
			port: Int
		) {
			originalSecurityManager?.checkAccept(host, port)
		}

		override fun checkMulticast(
			maddr: InetAddress
		) {
			originalSecurityManager?.checkMulticast(maddr)
		}

		override fun checkMulticast(
			maddr: InetAddress,
			ttl: Byte
		) {
			originalSecurityManager?.checkMulticast(maddr, ttl)
		}

		override fun checkPropertiesAccess() {
			originalSecurityManager?.checkPropertiesAccess()
		}

		override fun checkPropertyAccess(
			key: String
		) {
			originalSecurityManager?.checkPropertyAccess(key)
		}

		fun checkTopLevelWindow(
			window: Any?
		): Boolean {
//			return if ((originalSecurityManager == null)) super.checkTopLevelWindow(window) else originalSecurityManager.checkTopLevelWindow(
//				window
//			)
			return false;
		}

		override fun checkPrintJobAccess() {
			originalSecurityManager?.checkPrintJobAccess()
		}

		fun checkSystemClipboardAccess() {
//			originalSecurityManager?.checkSystemClipboardAccess()
		}

		fun checkAwtEventQueueAccess() {
//			originalSecurityManager?.checkAwtEventQueueAccess()
		}

		override fun checkPackageAccess(
			pkg: String
		) {
			originalSecurityManager?.checkPackageAccess(pkg)
		}

		override fun checkPackageDefinition(
			pkg: String
		) {
			originalSecurityManager?.checkPackageDefinition(pkg)
		}

		override fun checkSetFactory() {
			originalSecurityManager?.checkSetFactory()
		}

		fun checkMemberAccess(
			clazz: Class<*>?,
			which: Int
		) {
//			originalSecurityManager?.checkMemberAccess(clazz, which)
		}

		override fun checkSecurityAccess(
			target: String
		) {
			originalSecurityManager?.checkSecurityAccess(target)
		}

		override fun getThreadGroup(): ThreadGroup {
			return if ((originalSecurityManager == null)) super.getThreadGroup() else originalSecurityManager.threadGroup
		}
	}
}