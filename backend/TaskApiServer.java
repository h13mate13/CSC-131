import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskApiServer {
    private static final int PORT = 8080;
    private static final TaskManager taskManager = TaskManager.getInstance();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/tasks", TaskApiServer::handleTasks);

        server.setExecutor(null);
        server.start();

        System.out.println("TaskFlow Java backend running at http://localhost:" + PORT);
    }

    private static void handleTasks(HttpExchange exchange) throws IOException {
        addCorsHeaders(exchange);

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            if (method.equalsIgnoreCase("GET") && path.equals("/tasks")) {
                handleGetTasks(exchange);
                return;
            }

            if (method.equalsIgnoreCase("POST") && path.equals("/tasks")) {
                handleCreateTask(exchange);
                return;
            }

            if (method.equalsIgnoreCase("DELETE") && path.matches("/tasks/\\d+")) {
                handleDeleteTask(exchange, path);
                return;
            }

            if (method.equalsIgnoreCase("PUT") && path.matches("/tasks/\\d+")) {
                handleEditTask(exchange, path);
                return;
            }

            if (method.equalsIgnoreCase("POST") && path.matches("/tasks/\\d+/complete")) {
                handleCompleteTask(exchange, path);
                return;
            }

            sendJson(exchange, 404, "{\"error\":\"Route not found\"}");
        } catch (Exception exception) {
            sendJson(exchange, 500, "{\"error\":\"Server error\"}");
        }
    }

    private static void handleGetTasks(HttpExchange exchange) throws IOException {
        ArrayList<Task> tasks = taskManager.getTasks();

        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            json.append("{");
            json.append("\"title\":\"").append(escapeJson(task.getTitle())).append("\",");
            json.append("\"description\":\"").append(escapeJson(task.getDescription())).append("\",");
            json.append("\"dueDate\":\"").append(escapeJson(task.getDueDate())).append("\",");
            json.append("\"priority\":\"").append(escapeJson(task.getPriority())).append("\",");
            json.append("\"category\":\"").append(escapeJson(task.getCategory())).append("\",");
            json.append("\"completed\":").append(task.isCompleted());
            json.append("}");

            if (i < tasks.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        sendJson(exchange, 200, json.toString());
    }

    private static void handleCreateTask(HttpExchange exchange) throws IOException {
        Map<String, String> params = parseFormData(exchange);

        String title = params.getOrDefault("title", "").trim();
        String description = params.getOrDefault("description", "").trim();
        String dueDate = params.getOrDefault("dueDate", "").trim();
        String priority = params.getOrDefault("priority", "").trim();
        String category = params.getOrDefault("category", "").trim();

        if (title.isEmpty()) {
            sendJson(exchange, 400, "{\"error\":\"Title cannot be blank\"}");
            return;
        }

        Task task = new Task(title, description, dueDate, priority, category);
        taskManager.addTask(task);

        sendJson(exchange, 201, "{\"message\":\"Task created\"}");
    }

    private static void handleDeleteTask(HttpExchange exchange, String path) throws IOException {
        int index = getIndexFromPath(path);
        boolean deleted = taskManager.deleteTask(index);

        if (deleted) {
            sendJson(exchange, 200, "{\"message\":\"Task deleted\"}");
        } else {
            sendJson(exchange, 404, "{\"error\":\"Task not found\"}");
        }
    }

    private static void handleEditTask(HttpExchange exchange, String path) throws IOException {
        int index = getIndexFromPath(path);
        Map<String, String> params = parseFormData(exchange);

        String title = params.getOrDefault("title", "").trim();
        String description = params.getOrDefault("description", "").trim();
        String dueDate = params.getOrDefault("dueDate", "").trim();
        String priority = params.getOrDefault("priority", "").trim();
        String category = params.getOrDefault("category", "").trim();

        if (title.isEmpty()) {
            sendJson(exchange, 400, "{\"error\":\"Title cannot be blank\"}");
            return;
        }

        boolean edited = taskManager.editTask(index, title, description, dueDate, priority, category);

        if (edited) {
            sendJson(exchange, 200, "{\"message\":\"Task edited\"}");
        } else {
            sendJson(exchange, 404, "{\"error\":\"Task not found\"}");
        }
    }

    private static void handleCompleteTask(HttpExchange exchange, String path) throws IOException {
        int index = getIndexFromPath(path);
        boolean completed = taskManager.markTaskComplete(index);

        if (completed) {
            sendJson(exchange, 200, "{\"message\":\"Task completed\"}");
        } else {
            sendJson(exchange, 404, "{\"error\":\"Task not found\"}");
        }
    }

    private static int getIndexFromPath(String path) {
        String[] parts = path.split("/");
        return Integer.parseInt(parts[2]);
    }

    private static Map<String, String> parseFormData(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        Map<String, String> params = new HashMap<>();

        if (body.isEmpty()) {
            return params;
        }

        String[] pairs = body.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);

            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = "";

            if (keyValue.length > 1) {
                value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
            }

            params.put(key, value);
        }

        return params;
    }

    private static void sendJson(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.close();
    }

    private static void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    private static String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}