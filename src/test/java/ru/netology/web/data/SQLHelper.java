package ru.netology.web.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import lombok.SneakyThrows;

public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getStatusPaymentEntity() {
        var requestSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";

        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, requestSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getStatusCreditRequestEntity() {
        var requestSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, requestSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getIdOrderEntity() {
        var requestSQL = "SELECT id FROM order_entity ORDER BY created DESC LIMIT 1;";

        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, requestSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static int getAmountPaymentEntity() {
        var requestSQL = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1;";

        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, requestSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void cleanDataBase() {
        try (var conn = getConn()) {
            QUERY_RUNNER.execute(conn, "DELETE FROM credit_request_entity");
            QUERY_RUNNER.execute(conn, "DELETE FROM order_entity");
            QUERY_RUNNER.execute(conn, "DELETE FROM payment_entity");
        }
    }

}
