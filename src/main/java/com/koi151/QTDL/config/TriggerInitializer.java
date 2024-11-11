package com.koi151.QTDL.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Configuration
public class TriggerInitializer {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner initializeTriggers() {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {

                // Danh sách các trigger và đường dẫn file tương ứng
                String[][] triggerFiles = {
                    {"before_supplier_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_supplier.sql"},
                    {"before_product_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_product.sql"},
                    {"before_category_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_category.sql"},
                    {"before_agency_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_agency.sql"},
                    {"before_employee_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_employee.sql"},
                    {"before_role_insert", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_insert_role.sql"},

                    // Trigger trước khi update
                    {"before_role_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_role.sql"},
                    {"before_employee_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_employee.sql"},
                    {"before_category_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_category.sql"},
                    {"before_agency_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_agency.sql"},
                    {"before_supplier_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_supplier.sql"},
                    {"before_product_update", "C:\\Users\\antho\\Downloads\\6_tinh_nang\\triggers\\trigger_before_update_product.sql"}
                };

                // Duyệt qua danh sách trigger và tạo trigger nếu chưa tồn tại
                for (String[] triggerData : triggerFiles) {
                    String triggerName = triggerData[0];
                    String filePath = triggerData[1];

                    if (!triggerExists(conn, triggerName)) {
                        String triggerSQL = new String(Files.readAllBytes(Paths.get(filePath)));

                        // Loại bỏ tất cả các dòng chứa `DELIMITER` hoặc `//` trong nội dung trigger
                        triggerSQL = triggerSQL.replaceAll("(?i)DELIMITER //", "")
                            .replaceAll("(?i)DELIMITER ;", "")
                            .replaceAll("//", "")
                            .trim();

                        try (Statement stmt = conn.createStatement()) {
                            stmt.execute(triggerSQL);
                            System.out.println("Trigger '" + triggerName + "' đã được tạo.");
                        }
                    } else {
                        System.out.println("Trigger '" + triggerName + "' đã tồn tại.");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * Kiểm tra xem trigger đã tồn tại trong cơ sở dữ liệu hay chưa
     *
     * @param conn        kết nối cơ sở dữ liệu
     * @param triggerName tên của trigger cần kiểm tra
     * @return true nếu trigger đã tồn tại, false nếu chưa tồn tại
     * @throws Exception nếu có lỗi khi truy vấn cơ sở dữ liệu
     */
    private boolean triggerExists(Connection conn, String triggerName) throws Exception {
        String query = "SELECT TRIGGER_NAME FROM information_schema.TRIGGERS " +
            "WHERE TRIGGER_NAME = '" + triggerName + "' " +
            "AND TRIGGER_SCHEMA = DATABASE();";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            return rs.next();
        }
    }
}
