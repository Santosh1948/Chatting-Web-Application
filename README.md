# 💬 Java Chatting Application (Client–Server)

A **desktop-based real-time chat application** built using **Java (Swing & AWT)** and **Socket Programming**.  
This project demonstrates **client–server communication**, **GUI design**, and **multithreading** in Java.

---

## 📌 Features

- 🔁 Real-time two-way communication
- 🖥️ Separate **Client** and **Server** applications
- 🎨 WhatsApp-style chat UI using Java Swing
- ⏰ Message timestamp
- 🧵 Multithreaded networking (UI never freezes)
- 📜 Scrollable chat window
- ❌ Exit button support

---

## 🛠️ Technologies Used

- **Java (JDK 8+)**
- **Swing & AWT** – GUI
- **Socket Programming**
- **Multithreading**
- **I/O Streams (DataInputStream & DataOutputStream)**

---

## 📂 Project Structure

chatting.application
│
├── Server.java
├── Client.java
├── icons/
│ ├── 1.png
│ ├── 2.png
│ ├── 3.png
│ ├── 3icon.png
│ ├── phone.png
│ └── video.png


---

## 🚀 How to Run the Project

### ✅ Step 1: Compile
Make sure Java is installed:
```bash
java -version

✅ Step 2: Run Server First

java chatting.application.Server

✅ Step 3: Run Client

java chatting.application.Client

⚠️ Important:
Server must be started before the Client.

🔄 How It Works

- listens on port 9999

-Client connects using 127.0.0.1

-Messages are sent using DataOutputStream

-Messages are received using DataInputStream

-UI updates are handled safely using SwingUtilities.invokeLater()

📸 UI Preview

-Chat bubbles aligned left/right

-Timestamp below each message

-Scrollable chat area

-Clean WhatsApp-like header design

📚 Learning Outcomes

-Understanding Client–Server Architecture

-Java Socket Programming

-GUI handling with Swing

-Thread management in Java

-Real-time data transfer

🔮 Future Enhancements

😄 Emoji support

👨‍💻 Author

Santosh
Engineering Student | Java Developer

🔗 GitHub: https://github.com/Santosh1948