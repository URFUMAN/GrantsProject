package com.mycompany.grantsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс для работы с базой данных SQLite.
 */
public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:grants.db";

    /**
     * Устанавливает соединение с базой данных.
     *
     * @return объект Connection или null при ошибке
     */
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Подключено к SQLite");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Создаёт таблицы в базе данных (если они ещё не существуют).
     */
    public static void createTables() {

        String createBusinessTypes = """
            CREATE TABLE IF NOT EXISTS business_types (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT UNIQUE
            );
        """;

        String createGrants = """
            CREATE TABLE IF NOT EXISTS grants (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                company_name TEXT,
                street TEXT,
                fiscal_year INTEGER,
                grant_amount REAL,
                jobs_created INTEGER,
                business_type_id INTEGER,
                FOREIGN KEY (business_type_id) REFERENCES business_types(id)
            );
        """;

        try (Connection conn = connect();
             var stmt = conn.createStatement()) {

            stmt.execute(createBusinessTypes);
            stmt.execute(createGrants);

            System.out.println("Таблицы созданы");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохраняет список грантов в базу данных.
     *
     * <p>Алгоритм:</p>
     * <ol>
     *     <li>Добавление уникального типа бизнеса</li>
     *     <li>Получение его ID</li>
     *     <li>Сохранение гранта с внешним ключом</li>
     * </ol>
     *
     * @param grants список грантов
     */
    public static void insertData(List<Grant> grants) {

        String insertBusinessType = "INSERT OR IGNORE INTO business_types(name) VALUES(?)";
        String getBusinessTypeId = "SELECT id FROM business_types WHERE name = ?";
        String insertGrant = """
            INSERT INTO grants(company_name, street, fiscal_year, grant_amount, jobs_created, business_type_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = connect()) {

            for (Grant g : grants) {

                // добавление типа бизнеса
                try (var pstmt = conn.prepareStatement(insertBusinessType)) {
                    pstmt.setString(1, g.getBusinessType());
                    pstmt.executeUpdate();
                }

                // получение ID типа бизнеса
                int businessTypeId = 0;
                try (var pstmt = conn.prepareStatement(getBusinessTypeId)) {
                    pstmt.setString(1, g.getBusinessType());
                    var rs = pstmt.executeQuery();
                    if (rs.next()) {
                        businessTypeId = rs.getInt("id");
                    }
                }

                // добавление гранта
                try (var pstmt = conn.prepareStatement(insertGrant)) {
                    pstmt.setString(1, g.getCompanyName());
                    pstmt.setString(2, g.getStreet());
                    pstmt.setInt(3, g.getFiscalYear());
                    pstmt.setDouble(4, g.getGrantAmount());
                    pstmt.setInt(5, g.getJobsCreated());
                    pstmt.setInt(6, businessTypeId);
                    pstmt.executeUpdate();
                }
            }

            System.out.println("Данные загружены в БД");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Получает среднее количество рабочих мест по каждому фискальному году.
    *
    * @return список строк вида "год - среднее значение"
    */
    public static void getAverageJobsByYear() {

        String sql = """
            SELECT fiscal_year, AVG(jobs_created) AS avg_jobs
            FROM grants
            GROUP BY fiscal_year
            ORDER BY fiscal_year
        """;

        try (Connection conn = connect();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            System.out.println("Среднее количество рабочих мест по годам:");

            while (rs.next()) {
                int year = rs.getInt("fiscal_year");
                double avg = rs.getDouble("avg_jobs");

                System.out.println(year + " -> " + avg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}