# 🧪 Junior Developer Trial Project Scope: Simple Task Management Application - Android(Kotlin) 


## ✅ Needed to be done to be considered a success:
- External packages ✔️
- gitignore always needed ✔️
- TaskService layer ✔️
- Unit test(s) for TaskService ✔️
- Base exception handlings ✔️
- Task input validation (max value input, characters) ✔️
- Task deletion confirm dialog ✔️
- Maybe a UX update for replacing the delete button next to the task description ?
- In ExampleUnitTest.kt: get the id from Task.kt ✔️
- Save just the task list (if app closed and reaopened, the selection is also saved)

---

This is an excellent idea for helping your junior developer explore different paths!  
A one-week scope needs to be **challenging but achievable**, focusing on **fundamental concepts** and showcasing **practical application**.  
The goal isn't a production-ready system, but rather a **functional prototype** that demonstrates understanding of the chosen stack.

---

## 🎯 Project Goal

To build a basic, functional task management application that allows users to create, view, mark as complete, and delete tasks. This project will serve as a practical exercise to explore a chosen programming language/platform/stack, understand its core concepts, and get a "feel" for the development environment.

---

## 🛠️ Setup and run instructions

1. Open Android Studio
2. Get the Project from GitHub:
   - On the welcome screen, click on "Get from VCS"
   - Paste the HTTPS clone URL of the GitHub repository. You can get this from the GitHub project page by clicking the green "Code" button and copying the URL
   - Choose where you want to save the project locally on your computer
   - Click "Clone"
3. Wait for Project to Clone and Open
4. Wait for Gradle to Sync:
   - Android Studio uses Gradle to build and manage the project. It will automatically start a "Gradle sync" process. 
   - You'll see a progress bar or "Gradle sync in progress..." message at the bottom
   - This is crucial. Wait for it to complete. It might download necessary libraries (dependencies) defined in the project, so make sure you have network connection
   - If you see a "Sync Now" banner, click it
5. Select a Run Configuration (Device/Emulator):
   - Look at the toolbar near the top. You'll see a dropdown menu that usually says "app" (or the name of the main module) and next to it, another dropdown for selecting the device.
   - Choose where to run the app:
     - Physical device:
       1. Enable Developer Options and USB Debugging on your Android phone/tablet. (Search online "enable developer options [your phone model]" if needed).
       2. Connect your device to your computer via USB
       3. Your device should appear in the dropdown. Select it. You might need to authorize debugging on the device itself.
     - Emulator(Virtual Device):
       1.  If you don't have an emulator, click the device dropdown and select "Device Manager"
       2. Click "Create device", choose a hardware profile, a system image (Android version), and click "Finish"
       3. Close the Device Manager. Your new emulator should now be in the device dropdown. Select it.
6. Run the App:
   - Click the green play button in the toolbar
   - Hopefully you don't need to configure the run
7. Have fun!

---

## ✅ Core Functionality (Minimum Viable Product)

### ✍️ Task Creation
- Input a task description (text)
- Store newly created tasks
- Assign each task a **unique identifier**

### 📋 Task Listing/Viewing
- Display all existing tasks
- Clearly show completed vs. incomplete tasks

### ✔️ Task Completion
- Mechanism to mark task as "completed"
- Reflect this in task listing

### ❌ Task Deletion
- Allow users to remove tasks from the list

### ⏺️ Task Listing
- Listin button on the top to listin the tasks
- Display all existing, completed, and incomplete tasks

---

## 🧠 Technical Requirements & Learning Objectives

### 🧩 Chosen Stack/Language

- Language: Kotlin
- UI Toolkit: 🚀 Jetpack Compose
- Key Jetpack Libraries: DataStore for storing tasks locally on users device

- **Frontend**  
  Focus: Client-side logic, component-based development, and local storage

- **Backend** 
  Focus: Server-side logic, API development, and database interaction

- **Fullstack** (a simplified hybrid approach)  
  Most challenging for one week, so use a lightweight setup

---

### 💾 Data Persistence

- DataStore Jetpack Library: storage for tasks to keep it simple
- Learn serialization, deserialization, and basic CRUD operations

---

### 🖥️ UI (if applicable)

- Just a simple Jetpack Compose UI for core function displaying
- Maybe later more staff on the main sreen and other screens in the future

---

### 🔁 Version Control

- Using Git for source control
- GitHub (public) repository ➡️ https://github.com/mpatrikik/STMPKotlinApp for code sharing
- 2 separate branches: 
  - master: main functionality
  - functions: implementations of new things and features

---

### 🧱 Code Structure & Readability

- Organize code with appropriate **file structure**
- Add basic **comments** to explain non-obvious logic
- Follow **language-specific coding conventions**

---

## 📦 Deliverables by End of Week(or when i'm done)

- ✅ **Working Application**: Implements all core functionalities
- 📂 **Source Code**: Publicly accessible Git repository
- 📝 **README.md File** including:
  - Project title and description
  - Chosen language/framework/stack
  - Setup and run instructions
  - Design choices and encountered challenges
  - Key learnings from the week
  - Potential future improvements

- 🎥 **Brief Demo (Optional but Recommended)**:  
  Short video/screenshare or walkthrough explaining the app and learnings

---

## 🧭 Guidance for the Junior Developer

- **Focus on Fundamentals**:  
  Syntax, data structures, control flow, basic libraries

- **Embrace Documentation**:  
  Read the official docs for your chosen tools

- **Utilize Resources**:  
  Tutorials, forums (Stack Overflow), and AI tools

- **Manage Time Well**:  
  Break problems into small steps. Partial completion with understanding is better than messy, rushed work.

- **Ask Questions**:  
  Asking for help shows engagement, not weakness

- **Reflect and Document**:  
  Use the README to tell the story of what you learned and struggled with

---

## 📊 Evaluation Criteria

- **Functionality**:  
  Does the app meet core MVP requirements?

- **Understanding**:  
  Does the dev show basic grasp of the chosen language/stack?

- **Problem-Solving**:  
  How did they tackle challenges?

- **Code Quality**:  
  Is the code reasonably clean and understandable for a junior level?

- **Lear**
