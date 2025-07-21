# Contact Manager - Java + MySQL

This is a simple **Contact Manager** desktop application built in **Java** using **JDBC (MySQL Connector)**. It allows users to **add, view, update, and delete contacts** from a MySQL database.

---

## 🚀 Features

- Add new contacts
- View existing contacts
- Update contact details
- Delete contacts
- MySQL database connectivity via JDBC
- Simple console-based UI

---

## 🛠️ Technologies Used

- Java (JDK 8+)
- JDBC (MySQL Connector/J)
- MySQL
- VS Code (recommended)
- Windows PowerShell or Command Prompt

---

## 🔧 How to Run

### ✅ Step 1: Clone the Repository

```bash
git clone https://github.com/Ayesha0702/ContactManager.git
cd ContactManager
✅ Step 2: Setup MySQL
Create a database called contactdb

Create a table:

CREATE TABLE contacts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15)
);
Update your DB credentials inside ContactManager.java

✅ Step 3: Compile and Run
Make sure the MySQL Connector JAR is in the lib/ folder.


javac -cp ".;lib/mysql-connector-j-9.3.0.jar" ContactManager.java
java -cp ".;lib/mysql-connector-j-9.3.0.jar" ContactManager
Or use the included run.bat file (on Windows):


./run.bat
📂 Project Structure
ContactManagerProject/
│
├── ContactManager.java       # Main Java class
├── run.bat                   # Windows batch file to compile & run
├── lib/                      # MySQL JDBC driver
├── .gitignore
└── .vscode/                  # VS Code launch config
🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

📄 License
This project is licensed under the MIT License.

👩‍💻 Author
Pathan Mohmadi Aysha Afzal
GitHub: Ayesha0702

---

Let me know if you want me to auto-create a version with your **exact DB table**, **screenshots**, or **future features section**.
