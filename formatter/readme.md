# XML to JSON Converter

This project is a professional REST API developed with **Spring Boot** designed to convert XML data into JSON format. It uses the **org.json** library to handle dynamic XML structures (non-fixed schemas) efficiently.

The API provides two main ways to perform the conversion:
1. **File Upload**: Processing physical `.xml` files.
2. **Raw Text**: Processing XML code pasted directly as a string.

---

## Usage Guide

### 1. Convert via File Upload
Use this method to process existing `.xml` files from your computer.

* **Endpoint**: `POST http://localhost:8080/api/parse/xml-json-file`
* **Instructions (Postman)**:
    1. Set the request method to **POST**.
    2. Go to the **Body** tab and select **form-data**.
    3. In the **Key** column, type `file`.
    4. Hover over the `file` key cell, click the dropdown arrow on the right, and change the type from "Text" to **File**.
    5. In the **Value** column, click "Select Files" and choose your XML file.
    6. Click **Send**.

### 2. Convert via Raw XML Text
Use this method to quickly test XML snippets by pasting them directly.

* **Endpoint**: `POST http://localhost:8080/api/parse/xml-json-raw`
* **Instructions (Postman)**:
    1. Set the request method to **POST**.
    2. Go to the **Body** tab and select **raw**.
    3. On the right-side dropdown, change the format from "Text" to **XML**.
    4. Paste your XML code into the text area.
    5. Click **Send**.

---

## Technical Stack
* **Java 17+**
* **Spring Boot 3.x**
* **Maven** (Dependency management)
* **JSON (org.json)**: Core library for XML/JSON transformation.

---

## Setup and Installation
1. Clone the repository: `git clone <>`
2. Navigate to the project folder: `cd `
3. Run the application using Maven: `./mvnw spring-boot:run`