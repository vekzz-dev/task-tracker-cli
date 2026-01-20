# AGENTS.md for Task Tracker CLI

This file provides guidelines for agentic coding agents (e.g., opencode) working on the Task Tracker CLI project. It includes build/lint/test commands, code style guidelines, and project-specific conventions derived from the Java Maven codebase.

## Project Overview
The Task Tracker CLI is a Java-based command-line application for managing tasks. It uses Maven for build management, Jackson for JSON persistence, AsciiTable for formatted output, JUnit/AssertJ/Mockito for testing, and Java 21+ features (configured for Java 25 in pom.xml). The project follows conventional commits and atomic changes. Key packages include models (e.g., Task, Status), CLI commands (e.g., AddCommand, MarkCommand), services (e.g., TaskService), repositories (e.g., TaskRepository), storage (e.g., FileManager), validations (e.g., TaskValidator, ArgumentValidator), and utils (e.g., JacksonConfig, TaskServiceHolder). CLI commands handle user input via CommandRouter and CommandParser, with output formatted using AsciiTable in OutputPrinter.

## Build/Lint/Test Commands

### Build Commands
- **Compile the project:** `mvn compile` - Compiles Java sources without running tests.
- **Package the application:** `mvn package` - Builds a JAR file including all dependencies (executable via `java -jar target/task-tracker-cli-1.0.jar`).
- **Clean and rebuild:** `mvn clean compile` - Removes previous builds and recompiles.
- **Skip tests during build:** `mvn package -DskipTests` - Builds without running tests for faster iterations.
- **Install to local repository:** `mvn install` - Builds and installs to local Maven repo.
- **Verify build:** `mvn verify` - Runs tests and integration tests.

### Test Commands
- **Run all tests:** `mvn test` - Executes all unit tests using JUnit 5.
- **Run a single test class:** `mvn test -Dtest=ClassName` (e.g., `mvn test -Dtest=FileManagerTest`).
- **Run a specific test method:** `mvn test -Dtest=ClassName#methodName` (e.g., `mvn test -Dtest=FileManagerTest#testWriteAndReadContent`).
- **Run tests in parallel:** `mvn test -DforkCount=2` - Speeds up execution on multi-core systems.
- **Run tests in verbose mode:** `mvn test -Dmaven.surefire.debug=true` - For debugging test failures.
- **Generate test reports:** `mvn surefire-report:report` - Creates HTML reports in target/site/surefire-report.html.
- **Run with coverage:** `mvn test jacoco:report` (requires Jacoco plugin) - Generate coverage reports.

### Lint Commands
No linting tools are currently configured in pom.xml. To enforce code quality, add these plugins to pom.xml:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.3.1</version>
    <configuration>
        <configLocation>checkstyle.xml</configLocation>
        <encoding>UTF-8</encoding>
        <consoleOutput>true</consoleOutput>
        <failsOnError>true</failsOnError>
    </configuration>
    <executions>
        <execution>
            <id>validate</id>
            <phase>validate</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

- Run style checks: `mvn checkstyle:check`
- Add SpotBugs for static analysis: `mvn spotbugs:check`

## Code Style Guidelines

### General Principles
- Follow Oracle Java Code Conventions (https://www.oracle.com/java/technologies/javase/codeconventions-contents.html).
- Prioritize readability and maintainability.
- No unnecessary comments; code should be self-explanatory.
- Use Java 21+ features (e.g., void main methods) where appropriate, but maintain compatibility with pom.xml settings.
- Prefer modern Java idioms over legacy patterns.

### Naming Conventions
- **Classes and Interfaces:** PascalCase (e.g., `Task`, `TaskRepository`).
- **Methods and Variables:** camelCase (e.g., `getDescription()`, `taskId`).
- **Constants and Enum Values:** UPPER_SNAKE_CASE (e.g., `TODO`, `IN_PROGRESS`).
- **Packages:** Lowercase, hierarchical (e.g., `io.vekzz_dev.task_tracker.models`).
- **Files:** Match class name (e.g., `Task.java`).

### Imports
- Organize imports alphabetically: static imports first, then regular.
- No wildcard imports (e.g., avoid `import java.util.*;`).
- Group by package: JDK, third-party (e.g., Jackson), project-specific.
- Example:
  ```java
  import static org.mockito.Mockito.*;

  import java.time.LocalDateTime;
  import java.util.List;

  import com.fasterxml.jackson.databind.ObjectMapper;

  import io.vekzz_dev.task_tracker.models.Status;
  ```

### Formatting
- **Indentation:** 4 spaces, no tabs.
- **Line Length:** Max 120 characters; wrap long lines logically.
- **Braces:** Opening brace on same line for classes, methods, and control structures.
- **Spacing:** One space around operators; no spaces inside parentheses.
- **Blank Lines:** One between methods; two between classes.
- Example:
  ```java
  public class Task {
      private int id;

      public int getId() {
          return id;
      }

      public void setDescription(String description) {
          this.description = description;
      }
  }
  ```

### Types and Data Structures
- Use primitives for performance (e.g., `int` for IDs).
- Leverage Jackson annotations for JSON serialization; use JacksonConfig for ObjectMapper setup.
- Prefer mutable objects for domain models; use records for simple data holders if applicable.
- Use Path for file paths; define constants for paths (e.g., BASE_PATH in FileManager).
- Example: `Task` class uses mutable fields for updates with selective setters; `Status` enum uses UPPER_SNAKE_CASE.

### Error Handling
- Use try-catch for IO operations (e.g., file reading/writing with Jackson or NIO).
- Validate inputs: Check for null/non-null, valid ranges (e.g., positive IDs); use validations package.
- Throw standard exceptions (e.g., `IllegalArgumentException` for invalid inputs).
- Wrap IOExceptions in RuntimeException subclasses like UncheckedIOException.
- Commands catch exceptions and print user-friendly messages (no stack traces).
- Example:
  ```java
  public static void validateDescription(String description) {
      if (description == null || description.isBlank()) {
          throw new IllegalArgumentException("Task description must not be blank");
      }
  }
  ```
- Example from FileManager: Wrap IOException in UncheckedIOException for file operations.

### Dependencies and Libraries
- **Jackson:** Use `ObjectMapper` for JSON handling; configure with JacksonConfig; register JSR310 module for dates.
- **AsciiTable:** Use for CLI output formatting; customize borders/styles as needed.
- **NIO:** Use java.nio.file for efficient file operations (Files, Path); prefer over legacy IO.
- **JUnit/AssertJ/Mockito:** Write descriptive test names; use AssertJ for fluent assertions; use Mockito for mocking.
- Avoid adding unnecessary dependencies; check pom.xml before introducing new ones.
- Current versions: Jackson 2.20.0, AsciiTable 0.3.2, JUnit 5.13.4, AssertJ 3.27.6, Mockito 5.20.0.

### CLI Commands
- Follow Command pattern: Each command implements Command interface with execute() method.
- Use CommandRouter for routing based on args; CommandParser for parsing (handles quoted strings).
- Name commands PascalCase (e.g., ListCommand); handle args via ArgumentValidator.
- Output via OutputPrinter using AsciiTable for tables; keep CLI simple and user-friendly.
- Example: AddCommand validates args, creates Task via TaskService, prints success message.

### Git and Workflow
- Use conventional commits: `feat(scope): description`, `fix(scope): description`.
- Atomic commits: One logical change per commit.
- Branches: Use feature branches (e.g., `feature/add-cli-commands`); merge via PRs.
- No secrets in code; use environment variables for configs.

### Testing
- Write unit tests for all public methods; use @TempDir for file-based tests.
- Use JUnit 5 annotations (@Test, @BeforeEach); AssertJ for fluent assertions; Mockito for mocking.
- Mock dependencies (e.g., TaskService in command tests via TaskServiceHolder).
- Test behavior over implementation; include edge cases and exception scenarios.
- Example: Use @TempDir for file operations; AssertJ for assertions like assertThat(file).exists().
- Example test pattern:
  ```java
  @Test
  void testExecuteAddsTaskWithValidDescription() {
      when(taskService.add("new task")).thenReturn(1);
      AddCommand command = new AddCommand(List.of("new task"));
      command.execute();
      verify(taskService).add("new task");
  }
  ```

### Security and Performance
- Use NIO for efficient file operations.
- Validate all user inputs; avoid command injection.
- Wrap checked exceptions to prevent unnecessary try-catch blocks.
- No secrets in code; use environment variables if needed.

### Additional Rules
- No TODO comments; resolve issues immediately.
- Follow Maven directory structure.
- Update this file when adding new features or changing conventions.
- For IDE: Use any Java IDE (IntelliJ IDEA, VS Code) with auto-format enabled.
- Cursor Rules: No .cursor/rules or .cursorrules files found; follow general Java conventions.
- Copilot Rules: No .github/copilot-instructions.md found; adhere to this AGENTS.md for AI-assisted coding.

### Utilities and Repositories
- **Utils Package:** Contains utility classes (FileManager for NIO-based file IO, JacksonConfig for JSON setup).
- **Validations Package:** Contains validators (TaskValidator for task checks, ArgumentValidator for CLI args).
- **Repositories Package:** Defines data access interfaces (Repository<T> for CRUD, TaskRepository for TaskStore).
- Example: FileManager uses NIO with UTF-8 encoding; Status.fromArgument() validates enum inputs.

This document is ~150 lines. Update as the project evolves. Refer to pom.xml for dependency versions.