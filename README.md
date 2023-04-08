# Api Test Framework Template

Foobar is a Python library for dealing with word pluralization.

## General Info

This structure was created to provide the necessary infrastructure to prepare more complex API test automation frameworks using Java, Cucumber, RestAssured.

## Execution

It will execute every runner in the framework which are 'TestRunner' and 'Failed Test Runner'.

```bash
mvn clean test
```

Maven cucumber filters could be used for executing specific test scenarios with scenario tags.

```bash
mvn test -Dcucumber.filter.tags="@smoke and @fast"
```

## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
