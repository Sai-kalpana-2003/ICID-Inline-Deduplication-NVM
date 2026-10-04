# Efficient In-Line Data Deduplication for Non-Volatile Memory Storage Using I/O Causality

B.Tech Major Project (2024-25), Dept. of CSE, Guru Nanak Institute of Technology (JNTUH)

**Team:** B. Venkata Abhinav (21831A0530), D. Sai Kalpana (21831A0545), D. Srilatha (21831A0547)
**Guide:** Mrs. P. Anusha, Assistant Professor, CSE

## Overview
ICID (I/O Causality-based In-line Deduplication) detects duplicate data *before* it is written,
instead of chunking and fingerprint-indexing it afterwards. This repository contains a Java web
application that demonstrates inline deduplication: every upload is checked against the data already
stored, and redundant content is rejected before it is written.

**How the check works** (`ManageDataSetServiceImpl`)
1. **Exact duplicate?** A SHA-256 fingerprint of the normalized text is looked up. Match -> rejected (100%).
2. **Near duplicate?** The new text is compared with every stored item using character 3-gram
   Jaccard similarity (`SimilarityUtil`). If the best match is **>= 60%** -> rejected.
3. **Otherwise** the content is unique and is stored.

Every check is logged, and the *Statistics* page shows how many uploads fell in each similarity band.

## Features
- User registration and login (passwords hashed with salted PBKDF2, never stored in plain text)
- Upload a `.txt` file or paste text, with an immediate similarity result
- View your stored data
- Similarity statistics page
- Login filter protecting all inner pages

## Tech Stack
Java 8+, JSP, Servlets (J2EE, `javax.servlet` 4.0), JSTL, MySQL, Maven, Apache Tomcat 9

## Project Structure
```
database/iodata.sql                  MySQL schema (register, files, similarity_log)
src/main/java/
  bean/        UserBean, DataSetBean, UploadResult
  util/        DBConnection, SimilarityUtil, PasswordUtil
  service/     UserService, ManageDataSetService, AdminManageService (+ Impl classes)
  controller/  Register, Login, Logout, UploadDataset, MyPickedData, SimilarityValue
  filter/      AuthFilter
src/main/resources/db.properties     database settings
src/main/webapp/                     JSP pages, CSS
docs/                                project report (PDF), project document (DOCX),
                                     and original-sample-code/ (code listed in the document)
```

## Setup
1. Install **JDK 8+**, **Maven**, **MySQL**, and **Apache Tomcat 9** (Tomcat 10+ will not work: it uses `jakarta.*`).
2. Create the database: `mysql -u root -p < database/iodata.sql`
3. Edit `src/main/resources/db.properties` with your MySQL user and password.
4. Build: `mvn clean package` (produces `target/IoDataDepli.war`).
5. Copy the WAR into Tomcat's `webapps/` folder, start Tomcat, and open
   `http://localhost:8080/IoDataDepli/`.

In Eclipse: *File > Import > Existing Maven Projects*, then *Run As > Run on Server* (Tomcat 9).

## Using it
Register -> log in -> **Upload**. Upload a text file, then upload it again (or a lightly edited copy):
the second upload is rejected with its similarity percentage.

## Documentation
- [Project report (PDF)](docs/Major_Document.pdf)
- [Project document (DOCX)](docs/Project_Document.docx)
