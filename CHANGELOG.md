# Changelog

## v1.0.0 - 2026-01-20

### Features
- (models) define task status enumeration with CLI argument mapping (631f01b)
- (models) create task entity with ID, description, status and timestamps (718a6b4)
- (models) implement task collection with auto-increment ID generation (6cc3aee)
- (utils) configure Jackson ObjectMapper for JSON serialization (4f6acb2)
- (storage) handle .tktdata/tasks.json file creation and I/O operations (88a5272)
- (utils) provide singleton access to TaskService instance (c127479)
- (output) format task data into ASCII tables for CLI display (be09d84)
- (validations) validate command arguments for CLI input (e343f63)
- (validations) validate task data integrity constraints (c1cf88f)
- (repositories) define generic CRUD repository contract (df58ecf)
- (repositories) implement JSON-based task persistence layer (1121e8b)
- (services) implement task CRUD operations with filtering by status (2be23d5)
- (cli) define command execution interface (270c462)
- (cli) map command strings to command type enumeration (11d07ca)
- (cli) parse CLI arguments including quoted strings (bf5a95b)
- (cli) route parsed commands to appropriate handlers (978f6fe)
- (cli) add command to create new tasks with description (aee7536)
- (cli) add command to list all tasks or filter by status (db1eab9)
- (cli) add command to change task status (45fecbd)
- (cli) add command to update task description (1740e24)
- (cli) add command to delete tasks by ID (60ce19a)
- (cli) add command to display usage information (b4a9857)
- (main) wire components and launch CLI application (f188d56)
- (build) add maven-shade-plugin for executable JAR (c1feddd)

### Bug Fixes
- (services) prevent TaskStore duplication and state loss (dd014b0)
- (storage) initialize file with valid JSON object (978d623)

### Refactors
- (repositories) remove unused List import (d0557bc)
- (main) move Main class to correct package location (01c0ef6)
- (main) centralize exception handling in Main (64f6510)
- (cli) remove try-catch blocks from CLI commands (3dad421)
- (main) inject TaskService directly in CommandRouter (b2715bb)
- (cli) inject TaskService in command constructors (8d87884)
- (test) update tests for dependency injection (f1c7524)
- (models) add getters and setters to TaskStore (b9e7040)

### Documentation
- add MIT license (8d42112)
- add user-focused README with JBang installation (6ec211f)
- format feature lists as unordered lists without emojis (d6dce95)
- add roadmap.sh challenge reference to README (c63de90)
- update changelog for v1.0.0 (9226b01)

### Tests
- (storage) verify file initialization, read and write operations (3ddd4c2)
- (validations) verify argument validation and error handling (2f536cd)
- (validations) verify task validation rules and exceptions (6d16180)
- (repositories) verify JSON serialization and deserialization of tasks (c10cf30)
- (services) verify task business logic and error handling (93a364e)
- (cli) verify argument tokenization and parsing logic (81b9fb9)
- (cli) verify command routing and handler invocation (d0a9d12)
- (cli) verify task creation and ID generation (f7bf258)
- (cli) verify task listing and status filtering (21f9b5b)
- (cli) verify task status modification (5b6ec3d)
- (cli) verify task description updates (0be220d)
- (cli) verify task deletion and error handling (a894b53)
- (cli) verify help message display (693b6c8)
- (cli) fix command tests to expect thrown exceptions (80b3ca8)
- (cli) add AssertJ assertThatThrownBy imports to command tests (6baa512)

### Chores
- (project) initialize Maven project with dependencies (96c5055)
- (pom) fix dependency indentation (75c2f3b)
- (git) ignore dependency-reduced-pom.xml (2b6342f)
- (test) organize test imports (dce1a46)
- (test) organize CommandRouterTest imports (1238cfa)