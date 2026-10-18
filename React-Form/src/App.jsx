
import { useState, useEffect } from "react";
import "./App.css";

const API = "http://localhost:8082/student";

const initialValues = {
  firstname: "",
  lastname: "",
  email: "",
  number: "",
  contact: "",
  gender: "",
  languages: [],
  subject: "",
  resumeName: "",
  date: "",
  about: "",
};

function App() {
  const [values, setValues] = useState(initialValues);
  const [students, setStudents] = useState([]);
  const [message, setMessage] = useState("");
  const [editingId, setEditingId] = useState(null);

  // Fetch all records from MySQL
  const fetchStudents = async () => {
    try {
      const response = await fetch(`${API}/getAll`);

      if (!response.ok) {
        throw new Error("Failed to fetch students");
      }

      const data = await response.json();
      setStudents(data);
    } catch (error) {
      console.error(error);
      setMessage("Unable to fetch students. Check Spring Boot.");
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  // Handle all input fields
  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;

    if (type === "checkbox") {
      setValues((prev) => ({
        ...prev,
        languages: checked
          ? [...prev.languages, value]
          : prev.languages.filter((item) => item !== value),
      }));
    } else if (type === "file") {
      setValues((prev) => ({
        ...prev,
        resumeName: files[0]?.name || "",
      }));
    } else {
      setValues((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  // Save all fields in MySQL
 const handleSubmit = async (e) => {
  e.preventDefault();
  setMessage("");

  const student = {
    ...values,
    languages: JSON.stringify(values.languages),
    date: values.date || null,
  };

  const url = editingId
    ? `${API}/update/${editingId}`
    : `${API}/add`;

  const method = editingId ? "PUT" : "POST";

  try {
    const response = await fetch(url, {
      method,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(student),
    });

    if (!response.ok) {
      throw new Error(await response.text());
    }

    setMessage(
      editingId
        ? "Student updated successfully!"
        : "Student saved successfully!"
    );

    setValues(initialValues);
    setEditingId(null);
    e.target.reset();

    await fetchStudents();
  } catch (error) {
    console.error("Save/Update error:", error);
    setMessage("Error: " + error.message);
  }
};
  const resetForm = (e) => {
    e.preventDefault();
    setValues(initialValues);
    setMessage("");
    e.currentTarget.form.reset();
  };

  const handleEdit = (student) => {
  let languages = [];

  try {
    languages = student.languages
      ? JSON.parse(student.languages)
      : [];
  } catch {
    languages = student.languages
      ? student.languages.split(",")
      : [];
  }

  setValues({
    firstname: student.firstname || "",
    lastname: student.lastname || "",
    email: student.email || "",
    number: student.number || "",
    contact: student.contact || "",
    gender: student.gender || "",
    languages: Array.isArray(languages) ? languages : [],
    subject: student.subject || "",
    resumeName: student.resumeName || "",
    date: student.date || "",
    about: student.about || "",
  });

  setEditingId(student.id);
  setMessage("Editing student ID: " + student.id);

  window.scrollTo({ top: 0, behavior: "smooth" });
};

const handleDelete = async (id) => {
  if (!window.confirm("Are you sure you want to delete this student?")) {
    return;
  }

  try {
    const response = await fetch(`${API}/delete/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Failed to delete student");
    }

    setMessage("Student deleted successfully!");

    if (editingId === id) {
      setEditingId(null);
      setValues(initialValues);
    }

    await fetchStudents();
  } catch (error) {
    console.error("Delete error:", error);
    setMessage("Error deleting student: " + error.message);
  }
};

  return (
    <div className="container">
      <h1>Student Registration Form</h1>

      <form onSubmit={handleSubmit}>
        <label>First Name *</label>
        <input
          name="firstname"
          value={values.firstname}
          onChange={handleChange}
          required
        />

        <label>Last Name *</label>
        <input
          name="lastname"
          value={values.lastname}
          onChange={handleChange}
          required
        />

        <label>Email *</label>
        <input
          type="email"
          name="email"
          value={values.email}
          onChange={handleChange}
          required
        />

        <label>ID Number</label>
        <input
          type="number"
          name="number"
          value={values.number}
          onChange={handleChange}
        />

        <label>Contact *</label>
        <input
          type="tel"
          name="contact"
          value={values.contact}
          onChange={handleChange}
          required
        />

        <label>Gender</label>
        {["Male", "Female", "Other"].map((item) => (
          <label key={item}>
            <input
              type="radio"
              name="gender"
              value={item}
              checked={values.gender === item}
              onChange={handleChange}
            />
            {item}
          </label>
        ))}

        <label>Languages Known</label>
        {["Marathi", "Hindi", "English", "Telugu"].map((item) => (
          <label key={item}>
            <input
              type="checkbox"
              name="languages"
              value={item}
              checked={values.languages.includes(item)}
              onChange={handleChange}
            />
            {item}
          </label>
        ))}

        <label>Subject</label>
        <select
          name="subject"
          value={values.subject}
          onChange={handleChange}
        >
          <option value="">Select a subject</option>
          <option value="Math">Math</option>
          <option value="English">English</option>
          <option value="Physics">Physics</option>
        </select>

        <label>Resume</label>
        <input
          type="file"
          accept=".pdf,.doc,.docx"
          onChange={handleChange}
        />

        <label>Date</label>
        <input
          type="date"
          name="date"
          value={values.date}
          onChange={handleChange}
        />

        <label>About</label>
        <textarea
          name="about"
          value={values.about}
          onChange={handleChange}
        />

        <button type="button" onClick={() => {
  setValues(initialValues);
  setEditingId(null);
  setMessage("");
  document.querySelector("form")?.reset();
}}>
  {editingId ? "Cancel Edit" : "Reset"}
</button>

<button type="submit">
  {editingId ? "Update Student" : "Submit"}
</button>
      </form>

      {message && <p>{message}</p>}

   <hr />
   <hr />

      <h2>Student Records from MySQL</h2>

      <button type="button" onClick={fetchStudents}>
        Refresh Records
      </button>

      <div style={{ overflowX: "auto" }}>
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>ID Number</th>
              <th>Contact</th>
              <th>Gender</th>
              <th>Languages</th>
              <th>Subject</th>
              <th>Resume Name</th>
              <th>Date</th>
              <th>About</th>
              <th>Action Data</th>
              
            </tr>
          </thead>

          <tbody>
            {students.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.firstname}</td>
                <td>{s.lastname}</td>
                <td>{s.email}</td>
                <td>{s.number}</td>
                <td>{s.contact}</td>
                <td>{s.gender}</td>
                <td>{s.languages}</td>
                <td>{s.subject}</td>
                <td>{s.resumeName}</td>
                <td>{s.date}</td>
                <td>{s.about}</td>
                <td>
  <button
    type="button"
    onClick={() => handleEdit(s)}
  >
    Edit
  </button>

  <button
    type="button"
    onClick={() => handleDelete(s.id)}
  >
    Delete
  </button>
</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;
