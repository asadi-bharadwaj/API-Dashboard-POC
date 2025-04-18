import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [apiList, setApiList] = useState([]);
  const [responseData, setResponseData] = useState(null);
  const [loadingId, setLoadingId] = useState(null);
  const [error, setError] = useState(null);
  const [darkMode, setDarkMode] = useState(() => {
    return localStorage.getItem("theme") === "dark";
  });

  useEffect(() => {
    fetchApis();
    document.body.className = darkMode ? "dark" : "light";
  }, [darkMode]);

  const fetchApis = async () => {
    try {
      const res = await axios.get("http://localhost:9090/apis");
      setApiList(res.data);
    } catch (err) {
      setError("Failed to fetch API list.");
    }
  };

  const invokeApi = async (id) => {
    setLoadingId(id);
    setResponseData(null);
    setError(null);
    try {
      const res = await axios.post(`http://localhost:9090/apis/invoke/${id}`);
      setResponseData(res.data);
    } catch (err) {
      setError(`Error invoking API with id ${id}`);
    } finally {
      setLoadingId(null);
    }
  };

  const toggleTheme = () => {
    const newTheme = !darkMode;
    setDarkMode(newTheme);
    localStorage.setItem("theme", newTheme ? "dark" : "light");
  };

  return (
    <div className="container">
      <div className="header-container">
        <h1 className="header">🚀 API Dashboard</h1>
        <button className="theme-toggle-btn" onClick={toggleTheme}>
          {darkMode ? "🌞 Light Mode" : "🌙 Dark Mode"}
        </button>
      </div>

      <div className="card-grid">
        {apiList.map((api) => (
          <div key={api.id} className="card">
            <h2>{api.service}</h2>
            <p><strong>Method:</strong> {api.method}</p>
            <p><strong>URL:</strong> {api.url}</p>
            <p><strong>Description:</strong> {api.description}</p>
            <button
              onClick={() => invokeApi(api.id)}
              disabled={loadingId === api.id}
              className="btn"
            >
              {loadingId === api.id ? "Calling..." : "Call API"}
            </button>
          </div>
        ))}
      </div>

      {responseData && (
        <div className="response-box success">
          <h3>✅ API Response:</h3>
          <pre>{JSON.stringify(responseData, null, 2)}</pre>
        </div>
      )}

      {error && (
        <div className="response-box error">
          <strong>Error:</strong> {error}
        </div>
      )}
    </div>
  );
}

export default App;
