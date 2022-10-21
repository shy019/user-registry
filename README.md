# USER-REGISTRY

Registration App is an app that let you save users to the database following some rules.

## Demo
These are some responses the API generate when we create the request:

![image](https://user-images.githubusercontent.com/33939388/197271190-34e6375f-17cf-4fb3-833e-3ec016f46b8a.png)

Once the application is running we'll be able to see the follow documents:

> Swagger documentation (http://localhost:8001/swagger-ui.html#)
![image](https://user-images.githubusercontent.com/33939388/197271381-3cce4710-cd66-4177-ba3c-01120f92dc03.png)

> Jacoco Coverage (Up to 70%)(http://localhost:63342/transfer-of-funds-api/target/site/jacoco/index.html)
![image](https://user-images.githubusercontent.com/33939388/197271466-f20083c1-88fd-4c48-9fb5-9a67e312a3df.png)
## Features

- Cypher password.
- User_id generated using UUID.
- Generate tokens.
- Validations added on every field.

## Installation Step

	Clone the repository (https://github.com/shy019/user-registry.git).

`$ git clone https://github.com/shy019/user-registry.git .`

Once you are there, follow the next steps:

`$ mvn clean package`

With these steps we are able to install all the features and files that the project required to run.
Finally we can start the application with the follow command on the port 8001 using your editor of choice.

## Build with

Technologies used in the project:
- Java 8
- Spring Boot
- H2 DB
- Swagger
- JPA
- Git
- Junit 5
- Mockito
- Jacoco
- Java Security and Crypto for encrypts
- Jwt

## Structure

> These are the following responses the app manages:
```java
public class UserRequestDto {

	@Size(min = 3, message = "UserName must have more than 3 characters.")
	@NotNull(message = "Name is required.")
	private String name;

	@Email(regexp = "^([_A-Za-z0-9-+]{3,7}+@(dominio.cl|dominio.cl))$", message = "The email must have to apply next format: aaaaaaa@dominio.cl")
	@NotNull(message = "Email is required.")
	private String email;

	@Size(min = 3, message = "Password must have more than 3 characters.")
	@NotNull(message = "Password is required")
	private String password;

	@Valid
	@NotNull(message = "Phones are required.")
	private Set<PhoneRequestDto> phones;
  
  public class PhoneRequestDto {

	@Size(min = 7, message = "The number must have more than 6 digits.")
	@NotNull(message = "Number is required.")
	private String number;

	@NotNull(message = "City code is required.")
	private String citycode;

	@NotNull(message = "Country code is required.")
	private String countrycode;
```
Also there are some validations:

```java
		if (!userRepository.findUserByEmail(userRequestDto.getEmail()).isEmpty()) {
			logger.error("Email is already registered for email {}.".concat(userRequestDto.getEmail()));
			throw new UserException("Email is already registered.");
		}

		if (userRequestDto.getPhones().stream()
				.anyMatch(phone -> !phoneRepository.findPhoneByNumber(phone.getNumber()).isEmpty())) {
			logger.error("Phone number is already registered");
			throw new UserException("Any of the phone numbers is already registered.");
		}
```

## Author
Made by Cristian Pérez
