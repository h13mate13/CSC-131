const API_URL = "http://localhost:8080";

const titleInput = document.getElementById("taskTitle");
const descriptionInput = document.getElementById("taskDescription");
const dueDateInput = document.getElementById("taskDueDate");
const priorityInput = document.getElementById("taskPriority");
const categoryInput = document.getElementById("taskCategory");
const addTaskButton = document.getElementById("addTaskButton");
const taskContainer = document.getElementById("taskContainer");

addTaskButton.addEventListener("click", addTask);

window.addEventListener("load", loadTasks);

async function loadTasks() {
  try {
    const response = await fetch(`${API_URL}/tasks`);
    const tasks = await response.json();
    displayTasks(tasks);
  } catch (error) {
    taskContainer.innerHTML = "<p>Could not connect to Java backend. Make sure TaskApiServer is running.</p>";
    console.error(error);
  }
}

async function addTask() {
  const title = titleInput.value.trim();
  const description = descriptionInput.value.trim();
  const dueDate = dueDateInput.value;
  const priority = priorityInput.value;
  const category = categoryInput.value.trim();

  if (title === "") {
    alert("Task title cannot be blank.");
    return;
  }

  const formData = new URLSearchParams();
  formData.append("title", title);
  formData.append("description", description);
  formData.append("dueDate", dueDate);
  formData.append("priority", priority);
  formData.append("category", category);

  await fetch(`${API_URL}/tasks`, {
    method: "POST",
    body: formData
  });

  titleInput.value = "";
  descriptionInput.value = "";
  dueDateInput.value = "";
  priorityInput.value = "Low Priority";
  categoryInput.value = "";

  loadTasks();
}

function displayTasks(tasks) {
  taskContainer.innerHTML = "";

  if (tasks.length === 0) {
    taskContainer.innerHTML = "<p>No tasks yet. Add your first task above!</p>";
    return;
  }

  tasks.forEach((task, index) => {
    const card = document.createElement("div");
    card.className = "task-card";

    const completedText = task.completed ? "Completed" : "Incomplete";

    card.innerHTML = `
      <div>
        <h3>${escapeHtml(task.title)}</h3>
        <p>${escapeHtml(task.description)}</p>
        <span>Due: ${escapeHtml(task.dueDate)}</span>
        <span>Priority: ${escapeHtml(task.priority)}</span>
        <span>Category: ${escapeHtml(task.category)}</span>
        <span>Status: ${completedText}</span>
      </div>

      <div class="task-actions">
        <button class="complete" onclick="completeTask(${index})">✓</button>
        <button class="edit" onclick="editTask(${index})">✎</button>
        <button class="delete" onclick="deleteTask(${index})">✕</button>
      </div>
    `;

    if (task.completed) {
      card.style.opacity = "0.65";
      card.querySelector("h3").style.textDecoration = "line-through";
    }

    taskContainer.appendChild(card);
  });
}

async function completeTask(index) {
  await fetch(`${API_URL}/tasks/${index}/complete`, {
    method: "POST"
  });

  loadTasks();
}

async function deleteTask(index) {
  const confirmDelete = confirm("Are you sure you want to delete this task?");

  if (!confirmDelete) {
    return;
  }

  await fetch(`${API_URL}/tasks/${index}`, {
    method: "DELETE"
  });

  loadTasks();
}

async function editTask(index) {
  const currentTasks = await fetch(`${API_URL}/tasks`).then(response => response.json());
  const task = currentTasks[index];

  const newTitle = prompt("New title:", task.title);
  if (newTitle === null || newTitle.trim() === "") {
    alert("Title cannot be blank.");
    return;
  }

  const newDescription = prompt("New description:", task.description);
  if (newDescription === null) return;

  const newDueDate = prompt("New due date:", task.dueDate);
  if (newDueDate === null) return;

  const newPriority = prompt("New priority:", task.priority);
  if (newPriority === null) return;

  const newCategory = prompt("New category:", task.category);
  if (newCategory === null) return;

  const formData = new URLSearchParams();
  formData.append("title", newTitle.trim());
  formData.append("description", newDescription.trim());
  formData.append("dueDate", newDueDate.trim());
  formData.append("priority", newPriority.trim());
  formData.append("category", newCategory.trim());

  await fetch(`${API_URL}/tasks/${index}`, {
    method: "PUT",
    body: formData
  });

  loadTasks();
}

function escapeHtml(value) {
  if (value === null || value === undefined) {
    return "";
  }

  return String(value)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#039;");
}