# API-Dashboard-POC
A full-stack web application to manage and test APIs with a user-friendly dashboard interface. This project includes both frontend (React) and backend (Node.js/Express) in a single repository.

---

## 📁 Project Structure

├── backend/ # Node.js + Express APIs ├── frontend/ # React.js frontend ├── README.md # You are here


---

## 🧩 Features

- 🌐 React frontend for displaying and calling APIs
- ⚙️ Node.js backend to handle API routes
- 🌓 Dark Mode / Light Mode toggle
- 🔄 Easy testing of GET/POST services from the dashboard

---

## 🔧 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
cd backend
npm install
npm start
Backend will run on http://localhost:9090 or the port specified in your app.
cd ../frontend
npm install

npm start
Frontend will run on http://localhost:3000

🌓 Dark Mode Support
You can switch between Light Mode and Dark Mode using the toggle button in the top-right corner of the UI. This is managed using React state and conditional CSS.


🧪 Sample APIs

Name	Method	URL	Description
Service 1	GET	http://localhost:8082/api/v1	Get service 1
Post Service	POST	http://localhost:8081/invoke/1	Create new post
You can modify or extend these services by updating the backend and frontend configuration.

🙌 Contributions
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.


✨ Author
Built with 💙 by Bharadwaj
